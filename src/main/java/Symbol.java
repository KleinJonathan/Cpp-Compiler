// Basisklasse für alle Symbole in der Symboltabelle

public class Symbol {
    // Speichern verschiedener Informationen über ein Symbol
    public String name;
    public SymbolType type;
    public Scope scope;
    public Boolean isConst;

    public String value;

    // Konstruktor
    public Symbol(String name, SymbolType type, Scope scope, Boolean isConst) {
        this.name = name;
        this.type = type;
        this.scope = scope;
        this.isConst = isConst;
    }

    // Ausgabe eines Symbols
    @Override
    public String toString() {
        return "Symbol{name='" + name + "', type='" + type.getName() + "', isConst='" + isConst + "'}";
    }
}

