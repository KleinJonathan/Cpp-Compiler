import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Scope {
    // key - value
    public Map<String, Symbol> scope = new HashMap<>();
    Scope prev;

    public Scope(Scope p){
        this.prev = p;
    }

    /*
    // Name - Type
    public Symbol bind(String key, String value){
        if (scope.containsKey(key)){
            System.out.println("Variable: '" + key + "' already declared");
            return null;
        }
        scope.put(key, new Symbol(key, new SymbolType(value)));
        return scope.get(key);
    }
     */

    // Name - Type
    public Symbol bind(Symbol s){
        if ((s.type.getName().equals("BUILTIN") ||
            s.type.getName().equals("FUNCTION") ||
            s.type.getName().equals("STRUCT")
          ) && !scope.containsKey(s.name)
        ){
            scope.put(s.name, s);
            return scope.put(s.name, s);
        } else if (!scope.containsKey(s.name) && resolve(s.type.getName()).name != null){
            //System.out.println("Variable: '" + s.name + "' declared");
            return scope.put(s.name, s);
        } else {
            throw new RuntimeException("Variable: '" + s.name + "' already declared");
        }
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
            //throw new RuntimeException("Variable: '" + key + "' not declared");
        }
    }

    public void print(){
        print(0);
    }

    private void print(int level) {
        String indent = getIndent(level);
        System.out.println(indent + "Scope-Level " + level + ": {");
        for (Map.Entry<String, Symbol> entry : scope.entrySet()) {
            System.out.println(indent + "  " + entry.getKey() + " : " + entry.getValue().type.getName() + " - " + entry.getValue().getClass().getSimpleName() + " - " + entry.getValue());
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
