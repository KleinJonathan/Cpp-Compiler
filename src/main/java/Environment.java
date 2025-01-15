import java.util.HashMap;

public class Environment {
    public Environment prevenv;
    public HashMap<String, Variable> values = new HashMap<>();

    public Environment(Environment prevenv) {
        this.prevenv = prevenv;
    }


    public void assign(String key, Object value) {
        //System.out.println("Assign: " + key + " : " + value);
        if (values.containsKey(key)) {
            Variable var = values.get(key);
            var.value = value;
        } else if (prevenv != null) {
            prevenv.assign(key, value);
        } else {
            System.out.println("Assign in Environment Assign funktioniert nicht");
            // TODO - Fehlerbehandlung
        }
    }

    public void define(String key, Object value) {
        values.put(key, new Variable(value));
    }

    public void defineReference(String key, Variable reference) {
        values.put(key, reference);
    }

    public Object get(String key) {
        Variable value = values.get(key);
        if (value != null) {
            return (Object) value.value;
        } else if (prevenv != null) {
            return prevenv.get(key);
        } else {
            System.out.println("Variable " + key + " not defined - Error in get");
            return null;
        }
    }

    public Variable getVariable(String key) {
        Variable value = values.get(key);
        if (value != null) {
            return value;
        } else if (prevenv != null) {
            return prevenv.getVariable(key);
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
