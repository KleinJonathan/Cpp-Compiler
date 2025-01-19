// Imports
import java.util.List;
import java.util.ArrayList;

public class MyListener extends CppBaseListener {
    // Speichern Verschiedener Zustände
    public AST wurzel;
    private AST acc;
    private Scope scope;
    private Scope rootScope;

    // Rückgabe des AST Wurzelknotens
    public AST getAST() {
        return wurzel;
    }
    // Rückgabe des Scopes
    public Scope getScope() {
        return rootScope;
    }


    //
    // Start - Startregel für den Listener
    //
    public void enterStart(CppParser.StartContext ctx) {
        scope = new Scope(null);
        rootScope = scope;
        wurzel = new AST("start", AST.Types.START, null, scope, null, true);
        acc = wurzel;

        // Hinzufügen der Standardtypen und Builtins
        String[] builtins = {
                "int",
                "bool",
                "char",
                "void",
                "print_int",
                "print_bool",
                "print_char",
                "return",
        };
        for (String builtin : builtins) {
            if (scope.resolve(builtin) != null) {
                throw new RuntimeException("Builtin: '" + builtin + "' already declared");
            }
            scope.bind(new SymbolBuiltin(builtin));
        }
    }
    public void exitStart(CppParser.StartContext ctx) {
        scope.print();
    }

    // Return - Es dürfen beliebig viele Return Statements in einer Funktion vorkommen, auch direkt hintereinander
    public void enterReturn(CppParser.ReturnContext ctx) {
        AST a = new AST("return", AST.Types.RETURN, acc, scope, null, false);
        acc.addChild(a);
        acc = a;
    }
    public void exitReturn(CppParser.ReturnContext ctx) {
        AST par = acc.prev;
        // Prüfen, ob Returntyp und Funktionsrückgabetyp übereinstimmen
        while (par.asttype != AST.Types.FUNCTION_DEF && par.prev != null) {
            par = par.prev;
        }
        // Zurücklaufen des AST bis zum Funktionsknoten zum Vergleich der Rückgabetypen
        if (par.asttype == AST.Types.FUNCTION_DEF) {
            if (par.rtype.equals("void")) {
                if (acc.kinder.size() > 0){
                    throw new RuntimeException("Return type: '" + acc.kinder.get(0).rtype + "' passt nicht zum Rückgabetyp der Funktion: '" + par.rtype + "'" + " - " + par.value);
                }
            } else {
                if (!par.rtype.equals(acc.kinder.get(0).rtype)) {
                    throw new RuntimeException("Return type: '" + acc.kinder.get(0).rtype + "' passt nicht zum Rückgabetyp der Funktion: '" + par.rtype + "'" + " - " + par.value);
                }
            }
        } else {
            throw new RuntimeException("Return nicht möglich");
        }
        // Speichern des Rückgabetyps im Returnknoten
        if (acc.kinder.size() > 0) {
            acc.rtype = acc.kinder.get(0).rtype;
        } else {
            acc.rtype = "void";
        }
        acc = acc.prev;
    }


    //
    // Scope / Open und Close
    //
    // Erstellen und schließen von Scopes
    public void enterOpenscope(CppParser.OpenscopeContext ctx) {
        scope = new Scope(scope);
        AST a = new AST("scope", AST.Types.SCOPE, acc, scope, null, false);
        acc.addChild(a);
        acc = a;
    }
    public void exitClosescope(CppParser.ClosescopeContext ctx) {
        scope.print();
        scope = scope.prev;
        acc = acc.prev;
    }


    //
    // Variable Asignment and Declaration
    //
    // Declaration Variable
    public void enterDeclvar(CppParser.DeclvarContext ctx) {
        // Bestimmen des Typs
        String type = ctx.getParent().getChild(0).getText();
        Boolean isConst = false;
        Symbol var;
        // Prüfen, ob Variable nicht existiert und Typ existieren
        if (scope.resolve(type) == null || scope.resolveLocal(ctx.ID().getText()) != null) {
            throw new RuntimeException("Typ: '" + type + "' nicht declariert oder Variable: '" + ctx.ID().getText() + "' bereits deklariert");
        }
        // Prüfen, ob es sich um eine Konstante handelt
        if (ctx.getParent().getChild(0).getText().equals("const")){
            isConst = true;
            var = new SymbolRefVariable(ctx.ID().getText(), new SymbolType(ctx.getParent().getChild(1).getText()), isConst);
        } else {
            var = new SymbolVariable(ctx.ID().getText(), new SymbolType(type), isConst);
        }
        // Erstellen des Symbols und Knostens und hinzufügen zum Scope und AST
        AST a = new AST(ctx.ID().getText(), AST.Types.DECLARATION, acc, scope, scope.bind(var), isConst);
        a.rtype = type;
        acc.addChild(a);
    }

    // Declaration Array
    public void enterDeclarray(CppParser.DeclarrayContext ctx) {
        // Bestimmen des Typs
        String type = ctx.getParent().getChild(0).getText();
        String name = ctx.ID().getText();
        AST.Types asttype = AST.Types.ARRAYDECLARATION;

        // Prüfen, ob Variable bereits und Typ existiert
        if (scope.resolve(type) == null || scope.resolveLocal(name) != null) {
            throw new RuntimeException("Typ: '" + type + "' nicht declariert oder Variable: '" + name + "' bereits deklariert");
        }
        // Erstellen des Symbols und Knostens und hinzufügen zum Scope und AST
        SymbolArray array = new SymbolArray(name, new SymbolType(type), false);
        AST a = new AST(name, asttype, acc, scope, scope.bind(array), false);
        acc.addChild(a);
        a.rtype = type;
        acc = a;
    }
    public void exitDeclarray(CppParser.DeclarrayContext ctx) {
        acc = acc.prev;
    }

    // Declaration Ref Variable
    public void enterDefrefvar(CppParser.DefrefvarContext ctx) {
        // Bestimmen des Typs
        AST.Types asttype = AST.Types.REF;
        String type = ctx.basetype().getText();
        Boolean isConst = false;
        // Prüfen, ob es sich um eine Konstante handelt
        if (ctx.const_() != null) {
            isConst = Boolean.valueOf(ctx.const_().getText());
        }
        // Prüfen, ob Variable bereits existiert und Typ existiert
        if (scope.resolve(type) == null || scope.resolveLocal(ctx.ID().getText()) != null) {
            throw new RuntimeException("Typ: '" + type + "' nicht deklariert oder Variable: '" + ctx.ID().getText() + "' bereits deklariert");
        }
        // Erstellen des Symbols und Knostens und hinzufügen zum Scope und AST
        SymbolRefVariable refvar = new SymbolRefVariable(ctx.ID().getText(), new SymbolType(type), isConst);
        AST a = new AST(ctx.ID().getText(), asttype, acc, scope, scope.bind(refvar), isConst);
        a.rtype = type;
        acc.addChild(a);
    }

