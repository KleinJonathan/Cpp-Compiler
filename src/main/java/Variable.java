// Klasse zum speichern von Werten in dem Environment.
// Mit dieser Wrapper-Klasse können auch direkte Referenzen auf Objekte gespeichert werden.

public class Variable {
    public Object value;

    // Konstruktor
    public Variable(Object value) {
        this.value = value;
    }

    // Ausgabe des Wertes
    @Override
    public String toString() {
        if (value == null) {
            return "null";
        }
        return value.toString();
    }
}
