public class Struct extends Symbol {
    public Struct(String name, Scope scope) {
        super(name, new SymbolType("STRUCT"), new Scope(scope), false);
    }

    public Symbol resolve(String key){
        return scope.resolve(key);
    }

    public Symbol resolveMember(String name) {
        return scope.resolve(name);
    }
}