    // Assignment Variable
    public void enterAssignvar(CppParser.AssignvarContext ctx) {
        AST.Types asttype = AST.Types.UNDEFINED;
        AST a = null;
        // Prüfen, ob eine alte oder neue Variable zugewiesen wird
        // => Zuweisung einer ALTEN Variable
        if (ctx.getParent() instanceof CppParser.AssignoldContext){
            asttype = AST.Types.ASSIGNOLD;
            // Prüfen, ob Variable existiert, ob es sich um eine Variable handelt und ob sie konstant ist
            Symbol s = scope.resolve(ctx.ID().getText());
            if (s == null) {
                throw new RuntimeException("Variable: '" + ctx.ID().getText() + "' nicht deklariert");
            }else if (ctx.getChild(2).getText().equals("[")){
                throw new RuntimeException(ctx.ID().getText() + " is an Array");
            } else if (s.isConst) {
                throw new RuntimeException("Variable: '" + ctx.ID().getText() + "' is konstant und kann nicht erneut zugewiesen werden");
            }
            // Erstellen des Knotens
            a = new AST(ctx.ID().getText(), asttype, acc, scope, null, scope.resolve(ctx.ID().getText()).isConst);
            a.rtype = s.type.getName();
        // => Zuweisung einer NEUEN Variable
        } else {
            String type = null;
            Boolean isConst = false;
            // Prüfen, ob es sich um eine Konstante handelt
            if (ctx.getParent().getChild(0).getText().equals("const")){
                type = ctx.getParent().getChild(1).getText();
                isConst = true;
            } else {
                type = ctx.getParent().getChild(0).getText();
            }
            // Prüfen, ob Variable schon existiert und ob Typ existiert
            if (scope.resolve(type) == null || scope.resolveLocal(ctx.ID().getText()) != null) {
                throw new RuntimeException("Variable: '" + ctx.ID().getText() + "' bereits deklariert oder Typ: '" + type + "' nicht gefunden");
            }
            // Prüfen, ob es sich um eine Referenz handelt
            // => Ist eine Referenz
            if (ctx.getParent().getChild(1).getText().equals("&") ||
                (ctx.getParent().getChild(2) != null
                && ctx.getParent().getChild(2).getText().equals("&"))
            ){
                asttype = AST.Types.REF;
                // Prüfen, ob es sich um eine Referenz auf eine Variable handelt
                if (ctx.expr().getClass() == CppParser.IdContext.class){
                    CppParser.IdContext id = (CppParser.IdContext) ctx.expr();
                    // Prüfen, ob zu referenzierende Variable existiert und ob die Typen übereinstimmt
                    if (!scope.resolve(id.getText()).type.getName().equals(type) &&
                        scope.resolve(id.getText()).isConst
                    ) {
                        throw new RuntimeException("Variable Ref: '" + ctx.ID().getText() + " und " + id.getText() + "' falsche Typen");
                    }
                    // Erstellen des Symbols und Knotens
                    SymbolRefVariable refvar = new SymbolRefVariable(
                        ctx.ID().getText(),
                        new SymbolType(type),
                        new SymbolVariable(id.getText(), new SymbolType(type), scope.resolve(id.getText()).isConst),
                        isConst
                    );
                    // Hinzufügen des Knotens zum AST
                    a = new AST(ctx.ID().getText(), asttype, acc, scope, scope.bind(refvar), isConst);
                    a.rtype = type;

                // Referenz auf eine Klassenvariable
                } else if (ctx.expr().getClass() == CppParser.ClassvarContext.class) {
                    // Ermitteln von Klassenname und Variablenname
                    CppParser.ClassvarContext classvar = (CppParser.ClassvarContext) ctx.expr();
                    String var = classvar.ID(1).getText();
                    Symbol object = scope.resolve(classvar.ID(0).getText());

                    // Prüfen, ob das Objekt existiert
                    if (object == null) {
                        throw new RuntimeException("Objekt/Variable: '" + classvar.ID(0).getText() + "' nicht deklariert");
                    }
                    // Prüfen, ob es sich um eine Klasse handelt
                    Symbol classSymbol = scope.resolve(object.type.getName());
                    if (classSymbol == null || !(classSymbol instanceof SymbolStruct)) {
                        throw new RuntimeException("Type: '" + object + "' nicht gefunden oder nicht eine struct/class");
                    }
                    // Prüfen, ob die Variable in der Klasse existiert
                    SymbolStruct structSymbol = (SymbolStruct) classSymbol;
                    Symbol member = structSymbol.resolveMember(var);
                    if (member == null) {
                        throw new RuntimeException("Member: '" + var + "' nicht gefunden in struct/class '" + object + "'");
                    }
                    // Prüfen, ob zu referenzierende Variable existiert und ob die Typen übereinstimmt
                    if (!member.type.equals(type) &&
                        member.isConst
                    ) {
                        throw new RuntimeException("Variable Ref: '" + ctx.ID().getText() + " und " + classvar.ID(1).getText() + "' falsche Typen");
                    }
                    // Erstellen des Symbols und Knotens
                    SymbolRefVariable refvar = new SymbolRefVariable(
                        ctx.ID().getText(),
                        new SymbolType(type),
                        new SymbolVariable(var, new SymbolType(type), member.isConst),
                        isConst
                    );
                    // Hinzufügen des Knotens zum AST
                    a = new AST(ctx.ID().getText(), asttype, acc, scope, scope.bind(refvar), isConst);
                    a.rtype = type;
                } else {
                    throw new RuntimeException("Referenz bindung nicht möglich");
                }
            } else {
                asttype = AST.Types.ASSIGNNEW;
                // Erstellen des Symbols und Knostens
                SymbolVariable var = new SymbolVariable(ctx.ID().getText(), new SymbolType(type), isConst);
                a = new AST(ctx.ID().getText(), asttype, acc, scope, scope.bind(var), isConst);
                a.rtype = type;
            }
        }
        // Hinzufügen des Knotens zum AST
        acc.addChild(a);
        acc = a;
    }
    public void exitAssignvar(CppParser.AssignvarContext ctx) {
        if (acc.asttype != AST.Types.REF ) {
            // Prüfen der Typen nach der Zuweisung, anhand des Attributs rtype, welches in den Knoten gespeichert wurde
            if (scope.resolve(acc.kinder.get(0).rtype) instanceof SymbolClazz) {
                // Class right meint A a = b; // Klasse von b
                // Prüfen, ob bei Polymorphie die Typen übereinstimmen
                SymbolClazz clazzRight = (SymbolClazz) scope.resolve(acc.kinder.get(0).rtype);
                if (clazzRight.parentClazz != null) {
                    if (!clazzRight.parentClazz.name.equals(acc.rtype)) {
                        throw new RuntimeException("Variable: '" + ctx.ID().getText() + "' falsche Typen");
                    }
                } else {
                    if (!acc.rtype.equals(acc.kinder.get(0).rtype)) {
                        throw new RuntimeException("Variable: '" + ctx.ID().getText() + "' falsche Typen");
                    }
                }
            } else {
                if (acc.kinder.get(0).rtype != null && !acc.kinder.get(0).rtype.equals(scope.resolve(ctx.ID().getText()).type.getName())){
                    throw new RuntimeException("Variable: '" + ctx.ID().getText() + "' false type");
                }
            }
        }
        acc = acc.prev;
    }

    // Assignment Array Element
    public void enterAssignarrayelem(CppParser.AssignarrayelemContext ctx) {
        // Prüfen, ob die Variable existiert und ein Array ist
        Symbol s = scope.resolve(ctx.ID().getText());
        if (s == null || !ctx.getChild(1).getText().equals("[") || !(s instanceof SymbolArray)){
            throw new RuntimeException("Array: '" + ctx.ID().getText() + "' nicht deklariert oder kein Array");
        } else if (scope.resolve(ctx.ID().getText()).isConst) {
            throw new RuntimeException("Array: '" + ctx.ID().getText() + "' ist konstant und kann nicht verändert werden");
        }
        // Erstellen des Knotens und hinzufügen zum AST
        AST a = new AST(ctx.ID().getText(), AST.Types.ASSIGNARRAYELEMENT, acc, scope, null, scope.resolve(ctx.ID().getText()).isConst);
        acc.addChild(a);
        acc = a;
    }
    public void exitAssignarrayelem(CppParser.AssignarrayelemContext ctx) {
        // Prüfen der Typen nach der Zuweisung, anhand des Attributs rtype, welches in den Knoten gespeichert wurde
        if (!acc.kinder.get(1).rtype.equals(scope.resolve(ctx.ID().getText()).type.getName())){
            throw new RuntimeException("Variable: '" + ctx.ID().getText() + "' falsche Typen");
        }
        acc.rtype = scope.resolve(ctx.ID().getText()).type.getName();
        acc = acc.prev;
    }

