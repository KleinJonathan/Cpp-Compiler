import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Scope {
    // key - value
    // LinkedHashMap um die Reihenfolge zu behalten und um Funktionsparameter vergleichen zu können
    public Map<String, Symbol> scope = new LinkedHashMap<>();
    Scope prev;

    public Scope(Scope p){
        this.prev = p;
    }

    // Name - Type
    public Symbol bind(Symbol s){
        return scope.put(s.name, s);
    }

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

    public Symbol resolveLocal(String key){
        if (key == null) return null;
        return scope.get(key);
    }

    public void print(){
        print(0);
    }

    private void print(int level) {
        String indent = getIndent(level);
        System.out.println(indent + "Scope-Level " + level + ": {");
        for (Map.Entry<String, Symbol> entry : scope.entrySet()) {
            System.out.println(indent + "  " + entry.getKey() + " : " + entry.getValue().type.getName() + " - " + entry.getValue().getClass().getSimpleName());
        }
        System.out.println(indent + "}");

        /*
        if (prev != null) {
            prev.print(level + 1);
        }
         */
    }

    private String getIndent(int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append("  ");
        }
        return sb.toString();
    }
}
