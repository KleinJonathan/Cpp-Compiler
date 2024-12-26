import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.lang.reflect.Array;
import java.util.List;
import java.util.ArrayList;

public class MyListener extends CppBaseListener {
    public AST wurzel;
    private AST acc;
    private Scope scope;

    // Rückgabe des AST Wurzelknotens
    public AST getAST() {
        return wurzel;
    }


    //
    // Start
    //
    public void enterStart(CppParser.StartContext ctx) {
        //System.out.println("Start");
        scope = new Scope(null);
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


    // Return // Es dürfen beliebig viele Return Statements in einer Funktion vorkommen, auch direkt hintereinander
    public void enterReturn(CppParser.ReturnContext ctx) {
        AST a = new AST("return", AST.Types.RETURN, acc, scope, null, false);
        acc.addChild(a);
        acc = a;
    }
    public void exitReturn(CppParser.ReturnContext ctx) {
        AST par = acc.prev;
        while (par.asttype != AST.Types.FUNCTION_DEF && par.prev != null) {
            par = par.prev;
        }
        if (par.asttype == AST.Types.FUNCTION_DEF) {
            if (!par.rtype.equals(acc.kinder.get(0).rtype)) {
                throw new RuntimeException("Return type: '" + acc.kinder.get(0).rtype + "' not equal to function type: '" + par.rtype + "'");
            }
        }

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
    public void enterOpenscope(CppParser.OpenscopeContext ctx) {
        scope = new Scope(scope);
        AST a = new AST("scope", AST.Types.SCOPE, acc, scope, null, false);
        acc.addChild(a);
        acc = a;
    }
    public void exitClosescope(CppParser.ClosescopeContext ctx) {
        scope.print();
        acc = acc.prev;
        scope = scope.prev;
    }


    //
    // Variable Asignment and Declaration
    //
    // Declaration Variable
    public void enterDeclvar(CppParser.DeclvarContext ctx) {
        // Bestimmen des Typs
        AST.Types asttype = AST.Types.DECLARATION;
        String type = ctx.getParent().getChild(0).getText();
        Boolean isConst = false;
        Symbol var;
        // Prüfen, ob Variable nicht und Typ existieren
        if (scope.resolve(type) == null || scope.resolveLocal(ctx.ID().getText()) != null) {
            throw new RuntimeException("Type: '" + type + "' not declared or Variable: '" + ctx.ID().getText() + "' already declared");
        }
        // Prüfen, ob es sich um eine Konstante handelt => muss initialisiert werden
        if (ctx.getParent().getChild(0).getText().equals("const")){
            isConst = true;
            var = new SymbolRefVariable(ctx.ID().getText(), new SymbolType(ctx.getParent().getChild(1).getText()), isConst);
        } else {
            var = new SymbolVariable(ctx.ID().getText(), new SymbolType(type), isConst);
        }
        // Erstellen des Symbols und Knostens und hinzufügen zum Scope und AST
        AST a = new AST(ctx.ID().getText(), asttype, acc, scope, scope.bind(var), isConst);
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
            throw new RuntimeException("Type: '" + type + "' not declared or Variable: '" + name + "' already declared");
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
        AST a = null;
        AST.Types asttype = AST.Types.REF;
        String type = ctx.basetype().getText();
        Boolean isConst = Boolean.valueOf(ctx.const_().getText());
        // Prüfen, ob Variable bereits und Typ existiert
        if (scope.resolve(type) == null || scope.resolveLocal(ctx.ID().getText()) != null) {
            throw new RuntimeException("Type: '" + type + "' not declared or Variable: '" + ctx.ID().getText() + "' already declared");
        }
        // Erstellen des Symbols und Knostens und hinzufügen zum Scope und AST
        SymbolRefVariable refvar = new SymbolRefVariable(ctx.ID().getText(), new SymbolType(type), isConst);
        a = new AST(ctx.ID().getText(), asttype, acc, scope, scope.bind(refvar), isConst);
        a.rtype = type;
        acc.addChild(a);
    }
    // Assignment Variable
    public void enterAssignvar(CppParser.AssignvarContext ctx) {
        AST.Types asttype = AST.Types.UNDEFINED;
        AST a = null;
        // Prüfen, ob eine alte oder neue Variable zugewiesen wird
        // Zuweisung einer ALTEN Variable
        if (ctx.getParent() instanceof CppParser.AssignoldContext){
            asttype = AST.Types.ASSIGN;
            // Prüfen, ob Variable existiert
            Symbol s = scope.resolve(ctx.ID().getText());
            System.out.println(ctx.expr().getClass());
            if (s == null) {
                throw new RuntimeException("Variable: '" + ctx.ID().getText() + "' not declared");
            }else if (ctx.getChild(2).getText().equals("[")){
                throw new RuntimeException(ctx.ID().getText() + " is or not a Variable");
            } else if (s.isConst) {
                throw new RuntimeException("Variable: '" + ctx.ID().getText() + "' is const");
            }
            // Erschaffen des Knotens
            a = new AST(ctx.ID().getText(), asttype, acc, scope, null, scope.resolve(ctx.ID().getText()).isConst);
            a.rtype = s.type.getName();
        // Zuweisung einer NEUEN Variable
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
            // Prüfen, ob Variable Variable existiert
            if (scope.resolve(type) == null || scope.resolveLocal(ctx.ID().getText()) != null) {
                throw new RuntimeException("Variable: '" + ctx.ID().getText() + "' not declared");
            }
            // Prüfen, ob es sich um eine Referenz handelt
            // Ist eine Referenz
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
                        throw new RuntimeException("Variable Ref: '" + ctx.ID().getText() + " and " + id.getText() + "' false type");
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
                } else if (ctx.expr().getClass() == CppParser.ClassvarContext.class) {
                    System.out.println("Classvar");
                } else {
                    throw new RuntimeException("Referenzen sind nur auf Variablen möglich");
                }
            } else {
                asttype = AST.Types.ASSIGN;
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
        if (!acc.kinder.get(0).rtype.equals(scope.resolve(ctx.ID().getText()).type.getName())){
            throw new RuntimeException("Variable: '" + ctx.ID().getText() + "' false type");
        }
        acc = acc.prev;
    }
    // Assignment Array
    public void enterAssignarrayelem(CppParser.AssignarrayelemContext ctx) {
        // Prüfen, ob Array existiert
        if (scope.resolve(ctx.ID().getText()) == null || !ctx.getChild(1).getText().equals("[")){
            throw new RuntimeException("Array: '" + ctx.ID().getText() + "' not declared or not a Array");
        } else if (scope.resolve(ctx.ID().getText()).isConst) {
            throw new RuntimeException("Array: '" + ctx.ID().getText() + "' is const");
        }
        // Erstellen des Knotens und hinzufügen zum AST
        AST a = new AST(ctx.ID().getText(), AST.Types.ASSIGNARRAYELEMENT, acc, scope, null, scope.resolve(ctx.ID().getText()).isConst);
        acc.addChild(a);
        acc = a;
    }
    public void exitAssignarrayelem(CppParser.AssignarrayelemContext ctx) {
        if (!acc.kinder.get(1).rtype.equals(scope.resolve(ctx.ID().getText()).type.getName())){
            throw new RuntimeException("Variable: '" + ctx.ID().getText() + "' false type");
        }
        acc.rtype = scope.resolve(ctx.ID().getText()).type.getName();
        acc = acc.prev;
    }
    // Increment Array Element
    public void enterIncarray(CppParser.IncarrayContext ctx) {
        // Prüfen, ob Array existiert
        if (scope.resolve(ctx.ID().getText()) == null || scope.resolve(ctx.ID().getText()).isConst){
            throw new RuntimeException("Array: '" + ctx.ID().getText() + "' not declared or Array is const");
        }
        AST a = new AST(ctx.ID().getText(), AST.Types.INCARRAY, acc, scope, null, scope.resolve(ctx.ID().getText()).isConst);
        acc.addChild(a);
        acc = a;
    }
    public void exitIncarray(CppParser.IncarrayContext ctx) {
        acc = acc.prev;
    }
    // Decrement Array Element
    public void enterDecarray(CppParser.DecarrayContext ctx) {
        // Prüfen, ob Array existiert
        if (scope.resolve(ctx.ID().getText()) == null || scope.resolve(ctx.ID().getText()).isConst){
            throw new RuntimeException("Array: '" + ctx.ID().getText() + "' not declared or Array is const");
        }
        AST a = new AST(ctx.ID().getText(), AST.Types.DECARRAY, acc, scope, null, scope.resolve(ctx.ID().getText()).isConst);
        acc.addChild(a);
        acc = a;
    }
    public void exitDecarray(CppParser.DecarrayContext ctx) {
        acc = acc.prev;
    }
    // Assignment Array
    public void enterAssignnewarray(CppParser.AssignnewarrayContext ctx) {
        AST.Types asttype = AST.Types.ARRAYASSIGN;
        String type;
        Boolean isConst = false;
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
        // Erst ab dem zweiten Kind prüfen, da das erste die Arraygröße ist
        for (int i = 1; i < acc.kinder.size(); i++) {
            AST kind = acc.kinder.get(i);
            if (!kind.rtype.equals(scope.resolve(ctx.ID().getText()).type.getName())) {
                throw new RuntimeException("Variable: '" + ctx.ID().getText() + "' false type");
            }
        }
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

        // Prüfen, ob es sich um eine Klasse handelt
        Symbol classSymbol = scope.resolve(object.type.getName());
        if (classSymbol == null || !(classSymbol instanceof Struct)) {
            throw new RuntimeException("Type: '" + object + "' not found or not a struct/class");
        }

        // Prüfen, ob die Variable in der Klasse existiert
        Struct structSymbol = (Struct) classSymbol;
        Symbol member = structSymbol.resolveMember(var);
        if (member == null) {
            throw new RuntimeException("Member: '" + var + "' not found in struct/class '" + object + "'");
        }
        if (member.isConst){
            throw new RuntimeException("Member: '" + var + "' is const");
        }

        // Erstellen des Knotens und hinzufügen zum AST
        AST a = new AST(ctx.ID(1).getText(), AST.Types.ASSIGN, acc, null, scope.resolve(ctx.ID(1).getText()), false);
        a.rtype = member.type.getName();
        acc.addChild(a);
        acc = a;
        AST b = new AST(ctx.ID(0).getText(), AST.Types.CLASS, acc, scope, scope.resolve(ctx.ID(0).getText()), false);
        acc.addChild(b);
    }
    public void exitAssignclassvar(CppParser.AssignclassvarContext ctx) {
        if (!acc.rtype.equals(acc.kinder.get(1).rtype)){
            throw new RuntimeException("Variable: '" + ctx.ID(1).getText() + "' false type");
        }
        acc = acc.prev;
    }
    // Assign new Class
    public void enterAssignnewclass(CppParser.AssignnewclassContext ctx) {
        // Prüfen, ob es sich um eine Konstante handelt
        Boolean isConst = false;
        String className;
        if (ctx.getParent().getChild(0).getText().equals("const")) {
            isConst = true;
            className = ctx.getParent().getChild(1).getText();
        } else {
            className = ctx.getParent().getChild(0).getText();
        }
        // Prüfen, ob Variable oder Klasse existiert
        if (scope.resolveLocal(ctx.ID().getText()) == null && scope.resolve(className) != null) {
            // Erstellen des Symbols und Knotens und hinzufügen zum Scope und AST
            SymbolVariable clazz = new SymbolVariable(ctx.ID().getText(), new SymbolType(className), isConst);
            AST a = new AST(ctx.ID().getText(), AST.Types.ASSIGNCLASS, acc, scope, scope.bind(clazz), isConst);
            acc.addChild(a);
            acc = a;
            AST b = new AST(className, AST.Types.CLASS, acc, scope, null, false);
            acc.addChild(b);
        } else {
            throw new RuntimeException("Variable: '" + ctx.ID().getText() + "' already declared or Class: '" + className + "' not declared");
        }
    }
    public void exitAssignnewclass(CppParser.AssignnewclassContext ctx) {
        acc = acc.prev;
    }





    //
    // Klassen
    //
    // Klassendefinition
    public void enterClassdef(CppParser.ClassdefContext ctx) {
        Clazz parent = null;
        if (ctx.vererbung() != null) {
            String name = ctx.vererbung().ID().getText();
            parent = (Clazz) scope.resolve(name);
        }
        Clazz clazz = new Clazz(ctx.ID().getText(), parent, scope);
        AST a = new AST(ctx.ID().getText(), AST.Types.CLASSDEF, acc, scope, scope.bind(clazz), false);
        acc.addChild(a);
        acc = a;
        scope = clazz.scope;
    }
    public void exitClassdef(CppParser.ClassdefContext ctx) {
        acc = acc.prev;
        scope.print();
        scope = scope.prev;
    }
    //Konstruktor
    public void enterConstructor(CppParser.ConstructorContext ctx) {
        if (!ctx.ID().getText().equals(acc.value.toString())) {
            throw new RuntimeException("Constructor: '" + ctx.ID().getText() + "' passt nicht zum Klassennamen");
        }
        SymbolFunction func = new SymbolFunction(
                ctx.ID().getText(),
                ctx.ID().getText(),
                scope,
                null,
                false,
                null
        ); // TODO - Ist die Declaration eines Construktors relvant oder darauf zu achten
        AST a = new AST(ctx.ID().getText(), AST.Types.CONSTRUCTOR, acc, scope, scope.bind(func), false);
        func.functionAST = a;
        acc.addChild(a);
        acc = a;
        scope = func.scope;
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




    //
    // Funktionen
    //
    // Declaration Funktion
    public void enterDeclfunc(CppParser.DeclfuncContext ctx) {
        String rtype = ctx.getChild(0).getText();
        // Erstellen der Parameterliste
        List<String> paramTypeList = new ArrayList<>();
        if (ctx.defparamlist() != null && ctx.defparamlist().defparam() != null) {
            for (CppParser.DefparamContext defpCtx : ctx.defparamlist().defparam()) {
                if (defpCtx.getChild(0).getClass() == CppParser.DeclContext.class) {
                    CppParser.DeclContext declCtx = (CppParser.DeclContext) defpCtx.getChild(0);
                    paramTypeList.add(declCtx.getChild(0).getText());
                } else if (defpCtx.getChild(0).getClass() == CppParser.AssignnewContext.class) {
                    CppParser.AssignnewContext declCtx = (CppParser.AssignnewContext) defpCtx.getChild(0);
                    paramTypeList.add(declCtx.basetype().getText());
                } else if (defpCtx.getChild(0).getClass() == CppParser.DeclnewContext.class) {
                    CppParser.DeclnewContext declCtx = (CppParser.DeclnewContext) defpCtx.getChild(0);
                    paramTypeList.add(declCtx.basetype().getText());
                } else if (defpCtx.getChild(0).getClass() == CppParser.DefrefvarContext.class) {
                    CppParser.DefrefvarContext declCtx = (CppParser.DefrefvarContext) defpCtx.getChild(0);
                    paramTypeList.add(declCtx.basetype().getText() + "&");
                } else if (defpCtx.getChild(0).getClass() == CppParser.DeclrefvarContext.class) {
                    CppParser.DeclrefvarContext declCtx = (CppParser.DeclrefvarContext) defpCtx.getChild(0);
                    paramTypeList.add(declCtx.basetype().getText() + "&");
                }
            }
        }
        // Funktionen können mit selben Signaturen mehrfach deklariert werden
        // => Werden einfach im Scope überschrieben, da dies eine Hashmap ist und somit nur ein Eintrag pro Key existieren kann
        SymbolFunction func = new SymbolFunction(ctx.ID().getText(), rtype, scope, null, true, paramTypeList);
        AST a = new AST(ctx.ID().getText(), AST.Types.FUNCTION_DEC, acc, scope, scope.bind(func), false);
        func.functionAST = a;
        a.rtype = rtype;
        acc.addChild(a);
        acc = a;
        scope = func.scope;
        AST b = new AST(ctx.getChild(0).getText(), AST.Types.RETURNTYPE, acc, scope, null, false);
        b.rtype = rtype;
        acc.addChild(b);
    }
    public void exitDeclfunc(CppParser.DeclfuncContext ctx) {
        acc = acc.prev;
        scope = scope.prev;
    }
    // Definition Funktion
    public void enterFuncdef(CppParser.FuncdefContext ctx) {
        String rtype = ctx.getChild(0).getText();
        // Prüfen, ob Rückgabetyp existiert
        if (scope.resolve(rtype) == null) {
            throw new RuntimeException("Return type: '" + rtype + "' not declared");
        }
        // Parameterliste extrahieren
        List<String> paramTypeList = new ArrayList<>();
        if (ctx.defparamlist() != null && ctx.defparamlist().defparam() != null) {
            for (CppParser.DefparamContext defpCtx : ctx.defparamlist().defparam()) {
                if (defpCtx.getChild(0).getClass() == CppParser.DeclContext.class) {
                    CppParser.DeclContext declCtx = (CppParser.DeclContext) defpCtx.getChild(0);
                    paramTypeList.add(declCtx.getChild(0).getText());
                } else if (defpCtx.getChild(0).getClass() == CppParser.AssignnewContext.class) {
                    CppParser.AssignnewContext declCtx = (CppParser.AssignnewContext) defpCtx.getChild(0);
                    paramTypeList.add(declCtx.basetype().getText());
                } else if (defpCtx.getChild(0).getClass() == CppParser.DeclnewContext.class) {
                    CppParser.DeclnewContext declCtx = (CppParser.DeclnewContext) defpCtx.getChild(0);
                    paramTypeList.add(declCtx.basetype().getText());
                } else if (defpCtx.getChild(0).getClass() == CppParser.DefrefvarContext.class) {
                    CppParser.DefrefvarContext declCtx = (CppParser.DefrefvarContext) defpCtx.getChild(0);
                    paramTypeList.add(declCtx.basetype().getText() + "&");
                } else  if (defpCtx.getChild(0).getClass() == CppParser.DeclrefvarContext.class) {
                    CppParser.DeclrefvarContext declCtx = (CppParser.DeclrefvarContext) defpCtx.getChild(0);
                    paramTypeList.add(declCtx.basetype().getText() + "&");
                    System.out.println("REF PARAM");
                }
            }
        }
        // Erstellen des Symbols und Knotens und hinzufügen zum Scope und AST
        SymbolFunction newfunc = new SymbolFunction(ctx.ID().getText(), rtype, scope, null, false, paramTypeList);
        AST a = new AST(ctx.ID().getText(), AST.Types.FUNCTION_DEF, acc, scope, scope.bind(newfunc), false);
        a.rtype = rtype;
        newfunc.functionAST = a;
        acc.addChild(a);
        acc = a;
        scope = newfunc.scope;
        AST b = new AST(ctx.getChild(0).getText(), AST.Types.RETURNTYPE, acc);
        acc.addChild(b);
    }
    public void exitFuncdef(CppParser.FuncdefContext ctx) {
        acc = acc.prev;
        scope.print();
        scope = scope.prev;
    }
    // Klassenfunktion
    public void enterClassfunc(CppParser.ClassfuncContext ctx) {
        AST a = new AST(ctx.ID(1).getText(), AST.Types.FUNCTION_DEF, acc);
        acc.addChild(a);
        acc = a;
        AST b = new AST(ctx.ID(0).getText(), AST.Types.CLASS, acc);
        acc.addChild(b);
        AST c = new AST(ctx.getChild(0).getText(), AST.Types.RETURNTYPE, acc);
        acc.addChild(c);
    }
    public void exitClassfunc(CppParser.ClassfuncContext ctx) {
        acc = acc.prev;
    }
    // Call Funktion
    public void enterCallfunc(CppParser.CallfuncContext ctx) {
        AST a = new AST(ctx.getChild(0).getText(), AST.Types.FUNCTION_CALL, acc);
        acc.addChild(a);
        acc = a;
    }
    public void exitCallfunc(CppParser.CallfuncContext ctx) {
        List<String> paramTypeList = new ArrayList<>();
        AST paramlist =  acc.kinder.get(0);
        for (AST kind : paramlist.kinder) {
            paramTypeList.add(kind.rtype);
        }
        SymbolFunction func = (SymbolFunction) scope.resolve(ctx.ID().getFirst().getText() + paramTypeList.toString());

        if (func == null) {
            throw new RuntimeException("Function: '" + ctx.ID().getFirst().getText() + paramTypeList.toString() + "' not declared");
        }
        if (func.functionAST != null){
            acc.rtype = func.functionAST.rtype;
        }
        acc = acc.prev;
    }
    // Param Lists
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
    // call Param
    public void enterCallparam(CppParser.CallparamContext ctx) {

    }
    public void exitCallparam(CppParser.CallparamContext ctx) {

    }
    // Builtin
    public void enterBuiltinstmt(CppParser.BuiltinstmtContext ctx) {
        AST.Types asttype = AST.Types.UNDEFINED;
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
        acc.addChild(a);
        acc = a;
    }
    public void exitIfelseblock(CppParser.IfelseblockContext ctx) {
        acc = acc.prev;
    }
    // IF
    public void enterIfblock(CppParser.IfblockContext ctx) {
        AST a = new AST("ifblock", AST.Types.IF_BLOCK, acc);
        acc.addChild(a);
        acc = a;
    }
    public void exitIfblock(CppParser.IfblockContext ctx) {
        acc = acc.prev;
    }
    // ELSE
    public void enterElseblock(CppParser.ElseblockContext ctx) {
        AST a = new AST("elseblock", AST.Types.ELSE_BLOCK, acc);
        acc.addChild(a);
        acc = a;
    }
    public void exitElseblock(CppParser.ElseblockContext ctx) {
        acc = acc.prev;
    }
    // WHILE
    public void enterWhileblock(CppParser.WhileblockContext ctx) {
        AST a = new AST("whileblock", AST.Types.WHILE_BLOCK, acc);
        acc.addChild(a);
        acc = a;
    }
    public void exitWhileblock(CppParser.WhileblockContext ctx) {
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
    // ELSE Condition
    public void enterElseconn(CppParser.ElseconnContext ctx) {
        if (ctx.getChildCount() > 1){
            AST a = new AST("elseconn", AST.Types.CONN, acc);
            acc.addChild(a);
            acc = a;
        }
    }
    public void exitElseconn(CppParser.ElseconnContext ctx) {
        if (ctx.getChildCount() > 1){
            acc = acc.prev;
        }
    }
    // WHILE Condition
    public void enterWhileconn(CppParser.WhileconnContext ctx) {
        AST a = new AST("whileconn", AST.Types.CONN, acc);
        acc.addChild(a);
        acc = a;
    }
    public void exitWhileconn(CppParser.WhileconnContext ctx) {
        acc = acc.prev;
    }
    // Body
    public void enterBody(CppParser.BodyContext ctx) {
        if (ctx.getChildCount() > 0){
            AST a = new AST("body", AST.Types.BODY, acc);
            acc.addChild(a);
            acc = a;
        }
    }
    public void exitBody(CppParser.BodyContext ctx) {
        if (ctx.getChildCount() > 0){
            acc = acc.prev;
        }
    }



    //
    // EXPRESSIONS
    //
    public void enterCompare(CppParser.CompareContext ctx) {
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
    }
    public void exitCompare(CppParser.CompareContext ctx) {
        // TODO - Typenvergleich
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
        if (type1.equals("int") && type2.equals("bool") || type1.equals("bool") && type2.equals("int")) {
            rtype = "int";
        } else if (!type1.equals(type2)){
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
    }
    public void exitAddeq(CppParser.AddeqContext ctx) {
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
        if (type1.equals("int") && type2.equals("bool") || type1.equals("bool") && type2.equals("int")) {
            rtype = "int";
        } else if (!type1.equals(type2)){
            throw new RuntimeException("Subtraction: '" + acc.kinder.get(0).rtype + "' and '" + acc.kinder.get(1).rtype + "' not possible");
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
    }
    public void exitSubeq(CppParser.SubeqContext ctx) {
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
        if (type1.equals("int") && type2.equals("bool") || type1.equals("bool") && type2.equals("int")) {
            rtype = "int";
        } else if (!type1.equals(type2)){
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
    }
    public void exitMuleq(CppParser.MuleqContext ctx) {
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
        if (type1.equals("int") && type2.equals("bool") || type1.equals("bool") && type2.equals("int")) {
            rtype = "int";
        } else if (!type1.equals(type2)){
            throw new RuntimeException("Division: '" + acc.kinder.get(0).rtype + "' and '" + acc.kinder.get(1).rtype + "' not possible");
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
    }
    public void exitDiveq(CppParser.DiveqContext ctx) {
        acc = acc.prev;
    }
    // Increment
    public void enterInc(CppParser.IncContext ctx) {
        AST a = new AST("++", AST.Types.INC, acc);
        acc.addChild(a);
        acc = a;
    }
    public void exitInc(CppParser.IncContext ctx) {
        acc = acc.prev;
    }
    // Decrement
    public void enterDec(CppParser.DecContext ctx) {
        AST a = new AST("--", AST.Types.DEC, acc);
        acc.addChild(a);
        acc = a;
    }
    public void exitDec(CppParser.DecContext ctx) {
        acc = acc.prev;
    }



    // von nutzen? (nicht sicher)
    public void enterInitlist(CppParser.InitlistContext ctx) {
    }

    public void exitInitlist(CppParser.InitlistContext ctx) {
    }

    /*
    public void enterRefexpr(CppParser.RefexprContext ctx) {
    }

    public void exitRefexpr(CppParser.RefexprContext ctx) {
    }

     */




    //
    // Lexar
    //
    public void enterNum(CppParser.NumContext ctx) {
        AST a = new AST(ctx.getText(), AST.Types.NUM, acc);
        a.rtype = "int";
        acc.addChild(a);

    }
    public void enterBool(CppParser.BoolContext ctx) {
        AST a = new AST(ctx.getText(), AST.Types.BOOL, acc);
        a.rtype = "bool";
        acc.addChild(a);
    }
    public void enterId(CppParser.IdContext ctx) {
        if (scope.resolve(ctx.getText()) == null){
            throw new RuntimeException("Variable: '" + ctx.getText() + "' not declared");
        }
        AST a = new AST(ctx.getText(), AST.Types.ID, acc);
        a.rtype = scope.resolve(ctx.getText()).type.getName();
        acc.addChild(a);
    }
    public void enterChar(CppParser.CharContext ctx) {
        AST a = new AST(ctx.getText(), AST.Types.CHAR, acc);
        a.rtype = "char";
        acc.addChild(a);
    }
}

