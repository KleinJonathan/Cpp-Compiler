// Imports
import java.io.*;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class Main {
  public static void main(String... args) throws IOException {
    // Einlesen der Datei
    File file = new File("./src/main/java/cpp/input.cpp");
    // Buffer zum lesen der Datei
    BufferedReader br = new BufferedReader(new FileReader(file));
    StringBuilder sb = new StringBuilder();
    String line;
    while ((line = br.readLine()) != null){
      sb.append(line).append("\n");
    }
    String input = sb.toString();

    // Prüfen, ob die Datei leer ist
    if (input == null) {
      System.out.println("File is empty");
    } else {
      // Lexer und Parser
      CppLexer lexer = new CppLexer(CharStreams.fromString(input));
      CommonTokenStream tokens = new CommonTokenStream(lexer);
      CppParser parser = new CppParser(tokens);
      ParseTree tree = parser.start(); // Start-Regel

      // Selbst implementierter Listener zum traversieren des Baumes und bauen des AST
      ParseTreeWalker walker = new ParseTreeWalker();
      MyListener listen = new MyListener();
      walker.walk(listen, tree);

      System.out.println("");

      AST ast = listen.getAST();
      ast.printAST(ast, "");

      // Interpreter zum interpretieren des AST
      System.out.println("\n\nINTERPRETER: \n");
      Interpreter interpreter = new Interpreter();
      interpreter.interpret(ast);
    }
  }
}
