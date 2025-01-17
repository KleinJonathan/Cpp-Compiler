import java.util.List;
// Symbol für Funktionen im AST

public class SymbolFunction extends Symbol {
    // Speichern verschiedener Informationen über eine Funktion sowie einiger Verknüpfungen
    public String returnType;
    public AST functionAST;
    public Boolean decl;
    public List<String> paramTypes;
    public boolean override;
    public boolean abstractFunction = false;
    public String klassName;

    // Konstruktor
    public SymbolFunction(String name, String returnType, Scope scope, AST functionAST, Boolean decl, List<String> paramTypes, boolean override, boolean abstractFunction) {
        super((paramTypes != null ? name + paramTypes.toString() : name), new SymbolType("FUNCTION"), new Scope(scope), false);
        this.returnType = returnType;
        this.functionAST = functionAST;
        this.decl = decl;
        this.paramTypes = paramTypes;
        this.override = override;
        this.abstractFunction = abstractFunction;
    }

    // Ausgabe der Funktion
    @Override
    public String toString() {
        return "SymbolFunction{name='" + name + "', type='" + type.getName() + "', returnType='" + returnType + "', decl='" + decl + "' + paramTypes='" + paramTypes + "'}" + ", override: " + override + ", abstractFunction: " + abstractFunction + " FunctionAST: " + functionAST;
    }
}