// Generated from /home/jonathan/Sync/Studium/3. Semester/3.1 Compilerbau/Praktikum/7 cpp/src/main/antlr/Cpp.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CppParser}.
 */
public interface CppListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CppParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(CppParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link CppParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(CppParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by the {@code decfunc}
	 * labeled alternative in {@link CppParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterDecfunc(CppParser.DecfuncContext ctx);
	/**
	 * Exit a parse tree produced by the {@code decfunc}
	 * labeled alternative in {@link CppParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitDecfunc(CppParser.DecfuncContext ctx);
	/**
	 * Enter a parse tree produced by the {@code dfunc}
	 * labeled alternative in {@link CppParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterDfunc(CppParser.DfuncContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dfunc}
	 * labeled alternative in {@link CppParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitDfunc(CppParser.DfuncContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cfunc}
	 * labeled alternative in {@link CppParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterCfunc(CppParser.CfuncContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cfunc}
	 * labeled alternative in {@link CppParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitCfunc(CppParser.CfuncContext ctx);
	/**
	 * Enter a parse tree produced by the {@code decl2}
	 * labeled alternative in {@link CppParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterDecl2(CppParser.Decl2Context ctx);
	/**
	 * Exit a parse tree produced by the {@code decl2}
	 * labeled alternative in {@link CppParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitDecl2(CppParser.Decl2Context ctx);
	/**
	 * Enter a parse tree produced by the {@code assign1}
	 * labeled alternative in {@link CppParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterAssign1(CppParser.Assign1Context ctx);
	/**
	 * Exit a parse tree produced by the {@code assign1}
	 * labeled alternative in {@link CppParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitAssign1(CppParser.Assign1Context ctx);
	/**
	 * Enter a parse tree produced by the {@code assign2}
	 * labeled alternative in {@link CppParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterAssign2(CppParser.Assign2Context ctx);
	/**
	 * Exit a parse tree produced by the {@code assign2}
	 * labeled alternative in {@link CppParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitAssign2(CppParser.Assign2Context ctx);
	/**
	 * Enter a parse tree produced by the {@code ifelseblock1}
	 * labeled alternative in {@link CppParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterIfelseblock1(CppParser.Ifelseblock1Context ctx);
	/**
	 * Exit a parse tree produced by the {@code ifelseblock1}
	 * labeled alternative in {@link CppParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitIfelseblock1(CppParser.Ifelseblock1Context ctx);
	/**
	 * Enter a parse tree produced by the {@code whileblock}
	 * labeled alternative in {@link CppParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterWhileblock(CppParser.WhileblockContext ctx);
	/**
	 * Exit a parse tree produced by the {@code whileblock}
	 * labeled alternative in {@link CppParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitWhileblock(CppParser.WhileblockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code builtinstmt}
	 * labeled alternative in {@link CppParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterBuiltinstmt(CppParser.BuiltinstmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code builtinstmt}
	 * labeled alternative in {@link CppParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitBuiltinstmt(CppParser.BuiltinstmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code classsstm}
	 * labeled alternative in {@link CppParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterClasssstm(CppParser.ClasssstmContext ctx);
	/**
	 * Exit a parse tree produced by the {@code classsstm}
	 * labeled alternative in {@link CppParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitClasssstm(CppParser.ClasssstmContext ctx);
	/**
	 * Enter a parse tree produced by the {@code block}
	 * labeled alternative in {@link CppParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterBlock(CppParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by the {@code block}
	 * labeled alternative in {@link CppParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitBlock(CppParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code return}
	 * labeled alternative in {@link CppParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterReturn(CppParser.ReturnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code return}
	 * labeled alternative in {@link CppParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitReturn(CppParser.ReturnContext ctx);
	/**
	 * Enter a parse tree produced by {@link CppParser#body}.
	 * @param ctx the parse tree
	 */
	void enterBody(CppParser.BodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link CppParser#body}.
	 * @param ctx the parse tree
	 */
	void exitBody(CppParser.BodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link CppParser#openscope}.
	 * @param ctx the parse tree
	 */
	void enterOpenscope(CppParser.OpenscopeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CppParser#openscope}.
	 * @param ctx the parse tree
	 */
	void exitOpenscope(CppParser.OpenscopeContext ctx);
	/**
	 * Enter a parse tree produced by {@link CppParser#closescope}.
	 * @param ctx the parse tree
	 */
	void enterClosescope(CppParser.ClosescopeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CppParser#closescope}.
	 * @param ctx the parse tree
	 */
	void exitClosescope(CppParser.ClosescopeContext ctx);
	/**
	 * Enter a parse tree produced by {@link CppParser#basetype}.
	 * @param ctx the parse tree
	 */
	void enterBasetype(CppParser.BasetypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CppParser#basetype}.
	 * @param ctx the parse tree
	 */
	void exitBasetype(CppParser.BasetypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link CppParser#declvar}.
	 * @param ctx the parse tree
	 */
	void enterDeclvar(CppParser.DeclvarContext ctx);
	/**
	 * Exit a parse tree produced by {@link CppParser#declvar}.
	 * @param ctx the parse tree
	 */
	void exitDeclvar(CppParser.DeclvarContext ctx);
	/**
	 * Enter a parse tree produced by {@link CppParser#declarray}.
	 * @param ctx the parse tree
	 */
	void enterDeclarray(CppParser.DeclarrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link CppParser#declarray}.
	 * @param ctx the parse tree
	 */
	void exitDeclarray(CppParser.DeclarrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link CppParser#defrefvar}.
	 * @param ctx the parse tree
	 */
	void enterDefrefvar(CppParser.DefrefvarContext ctx);
	/**
	 * Exit a parse tree produced by {@link CppParser#defrefvar}.
	 * @param ctx the parse tree
	 */
	void exitDefrefvar(CppParser.DefrefvarContext ctx);
	/**
	 * Enter a parse tree produced by {@link CppParser#declrefvar}.
	 * @param ctx the parse tree
	 */
	void enterDeclrefvar(CppParser.DeclrefvarContext ctx);
	/**
	 * Exit a parse tree produced by {@link CppParser#declrefvar}.
	 * @param ctx the parse tree
	 */
	void exitDeclrefvar(CppParser.DeclrefvarContext ctx);
	/**
	 * Enter a parse tree produced by {@link CppParser#decl}.
	 * @param ctx the parse tree
	 */
	void enterDecl(CppParser.DeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link CppParser#decl}.
	 * @param ctx the parse tree
	 */
	void exitDecl(CppParser.DeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link CppParser#assignvar}.
	 * @param ctx the parse tree
	 */
	void enterAssignvar(CppParser.AssignvarContext ctx);
	/**
	 * Exit a parse tree produced by {@link CppParser#assignvar}.
	 * @param ctx the parse tree
	 */
	void exitAssignvar(CppParser.AssignvarContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignarrayelem}
	 * labeled alternative in {@link CppParser#assignarrayelement}.
	 * @param ctx the parse tree
	 */
	void enterAssignarrayelem(CppParser.AssignarrayelemContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignarrayelem}
	 * labeled alternative in {@link CppParser#assignarrayelement}.
	 * @param ctx the parse tree
	 */
	void exitAssignarrayelem(CppParser.AssignarrayelemContext ctx);
	/**
	 * Enter a parse tree produced by the {@code incarray}
	 * labeled alternative in {@link CppParser#assignarrayelement}.
	 * @param ctx the parse tree
	 */
	void enterIncarray(CppParser.IncarrayContext ctx);
	/**
	 * Exit a parse tree produced by the {@code incarray}
	 * labeled alternative in {@link CppParser#assignarrayelement}.
	 * @param ctx the parse tree
	 */
	void exitIncarray(CppParser.IncarrayContext ctx);
	/**
	 * Enter a parse tree produced by the {@code decarray}
	 * labeled alternative in {@link CppParser#assignarrayelement}.
	 * @param ctx the parse tree
	 */
	void enterDecarray(CppParser.DecarrayContext ctx);
	/**
	 * Exit a parse tree produced by the {@code decarray}
	 * labeled alternative in {@link CppParser#assignarrayelement}.
	 * @param ctx the parse tree
	 */
	void exitDecarray(CppParser.DecarrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link CppParser#assignnewarray}.
	 * @param ctx the parse tree
	 */
	void enterAssignnewarray(CppParser.AssignnewarrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link CppParser#assignnewarray}.
	 * @param ctx the parse tree
	 */
	void exitAssignnewarray(CppParser.AssignnewarrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link CppParser#arraysize}.
	 * @param ctx the parse tree
	 */
	void enterArraysize(CppParser.ArraysizeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CppParser#arraysize}.
	 * @param ctx the parse tree
	 */
	void exitArraysize(CppParser.ArraysizeContext ctx);
	/**
	 * Enter a parse tree produced by {@link CppParser#assignclassvar}.
	 * @param ctx the parse tree
	 */
	void enterAssignclassvar(CppParser.AssignclassvarContext ctx);
	/**
	 * Exit a parse tree produced by {@link CppParser#assignclassvar}.
	 * @param ctx the parse tree
	 */
	void exitAssignclassvar(CppParser.AssignclassvarContext ctx);
	/**
	 * Enter a parse tree produced by {@link CppParser#assignnewclass}.
	 * @param ctx the parse tree
	 */
	void enterAssignnewclass(CppParser.AssignnewclassContext ctx);
	/**
	 * Exit a parse tree produced by {@link CppParser#assignnewclass}.
	 * @param ctx the parse tree
	 */
	void exitAssignnewclass(CppParser.AssignnewclassContext ctx);
	/**
	 * Enter a parse tree produced by {@link CppParser#declnew}.
	 * @param ctx the parse tree
	 */
	void enterDeclnew(CppParser.DeclnewContext ctx);
	/**
	 * Exit a parse tree produced by {@link CppParser#declnew}.
	 * @param ctx the parse tree
	 */
	void exitDeclnew(CppParser.DeclnewContext ctx);
	/**
	 * Enter a parse tree produced by {@link CppParser#assignnew}.
	 * @param ctx the parse tree
	 */
	void enterAssignnew(CppParser.AssignnewContext ctx);
	/**
	 * Exit a parse tree produced by {@link CppParser#assignnew}.
	 * @param ctx the parse tree
	 */
	void exitAssignnew(CppParser.AssignnewContext ctx);
	/**
	 * Enter a parse tree produced by {@link CppParser#assignold}.
	 * @param ctx the parse tree
	 */
	void enterAssignold(CppParser.AssignoldContext ctx);
	/**
	 * Exit a parse tree produced by {@link CppParser#assignold}.
	 * @param ctx the parse tree
	 */
	void exitAssignold(CppParser.AssignoldContext ctx);
	/**
	 * Enter a parse tree produced by {@link CppParser#classdef}.
	 * @param ctx the parse tree
	 */
	void enterClassdef(CppParser.ClassdefContext ctx);
	/**
	 * Exit a parse tree produced by {@link CppParser#classdef}.
	 * @param ctx the parse tree
	 */
	void exitClassdef(CppParser.ClassdefContext ctx);
	/**
	 * Enter a parse tree produced by {@link CppParser#constructor}.
	 * @param ctx the parse tree
	 */
	void enterConstructor(CppParser.ConstructorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CppParser#constructor}.
	 * @param ctx the parse tree
	 */
	void exitConstructor(CppParser.ConstructorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CppParser#destruct}.
	 * @param ctx the parse tree
	 */
	void enterDestruct(CppParser.DestructContext ctx);
	/**
	 * Exit a parse tree produced by {@link CppParser#destruct}.
	 * @param ctx the parse tree
	 */
	void exitDestruct(CppParser.DestructContext ctx);
	/**
	 * Enter a parse tree produced by {@link CppParser#initlist}.
	 * @param ctx the parse tree
	 */
	void enterInitlist(CppParser.InitlistContext ctx);
	/**
	 * Exit a parse tree produced by {@link CppParser#initlist}.
	 * @param ctx the parse tree
	 */
	void exitInitlist(CppParser.InitlistContext ctx);
	/**
	 * Enter a parse tree produced by {@link CppParser#vererbung}.
	 * @param ctx the parse tree
	 */
	void enterVererbung(CppParser.VererbungContext ctx);
	/**
	 * Exit a parse tree produced by {@link CppParser#vererbung}.
	 * @param ctx the parse tree
	 */
	void exitVererbung(CppParser.VererbungContext ctx);
	/**
	 * Enter a parse tree produced by {@link CppParser#abstractfunc}.
	 * @param ctx the parse tree
	 */
	void enterAbstractfunc(CppParser.AbstractfuncContext ctx);
	/**
	 * Exit a parse tree produced by {@link CppParser#abstractfunc}.
	 * @param ctx the parse tree
	 */
	void exitAbstractfunc(CppParser.AbstractfuncContext ctx);
	/**
	 * Enter a parse tree produced by {@link CppParser#declfunc}.
	 * @param ctx the parse tree
	 */
	void enterDeclfunc(CppParser.DeclfuncContext ctx);
	/**
	 * Exit a parse tree produced by {@link CppParser#declfunc}.
	 * @param ctx the parse tree
	 */
	void exitDeclfunc(CppParser.DeclfuncContext ctx);
	/**
	 * Enter a parse tree produced by the {@code funcdef}
	 * labeled alternative in {@link CppParser#deffunc}.
	 * @param ctx the parse tree
	 */
	void enterFuncdef(CppParser.FuncdefContext ctx);
	/**
	 * Exit a parse tree produced by the {@code funcdef}
	 * labeled alternative in {@link CppParser#deffunc}.
	 * @param ctx the parse tree
	 */
	void exitFuncdef(CppParser.FuncdefContext ctx);
	/**
	 * Enter a parse tree produced by {@link CppParser#callfunc}.
	 * @param ctx the parse tree
	 */
	void enterCallfunc(CppParser.CallfuncContext ctx);
	/**
	 * Exit a parse tree produced by {@link CppParser#callfunc}.
	 * @param ctx the parse tree
	 */
	void exitCallfunc(CppParser.CallfuncContext ctx);
	/**
	 * Enter a parse tree produced by {@link CppParser#defparamlist}.
	 * @param ctx the parse tree
	 */
	void enterDefparamlist(CppParser.DefparamlistContext ctx);
	/**
	 * Exit a parse tree produced by {@link CppParser#defparamlist}.
	 * @param ctx the parse tree
	 */
	void exitDefparamlist(CppParser.DefparamlistContext ctx);
	/**
	 * Enter a parse tree produced by {@link CppParser#defparam}.
	 * @param ctx the parse tree
	 */
	void enterDefparam(CppParser.DefparamContext ctx);
	/**
	 * Exit a parse tree produced by {@link CppParser#defparam}.
	 * @param ctx the parse tree
	 */
	void exitDefparam(CppParser.DefparamContext ctx);
	/**
	 * Enter a parse tree produced by {@link CppParser#callparamlist}.
	 * @param ctx the parse tree
	 */
	void enterCallparamlist(CppParser.CallparamlistContext ctx);
	/**
	 * Exit a parse tree produced by {@link CppParser#callparamlist}.
	 * @param ctx the parse tree
	 */
	void exitCallparamlist(CppParser.CallparamlistContext ctx);
	/**
	 * Enter a parse tree produced by {@link CppParser#callparam}.
	 * @param ctx the parse tree
	 */
	void enterCallparam(CppParser.CallparamContext ctx);
	/**
	 * Exit a parse tree produced by {@link CppParser#callparam}.
	 * @param ctx the parse tree
	 */
	void exitCallparam(CppParser.CallparamContext ctx);
	/**
	 * Enter a parse tree produced by the {@code printbool}
	 * labeled alternative in {@link CppParser#builtin}.
	 * @param ctx the parse tree
	 */
	void enterPrintbool(CppParser.PrintboolContext ctx);
	/**
	 * Exit a parse tree produced by the {@code printbool}
	 * labeled alternative in {@link CppParser#builtin}.
	 * @param ctx the parse tree
	 */
	void exitPrintbool(CppParser.PrintboolContext ctx);
	/**
	 * Enter a parse tree produced by the {@code printint}
	 * labeled alternative in {@link CppParser#builtin}.
	 * @param ctx the parse tree
	 */
	void enterPrintint(CppParser.PrintintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code printint}
	 * labeled alternative in {@link CppParser#builtin}.
	 * @param ctx the parse tree
	 */
	void exitPrintint(CppParser.PrintintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code printchar}
	 * labeled alternative in {@link CppParser#builtin}.
	 * @param ctx the parse tree
	 */
	void enterPrintchar(CppParser.PrintcharContext ctx);
	/**
	 * Exit a parse tree produced by the {@code printchar}
	 * labeled alternative in {@link CppParser#builtin}.
	 * @param ctx the parse tree
	 */
	void exitPrintchar(CppParser.PrintcharContext ctx);
	/**
	 * Enter a parse tree produced by {@link CppParser#ifelseblock}.
	 * @param ctx the parse tree
	 */
	void enterIfelseblock(CppParser.IfelseblockContext ctx);
	/**
	 * Exit a parse tree produced by {@link CppParser#ifelseblock}.
	 * @param ctx the parse tree
	 */
	void exitIfelseblock(CppParser.IfelseblockContext ctx);
	/**
	 * Enter a parse tree produced by {@link CppParser#ifblock}.
	 * @param ctx the parse tree
	 */
	void enterIfblock(CppParser.IfblockContext ctx);
	/**
	 * Exit a parse tree produced by {@link CppParser#ifblock}.
	 * @param ctx the parse tree
	 */
	void exitIfblock(CppParser.IfblockContext ctx);
	/**
	 * Enter a parse tree produced by {@link CppParser#elseblock}.
	 * @param ctx the parse tree
	 */
	void enterElseblock(CppParser.ElseblockContext ctx);
	/**
	 * Exit a parse tree produced by {@link CppParser#elseblock}.
	 * @param ctx the parse tree
	 */
	void exitElseblock(CppParser.ElseblockContext ctx);
	/**
	 * Enter a parse tree produced by {@link CppParser#ifconn}.
	 * @param ctx the parse tree
	 */
	void enterIfconn(CppParser.IfconnContext ctx);
	/**
	 * Exit a parse tree produced by {@link CppParser#ifconn}.
	 * @param ctx the parse tree
	 */
	void exitIfconn(CppParser.IfconnContext ctx);
	/**
	 * Enter a parse tree produced by {@link CppParser#whileconn}.
	 * @param ctx the parse tree
	 */
	void enterWhileconn(CppParser.WhileconnContext ctx);
	/**
	 * Exit a parse tree produced by {@link CppParser#whileconn}.
	 * @param ctx the parse tree
	 */
	void exitWhileconn(CppParser.WhileconnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code add}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAdd(CppParser.AddContext ctx);
	/**
	 * Exit a parse tree produced by the {@code add}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAdd(CppParser.AddContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sub}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterSub(CppParser.SubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sub}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitSub(CppParser.SubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code compare}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCompare(CppParser.CompareContext ctx);
	/**
	 * Exit a parse tree produced by the {@code compare}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCompare(CppParser.CompareContext ctx);
	/**
	 * Enter a parse tree produced by the {@code dec}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterDec(CppParser.DecContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dec}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitDec(CppParser.DecContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bool}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBool(CppParser.BoolContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bool}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBool(CppParser.BoolContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mul}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMul(CppParser.MulContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mul}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMul(CppParser.MulContext ctx);
	/**
	 * Enter a parse tree produced by the {@code muleq}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMuleq(CppParser.MuleqContext ctx);
	/**
	 * Exit a parse tree produced by the {@code muleq}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMuleq(CppParser.MuleqContext ctx);
	/**
	 * Enter a parse tree produced by the {@code subeq}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterSubeq(CppParser.SubeqContext ctx);
	/**
	 * Exit a parse tree produced by the {@code subeq}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitSubeq(CppParser.SubeqContext ctx);
	/**
	 * Enter a parse tree produced by the {@code num}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNum(CppParser.NumContext ctx);
	/**
	 * Exit a parse tree produced by the {@code num}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNum(CppParser.NumContext ctx);
	/**
	 * Enter a parse tree produced by the {@code addeq}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAddeq(CppParser.AddeqContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addeq}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAddeq(CppParser.AddeqContext ctx);
	/**
	 * Enter a parse tree produced by the {@code classvar}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterClassvar(CppParser.ClassvarContext ctx);
	/**
	 * Exit a parse tree produced by the {@code classvar}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitClassvar(CppParser.ClassvarContext ctx);
	/**
	 * Enter a parse tree produced by the {@code diveq}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterDiveq(CppParser.DiveqContext ctx);
	/**
	 * Exit a parse tree produced by the {@code diveq}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitDiveq(CppParser.DiveqContext ctx);
	/**
	 * Enter a parse tree produced by the {@code call}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCall(CppParser.CallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code call}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCall(CppParser.CallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code div}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterDiv(CppParser.DivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code div}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitDiv(CppParser.DivContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayelem}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterArrayelem(CppParser.ArrayelemContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayelem}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitArrayelem(CppParser.ArrayelemContext ctx);
	/**
	 * Enter a parse tree produced by the {@code char}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterChar(CppParser.CharContext ctx);
	/**
	 * Exit a parse tree produced by the {@code char}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitChar(CppParser.CharContext ctx);
	/**
	 * Enter a parse tree produced by the {@code id}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterId(CppParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by the {@code id}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitId(CppParser.IdContext ctx);
	/**
	 * Enter a parse tree produced by the {@code classarrayelem}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterClassarrayelem(CppParser.ClassarrayelemContext ctx);
	/**
	 * Exit a parse tree produced by the {@code classarrayelem}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitClassarrayelem(CppParser.ClassarrayelemContext ctx);
	/**
	 * Enter a parse tree produced by the {@code inc}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterInc(CppParser.IncContext ctx);
	/**
	 * Exit a parse tree produced by the {@code inc}
	 * labeled alternative in {@link CppParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitInc(CppParser.IncContext ctx);
	/**
	 * Enter a parse tree produced by {@link CppParser#com}.
	 * @param ctx the parse tree
	 */
	void enterCom(CppParser.ComContext ctx);
	/**
	 * Exit a parse tree produced by {@link CppParser#com}.
	 * @param ctx the parse tree
	 */
	void exitCom(CppParser.ComContext ctx);
	/**
	 * Enter a parse tree produced by {@link CppParser#const}.
	 * @param ctx the parse tree
	 */
	void enterConst(CppParser.ConstContext ctx);
	/**
	 * Exit a parse tree produced by {@link CppParser#const}.
	 * @param ctx the parse tree
	 */
	void exitConst(CppParser.ConstContext ctx);
}