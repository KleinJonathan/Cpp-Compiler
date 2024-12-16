import java.io.*;
import java.util.ArrayList;
import java.util.Stack;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class Main {
  public static void main(String... args) throws IOException {
    // Lesen der Datei
    File file = new File("./src/main/java/input.cpp");
    // Buffer zum lesen der Datei
    BufferedReader br = new BufferedReader(new FileReader(file));

    StringBuilder sb = new StringBuilder();
    String line;
    while ((line = br.readLine()) != null){
      sb.append(line).append("\n");
    }
    String input = sb.toString();

    //System.out.println(input);

    if (input == null) {
      System.out.println("File is empty");
    } else {
      CppLexer lexer = new CppLexer(CharStreams.fromString(input));
      CommonTokenStream tokens = new CommonTokenStream(lexer);
      CppParser parser = new CppParser(tokens);

      ParseTree tree = parser.start(); // Start-Regel

      ParseTreeWalker walker = new ParseTreeWalker();
      MyListener listen = new MyListener();
      walker.walk(listen, tree);

      System.out.println("");

      AST ast = listen.getAST();
      ast.printAST(ast, "");
      //ast.scope.print();
      //ast.visitChild(ast);

      Interpreter interpreter = new Interpreter();
      interpreter.interpret(ast);
    }
  }
}
