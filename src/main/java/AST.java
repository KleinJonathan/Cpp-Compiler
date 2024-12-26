import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AST {
    enum Types {
        START,
        BLOCK,
        SCOPE,

        UNDEFINED,

        // Charaktere
        ID,
        NUM,
        CHAR,
        BOOL,

        // Math
        MULL,
        MULEQ,
        ADD,
        ADDEQ,
        DIV,
        DIVEQ,
        SUB,
        SUBEQ,
        INC,
        DEC,

        COMPARE,

        // Assign
        INTASSIGN,
        ARRAYASSIGN,
        CHARASSIGN,
        BOOLASSIGN,
        ASSIGN,

        // Declaration
        DECLARATION,
        ARRAYDECLARATION,

        ASSIGNARRAYELEMENT,
        ARRAYSIZE,
        INCARRAY,
        DECARRAY,

        // Funktionen
        PARAMLIST,
        FUNCTION_CALL,
        FUNCTION_DEF,
        FUNCTION_DEC,
        RETURN,
        RETURNTYPE,

        REF,

        // Builtin
        PRINT_INT,
        PRINT_CHAR,
        PRINT_BOOL,

        // Klassen
        ASSIGNCLASS,
        CLASS,
        CLASSDEF,
        CONSTRUCTOR,
        PARENT,

        // IF ELSE WHILE
        BODY,
        IF_ELSE_BLOCK,
        IF_BLOCK,
        ELSE_BLOCK,
        WHILE_BLOCK,
        CONN,
        EQUAL,
        NOT_EQUAL,
        GREATER,
        LOWER,
        GREATER_EQUAL,
        LOWER_EQUAL,
    }

    Types asttype;
    String value;
    ArrayList<AST> kinder = new ArrayList<>();
    AST prev;
    Scope scope;
    Symbol symbol;
    Boolean isConst;

    String rtype;

    public AST(String v, Types t, AST p){
        this.value = v;
        this.asttype = t;
        this.prev = p;
    }

    public AST(String value, Types type, AST ast, Scope scope, Symbol symbol, Boolean isConst){
        this.value = value;
        this.asttype = type;
        this.prev = ast;
        this.scope = scope;
        this.symbol = symbol;
        this.isConst = isConst;
    }

    void addChild(AST a){
        this.kinder.add(a);
    }

    // Ausgeben des AST und aller Kinder auf der Konsole in einer Baumstruktur
    public void printAST(AST node, String prefix) {
        if (node.kinder.isEmpty()) {
            // Blattknoten
            System.out.println(prefix + "- [Leaf] " + node.asttype + " (" + node.value + ")" + " (" + node.rtype + ")");
        } else {
            // Innerer Knoten
            System.out.println(prefix + "- [Node] " + node.asttype + " (" + node.value + ")" + " (" + node.rtype + ")");
        }
        // Rekursiv alle Kinder ausgeben
        for (AST child : node.kinder) {
            printAST(child, prefix + "  ");
        }
    }

    // Überprüfen, ob ein Knoten ein Blattknoten ist
    public List<String> checkTerminal(AST node, List<String> terminalTypes) {
        if (node.kinder == null || node.kinder.isEmpty()) {
            terminalTypes.add(node.asttype.toString());
        } else {
            // Nicht-terminaler Knoten
            for (AST child : node.kinder) {
                checkTerminal(child, terminalTypes);
            }
        }
        return terminalTypes;
    }
}