// Symbol für Builtins im AST

public class SymbolBuiltin extends Symbol{
    public SymbolBuiltin(String name) {
        super(name, new SymbolType("BUILTIN"), null, true);

    }
}
