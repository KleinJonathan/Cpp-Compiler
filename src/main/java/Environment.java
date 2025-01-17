import java.util.HashMap;

public class Environment {
    public Environment prevenv;
    public HashMap<String, Variable> values = new HashMap<>();

    public Environment(Environment prevenv) {
        this.prevenv = prevenv;
    }


    public void assign(String key, Object value) {
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
            //System.out.println("Variable " + key + " not defined - Error in get in Environment File");
            return null;
        }
    }

    public Object getClassMember(String key, String className) {
        Variable value = values.get(className);
        //System.out.println("ClassMember in getClassMember: " + key + " - " + className + " - " + value);
        if (value != null) {
            //System.out.println("ClassMember: " + key + " - " + className + " - " + value + " - " + value.value.getClass().getName());
            if (value.value instanceof VariableClazz) {
                VariableClazz var = (VariableClazz) value.value;
                return var.env.values.get(key);
            } else {
                Environment env = (Environment) value.value;
                return env.values.get(key);
            }
        } else if (prevenv != null) {
            return prevenv.getClassMember(key, className);
        } else {
            //System.out.println("Variable " + key + " not defined - Error in get in Environment File");
            return null;
        }
    }

    public Variable getClassMemberVariable(String key, String className) {
        Variable value = values.get(className);
        if (value != null) {
            System.out.println(value.getClass());
            Environment env = (Environment) value.value;
            return env.values.get(key);
        } else if (prevenv != null) {
            return prevenv.getClassMemberVariable(key, className);
        } else {
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
            return null;
        }
    }




    public void print(){
        for (String key : values.keySet()) {
            System.out.println(key + " : " + values.get(key).value);
        }
    }

}
