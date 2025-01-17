// Klasse zum Speichern von Klassen im AST und zum Auflösen von Membern

public class SymbolClazz extends SymbolStruct {
    public SymbolClazz parentClazz;

    // Konstruktor
    public SymbolClazz(String name, SymbolClazz parent, Scope scope) {
        super(name, scope);
        this.parentClazz = parent;
    }

    // Auflösen von Symbolen bis hin zum BasisScope
    public Symbol resolve(String key) {
        // Suche Symbol im aktuellen Scope
        Symbol s = scope.scope.get(key);
        if (s != null) return s;

        // Wenn Symbol nicht gefunden wurde, suche in der Basisklasse
        if (parentClazz != null) return parentClazz.resolve(key);
        if (scope != null) return scope.resolve(key);

        return null;
    }

    // Auflösen von Membern in der Klasse oder der Basisklasse
    @Override
    public Symbol resolveMember(String name) {
        if (name == null) return null;
        Symbol s = scope.scope.get(name);
        if (s != null) return s;
        if (parentClazz != null) return parentClazz.resolveMember(name);
        return null;
    }
}
