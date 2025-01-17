// Variable für Klassen im Environment
// Dient nur zum speichern der Klassendefinition im Environment
// Objekte werden als Environment gespeichert

public class VariableClazz{
    // Speichern verschiedener Informationen über die Klasse und verknüpfen
    public VariableClazz parentClazz;
    public AST ast;
    public Environment env;

    // Konstruktor
    public VariableClazz(AST ast, Environment env, VariableClazz parentClazz) {
        this.ast = ast;
        this.parentClazz = parentClazz;
        this.env = env;
    }
}
