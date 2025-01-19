import java.util.ArrayList;

// Klasse für den AST
public class AST {
    // Enum Types für den AST
    enum Types {
        START,
        SCOPE,

        UNDEFINED,

        // Literale
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

        // Assign
        ARRAYASSIGN,
        ASSIGNOLD,
        ASSIGNNEW,

        // Declaration
        DECLARATION,
        ARRAYDECLARATION,

        ASSIGNARRAYELEMENT,
        ARRAYSIZE,
        ARRAYELEMENT,

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
        DESTRUCTOR,
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

    // Klassenvariablen
    Types asttype;
    String value;
    ArrayList<AST> kinder = new ArrayList<>();
    AST prev;
    Scope scope;
    Symbol symbol;
    Boolean isConst;

    // Es wurde jedem Knoten möglichst ein Typ zugewiesen, um die spätere Typenüberprüfung zu erleichtern z.b. Mul...
    String rtype;

    // Konstruktor
    public AST(String v, Types t, AST p){
        this.value = v;
        this.asttype = t;
        this.prev = p;
    }

    // Konstruktor
    public AST(String value, Types type, AST ast, Scope scope, Symbol symbol, Boolean isConst){
        this.value = value;
        this.asttype = type;
        this.prev = ast;
        this.scope = scope;
        this.symbol = symbol;
        this.isConst = isConst;
    }

    // Hinzufügen eines Kinds zu einem Knoten
    void addChild(AST a){
        this.kinder.add(a);
    }

    // Ausgeben des AST und aller Kinder auf der Konsole in einer Baumstruktur basierend auf einem Basisknoten
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
}