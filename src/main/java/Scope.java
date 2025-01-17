// Imports
import java.util.LinkedHashMap;
import java.util.Map;

// Scope Klasse zum Verwalten von Symbolen
public class Scope {
    // LinkedHashMap um die Reihenfolge zu behalten und um Funktionsparameter vergleichen zu können
    public Map<String, Symbol> scope = new LinkedHashMap<>();
    public Scope prev;

    public Scope(Scope p){
        this.prev = p;
    }

    // Name - Type / Binden von Symbolen an den Scope
    public Symbol bind(Symbol s){
        scope.put(s.name, s);
        return s;
    }

    // Name - Type / Auflösen von Symbolen an den Scope
    public Symbol resolve(String key){
        if (key == null) return null;
        //System.out.println("Resolve: " + key);
        Symbol s = scope.get(key);
        if (s != null){
            return s;
        } else if (prev != null){
            return prev.resolve(key);
        } else {
            return null;
        }
    }

    // Name - Type / Auflösen von Symbolen in dem Lokalen Scope
    // Parameter zum Beispiel dürfen in jedem Scope neu definiert werden
    public Symbol resolveLocal(String key){
        if (key == null) return null;
        return scope.get(key);
    }

    // Ausgabe des Scopes
    public void print() {
        System.out.println("Scope-Level : {");
        for (Map.Entry<String, Symbol> entry : scope.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue().type.getName() + " - " + entry.getValue().getClass().getSimpleName());
        }
        System.out.println("}");
    }
}