    // Assignment Array
    public void enterAssignnewarray(CppParser.AssignnewarrayContext ctx) {
        AST.Types asttype = AST.Types.ARRAYASSIGN;
        String type;
        Boolean isConst = false;
        // Prüfen, ob es sich um eine Konstante handelt und ermitteln des Typs
        if (ctx.getParent().getChild(0).getText().equals("const")){
            type = ctx.getParent().getChild(1).getText();
            isConst = true;
        } else {
            type = ctx.getParent().getChild(0).getText();
        }

        // Prüfen, ob Array und Typ existieren
        if (scope.resolveLocal(ctx.ID().getText()) != null){
            throw new RuntimeException("Array: '" + ctx.ID().getText() + "' already declared");
        } else if (scope.resolve(type) == null) {
            throw new RuntimeException("Type: '" + ctx.getParent().getChild(0).getText() + "' not declared");
        }

        // Erstellen des Symbols und Knotens und hinzufügen zum Scope und AST
        SymbolArray array = new SymbolArray(ctx.ID().getText(), new SymbolType(type), isConst);
        AST a = new AST(ctx.ID().getText(), asttype, acc, scope, scope.bind(array), isConst);
        acc.addChild(a);
        acc = a;
    }
    public void exitAssignnewarray(CppParser.AssignnewarrayContext ctx) {
        // Erst ab dem zweiten Kind prüfen, da das erste die Arraygröße enthält
        // Prüfen aller Typen nach der Zuweisung, anhand des Attributs rtype, welches in den Knoten gespeichert wurde
        for (int i = 1; i < acc.kinder.size(); i++) {
            AST kind = acc.kinder.get(i);
            if (!kind.rtype.equals(scope.resolve(ctx.ID().getText()).type.getName())) {
                throw new RuntimeException("Variable: '" + ctx.ID().getText() + "' false type");
            }
        }
        acc.rtype = acc.kinder.get(1).rtype;
        acc = acc.prev;
    }

    // Array Size
    public void enterArraysize(CppParser.ArraysizeContext ctx) {
        AST a = new AST("Arraysize", AST.Types.ARRAYSIZE, acc, scope, null, false);
        acc.addChild(a);
        acc = a;
    }
    public void exitArraysize(CppParser.ArraysizeContext ctx) {
        acc = acc.prev;
    }

    // KLassen Variablen
    // Assign Class Variable - Zugriff auf Klassenvariablen
    public void enterAssignclassvar(CppParser.AssignclassvarContext ctx) {
        String var = ctx.ID(1).getText();
        Symbol object = scope.resolve(ctx.ID(0).getText());

        // Prüfen, ob das Objekt existiert
        if (object == null) {
            throw new RuntimeException("Variable: '" + ctx.ID(0).getText() + "' not declared");
        }

        // Prüfen, ob es sich um eine Klasse handelt
        Symbol classSymbol = scope.resolve(object.type.getName());
        if (classSymbol == null || !(classSymbol instanceof SymbolStruct)) {
            throw new RuntimeException("Type: '" + object + "' not found or not a struct/class");
        }

        // Prüfen, ob die Variable in der Klasse existiert
        SymbolStruct structSymbol = (SymbolClazz) classSymbol;
        Symbol member = structSymbol.resolveMember(var);
        if (member == null) {
            throw new RuntimeException("Member: '" + var + "' nicht gefunden in der Klassse '" + object + "'");
        }
        // Prüfen, ob Variable konstant ist
        if (member.isConst){
            throw new RuntimeException("Member: '" + var + "' ist konstant und kann nicht verändert werden");
        }

        // Erstellen des Knotens und hinzufügen zum AST
        AST a = new AST(ctx.ID(1).getText(), AST.Types.ASSIGNOLD, acc, null, scope.resolve(ctx.ID(1).getText()), false);
        a.rtype = member.type.getName();
        acc.addChild(a);
        acc = a;
        AST b = new AST(ctx.ID(0).getText(), AST.Types.CLASS, acc, scope, scope.resolve(ctx.ID(0).getText()), false);
        acc.addChild(b);
    }
    public void exitAssignclassvar(CppParser.AssignclassvarContext ctx) {
        /*
        // Prüfen der Typen nach der Zuweisung, anhand des Attributs rtype, welches in den Knoten gespeichert wurde
        if (!acc.rtype.equals(acc.kinder.get(1).rtype)){
            throw new RuntimeException("Variable: '" + ctx.ID(1).getText() + "' falscher Typen");
        }
         */
        acc = acc.prev;
    }

    // Array Element Expression - Zugriff auf Arrayelemente
    public void enterArrayelem(CppParser.ArrayelemContext ctx) {
        Symbol s = scope.resolve(ctx.ID().getText());
        // Prüfen, ob Array existiert und ob es sich um ein Array handelt
        if (s == null || !ctx.getChild(1).getText().equals("[") || !(s instanceof SymbolArray)){
            throw new RuntimeException("Array: '" + ctx.ID().getText() + "' nicht deklariert oder kein Array");
        }
        // Erstellen des Knotens und hinzufügen zum AST
        AST a = new AST(ctx.ID().getText(), AST.Types.ARRAYELEMENT, acc, scope, null, s.isConst);
        a.rtype = s.type.getName();
        acc.addChild(a);
        acc = a;
    }
    public void exitArrayelem(CppParser.ArrayelemContext ctx) {
        acc = acc.prev;
    }

    // Assign new Class
    public void enterAssignnewclass(CppParser.AssignnewclassContext ctx) {
        // Prüfen, ob es sich um eine Konstante handelt
        Boolean isConst = false;
        String className;
        // Ermitteln des Klassennamens und ob es sich um eine Konstante handelt
        if (ctx.getParent().getChild(0).getText().equals("const")) {
            isConst = true;
            className = ctx.getParent().getChild(1).getText();
        } else {
            className = ctx.getParent().getChild(0).getText();
        }

        // Abgangen von Standardtypen und Builtins, welche nicht instanziiert wurden
        if (isConst){
            if (className.equals("int") || className.equals("bool") || className.equals("char") || className.equals("void")) {
                throw new RuntimeException("Const Variablen müssen initialisiert werden");
            }
        }

        // Prüfen, ob Variable oder Klasse existiert
        if (scope.resolveLocal(ctx.ID().getText()) == null && scope.resolve(className) != null) {
            // Prüfen, ob es sich um eine Klasse handelt
            // In bestimmten Fällen kann es sich auch um eine Variable handeln mit basistyp handeln
            // => Keine Klasse
            if (!(scope.resolve(className) instanceof SymbolClazz)) {
                Symbol var;
                SymbolType type;
                // Ermitteln des Typs und ob es sich um eine Konstante handelt
                if (ctx.getParent().getChild(0).getText().equals("const")){
                    isConst = true;
                    type = new SymbolType(ctx.getParent().getChild(1).getText());

                } else {
                    type = new SymbolType(className);
                }

                // Ermitteln ob die Variable eine Referenz ist
                AST.Types asttype = AST.Types.DECLARATION;
                CppParser.AssignnewContext par = (CppParser.AssignnewContext) ctx.getParent();
                if (par != null) {
                    if (par.REF() != null && par.getClass().getSimpleName().equals("AssignnewContext")) {
                        asttype = AST.Types.REF;
                    } else if (par.REF() != null && !par.getClass().getSimpleName().equals("AssignnewContext")){
                        throw new RuntimeException("Ref Variablen müssen initialisiert werden");
                    }
                }

                // Erstellen des Symbols und Knotens und hinzufügen zum Scope und AST
                var = new SymbolVariable(ctx.ID().getText(), type, isConst);
                AST a = new AST(ctx.ID().getText(), asttype, acc, scope, scope.bind(var), isConst);
                a.rtype = className;
                acc.addChild(a);
                acc = a;

            // => Klasse
            } else {
                // Erstellen des Symbols und Knotens und hinzufügen zum Scope und AST
                SymbolVariable clazz = new SymbolVariable(ctx.ID().getText(), new SymbolType(className), isConst);
                AST a = new AST(ctx.ID().getText(), AST.Types.ASSIGNCLASS, acc, scope, scope.bind(clazz), isConst);
                acc.addChild(a);
                acc = a;
                AST b = new AST(className, AST.Types.CLASS, acc, scope, null, false);
                b.rtype = className;
                acc.addChild(b);
                acc.rtype = className;
            }
        } else {
            throw new RuntimeException("Variable: '" + ctx.ID().getText() + "' bereits declariert oder Klasse: '" + className + "' nicht gefunden");
        }
    }
    public void exitAssignnewclass(CppParser.AssignnewclassContext ctx) {
        // Prüfen, ob es sich um ein Klassenassignment handelt
        if (!acc.asttype.equals(AST.Types.REF) && !acc.asttype.equals(AST.Types.DECLARATION)) {
            // Ermitteln der Parameterliste
            List<String> paramTypeList = new ArrayList<>();
            if (acc.kinder.size() > 1) {
                AST paramlist =  acc.kinder.get(1);
                for (AST kind : paramlist.kinder) {
                    if (!kind.asttype.equals(AST.Types.CLASS)){
                        paramTypeList.add(kind.rtype);
                    }
                }
            }

            // Klasse ermitteln und prüfen, ob sie existiert
            Symbol object = scope.resolve(acc.kinder.get(0).value);
            if (object == null || !(object instanceof SymbolStruct)) {
                throw new RuntimeException("Type: '" + object + "' not found or not a struct/class");
            }
            // Prüfen, ob die Funktion/Construktor in der Klasse existiert
            SymbolStruct structSymbol = (SymbolClazz) object;
            SymbolFunction func = (SymbolFunction) structSymbol.resolveMember(acc.kinder.get(0).value + paramTypeList.toString());
            if (func == null) {
                throw new RuntimeException("Member: '" + acc.kinder.get(0).value + "' not found in class '" + object + "'");
            }

            // Anpassen des Namens des Contruktoraufrufs mit Parametern zum späteren Vergleich
            acc.symbol.name = acc.kinder.get(0).value + paramTypeList.toString();

            // Prüft ob, wenn eine Referenz gefordert ist. Wenn ja dann auch ob eine Variable/ID und kein direkter Wert übergeben wurde
            for (int i = 0; i < func.functionAST.kinder.get(0).kinder.size(); i++){
                if (func.functionAST.kinder.get(0).kinder.get(i).asttype.equals(AST.Types.REF)
                        && !acc.kinder.get(1).kinder.get(i).asttype.equals(AST.Types.ID)
                ){
                    throw new RuntimeException("Function: '" + ctx.ID().getText() + paramTypeList.toString() + "' false type");
                }
            }
        }
        acc = acc.prev;
    }

