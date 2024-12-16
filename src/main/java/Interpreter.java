import javax.swing.text.html.parser.Parser;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Interpreter {
    AST root;
    Map<String, Symbol> scope = new HashMap<>();


    public void interpret(AST root){
        this.root = root;
        eval(root);
    }

    public Object eval(AST t){
        if (t.asttype == AST.Types.START){
            for (AST c : t.kinder) {
                eval(c);
            }
        } else if (t.asttype == AST.Types.NUM){
            return Integer.parseInt(t.value);
        } else if (t.asttype == AST.Types.CHAR){
            return t.value;
        } else if (t.asttype == AST.Types.BOOL){
            return Boolean.parseBoolean(t.value);
        }else if (t.asttype == AST.Types.ADD){
            return (Integer) eval(t.kinder.get(0)) + (Integer) eval(t.kinder.get(1));
        } else if (t.asttype == AST.Types.SUB){
            return (Integer) eval(t.kinder.get(0)) - (Integer) eval(t.kinder.get(1));
        } else if (t.asttype == AST.Types.MULL){
            return (Integer) eval(t.kinder.get(0)) * (Integer) eval(t.kinder.get(1));
        } else if (t.asttype == AST.Types.DIV){
            return (Integer) eval(t.kinder.get(0)) / (Integer) eval(t.kinder.get(1));
        } else if (t.asttype == AST.Types.EQUAL){
            return 0;
        } else if (t.asttype == AST.Types.NOT_EQUAL){
            return 0;
        } else if (t.asttype == AST.Types.GREATER){
            return 0;
        } else if (t.asttype == AST.Types.LOWER){
            return 0;
        } else if (t.asttype == AST.Types.GREATER_EQUAL){
            return 0;
        } else if (t.asttype == AST.Types.LOWER_EQUAL){
            return 0;
        } else if (t.asttype == AST.Types.PRINT_INT){
            System.out.println(eval(t.kinder.get(0)));
        } else if (t.asttype == AST.Types.PRINT_CHAR){
            System.out.println(t.value);
        } else if (t.asttype == AST.Types.PRINT_BOOL){
            System.out.println(t.value);
        }
        return null;
    }
}
