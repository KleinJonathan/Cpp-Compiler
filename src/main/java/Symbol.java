import java.util.HashMap;
import java.util.List;

public class Symbol {
    public String name;
    public SymbolType type;
    public Scope scope;
    public Boolean isConst;

    public Symbol(String name, SymbolType type, Scope scope, Boolean isConst) {
        this.name = name;
        this.type = type;
        this.scope = scope;
        this.isConst = isConst;
    }

    @Override
    public String toString() {
        return "Symbol{name='" + name + "', type='" + type.getName() + "', isConst='" + isConst + "'}";
    }
}

