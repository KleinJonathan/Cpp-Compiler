import java.util.List;

public class SymbolFunction extends Symbol {
    public String returnType;
    public AST functionAST;

    public SymbolFunction(String name, String returnType, Scope scope, AST functionAST) {
        super(name, new SymbolType("FUNCTION"), new Scope(scope), false);
        this.returnType = returnType;
        this.functionAST = functionAST;
    }

    @Override
    public String toString() {
        return "SymbolFunction{name='" + name + "', type='" + type.getName() + "', returnType='" + returnType + "'}";
    }
}