// Variable für Funktionen im Environment

public class VariableFunction{
    // Speichern verschiedener Informationen über die Funktion und verknüpfen zum AST/Aufruf und Environment
    public AST ast;
    public Environment env;
    public boolean abstractFunction = false;

    // Konstruktor
    public VariableFunction(AST ast, Environment env) {
        this.ast = ast;
        this.env = env;
    }
}
