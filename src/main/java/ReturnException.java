// Return Exception für die Rückgabe von Werten aus Funktionen

public class ReturnException extends RuntimeException {
    public Object value;

    // Konstruktor
    public ReturnException(Object value) {
        super(null, null, false, false);
        this.value = value;
    }

    // Gibt den Wert zurück
    public Object getValue() {
        return value;
    }
}
