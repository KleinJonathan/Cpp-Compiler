// Symbol für Variablen im AST

public class SymbolVariable extends Symbol {
    public SymbolVariable(String name, SymbolType type, Boolean isConst) {
        super(name, type, null, isConst);
    }
}