    // Class Var - Speichern von Klassennamen für den Interpreter
    public void enterClassvar(CppParser.ClassvarContext ctx) {
        // Prüfen, ob das Objekt existiert
        Symbol s = scope.resolve(ctx.ID(0).getText());
        if (s == null){
            throw new RuntimeException("Class: '" + ctx.ID(0).getText() + "' nicht deklariert");
        }
        SymbolClazz clazzSymbol = (SymbolClazz) scope.resolve(s.type.getName());
        // Prüfen, ob die Variable in der Klasse existiert
        Symbol member = clazzSymbol.resolveMember(ctx.ID(1).getText());
        if (member == null) {
            if (clazzSymbol.parentClazz != null) {
                Symbol parent = scope.resolve(clazzSymbol.parentClazz.name);
                member = ((SymbolClazz) parent).resolveMember(ctx.ID(1).getText());
            }

            if (member == null) {
                throw new RuntimeException("Member: '" + ctx.ID(1).getText() + "' nicht gefunden in Objekt '" + ctx.ID(0).getText() + "'");
            }
        }

        // Erstellen des Knotens und hinzufügen zum AST
        AST b = new AST(ctx.ID(0).getText(), AST.Types.CLASS, acc, scope, scope.resolve(ctx.ID(0).getText()), false);
        acc.addChild(b);
        AST a = new AST(ctx.ID(1).getText(), AST.Types.ID, acc, scope, null, false);
        acc.addChild(a);
        a.rtype = member.type.getName();

    }
    public void exitClassvar(CppParser.ClassvarContext ctx) {
    }


    //
    // Klassen
    //
    // Klassendefinition
    public void enterClassdef(CppParser.ClassdefContext ctx) {
        SymbolClazz parent = null;
        Scope parentScope = scope;

        // Prüfen, ob die klasse erbt und ob die Basisklasse existiert
        if (ctx.vererbung() != null) {
            Symbol parentSymbol = scope.resolve(ctx.vererbung().ID().getText());
            if (parentSymbol == null || !(parentSymbol instanceof SymbolClazz)) {
                throw new RuntimeException("Basisklasse '" + ctx.vererbung().ID().getText() + "' nicht gefunden oder ist keine Klasse.");
            }
            parent = (SymbolClazz) parentSymbol;
            parentScope = parent.scope;
        }

        // Erstellen des Symbols und Knotens und hinzufügen zum Scope und AST
        SymbolClazz clazz = new SymbolClazz(ctx.ID().getText(), parent, parentScope);
        scope.bind(clazz);

        AST a = new AST(ctx.ID().getText(), AST.Types.CLASSDEF, acc, scope, clazz, false);
        acc.addChild(a);
        acc = a;
        scope = clazz.scope;
    }
    public void exitClassdef(CppParser.ClassdefContext ctx) {
        // Erstelle Default Copy-Constructor direkt zu beginn der Klassendefinition
        // Kann überschrieben werden, da dieser als Declaration markiert ist
        SymbolFunction copyConstructor = (SymbolFunction) scope.resolve(ctx.ID().getText() + "[" + ctx.ID().getText() + "]");
        if (copyConstructor == null) {
            Scope tmpscope = scope;
            AST tmpacc = acc;

            // Erstellen der Parameterliste für den Copy-Constructor
            List<String> paramTypeList = new ArrayList<>();
            paramTypeList.add(ctx.ID().getText());
            // Erstelle Copy-Constructor
            SymbolFunction copyConstructorFunc = new SymbolFunction(
                    ctx.ID().getText(),
                    ctx.ID().getText(),
                    scope,
                    null,
                    true,
                    paramTypeList,
                    false,
                    false
            );
            // Erstellen des Construktorsymbols und Knotens und hinzufügen zum Scope und AST
            scope.bind(copyConstructorFunc);
            AST copyConstructorAST = new AST(ctx.ID().getText(), AST.Types.CONSTRUCTOR, acc, scope, copyConstructorFunc, false);
            copyConstructorFunc.functionAST = copyConstructorAST;
            copyConstructorFunc.functionAST.rtype = ctx.ID().getText();
            acc.addChild(copyConstructorAST);
            acc = copyConstructorAST;
            scope = copyConstructorFunc.scope;
            // Hinzufügen der Parameterliste zum AST
            AST paramlist = new AST("paramlist", AST.Types.PARAMLIST, acc, scope, null, false);
            acc.addChild(paramlist);
            // Hinzufügen eines Parameters - Der eigenen Klasse
            AST param = new AST(ctx.ID().getText(), AST.Types.DECLARATION, acc, scope, null, false);
            param.rtype = ctx.ID().getText();
            paramlist.addChild(param);

            // Zurücksetzen der Umgebung
            acc = tmpacc;
            scope = tmpscope;
        }

        // Zurücksetzen der Umgebung
        if (ctx.vererbung() != null) {
            scope = scope.prev;
        }
        acc = acc.prev;
        scope.print();
        scope = scope.prev;
    }

