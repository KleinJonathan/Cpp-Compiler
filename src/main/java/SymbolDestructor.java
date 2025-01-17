import java.util.List;
// Symbol für Destruktoren im AST - abgeleitet von SymbolFunction

public class SymbolDestructor extends SymbolFunction{
    public SymbolDestructor(String name, String returnType, Scope scope, AST functionAST, Boolean decl, List<String> paramTypes, boolean override, boolean abstractFunction) {
        super(name, returnType, scope, functionAST, decl, paramTypes, override, abstractFunction);
    }
}
