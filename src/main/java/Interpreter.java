import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Interpreter {
    // Speichern verschiedener Werte und Umgebungen
    AST root;
    Map<String, Symbol> scope = new HashMap<>();
    Environment environment = new Environment(null);

    // Starte das Interpretieren des AST
    public void interpret(AST root){
        this.root = root;
        eval(root);
    }

    // Auswerten des AST
    // Der gesammte AST wird rekurisv mit dieser Funktion durchlaufen abhängig von dem Typ des Knotens ausgewertet
    public Object eval(AST t){
        if (t == null) return null;

        // Auswerten des AST abhängig von dem Typ des Knotens
        switch (t.asttype) {
        // Startknoten
        case START:
            // Durchlaufen des gesammten AST
            for (AST c : t.kinder) {
                eval(c);
            }

            // Nach durchlaufen des gesammten AST Main Funktion finden und ausführen
            // Suchen der Main Funktion
            VariableFunction main = null;
            for (String key : environment.values.keySet()) {
                if (key.contains("main[")) {
                    main = (VariableFunction) environment.get(key);
                }
            }
            if (main == null) {
                throw new RuntimeException("Keine Mainfunktion gefunden");
            } else {
                // Ausführen der Main Funktion
                // Catchen der Rückgabe der Main Funktion
                try {
                    for (AST c : main.ast.kinder) {
                        eval(c);
                    }
                } catch (ReturnException e){
                    System.out.println("Main return: " + e.getValue());
                }
            }
            break;

        // Return Statement - Rückgabe des Wertes
        case RETURN:
            if (t.kinder.size() > 0){
                Object returnvalue = eval(t.kinder.get(0));
                if (returnvalue instanceof String){
                    returnvalue = environment.get((String) returnvalue);
                }
                throw new ReturnException(returnvalue);
            } else {
                return null;
            }

        // Zuweisungen einer neuen Variable
        case ASSIGNNEW:
            // Prüfen, ob es sich um eine Klasseninstanz handelt
            if (t.kinder.get(0).asttype == AST.Types.ASSIGNCLASS){
                // Erstellen eines Objekts
                createClassObject(t.kinder.get(0), false);
            } else {
                // Prüfen, ob es sich um eine Variable mit Basistyp handelt
                if (t.rtype.equals("int") || t.rtype.equals("char") || t.rtype.equals("bool")){
                    String assignnewid = t.value;
                    Object assignnewvalue = eval(t.kinder.get(0));
                    environment.define(assignnewid, assignnewvalue);
                // Hier ist der Fall, dass um eine Klasseninstanz handelt
                } else {
                    if (t.kinder.get(0).asttype == AST.Types.ID){
                        t.kinder.addFirst(new AST(t.rtype, AST.Types.CLASS, t));
                        createClassObject(t, false);
                    } else {
                        createClassObject(t, true);
                    }
                }

            }
            // Rückgabe des Wertes der Expression
            return environment.get(t.value);

        // Zuweisungen einer bereits existierenden Variable
        case ASSIGNOLD:
            // Prüfen, ob es sich um eine "Normale" Variable handelt
            if (t.kinder.get(0).asttype == AST.Types.ID){
                Object assignoldvalue = eval(t.kinder.get(0));
                if (assignoldvalue instanceof String){
                    assignoldvalue = environment.get((String) assignoldvalue);
                }
                environment.assign(t.value, assignoldvalue);
            // Prüfen, ob es sich um eine Klasseninstanz handelt
            } else  if (t.kinder.get(0).asttype == AST.Types.CLASS){
                Environment clazzAssignOld = (Environment) (environment.getVariable(t.kinder.get(0).value)).value;
                clazzAssignOld.assign(t.value, eval(t.kinder.get(1)));
            } else {
                Object assignoldvalue = eval(t.kinder.get(0));
                environment.assign(t.value, assignoldvalue);
            }
            // Rückgabe des Wertes der Expression
            return environment.get(t.value);

        // Öffnen eines neuen Scopes und damit einer neuen Umgebung
        case SCOPE:
            // Neue Umgebung erstellen
            environment = new Environment(environment);
            // Evaluation der Kinder
            for (AST c : t.kinder) {
                eval(c);
            }
            // Umgebung zurücksetzen
            environment = environment.prevenv;
            break;

        // Deklaration einer neuen Variable
        case DECLARATION:
            String declarationid = t.value;
            // Prüfen, ob Variable schon existiert
            if (environment.getVariable(t.value) == null){
                // Die Basistypen bekommen zufällige Werte
                // Verhaltensweise in C++ soll imitiert werden, wo in dem Speicher zufällige Werte stehen
                // Interpretieren der Werte in den jeweiligen Typ
                if (t.rtype.equals("int")){
                    environment.define(t.value, (int) (Math.random()*1000000));
                } else if (t.rtype.equals("char")){
                    environment.define(t.value, (char) (Math.random()*26 + 'a'));
                } else if (t.rtype.equals("bool")){
                    environment.define(t.value, (Math.random() > 0.5));
                } else {
                    // Declaration einer Klasse = Erstellung eines Klassenobjekts
                    createClassObject(t, true);
                }
            }
            // Rückgabe des Wertes der Expression
            return environment.get(t.value);

        // Declaration eines neuen Arrays
        case ARRAYDECLARATION:
            // Ermitteln der Arraygröße und erstellen des Arrays
            int arraydeclerationsize = (Integer) eval(t.kinder.get(0));
            Object[] arraydeclaration = new Object[arraydeclerationsize];
            // Durchlaufen des Arrays und Zuweisung von Werten
            for (int i = 0; i < arraydeclerationsize; i++){
                if (t.rtype.equals("int")){
                    arraydeclaration[i] = (int) (Math.random() * 1000000);
                } else if (t.rtype.equals("char")){
                    arraydeclaration[i] = (char) (Math.random() * 26 + 'a');
                } else if (t.rtype.equals("bool")){
                    arraydeclaration[i] = Math.random() > 0.5;
                } else {
                    // TODO - Fehlerbehandlung und Klassenerstellung
                    // Arrays aus Objekten werden nicht unterstützt
                    //System.out.println("Die erstellung und der Zugriff von Klassenobjekten in Arrays wird nicht unterstützt");
                    //arraydeclaration[i] = createClassObject(t, true);
                }
            }
            // Array in der Umgebung speichern und Rückgabe des Wertes
            environment.define(t.value, t.value);
            return environment.get(t.value);

        // Referenz auf eine Variable
        case REF:
            // Das Prinzip der Referenz ist, dass im Hintergrund eine Tatsächliche Referenz auf die Variable gespeichert wird
            // Darüf dient die "Wrapperklasse" Variable, welche den Wert als Objekt speichert

            // Ermitteln, ob es sich um eine Referenz auf eine Variable oder auf eine Klasseninstanz handelt
            if (t.kinder.size() == 1){
                // Prüfen, ob es sich um eine Klasseninstanz handelt
                if (environment.get(t.symbol.type.name) instanceof VariableClazz) {
                    // Ermitteln der Klasse
                    VariableClazz refPolyClazz = (VariableClazz) environment.get(t.symbol.type.name);
                    // Zu referenzierende Variable
                    Variable refVar = environment.getVariable(t.kinder.get(0).value);
                    // Objekt
                    Environment refVarEnv = (Environment) refVar.value;

                    // Speichern des aktuellen Environments
                    Environment refPrePolyEnv = environment;

                    // Neues Environment für das Objekt erstellen
                    Environment newRefEnv = new Environment(environment);
                    environment = newRefEnv;

                    // Durchlaufen der Klassenvariablen und speichern in der neuen Umgebung
                    for (Map.Entry<String, Variable> entry : refVarEnv.values.entrySet()) {
                        environment.defineReference(entry.getKey(), entry.getValue());
                    }

                    // Durchlaufen der Klassenfunktionen und speichern in der neuen Umgebung
                    // Die Funktionen aus der Oberklasse überschreiben die Funktionen aus der Unterklasse
                    for (AST c : refPolyClazz.ast.kinder) {
                        if (c.asttype == AST.Types.FUNCTION_DEF) {
                            SymbolFunction funcSymbolFuncDef = (SymbolFunction) c.symbol;
                            // Prüfen, ob es sich um eine abstrakte Funktion handelt - Diese wird dann in der Unterklasse überschrieben
                            if (!funcSymbolFuncDef.abstractFunction){
                                eval(c);
                            }
                        }
                    }

                    // Umgebung zurücksetzen und Referenz speichern und zurückgeben
                    environment = refPrePolyEnv;
                    environment.defineReference(t.value, new Variable(newRefEnv));
                    return newRefEnv;
                } else {
                    // Referenz speichern und zurückgeben
                    Variable refVar =  environment.getVariable(t.kinder.get(0).value);
                    environment.defineReference(t.value, refVar);
                    return refVar;
                }
            } else {
                // Referenz speichern und zurückgeben
                Variable refVar = environment.getClassMemberVariable(t.kinder.get(1).value, t.kinder.get(0).value);
                environment.defineReference(t.value, refVar);
                return refVar;
            }

        // Zuweisen eines Arrayelements
        case ASSIGNARRAYELEMENT:

            Object assignarrayelementindex = eval(t.kinder.get(0));
            Object assignarrayelementarray = environment.get(t.value);
            // Prüfen, ob es sich um ein Array handelt
            if (assignarrayelementarray instanceof Object[]){
                if (t.kinder.get(0).asttype == AST.Types.ID){
                    assignarrayelementindex = environment.get(t.kinder.get(0).value);
                }
                // Prüfen, ob der Index im Array liegt
                if ((Integer) assignarrayelementindex >= ((Object[]) assignarrayelementarray).length){
                    throw new RuntimeException("Array index out of bounds");
                }
                // Wert zuweisen
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

        // Rpckgabe der Arraygröße
        case ARRAYSIZE:
            return (Integer) eval(t.kinder.get(0));

        // Zuweisung eines neuen Arrays
        case ARRAYASSIGN:
            // Ermitteln der Arraygröße und erstellen eines Arrays
            int arrayassignsize = (Integer) eval(t.kinder.get(0)) == null ? 1 : (Integer) eval(t.kinder.get(0));
            Object[] arrayassign = new Object[arrayassignsize];
            // Durchlaufen des Arrays und der Kinder und Zuweisung der Werte, falls vorhanden ansonsten Zufallswerte
            // Bsp: int[10] a = {1,2,3}; => Fehlende Werte werden mit Zufallswerten gefüllt
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
                        // Arrays aus Objekten werden nicht unterstützt
                        //System.out.println("Die erstellung und der Zugriff von Klassenobjekten in Arrays wird nicht unterstützt");
                        //arrayassign[i] = createClassObject(t, false);
                    }
                }
            }
            // Array in der Umgebung speicher und zurückgeben
            environment.define(t.value, arrayassign);
            return environment.get(t.value);

        // Rückgabe eines Arrayelements
        case ARRAYELEMENT:
            // Ermittlen des Indexes und des Arrays
            Object arrayelementindex = eval(t.kinder.get(0));
            Object arrayelementarray = environment.get(t.value);
            if (arrayelementarray instanceof Object[]){
                if (t.kinder.get(0).asttype == AST.Types.ID){
                    arrayelementindex = environment.get(t.kinder.get(0).value);
                }
                // Prüfen, ob der Index im Array liegt
                if ((Integer) arrayelementindex >= ((Object[]) arrayelementarray).length){
                    throw new RuntimeException("Array index out of bounds");
                }
                // Rückgabe des Wertes
                return ((Object[]) environment.get(t.value))[(Integer) arrayelementindex];
            } else {
                throw new RuntimeException("Array: " + t.value + " is not an array");
            }


        //
        // Klassen
        //
        // Erstellen eines Klassenobjekts
        case ASSIGNCLASS:
            if (t.kinder.get(0).asttype == AST.Types.CLASS && t.kinder.size() > 1){
                createClassObject(t, false);
            }
            break;

        // Klasseninstanz / Regel wird nicht benötigt
        case CLASS:
            break;

        // Klassendefinition
        case CLASSDEF:
            // Umgebung speichern und neue Umgebung erstellen
            Environment preenvironmentclassdef = environment;
            environment = new Environment(environment);
            // Evaluation des ASTs und erstellen der "Klassensignatur"
            for (AST c : t.kinder) {
                eval(c);
            }

            // Umgebung zurücksetzen
            Environment classdefEnvironment = environment;
            environment = preenvironmentclassdef;

            // Ermitteln der Eltern und speichern und falls vorhanden speichern der Elternklasse
            VariableClazz parent = null;
            if (t.kinder.get(0).asttype == AST.Types.PARENT){
                parent = (VariableClazz) ((Variable) environment.getVariable(t.kinder.get(0).value)).value;
            }
            VariableClazz classdefVar = new VariableClazz(t, classdefEnvironment, parent);

            // Speichern der Klasse in der Umgebung
            environment.define(t.value, classdefVar);
            break;

        // Konstruktor
        case CONSTRUCTOR:
            // Prüfen, ob es sich um eine abstrakte Funktion handelt
            // Der Kopykonstruktor wird im AST nur Deklariert um diesen zu unterstützen
            if (!((SymbolFunction) t.symbol).decl){
                // Speichern der Funktion als SymbolFunction, da dort bereits der AST gespeichert ist
                SymbolFunction funcSymbolConstructor = (SymbolFunction) t.symbol;
                VariableFunction varFunctionDef = new VariableFunction(funcSymbolConstructor.functionAST, environment);
                // Aktuelle Umgebung in der Funktionsdefinition speichern
                // Funktionen in das Environment speichern
                environment.define(t.symbol.name, varFunctionDef);
            }
            break;

        // Destruktor - Werden nicht unterstützt und ausgeführt
        case DESTRUCTOR:
            // TODO - Destruktoren erstellen und speichern
            System.out.println("Destructor: " + t.value);
            break;
        // Elternklasse
        case PARENT:
            break;


        //
        // Funktionen
        //
        // Funktionsdeklaration
        case FUNCTION_DEC:
            // Speichert ausschließlich abstrakte Funktionsdeclarationen
            // Die restlichen Funktionsdeclarationen werden nicht benötigt, da sie nicht ausgeführt werden können
            // Speichern des AST und der Umgebung
            SymbolFunction funcSymbolFuncDec = (SymbolFunction) t.symbol;
            if (funcSymbolFuncDec.abstractFunction){
                VariableFunction abstractFunc = new VariableFunction(funcSymbolFuncDec.functionAST, environment);
                abstractFunc.abstractFunction = true;
                // Aktuelle Umgebung in der Funktionsdefinition speichern
                // Funktionen in das Environment speichern
                environment.define(t.symbol.name, abstractFunc);
            }
            break;
        // Funktionsdefinition
        case FUNCTION_DEF:
            // Speichern von Funktionsdefinitionen in dem aktuellen Environment zusammen mit dem AST,
            // welcher die Funktion, die Parameter und die tatsächliche Funktion der Funktion speichert
            SymbolFunction funcSymbolFuncDef = (SymbolFunction) t.symbol;
            VariableFunction varConstructorDef = new VariableFunction(funcSymbolFuncDef.functionAST, environment);
            // Aktuelle Umgebung in der Funktionsdefinition speichern
            // Funktionen in das Environment speichern
            environment.define(t.symbol.name, varConstructorDef);
            break;
        // Funktionsaufruf
        case FUNCTION_CALL:
            // Speichern des aktuellen Environments
            VariableFunction funcFuncCall = null;
            Environment preenvironment = environment;

            // Prüfen, ob es sich um eine Klassenfunktion handelt
            if (t.kinder.size() > 0 && t.kinder.getLast().asttype.equals(AST.Types.CLASS)){
                // Abrufen der Klassenfunktion
                funcFuncCall = (VariableFunction) ((Variable) environment.getClassMember(t.symbol.name, t.kinder.getLast().value)).value;
            } else {
                funcFuncCall = (VariableFunction) environment.get(t.symbol.name);
            }

            // Prüfen, ob die Funktion existiert
            if (funcFuncCall == null){
                throw new RuntimeException("Function " + t.symbol.name + " not defined");
            }

            // Funktionsaufruf im aktuellen Environment und try-catch Block für das Return Statement
            try{
                // Temporäre Umgebung für die Funktionsparameter erstellen
                // Aktuelles Environment wird erst später gesetzt, um rekursion und verschachtelte Funktionsaufrufe zu ermöglichen
                Environment funcEnvironment = new Environment(funcFuncCall.env);
                // Ermitteln der Anzahl der Funktionsparameter
                int funcCallParams = funcFuncCall.ast.kinder.get(1).kinder.size();
                // Sind Funktionsparameter vorhanden, werden diese in der temporären Umgebung evaluiert und gespeichert
                if (funcCallParams > 0){
                    for (int i = 0; i < funcCallParams ; i++){
                        if (t.kinder.get(0).kinder.get(i).asttype == AST.Types.CLASS){
                            continue;
                        }
                        Object funcCallArg = eval(t.kinder.get(0).kinder.get(i));
                        // Parameter auswerten
                        // Auswerten von Variablen
                        if (funcCallArg instanceof String){
                            // Prüfen ob es sich um eine Referenz handelt
                            if (funcFuncCall.ast.kinder.get(1).kinder.get(i).asttype == AST.Types.REF){
                                // Greifen des Referenzobjekts und speichern der tatsächlichen Referenz in der neuen Variable
                                Variable refVarFuncCall = preenvironment.getVariable((String) funcCallArg);
                                funcEnvironment.defineReference(funcFuncCall.ast.kinder.get(1).kinder.get(i).value, refVarFuncCall);
                            } else {
                                // Auswerten von Variablen
                                funcCallArg = preenvironment.get((String) funcCallArg);
                                funcEnvironment.define(funcFuncCall.ast.kinder.get(1).kinder.get(i).value, funcCallArg);
                            }
                        // Auswerten von Ausdrücken
                        } else {
                            funcEnvironment.define(funcFuncCall.ast.kinder.get(1).kinder.get(i).value, funcCallArg);
                        }
                    }
                }

                // Umgebung auf die Funktionsumgebung setzen
                environment = funcEnvironment;

                // Durchlaufen/Evaluieren und ausführen der Funktion
                for (AST c : funcFuncCall.ast.kinder.get(2).kinder) {
                    eval(c);
                }
            // Catchen des Rückgabewertes
            } catch (ReturnException e){
                // Umgebung zurücksetzen und rückgabe aus der Funktion
                environment = preenvironment;
                return(e.getValue());
            }
            // Umgebung zurücksetzen
            environment = preenvironment;
            return null;
        // Funktionsparameterliste evaluieren
        case PARAMLIST:
            // Durchlaufen der Funktionsparameter und speichern in der Umgebung
            for (AST c : t.kinder) {
                eval(c);
            }
            break;
        case RETURNTYPE:
            break;


        //
        // Kontrollstrukturen
        //
        // If-Else Block
        case AST.Types.IF_ELSE_BLOCK:
            // Evaluieren der Condition
            Object if_else_block_condition = eval(t.kinder.get(0));
            // Ausführen der des If oder Else Blocks abhängig von der Condition
            if (mathhelper(if_else_block_condition) != 0){
                eval(t.kinder.get(1));
            } else {
                eval(t.kinder.get(2));
            }
            break;
        // While Block
        case AST.Types.WHILE_BLOCK:
            // Evaluieren der Condition
            for (AST c : t.kinder) {
                Object while_block_condition = eval(t.kinder.get(0));
                // Ausführen des While Blocks solange die Condition erfüllt ist
                while (mathhelper(while_block_condition) != 0){
                    // Evaluieren des While Blocks
                    eval(t.kinder.get(1));
                    // Erneutes Evaluieren der Condition
                    while_block_condition = eval(t.kinder.get(0));
                }
            }
            break;
        // If Block
        case AST.Types.IF_BLOCK:
            // Evaluieren des Blocks
            return eval(t.kinder.get(0));
        // Else Block
        case AST.Types.ELSE_BLOCK:
            // Evaluieren des Blocks
            return eval(t.kinder.get(0));
        // Evaluation der Condition und Rückgabe des Wertes
        case AST.Types.CONN:
            if (t.kinder.size() == 1){
                return eval(t.kinder.get(0));
            }
            break;


        //
        // LOGISCHE OPERATIONEN
        //
        // =
        case AST.Types.EQUAL:
            // Evaluation der beiden Kinder und Rückegabe des Ergebnisses als Boolean
            if (mathhelper(eval(t.kinder.get(0))) == mathhelper(eval(t.kinder.get(1)))){
                return true;
            } else {
                return false;
            }
        // !=
        case AST.Types.NOT_EQUAL:
            // Evaluation der beiden Kinder und Rückegabe des Ergebnisses als Boolean
            if (mathhelper(eval(t.kinder.get(0))) != mathhelper(eval(t.kinder.get(1)))){
                return true;
            } else {
                return false;
            }
        // >
        case AST.Types.GREATER:
            // Evaluation der beiden Kinder und Rückegabe des Ergebnisses als Boolean
            if (mathhelper(eval(t.kinder.get(0))) > mathhelper(eval(t.kinder.get(1)))){
                return true;
            } else {
                return false;
            }
        // <
        case AST.Types.LOWER:
            // Evaluation der beiden Kinder und Rückegabe des Ergebnisses als Boolean
            if (mathhelper(eval(t.kinder.get(0))) < mathhelper(eval(t.kinder.get(1)))){
                return true;
            } else {
                return false;
            }
        // >=
        case AST.Types.GREATER_EQUAL:
            // Evaluation der beiden Kinder und Rückegabe des Ergebnisses als Boolean
            if (mathhelper(eval(t.kinder.get(0))) >= mathhelper(eval(t.kinder.get(1)))){
                return true;
            } else {
                return false;
            }
        // <=
        case AST.Types.LOWER_EQUAL:
            // Evaluation der beiden Kinder und Rückegabe des Ergebnisses als Boolean
            if (mathhelper(eval(t.kinder.get(0))) <= mathhelper(eval(t.kinder.get(1)))){
                return true;
            } else {
                return false;
            }

        // Evaluieren eines Blocks
        case AST.Types.BODY:
            for (AST c : t.kinder) {
                eval(c);
            }
            break;


        //
        // MATHEMATISCHE OPERATIONEN
        //
        //
        // +
        case AST.Types.ADD:
            // Evaluation der beiden Kinder und Rückegabe des Ergebnisses
            Object addleft = eval(t.kinder.get(0));
            Object addright = eval(t.kinder.get(1));
            return mathhelper(addleft) + mathhelper(addright);
        // +=
        case AST.Types.ADDEQ:
            // Evaluation der beiden Kinder und Rückegabe des Ergebnisses
            String addeqvar = (String) eval(t.kinder.get(0));
            Object addeqvalue = eval(t.kinder.get(1));
            if (addeqvalue instanceof String){
                addeqvalue = environment.get((String) addeqvalue);
            }
            Object resultqddey = mathhelper(environment.get(addeqvar)) + mathhelper(addeqvalue);
            environment.assign((String) addeqvar, resultqddey);
            return environment.get(t.kinder.get(0).value);
        // -
        case AST.Types.SUB:
            // Evaluation der beiden Kinder und Rückegabe des Ergebnisses
            Object subleft = eval(t.kinder.get(0));
            Object subright = eval(t.kinder.get(1));
            return mathhelper(subleft) - mathhelper(subright);
        // -=
        case AST.Types.SUBEQ:
            // Evaluation der beiden Kinder und Rückegabe des Ergebnisses
            String subeqvar = (String) eval(t.kinder.get(0));
            Object subeqvalue = eval(t.kinder.get(1));
            if (subeqvalue instanceof String){
                subeqvalue = environment.get((String) subeqvalue);
            }
            Object resultsubeqddey = mathhelper(environment.get(subeqvar)) - mathhelper(subeqvalue);
            environment.assign((String) subeqvar, resultsubeqddey);
            return environment.get(t.kinder.get(0).value);
        // *
        case AST.Types.MULL:
            // Evaluation der beiden Kinder und Rückegabe des Ergebnisses
            Object mulleft = eval(t.kinder.get(0));
            Object mulright = eval(t.kinder.get(1));
            return mathhelper(mulleft) * mathhelper(mulright);
        // *=
        case AST.Types.MULEQ:
            // Evaluation der beiden Kinder und Rückegabe des Ergebnisses
            String muleqvar = (String) eval(t.kinder.get(0));
            Object muleqvalue = eval(t.kinder.get(1));
            if (muleqvalue instanceof String){
                muleqvalue = environment.get((String) muleqvalue);
            }
            Object resultmuleqddey = mathhelper(environment.get(muleqvar)) * mathhelper(muleqvalue);
            environment.assign((String) muleqvar, resultmuleqddey);
            return environment.get(t.kinder.get(0).value);
        // /
        case AST.Types.DIV:
            // Evaluation der beiden Kinder und Rückegabe des Ergebnisses
            Object divleft = eval(t.kinder.get(0));
            Object divright = eval(t.kinder.get(1));
            // Prüfen, ob durch 0 geteilt wird
            if (mathhelper(divright) == 0){
                throw new RuntimeException("Division by zero");
            }
            return mathhelper(divleft) / mathhelper(divright);
        // /=
        case AST.Types.DIVEQ:
            // Evaluation der beiden Kinder und Rückegabe des Ergebnisses
            String diveqvar = (String) eval(t.kinder.get(0));
            Object diveqvalue = eval(t.kinder.get(1));
            if (diveqvalue instanceof String){
                diveqvalue = environment.get((String) diveqvalue);
            }
            // Prüfen, ob durch 0 geteilt wird
            if (mathhelper(diveqvalue) == 0){
                throw new RuntimeException("Division by zero");
            }
            Object resultdiveqddey = mathhelper(environment.get(diveqvar)) / mathhelper(diveqvalue);
            environment.assign((String) diveqvar, resultdiveqddey);
            return environment.get(t.kinder.get(0).value);
        // ++
        case AST.Types.INC:
            // Incrementieren von Variablen
            int resultinc = 0;
            // Das incrementieren von Chars und Bools verhält sich eventuell anders als in C++
            if (AST.Types.ID == t.kinder.get(0).asttype){
                Object incvar = environment.get(t.kinder.get(0).value);
                resultinc = (Integer) mathhelper(incvar) + 1;
                environment.assign(t.kinder.get(0).value, resultinc);
            }
            return resultinc;
        // --
        case AST.Types.DEC:
            // Dekrementieren von Variablen
            int resultdec = 0;
            // Das decrementieren von Chars und Bools verhält sich eventuell anders als in C++
            if (AST.Types.ID == t.kinder.get(0).asttype){
                Object decvar = environment.get(t.kinder.get(0).value);
                resultdec = (Integer) mathhelper(decvar) - 1;
                environment.assign(t.kinder.get(0).value, resultdec);
            }
            return resultdec;


        //
        // BUILT-IN Funktionen
        //
        // print_int Funktionen
        case AST.Types.PRINT_INT:
            // Evaluation der Funktion und Interpretation des Rückgabewertes als Integer
            System.out.println(builtinhelper(t));
            break;
        // print_char Funktionen
        case AST.Types.PRINT_CHAR:
            // Evaluation der Funktion und Interpretation des Rückgabewertes als Char
            System.out.println((char) builtinhelper(t));;
            break;
        // print_bool Funktionen
        case AST.Types.PRINT_BOOL:
            // Evaluation der Funktion und Interpretation des Rückgabewertes als Boolean
            System.out.println(builtinhelper(t) == 0 ? "false" : "true");
            break;


        //
        // "Primitive" Datentypen auswerden und zurückgeben
        //
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



    //
    // Hilfsfunktionen
    //
    // Hilfsfunktion zum Umrechnen von Objekten in Integer
    public int mathhelper(Object ob){
        // Prüfen auf null
        if (ob == null) return 0;
        // Casten der einzelnen Datentypen in Integer und auswerten von Klassenvariablen und normalen Variablen
        switch (ob.getClass().getSimpleName()){
            case "Integer": ob = ob; break;
            // Variable
            case "String":
                Object obvar = environment.get((String) ob);
                if (obvar == null){
                    //System.out.println("Variable " + ob + " not defined - Error in Mathhelper");
                    return 0;
                }
                // Umwandlung von Variableinhalten in Integer
                switch (obvar.getClass().getSimpleName()){
                    case "Integer": ob = obvar; break;
                    case "Character": ob = (int) (Character) obvar; break;
                    case "Boolean": ob = (Boolean) obvar ? 1 : 0; break;
                }
                break;
            case "Character": ob = (int) (Character) ob; break;
            case "Boolean": ob = (Boolean) ob ? 1 : 0; break;
            case "Variable": ob = ((Variable) ob).value; break;
            default: /*System.out.println("Default case in Mathhelper with Type: " + ob.getClass().getSimpleName());*/ break;
        }
        return (Integer) ob;
    }

    // Hilfsfunktion zum Auswerten von BUILT-IN Funktionen und Rückgabe als Integer mithilfe der mathhelper Funktion
    public int builtinhelper(AST a){
        // Umwandlung von Objekten in Integer und auswerten von Funktionen und Variablen
        if (AST.Types.ID == a.kinder.get(0).asttype) {
            return(mathhelper(environment.get(a.kinder.get(0).value)));
        // Auswerten von Klassenvariablen und Funktionen
        } else if (AST.Types.CLASS == a.kinder.get(0).asttype) {
            if (a.kinder.get(1).asttype == AST.Types.ID){
                // Zugriff auf einfache ID
                Object builtinVar = environment.getClassMember(a.kinder.get(1).value, a.kinder.get(0).value);
                if (builtinVar == null){
                    /*System.out.println("Variable " + a.kinder.get(1).value + " not defined in class " + a.kinder.get(0).value +
                            " - Error in builtinhelper");*/
                    return 0;
                } else {
                    return mathhelper((((Variable) environment.getClassMember((a.kinder.get(1).value).toString(), a.kinder.get(0).value)).value));
                }
            } else {
                return(mathhelper(eval(a.kinder.getLast())));
            }
        } else {
            return(mathhelper(eval(a.kinder.get(0))));
        }
    }
    // Hilfsfunktion zum Erstellen von Klassenobjekten anhand eines gegebenen ASTs, welcher die Klassendefinition enthält
    public Object createClassObject(AST t, boolean emptyConstructor){

        // Default Konstruktoraufruf, wenn kein Konstruktoraufruf oder Parameter angegeben sind
        if (emptyConstructor){
            // Prüfen, ob wenn Konstruktoren vorhanden sind, ob auch ein Konstruktor ohne Parameter vorhanden ist
            VariableClazz declarationclassVar = (VariableClazz) environment.get(t.rtype);
            Environment declarationclass = declarationclassVar.env;

            // Durchsuchen der Klasse nach allen Konstruktoren
            ArrayList <Variable> constructors = new ArrayList<>();
            for (Variable v : ((Environment) declarationclass).values.values()){
                if (v.value.getClass().getSimpleName().equals("VariableFunction") ){
                    VariableFunction constructor = (VariableFunction) v.value;
                    if (constructor.ast.asttype == AST.Types.CONSTRUCTOR){
                        constructors.add(v);
                    }
                }
            }

            // Prüfen, ob ein Konstruktor ohne Parameter vorhanden ist, wenn noch andere Konstruktoren vorhanden sind
            Object emptyConstructorVar = ((Environment) declarationclass).getClassMember(t.rtype + "[]", t.rtype);
            if (constructors.size() > 0 && emptyConstructorVar == null){
                throw new RuntimeException("No constructor found for class: " + t.rtype);
            }

            // Neues Environment für die Klasse erstellen und alle Variablen und Funktionen speichern und altes speichern
            Environment preenvironmentDeclaration = environment;
            Environment assignclassObjectenv = new Environment(environment);
            environment = assignclassObjectenv;

            // Erstellen des Klassenobjekts
            createClassObjectHelper(declarationclass, t.value, false);

            // Konstruktoraufruf
            if (constructors.size() > 0){
                environment = new Environment(environment);
                environment.prevenv = assignclassObjectenv;

                // Evaluation des Konstruktors
                for (AST c : ((VariableFunction) constructors.get(0).value).ast.kinder) {
                    eval(c);
                }
            }

            // Objekt in der Umgebung speichern und Umgebung zurücksetzen
            environment = preenvironmentDeclaration;
            environment.define(t.value, assignclassObjectenv);

        // Spezieller Konstruktoraufruf
        } else {
            // Abrufen der Klassendefinition
            VariableClazz assignclass = (VariableClazz) environment.get(t.kinder.get(0).value);
            Environment assignclassenv = assignclass.env;

            // Prüfen, ob es sich um einen normalen Konstruktoraufruf oder einen Kopykonstruktor handelt
            // Abfrage ist der Struktur des AST geschuldet
            Variable assignclassVar;
            if (t.kinder.get(0).asttype.equals(AST.Types.ID)){
                assignclassVar = (Variable) environment.get(t.kinder.get(0).value);
            } else {
                assignclassVar = (Variable) environment.getClassMember(t.symbol.name, t.kinder.get(0).value);
            }

            // Prüfen, ob es sich um den Kopykonstruktor handelt
            // Implemeniert ist nur der Default Kopykonstruktor - Andere Kopykonstruktoren werden nicht richtig erkannt
            if (assignclassVar == null){
                // Abrufen der alten zu kopierenden Klasse
                // Prüfen verschiedener Fälle von Kombinationen, wie die Klasse übergeben werden kann
                Environment oldClazz;
                if (t.kinder.get(1).kinder.size() == 0){
                    oldClazz = (Environment) environment.get(t.kinder.get(1).value);
                } else {
                    oldClazz = (Environment) environment.get(t.kinder.get(1).kinder.get(0).value);
                }

                // Neues Environment für die Klasse erstellen und alle Variablen und Funktionen speichern und altes speichern
                Environment environmentBevorClassAssign = environment;
                Environment assignclassObjectenv = new Environment(environment);
                environment = assignclassObjectenv;

                // Prüfen auf Polymporphie
                // Object Slicing - Muss hier nicht geprüft werden, da der Listener schon prüft, ob die Variable, auf welche zugewiesen wird, in der Klasse vorhanden ist
                if (!t.rtype.equals(t.kinder.get(1).rtype) && t.kinder.get(1).rtype != null){
                    VariableClazz leftClazz = (VariableClazz) environment.get(t.kinder.get(1).rtype);

                    // Erstellen des Klassenobjekts
                    createClassObjectHelper(oldClazz, t.value, true);

                    if (leftClazz != null) {
                        if (leftClazz.parentClazz.ast.value.equals(t.rtype)) {
                            // Speichern aller Methoden der Elternklasse in der neuen Klasse
                            for (Map.Entry<String, Variable> entry : ((Environment) leftClazz.parentClazz.env).values.entrySet()) {
                                if (entry.getValue().value.getClass().getSimpleName().equals("VariableFunction")) {
                                    // Prüfen, ob es sich um eine abstrakte Funktion handelt - Wenn ja wird diese nicht übernommen - Dynamische Bindung
                                    VariableFunction testAbsFunc = (VariableFunction) entry.getValue().value;
                                    if (!testAbsFunc.abstractFunction) {
                                        eval(((VariableFunction) entry.getValue().value).ast);
                                    }
                                }
                            }
                        }
                    }
                } else {
                    // Erstellen des Klassenobjekts
                    createClassObjectHelper(oldClazz, t.value, false);
                }

                // Objekt in der Umgebung speichern und Umgebung zurücksetzen
                environment = environmentBevorClassAssign;
                environment.define(t.value, assignclassObjectenv);

            // Es handelt sich um einen normalen Konstruktoraufruf
            } else {
                // Abrufen des Konstruktors
                VariableFunction constructorAssignClass = (VariableFunction) assignclassVar.value;
                AST assignClassParams = constructorAssignClass.ast.kinder.get(0);

                // Temporäre Umgebung für die Funktionsparameter des Konstruktors erstellen
                // Environment wird erst später gesetzt, um rekursion und verschachtelte Funktionsaufrufe zu ermöglichen
                // Parameter evaluieren
                Environment assignClassEnvironmentConstructorEnv = new Environment(environment);
                // Anzahl der Funktionsparameter
                int assignClassCallParams = t.kinder.get(1).kinder.size();
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

                // Neues Environment für die Klasse erstellen und alle Variablen und Funktionen speichern und altes zurücksetzen
                Environment environmentBevorClassAssign = environment;
                Environment assignclassObjectenv = new Environment(environment);
                environment = assignclassObjectenv;


                // Prüfen, ob dies eine abgeleitete Klasse ist
                int parentConstructors = 0;
                if (assignclass.parentClazz != null){
                    // Abrufen des Default Konstruktors der Basisklasse
                    VariableClazz parent = assignclass.parentClazz;
                    createClassObjectHelper(parent.env, t.value, true);

                    // Suchen des Default Konstruktors der Basisklasse
                    Variable parentConstructor = (Variable) parent.env.getClassMember(parent.ast.value + "[]", parent.ast.value);

                    // Da keine Initialisierungsliste implementiert ist muss ein Default Konstruktor vorhanden sein werden dann aufgerufen wird
                    // Prüfen, ob ein Default Konstruktor Definiert ist
                    if (parentConstructor == null){
                        // Prüfen ob andere Konstruktoren vorhanden sind
                        // Wenn nein dann wird der Defaultkonstruktor aufgerufen
                        if (parentConstructors == 0){
                            eval(parent.ast.kinder.get(0));
                        // Error werfen, wenn kein Defaultkonstruktor vorhanden ist
                        } else {
                            throw new RuntimeException("No default constructor found for parent class: " + parent.ast.value);
                        }
                    // Aufrufen des Basiskonstruktors der Basisklasse
                    } else {
                        for (AST c : ((VariableFunction) parentConstructor.value).ast.kinder) {
                            eval(c);
                        }
                    }
                }

                // Erstellen des Klassenobjekts - Erneute implemnierungen von Methoden werden von denen von der Elternklasse überschrieben
                createClassObjectHelper(assignclassenv, t.value, false);

                // Umgebung für den Konstruktor setzen
                environment = assignClassEnvironmentConstructorEnv;
                environment.prevenv = assignclassObjectenv;

                // Ausführen/Evaluieren des Konstruktors
                for (AST c : constructorAssignClass.ast.kinder) {
                    eval(c);
                }

                // Objekt in der Umgebung speichern und Umgebung zurücksetzen
                environment = environmentBevorClassAssign;
                environment.define(t.value, assignclassObjectenv);
            }
        }
        return environment.get(t.value);
    }

    // Hilfsfunktion zum Hinzufügen von Variablen und Funktionen in das neue Objektenvironment
    void createClassObjectHelper(Environment env, String klasse, boolean parent){
        // Iterieren über die hashmap der Klassendefinition und anschließendes Evaluieren der gespeicherten Werte in der neuen Umgebung
        for (Map.Entry<String, Variable> entry : ((Environment) env).values.entrySet()) {
            if (entry.getValue().value.getClass().getSimpleName().equals("VariableFunction")){
                VariableFunction testAbsFunc =  (VariableFunction) entry.getValue().value;
                if (testAbsFunc.abstractFunction && !parent){
                    throw new RuntimeException("Abstract Function " + entry.getKey() + " not implemented in class " + klasse);
                }
                // Speichern der Funktionsfedinitionen in dem Objekt
                // => Existiert die Funktion bereits in der Basisklasse wird diese überschrieben
                eval(((VariableFunction) entry.getValue().value).ast);
            } else {
                environment.define(entry.getKey(), entry.getValue().value);
            }
        }
    }
}