    //Konstruktor
    public void enterConstructor(CppParser.ConstructorContext ctx) {
        // Prüfen, ob der Konstruktor den Klassennamen hat
        if (!ctx.ID().getText().equals(acc.value.toString())) {
            throw new RuntimeException("Constructor: '" + ctx.ID().getText() + "' passt nicht zum Klassennamen");
        }

        // Erstellen der Parameterliste zum späteren Vergleich mit den Funktionsaufrufen
        List<String> paramTypeList = extractParamTypes(ctx.defparamlist());
        SymbolFunction func = (SymbolFunction) scope.resolve(ctx.ID().getText() + paramTypeList.toString());

        // Prüfen, ob die Funktion bereits definiert wurde
        if (func != null) {
            // Declarierte Funktionen mit gleichen Parametern werden einfach überschrieben
            if (!func.decl) {
                throw new RuntimeException("Function: '" + ctx.ID().getText() + "' already declared");
            }
        }

        // Erstellen des Symbols und Knotens und hinzufügen zum Scope und AST
        SymbolFunction newfunc = new SymbolFunction(
                ctx.ID().getText(),
                ctx.ID().getText(),
                scope,
                null,
                false,
                paramTypeList,
                false,
                false
        ); // TODO - Ist die Declaration eines Construktors relvant oder darauf zu achten
        scope.bind(newfunc);
        AST a = new AST(ctx.ID().getText(), AST.Types.CONSTRUCTOR, acc, scope, newfunc, false);
        newfunc.functionAST = a;
        newfunc.functionAST.rtype = ctx.ID().getText();
        acc.addChild(a);
        acc = a;
        scope = newfunc.scope;
    }
    public void exitConstructor(CppParser.ConstructorContext ctx) {
        acc = acc.prev;
        scope.print();
        scope = scope.prev;
    }

    // Class Vererbung
    public void enterVererbung(CppParser.VererbungContext ctx) {
        AST a = new AST(ctx.ID().getText(), AST.Types.PARENT, acc);
        acc.addChild(a);
    }
    public void exitVererbung(CppParser.VererbungContext ctx) {
    }

    // Destruktoren
    public void enterDestruct(CppParser.DestructContext ctx) {
        boolean abstractFunc = false;
        // Prüfen, ob der Destruktor zur Klasse passt
        if (!ctx.ID().getText().equals(acc.value)) {
            throw new RuntimeException("Destructor: '" + ctx.ID().getText() + "' passt nicht zum Klassennamen");
        }
        if (ctx.VIRTUAL() != null) {
            abstractFunc = true;
        }
        // Erstellen des Symbols und Knotens und hinzufügen zum Scope und AST
        SymbolDestructor func = new SymbolDestructor(
                ctx.ID().getText(),
                ctx.ID().getText(),
                scope,
                null,
                false,
                null,
                false,
                abstractFunc
        );
        AST a = new AST(ctx.ID().getText(), AST.Types.DESTRUCTOR, acc, scope, scope.bind(func), false);
        func.functionAST = a;
        acc.addChild(a);
        acc = a;
        scope = func.scope;
    }
    public void exitDestruct(CppParser.DestructContext ctx) {
        acc = acc.prev;
        scope.print();
        scope = scope.prev;
    }


    //
    // Funktionen
    //
    // Declaration Funktion
    public void enterDeclfunc(CppParser.DeclfuncContext ctx) {
        boolean abstractFunc = false;
        String rtype = null;
        // Prüfen, ob es sich um eine virtuelle Funktion handelt und ob diese richtig geschrieben sit
        if (ctx.VIRTUAL() != null && ctx.abstractfunc() != null){
            CppParser.AbstractfuncContext abstractfunc = ctx.abstractfunc();
            if (abstractfunc.NUM() == null) {
                throw new RuntimeException("Virtuelle Funktion muss die Nummer 0 haben");
            }
            abstractFunc = true;
            rtype = ctx.getChild(1).getText();
        } else if (ctx.VIRTUAL() != null && ctx.abstractfunc() == null){
            throw new RuntimeException("Virtuelle Funktion muss abstrakt sein");
        } else if (ctx.VIRTUAL() == null && ctx.abstractfunc() != null){
            throw new RuntimeException("Abstrakte Funktion muss virtuell sein");
        } else  if (ctx.VIRTUAL() == null && ctx.abstractfunc() == null){
            rtype = ctx.getChild(0).getText();
        } else {
            throw new RuntimeException("Fehler bei der Deklaration der Funktion");
        }

        // Prüfen, ob es sich um eine virtuelle Funktion handelt und ob diese richtig geschrieben sit
        if (abstractFunc){
            if (!ctx.abstractfunc().NUM().getText().equals("0")) {
                throw new RuntimeException("Virtuelle Funktion muss die Nummer 0 haben");
            }
        }

        // Prüfen, ob der Rückgabetyp der Funktion existiert
        if (scope.resolve(rtype) == null) {
            throw new RuntimeException("Return type: '" + rtype + "' nicht deklariert");
        }

        // Erstellen der Parameterliste zum späteren Vergleich mit den Funktionsaufrufen
        // Es werden die schlussendlichen Typen der Expressions in der Liste gespeichert. Der Name der Variablen ist nicht relevant
        List<String> paramTypeList = extractParamTypes(ctx.defparamlist());

        // Prüfen, ob die Funktion bereits definiert wurde - Declarationen mit gleichen Paramtern werden ignoriert und einfach überschrieben
        SymbolFunction funcsymbol = (SymbolFunction) scope.resolveLocal(ctx.ID().getText() + paramTypeList.toString());
        if (funcsymbol != null) {
            if (!funcsymbol.decl) {
                throw new RuntimeException("Function: '" + ctx.ID().getText() + "' bereits deklariert");
            }
        }

        // Erstellen des Symbols und Knotens und hinzufügen zum Scope und AST
        SymbolFunction func = new SymbolFunction(
                ctx.ID().getText(),
                rtype,
                scope,
                null,
                true,
                paramTypeList,
                false,
                abstractFunc
        );
        AST a = new AST(ctx.ID().getText(), AST.Types.FUNCTION_DEC, acc, scope, scope.bind(func), false);
        func.functionAST = a;
        a.rtype = rtype;
        acc.addChild(a);
        acc = a;
        scope = func.scope;
        AST b = new AST(abstractFunc ? ctx.getChild(1).getText() : ctx.getChild(0).getText(), AST.Types.RETURNTYPE, acc, scope, null, false);
        b.rtype = rtype;
        acc.addChild(b);
    }
    public void exitDeclfunc(CppParser.DeclfuncContext ctx) {
        // Prüfen, ob Funktion deklariert wurde, wenn ja, dann zurücksetzen des AST und Scopes
        if (acc.asttype == AST.Types.FUNCTION_DEC) {
            // Zurücksetzen des AST und Scopes
            acc = acc.prev;
            scope = scope.prev;
        }
    }

    // Definition Funktion
    public void enterFuncdef(CppParser.FuncdefContext ctx) {
        String rtype;
        boolean override = false;
        boolean abstractFunc = false;
        // Prüfen, ob es sich um eine virtuelle Funktion handelt
        if (ctx.VIRTUAL() != null) {
            abstractFunc = true;
            rtype = ctx.getChild(1).getText();
        } else {
            rtype = ctx.getChild(0).getText();
        }
        // Prüfen, ob der Rückgabetyp der Funktion existiert
        if (scope.resolve(rtype) == null) {
            throw new RuntimeException("Return type: '" + rtype + "' nicht deklariert");
        }
        // Prüfen, ob es sich um eine überschriebene Funktion handelt
        if (ctx.OVERRIDE() != null) {
            override = true;
        }
        // Parameterliste extrahieren und speichern der Typen zum späteren Vergleich der Funktionsaufrufe
        // Somit können Funktionen mit gleichen Namen und unterschiedlichen Parametern deklariert werden (Überladung)
        List<String> paramTypeList = extractParamTypes(ctx.defparamlist());
        SymbolFunction func = (SymbolFunction) scope.resolveLocal(ctx.ID().getText() + paramTypeList.toString());

        // Prüfen, ob die Funktion bereits definiert wurde
        if (func != null) {
            // Declarierte Funktionen mit gleichen Parametern werden einfach überschrieben
            if (!func.decl) {
                throw new RuntimeException("Function: '" + ctx.ID().getText() + "' bereits deklariert");
            }
        }

        // Prüfen, es sich um die Main Funktion handelt
        if (ctx.ID().getText().equals("main")){
            // Prüfen, ob die Main Funktion bereits definiert wurde
            // Die Main Funktion darf nur einmal definiert werden - Keine Überladung
            for (String key : rootScope.scope.keySet()) {
                if (key.contains("main")) {
                    rootScope.scope.get(key);
                    if (!((SymbolFunction) rootScope.scope.get(key)).decl) {
                        throw new RuntimeException("Main Funktion bereits deklariert");
                    }
                }
            }
        }

        // Erstellen des Symbols und Knotens und hinzufügen zum Scope und AST
        SymbolFunction newfunc = new SymbolFunction(
                ctx.ID().getText(),
                rtype,
                scope,
                null,
                false,
                paramTypeList,
                override,
                abstractFunc
        );
        // Prüfen, ob es sich um eine Klassenfunktion handelt
        if (ctx.getParent().getParent().getClass().getSimpleName().equals("ClassdefContext")) {
            CppParser.ClassdefContext classdef = (CppParser.ClassdefContext) ctx.getParent().getParent();
            newfunc.klassName = classdef.ID().getText();
        }
        // Hinzufügen des Symbols und Knotens zum Scope und AST
        scope.bind(newfunc);
        AST a = new AST(ctx.ID().getText(), AST.Types.FUNCTION_DEF, acc, scope, newfunc, false);
        newfunc.functionAST = a;
        a.rtype = rtype;
        acc.addChild(a);
        acc = a;
        scope = newfunc.scope;
        AST b = new AST(rtype, AST.Types.RETURNTYPE, acc, scope, null, false);
        b.rtype = rtype;
        acc.addChild(b);
    }
    public void exitFuncdef(CppParser.FuncdefContext ctx) {
        // Zurücksetzen des AST und Scopes und Ausgabe des Scopes
        acc = acc.prev;
        scope = scope.prev;
    }

