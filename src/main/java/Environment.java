import java.util.HashMap;

public class Environment {
    public Environment prevenv;
    public HashMap<String, Object> values = new HashMap<>();

    public Environment(Environment prevenv) {
        this.prevenv = prevenv;
    }


    public void assign(String key, Object value) {
        //System.out.println("Assign: " + key + " : " + value);
        if (values.containsKey(key)) {
            values.put(key, value);
        } else if (prevenv != null) {
            prevenv.assign(key, value);
        } else {
            System.out.println("Assign in Environment Assign funktioniert nicht");
            // TODO - Fehlerbehandlung
        }
    }

    public void define(String key, Object value) {
        values.put(key, value);
    }

    public Object get(String key) {
        Object value = values.get(key);
        if (value != null) {
            return value;
        } else if (prevenv != null) {
            return prevenv.get(key);
        } else {
            System.out.println("Variable " + key + " not defined");
            return null;
        }
    }

    public void print(){
        for (String key : values.keySet()) {
            System.out.println(key + " : " + values.get(key));
        }

    }

}
