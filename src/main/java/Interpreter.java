import javax.rmi.ssl.SslRMIClientSocketFactory;
import javax.swing.text.html.parser.Parser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.swing.text.html.parser.Parser;


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
                for (AST c : t.kinder) {
                    eval(c);
                }

                SymbolFunction main = (SymbolFunction) environment.get("main[]");
                if (main == null) {
                    throw new RuntimeException("No main function found");
                } else {
                    for (AST c : main.functionAST.kinder) {
                        eval(c);
                    }
                }

                break;
                // TODO - Nach ablaufen des gesammten AST die Main Funktion suchen und ausführen
            case RETURN:
                // TODO - Bei rückgabe von Referenzen muss ich die Variable und nicht den Wert zurückgeben - Was passiert bei Lokalen Variablen?
                Object returnvalue = eval(t.kinder.get(0));
                environment.print();
                if (returnvalue instanceof String){
                    returnvalue = environment.get((String) returnvalue);
                }
                throw new ReturnException(returnvalue);
            case ASSIGNNEW:
                // TODO - Was passiert bei einer reinen dekklaration? Welcher Wert wird zugewiesen?
                String assignnewid = t.value;
                Object assignnewvalue = eval(t.kinder.get(0));
                environment.define(assignnewid, assignnewvalue);
                break;
            case ASSIGNOLD:
                Object assignoldvalue = eval(t.kinder.get(0));
                environment.assign(t.value, assignoldvalue);
                break;
            case SCOPE:
                environment = new Environment(environment);
                for (AST c : t.kinder) {
                    eval(c);
                }
                environment = environment.prevenv;
                break;
            case DECLARATION:
                String declarationid = t.value;
                if (t.rtype.equals("int")){
                    environment.define(declarationid, 0);
                } else if (t.rtype.equals("char")){
                    environment.define(declarationid, 'a');
                } else if (t.rtype.equals("bool")){
                    environment.define(declarationid, false);
                } else {
                    // TODO - Fehlerbehandlung und Klassenerstellung
                    System.out.println("Else in declaration: " + declarationid + " - " + t.rtype);
                    environment.define(declarationid, 10);
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
                        System.out.println("Else in arraydeclaration");
                    }
                }
                environment.define(arraydeclarationid, arraydeclaration);
                break;
            case REF:
                return null;
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
                int arrayassignsize = (Integer) eval(t.kinder.get(0));
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
                            // TODO - Fehlerbehandlung und Klassenerstellung
                            System.out.println("Else in arrayassign");
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
                    // TODO - Fehlerbehandlung
                    throw new RuntimeException("Array: " + t.value + " is not an array");
                }




            // Funktionen
            case FUNCTION_DEC:
                /*
                TODO - Funktionsdeclarationen müssen nicht in dem Environment gespeichert werden - Definitionen schon
                 Oder doch? Was passiert bei Aufrufen, welche ich vor der Definition gemacht habe?
                 Diese sind dann noch nicht im Environment gespeichert
                */
                break;
            case FUNCTION_DEF:
                // Speichern der Funktion als SymbolFunction, da dort bereits der AST gespeichert ist
                SymbolFunction funcSymbolFuncDef = (SymbolFunction) t.symbol;
                // Aktuelle Umgebung in der Funktionsdefinition speichern
                funcSymbolFuncDef.env = environment;
                // Funktionen in das Environment speichern
                environment.define(t.symbol.name, funcSymbolFuncDef);
                break;
            case FUNCTION_CALL:
                SymbolFunction funcFuncCall = (SymbolFunction) environment.get(t.symbol.name);
                if (funcFuncCall == null){
                    throw new RuntimeException("Function " + t.symbol.name + " not defined");
                }
                // Umgebung speichern
                Environment preenvironment = environment;
                try{
                    // Umgebung auf die Funktion umstellen
                    environment = funcFuncCall.env;
                    // Temporäre Umgebung für die Funktionsparameter erstellen
                    environment = new Environment(environment);
                    // Funktionsparameter in die Umgebung speichern
                    int funcCallParams = funcFuncCall.functionAST.kinder.get(1).kinder.size();
                    Object funcCallArgs[] = new Object[funcCallParams];
                    if (funcCallParams > 0){
                        for (int i = 0; i < funcCallParams ; i++){
                            Object funcCallArg = eval(t.kinder.get(0).kinder.get(i));
                            System.out.println("Function call arg: " + funcCallArg + " - Type: " + funcCallArg.getClass().getSimpleName());
                            // Parameter auswerten
                            // TODO - Je nach dem ob es sich um einer Refernz handelt oder nicht darf ich diese auswerten oder nicht
                            if (funcCallArg instanceof String){
                                // Auswerten von Variablen
                                funcCallArg = preenvironment.get((String) funcCallArg);
                                funcCallArgs[i] = funcCallArg;
                            } else {
                                funcCallArgs[i] = funcCallArg;
                            }
                            // Funktionsparameter in die Umgebung speichern
                            environment.define(funcFuncCall.functionAST.kinder.get(1).kinder.get(i).value, funcCallArg);
                        }
                    }
                    // Temporäre Umgebung für den Funktionskörper erstellen
                    environment = new Environment(environment);
                    // Durchlaufen und ausführen der Funktion
                    for (AST c : funcFuncCall.functionAST.kinder) {
                        eval(c);
                    }
                } catch (ReturnException e){
                    // Umgebung zurücksetzen und rückgabe aus der Funktion
                    environment = preenvironment;
                    return(e.getValue());
                }
                break;
            case PARAMLIST:
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



            // LOGISCHE OPERATIONEN TODO - Typen prüfen und Fehlerbehandlung
            case AST.Types.EQUAL:
                if ((Object) eval(t.kinder.get(0)) == (Object) eval(t.kinder.get(1))){
                    return true;
                } else {
                    return false;
                }
            case AST.Types.NOT_EQUAL:
                if ((Object) eval(t.kinder.get(0)) != (Object) eval(t.kinder.get(1))){
                    return true;
                } else {
                    return false;
                }
            case AST.Types.GREATER:
                if ((Integer) eval(t.kinder.get(0)) > (Integer) eval(t.kinder.get(1))){
                    return true;
                } else {
                    return false;
                }
            case AST.Types.LOWER:
                if ((Integer) eval(t.kinder.get(0)) < (Integer) eval(t.kinder.get(1))){
                    return true;
                } else {
                    return false;
                }
            case AST.Types.GREATER_EQUAL:
                if ((Integer) eval(t.kinder.get(0)) >= (Integer) eval(t.kinder.get(1))){
                    return true;
                } else {
                    return false;
                }
            case AST.Types.LOWER_EQUAL:
                if ((Integer) eval(t.kinder.get(0)) <= (Integer) eval(t.kinder.get(1))){
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
                addeqvalue = environment.get((String) addeqvalue);
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
                subeqvalue = environment.get((String) subeqvalue);
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
                muleqvalue = environment.get((String) muleqvalue);
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
                diveqvalue = environment.get((String) diveqvalue);
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
                // Umwandlung von Objekten in Integer und auswerten fun Funktionen und Variablen
                if (AST.Types.ID == t.kinder.get(0).asttype) {
                    System.out.println(mathhelper(environment.get(t.kinder.get(0).value)));
                } else {
                    System.out.println(mathhelper(eval(t.kinder.get(0))));
                }
                break;
            case AST.Types.PRINT_CHAR:
                for (AST c : t.kinder) {
                    System.out.println(eval(c));
                }
                break;
            case AST.Types.PRINT_BOOL:
                for (AST c : t.kinder) {
                    System.out.println(eval(c));
                }
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
        if (ob == null) return 0;
        switch (ob.getClass().getSimpleName()){
            case "Integer": ob = (Integer) ob; break;
            case "String":
                Object obvar = environment.get((String) ob);
                switch (obvar.getClass().getSimpleName()){
                    case "Integer": ob = (Integer) obvar; break;
                    case "Character": ob = (int) (Character) obvar; break;
                    case "Boolean": ob = (Boolean) obvar ? 1 : 0; break;
                }
                break;
            case "Character": ob = (int) (Character) ob; break;
            case "Boolean": ob = (Boolean) ob ? 1 : 0; break;
            default: System.out.println("Default case in Mathhelper"); break;
        }
        return (Integer) ob;
    }

}
