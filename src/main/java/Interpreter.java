import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Interpreter {
    AST root;
    Map<String, Symbol> scope = new HashMap<>();

    Environment environment = new Environment(null);


    public void interpret(AST root){
        this.root = root;
        eval(root);
    }

    public Object eval(AST t){
        if (t == null) return null;

        switch (t.asttype) {
        case START:
            // Durchlaufen des gesammten AST
            for (AST c : t.kinder) {
                eval(c);
            }

            // Main Funktion finden und ausführen
            VariableFunction main = null;
            for (String key : environment.values.keySet()) {
                if (key.contains("main[")) {
                    main = (VariableFunction) environment.get(key);
                }
            }
            if (main == null) {
                throw new RuntimeException("No main function found");
            } else {
                try {
                    for (AST c : main.ast.kinder) {
                        eval(c);
                    }
                } catch (ReturnException e){
                    System.out.println("Main return: " + e.getValue());
                }
            }
            break;
        case RETURN:
            // TODO - Bei rückgabe von Referenzen muss ich die Variable und nicht den Wert zurückgeben - Was passiert bei Lokalen Variablen?
            if (t.kinder.size() > 0){
                Object returnvalue = eval(t.kinder.get(0));
                if (returnvalue instanceof String){
                    returnvalue = environment.get((String) returnvalue);
                }
                throw new ReturnException(returnvalue);
            } else {
                return null;
            }
        case ASSIGNNEW:
            if (t.kinder.get(0).asttype == AST.Types.ASSIGNCLASS){
                createClassObject(t.kinder.get(0), false);
            } else {
                // TODO - Was passiert bei einer reinen dekklaration? Welcher Wert wird zugewiesen?
                if (t.rtype.equals("int") || t.rtype.equals("char") || t.rtype.equals("bool")){
                    String assignnewid = t.value;
                    Object assignnewvalue = eval(t.kinder.get(0));
                    environment.define(assignnewid, assignnewvalue);
                } else {
                    System.out.println("Die erstellung und der Zugriff von Klassenobjekten in Arrays wird nicht unterstützt");
                    //createClassObject(t, true);
                }

            }
            return environment.get(t.value);
        case ASSIGNOLD:
            if (t.kinder.get(0).asttype == AST.Types.ID){
                Object assignoldvalue = eval(t.kinder.get(0));
                if (assignoldvalue instanceof String){
                    assignoldvalue = environment.get((String) assignoldvalue);
                }
                environment.assign(t.value, assignoldvalue);
            } else  if (t.kinder.get(0).asttype == AST.Types.CLASS){
                Environment clazzAssignOld = (Environment) (environment.getVariable(t.kinder.get(0).value)).value;
                clazzAssignOld.assign(t.value, eval(t.kinder.get(1)));
            } else {
                Object assignoldvalue = eval(t.kinder.get(0));
                environment.assign(t.value, assignoldvalue);
            }
            return environment.get(t.value);
        case SCOPE:
            environment = new Environment(environment);
            for (AST c : t.kinder) {
                eval(c);
            }
            environment = environment.prevenv;
            break;
        case DECLARATION:
            String declarationid = t.value;
            // Prüfen, ob Variable schon existiert
            if (environment.getVariable(declarationid) == null){
                // Die Basistypen bekommen wie in c++ zufällige werte, welche dann in den jeweiligen Typ interpretiert werden
                if (t.rtype.equals("int")){
                    environment.define(declarationid, (int) (Math.random()*1000000));
                } else if (t.rtype.equals("char")){
                    environment.define(declarationid, (char) (Math.random()*26 + 'a'));
                } else if (t.rtype.equals("bool")){
                    environment.define(declarationid, (boolean) (Math.random() > 0.5));
                } else {
                    System.out.println("Die erstellung und der Zugriff von Klassenobjekten in Arrays wird nicht unterstützt");
                    //createClassObject(t, true);
                }
            } else {
                System.out.println("Variable " + declarationid + " already defined");
            }
            break;
        case ARRAYDECLARATION:
            String arraydeclarationid = t.value;
            int arraydeclerationsize = (Integer) eval(t.kinder.get(0));
            Object[] arraydeclaration = new Object[arraydeclerationsize];
            for (int i = 0; i < arraydeclerationsize; i++){
                if (t.rtype.equals("int")){
                    arraydeclaration[i] = (int) (Math.random() * 1000000);
                } else if (t.rtype.equals("char")){
                    arraydeclaration[i] = (char) (Math.random() * 26 + 'a');
                } else if (t.rtype.equals("bool")){
                    arraydeclaration[i] = (boolean) false;
                } else {
                    // TODO - Fehlerbehandlung und Klassenerstellung
                    System.out.println("Die erstellung und der Zugriff von Klassenobjekten in Arrays wird nicht unterstützt");
                    //arraydeclaration[i] = createClassObject(t, true);
                }
            }
            environment.define(arraydeclarationid, arraydeclaration);
            break;
        case REF:
            System.out.println("Ref 123 " + t.kinder.size());
            if (t.kinder.size() == 1){
                Variable refVar = environment.getVariable(t.kinder.get(0).value);
                environment.defineReference(t.value, refVar);
            } else {
                Variable refVar = environment.getClassMemberVariable(t.kinder.get(1).value, t.kinder.get(0).value);
                environment.defineReference(t.value, refVar);
            }
            break;
        case ASSIGNARRAYELEMENT:
            Object assignarrayelementindex = eval(t.kinder.get(0));
            Object assignarrayelementarray = environment.get(t.value);
            if (assignarrayelementarray instanceof Object[]){
                if (t.kinder.get(0).asttype == AST.Types.ID){
                    assignarrayelementindex = environment.get(t.kinder.get(0).value);
                }
                if ((Integer) assignarrayelementindex >= ((Object[]) assignarrayelementarray).length){
                    throw new RuntimeException("Array index out of bounds");
                }

                if (t.kinder.get(1).asttype == AST.Types.ID){
                    ((Object[]) assignarrayelementarray)[(Integer) assignarrayelementindex] = environment.get(t.kinder.get(1).value);
                } else {
                    ((Object[]) assignarrayelementarray)[(Integer) assignarrayelementindex] = eval(t.kinder.get(1));
                }
                environment.assign(t.value, assignarrayelementarray);
            } else {
                // TODO - Fehlerbehandlung
                throw new RuntimeException("Array: " + t.value + " is not an array");
            }
            break;
        case ARRAYSIZE:
            return (Integer) eval(t.kinder.get(0));
        case INCARRAY:
            return null;
        case DECARRAY:
            return null;
        case ARRAYASSIGN:
            int arrayassignsize = (Integer) eval(t.kinder.get(0)) == null ? 1 : (Integer) eval(t.kinder.get(0));
            String arrayassignid = t.value;
            Object[] arrayassign = new Object[arrayassignsize];
            // Durchlaufen des Arrays und der Kinder und Zuweisung der Werte, falls vorhanden ansonsten Zufallswerte
            for (int i = 0; i < arrayassignsize; i++){
                Object arrayassignvalue = null;
                // Prüfen, ob es ausreichend initialisierungen gibt
                if (i < t.kinder.size()-1){
                    if (t.kinder.get(i+1).asttype == AST.Types.ID){
                        arrayassignvalue = environment.get(t.kinder.get(i+1).value);
                    } else {
                        arrayassignvalue = eval(t.kinder.get(i + 1));
                    }
                }
                // Ist ein Initialisierungswert für diesen Index vorhanden? => Wenn ja dann einfügen sonst Zufallswert
                if (arrayassignvalue != null){
                    if (arrayassignvalue instanceof String){
                        arrayassignvalue = environment.get((String) arrayassignvalue);
                    }
                    arrayassign[i] = arrayassignvalue;
                } else {
                    if (t.rtype.equals("int")) {
                        arrayassign[i] = (int) (Math.random() * 1000000);
                    } else if (t.rtype.equals("char")) {
                        arrayassign[i] = (char) (Math.random() * 26 + 'a');
                    } else if (t.rtype.equals("bool")) {
                        arrayassign[i] = (boolean) false;
                    } else {
                        System.out.println("Die erstellung und der Zugriff von Klassenobjekten in Arrays wird nicht unterstützt");
                        //arrayassign[i] = createClassObject(t, false);
                    }
                }
            }
            // Array in der Umgebung speichern
            environment.define(arrayassignid, arrayassign);
            break;
        case ARRAYELEMENT:
            Object arrayelementindex = eval(t.kinder.get(0));
            Object arrayelementarray = environment.get(t.value);
            if (arrayelementarray instanceof Object[]){
                if (t.kinder.get(0).asttype == AST.Types.ID){
                    arrayelementindex = environment.get(t.kinder.get(0).value);
                }
                if ((Integer) arrayelementindex >= ((Object[]) arrayelementarray).length){
                    throw new RuntimeException("Array index out of bounds");
                }
                return ((Object[]) environment.get(t.value))[(Integer) arrayelementindex];
            } else {
                throw new RuntimeException("Array: " + t.value + " is not an array");
            }


            //
            // Klassen
            //
        case ASSIGNCLASS:
            if (t.kinder.get(0).asttype == AST.Types.CLASS && t.kinder.size() > 1){
                createClassObject(t, false);
            }
            break;
        case CLASS:
            //System.out.println("Class: " + t.value);
            // TODO - Klassen erstellen und speichern
            break;
        case CLASSDEF:
            //System.out.println("Enter classdef " + t.value);
            Environment preenvironmentclassdef = environment;
            environment = new Environment(environment);
            for (AST c : t.kinder) {
                eval(c);
            }
            Environment classdefEnvironment = environment;

            environment = preenvironmentclassdef;

            // Ermitteln der Eltern und speichern
            VariableClazz parent = null;
            if (t.kinder.get(0).asttype == AST.Types.PARENT){
                //System.out.println("Parent: " + t.kinder.get(0).value);
                //System.out.println((environment.get(t.kinder.get(0).value)).getClass());
                parent = (VariableClazz) ((Variable) environment.getVariable(t.kinder.get(0).value)).value;
            }
            VariableClazz classdefVar = new VariableClazz(t, classdefEnvironment, parent);

            environment.define(t.value, classdefVar);
            //System.out.println("exit classdef");
            break;
        case CONSTRUCTOR:
            if (!((SymbolFunction) t.symbol).decl){
                // Speichern der Funktion als SymbolFunction, da dort bereits der AST gespeichert ist
                SymbolFunction funcSymbolConstructor = (SymbolFunction) t.symbol;
                VariableFunction varFunctionDef = new VariableFunction(funcSymbolConstructor.functionAST, environment);
                // Aktuelle Umgebung in der Funktionsdefinition speichern
                // Funktionen in das Environment speichern
                environment.define(t.symbol.name, varFunctionDef);
            }
            break;
        case DESTRUCTOR:
            System.out.println("Destructor: " + t.value);
            // TODO - Destruktoren erstellen und speichern
            break;
        case PARENT:
            //System.out.println("Parent: " + t.value);
            // TODO - Vererbung
            break;



        //
        // Funktionen
        //
        case FUNCTION_DEC:
            SymbolFunction funcSymbolFuncDec = (SymbolFunction) t.symbol;
            if (funcSymbolFuncDec.abstractFunction){
                // Speichern der Funktion als SymbolFunction, da dort bereits der AST gespeichert ist
                VariableFunction abstractFunc = new VariableFunction(funcSymbolFuncDec.functionAST, environment);
                abstractFunc.abstractFunction = true;
                // Aktuelle Umgebung in der Funktionsdefinition speichern
                // Funktionen in das Environment speichern
                environment.define(t.symbol.name, abstractFunc);
            }
                /*
                TODO - Funktionsdeclarationen müssen nicht in dem Environment gespeichert werden - Definitionen schon
                 Oder doch? Was passiert bei Aufrufen, welche ich vor der Definition gemacht habe?
                 Diese sind dann noch nicht im Environment gespeichert
                */
            break;
        case FUNCTION_DEF:
            // Speichern der Funktion als SymbolFunction, da dort bereits der AST gespeichert ist
            SymbolFunction funcSymbolFuncDef = (SymbolFunction) t.symbol;
            VariableFunction varConstructorDef = new VariableFunction(funcSymbolFuncDef.functionAST, environment);
            // Aktuelle Umgebung in der Funktionsdefinition speichern
            // Funktionen in das Environment speichern
            environment.define(t.symbol.name, varConstructorDef);
            break;
        case FUNCTION_CALL:
            VariableFunction funcFuncCall = null;
            Environment preenvironment = environment;

            if (t.kinder.size() > 0 && t.kinder.getLast().asttype.equals(AST.Types.CLASS)){
                // Abrufen der Klassenfunktion und setzen des Environments der Funktion auf das des Klassenobjekts
                funcFuncCall = (VariableFunction) ((Variable) environment.getClassMember(t.symbol.name, t.kinder.getLast().value)).value;
                // funcFuncCall.env = (Environment) environment.get(t.kinder.getLast().value);
            } else {
                funcFuncCall = (VariableFunction) environment.get(t.symbol.name);
            }

            if (funcFuncCall == null){
                throw new RuntimeException("Function " + t.symbol.name + " not defined");
            }
            // Umgebung speichern
            try{
                // Temporäre Umgebung für die Funktionsparameter erstellen
                // Environment wird erst später gesetzt, um rekursion und verschachtelte Funktionsaufrufe zu ermöglichen
                Environment funcEnvironment = new Environment(funcFuncCall.env);
                funcEnvironment.print();
                // Anzahl der Funktionsparameter
                int funcCallParams = funcFuncCall.ast.kinder.get(1).kinder.size();
                if (funcCallParams > 0){
                    for (int i = 0; i < funcCallParams ; i++){
                        if (t.kinder.get(0).kinder.get(i).asttype == AST.Types.CLASS){
                            continue;
                        }
                        Object funcCallArg = eval(t.kinder.get(0).kinder.get(i));
                        // Parameter auswerten
                        // TODO - Je nach dem ob es sich um einer Refernz handelt oder nicht darf ich diese auswerten oder nicht
                        if (funcCallArg instanceof String){
                            // Prüfen ob es sich um eine Referenz handelt
                            if (funcFuncCall.ast.kinder.get(1).kinder.get(i).asttype == AST.Types.REF){
                                // Greifen des Referenzobjekts und speichern also tatsächloche Referenz in der neuen Variable
                                Variable refVarFuncCall = preenvironment.getVariable((String) funcCallArg);
                                funcEnvironment.defineReference(funcFuncCall.ast.kinder.get(1).kinder.get(i).value, refVarFuncCall);
                            } else {
                                // Auswerten von Variablen
                                funcCallArg = preenvironment.get((String) funcCallArg);
                                funcEnvironment.define(funcFuncCall.ast.kinder.get(1).kinder.get(i).value, funcCallArg);
                            }
                        } else {
                            funcEnvironment.define(funcFuncCall.ast.kinder.get(1).kinder.get(i).value, funcCallArg);
                        }
                    }
                }

                // Umgebung auf die Funktionsumgebung setzen
                environment = funcEnvironment;

                // Durchlaufen und ausführen der Funktion
                for (AST c : funcFuncCall.ast.kinder.get(2).kinder) {
                    eval(c);
                }
            } catch (ReturnException e){
                // Umgebung zurücksetzen und rückgabe aus der Funktion
                environment = preenvironment;
                return(e.getValue());
            }
            // Umgebung zurücksetzen
            environment = preenvironment;
            break;
        case PARAMLIST:
            for (AST c : t.kinder) {
                eval(c);
            }
            break;
        case RETURNTYPE:
            break;


        // Kontrollstrukturen
        case AST.Types.IF_ELSE_BLOCK:
            Object if_else_block_condition = eval(t.kinder.get(0));
            if (mathhelper(if_else_block_condition) != 0){
                eval(t.kinder.get(1));
            } else {
                eval(t.kinder.get(2));
            }
            break;

        case AST.Types.WHILE_BLOCK:
            for (AST c : t.kinder) {
                Object while_block_condition = eval(t.kinder.get(0));
                while (mathhelper(while_block_condition) != 0){
                    eval(t.kinder.get(1));
                    while_block_condition = eval(t.kinder.get(0));
                }
            }
            break;
        case AST.Types.IF_BLOCK:
            return eval(t.kinder.get(0));
        case AST.Types.ELSE_BLOCK:
            return eval(t.kinder.get(0));
        case AST.Types.CONN:
            if (t.kinder.size() == 1){
                return eval(t.kinder.get(0));
            }
            break;



        // LOGISCHE OPERATIONEN TODO - Typen prüfen und Fehlerbehandlung - Was passiert beim Verlgiech von Objekten
        case AST.Types.EQUAL:
            if (mathhelper(eval(t.kinder.get(0))) == mathhelper(eval(t.kinder.get(1)))){
                return true;
            } else {
                return false;
            }
        case AST.Types.NOT_EQUAL:
            if (mathhelper(eval(t.kinder.get(0))) != mathhelper(eval(t.kinder.get(1)))){
                return true;
            } else {
                return false;
            }
        case AST.Types.GREATER:
            if (mathhelper(eval(t.kinder.get(0))) > mathhelper(eval(t.kinder.get(1)))){
                return true;
            } else {
                return false;
            }
        case AST.Types.LOWER:
            if (mathhelper(eval(t.kinder.get(0))) < mathhelper(eval(t.kinder.get(1)))){
                return true;
            } else {
                return false;
            }
        case AST.Types.GREATER_EQUAL:
            if (mathhelper(eval(t.kinder.get(0))) >= mathhelper(eval(t.kinder.get(1)))){
                return true;
            } else {
                return false;
            }
        case AST.Types.LOWER_EQUAL:
            if (mathhelper(eval(t.kinder.get(0))) <= mathhelper(eval(t.kinder.get(1)))){
                return true;
            } else {
                return false;
            }
        case AST.Types.BODY:
            for (AST c : t.kinder) {
                eval(c);
            }
            break;



        // MATHEMATISCHE OPERATIONEN
        // TODO Mathemathanische Operationen Typen prüfen und Fehlerbehandlung und Interoverflow
        case AST.Types.ADD:
            Object addleft = eval(t.kinder.get(0));
            Object addright = eval(t.kinder.get(1));
            return mathhelper(addleft) + mathhelper(addright);
        case AST.Types.ADDEQ:
            String addeqvar = (String) eval(t.kinder.get(0));
            Object addeqvalue = eval(t.kinder.get(1));
            if (addeqvalue instanceof String){
                addeqvalue = environment.get((String) addeqvalue);
            }
            Object resultqddey = mathhelper(environment.get(addeqvar)) + mathhelper(addeqvalue);
            environment.assign((String) addeqvar, resultqddey);
            return environment.get(t.kinder.get(0).value);
        case AST.Types.SUB:
            Object subleft = eval(t.kinder.get(0));
            Object subright = eval(t.kinder.get(1));
            return mathhelper(subleft) - mathhelper(subright);
        case AST.Types.SUBEQ:
            String subeqvar = (String) eval(t.kinder.get(0));
            Object subeqvalue = eval(t.kinder.get(1));
            if (subeqvalue instanceof String){
                subeqvalue = environment.get((String) subeqvalue);
            }
            Object resultsubeqddey = mathhelper(environment.get(subeqvar)) - mathhelper(subeqvalue);
            environment.assign((String) subeqvar, resultsubeqddey);
            return environment.get(t.kinder.get(0).value);
        case AST.Types.MULL:
            Object mulleft = eval(t.kinder.get(0));
            Object mulright = eval(t.kinder.get(1));
            return mathhelper(mulleft) * mathhelper(mulright);
        case AST.Types.MULEQ:
            String muleqvar = (String) eval(t.kinder.get(0));
            Object muleqvalue = eval(t.kinder.get(1));
            if (muleqvalue instanceof String){
                muleqvalue = environment.get((String) muleqvalue);
            }
            Object resultmuleqddey = mathhelper(environment.get(muleqvar)) * mathhelper(muleqvalue);
            environment.assign((String) muleqvar, resultmuleqddey);
            return environment.get(t.kinder.get(0).value);
        case AST.Types.DIV:
            Object divleft = eval(t.kinder.get(0));
            Object divright = eval(t.kinder.get(1));
            if (mathhelper(divright) == 0){
                throw new RuntimeException("Division by zero");
            }
            return mathhelper(divleft) / mathhelper(divright);
        case AST.Types.DIVEQ:
            String diveqvar = (String) eval(t.kinder.get(0));
            Object diveqvalue = eval(t.kinder.get(1));
            if (diveqvalue instanceof String){
                diveqvalue = environment.get((String) diveqvalue);
            }
            if (mathhelper(diveqvalue) == 0){
                throw new RuntimeException("Division by zero");
            }
            Object resultdiveqddey = mathhelper(environment.get(diveqvar)) / mathhelper(diveqvalue);
            environment.assign((String) diveqvar, resultdiveqddey);
            return environment.get(t.kinder.get(0).value);
        case AST.Types.INC:
            int resultinc = 0;
            // Das incrementieren von Chars und Bools verhält sich eventuell anders als in C++
            if (AST.Types.ID == t.kinder.get(0).asttype){
                Object incvar = environment.get(t.kinder.get(0).value);
                resultinc = (Integer) mathhelper(incvar) + 1;
                environment.assign(t.kinder.get(0).value, resultinc);
            }
            return resultinc;
        case AST.Types.DEC:
            int resultdec = 0;
            // Das decrementieren von Chars und Bools verhält sich eventuell anders als in C++
            if (AST.Types.ID == t.kinder.get(0).asttype){
                Object decvar = environment.get(t.kinder.get(0).value);
                resultdec = (Integer) mathhelper(decvar) - 1;
                environment.assign(t.kinder.get(0).value, resultdec);
            }
            return resultdec;
        case AST.Types.PRINT_INT:
            System.out.println(builtinhelper(t));
            break;
        case AST.Types.PRINT_CHAR:
            System.out.println((char) builtinhelper(t));;
            break;
        case AST.Types.PRINT_BOOL:
            System.out.println(builtinhelper(t) == 0 ? "false" : "true");
            break;


        // "Primitive" Datentypen auswerden und zurückgeben
        case AST.Types.NUM:
            return Integer.parseInt(t.value);
        case AST.Types.CHAR:
            return (Character) (t.value.charAt(1));
        case AST.Types.ID:
            return t.value;
        case AST.Types.BOOL:
            return Boolean.parseBoolean(t.value);
        default:
            break;
        }
        return null;

    }



    // Hilfsfunktion zum Umrechnen von Objekten in Integer
    public int mathhelper(Object ob){
        //System.out.println("Mathhelper1: " + ob + " - Type: " + ob.getClass().getSimpleName());
        if (ob == null) return 0;
        switch (ob.getClass().getSimpleName()){
        case "Integer": ob = ob; break;
        case "String":
            //System.out.println("String in Mathhelper: " + ob);
            Object obvar = environment.get((String) ob);
            //System.out.println("String in Mathhelper obvar: " + obvar);
            switch (obvar.getClass().getSimpleName()){
            case "Integer": ob = obvar; break;
            case "Character": ob = (int) (Character) obvar; break;
            case "Boolean": ob = (Boolean) obvar ? 1 : 0; break;
            }
            break;
        case "Character": ob = (int) (Character) ob; break;
        case "Boolean": ob = (Boolean) ob ? 1 : 0; break;
        case "Variable": ob = ((Variable) ob).value; break;
        default: System.out.println("Default case in Mathhelper with Type: " + ob.getClass().getSimpleName()); break;
        }
        //System.out.println("Mathhelper2: " + ob + " - Type: " + ob.getClass().getSimpleName());
        return (Integer) ob;
    }

    public int builtinhelper(AST a){
        // Umwandlung von Objekten in Integer und auswerten fun Funktionen und Variablen
        if (AST.Types.ID == a.kinder.get(0).asttype) {
            return(mathhelper(environment.get(a.kinder.get(0).value)));
        } else if (AST.Types.CLASS == a.kinder.get(0).asttype) {
            if (a.kinder.get(1).asttype == AST.Types.ID){
                // Zugriff auf einfache ID
                Object builtinVar = environment.getClassMember(a.kinder.get(1).value, a.kinder.get(0).value);
                if (builtinVar == null){
                    System.out.println("Variable " + a.kinder.get(1).value + " not defined in class " + a.kinder.get(0).value +
                            " - Error in builtinhelper");
                    return 0;
                } else {
                    return mathhelper((((Variable) environment.getClassMember((a.kinder.get(1).value).toString(), a.kinder.get(0).value)).value));
                }

            } else {
                Object varFuncPrintIntClass = environment.getClassMember(a.kinder.get(1).symbol.name, a.kinder.get(0).value);
                VariableFunction funcFuncPrintIntClass = (VariableFunction) ((Variable) varFuncPrintIntClass).value;
                //System.out.println(mathhelper(eval(funcFuncPrintIntClass.ast)));
                Environment preenvironmentBuiltin = environment;
                //System.out.println(a.kinder.getLast().asttype);
                return(mathhelper(eval(a.kinder.getLast())));
            }

        } else {
            return(mathhelper(eval(a.kinder.get(0))));
        }
    }

    public Object createClassObject(AST t, boolean emptyConstructor){
        // Default Konstruktoraufruf, wenn keiner definiert ist

        if (emptyConstructor){
            System.out.println("Empty Constructor: " + t.rtype);

            // Prüfen, ob wenn konstruktoren vorhanden sind, ob auch ein Konstruktor ohne Parameter vorhanden ist
            VariableClazz declarationclassVar = (VariableClazz) environment.get(t.rtype);
            Environment declarationclass = declarationclassVar.env;

            ArrayList <Variable> constructors = new ArrayList<>();
            for (Variable v : ((Environment) declarationclass).values.values()){
                if (v.value.getClass().getSimpleName().equals("VariableFunction") ){
                    VariableFunction constructor = (VariableFunction) v.value;
                    if (constructor.ast.asttype == AST.Types.CONSTRUCTOR){
                        constructors.add(v);
                    }
                }
            }
            Object emptyConstructorVar = ((Environment) declarationclass).getClassMember(t.rtype + "[]", t.rtype);
            if (constructors.size() > 0 && emptyConstructorVar == null){
                throw new RuntimeException("No constructor found for class: " + t.rtype);
            }

            // Neues Environment für die Klasse erstellen und alle Variablen und Funktionen speichern und altes speichern
            Environment preenvironmentDeclaration = environment;
            Environment assignclassObjectenv = new Environment(environment);
            environment = assignclassObjectenv;


            // Iterier über die hashmap values und gib alle keys und values aus
            for (Map.Entry<String, Variable> entry : ((Environment) declarationclass).values.entrySet()) {
                if (entry.getValue().value.getClass().getSimpleName().equals("VariableFunction")){
                    eval(((VariableFunction) entry.getValue().value).ast);
                } else {
                    environment.define(entry.getKey(), entry.getValue().value);
                }
            }


            if (constructors.size() > 0){
                environment = new Environment(environment);
                environment.prevenv = assignclassObjectenv;

                for (AST c : ((VariableFunction) constructors.get(0).value).ast.kinder) {
                    eval(c);
                }
            }



            environment = preenvironmentDeclaration;
            environment.define(t.value, assignclassObjectenv);

            // Konstruktoraufruf
        } else {
            VariableClazz assignclass = (VariableClazz) environment.get(t.kinder.get(0).value);
            Environment assignclassenv = assignclass.env;
            System.out.println(t.symbol.name);
            Variable assignclassVar = (Variable) environment.getClassMember(t.symbol.name, t.kinder.get(0).value);



            // Prüfen, ob es sich um den Copykonstruktor handelt - Es kann nur einen undefinierten Konstruktor geben - den default Copy Cnstruktor
            if (assignclassVar == null){
                System.out.println("Default Copy Constructor: " + t.kinder.get(0).value);
                Environment oldClazz = (Environment) environment.get(t.kinder.get(1).kinder.get(0).value);

                // Neues Environment für die Klasse erstellen und alle Variablen und Funktionen speichern und altes speichern
                Environment environmentBevorClassAssign = environment;
                Environment assignclassObjectenv = new Environment(environment);
                environment = assignclassObjectenv;

                // Iterier über die hashmap values aus der des bestehenden Objekt aus dem Parameter und speicher es als neues Klassenobjekt
                for (Map.Entry<String, Variable> entry : oldClazz.values.entrySet()) {
                    System.out.println("Copy Constructor Copy Param: " + entry.getKey() + " - " + entry.getValue().value);
                    // TODO - Die Variable heist nicht mehr EnvFunction sondern VariableFunction
                    if (entry.getValue().value.getClass().getSimpleName().equals("VariableFunction")){
                        eval(((VariableFunction) entry.getValue().value).ast);
                    } else {
                        environment.define(entry.getKey(), entry.getValue().value);
                    }
                }

                environment = environmentBevorClassAssign;
                environment.define(t.value, assignclassObjectenv);

                // Umgebung speichern
                //Object assignclassenv = (Environment) environment.get(t.rtype);
            } else {

                System.out.println("AssignClass: " + t.kinder.get(0).value + " - " + assignclassVar);
                VariableFunction constructorAssignClass = (VariableFunction) assignclassVar.value;
                AST assignClassParams = constructorAssignClass.ast.kinder.get(0);

                // Temporäre Umgebung für die Funktionsparameter erstellen
                // Environment wird erst später gesetzt, um rekursion und verschachtelte Funktionsaufrufe zu ermöglichen
                Environment assignClassEnvironmentConstructorEnv = new Environment(environment);
                // Anzahl der Funktionsparameter
                int assignClassCallParams = t.kinder.get(1).kinder.size();
                //if (assignClassCallParams > 0) {
                for (int i = 0; i < assignClassCallParams; i++) {
                    Object funcCallArg = eval(t.kinder.get(1).kinder.get(i));
                    // Parameter auswerten
                    // TODO - Je nach dem ob es sich um einer Refernz handelt oder nicht darf ich diese auswerten oder nicht
                    if (funcCallArg instanceof String) {
                        // Prüfen ob es sich um eine Referenz handelt
                        if (t.kinder.get(1).kinder.get(i).asttype == AST.Types.REF) {
                            // Greifen des Referenzobjekts und speichern also tatsächloche Referenz in der neuen Variable
                            Variable refVarFuncCall = environment.getVariable((String) funcCallArg);
                            assignClassEnvironmentConstructorEnv.defineReference(assignClassParams.kinder.get(i).value, refVarFuncCall);
                        } else {
                            // Auswerten von Variablen
                            funcCallArg = environment.get((String) funcCallArg);
                            assignClassEnvironmentConstructorEnv.define(assignClassParams.kinder.get(i).value, funcCallArg);
                        }
                    } else {
                        assignClassEnvironmentConstructorEnv.define(assignClassParams.kinder.get(i).value, funcCallArg);
                    }
                }
                //}

                Environment environmentBevorClassAssign = environment;
                Environment assignclassObjectenv = new Environment(environment);
                environment = assignclassObjectenv;




                // TODO - Hier Vererbung implementieren - Funktionen und Methoden der Basisklasse in diese mit übernehmen
                //  Prüfen, welche Objekte und FUnktionen wann und wie aus der Basisklasse übernommen werden, und welche
                //  überschrieben werden

                //
                // TODO - Basiskonstruktor des Parent aufrufen und so alles erstellen
                //

                if (assignclass.parentClazz != null){
                    VariableClazz parent = assignclass.parentClazz;
                    for (Map.Entry<String, Variable> entry : ((Environment) parent.env).values.entrySet()) {
                        if (entry.getValue().value.getClass().getSimpleName().equals("VariableFunction")){
                            // Prüft, ob es sich um eine abstrakte Funktion handelt und ob diese in der ableitenden Klasse implementiert wurde
                            if (((VariableFunction) entry.getValue().value).abstractFunction) {
                                if (environment.getClassMember(entry.getKey(), t.kinder.get(0).value) == null) {
                                    throw new RuntimeException("Abstract Function " + entry.getKey() + " not implemented in class " + t.value);
                                }
                            } else {
                                eval(((VariableFunction) entry.getValue().value).ast);
                            }
                        } else {
                            environment.define(entry.getKey(), entry.getValue().value);
                        }
                    }

                    // Suchen des Default Konstruktors der Basisklasse
                    Variable parentConstructor = (Variable) parent.env.getClassMember(parent.ast.value + "[]", parent.ast.value);

                    // Aufrufen des Basiskonstruktors der Basisklasse
                    for (AST c : ((VariableFunction) parentConstructor.value).ast.kinder) {
                        eval(c);
                    }
                }

                // Iterier über die hashmap values und gib alle keys und values aus
                for (Map.Entry<String, Variable> entry : ((Environment) assignclassenv).values.entrySet()) {
                    if (entry.getValue().value.getClass().getSimpleName().equals("VariableFunction")){
                        // TODO - Prüfen ob abstrakte Funktionen implementiert wurden - Auch in der Basisklasse
                            VariableFunction testAbsFunc =  (VariableFunction) entry.getValue().value;
                            if (testAbsFunc.abstractFunction){
                                throw new RuntimeException("Abstract Function " + entry.getKey() + " not implemented in class " + t.value);
                            }
                        eval(((VariableFunction) entry.getValue().value).ast);
                    } else {
                        environment.define(entry.getKey(), entry.getValue().value);
                    }
                }

                environment = assignClassEnvironmentConstructorEnv;
                environment.prevenv = assignclassObjectenv;

                for (AST c : constructorAssignClass.ast.kinder) {
                    eval(c);
                }

                environment = environmentBevorClassAssign;
                environment.define(t.value, assignclassObjectenv);
            }
        }
        System.out.println("ClassObject erstellt: " + t.value);
        return environment.get(t.value);
    }

}
