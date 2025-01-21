// Generated from /home/jonathan/Sync/Studium/3. Semester/3.1 Compilerbau/Praktikum/7 cpp/src/main/antlr/Cpp.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CppParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CppVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CppParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(CppParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by the {@code decfunc}
	 * labeled alternative in {@link CppParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecfunc(CppParser.DecfuncContext ctx);
	/**
	 * Visit a parse tree produced by the {@code dfunc}
	 * labeled alternative in {@link CppParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDfunc(CppParser.DfuncContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cfunc}
	 * labeled alternative in {@link CppParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCfunc(CppParser.CfuncContext ctx);
	/**
	 * Visit a parse tree produced by the {@code decl2}
	 * labeled alternative in {@link CppParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecl2(CppParser.Decl2Context ctx);
	/**
	 * Visit a parse tree produced by the {@code assign1}
	 * labeled alternative in {@link CppParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign1(CppParser.Assign1Context ctx);
	/**
	 * Visit a parse tree produced by the {@code assign2}
	 * labeled alternative in {@link CppParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign2(CppParser.Assign2Context ctx);
	/**
	 * Visit a parse tree produced by the {@code ifelseblock1}
	 * labeled alternative in {@link CppParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfelseblock1(CppParser.Ifelseblock1Context ctx);
	/**
	 * Visit a parse tree produced by the {@code whileblock}
	 * labeled alternative in {@link CppParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileblock(CppParser.WhileblockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code builtinstmt}
	 * labeled alternative in {@link CppParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBuiltinstmt(CppParser.BuiltinstmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code classsstm}
	 * labeled alternative in {@link CppParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClasssstm(CppParser.ClasssstmContext ctx);
	/**
	 * Visit a parse tree produced by the {@code block}
	 * labeled alternative in {@link CppParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(CppParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code return}
	 * labeled alternative in {@link CppParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn(CppParser.ReturnContext ctx);
	/**
	 * Visit a parse tree produced by {@link CppParser#body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBody(CppParser.BodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link CppParser#openscope}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpenscope(CppParser.OpenscopeContext ctx);
	/**
	 * Visit a parse tree produced by {@link CppParser#closescope}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClosescope(CppParser.ClosescopeContext ctx);
	/**
	 * Visit a parse tree produced by {@link CppParser#basetype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBasetype(CppParser.BasetypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link CppParser#declvar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclvar(CppParser.DeclvarContext ctx);
	/**
	 * Visit a parse tree produced by {@link CppParser#declarray}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarray(CppParser.DeclarrayContext ctx);
	/**
	 * Visit a parse tree produced by {@link CppParser#defrefvar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefrefvar(CppParser.DefrefvarContext ctx);
	/**
	 * Visit a parse tree produced by {@link CppParser#declrefvar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclrefvar(CppParser.DeclrefvarContext ctx);
	/**
	 * Visit a parse tree produced by {@link CppParser#decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecl(CppParser.DeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link CppParser#assignvar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignvar(CppParser.AssignvarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignarrayelem}
	 * labeled alternative in {@link CppParser#assignarrayelement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignarrayelem(CppParser.AssignarrayelemContext ctx);
	/**
	 * Visit a parse tree produced by the {@code incarray}
	 * labeled alternative in {@link CppParser#assignarrayelement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIncarray(CppParser.IncarrayContext ctx);
	/**
	 * Visit a parse tree produced by the {@code decarray}
	 * labeled alternative in {@link CppParser#assignarrayelement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecarray(CppParser.DecarrayContext ctx);
	/**
	 * Visit a parse tree produced by {@link CppParser#assignnewarray}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignnewarray(CppParser.AssignnewarrayContext ctx);
	/**
	 * Visit a parse tree produced by {@link CppParser#arraysize}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArraysize(CppParser.ArraysizeContext ctx);
	/**
	 * Visit a parse tree produced by {@link CppParser#assignclassvar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignclassvar(CppParser.AssignclassvarContext ctx);
	/**
	 * Visit a parse tree produced by {@link CppParser#assignnewclass}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignnewclass(CppParser.AssignnewclassContext ctx);
	/**
	 * Visit a parse tree produced by {@link CppParser#declnew}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclnew(CppParser.DeclnewContext ctx);
	/**
	 * Visit a parse tree produced by {@link CppParser#assignnew}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignnew(CppParser.AssignnewContext ctx);
	/**
	 * Visit a parse tree produced by {@link CppParser#assignold}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignold(CppParser.AssignoldContext ctx);
	/**
	 * Visit a parse tree produced by {@link CppParser#classdef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassdef(CppParser.ClassdefContext ctx);
	/**
	 * Visit a parse tree produced by {@link CppParser#constructor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstructor(CppParser.ConstructorContext ctx);
	/**
	 * Visit a parse tree produced by {@link CppParser#destruct}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDestruct(CppParser.DestructContext ctx);
	/**
	 * Visit a parse tree produced by {@link CppParser#initlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitlist(CppParser.InitlistContext ctx);
	/**
	 * Visit a parse tree produced by {@link CppParser#vererbung}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVererbung(CppParser.VererbungContext ctx);
	/**
	 * Visit a parse tree produced by {@link CppParser#abstractfunc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAbstractfunc(CppParser.AbstractfuncContext ctx);
	/**
	 * Visit a parse tree produced by {@link CppParser#declfunc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclfunc(CppParser.DeclfuncContext ctx);
	/**
	 * Visit a parse tree produced by the {@code funcdef}
	 * labeled alternative in {@link CppParser#deffunc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncdef(CppParser.FuncdefContext ctx);
	/**
	 * Visit a parse tree produced by {@link CppParser#callfunc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallfunc(CppParser.CallfuncContext ctx);
	/**
	 * Visit a parse tree produced by {@link CppParser#defparamlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefparamlist(CppParser.DefparamlistContext ctx);
	/**
	 * Visit a parse tree produced by {@link CppParser#defparam}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefparam(CppParser.DefparamContext ctx);
	/**
	 * Visit a parse tree produced by {@link CppParser#callparamlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallparamlist(CppParser.CallparamlistContext ctx);
	/**
	 * Visit a parse tree produced by {@link CppParser#callparam}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallparam(CppParser.CallparamContext ctx);
	/**
	 * Visit a parse tree produced by the {@code printbool}
	 * labeled alternative in {@link CppParser#builtin}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintbool(CppParser.PrintboolContext ctx);
	/**
	 * Visit a parse tree produced by the {@code printint}
	 * labeled alternative in {@link CppParser#builtin}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintint(CppParser.PrintintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code printchar}
	 * labeled alternative in {@link CppParser#builtin}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintchar(CppParser.PrintcharContext ctx);
	/**
	 * Visit a parse tree produced by {@link CppParser#ifelseblock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfelseblock(CppParser.IfelseblockContext ctx);
	/**
	 * Visit a parse tree produced by {@link CppParser#ifblock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfblock(CppParser.IfblockContext ctx);
	/**
	 * Visit a parse tree produced by {@link CppParser#elseblock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElseblock(CppParser.ElseblockContext ctx);
	/**
	 * Visit a parse tree produced by {@link CppParser#ifconn}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfconn(CppParser.IfconnContext ctx);
	/**
	 * Visit a parse tree produced by {@link CppParser#whileconn}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileconn(CppParser.WhileconnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code add}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdd(CppParser.AddContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sub}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSub(CppParser.SubContext ctx);
	/**
	 * Visit a parse tree produced by the {@code compare}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompare(CppParser.CompareContext ctx);
	/**
	 * Visit a parse tree produced by the {@code dec}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDec(CppParser.DecContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bool}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBool(CppParser.BoolContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mul}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMul(CppParser.MulContext ctx);
	/**
	 * Visit a parse tree produced by the {@code muleq}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMuleq(CppParser.MuleqContext ctx);
	/**
	 * Visit a parse tree produced by the {@code subeq}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubeq(CppParser.SubeqContext ctx);
	/**
	 * Visit a parse tree produced by the {@code num}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNum(CppParser.NumContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addeq}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddeq(CppParser.AddeqContext ctx);
	/**
	 * Visit a parse tree produced by the {@code classvar}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassvar(CppParser.ClassvarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code diveq}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDiveq(CppParser.DiveqContext ctx);
	/**
	 * Visit a parse tree produced by the {@code call}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCall(CppParser.CallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code div}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDiv(CppParser.DivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayelem}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayelem(CppParser.ArrayelemContext ctx);
	/**
	 * Visit a parse tree produced by the {@code char}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChar(CppParser.CharContext ctx);
	/**
	 * Visit a parse tree produced by the {@code id}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId(CppParser.IdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code classarrayelem}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassarrayelem(CppParser.ClassarrayelemContext ctx);
	/**
	 * Visit a parse tree produced by the {@code inc}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInc(CppParser.IncContext ctx);
	/**
	 * Visit a parse tree produced by {@link CppParser#com}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCom(CppParser.ComContext ctx);
	/**
	 * Visit a parse tree produced by {@link CppParser#const}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConst(CppParser.ConstContext ctx);
}