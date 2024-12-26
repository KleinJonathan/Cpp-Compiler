import java.util.List;

public class SymbolFunction extends Symbol {
    public String returnType;
    public AST functionAST;
    public Boolean decl;
    public List<String> paramTypes;

    public SymbolFunction(String name, String returnType, Scope scope, AST functionAST, Boolean decl, List<String> paramTypes) {
        super((paramTypes != null ? name + paramTypes.toString() : name), new SymbolType("FUNCTION"), new Scope(scope), false);
        this.returnType = returnType;
        this.functionAST = functionAST;
        this.decl = decl;
        this.paramTypes = paramTypes;
    }

    @Override
    public String toString() {
        if (functionAST != null) {
            return "SymbolFunction{name='" + name + "', type='" + type.getName() + "', returnType='" + returnType + "', functionAST='" + functionAST + "', decl='" + decl + "' + paramTypes='" + paramTypes + "'}";
        } else {
            return "SymbolFunction{name='" + name + "', type='" + type.getName() + "', returnType='" + returnType + "', decl='" + decl + "' + paramTypes='" + paramTypes + "'}";
        }
    }
}