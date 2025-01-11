import java.util.List;

public class SymbolDestructor extends SymbolFunction{
    public SymbolDestructor(String name, String returnType, Scope scope, AST functionAST, Boolean decl, List<String> paramTypes, boolean override, boolean abstractFunction) {
        super(name, returnType, scope, functionAST, decl, paramTypes, override, abstractFunction);
    }
}