    // Call Funktion
    public void enterCallfunc(CppParser.CallfuncContext ctx) {
        String name;
        // Extrahieren des Funktionsnamens und hinzufügen zum AST
        if (ctx.ID().getLast() != null) {
            name = ctx.ID().getLast().getText();
        } else {
            name = ctx.ID().getFirst().getText();
        }
        // Prüfen, ob es sich um eine Klassenfunktion handelt
        if (ctx.ID().size() > 1) {
            AST a = new AST(ctx.ID().getFirst().getText(), AST.Types.CLASS, acc);
            acc.addChild(a);
        }
        // Erstellen des Knotens und hinzufügen zum AST
        AST a = new AST(name, AST.Types.FUNCTION_CALL, acc);
        acc.addChild(a);
        acc = a;
    }
    public void exitCallfunc(CppParser.CallfuncContext ctx) {
        // Extrahieren der Parameterliste und speichern der Typen zum Vergleichen mit den Funktionsdeklarationen oder -definitionen
        List<String> paramTypeList = new ArrayList<>();
        if (acc.kinder.size() > 0) {
            AST paramlist =  acc.kinder.get(0);
            for (AST kind : paramlist.kinder) {
                if (!kind.asttype.equals(AST.Types.CLASS)){
                    paramTypeList.add(kind.rtype);
                }
            }
        }

        // Ckecken ob es sich um einen Konstruktor handelt
        Symbol checkClass = scope.resolve(ctx.ID().getFirst().getText());

        // Prüfen, ob die Funktion existiert und ob es sich um eine Klassenfunktion handelt
        // Vergleich der Funktionen mithilfe der Parameterliste
        SymbolFunction func;
        // Die Funktion ist eine Klassenfunktion
        if ((ctx.ID().getLast() != null && ctx.ID().size() > 1) || checkClass instanceof SymbolStruct) {
            // Die Funktion ist ein Konstruktoraufruf
            if (checkClass instanceof SymbolStruct) {
                SymbolStruct structSymbol = (SymbolStruct) checkClass;
                func = (SymbolFunction) structSymbol.resolveMember(ctx.ID().getLast().getText() + paramTypeList.toString());
                if (acc.asttype == AST.Types.FUNCTION_CALL) {
                    acc.asttype = AST.Types.ASSIGNCLASS;
                    acc.value = acc.prev.value;
                    acc.rtype = structSymbol.name;
                }
                // Erstellen des Knotens und hinzufügen zum AST
                AST b = new AST(ctx.ID().getFirst().getText(), AST.Types.CLASS, acc);
                acc.kinder.addFirst(b);
            // Normale Klassenfunktion
            } else {
                Symbol object = scope.resolve(ctx.ID(0).getText());
                String function = ctx.ID().getLast().getText();
                // Prüfen, ob es sich um eine Klasse handelt
                Symbol classSymbol = scope.resolve(object.type.getName());
                if (classSymbol == null || !(classSymbol instanceof SymbolStruct)) {
                    throw new RuntimeException("Type: '" + object + "' nicht gefunden oder keine Klasse/Struct");
                }
                // Prüfen, ob die Funktion in der Klasse existiert
                SymbolClazz structSymbol = (SymbolClazz) classSymbol;
                func = (SymbolFunction) structSymbol.resolveMember(function + paramTypeList.toString());
                if (func == null) {
                    if (func == null) {
                        throw new RuntimeException("Member: '" + function + "' nicht gefunden in Klasse '" + object + "'");
                    }
                }
                // Hinzufügen des Klassennamens zum AST
                AST a = new AST(ctx.ID().getFirst().getText(), AST.Types.CLASS, acc);
                acc.addChild(a);
                for (int i = acc.prev.kinder.size() - 1; i >= 0; i--) {
                    if (acc.prev.kinder.get(i).asttype == AST.Types.CLASS) {
                        acc.prev.kinder.remove(i);
                    }
                }
            }

        // Die Funktion ist keine Klassenfunktion
        } else {
            func = (SymbolFunction) scope.resolve(ctx.ID().getFirst().getText() + paramTypeList.toString());
        }

        // Prüfen, ob die Funktion existiert
        if (func == null) {
            throw new RuntimeException("Function: '" + ctx.ID().getFirst().getText() + paramTypeList.toString() + "' not declared");
        }

        // Prüft ob, wenn eine Referenz gefordert ist, auch eine Variable(ID) und kein direkter Wert übergeben wurde
        for (int i = 0; i < func.functionAST.kinder.get(1).kinder.size(); i++){
            if (func.functionAST.kinder.get(1).kinder.get(i).asttype.equals(AST.Types.REF)
                && !acc.kinder.get(0).kinder.get(i).asttype.equals(AST.Types.ID)
            ){
                throw new RuntimeException("Function: '" + ctx.ID().getFirst().getText() + paramTypeList.toString() + "' false type");
            }
        }

        // Verknüpfen des Symbols mit dem AST
        if (func.functionAST != null){
            acc.rtype = func.functionAST.rtype;
        }

        // Verknüpfen des Symbols mit dem AST
        acc.symbol = func;
        acc = acc.prev;
    }


    //
    // Param Lists
    //
    // Definition Param List
    public void enterDefparamlist(CppParser.DefparamlistContext ctx) {
        if (ctx.getChildCount() > 0){
            AST a = new AST("paramlist", AST.Types.PARAMLIST, acc);
            acc.addChild(a);
            acc = a;
        }
    }
    public void exitDefparamlist(CppParser.DefparamlistContext ctx) {
        if (ctx.getChildCount() > 0){
            acc = acc.prev;
        }
    }

    // Call Param List
    public void enterCallparamlist(CppParser.CallparamlistContext ctx) {
        if (ctx.getChildCount() > 0){
            AST a = new AST("paramlist", AST.Types.PARAMLIST, acc);
            acc.addChild(a);
            acc = a;
        }
    }
    public void exitCallparamlist(CppParser.CallparamlistContext ctx) {
        if (ctx.getChildCount() > 0){
            acc = acc.prev;
        }
    }


    //
    // Builtin
    //
    public void enterBuiltinstmt(CppParser.BuiltinstmtContext ctx) {
        AST.Types asttype = AST.Types.UNDEFINED;
        // Hinzufügen des Builtins zum AST
        switch (ctx.getChild(0).getText()) {
            case "print_bool" -> asttype = AST.Types.PRINT_BOOL;
            case "print_int" -> asttype = AST.Types.PRINT_INT;
            case "print_char" -> asttype = AST.Types.PRINT_CHAR;
        }
        AST a = new AST("builtinstmt", asttype, acc);
        acc.addChild(a);
        acc = a;
    }
    public void exitBuiltinstmt(CppParser.BuiltinstmtContext ctx) {
        acc = acc.prev;
    }


