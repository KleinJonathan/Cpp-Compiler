public class SymbolRefVariable extends Symbol {
    public Symbol referencSymbol;

    // Referenzen binden sich auf Lebenszeit an ein Symbol, weswegen sie nicht mehr geändert werden können und hier gespeichert wird
    public SymbolRefVariable(String name, SymbolType refType, Symbol referencedSymbol, Boolean isConst) {
        super(name, refType, null, isConst);
        this.referencSymbol = referencedSymbol;
    }

    public SymbolRefVariable(String name, SymbolType refType, Boolean isConst) {
        super(name, refType, null, isConst);
    }

    @Override
    public String toString() {
        return "SymbolRefVariable{name='" + name + "', type='" + type.getName() + "', reference='" + referencSymbol.name + "'}";
    }
}
