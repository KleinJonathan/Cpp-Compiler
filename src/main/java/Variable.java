public class Variable {
    public Object value;


    public Variable(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        if (value == null) {
            return "null";
        }
        return value.toString();
    }

}