    //
    // IF ELSE WHILE
    //
    // IF ELSE Block
    public void enterIfelseblock(CppParser.IfelseblockContext ctx) {
        AST a = new AST("ifelseblock", AST.Types.IF_ELSE_BLOCK, acc);
        scope = new Scope(scope);
        acc.addChild(a);
        acc = a;
    }
    public void exitIfelseblock(CppParser.IfelseblockContext ctx) {
        // Der scope der Conndition wird hier geschlossen
        scope = scope.prev;
        acc = acc.prev;
    }

    // IF
    public void enterIfblock(CppParser.IfblockContext ctx) {
        AST a = new AST("ifblock", AST.Types.IF_BLOCK, acc);
        scope = new Scope(scope);
        acc.addChild(a);
        acc = a;
    }
    public void exitIfblock(CppParser.IfblockContext ctx) {
        scope = scope.prev;
        acc = acc.prev;
    }

    // ELSE
    public void enterElseblock(CppParser.ElseblockContext ctx) {
        AST a = new AST("elseblock", AST.Types.ELSE_BLOCK, acc);
        scope = new Scope(scope);
        acc.addChild(a);
        acc = a;
    }
    public void exitElseblock(CppParser.ElseblockContext ctx) {
        scope = scope.prev;
        acc = acc.prev;
    }

    // IF Condition
    public void enterIfconn(CppParser.IfconnContext ctx) {
        AST a = new AST("ifconn", AST.Types.CONN, acc);
        acc.addChild(a);
        acc = a;
    }
    public void exitIfconn(CppParser.IfconnContext ctx) {
        acc = acc.prev;
    }

    // WHILE
    public void enterWhileblock(CppParser.WhileblockContext ctx) {
        AST a = new AST("whileblock", AST.Types.WHILE_BLOCK, acc);
        scope = new Scope(scope);
        acc.addChild(a);
        acc = a;
    }
    public void exitWhileblock(CppParser.WhileblockContext ctx) {
        scope = scope.prev;
        scope = scope.prev;
        acc = acc.prev;
    }

    // WHILE Condition
    public void enterWhileconn(CppParser.WhileconnContext ctx) {
        AST a = new AST("whileconn", AST.Types.CONN, acc);
        scope = new Scope(scope);
        acc.addChild(a);
        acc = a;
    }
    public void exitWhileconn(CppParser.WhileconnContext ctx) {
        acc = acc.prev;
    }


    //
    // Body für Statements
    //
    public void enterBody(CppParser.BodyContext ctx) {
        AST a = new AST("body", AST.Types.BODY, acc);
        acc.addChild(a);
        acc = a;
    }
    public void exitBody(CppParser.BodyContext ctx) {
        acc = acc.prev;
    }



    //
    // Expressions
    //

    //
    // Compares
    //
    public void enterCompare(CppParser.CompareContext ctx) {
        // Hinzufüfen des Vergleichsoperators zum AST
        if (ctx.com().getText().equals("==")) {
            AST a = new AST("==", AST.Types.EQUAL, acc);
            acc.addChild(a);
            acc = a;
        } else if (ctx.com().getText().equals("!=")) {
            AST a = new AST("!=", AST.Types.NOT_EQUAL, acc);
            acc.addChild(a);
            acc = a;
        } else if (ctx.com().getText().equals("<")) {
            AST a = new AST("<", AST.Types.LOWER, acc);
            acc.addChild(a);
            acc = a;
        } else if (ctx.com().getText().equals("<=")) {
            AST a = new AST("<=", AST.Types.LOWER_EQUAL, acc);
            acc.addChild(a);
            acc = a;
        } else if (ctx.com().getText().equals(">")) {
            AST a = new AST(">", AST.Types.GREATER, acc);
            acc.addChild(a);
            acc = a;
        } else if (ctx.com().getText().equals(">=")) {
            AST a = new AST(">=", AST.Types.GREATER_EQUAL, acc);
            acc.addChild(a);
            acc = a;
        }
        acc.rtype = "bool";
    }
    public void exitCompare(CppParser.CompareContext ctx) {
        acc = acc.prev;
    }


