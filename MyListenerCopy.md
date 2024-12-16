---
created: 2024-12-13 11-30-17
referenzen: 
quellen:
---
```
import java.util.ArrayList;  
import java.util.List;  
  
public class MyListenerCOPY extends CppBaseListener {  
    public AST wurzel;  
    private AST acc;  
    private int id = 0;  
    private Scope scope;  
  
    public AST getAST() {  
        return wurzel;  
    }  
  
  
    //  
    // Start    //    public void enterStart(CppParser.StartContext ctx) {  
        //System.out.println("Start");  
        wurzel = new AST("start", AST.Types.START, null, null);  
        acc = wurzel;  
        scope = new Scope(null);  
    }  
    public void exitStart(CppParser.StartContext ctx) {  
        //System.out.println("End");  
        scope.checkFunc();  
    }  
  
  
    //  
    // Scope    //    public void enterOpenscope(CppParser.OpenscopeContext ctx) {  
        scope = new Scope(scope);  
    }  
    public void exitClosescope(CppParser.ClosescopeContext ctx) {  
        //scope.print();  
        //System.out.println("\n");        scope.checkFunc();  
        scope = scope.prev;  
    }  
  
  
    //  
    // While    //    public void enterWhileblock(CppParser.WhileblockContext ctx) {  
        AST a = new AST("whileblock", AST.Types.WHILE_STMT, acc, wurzel);  
        acc.addChild(a);  
        acc = a;  
    }  
    public void exitWhileblock(CppParser.WhileblockContext ctx) { acc = acc.prev;}  
  
    public void enterWhileconn(CppParser.WhileconnContext ctx) {  
        AST a = new AST("whileconn", AST.Types.WHILECONN, acc, wurzel);  
        acc.addChild(a);  
        acc = a;  
    }  
    public void exitWhileconn(CppParser.WhileconnContext ctx) { acc = acc.prev;}  
  
  
    //  
    // StBlock    //    public void enterStblock(CppParser.StblockContext ctx) {  
        AST a = new AST("stblock", AST.Types.STMT, acc, wurzel);  
        acc.addChild(a);  
        acc = a;  
    }  
    public void exitStblock(CppParser.StblockContext ctx) { acc = acc.prev;}  
  
  
    //  
    // Compare    //    public void enterCom(CppParser.ComContext ctx) {  
        AST a = new AST(ctx.getText(), AST.Types.COMPARE, acc, wurzel);  
        acc.addChild(a);  
    }  
  
  
    //  
    // If    //    public void enterIfblock(CppParser.IfblockContext ctx) {  
        AST a = new AST("ifblock", AST.Types.IF_STMT, acc, wurzel);  
        acc.addChild(a);  
        acc = a;  
    }  
    public void exitIfblock(CppParser.IfblockContext ctx) { acc = acc.prev;}  
  
    public void enterIfconn(CppParser.IfconnContext ctx) {  
        AST a = new AST("ifconn", AST.Types.IFCONN, acc, wurzel);  
        acc.addChild(a);  
        acc = a;  
    }  
    public void exitIfconn(CppParser.IfconnContext ctx) {  
        acc = acc.prev;  
    }  
  
    public void enterIfelseblock(CppParser.IfelseblockContext ctx) {  
        AST a = new AST("ifelseblock", AST.Types.IF_STMT, acc, wurzel);  
        acc.addChild(a);  
        acc = a;  
    }  
    public void exitIfelseblock(CppParser.IfelseblockContext ctx) {  
        acc = acc.prev;  
    }  
  
  
    //  
    // Funktionen    //    public void enterDeffunc(CppParser.DeffuncContext ctx) {  
        AST a = new AST(ctx.ID().getText(), AST.Types.FUNCTION_DEF, acc, wurzel);  
        acc.addChild(a);  
        acc = a;  
        AST ret = new AST(ctx.FTYPE().getText(), AST.Types.RETURN, acc, wurzel);  
        acc.addChild(ret);  
        scope.add(ctx.ID().getText(), ctx.FTYPE().getText(), true);  
    }  
    public void exitDeffunc(CppParser.DeffuncContext ctx) {  
        acc = acc.prev;  
        acc = acc.prev;  
        scope = scope.prev;  
    }  
  
    public void enterDefparam(CppParser.DefparamContext ctx) {  
        AST a = new AST("Params", AST.Types.FUNCTION_DEF_PARAM, acc, wurzel);  
        acc.addChild(a);  
        acc = a;  
        scope = new Scope(scope);  
    }  
    public void exitDefparam(CppParser.DefparamContext ctx) {  
    }  
  
    public void enterCallfunc(CppParser.CallfuncContext ctx) {  
        AST a = new AST(ctx.ID().getText(), AST.Types.FUNCTION_CALL, acc, wurzel);  
        acc.addChild(a);  
        acc = a;  
        scope.addFunctionCall(ctx.ID().getText());  
    }  
    public void exitCallfunc(CppParser.CallfuncContext ctx) { acc = acc.prev;}  
  
    public void enterBody(CppParser.BodyContext ctx) {  
        AST a = new AST("Body", AST.Types.BLOCK, acc, wurzel);  
        acc.addChild(a);  
        acc = a;  
    }  
    public void exitBody(CppParser.BodyContext ctx) {  
        acc = acc.prev;  
    }  
  
    public void enterReturn(CppParser.ReturnContext ctx) {  
        AST a = new AST("return", AST.Types.RETURN, acc, wurzel);  
        acc.addChild(a);  
        acc = a;  
    }  
    public void exitReturn(CppParser.ReturnContext ctx) {  
        acc = acc.prev;  
    }  
  
    //  
    // Mathematische Operationen    //    public void enterMul(CppParser.MulContext ctx) {  
        AST a = new AST("MUL", AST.Types.MULL, acc, wurzel);  
        acc.addChild(a);  
        acc = a;  
    }  
    public void exitMul(CppParser.MulContext ctx) {  
        acc = acc.prev;  
    }  
  
    public void enterAdd(CppParser.AddContext ctx) {  
        AST a = new AST("ADD", AST.Types.ADD, acc, wurzel);  
        acc.addChild(a);  
        acc = a;  
    }  
    public void exitAdd(CppParser.AddContext ctx) {  
        acc = acc.prev;  
    }  
  
    public void enterDiv(CppParser.DivContext ctx) {  
        AST a = new AST("DIV", AST.Types.DIV, acc, wurzel);  
        acc.addChild(a);  
        acc = a;  
    }  
    public void exitDiv(CppParser.DivContext ctx) {  
        acc = acc.prev;  
    }  
  
    public void enterSub(CppParser.SubContext ctx) {  
        AST a = new AST("SUB", AST.Types.SUB, acc, wurzel);  
        acc.addChild(a);  
        acc = a;  
    }  
    public void exitSub(CppParser.SubContext ctx) {  
        acc = acc.prev;  
    }  
  
  
    //  
    // Assign    //    public void enterAssignnew(CppParser.AssignnewContext ctx) {  
        AST.Types type;  
        if (ctx.TYPE().getText().equals("int")){  
            type = AST.Types.INTASSIGN;  
        } else if (ctx.TYPE().getText().equals("bool")){  
            type = AST.Types.BOOLASSIGN;  
        } else {  
            type = AST.Types.STRINGASSIGN;  
        }  
  
        AST a = new AST("ass", type, acc, wurzel);  
        acc.addChild(a);  
        scope.add(ctx.ID().getText(), ctx.TYPE().getText(), false);  
        acc = a;  
        AST id = new AST(ctx.ID().getText(), AST.Types.ID, acc, wurzel);  
        acc.addChild(id);  
    }  
    public void exitAssignnew(CppParser.AssignnewContext ctx) {  
        String reqeustedType;  
        if (ctx.TYPE().getText().equals("int")){  
            reqeustedType = "NUM";  
        } else if (ctx.TYPE().getText().equals("bool")){  
            reqeustedType = "BOOL";  
        } else {  
            reqeustedType = "STRING";  
        }  
        boolean pass = true;  
        for (int i = 1; i < acc.kinder.size(); i++){  
            List<String> terminals = new ArrayList<>();  
            terminals = acc.checkTerminal(acc.kinder.get(i), terminals);  
            for (String s : terminals){  
                //System.out.println(s);  
                if (!s.equals(reqeustedType)){  
                    pass = false;  
                }  
            }  
        }  
        System.out.println("Pass assigntest for new var: " + acc.kinder.get(0).value + " " + pass);  
        acc = acc.prev;  
  
    }  
  
    public void enterAssignold(CppParser.AssignoldContext ctx) {  
        if (scope.resolve(ctx.ID().getText()) != null){  
            String typeString = scope.resolve(ctx.ID().getText()).type;  
            AST.Types type;  
            if (typeString.equals("int")){  
                type = AST.Types.INTASSIGN;  
            } else if (typeString.equals("bool")){  
                type = AST.Types.BOOLASSIGN;  
            } else {  
                type = AST.Types.STRINGASSIGN;  
            }  
  
            AST a = new AST("ass", type, acc, wurzel);  
            acc.addChild(a);  
            acc = a;  
            AST id = new AST(ctx.ID().getText(), AST.Types.ID, acc, wurzel);  
            acc.addChild(id);  
        }  
    }  
    public void exitAssignold(CppParser.AssignoldContext ctx) {  
        boolean pass = true;  
        if (scope.resolve(ctx.ID().getText()) == null){  
            pass = false;  
        } else {  
            String type = "";  
            type = scope.resolve(ctx.ID().getText()).type;  
            String reqeustedType;  
            if (type.equals("int")){  
                reqeustedType = "NUM";  
            } else if (type.equals("bool")){  
                reqeustedType = "BOOL";  
            } else {  
                reqeustedType = "STRING";  
            }  
            for (int i = 1; i < acc.kinder.size(); i++){  
                List<String> terminals = new ArrayList<>();  
                terminals = acc.checkTerminal(acc.kinder.get(i), terminals);  
                for (String s : terminals){  
                    //System.out.println(s);  
                    if (!s.equals(reqeustedType)){  
                        pass = false;  
                    }  
                }  
            }  
            System.out.println("Pass assigntest for OLD var: " + acc.kinder.get(0).value + " " + pass);  
            acc = acc.prev;  
        }  
    }  
  
  
    //  
    // Declaration    //    public void enterDecl(CppParser.DeclContext ctx) {  
        AST.Types type;  
        if (ctx.TYPE().getText().equals("int")) {  
            type = AST.Types.INTDECLARATION;  
        } else if (ctx.TYPE().getText().equals("bool")) {  
            type = AST.Types.BOOLDECLARATION;  
        } else {  
            type = AST.Types.STRINGDECLARATION;  
        }  
        AST a = new AST(ctx.ID().getText(), type, acc, wurzel);  
        acc.addChild(a);  
        scope.add(ctx.ID().getText(), ctx.TYPE().getText(), false);  
    }  
  
  
    //  
    // Lexar    //    public void enterNum(CppParser.NumContext ctx) {  
        AST a = new AST(ctx.getText(), AST.Types.NUM, acc, wurzel);  
        acc.addChild(a);  
    }  
  
    public void enterId(CppParser.IdContext ctx) {  
        AST a = new AST(ctx.getText(), AST.Types.ID, acc, wurzel);  
        acc.addChild(a);  
    }  
  
    public void enterString(CppParser.StringContext ctx) {  
        AST a = new AST(ctx.getText(), AST.Types.STRING, acc, wurzel);  
        acc.addChild(a);  
    }  
}
```
