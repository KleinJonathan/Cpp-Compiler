// Imports
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

// Scope Klasse zum Verwalten von Symbolen
public class Scope {
    // LinkedHashMap um die Reihenfolge zu behalten und um Funktionsparameter vergleichen zu können
    public Map<String, Symbol> scope = new LinkedHashMap<>();
    public Scope prev;

    public List<Scope> children = new ArrayList<>();

    public Scope(Scope p){
        this.prev = p;
        if (p != null) {
            p.children.add(this);
        }
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




    public void printAll() {
        printIndented(0);
    }


    // Rekursive Hilfsmethode zur eingerückten Ausgabe
    private void printIndented(int indentLevel) {
        // Ausgabe des aktuellen Scopes mit entsprechender Einrückung
        String indent = "    ".repeat(indentLevel); // 4 Leerzeichen pro Ebene
        System.out.println(indent + "Scope-Level " + indentLevel + " : {");
        for (Map.Entry<String, Symbol> entry : scope.entrySet()) {
            System.out.println(indent + "    " + entry.getKey() + " : " + entry.getValue().type.getName() + " - " + entry.getValue().getClass().getSimpleName());
        }
        System.out.println(indent + "}");

        // Rekursiver Aufruf für alle Kind-Scopes
        for (Scope child : children) {
            child.printIndented(indentLevel + 1);
        }
    }

    // Optional: Überschreiben von toString(), falls benötigt
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        printIndentedToBuilder(sb, 0);
        return sb.toString();
    }

    private void printIndentedToBuilder(StringBuilder sb, int indentLevel) {
        String indent = "    ".repeat(indentLevel);
        sb.append(indent).append("Scope-Level ").append(indentLevel).append(" : {\n");
        for (Map.Entry<String, Symbol> entry : scope.entrySet()) {
            sb.append(indent).append("    ").append(entry.getKey()).append(" : ")
                    .append(entry.getValue().type.getName()).append(" - ")
                    .append(entry.getValue().getClass().getSimpleName()).append("\n");
        }
        sb.append(indent).append("}\n");

        for (Scope child : children) {
            child.printIndentedToBuilder(sb, indentLevel + 1);
        }
    }

}