    //
    // MATH
    //
    // Add
    public void enterAdd(CppParser.AddContext ctx) {
        AST a = new AST("+", AST.Types.ADD, acc);
        acc.addChild(a);
        acc = a;
    }
    public void exitAdd(CppParser.AddContext ctx) {
        String type1 = acc.kinder.get(0).rtype;
        String type2 = acc.kinder.get(1).rtype;
        String rtype;
        // Prüfen, ob die Addition möglich ist und den Rückgabetyp setzen der Operation setzen
        if ((type1.equals("int") || type1.equals("bool") || type1.equals("char")) &&
            (type2.equals("int") || type2.equals("bool") || type2.equals("char")))
        {
            rtype = "int";
        } else if (!type1.equals(type2) || (!type1.equals("int") && !type1.equals("char") && !type1.equals("bool"))){
            throw new RuntimeException("Addition: '" + acc.kinder.get(0).rtype + "' and '" + acc.kinder.get(1).rtype + "' not possible");
        } else {
            rtype = type1;
        }
        acc.rtype = rtype;
        acc = acc.prev;
    }
    //Add Equal
    public void enterAddeq(CppParser.AddeqContext ctx) {
        AST a = new AST("+=", AST.Types.ADDEQ, acc);
        acc.addChild(a);
        acc = a;
        Symbol id = scope.resolve(ctx.ID().getText());
        // Prüfen, ob die Variable existiert
        if (id == null){
            throw new RuntimeException("Variable: '" + ctx.ID().getText() + "' not declared");
        }
        AST b = new AST(ctx.ID().getText(), AST.Types.ID, acc, scope, id, id.isConst);
        acc.addChild(b);
        b.rtype = id.type.getName();
    }
    public void exitAddeq(CppParser.AddeqContext ctx) {
        acc.rtype = acc.kinder.get(0).rtype;
        acc = acc.prev;
    }
    //Sub
    public void enterSub(CppParser.SubContext ctx) {
        AST a = new AST("-", AST.Types.SUB, acc);
        acc.addChild(a);
        acc = a;
    }
    public void exitSub(CppParser.SubContext ctx) {
        String type1 = acc.kinder.get(0).rtype;
        String type2 = acc.kinder.get(1).rtype;
        String rtype;
        // Prüfen, ob die Addition möglich ist und den Rückgabetyp setzen der Operation setzen
        if ((type1.equals("int") || type1.equals("bool") || type1.equals("char")) &&
                (type2.equals("int") || type2.equals("bool") || type2.equals("char")))
        {
            rtype = "int";
        } else if (!type1.equals(type2) || (!type1.equals("int") && !type1.equals("char") && !type1.equals("bool"))){
            throw new RuntimeException("Substraction: '" + acc.kinder.get(0).rtype + "' and '" + acc.kinder.get(1).rtype + "' not possible");
        } else {
            rtype = type1;
        }
        acc.rtype = rtype;
        acc = acc.prev;
    }
    //Sub Equal
    public void enterSubeq(CppParser.SubeqContext ctx) {
        AST a = new AST("-=", AST.Types.SUBEQ, acc);
        acc.addChild(a);
        acc = a;
        Symbol id = scope.resolve(ctx.ID().getText());
        // Prüfen, ob die Variable existiert
        if (id == null){
            throw new RuntimeException("Variable: '" + ctx.ID().getText() + "' not declared");
        }
        AST b = new AST(ctx.ID().getText(), AST.Types.ID, acc, scope, id, id.isConst);
        acc.addChild(b);
        b.rtype = id.type.getName();
    }
    public void exitSubeq(CppParser.SubeqContext ctx) {
        acc.rtype = acc.kinder.get(0).rtype;
        acc = acc.prev;
    }
    //Mul
    public void enterMul(CppParser.MulContext ctx) {
        AST a = new AST("*", AST.Types.MULL, acc);
        acc.addChild(a);
        acc = a;
    }
    public void exitMul(CppParser.MulContext ctx) {
        String type1 = acc.kinder.get(0).rtype;
        String type2 = acc.kinder.get(1).rtype;
        String rtype;
        // Prüfen, ob die Addition möglich ist und den Rückgabetyp setzen der Operation setzen
        if ((type1.equals("int") || type1.equals("bool") || type1.equals("char")) &&
                (type2.equals("int") || type2.equals("bool") || type2.equals("char")))
        {
            rtype = "int";
        } else if (!type1.equals(type2) || (!type1.equals("int") && !type1.equals("char") && !type1.equals("bool"))){
            throw new RuntimeException("Multiplikation: '" + acc.kinder.get(0).rtype + "' and '" + acc.kinder.get(1).rtype + "' not possible");
        } else {
            rtype = type1;
        }
        acc.rtype = rtype;
        acc = acc.prev;
    }
    //Mul Equal
    public void enterMuleq(CppParser.MuleqContext ctx) {
        AST a = new AST("*=", AST.Types.MULEQ, acc);
        acc.addChild(a);
        acc = a;
        Symbol id = scope.resolve(ctx.ID().getText());
        // Prüfen, ob die Variable existiert
        if (id == null){
            throw new RuntimeException("Variable: '" + ctx.ID().getText() + "' not declared");
        }
        AST b = new AST(ctx.ID().getText(), AST.Types.ID, acc, scope, id, id.isConst);
        acc.addChild(b);
        b.rtype = id.type.getName();
    }
    public void exitMuleq(CppParser.MuleqContext ctx) {
        acc.rtype = acc.kinder.get(0).rtype;
        acc = acc.prev;
    }
    //Div
    public void enterDiv(CppParser.DivContext ctx) {
        AST a = new AST("/", AST.Types.DIV, acc);
        acc.addChild(a);
        acc = a;
    }
    public void exitDiv(CppParser.DivContext ctx) {
        String type1 = acc.kinder.get(0).rtype;
        String type2 = acc.kinder.get(1).rtype;
        String rtype;
        // Prüfen, ob die Addition möglich ist und den Rückgabetyp setzen der Operation setzen
        if ((type1.equals("int") || type1.equals("bool") || type1.equals("char")) &&
                (type2.equals("int") || type2.equals("bool") || type2.equals("char")))
        {
            rtype = "int";
        } else if (!type1.equals(type2) || (!type1.equals("int") && !type1.equals("char") && !type1.equals("bool"))){
            throw new RuntimeException("Divison: '" + acc.kinder.get(0).rtype + "' and '" + acc.kinder.get(1).rtype + "' not possible");
        } else {
            rtype = type1;
        }
        acc.rtype = rtype;
        acc = acc.prev;
    }
    //Div Equal
    public void enterDiveq(CppParser.DiveqContext ctx) {
        AST a = new AST("/=", AST.Types.DIVEQ, acc);
        acc.addChild(a);
        acc = a;
        Symbol id = scope.resolve(ctx.ID().getText());
        // Prüfen, ob die Variable existiert
        if (id == null){
            throw new RuntimeException("Variable: '" + ctx.ID().getText() + "' not declared");
        }
        AST b = new AST(ctx.ID().getText(), AST.Types.ID, acc, scope, id, id.isConst);
        acc.addChild(b);
        b.rtype = id.type.getName();
    }
    public void exitDiveq(CppParser.DiveqContext ctx) {
        acc.rtype = acc.kinder.get(0).rtype;
        acc = acc.prev;
    }
    // Increment
    public void enterInc(CppParser.IncContext ctx) {
        AST a = new AST("++", AST.Types.INC, acc);
        acc.addChild(a);
        acc = a;
        Symbol id = scope.resolve(ctx.ID().getText());
        // Prüfen, ob die Variable existiert
        if (id == null){
            throw new RuntimeException("Variable: '" + ctx.ID().getText() + "' not declared");
        }
        AST b = new AST(ctx.ID().getText(), AST.Types.ID, acc, scope, id, id.isConst);
        acc.addChild(b);
        b.rtype = id.type.getName();
    }
    public void exitInc(CppParser.IncContext ctx) {
        acc = acc.prev;
    }
    // Decrement
    public void enterDec(CppParser.DecContext ctx) {
        AST a = new AST("--", AST.Types.DEC, acc);
        acc.addChild(a);
        acc = a;
        Symbol id = scope.resolve(ctx.ID().getText());
        // Prüfen, ob die Variable existiert
        if (id == null){
            throw new RuntimeException("Variable: '" + ctx.ID().getText() + "' not declared");
        }
        AST b = new AST(ctx.ID().getText(), AST.Types.ID, acc, scope, id, id.isConst);
        acc.addChild(b);
        b.rtype = id.type.getName();
    }
    public void exitDec(CppParser.DecContext ctx) {
        acc = acc.prev;
    }


    //
    // Lexar
    //
    // Hinzufügen der einzelnen Lexeme zum AST
    // Num
    public void enterNum(CppParser.NumContext ctx) {
        AST a = new AST(ctx.getText(), AST.Types.NUM, acc);
        a.rtype = "int";
        acc.addChild(a);
    }

    // Bool
    public void enterBool(CppParser.BoolContext ctx) {
        AST a = new AST(ctx.getText(), AST.Types.BOOL, acc);
        a.rtype = "bool";
        acc.addChild(a);
    }

    // ID
    public void enterId(CppParser.IdContext ctx) {
        // Prüfen, ob Variable existiert und ermitteln des Typs
        Symbol s = scope.resolve(ctx.getText());
        if (s == null){
            throw new RuntimeException("Variable: '" + ctx.getText() + "' not declared");
        }
        AST a = new AST(ctx.getText(), AST.Types.ID, acc);
        a.rtype = s.type.getName();
        acc.addChild(a);
    }

    // Char
    public void enterChar(CppParser.CharContext ctx) {
        AST a = new AST(ctx.getText(), AST.Types.CHAR, acc);
        a.rtype = "char";
        acc.addChild(a);
    }



    //
    // Hilfsfunktionen
    //

    // Extrahieren der Parametertypen
    public List<String> extractParamTypes(CppParser.DefparamlistContext defparamlistCtx) {
        // Iterieren über die Parameterliste und speichern der Typen in einer Liste für den späteren Vergleich mit den Funktionsaufrufen
        List<String> paramTypeList = new ArrayList<>();
        if (defparamlistCtx != null && defparamlistCtx.defparam() != null) {
            for (CppParser.DefparamContext defpCtx : defparamlistCtx.defparam()) {
                if (defpCtx.children == null) {
                    continue;
                }
                if (defpCtx.getChild(0) instanceof CppParser.DeclContext) {
                    CppParser.DeclContext declCtx = (CppParser.DeclContext) defpCtx.getChild(0);
                    paramTypeList.add(declCtx.getChild(0).getText());
                } else if (defpCtx.getChild(0) instanceof CppParser.AssignnewContext) {
                    CppParser.AssignnewContext assignnewCtx = (CppParser.AssignnewContext) defpCtx.getChild(0);
                    paramTypeList.add(assignnewCtx.basetype().getText());
                } else if (defpCtx.getChild(0) instanceof CppParser.DeclnewContext) {
                    CppParser.DeclnewContext declnewCtx = (CppParser.DeclnewContext) defpCtx.getChild(0);
                    paramTypeList.add(declnewCtx.basetype().getText());
                } else if (defpCtx.getChild(0) instanceof CppParser.DefrefvarContext) {
                    CppParser.DefrefvarContext defrefvarCtx = (CppParser.DefrefvarContext) defpCtx.getChild(0);
                    paramTypeList.add(defrefvarCtx.basetype().getText());
                } else if (defpCtx.getChild(0) instanceof CppParser.DeclrefvarContext) {
                    CppParser.DeclrefvarContext declrefvarCtx = (CppParser.DeclrefvarContext) defpCtx.getChild(0);
                    paramTypeList.add(declrefvarCtx.basetype().getText());
                } else {
                    throw new RuntimeException("Unbekannter Parametertyp: " + defpCtx.getText());
                }
            }
        }
        return paramTypeList;
    }
}

