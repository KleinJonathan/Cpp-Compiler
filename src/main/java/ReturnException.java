public class ReturnException extends RuntimeException {
    public Object value;

    public ReturnException(Object value) {
        super(null, null, false, false);
        this.value = value;
    }

    public Object getValue() {
        return value;
    }
}
