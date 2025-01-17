// Struct zum Speichern von Klassen und Structs

public class SymbolStruct extends Symbol {
    // Konstruktor
    public SymbolStruct(String name, Scope scope) {
        super(name, new SymbolType("STRUCT"), new Scope(scope), false);
    }

    // Methode zum Auflösen von Symbolen
    public Symbol resolve(String key){
        return scope.resolve(key);
    }

    // Methode zum Auflösen von Klassenmitgliedern
    public Symbol resolveMember(String name) {
        return scope.resolve(name);
    }
}
