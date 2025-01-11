public class Clazz extends Struct {
    public Clazz parentClazz;

    public Clazz(String name, Clazz parent, Scope scope) {
        super(name, scope);
        this.parentClazz = parent;
    }

    public Symbol resolve(String key) {
        // Suche Symbol im aktuellen Scope
        Symbol s = scope.scope.get(key);
        if (s != null) return s;

        // Wenn Symbol nicht gefunden wurde, suche in der Basisklasse
        if (parentClazz != null) return parentClazz.resolve(key);
        if (scope != null) return scope.resolve(key);

        return null;
    }

    @Override
    public Symbol resolveMember(String name) {
        if (name == null) return null;
        Symbol s = scope.scope.get(name);
        if (s != null) return s;
        if (parentClazz != null) return parentClazz.resolveMember(name);
        return null;
    }

}
