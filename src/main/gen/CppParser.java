// Generated from /home/jonathan/Sync/Studium/3. Semester/3.1 Compilerbau/Praktikum/7 cpp/src/main/antlr/Cpp.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class CppParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, OPENSCOPE=26, CLOSESCOPE=27, OVERRIDE=28, VIRTUAL=29, RETURN=30, 
		IF=31, ELSE=32, WHILE=33, DO=34, EQUAL=35, NOTEQUAL=36, LOWER=37, LOWEREQUAL=38, 
		GREATER=39, GREATEREQUAL=40, BOOL=41, SET=42, CONST=43, VOID=44, TYPE=45, 
		EXPLIZIT=46, REF=47, NUM=48, ID=49, CHAR=50, COMMENTLINE=51, COMMENTBLOCK=52, 
		WS=53;
	public static final int
		RULE_start = 0, RULE_stmt = 1, RULE_body = 2, RULE_openscope = 3, RULE_closescope = 4, 
		RULE_basetype = 5, RULE_declvar = 6, RULE_declarray = 7, RULE_defrefvar = 8, 
		RULE_declrefvar = 9, RULE_decl = 10, RULE_assignvar = 11, RULE_assignarrayelement = 12, 
		RULE_assignnewarray = 13, RULE_arraysize = 14, RULE_assignclassvar = 15, 
		RULE_assignnewclass = 16, RULE_declnew = 17, RULE_assignnew = 18, RULE_assignold = 19, 
		RULE_classdef = 20, RULE_constructor = 21, RULE_destruct = 22, RULE_initlist = 23, 
		RULE_vererbung = 24, RULE_abstractfunc = 25, RULE_declfunc = 26, RULE_deffunc = 27, 
		RULE_callfunc = 28, RULE_defparamlist = 29, RULE_defparam = 30, RULE_callparamlist = 31, 
		RULE_callparam = 32, RULE_builtin = 33, RULE_ifelseblock = 34, RULE_ifblock = 35, 
		RULE_elseblock = 36, RULE_ifconn = 37, RULE_whileconn = 38, RULE_expr = 39, 
		RULE_com = 40, RULE_const = 41;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "stmt", "body", "openscope", "closescope", "basetype", "declvar", 
			"declarray", "defrefvar", "declrefvar", "decl", "assignvar", "assignarrayelement", 
			"assignnewarray", "arraysize", "assignclassvar", "assignnewclass", "declnew", 
			"assignnew", "assignold", "classdef", "constructor", "destruct", "initlist", 
			"vererbung", "abstractfunc", "declfunc", "deffunc", "callfunc", "defparamlist", 
			"defparam", "callparamlist", "callparam", "builtin", "ifelseblock", "ifblock", 
			"elseblock", "ifconn", "whileconn", "expr", "com", "const"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'('", "')'", "'['", "']'", "'++'", "'--'", "','", "'.'", 
			"'class'", "'public'", "':'", "'default'", "'~'", "'print_bool'", "'print_int'", 
			"'print_char'", "'*'", "'*='", "'/'", "'/='", "'+'", "'+='", "'-'", "'-='", 
			"'{'", "'}'", "'override'", "'virtual'", "'return'", "'if'", "'else'", 
			"'while'", "'do'", "'=='", "'!='", "'<'", "'<='", "'>'", "'>='", null, 
			"'='", "'const'", "'void'", null, "'explicit'", "'&'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, "OPENSCOPE", "CLOSESCOPE", "OVERRIDE", "VIRTUAL", "RETURN", 
			"IF", "ELSE", "WHILE", "DO", "EQUAL", "NOTEQUAL", "LOWER", "LOWEREQUAL", 
			"GREATER", "GREATEREQUAL", "BOOL", "SET", "CONST", "VOID", "TYPE", "EXPLIZIT", 
			"REF", "NUM", "ID", "CHAR", "COMMENTLINE", "COMMENTBLOCK", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Cpp.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CppParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StartContext extends ParserRuleContext {
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitStart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitStart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 624535019947008L) != 0)) {
				{
				{
				setState(84);
				stmt();
				}
				}
				setState(89);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StmtContext extends ParserRuleContext {
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
	 
		public StmtContext() { }
		public void copyFrom(StmtContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class WhileblockContext extends StmtContext {
		public WhileconnContext whileconn() {
			return getRuleContext(WhileconnContext.class,0);
		}
		public OpenscopeContext openscope() {
			return getRuleContext(OpenscopeContext.class,0);
		}
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public ClosescopeContext closescope() {
			return getRuleContext(ClosescopeContext.class,0);
		}
		public WhileblockContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterWhileblock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitWhileblock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitWhileblock(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CfuncContext extends StmtContext {
		public CallfuncContext callfunc() {
			return getRuleContext(CallfuncContext.class,0);
		}
		public CfuncContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterCfunc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitCfunc(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitCfunc(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class Decl2Context extends StmtContext {
		public DeclnewContext declnew() {
			return getRuleContext(DeclnewContext.class,0);
		}
		public Decl2Context(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterDecl2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitDecl2(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitDecl2(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DfuncContext extends StmtContext {
		public DeffuncContext deffunc() {
			return getRuleContext(DeffuncContext.class,0);
		}
		public DfuncContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterDfunc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitDfunc(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitDfunc(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BuiltinstmtContext extends StmtContext {
		public BuiltinContext builtin() {
			return getRuleContext(BuiltinContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public BuiltinstmtContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterBuiltinstmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitBuiltinstmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitBuiltinstmt(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BlockContext extends StmtContext {
		public OpenscopeContext openscope() {
			return getRuleContext(OpenscopeContext.class,0);
		}
		public ClosescopeContext closescope() {
			return getRuleContext(ClosescopeContext.class,0);
		}
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public BlockContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class Ifelseblock1Context extends StmtContext {
		public IfelseblockContext ifelseblock() {
			return getRuleContext(IfelseblockContext.class,0);
		}
		public Ifelseblock1Context(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterIfelseblock1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitIfelseblock1(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitIfelseblock1(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ClasssstmContext extends StmtContext {
		public ClassdefContext classdef() {
			return getRuleContext(ClassdefContext.class,0);
		}
		public ClasssstmContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterClasssstm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitClasssstm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitClasssstm(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class Assign2Context extends StmtContext {
		public AssignoldContext assignold() {
			return getRuleContext(AssignoldContext.class,0);
		}
		public Assign2Context(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterAssign2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitAssign2(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitAssign2(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DecfuncContext extends StmtContext {
		public DeclfuncContext declfunc() {
			return getRuleContext(DeclfuncContext.class,0);
		}
		public DecfuncContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterDecfunc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitDecfunc(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitDecfunc(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class Assign1Context extends StmtContext {
		public AssignnewContext assignnew() {
			return getRuleContext(AssignnewContext.class,0);
		}
		public Assign1Context(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterAssign1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitAssign1(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitAssign1(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ReturnContext extends StmtContext {
		public TerminalNode RETURN() { return getToken(CppParser.RETURN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ReturnContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterReturn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitReturn(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitReturn(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_stmt);
		int _la;
		try {
			setState(135);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				_localctx = new DecfuncContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(90);
				declfunc();
				setState(91);
				match(T__0);
				}
				break;
			case 2:
				_localctx = new DfuncContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(93);
				deffunc();
				}
				break;
			case 3:
				_localctx = new CfuncContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(94);
				callfunc();
				setState(95);
				match(T__0);
				}
				break;
			case 4:
				_localctx = new Decl2Context(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(97);
				declnew();
				setState(98);
				match(T__0);
				}
				break;
			case 5:
				_localctx = new Assign1Context(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(100);
				assignnew();
				setState(101);
				match(T__0);
				}
				break;
			case 6:
				_localctx = new Assign2Context(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(103);
				assignold();
				setState(104);
				match(T__0);
				}
				break;
			case 7:
				_localctx = new Ifelseblock1Context(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(106);
				ifelseblock();
				}
				break;
			case 8:
				_localctx = new WhileblockContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(107);
				whileconn();
				setState(108);
				openscope();
				setState(109);
				body();
				setState(110);
				closescope();
				}
				break;
			case 9:
				_localctx = new BuiltinstmtContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(112);
				builtin();
				setState(113);
				match(T__1);
				setState(114);
				expr(0);
				setState(115);
				match(T__2);
				setState(116);
				match(T__0);
				}
				break;
			case 10:
				_localctx = new ClasssstmContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(118);
				classdef();
				}
				break;
			case 11:
				_localctx = new BlockContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(119);
				openscope();
				setState(124); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					setState(124);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
					case 1:
						{
						setState(120);
						stmt();
						}
						break;
					case 2:
						{
						setState(121);
						expr(0);
						setState(122);
						match(T__0);
						}
						break;
					}
					}
					setState(126); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 2034108926755840L) != 0) );
				setState(128);
				closescope();
				}
				break;
			case 12:
				_localctx = new ReturnContext(_localctx);
				enterOuterAlt(_localctx, 12);
				{
				setState(130);
				match(RETURN);
				setState(132);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1972523860230144L) != 0)) {
					{
					setState(131);
					expr(0);
					}
				}

				setState(134);
				match(T__0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BodyContext extends ParserRuleContext {
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public BodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BodyContext body() throws RecognitionException {
		BodyContext _localctx = new BodyContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_body);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(141); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(141);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
				case 1:
					{
					setState(137);
					stmt();
					}
					break;
				case 2:
					{
					setState(138);
					expr(0);
					setState(139);
					match(T__0);
					}
					break;
				}
				}
				setState(143); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 2034108926755840L) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OpenscopeContext extends ParserRuleContext {
		public TerminalNode OPENSCOPE() { return getToken(CppParser.OPENSCOPE, 0); }
		public OpenscopeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_openscope; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterOpenscope(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitOpenscope(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitOpenscope(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OpenscopeContext openscope() throws RecognitionException {
		OpenscopeContext _localctx = new OpenscopeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_openscope);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			match(OPENSCOPE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ClosescopeContext extends ParserRuleContext {
		public TerminalNode CLOSESCOPE() { return getToken(CppParser.CLOSESCOPE, 0); }
		public ClosescopeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_closescope; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterClosescope(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitClosescope(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitClosescope(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClosescopeContext closescope() throws RecognitionException {
		ClosescopeContext _localctx = new ClosescopeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_closescope);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147);
			match(CLOSESCOPE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BasetypeContext extends ParserRuleContext {
		public TerminalNode TYPE() { return getToken(CppParser.TYPE, 0); }
		public TerminalNode ID() { return getToken(CppParser.ID, 0); }
		public BasetypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_basetype; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterBasetype(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitBasetype(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitBasetype(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BasetypeContext basetype() throws RecognitionException {
		BasetypeContext _localctx = new BasetypeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_basetype);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149);
			_la = _input.LA(1);
			if ( !(_la==TYPE || _la==ID) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclvarContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(CppParser.ID, 0); }
		public DeclvarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declvar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterDeclvar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitDeclvar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitDeclvar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclvarContext declvar() throws RecognitionException {
		DeclvarContext _localctx = new DeclvarContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_declvar);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclarrayContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(CppParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public DeclarrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declarray; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterDeclarray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitDeclarray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitDeclarray(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclarrayContext declarray() throws RecognitionException {
		DeclarrayContext _localctx = new DeclarrayContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_declarray);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(153);
			match(ID);
			setState(154);
			match(T__3);
			setState(155);
			expr(0);
			setState(156);
			match(T__4);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DefrefvarContext extends ParserRuleContext {
		public BasetypeContext basetype() {
			return getRuleContext(BasetypeContext.class,0);
		}
		public TerminalNode REF() { return getToken(CppParser.REF, 0); }
		public TerminalNode ID() { return getToken(CppParser.ID, 0); }
		public ConstContext const_() {
			return getRuleContext(ConstContext.class,0);
		}
		public DefrefvarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defrefvar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterDefrefvar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitDefrefvar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitDefrefvar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefrefvarContext defrefvar() throws RecognitionException {
		DefrefvarContext _localctx = new DefrefvarContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_defrefvar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==CONST) {
				{
				setState(158);
				const_();
				}
			}

			setState(161);
			basetype();
			setState(162);
			match(REF);
			setState(163);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclrefvarContext extends ParserRuleContext {
		public BasetypeContext basetype() {
			return getRuleContext(BasetypeContext.class,0);
		}
		public TerminalNode REF() { return getToken(CppParser.REF, 0); }
		public ConstContext const_() {
			return getRuleContext(ConstContext.class,0);
		}
		public DeclrefvarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declrefvar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterDeclrefvar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitDeclrefvar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitDeclrefvar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclrefvarContext declrefvar() throws RecognitionException {
		DeclrefvarContext _localctx = new DeclrefvarContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_declrefvar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==CONST) {
				{
				setState(165);
				const_();
				}
			}

			setState(168);
			basetype();
			setState(169);
			match(REF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclContext extends ParserRuleContext {
		public BasetypeContext basetype() {
			return getRuleContext(BasetypeContext.class,0);
		}
		public DeclvarContext declvar() {
			return getRuleContext(DeclvarContext.class,0);
		}
		public DeclarrayContext declarray() {
			return getRuleContext(DeclarrayContext.class,0);
		}
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_decl);
		try {
			setState(177);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(171);
				basetype();
				setState(172);
				declvar();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(174);
				basetype();
				setState(175);
				declarray();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssignvarContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(CppParser.ID, 0); }
		public TerminalNode SET() { return getToken(CppParser.SET, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AssignvarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignvar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterAssignvar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitAssignvar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitAssignvar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignvarContext assignvar() throws RecognitionException {
		AssignvarContext _localctx = new AssignvarContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_assignvar);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(179);
			match(ID);
			setState(180);
			match(SET);
			setState(181);
			expr(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssignarrayelementContext extends ParserRuleContext {
		public AssignarrayelementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignarrayelement; }
	 
		public AssignarrayelementContext() { }
		public void copyFrom(AssignarrayelementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AssignarrayelemContext extends AssignarrayelementContext {
		public TerminalNode ID() { return getToken(CppParser.ID, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode SET() { return getToken(CppParser.SET, 0); }
		public AssignarrayelemContext(AssignarrayelementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterAssignarrayelem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitAssignarrayelem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitAssignarrayelem(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DecarrayContext extends AssignarrayelementContext {
		public TerminalNode ID() { return getToken(CppParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public DecarrayContext(AssignarrayelementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterDecarray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitDecarray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitDecarray(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IncarrayContext extends AssignarrayelementContext {
		public TerminalNode ID() { return getToken(CppParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public IncarrayContext(AssignarrayelementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterIncarray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitIncarray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitIncarray(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignarrayelementContext assignarrayelement() throws RecognitionException {
		AssignarrayelementContext _localctx = new AssignarrayelementContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_assignarrayelement);
		try {
			setState(202);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				_localctx = new AssignarrayelemContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(183);
				match(ID);
				setState(184);
				match(T__3);
				setState(185);
				expr(0);
				setState(186);
				match(T__4);
				setState(187);
				match(SET);
				setState(188);
				expr(0);
				}
				break;
			case 2:
				_localctx = new IncarrayContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(190);
				match(ID);
				setState(191);
				match(T__3);
				setState(192);
				expr(0);
				setState(193);
				match(T__4);
				setState(194);
				match(T__5);
				}
				break;
			case 3:
				_localctx = new DecarrayContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(196);
				match(ID);
				setState(197);
				match(T__3);
				setState(198);
				expr(0);
				setState(199);
				match(T__4);
				setState(200);
				match(T__6);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssignnewarrayContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(CppParser.ID, 0); }
		public TerminalNode SET() { return getToken(CppParser.SET, 0); }
		public TerminalNode OPENSCOPE() { return getToken(CppParser.OPENSCOPE, 0); }
		public TerminalNode CLOSESCOPE() { return getToken(CppParser.CLOSESCOPE, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ArraysizeContext arraysize() {
			return getRuleContext(ArraysizeContext.class,0);
		}
		public AssignnewarrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignnewarray; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterAssignnewarray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitAssignnewarray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitAssignnewarray(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignnewarrayContext assignnewarray() throws RecognitionException {
		AssignnewarrayContext _localctx = new AssignnewarrayContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_assignnewarray);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(204);
			match(ID);
			setState(205);
			match(T__3);
			setState(207);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1972523860230144L) != 0)) {
				{
				setState(206);
				arraysize();
				}
			}

			setState(209);
			match(T__4);
			setState(210);
			match(SET);
			setState(211);
			match(OPENSCOPE);
			{
			setState(212);
			expr(0);
			}
			setState(217);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__7) {
				{
				{
				setState(213);
				match(T__7);
				setState(214);
				expr(0);
				}
				}
				setState(219);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(220);
			match(CLOSESCOPE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArraysizeContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ArraysizeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arraysize; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterArraysize(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitArraysize(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitArraysize(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArraysizeContext arraysize() throws RecognitionException {
		ArraysizeContext _localctx = new ArraysizeContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_arraysize);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(222);
			expr(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssignclassvarContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(CppParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(CppParser.ID, i);
		}
		public TerminalNode SET() { return getToken(CppParser.SET, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public AssignclassvarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignclassvar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterAssignclassvar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitAssignclassvar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitAssignclassvar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignclassvarContext assignclassvar() throws RecognitionException {
		AssignclassvarContext _localctx = new AssignclassvarContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_assignclassvar);
		try {
			setState(238);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(224);
				match(ID);
				setState(225);
				match(T__8);
				setState(226);
				match(ID);
				setState(227);
				match(SET);
				setState(228);
				expr(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(229);
				match(ID);
				setState(230);
				match(T__3);
				setState(231);
				expr(0);
				setState(232);
				match(T__4);
				setState(233);
				match(T__8);
				setState(234);
				match(ID);
				setState(235);
				match(SET);
				setState(236);
				expr(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssignnewclassContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(CppParser.ID, 0); }
		public TerminalNode SET() { return getToken(CppParser.SET, 0); }
		public CallfuncContext callfunc() {
			return getRuleContext(CallfuncContext.class,0);
		}
		public CallparamlistContext callparamlist() {
			return getRuleContext(CallparamlistContext.class,0);
		}
		public AssignnewclassContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignnewclass; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterAssignnewclass(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitAssignnewclass(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitAssignnewclass(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignnewclassContext assignnewclass() throws RecognitionException {
		AssignnewclassContext _localctx = new AssignnewclassContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_assignnewclass);
		try {
			setState(249);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(240);
				match(ID);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(241);
				match(ID);
				setState(242);
				match(SET);
				setState(243);
				callfunc();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(244);
				match(ID);
				setState(245);
				match(T__1);
				setState(246);
				callparamlist();
				setState(247);
				match(T__2);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclnewContext extends ParserRuleContext {
		public BasetypeContext basetype() {
			return getRuleContext(BasetypeContext.class,0);
		}
		public List<DeclarrayContext> declarray() {
			return getRuleContexts(DeclarrayContext.class);
		}
		public DeclarrayContext declarray(int i) {
			return getRuleContext(DeclarrayContext.class,i);
		}
		public List<DeclvarContext> declvar() {
			return getRuleContexts(DeclvarContext.class);
		}
		public DeclvarContext declvar(int i) {
			return getRuleContext(DeclvarContext.class,i);
		}
		public DeclnewContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declnew; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterDeclnew(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitDeclnew(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitDeclnew(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclnewContext declnew() throws RecognitionException {
		DeclnewContext _localctx = new DeclnewContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_declnew);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(251);
			basetype();
			setState(254);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				setState(252);
				declarray();
				}
				break;
			case 2:
				{
				setState(253);
				declvar();
				}
				break;
			}
			setState(263);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__7) {
				{
				{
				setState(256);
				match(T__7);
				setState(259);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
				case 1:
					{
					setState(257);
					declarray();
					}
					break;
				case 2:
					{
					setState(258);
					declvar();
					}
					break;
				}
				}
				}
				setState(265);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssignnewContext extends ParserRuleContext {
		public BasetypeContext basetype() {
			return getRuleContext(BasetypeContext.class,0);
		}
		public List<AssignvarContext> assignvar() {
			return getRuleContexts(AssignvarContext.class);
		}
		public AssignvarContext assignvar(int i) {
			return getRuleContext(AssignvarContext.class,i);
		}
		public List<AssignnewarrayContext> assignnewarray() {
			return getRuleContexts(AssignnewarrayContext.class);
		}
		public AssignnewarrayContext assignnewarray(int i) {
			return getRuleContext(AssignnewarrayContext.class,i);
		}
		public List<AssignnewclassContext> assignnewclass() {
			return getRuleContexts(AssignnewclassContext.class);
		}
		public AssignnewclassContext assignnewclass(int i) {
			return getRuleContext(AssignnewclassContext.class,i);
		}
		public ConstContext const_() {
			return getRuleContext(ConstContext.class,0);
		}
		public TerminalNode REF() { return getToken(CppParser.REF, 0); }
		public AssignnewContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignnew; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterAssignnew(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitAssignnew(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitAssignnew(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignnewContext assignnew() throws RecognitionException {
		AssignnewContext _localctx = new AssignnewContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_assignnew);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(267);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==CONST) {
				{
				setState(266);
				const_();
				}
			}

			setState(269);
			basetype();
			setState(271);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==REF) {
				{
				setState(270);
				match(REF);
				}
			}

			setState(276);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				{
				setState(273);
				assignvar();
				}
				break;
			case 2:
				{
				setState(274);
				assignnewarray();
				}
				break;
			case 3:
				{
				setState(275);
				assignnewclass();
				}
				break;
			}
			setState(286);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(278);
					match(T__7);
					setState(282);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
					case 1:
						{
						setState(279);
						assignvar();
						}
						break;
					case 2:
						{
						setState(280);
						assignnewarray();
						}
						break;
					case 3:
						{
						setState(281);
						assignnewclass();
						}
						break;
					}
					}
					} 
				}
				setState(288);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssignoldContext extends ParserRuleContext {
		public List<AssignvarContext> assignvar() {
			return getRuleContexts(AssignvarContext.class);
		}
		public AssignvarContext assignvar(int i) {
			return getRuleContext(AssignvarContext.class,i);
		}
		public List<AssignarrayelementContext> assignarrayelement() {
			return getRuleContexts(AssignarrayelementContext.class);
		}
		public AssignarrayelementContext assignarrayelement(int i) {
			return getRuleContext(AssignarrayelementContext.class,i);
		}
		public List<AssignclassvarContext> assignclassvar() {
			return getRuleContexts(AssignclassvarContext.class);
		}
		public AssignclassvarContext assignclassvar(int i) {
			return getRuleContext(AssignclassvarContext.class,i);
		}
		public List<AssignnewclassContext> assignnewclass() {
			return getRuleContexts(AssignnewclassContext.class);
		}
		public AssignnewclassContext assignnewclass(int i) {
			return getRuleContext(AssignnewclassContext.class,i);
		}
		public AssignoldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignold; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterAssignold(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitAssignold(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitAssignold(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignoldContext assignold() throws RecognitionException {
		AssignoldContext _localctx = new AssignoldContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_assignold);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(293);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				{
				setState(289);
				assignvar();
				}
				break;
			case 2:
				{
				setState(290);
				assignarrayelement();
				}
				break;
			case 3:
				{
				setState(291);
				assignclassvar();
				}
				break;
			case 4:
				{
				setState(292);
				assignnewclass();
				}
				break;
			}
			setState(304);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__7) {
				{
				{
				setState(295);
				match(T__7);
				setState(300);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
				case 1:
					{
					setState(296);
					assignvar();
					}
					break;
				case 2:
					{
					setState(297);
					assignarrayelement();
					}
					break;
				case 3:
					{
					setState(298);
					assignclassvar();
					}
					break;
				case 4:
					{
					setState(299);
					assignnewclass();
					}
					break;
				}
				}
				}
				setState(306);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ClassdefContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(CppParser.ID, 0); }
		public TerminalNode OPENSCOPE() { return getToken(CppParser.OPENSCOPE, 0); }
		public TerminalNode CLOSESCOPE() { return getToken(CppParser.CLOSESCOPE, 0); }
		public VererbungContext vererbung() {
			return getRuleContext(VererbungContext.class,0);
		}
		public List<ConstructorContext> constructor() {
			return getRuleContexts(ConstructorContext.class);
		}
		public ConstructorContext constructor(int i) {
			return getRuleContext(ConstructorContext.class,i);
		}
		public List<DestructContext> destruct() {
			return getRuleContexts(DestructContext.class);
		}
		public DestructContext destruct(int i) {
			return getRuleContext(DestructContext.class,i);
		}
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public ClassdefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classdef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterClassdef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitClassdef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitClassdef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassdefContext classdef() throws RecognitionException {
		ClassdefContext _localctx = new ClassdefContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_classdef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(307);
			match(T__9);
			setState(308);
			match(ID);
			setState(310);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__11) {
				{
				setState(309);
				vererbung();
				}
			}

			setState(312);
			match(OPENSCOPE);
			setState(313);
			match(T__10);
			setState(314);
			match(T__11);
			setState(320);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 624535019963392L) != 0)) {
				{
				setState(318);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
				case 1:
					{
					setState(315);
					constructor();
					}
					break;
				case 2:
					{
					setState(316);
					destruct();
					}
					break;
				case 3:
					{
					setState(317);
					stmt();
					}
					break;
				}
				}
				setState(322);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(323);
			match(CLOSESCOPE);
			setState(324);
			match(T__0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ConstructorContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(CppParser.ID, 0); }
		public DefparamlistContext defparamlist() {
			return getRuleContext(DefparamlistContext.class,0);
		}
		public OpenscopeContext openscope() {
			return getRuleContext(OpenscopeContext.class,0);
		}
		public ClosescopeContext closescope() {
			return getRuleContext(ClosescopeContext.class,0);
		}
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public List<InitlistContext> initlist() {
			return getRuleContexts(InitlistContext.class);
		}
		public InitlistContext initlist(int i) {
			return getRuleContext(InitlistContext.class,i);
		}
		public TerminalNode SET() { return getToken(CppParser.SET, 0); }
		public ConstructorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constructor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterConstructor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitConstructor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitConstructor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstructorContext constructor() throws RecognitionException {
		ConstructorContext _localctx = new ConstructorContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_constructor);
		int _la;
		try {
			setState(373);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(326);
				match(ID);
				setState(327);
				match(T__1);
				setState(328);
				defparamlist();
				setState(329);
				match(T__2);
				setState(330);
				openscope();
				setState(332);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2034108926755840L) != 0)) {
					{
					setState(331);
					body();
					}
				}

				setState(334);
				closescope();
				setState(336);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__0) {
					{
					setState(335);
					match(T__0);
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(338);
				match(ID);
				setState(339);
				match(T__1);
				setState(340);
				defparamlist();
				setState(341);
				match(T__2);
				setState(342);
				match(T__11);
				setState(351);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(343);
					initlist();
					setState(348);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__7) {
						{
						{
						setState(344);
						match(T__7);
						setState(345);
						initlist();
						}
						}
						setState(350);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(353);
				openscope();
				setState(355);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2034108926755840L) != 0)) {
					{
					setState(354);
					body();
					}
				}

				setState(357);
				closescope();
				setState(359);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__0) {
					{
					setState(358);
					match(T__0);
					}
				}

				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(361);
				match(ID);
				setState(362);
				match(T__1);
				setState(363);
				defparamlist();
				setState(364);
				match(T__2);
				setState(365);
				match(T__0);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(367);
				match(ID);
				setState(368);
				match(T__1);
				setState(369);
				match(T__2);
				setState(370);
				match(SET);
				setState(371);
				match(T__12);
				setState(372);
				match(T__0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DestructContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(CppParser.ID, 0); }
		public OpenscopeContext openscope() {
			return getRuleContext(OpenscopeContext.class,0);
		}
		public ClosescopeContext closescope() {
			return getRuleContext(ClosescopeContext.class,0);
		}
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public TerminalNode VIRTUAL() { return getToken(CppParser.VIRTUAL, 0); }
		public DestructContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_destruct; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterDestruct(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitDestruct(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitDestruct(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DestructContext destruct() throws RecognitionException {
		DestructContext _localctx = new DestructContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_destruct);
		int _la;
		try {
			setState(415);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(375);
				match(T__13);
				setState(376);
				match(ID);
				setState(377);
				match(T__1);
				setState(378);
				match(T__2);
				setState(379);
				openscope();
				setState(381);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2034108926755840L) != 0)) {
					{
					setState(380);
					body();
					}
				}

				setState(383);
				closescope();
				setState(385);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__0) {
					{
					setState(384);
					match(T__0);
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(387);
				match(T__13);
				setState(388);
				match(ID);
				setState(389);
				match(T__1);
				setState(390);
				match(T__2);
				setState(392);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__0) {
					{
					setState(391);
					match(T__0);
					}
				}

				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(394);
				match(VIRTUAL);
				setState(395);
				match(T__13);
				setState(396);
				match(ID);
				setState(397);
				match(T__1);
				setState(398);
				match(T__2);
				setState(400);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__0) {
					{
					setState(399);
					match(T__0);
					}
				}

				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(402);
				match(VIRTUAL);
				setState(403);
				match(T__13);
				setState(404);
				match(ID);
				setState(405);
				match(T__1);
				setState(406);
				match(T__2);
				setState(407);
				openscope();
				setState(409);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2034108926755840L) != 0)) {
					{
					setState(408);
					body();
					}
				}

				setState(411);
				closescope();
				setState(413);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__0) {
					{
					setState(412);
					match(T__0);
					}
				}

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InitlistContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(CppParser.ID, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public InitlistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_initlist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterInitlist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitInitlist(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitInitlist(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InitlistContext initlist() throws RecognitionException {
		InitlistContext _localctx = new InitlistContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_initlist);
		try {
			setState(429);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(417);
				match(ID);
				setState(418);
				match(T__1);
				setState(419);
				expr(0);
				setState(420);
				match(T__2);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(422);
				match(ID);
				setState(423);
				match(T__1);
				setState(424);
				expr(0);
				setState(425);
				match(T__7);
				setState(426);
				expr(0);
				setState(427);
				match(T__2);
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VererbungContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(CppParser.ID, 0); }
		public VererbungContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_vererbung; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterVererbung(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitVererbung(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitVererbung(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VererbungContext vererbung() throws RecognitionException {
		VererbungContext _localctx = new VererbungContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_vererbung);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(431);
			match(T__11);
			setState(433);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(432);
				match(T__10);
				}
			}

			setState(435);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AbstractfuncContext extends ParserRuleContext {
		public TerminalNode SET() { return getToken(CppParser.SET, 0); }
		public TerminalNode NUM() { return getToken(CppParser.NUM, 0); }
		public ConstContext const_() {
			return getRuleContext(ConstContext.class,0);
		}
		public AbstractfuncContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_abstractfunc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterAbstractfunc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitAbstractfunc(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitAbstractfunc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AbstractfuncContext abstractfunc() throws RecognitionException {
		AbstractfuncContext _localctx = new AbstractfuncContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_abstractfunc);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(438);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==CONST) {
				{
				setState(437);
				const_();
				}
			}

			setState(440);
			match(SET);
			setState(441);
			match(NUM);
			setState(443);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
			case 1:
				{
				setState(442);
				match(T__0);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclfuncContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(CppParser.ID, 0); }
		public DefparamlistContext defparamlist() {
			return getRuleContext(DefparamlistContext.class,0);
		}
		public BasetypeContext basetype() {
			return getRuleContext(BasetypeContext.class,0);
		}
		public TerminalNode VOID() { return getToken(CppParser.VOID, 0); }
		public TerminalNode VIRTUAL() { return getToken(CppParser.VIRTUAL, 0); }
		public AbstractfuncContext abstractfunc() {
			return getRuleContext(AbstractfuncContext.class,0);
		}
		public TerminalNode REF() { return getToken(CppParser.REF, 0); }
		public DeclfuncContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declfunc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterDeclfunc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitDeclfunc(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitDeclfunc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclfuncContext declfunc() throws RecognitionException {
		DeclfuncContext _localctx = new DeclfuncContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_declfunc);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(446);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==VIRTUAL) {
				{
				setState(445);
				match(VIRTUAL);
				}
			}

			setState(453);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TYPE:
			case ID:
				{
				setState(448);
				basetype();
				setState(450);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==REF) {
					{
					setState(449);
					match(REF);
					}
				}

				}
				break;
			case VOID:
				{
				setState(452);
				match(VOID);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(455);
			match(ID);
			setState(456);
			match(T__1);
			setState(457);
			defparamlist();
			setState(458);
			match(T__2);
			setState(460);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SET || _la==CONST) {
				{
				setState(459);
				abstractfunc();
				}
			}

			setState(463);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,51,_ctx) ) {
			case 1:
				{
				setState(462);
				match(T__0);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeffuncContext extends ParserRuleContext {
		public DeffuncContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_deffunc; }
	 
		public DeffuncContext() { }
		public void copyFrom(DeffuncContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FuncdefContext extends DeffuncContext {
		public TerminalNode ID() { return getToken(CppParser.ID, 0); }
		public DefparamlistContext defparamlist() {
			return getRuleContext(DefparamlistContext.class,0);
		}
		public OpenscopeContext openscope() {
			return getRuleContext(OpenscopeContext.class,0);
		}
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public ClosescopeContext closescope() {
			return getRuleContext(ClosescopeContext.class,0);
		}
		public BasetypeContext basetype() {
			return getRuleContext(BasetypeContext.class,0);
		}
		public TerminalNode VOID() { return getToken(CppParser.VOID, 0); }
		public TerminalNode VIRTUAL() { return getToken(CppParser.VIRTUAL, 0); }
		public TerminalNode OVERRIDE() { return getToken(CppParser.OVERRIDE, 0); }
		public TerminalNode REF() { return getToken(CppParser.REF, 0); }
		public ConstContext const_() {
			return getRuleContext(ConstContext.class,0);
		}
		public FuncdefContext(DeffuncContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterFuncdef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitFuncdef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitFuncdef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeffuncContext deffunc() throws RecognitionException {
		DeffuncContext _localctx = new DeffuncContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_deffunc);
		int _la;
		try {
			_localctx = new FuncdefContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(466);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==VIRTUAL) {
				{
				setState(465);
				match(VIRTUAL);
				}
			}

			setState(473);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TYPE:
			case ID:
				{
				setState(468);
				basetype();
				setState(470);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==REF) {
					{
					setState(469);
					match(REF);
					}
				}

				}
				break;
			case VOID:
				{
				setState(472);
				match(VOID);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(475);
			match(ID);
			setState(476);
			match(T__1);
			setState(477);
			defparamlist();
			setState(478);
			match(T__2);
			setState(483);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OVERRIDE || _la==CONST) {
				{
				setState(480);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CONST) {
					{
					setState(479);
					const_();
					}
				}

				setState(482);
				match(OVERRIDE);
				}
			}

			setState(485);
			openscope();
			setState(486);
			body();
			setState(487);
			closescope();
			setState(489);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(488);
				match(T__0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CallfuncContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(CppParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(CppParser.ID, i);
		}
		public CallparamlistContext callparamlist() {
			return getRuleContext(CallparamlistContext.class,0);
		}
		public CallfuncContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_callfunc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterCallfunc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitCallfunc(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitCallfunc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CallfuncContext callfunc() throws RecognitionException {
		CallfuncContext _localctx = new CallfuncContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_callfunc);
		try {
			setState(503);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,58,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(491);
				match(ID);
				setState(492);
				match(T__1);
				setState(493);
				callparamlist();
				setState(494);
				match(T__2);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(496);
				match(ID);
				setState(497);
				match(T__8);
				setState(498);
				match(ID);
				setState(499);
				match(T__1);
				setState(500);
				callparamlist();
				setState(501);
				match(T__2);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DefparamlistContext extends ParserRuleContext {
		public List<DefparamContext> defparam() {
			return getRuleContexts(DefparamContext.class);
		}
		public DefparamContext defparam(int i) {
			return getRuleContext(DefparamContext.class,i);
		}
		public DefparamlistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defparamlist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterDefparamlist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitDefparamlist(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitDefparamlist(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefparamlistContext defparamlist() throws RecognitionException {
		DefparamlistContext _localctx = new DefparamlistContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_defparamlist);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(514);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,60,_ctx) ) {
			case 1:
				{
				{
				setState(505);
				defparam();
				}
				setState(510);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__7) {
					{
					{
					setState(506);
					match(T__7);
					setState(507);
					defparam();
					}
					}
					setState(512);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				{
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DefparamContext extends ParserRuleContext {
		public AssignnewContext assignnew() {
			return getRuleContext(AssignnewContext.class,0);
		}
		public DeclContext decl() {
			return getRuleContext(DeclContext.class,0);
		}
		public DefrefvarContext defrefvar() {
			return getRuleContext(DefrefvarContext.class,0);
		}
		public DeclrefvarContext declrefvar() {
			return getRuleContext(DeclrefvarContext.class,0);
		}
		public ConstContext const_() {
			return getRuleContext(ConstContext.class,0);
		}
		public DefparamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defparam; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterDefparam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitDefparam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitDefparam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefparamContext defparam() throws RecognitionException {
		DefparamContext _localctx = new DefparamContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_defparam);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(524);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,62,_ctx) ) {
			case 1:
				{
				setState(516);
				assignnew();
				}
				break;
			case 2:
				{
				setState(518);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CONST) {
					{
					setState(517);
					const_();
					}
				}

				setState(520);
				decl();
				}
				break;
			case 3:
				{
				setState(521);
				defrefvar();
				}
				break;
			case 4:
				{
				setState(522);
				declrefvar();
				}
				break;
			case 5:
				{
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CallparamlistContext extends ParserRuleContext {
		public List<CallparamContext> callparam() {
			return getRuleContexts(CallparamContext.class);
		}
		public CallparamContext callparam(int i) {
			return getRuleContext(CallparamContext.class,i);
		}
		public CallparamlistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_callparamlist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterCallparamlist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitCallparamlist(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitCallparamlist(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CallparamlistContext callparamlist() throws RecognitionException {
		CallparamlistContext _localctx = new CallparamlistContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_callparamlist);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(535);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOL:
			case NUM:
			case ID:
			case CHAR:
				{
				{
				setState(526);
				callparam();
				}
				setState(531);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__7) {
					{
					{
					setState(527);
					match(T__7);
					setState(528);
					callparam();
					}
					}
					setState(533);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case T__2:
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CallparamContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public CallparamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_callparam; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterCallparam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitCallparam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitCallparam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CallparamContext callparam() throws RecognitionException {
		CallparamContext _localctx = new CallparamContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_callparam);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(537);
			expr(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BuiltinContext extends ParserRuleContext {
		public BuiltinContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_builtin; }
	 
		public BuiltinContext() { }
		public void copyFrom(BuiltinContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PrintcharContext extends BuiltinContext {
		public PrintcharContext(BuiltinContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterPrintchar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitPrintchar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitPrintchar(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PrintintContext extends BuiltinContext {
		public PrintintContext(BuiltinContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterPrintint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitPrintint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitPrintint(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PrintboolContext extends BuiltinContext {
		public PrintboolContext(BuiltinContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterPrintbool(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitPrintbool(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitPrintbool(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BuiltinContext builtin() throws RecognitionException {
		BuiltinContext _localctx = new BuiltinContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_builtin);
		try {
			setState(542);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__14:
				_localctx = new PrintboolContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(539);
				match(T__14);
				}
				break;
			case T__15:
				_localctx = new PrintintContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(540);
				match(T__15);
				}
				break;
			case T__16:
				_localctx = new PrintcharContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(541);
				match(T__16);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IfelseblockContext extends ParserRuleContext {
		public IfconnContext ifconn() {
			return getRuleContext(IfconnContext.class,0);
		}
		public IfblockContext ifblock() {
			return getRuleContext(IfblockContext.class,0);
		}
		public ElseblockContext elseblock() {
			return getRuleContext(ElseblockContext.class,0);
		}
		public IfelseblockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifelseblock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterIfelseblock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitIfelseblock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitIfelseblock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfelseblockContext ifelseblock() throws RecognitionException {
		IfelseblockContext _localctx = new IfelseblockContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_ifelseblock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(544);
			ifconn();
			setState(545);
			ifblock();
			setState(547);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(546);
				elseblock();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IfblockContext extends ParserRuleContext {
		public OpenscopeContext openscope() {
			return getRuleContext(OpenscopeContext.class,0);
		}
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public ClosescopeContext closescope() {
			return getRuleContext(ClosescopeContext.class,0);
		}
		public IfblockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifblock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterIfblock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitIfblock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitIfblock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfblockContext ifblock() throws RecognitionException {
		IfblockContext _localctx = new IfblockContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_ifblock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(549);
			openscope();
			setState(550);
			body();
			setState(551);
			closescope();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ElseblockContext extends ParserRuleContext {
		public TerminalNode ELSE() { return getToken(CppParser.ELSE, 0); }
		public IfelseblockContext ifelseblock() {
			return getRuleContext(IfelseblockContext.class,0);
		}
		public OpenscopeContext openscope() {
			return getRuleContext(OpenscopeContext.class,0);
		}
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public ClosescopeContext closescope() {
			return getRuleContext(ClosescopeContext.class,0);
		}
		public ElseblockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elseblock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterElseblock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitElseblock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitElseblock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElseblockContext elseblock() throws RecognitionException {
		ElseblockContext _localctx = new ElseblockContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_elseblock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(553);
			match(ELSE);
			setState(559);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IF:
				{
				setState(554);
				ifelseblock();
				}
				break;
			case OPENSCOPE:
				{
				setState(555);
				openscope();
				setState(556);
				body();
				setState(557);
				closescope();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IfconnContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(CppParser.IF, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public DeclContext decl() {
			return getRuleContext(DeclContext.class,0);
		}
		public AssignnewContext assignnew() {
			return getRuleContext(AssignnewContext.class,0);
		}
		public AssignoldContext assignold() {
			return getRuleContext(AssignoldContext.class,0);
		}
		public AssignclassvarContext assignclassvar() {
			return getRuleContext(AssignclassvarContext.class,0);
		}
		public List<ComContext> com() {
			return getRuleContexts(ComContext.class);
		}
		public ComContext com(int i) {
			return getRuleContext(ComContext.class,i);
		}
		public IfconnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifconn; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterIfconn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitIfconn(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitIfconn(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfconnContext ifconn() throws RecognitionException {
		IfconnContext _localctx = new IfconnContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_ifconn);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(561);
			match(IF);
			setState(562);
			match(T__1);
			setState(568);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,68,_ctx) ) {
			case 1:
				{
				setState(563);
				expr(0);
				}
				break;
			case 2:
				{
				setState(564);
				decl();
				}
				break;
			case 3:
				{
				setState(565);
				assignnew();
				}
				break;
			case 4:
				{
				setState(566);
				assignold();
				}
				break;
			case 5:
				{
				setState(567);
				assignclassvar();
				}
				break;
			}
			setState(575);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2164663517184L) != 0)) {
				{
				{
				setState(570);
				com();
				setState(571);
				expr(0);
				}
				}
				setState(577);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(578);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class WhileconnContext extends ParserRuleContext {
		public TerminalNode WHILE() { return getToken(CppParser.WHILE, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public DeclContext decl() {
			return getRuleContext(DeclContext.class,0);
		}
		public AssignnewContext assignnew() {
			return getRuleContext(AssignnewContext.class,0);
		}
		public AssignoldContext assignold() {
			return getRuleContext(AssignoldContext.class,0);
		}
		public AssignclassvarContext assignclassvar() {
			return getRuleContext(AssignclassvarContext.class,0);
		}
		public List<ComContext> com() {
			return getRuleContexts(ComContext.class);
		}
		public ComContext com(int i) {
			return getRuleContext(ComContext.class,i);
		}
		public WhileconnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileconn; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterWhileconn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitWhileconn(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitWhileconn(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhileconnContext whileconn() throws RecognitionException {
		WhileconnContext _localctx = new WhileconnContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_whileconn);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(580);
			match(WHILE);
			setState(581);
			match(T__1);
			setState(587);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,70,_ctx) ) {
			case 1:
				{
				setState(582);
				expr(0);
				}
				break;
			case 2:
				{
				setState(583);
				decl();
				}
				break;
			case 3:
				{
				setState(584);
				assignnew();
				}
				break;
			case 4:
				{
				setState(585);
				assignold();
				}
				break;
			case 5:
				{
				setState(586);
				assignclassvar();
				}
				break;
			}
			setState(594);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2164663517184L) != 0)) {
				{
				{
				setState(589);
				com();
				setState(590);
				expr(0);
				}
				}
				setState(596);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(597);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AddContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public AddContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterAdd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitAdd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitAdd(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SubContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public SubContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterSub(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitSub(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitSub(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CompareContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ComContext com() {
			return getRuleContext(ComContext.class,0);
		}
		public CompareContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterCompare(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitCompare(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitCompare(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DecContext extends ExprContext {
		public TerminalNode ID() { return getToken(CppParser.ID, 0); }
		public DecContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterDec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitDec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitDec(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BoolContext extends ExprContext {
		public TerminalNode BOOL() { return getToken(CppParser.BOOL, 0); }
		public BoolContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterBool(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitBool(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitBool(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MulContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public MulContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterMul(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitMul(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitMul(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MuleqContext extends ExprContext {
		public TerminalNode ID() { return getToken(CppParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public MuleqContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterMuleq(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitMuleq(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitMuleq(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SubeqContext extends ExprContext {
		public TerminalNode ID() { return getToken(CppParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public SubeqContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterSubeq(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitSubeq(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitSubeq(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NumContext extends ExprContext {
		public TerminalNode NUM() { return getToken(CppParser.NUM, 0); }
		public NumContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterNum(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitNum(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitNum(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AddeqContext extends ExprContext {
		public TerminalNode ID() { return getToken(CppParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AddeqContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterAddeq(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitAddeq(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitAddeq(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ClassvarContext extends ExprContext {
		public List<TerminalNode> ID() { return getTokens(CppParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(CppParser.ID, i);
		}
		public ClassvarContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterClassvar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitClassvar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitClassvar(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DiveqContext extends ExprContext {
		public TerminalNode ID() { return getToken(CppParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public DiveqContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterDiveq(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitDiveq(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitDiveq(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CallContext extends ExprContext {
		public CallfuncContext callfunc() {
			return getRuleContext(CallfuncContext.class,0);
		}
		public CallContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitCall(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DivContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public DivContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterDiv(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitDiv(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitDiv(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ArrayelemContext extends ExprContext {
		public TerminalNode ID() { return getToken(CppParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ArrayelemContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterArrayelem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitArrayelem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitArrayelem(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CharContext extends ExprContext {
		public TerminalNode CHAR() { return getToken(CppParser.CHAR, 0); }
		public CharContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterChar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitChar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitChar(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IdContext extends ExprContext {
		public TerminalNode ID() { return getToken(CppParser.ID, 0); }
		public IdContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitId(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitId(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ClassarrayelemContext extends ExprContext {
		public List<TerminalNode> ID() { return getTokens(CppParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(CppParser.ID, i);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ClassarrayelemContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterClassarrayelem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitClassarrayelem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitClassarrayelem(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IncContext extends ExprContext {
		public TerminalNode ID() { return getToken(CppParser.ID, 0); }
		public IncContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterInc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitInc(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitInc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 78;
		enterRecursionRule(_localctx, 78, RULE_expr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(636);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,72,_ctx) ) {
			case 1:
				{
				_localctx = new MuleqContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(600);
				match(ID);
				setState(601);
				match(T__18);
				setState(602);
				expr(18);
				}
				break;
			case 2:
				{
				_localctx = new DiveqContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(603);
				match(ID);
				setState(604);
				match(T__20);
				setState(605);
				expr(16);
				}
				break;
			case 3:
				{
				_localctx = new AddeqContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(606);
				match(ID);
				setState(607);
				match(T__22);
				setState(608);
				expr(14);
				}
				break;
			case 4:
				{
				_localctx = new IncContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(609);
				match(ID);
				setState(610);
				match(T__5);
				}
				break;
			case 5:
				{
				_localctx = new SubeqContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(611);
				match(ID);
				setState(612);
				match(T__24);
				setState(613);
				expr(11);
				}
				break;
			case 6:
				{
				_localctx = new DecContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(614);
				match(ID);
				setState(615);
				match(T__6);
				}
				break;
			case 7:
				{
				_localctx = new CallContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(616);
				callfunc();
				}
				break;
			case 8:
				{
				_localctx = new ClassarrayelemContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(617);
				match(ID);
				setState(618);
				match(T__3);
				setState(619);
				expr(0);
				setState(620);
				match(T__4);
				setState(621);
				match(T__8);
				setState(622);
				match(ID);
				}
				break;
			case 9:
				{
				_localctx = new ArrayelemContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(624);
				match(ID);
				setState(625);
				match(T__3);
				setState(626);
				expr(0);
				setState(627);
				match(T__4);
				}
				break;
			case 10:
				{
				_localctx = new ClassvarContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(629);
				match(ID);
				setState(630);
				match(T__8);
				setState(631);
				match(ID);
				}
				break;
			case 11:
				{
				_localctx = new NumContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(632);
				match(NUM);
				}
				break;
			case 12:
				{
				_localctx = new IdContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(633);
				match(ID);
				}
				break;
			case 13:
				{
				_localctx = new CharContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(634);
				match(CHAR);
				}
				break;
			case 14:
				{
				_localctx = new BoolContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(635);
				match(BOOL);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(656);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,74,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(654);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,73,_ctx) ) {
					case 1:
						{
						_localctx = new MulContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(638);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(639);
						match(T__17);
						setState(640);
						expr(20);
						}
						break;
					case 2:
						{
						_localctx = new DivContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(641);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(642);
						match(T__19);
						setState(643);
						expr(18);
						}
						break;
					case 3:
						{
						_localctx = new AddContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(644);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(645);
						match(T__21);
						setState(646);
						expr(16);
						}
						break;
					case 4:
						{
						_localctx = new SubContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(647);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(648);
						match(T__23);
						setState(649);
						expr(13);
						}
						break;
					case 5:
						{
						_localctx = new CompareContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(650);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(651);
						com();
						setState(652);
						expr(10);
						}
						break;
					}
					} 
				}
				setState(658);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,74,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ComContext extends ParserRuleContext {
		public TerminalNode EQUAL() { return getToken(CppParser.EQUAL, 0); }
		public TerminalNode NOTEQUAL() { return getToken(CppParser.NOTEQUAL, 0); }
		public TerminalNode LOWER() { return getToken(CppParser.LOWER, 0); }
		public TerminalNode LOWEREQUAL() { return getToken(CppParser.LOWEREQUAL, 0); }
		public TerminalNode GREATER() { return getToken(CppParser.GREATER, 0); }
		public TerminalNode GREATEREQUAL() { return getToken(CppParser.GREATEREQUAL, 0); }
		public ComContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_com; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterCom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitCom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitCom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComContext com() throws RecognitionException {
		ComContext _localctx = new ComContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_com);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(659);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 2164663517184L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ConstContext extends ParserRuleContext {
		public TerminalNode CONST() { return getToken(CppParser.CONST, 0); }
		public ConstContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_const; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).enterConst(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CppListener ) ((CppListener)listener).exitConst(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CppVisitor ) return ((CppVisitor<? extends T>)visitor).visitConst(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstContext const_() throws RecognitionException {
		ConstContext _localctx = new ConstContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_const);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(661);
			match(CONST);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 39:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 19);
		case 1:
			return precpred(_ctx, 17);
		case 2:
			return precpred(_ctx, 15);
		case 3:
			return precpred(_ctx, 12);
		case 4:
			return precpred(_ctx, 9);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u00015\u0298\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0002"+
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007\'\u0002"+
		"(\u0007(\u0002)\u0007)\u0001\u0000\u0005\u0000V\b\u0000\n\u0000\f\u0000"+
		"Y\t\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0004\u0001"+
		"}\b\u0001\u000b\u0001\f\u0001~\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0003\u0001\u0085\b\u0001\u0001\u0001\u0003\u0001\u0088\b\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0004\u0002\u008e\b\u0002"+
		"\u000b\u0002\f\u0002\u008f\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004"+
		"\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0003\b\u00a0\b\b\u0001\b"+
		"\u0001\b\u0001\b\u0001\b\u0001\t\u0003\t\u00a7\b\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003\n\u00b2\b\n\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0003\f\u00cb\b\f\u0001"+
		"\r\u0001\r\u0001\r\u0003\r\u00d0\b\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0005\r\u00d8\b\r\n\r\f\r\u00db\t\r\u0001\r\u0001\r\u0001\u000e"+
		"\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0003\u000f\u00ef\b\u000f\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0003\u0010\u00fa\b\u0010\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0003\u0011\u00ff\b\u0011\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0003\u0011\u0104\b\u0011\u0005\u0011\u0106\b\u0011\n\u0011\f\u0011\u0109"+
		"\t\u0011\u0001\u0012\u0003\u0012\u010c\b\u0012\u0001\u0012\u0001\u0012"+
		"\u0003\u0012\u0110\b\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0003\u0012"+
		"\u0115\b\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0003\u0012"+
		"\u011b\b\u0012\u0005\u0012\u011d\b\u0012\n\u0012\f\u0012\u0120\t\u0012"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0003\u0013\u0126\b\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0003\u0013"+
		"\u012d\b\u0013\u0005\u0013\u012f\b\u0013\n\u0013\f\u0013\u0132\t\u0013"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0003\u0014\u0137\b\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0005\u0014"+
		"\u013f\b\u0014\n\u0014\f\u0014\u0142\t\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0003\u0015\u014d\b\u0015\u0001\u0015\u0001\u0015\u0003\u0015\u0151"+
		"\b\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0005\u0015\u015b\b\u0015\n\u0015\f\u0015"+
		"\u015e\t\u0015\u0003\u0015\u0160\b\u0015\u0001\u0015\u0001\u0015\u0003"+
		"\u0015\u0164\b\u0015\u0001\u0015\u0001\u0015\u0003\u0015\u0168\b\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0003\u0015\u0176\b\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0003\u0016\u017e\b\u0016\u0001\u0016\u0001\u0016"+
		"\u0003\u0016\u0182\b\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0001\u0016\u0003\u0016\u0189\b\u0016\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0003\u0016\u0191\b\u0016\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0003\u0016\u019a\b\u0016\u0001\u0016\u0001\u0016\u0003\u0016\u019e\b"+
		"\u0016\u0003\u0016\u01a0\b\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0003\u0017\u01ae\b\u0017\u0001\u0018\u0001"+
		"\u0018\u0003\u0018\u01b2\b\u0018\u0001\u0018\u0001\u0018\u0001\u0019\u0003"+
		"\u0019\u01b7\b\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0003\u0019\u01bc"+
		"\b\u0019\u0001\u001a\u0003\u001a\u01bf\b\u001a\u0001\u001a\u0001\u001a"+
		"\u0003\u001a\u01c3\b\u001a\u0001\u001a\u0003\u001a\u01c6\b\u001a\u0001"+
		"\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0003\u001a\u01cd"+
		"\b\u001a\u0001\u001a\u0003\u001a\u01d0\b\u001a\u0001\u001b\u0003\u001b"+
		"\u01d3\b\u001b\u0001\u001b\u0001\u001b\u0003\u001b\u01d7\b\u001b\u0001"+
		"\u001b\u0003\u001b\u01da\b\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001"+
		"\u001b\u0001\u001b\u0003\u001b\u01e1\b\u001b\u0001\u001b\u0003\u001b\u01e4"+
		"\b\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0003\u001b\u01ea"+
		"\b\u001b\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001"+
		"\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001"+
		"\u001c\u0003\u001c\u01f8\b\u001c\u0001\u001d\u0001\u001d\u0001\u001d\u0005"+
		"\u001d\u01fd\b\u001d\n\u001d\f\u001d\u0200\t\u001d\u0001\u001d\u0003\u001d"+
		"\u0203\b\u001d\u0001\u001e\u0001\u001e\u0003\u001e\u0207\b\u001e\u0001"+
		"\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0003\u001e\u020d\b\u001e\u0001"+
		"\u001f\u0001\u001f\u0001\u001f\u0005\u001f\u0212\b\u001f\n\u001f\f\u001f"+
		"\u0215\t\u001f\u0001\u001f\u0003\u001f\u0218\b\u001f\u0001 \u0001 \u0001"+
		"!\u0001!\u0001!\u0003!\u021f\b!\u0001\"\u0001\"\u0001\"\u0003\"\u0224"+
		"\b\"\u0001#\u0001#\u0001#\u0001#\u0001$\u0001$\u0001$\u0001$\u0001$\u0001"+
		"$\u0003$\u0230\b$\u0001%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001%\u0003"+
		"%\u0239\b%\u0001%\u0001%\u0001%\u0005%\u023e\b%\n%\f%\u0241\t%\u0001%"+
		"\u0001%\u0001&\u0001&\u0001&\u0001&\u0001&\u0001&\u0001&\u0003&\u024c"+
		"\b&\u0001&\u0001&\u0001&\u0005&\u0251\b&\n&\f&\u0254\t&\u0001&\u0001&"+
		"\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001"+
		"\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001"+
		"\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001"+
		"\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001"+
		"\'\u0001\'\u0003\'\u027d\b\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001"+
		"\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001"+
		"\'\u0001\'\u0005\'\u028f\b\'\n\'\f\'\u0292\t\'\u0001(\u0001(\u0001)\u0001"+
		")\u0001)\u0000\u0001N*\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012"+
		"\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468:<>@BDFHJLNPR\u0000"+
		"\u0002\u0002\u0000--11\u0001\u0000#(\u02e8\u0000W\u0001\u0000\u0000\u0000"+
		"\u0002\u0087\u0001\u0000\u0000\u0000\u0004\u008d\u0001\u0000\u0000\u0000"+
		"\u0006\u0091\u0001\u0000\u0000\u0000\b\u0093\u0001\u0000\u0000\u0000\n"+
		"\u0095\u0001\u0000\u0000\u0000\f\u0097\u0001\u0000\u0000\u0000\u000e\u0099"+
		"\u0001\u0000\u0000\u0000\u0010\u009f\u0001\u0000\u0000\u0000\u0012\u00a6"+
		"\u0001\u0000\u0000\u0000\u0014\u00b1\u0001\u0000\u0000\u0000\u0016\u00b3"+
		"\u0001\u0000\u0000\u0000\u0018\u00ca\u0001\u0000\u0000\u0000\u001a\u00cc"+
		"\u0001\u0000\u0000\u0000\u001c\u00de\u0001\u0000\u0000\u0000\u001e\u00ee"+
		"\u0001\u0000\u0000\u0000 \u00f9\u0001\u0000\u0000\u0000\"\u00fb\u0001"+
		"\u0000\u0000\u0000$\u010b\u0001\u0000\u0000\u0000&\u0125\u0001\u0000\u0000"+
		"\u0000(\u0133\u0001\u0000\u0000\u0000*\u0175\u0001\u0000\u0000\u0000,"+
		"\u019f\u0001\u0000\u0000\u0000.\u01ad\u0001\u0000\u0000\u00000\u01af\u0001"+
		"\u0000\u0000\u00002\u01b6\u0001\u0000\u0000\u00004\u01be\u0001\u0000\u0000"+
		"\u00006\u01d2\u0001\u0000\u0000\u00008\u01f7\u0001\u0000\u0000\u0000:"+
		"\u0202\u0001\u0000\u0000\u0000<\u020c\u0001\u0000\u0000\u0000>\u0217\u0001"+
		"\u0000\u0000\u0000@\u0219\u0001\u0000\u0000\u0000B\u021e\u0001\u0000\u0000"+
		"\u0000D\u0220\u0001\u0000\u0000\u0000F\u0225\u0001\u0000\u0000\u0000H"+
		"\u0229\u0001\u0000\u0000\u0000J\u0231\u0001\u0000\u0000\u0000L\u0244\u0001"+
		"\u0000\u0000\u0000N\u027c\u0001\u0000\u0000\u0000P\u0293\u0001\u0000\u0000"+
		"\u0000R\u0295\u0001\u0000\u0000\u0000TV\u0003\u0002\u0001\u0000UT\u0001"+
		"\u0000\u0000\u0000VY\u0001\u0000\u0000\u0000WU\u0001\u0000\u0000\u0000"+
		"WX\u0001\u0000\u0000\u0000X\u0001\u0001\u0000\u0000\u0000YW\u0001\u0000"+
		"\u0000\u0000Z[\u00034\u001a\u0000[\\\u0005\u0001\u0000\u0000\\\u0088\u0001"+
		"\u0000\u0000\u0000]\u0088\u00036\u001b\u0000^_\u00038\u001c\u0000_`\u0005"+
		"\u0001\u0000\u0000`\u0088\u0001\u0000\u0000\u0000ab\u0003\"\u0011\u0000"+
		"bc\u0005\u0001\u0000\u0000c\u0088\u0001\u0000\u0000\u0000de\u0003$\u0012"+
		"\u0000ef\u0005\u0001\u0000\u0000f\u0088\u0001\u0000\u0000\u0000gh\u0003"+
		"&\u0013\u0000hi\u0005\u0001\u0000\u0000i\u0088\u0001\u0000\u0000\u0000"+
		"j\u0088\u0003D\"\u0000kl\u0003L&\u0000lm\u0003\u0006\u0003\u0000mn\u0003"+
		"\u0004\u0002\u0000no\u0003\b\u0004\u0000o\u0088\u0001\u0000\u0000\u0000"+
		"pq\u0003B!\u0000qr\u0005\u0002\u0000\u0000rs\u0003N\'\u0000st\u0005\u0003"+
		"\u0000\u0000tu\u0005\u0001\u0000\u0000u\u0088\u0001\u0000\u0000\u0000"+
		"v\u0088\u0003(\u0014\u0000w|\u0003\u0006\u0003\u0000x}\u0003\u0002\u0001"+
		"\u0000yz\u0003N\'\u0000z{\u0005\u0001\u0000\u0000{}\u0001\u0000\u0000"+
		"\u0000|x\u0001\u0000\u0000\u0000|y\u0001\u0000\u0000\u0000}~\u0001\u0000"+
		"\u0000\u0000~|\u0001\u0000\u0000\u0000~\u007f\u0001\u0000\u0000\u0000"+
		"\u007f\u0080\u0001\u0000\u0000\u0000\u0080\u0081\u0003\b\u0004\u0000\u0081"+
		"\u0088\u0001\u0000\u0000\u0000\u0082\u0084\u0005\u001e\u0000\u0000\u0083"+
		"\u0085\u0003N\'\u0000\u0084\u0083\u0001\u0000\u0000\u0000\u0084\u0085"+
		"\u0001\u0000\u0000\u0000\u0085\u0086\u0001\u0000\u0000\u0000\u0086\u0088"+
		"\u0005\u0001\u0000\u0000\u0087Z\u0001\u0000\u0000\u0000\u0087]\u0001\u0000"+
		"\u0000\u0000\u0087^\u0001\u0000\u0000\u0000\u0087a\u0001\u0000\u0000\u0000"+
		"\u0087d\u0001\u0000\u0000\u0000\u0087g\u0001\u0000\u0000\u0000\u0087j"+
		"\u0001\u0000\u0000\u0000\u0087k\u0001\u0000\u0000\u0000\u0087p\u0001\u0000"+
		"\u0000\u0000\u0087v\u0001\u0000\u0000\u0000\u0087w\u0001\u0000\u0000\u0000"+
		"\u0087\u0082\u0001\u0000\u0000\u0000\u0088\u0003\u0001\u0000\u0000\u0000"+
		"\u0089\u008e\u0003\u0002\u0001\u0000\u008a\u008b\u0003N\'\u0000\u008b"+
		"\u008c\u0005\u0001\u0000\u0000\u008c\u008e\u0001\u0000\u0000\u0000\u008d"+
		"\u0089\u0001\u0000\u0000\u0000\u008d\u008a\u0001\u0000\u0000\u0000\u008e"+
		"\u008f\u0001\u0000\u0000\u0000\u008f\u008d\u0001\u0000\u0000\u0000\u008f"+
		"\u0090\u0001\u0000\u0000\u0000\u0090\u0005\u0001\u0000\u0000\u0000\u0091"+
		"\u0092\u0005\u001a\u0000\u0000\u0092\u0007\u0001\u0000\u0000\u0000\u0093"+
		"\u0094\u0005\u001b\u0000\u0000\u0094\t\u0001\u0000\u0000\u0000\u0095\u0096"+
		"\u0007\u0000\u0000\u0000\u0096\u000b\u0001\u0000\u0000\u0000\u0097\u0098"+
		"\u00051\u0000\u0000\u0098\r\u0001\u0000\u0000\u0000\u0099\u009a\u0005"+
		"1\u0000\u0000\u009a\u009b\u0005\u0004\u0000\u0000\u009b\u009c\u0003N\'"+
		"\u0000\u009c\u009d\u0005\u0005\u0000\u0000\u009d\u000f\u0001\u0000\u0000"+
		"\u0000\u009e\u00a0\u0003R)\u0000\u009f\u009e\u0001\u0000\u0000\u0000\u009f"+
		"\u00a0\u0001\u0000\u0000\u0000\u00a0\u00a1\u0001\u0000\u0000\u0000\u00a1"+
		"\u00a2\u0003\n\u0005\u0000\u00a2\u00a3\u0005/\u0000\u0000\u00a3\u00a4"+
		"\u00051\u0000\u0000\u00a4\u0011\u0001\u0000\u0000\u0000\u00a5\u00a7\u0003"+
		"R)\u0000\u00a6\u00a5\u0001\u0000\u0000\u0000\u00a6\u00a7\u0001\u0000\u0000"+
		"\u0000\u00a7\u00a8\u0001\u0000\u0000\u0000\u00a8\u00a9\u0003\n\u0005\u0000"+
		"\u00a9\u00aa\u0005/\u0000\u0000\u00aa\u0013\u0001\u0000\u0000\u0000\u00ab"+
		"\u00ac\u0003\n\u0005\u0000\u00ac\u00ad\u0003\f\u0006\u0000\u00ad\u00b2"+
		"\u0001\u0000\u0000\u0000\u00ae\u00af\u0003\n\u0005\u0000\u00af\u00b0\u0003"+
		"\u000e\u0007\u0000\u00b0\u00b2\u0001\u0000\u0000\u0000\u00b1\u00ab\u0001"+
		"\u0000\u0000\u0000\u00b1\u00ae\u0001\u0000\u0000\u0000\u00b2\u0015\u0001"+
		"\u0000\u0000\u0000\u00b3\u00b4\u00051\u0000\u0000\u00b4\u00b5\u0005*\u0000"+
		"\u0000\u00b5\u00b6\u0003N\'\u0000\u00b6\u0017\u0001\u0000\u0000\u0000"+
		"\u00b7\u00b8\u00051\u0000\u0000\u00b8\u00b9\u0005\u0004\u0000\u0000\u00b9"+
		"\u00ba\u0003N\'\u0000\u00ba\u00bb\u0005\u0005\u0000\u0000\u00bb\u00bc"+
		"\u0005*\u0000\u0000\u00bc\u00bd\u0003N\'\u0000\u00bd\u00cb\u0001\u0000"+
		"\u0000\u0000\u00be\u00bf\u00051\u0000\u0000\u00bf\u00c0\u0005\u0004\u0000"+
		"\u0000\u00c0\u00c1\u0003N\'\u0000\u00c1\u00c2\u0005\u0005\u0000\u0000"+
		"\u00c2\u00c3\u0005\u0006\u0000\u0000\u00c3\u00cb\u0001\u0000\u0000\u0000"+
		"\u00c4\u00c5\u00051\u0000\u0000\u00c5\u00c6\u0005\u0004\u0000\u0000\u00c6"+
		"\u00c7\u0003N\'\u0000\u00c7\u00c8\u0005\u0005\u0000\u0000\u00c8\u00c9"+
		"\u0005\u0007\u0000\u0000\u00c9\u00cb\u0001\u0000\u0000\u0000\u00ca\u00b7"+
		"\u0001\u0000\u0000\u0000\u00ca\u00be\u0001\u0000\u0000\u0000\u00ca\u00c4"+
		"\u0001\u0000\u0000\u0000\u00cb\u0019\u0001\u0000\u0000\u0000\u00cc\u00cd"+
		"\u00051\u0000\u0000\u00cd\u00cf\u0005\u0004\u0000\u0000\u00ce\u00d0\u0003"+
		"\u001c\u000e\u0000\u00cf\u00ce\u0001\u0000\u0000\u0000\u00cf\u00d0\u0001"+
		"\u0000\u0000\u0000\u00d0\u00d1\u0001\u0000\u0000\u0000\u00d1\u00d2\u0005"+
		"\u0005\u0000\u0000\u00d2\u00d3\u0005*\u0000\u0000\u00d3\u00d4\u0005\u001a"+
		"\u0000\u0000\u00d4\u00d9\u0003N\'\u0000\u00d5\u00d6\u0005\b\u0000\u0000"+
		"\u00d6\u00d8\u0003N\'\u0000\u00d7\u00d5\u0001\u0000\u0000\u0000\u00d8"+
		"\u00db\u0001\u0000\u0000\u0000\u00d9\u00d7\u0001\u0000\u0000\u0000\u00d9"+
		"\u00da\u0001\u0000\u0000\u0000\u00da\u00dc\u0001\u0000\u0000\u0000\u00db"+
		"\u00d9\u0001\u0000\u0000\u0000\u00dc\u00dd\u0005\u001b\u0000\u0000\u00dd"+
		"\u001b\u0001\u0000\u0000\u0000\u00de\u00df\u0003N\'\u0000\u00df\u001d"+
		"\u0001\u0000\u0000\u0000\u00e0\u00e1\u00051\u0000\u0000\u00e1\u00e2\u0005"+
		"\t\u0000\u0000\u00e2\u00e3\u00051\u0000\u0000\u00e3\u00e4\u0005*\u0000"+
		"\u0000\u00e4\u00ef\u0003N\'\u0000\u00e5\u00e6\u00051\u0000\u0000\u00e6"+
		"\u00e7\u0005\u0004\u0000\u0000\u00e7\u00e8\u0003N\'\u0000\u00e8\u00e9"+
		"\u0005\u0005\u0000\u0000\u00e9\u00ea\u0005\t\u0000\u0000\u00ea\u00eb\u0005"+
		"1\u0000\u0000\u00eb\u00ec\u0005*\u0000\u0000\u00ec\u00ed\u0003N\'\u0000"+
		"\u00ed\u00ef\u0001\u0000\u0000\u0000\u00ee\u00e0\u0001\u0000\u0000\u0000"+
		"\u00ee\u00e5\u0001\u0000\u0000\u0000\u00ef\u001f\u0001\u0000\u0000\u0000"+
		"\u00f0\u00fa\u00051\u0000\u0000\u00f1\u00f2\u00051\u0000\u0000\u00f2\u00f3"+
		"\u0005*\u0000\u0000\u00f3\u00fa\u00038\u001c\u0000\u00f4\u00f5\u00051"+
		"\u0000\u0000\u00f5\u00f6\u0005\u0002\u0000\u0000\u00f6\u00f7\u0003>\u001f"+
		"\u0000\u00f7\u00f8\u0005\u0003\u0000\u0000\u00f8\u00fa\u0001\u0000\u0000"+
		"\u0000\u00f9\u00f0\u0001\u0000\u0000\u0000\u00f9\u00f1\u0001\u0000\u0000"+
		"\u0000\u00f9\u00f4\u0001\u0000\u0000\u0000\u00fa!\u0001\u0000\u0000\u0000"+
		"\u00fb\u00fe\u0003\n\u0005\u0000\u00fc\u00ff\u0003\u000e\u0007\u0000\u00fd"+
		"\u00ff\u0003\f\u0006\u0000\u00fe\u00fc\u0001\u0000\u0000\u0000\u00fe\u00fd"+
		"\u0001\u0000\u0000\u0000\u00ff\u0107\u0001\u0000\u0000\u0000\u0100\u0103"+
		"\u0005\b\u0000\u0000\u0101\u0104\u0003\u000e\u0007\u0000\u0102\u0104\u0003"+
		"\f\u0006\u0000\u0103\u0101\u0001\u0000\u0000\u0000\u0103\u0102\u0001\u0000"+
		"\u0000\u0000\u0104\u0106\u0001\u0000\u0000\u0000\u0105\u0100\u0001\u0000"+
		"\u0000\u0000\u0106\u0109\u0001\u0000\u0000\u0000\u0107\u0105\u0001\u0000"+
		"\u0000\u0000\u0107\u0108\u0001\u0000\u0000\u0000\u0108#\u0001\u0000\u0000"+
		"\u0000\u0109\u0107\u0001\u0000\u0000\u0000\u010a\u010c\u0003R)\u0000\u010b"+
		"\u010a\u0001\u0000\u0000\u0000\u010b\u010c\u0001\u0000\u0000\u0000\u010c"+
		"\u010d\u0001\u0000\u0000\u0000\u010d\u010f\u0003\n\u0005\u0000\u010e\u0110"+
		"\u0005/\u0000\u0000\u010f\u010e\u0001\u0000\u0000\u0000\u010f\u0110\u0001"+
		"\u0000\u0000\u0000\u0110\u0114\u0001\u0000\u0000\u0000\u0111\u0115\u0003"+
		"\u0016\u000b\u0000\u0112\u0115\u0003\u001a\r\u0000\u0113\u0115\u0003 "+
		"\u0010\u0000\u0114\u0111\u0001\u0000\u0000\u0000\u0114\u0112\u0001\u0000"+
		"\u0000\u0000\u0114\u0113\u0001\u0000\u0000\u0000\u0115\u011e\u0001\u0000"+
		"\u0000\u0000\u0116\u011a\u0005\b\u0000\u0000\u0117\u011b\u0003\u0016\u000b"+
		"\u0000\u0118\u011b\u0003\u001a\r\u0000\u0119\u011b\u0003 \u0010\u0000"+
		"\u011a\u0117\u0001\u0000\u0000\u0000\u011a\u0118\u0001\u0000\u0000\u0000"+
		"\u011a\u0119\u0001\u0000\u0000\u0000\u011b\u011d\u0001\u0000\u0000\u0000"+
		"\u011c\u0116\u0001\u0000\u0000\u0000\u011d\u0120\u0001\u0000\u0000\u0000"+
		"\u011e\u011c\u0001\u0000\u0000\u0000\u011e\u011f\u0001\u0000\u0000\u0000"+
		"\u011f%\u0001\u0000\u0000\u0000\u0120\u011e\u0001\u0000\u0000\u0000\u0121"+
		"\u0126\u0003\u0016\u000b\u0000\u0122\u0126\u0003\u0018\f\u0000\u0123\u0126"+
		"\u0003\u001e\u000f\u0000\u0124\u0126\u0003 \u0010\u0000\u0125\u0121\u0001"+
		"\u0000\u0000\u0000\u0125\u0122\u0001\u0000\u0000\u0000\u0125\u0123\u0001"+
		"\u0000\u0000\u0000\u0125\u0124\u0001\u0000\u0000\u0000\u0126\u0130\u0001"+
		"\u0000\u0000\u0000\u0127\u012c\u0005\b\u0000\u0000\u0128\u012d\u0003\u0016"+
		"\u000b\u0000\u0129\u012d\u0003\u0018\f\u0000\u012a\u012d\u0003\u001e\u000f"+
		"\u0000\u012b\u012d\u0003 \u0010\u0000\u012c\u0128\u0001\u0000\u0000\u0000"+
		"\u012c\u0129\u0001\u0000\u0000\u0000\u012c\u012a\u0001\u0000\u0000\u0000"+
		"\u012c\u012b\u0001\u0000\u0000\u0000\u012d\u012f\u0001\u0000\u0000\u0000"+
		"\u012e\u0127\u0001\u0000\u0000\u0000\u012f\u0132\u0001\u0000\u0000\u0000"+
		"\u0130\u012e\u0001\u0000\u0000\u0000\u0130\u0131\u0001\u0000\u0000\u0000"+
		"\u0131\'\u0001\u0000\u0000\u0000\u0132\u0130\u0001\u0000\u0000\u0000\u0133"+
		"\u0134\u0005\n\u0000\u0000\u0134\u0136\u00051\u0000\u0000\u0135\u0137"+
		"\u00030\u0018\u0000\u0136\u0135\u0001\u0000\u0000\u0000\u0136\u0137\u0001"+
		"\u0000\u0000\u0000\u0137\u0138\u0001\u0000\u0000\u0000\u0138\u0139\u0005"+
		"\u001a\u0000\u0000\u0139\u013a\u0005\u000b\u0000\u0000\u013a\u0140\u0005"+
		"\f\u0000\u0000\u013b\u013f\u0003*\u0015\u0000\u013c\u013f\u0003,\u0016"+
		"\u0000\u013d\u013f\u0003\u0002\u0001\u0000\u013e\u013b\u0001\u0000\u0000"+
		"\u0000\u013e\u013c\u0001\u0000\u0000\u0000\u013e\u013d\u0001\u0000\u0000"+
		"\u0000\u013f\u0142\u0001\u0000\u0000\u0000\u0140\u013e\u0001\u0000\u0000"+
		"\u0000\u0140\u0141\u0001\u0000\u0000\u0000\u0141\u0143\u0001\u0000\u0000"+
		"\u0000\u0142\u0140\u0001\u0000\u0000\u0000\u0143\u0144\u0005\u001b\u0000"+
		"\u0000\u0144\u0145\u0005\u0001\u0000\u0000\u0145)\u0001\u0000\u0000\u0000"+
		"\u0146\u0147\u00051\u0000\u0000\u0147\u0148\u0005\u0002\u0000\u0000\u0148"+
		"\u0149\u0003:\u001d\u0000\u0149\u014a\u0005\u0003\u0000\u0000\u014a\u014c"+
		"\u0003\u0006\u0003\u0000\u014b\u014d\u0003\u0004\u0002\u0000\u014c\u014b"+
		"\u0001\u0000\u0000\u0000\u014c\u014d\u0001\u0000\u0000\u0000\u014d\u014e"+
		"\u0001\u0000\u0000\u0000\u014e\u0150\u0003\b\u0004\u0000\u014f\u0151\u0005"+
		"\u0001\u0000\u0000\u0150\u014f\u0001\u0000\u0000\u0000\u0150\u0151\u0001"+
		"\u0000\u0000\u0000\u0151\u0176\u0001\u0000\u0000\u0000\u0152\u0153\u0005"+
		"1\u0000\u0000\u0153\u0154\u0005\u0002\u0000\u0000\u0154\u0155\u0003:\u001d"+
		"\u0000\u0155\u0156\u0005\u0003\u0000\u0000\u0156\u015f\u0005\f\u0000\u0000"+
		"\u0157\u015c\u0003.\u0017\u0000\u0158\u0159\u0005\b\u0000\u0000\u0159"+
		"\u015b\u0003.\u0017\u0000\u015a\u0158\u0001\u0000\u0000\u0000\u015b\u015e"+
		"\u0001\u0000\u0000\u0000\u015c\u015a\u0001\u0000\u0000\u0000\u015c\u015d"+
		"\u0001\u0000\u0000\u0000\u015d\u0160\u0001\u0000\u0000\u0000\u015e\u015c"+
		"\u0001\u0000\u0000\u0000\u015f\u0157\u0001\u0000\u0000\u0000\u015f\u0160"+
		"\u0001\u0000\u0000\u0000\u0160\u0161\u0001\u0000\u0000\u0000\u0161\u0163"+
		"\u0003\u0006\u0003\u0000\u0162\u0164\u0003\u0004\u0002\u0000\u0163\u0162"+
		"\u0001\u0000\u0000\u0000\u0163\u0164\u0001\u0000\u0000\u0000\u0164\u0165"+
		"\u0001\u0000\u0000\u0000\u0165\u0167\u0003\b\u0004\u0000\u0166\u0168\u0005"+
		"\u0001\u0000\u0000\u0167\u0166\u0001\u0000\u0000\u0000\u0167\u0168\u0001"+
		"\u0000\u0000\u0000\u0168\u0176\u0001\u0000\u0000\u0000\u0169\u016a\u0005"+
		"1\u0000\u0000\u016a\u016b\u0005\u0002\u0000\u0000\u016b\u016c\u0003:\u001d"+
		"\u0000\u016c\u016d\u0005\u0003\u0000\u0000\u016d\u016e\u0005\u0001\u0000"+
		"\u0000\u016e\u0176\u0001\u0000\u0000\u0000\u016f\u0170\u00051\u0000\u0000"+
		"\u0170\u0171\u0005\u0002\u0000\u0000\u0171\u0172\u0005\u0003\u0000\u0000"+
		"\u0172\u0173\u0005*\u0000\u0000\u0173\u0174\u0005\r\u0000\u0000\u0174"+
		"\u0176\u0005\u0001\u0000\u0000\u0175\u0146\u0001\u0000\u0000\u0000\u0175"+
		"\u0152\u0001\u0000\u0000\u0000\u0175\u0169\u0001\u0000\u0000\u0000\u0175"+
		"\u016f\u0001\u0000\u0000\u0000\u0176+\u0001\u0000\u0000\u0000\u0177\u0178"+
		"\u0005\u000e\u0000\u0000\u0178\u0179\u00051\u0000\u0000\u0179\u017a\u0005"+
		"\u0002\u0000\u0000\u017a\u017b\u0005\u0003\u0000\u0000\u017b\u017d\u0003"+
		"\u0006\u0003\u0000\u017c\u017e\u0003\u0004\u0002\u0000\u017d\u017c\u0001"+
		"\u0000\u0000\u0000\u017d\u017e\u0001\u0000\u0000\u0000\u017e\u017f\u0001"+
		"\u0000\u0000\u0000\u017f\u0181\u0003\b\u0004\u0000\u0180\u0182\u0005\u0001"+
		"\u0000\u0000\u0181\u0180\u0001\u0000\u0000\u0000\u0181\u0182\u0001\u0000"+
		"\u0000\u0000\u0182\u01a0\u0001\u0000\u0000\u0000\u0183\u0184\u0005\u000e"+
		"\u0000\u0000\u0184\u0185\u00051\u0000\u0000\u0185\u0186\u0005\u0002\u0000"+
		"\u0000\u0186\u0188\u0005\u0003\u0000\u0000\u0187\u0189\u0005\u0001\u0000"+
		"\u0000\u0188\u0187\u0001\u0000\u0000\u0000\u0188\u0189\u0001\u0000\u0000"+
		"\u0000\u0189\u01a0\u0001\u0000\u0000\u0000\u018a\u018b\u0005\u001d\u0000"+
		"\u0000\u018b\u018c\u0005\u000e\u0000\u0000\u018c\u018d\u00051\u0000\u0000"+
		"\u018d\u018e\u0005\u0002\u0000\u0000\u018e\u0190\u0005\u0003\u0000\u0000"+
		"\u018f\u0191\u0005\u0001\u0000\u0000\u0190\u018f\u0001\u0000\u0000\u0000"+
		"\u0190\u0191\u0001\u0000\u0000\u0000\u0191\u01a0\u0001\u0000\u0000\u0000"+
		"\u0192\u0193\u0005\u001d\u0000\u0000\u0193\u0194\u0005\u000e\u0000\u0000"+
		"\u0194\u0195\u00051\u0000\u0000\u0195\u0196\u0005\u0002\u0000\u0000\u0196"+
		"\u0197\u0005\u0003\u0000\u0000\u0197\u0199\u0003\u0006\u0003\u0000\u0198"+
		"\u019a\u0003\u0004\u0002\u0000\u0199\u0198\u0001\u0000\u0000\u0000\u0199"+
		"\u019a\u0001\u0000\u0000\u0000\u019a\u019b\u0001\u0000\u0000\u0000\u019b"+
		"\u019d\u0003\b\u0004\u0000\u019c\u019e\u0005\u0001\u0000\u0000\u019d\u019c"+
		"\u0001\u0000\u0000\u0000\u019d\u019e\u0001\u0000\u0000\u0000\u019e\u01a0"+
		"\u0001\u0000\u0000\u0000\u019f\u0177\u0001\u0000\u0000\u0000\u019f\u0183"+
		"\u0001\u0000\u0000\u0000\u019f\u018a\u0001\u0000\u0000\u0000\u019f\u0192"+
		"\u0001\u0000\u0000\u0000\u01a0-\u0001\u0000\u0000\u0000\u01a1\u01a2\u0005"+
		"1\u0000\u0000\u01a2\u01a3\u0005\u0002\u0000\u0000\u01a3\u01a4\u0003N\'"+
		"\u0000\u01a4\u01a5\u0005\u0003\u0000\u0000\u01a5\u01ae\u0001\u0000\u0000"+
		"\u0000\u01a6\u01a7\u00051\u0000\u0000\u01a7\u01a8\u0005\u0002\u0000\u0000"+
		"\u01a8\u01a9\u0003N\'\u0000\u01a9\u01aa\u0005\b\u0000\u0000\u01aa\u01ab"+
		"\u0003N\'\u0000\u01ab\u01ac\u0005\u0003\u0000\u0000\u01ac\u01ae\u0001"+
		"\u0000\u0000\u0000\u01ad\u01a1\u0001\u0000\u0000\u0000\u01ad\u01a6\u0001"+
		"\u0000\u0000\u0000\u01ae/\u0001\u0000\u0000\u0000\u01af\u01b1\u0005\f"+
		"\u0000\u0000\u01b0\u01b2\u0005\u000b\u0000\u0000\u01b1\u01b0\u0001\u0000"+
		"\u0000\u0000\u01b1\u01b2\u0001\u0000\u0000\u0000\u01b2\u01b3\u0001\u0000"+
		"\u0000\u0000\u01b3\u01b4\u00051\u0000\u0000\u01b41\u0001\u0000\u0000\u0000"+
		"\u01b5\u01b7\u0003R)\u0000\u01b6\u01b5\u0001\u0000\u0000\u0000\u01b6\u01b7"+
		"\u0001\u0000\u0000\u0000\u01b7\u01b8\u0001\u0000\u0000\u0000\u01b8\u01b9"+
		"\u0005*\u0000\u0000\u01b9\u01bb\u00050\u0000\u0000\u01ba\u01bc\u0005\u0001"+
		"\u0000\u0000\u01bb\u01ba\u0001\u0000\u0000\u0000\u01bb\u01bc\u0001\u0000"+
		"\u0000\u0000\u01bc3\u0001\u0000\u0000\u0000\u01bd\u01bf\u0005\u001d\u0000"+
		"\u0000\u01be\u01bd\u0001\u0000\u0000\u0000\u01be\u01bf\u0001\u0000\u0000"+
		"\u0000\u01bf\u01c5\u0001\u0000\u0000\u0000\u01c0\u01c2\u0003\n\u0005\u0000"+
		"\u01c1\u01c3\u0005/\u0000\u0000\u01c2\u01c1\u0001\u0000\u0000\u0000\u01c2"+
		"\u01c3\u0001\u0000\u0000\u0000\u01c3\u01c6\u0001\u0000\u0000\u0000\u01c4"+
		"\u01c6\u0005,\u0000\u0000\u01c5\u01c0\u0001\u0000\u0000\u0000\u01c5\u01c4"+
		"\u0001\u0000\u0000\u0000\u01c6\u01c7\u0001\u0000\u0000\u0000\u01c7\u01c8"+
		"\u00051\u0000\u0000\u01c8\u01c9\u0005\u0002\u0000\u0000\u01c9\u01ca\u0003"+
		":\u001d\u0000\u01ca\u01cc\u0005\u0003\u0000\u0000\u01cb\u01cd\u00032\u0019"+
		"\u0000\u01cc\u01cb\u0001\u0000\u0000\u0000\u01cc\u01cd\u0001\u0000\u0000"+
		"\u0000\u01cd\u01cf\u0001\u0000\u0000\u0000\u01ce\u01d0\u0005\u0001\u0000"+
		"\u0000\u01cf\u01ce\u0001\u0000\u0000\u0000\u01cf\u01d0\u0001\u0000\u0000"+
		"\u0000\u01d05\u0001\u0000\u0000\u0000\u01d1\u01d3\u0005\u001d\u0000\u0000"+
		"\u01d2\u01d1\u0001\u0000\u0000\u0000\u01d2\u01d3\u0001\u0000\u0000\u0000"+
		"\u01d3\u01d9\u0001\u0000\u0000\u0000\u01d4\u01d6\u0003\n\u0005\u0000\u01d5"+
		"\u01d7\u0005/\u0000\u0000\u01d6\u01d5\u0001\u0000\u0000\u0000\u01d6\u01d7"+
		"\u0001\u0000\u0000\u0000\u01d7\u01da\u0001\u0000\u0000\u0000\u01d8\u01da"+
		"\u0005,\u0000\u0000\u01d9\u01d4\u0001\u0000\u0000\u0000\u01d9\u01d8\u0001"+
		"\u0000\u0000\u0000\u01da\u01db\u0001\u0000\u0000\u0000\u01db\u01dc\u0005"+
		"1\u0000\u0000\u01dc\u01dd\u0005\u0002\u0000\u0000\u01dd\u01de\u0003:\u001d"+
		"\u0000\u01de\u01e3\u0005\u0003\u0000\u0000\u01df\u01e1\u0003R)\u0000\u01e0"+
		"\u01df\u0001\u0000\u0000\u0000\u01e0\u01e1\u0001\u0000\u0000\u0000\u01e1"+
		"\u01e2\u0001\u0000\u0000\u0000\u01e2\u01e4\u0005\u001c\u0000\u0000\u01e3"+
		"\u01e0\u0001\u0000\u0000\u0000\u01e3\u01e4\u0001\u0000\u0000\u0000\u01e4"+
		"\u01e5\u0001\u0000\u0000\u0000\u01e5\u01e6\u0003\u0006\u0003\u0000\u01e6"+
		"\u01e7\u0003\u0004\u0002\u0000\u01e7\u01e9\u0003\b\u0004\u0000\u01e8\u01ea"+
		"\u0005\u0001\u0000\u0000\u01e9\u01e8\u0001\u0000\u0000\u0000\u01e9\u01ea"+
		"\u0001\u0000\u0000\u0000\u01ea7\u0001\u0000\u0000\u0000\u01eb\u01ec\u0005"+
		"1\u0000\u0000\u01ec\u01ed\u0005\u0002\u0000\u0000\u01ed\u01ee\u0003>\u001f"+
		"\u0000\u01ee\u01ef\u0005\u0003\u0000\u0000\u01ef\u01f8\u0001\u0000\u0000"+
		"\u0000\u01f0\u01f1\u00051\u0000\u0000\u01f1\u01f2\u0005\t\u0000\u0000"+
		"\u01f2\u01f3\u00051\u0000\u0000\u01f3\u01f4\u0005\u0002\u0000\u0000\u01f4"+
		"\u01f5\u0003>\u001f\u0000\u01f5\u01f6\u0005\u0003\u0000\u0000\u01f6\u01f8"+
		"\u0001\u0000\u0000\u0000\u01f7\u01eb\u0001\u0000\u0000\u0000\u01f7\u01f0"+
		"\u0001\u0000\u0000\u0000\u01f89\u0001\u0000\u0000\u0000\u01f9\u01fe\u0003"+
		"<\u001e\u0000\u01fa\u01fb\u0005\b\u0000\u0000\u01fb\u01fd\u0003<\u001e"+
		"\u0000\u01fc\u01fa\u0001\u0000\u0000\u0000\u01fd\u0200\u0001\u0000\u0000"+
		"\u0000\u01fe\u01fc\u0001\u0000\u0000\u0000\u01fe\u01ff\u0001\u0000\u0000"+
		"\u0000\u01ff\u0203\u0001\u0000\u0000\u0000\u0200\u01fe\u0001\u0000\u0000"+
		"\u0000\u0201\u0203\u0001\u0000\u0000\u0000\u0202\u01f9\u0001\u0000\u0000"+
		"\u0000\u0202\u0201\u0001\u0000\u0000\u0000\u0203;\u0001\u0000\u0000\u0000"+
		"\u0204\u020d\u0003$\u0012\u0000\u0205\u0207\u0003R)\u0000\u0206\u0205"+
		"\u0001\u0000\u0000\u0000\u0206\u0207\u0001\u0000\u0000\u0000\u0207\u0208"+
		"\u0001\u0000\u0000\u0000\u0208\u020d\u0003\u0014\n\u0000\u0209\u020d\u0003"+
		"\u0010\b\u0000\u020a\u020d\u0003\u0012\t\u0000\u020b\u020d\u0001\u0000"+
		"\u0000\u0000\u020c\u0204\u0001\u0000\u0000\u0000\u020c\u0206\u0001\u0000"+
		"\u0000\u0000\u020c\u0209\u0001\u0000\u0000\u0000\u020c\u020a\u0001\u0000"+
		"\u0000\u0000\u020c\u020b\u0001\u0000\u0000\u0000\u020d=\u0001\u0000\u0000"+
		"\u0000\u020e\u0213\u0003@ \u0000\u020f\u0210\u0005\b\u0000\u0000\u0210"+
		"\u0212\u0003@ \u0000\u0211\u020f\u0001\u0000\u0000\u0000\u0212\u0215\u0001"+
		"\u0000\u0000\u0000\u0213\u0211\u0001\u0000\u0000\u0000\u0213\u0214\u0001"+
		"\u0000\u0000\u0000\u0214\u0218\u0001\u0000\u0000\u0000\u0215\u0213\u0001"+
		"\u0000\u0000\u0000\u0216\u0218\u0001\u0000\u0000\u0000\u0217\u020e\u0001"+
		"\u0000\u0000\u0000\u0217\u0216\u0001\u0000\u0000\u0000\u0218?\u0001\u0000"+
		"\u0000\u0000\u0219\u021a\u0003N\'\u0000\u021aA\u0001\u0000\u0000\u0000"+
		"\u021b\u021f\u0005\u000f\u0000\u0000\u021c\u021f\u0005\u0010\u0000\u0000"+
		"\u021d\u021f\u0005\u0011\u0000\u0000\u021e\u021b\u0001\u0000\u0000\u0000"+
		"\u021e\u021c\u0001\u0000\u0000\u0000\u021e\u021d\u0001\u0000\u0000\u0000"+
		"\u021fC\u0001\u0000\u0000\u0000\u0220\u0221\u0003J%\u0000\u0221\u0223"+
		"\u0003F#\u0000\u0222\u0224\u0003H$\u0000\u0223\u0222\u0001\u0000\u0000"+
		"\u0000\u0223\u0224\u0001\u0000\u0000\u0000\u0224E\u0001\u0000\u0000\u0000"+
		"\u0225\u0226\u0003\u0006\u0003\u0000\u0226\u0227\u0003\u0004\u0002\u0000"+
		"\u0227\u0228\u0003\b\u0004\u0000\u0228G\u0001\u0000\u0000\u0000\u0229"+
		"\u022f\u0005 \u0000\u0000\u022a\u0230\u0003D\"\u0000\u022b\u022c\u0003"+
		"\u0006\u0003\u0000\u022c\u022d\u0003\u0004\u0002\u0000\u022d\u022e\u0003"+
		"\b\u0004\u0000\u022e\u0230\u0001\u0000\u0000\u0000\u022f\u022a\u0001\u0000"+
		"\u0000\u0000\u022f\u022b\u0001\u0000\u0000\u0000\u0230I\u0001\u0000\u0000"+
		"\u0000\u0231\u0232\u0005\u001f\u0000\u0000\u0232\u0238\u0005\u0002\u0000"+
		"\u0000\u0233\u0239\u0003N\'\u0000\u0234\u0239\u0003\u0014\n\u0000\u0235"+
		"\u0239\u0003$\u0012\u0000\u0236\u0239\u0003&\u0013\u0000\u0237\u0239\u0003"+
		"\u001e\u000f\u0000\u0238\u0233\u0001\u0000\u0000\u0000\u0238\u0234\u0001"+
		"\u0000\u0000\u0000\u0238\u0235\u0001\u0000\u0000\u0000\u0238\u0236\u0001"+
		"\u0000\u0000\u0000\u0238\u0237\u0001\u0000\u0000\u0000\u0239\u023f\u0001"+
		"\u0000\u0000\u0000\u023a\u023b\u0003P(\u0000\u023b\u023c\u0003N\'\u0000"+
		"\u023c\u023e\u0001\u0000\u0000\u0000\u023d\u023a\u0001\u0000\u0000\u0000"+
		"\u023e\u0241\u0001\u0000\u0000\u0000\u023f\u023d\u0001\u0000\u0000\u0000"+
		"\u023f\u0240\u0001\u0000\u0000\u0000\u0240\u0242\u0001\u0000\u0000\u0000"+
		"\u0241\u023f\u0001\u0000\u0000\u0000\u0242\u0243\u0005\u0003\u0000\u0000"+
		"\u0243K\u0001\u0000\u0000\u0000\u0244\u0245\u0005!\u0000\u0000\u0245\u024b"+
		"\u0005\u0002\u0000\u0000\u0246\u024c\u0003N\'\u0000\u0247\u024c\u0003"+
		"\u0014\n\u0000\u0248\u024c\u0003$\u0012\u0000\u0249\u024c\u0003&\u0013"+
		"\u0000\u024a\u024c\u0003\u001e\u000f\u0000\u024b\u0246\u0001\u0000\u0000"+
		"\u0000\u024b\u0247\u0001\u0000\u0000\u0000\u024b\u0248\u0001\u0000\u0000"+
		"\u0000\u024b\u0249\u0001\u0000\u0000\u0000\u024b\u024a\u0001\u0000\u0000"+
		"\u0000\u024c\u0252\u0001\u0000\u0000\u0000\u024d\u024e\u0003P(\u0000\u024e"+
		"\u024f\u0003N\'\u0000\u024f\u0251\u0001\u0000\u0000\u0000\u0250\u024d"+
		"\u0001\u0000\u0000\u0000\u0251\u0254\u0001\u0000\u0000\u0000\u0252\u0250"+
		"\u0001\u0000\u0000\u0000\u0252\u0253\u0001\u0000\u0000\u0000\u0253\u0255"+
		"\u0001\u0000\u0000\u0000\u0254\u0252\u0001\u0000\u0000\u0000\u0255\u0256"+
		"\u0005\u0003\u0000\u0000\u0256M\u0001\u0000\u0000\u0000\u0257\u0258\u0006"+
		"\'\uffff\uffff\u0000\u0258\u0259\u00051\u0000\u0000\u0259\u025a\u0005"+
		"\u0013\u0000\u0000\u025a\u027d\u0003N\'\u0012\u025b\u025c\u00051\u0000"+
		"\u0000\u025c\u025d\u0005\u0015\u0000\u0000\u025d\u027d\u0003N\'\u0010"+
		"\u025e\u025f\u00051\u0000\u0000\u025f\u0260\u0005\u0017\u0000\u0000\u0260"+
		"\u027d\u0003N\'\u000e\u0261\u0262\u00051\u0000\u0000\u0262\u027d\u0005"+
		"\u0006\u0000\u0000\u0263\u0264\u00051\u0000\u0000\u0264\u0265\u0005\u0019"+
		"\u0000\u0000\u0265\u027d\u0003N\'\u000b\u0266\u0267\u00051\u0000\u0000"+
		"\u0267\u027d\u0005\u0007\u0000\u0000\u0268\u027d\u00038\u001c\u0000\u0269"+
		"\u026a\u00051\u0000\u0000\u026a\u026b\u0005\u0004\u0000\u0000\u026b\u026c"+
		"\u0003N\'\u0000\u026c\u026d\u0005\u0005\u0000\u0000\u026d\u026e\u0005"+
		"\t\u0000\u0000\u026e\u026f\u00051\u0000\u0000\u026f\u027d\u0001\u0000"+
		"\u0000\u0000\u0270\u0271\u00051\u0000\u0000\u0271\u0272\u0005\u0004\u0000"+
		"\u0000\u0272\u0273\u0003N\'\u0000\u0273\u0274\u0005\u0005\u0000\u0000"+
		"\u0274\u027d\u0001\u0000\u0000\u0000\u0275\u0276\u00051\u0000\u0000\u0276"+
		"\u0277\u0005\t\u0000\u0000\u0277\u027d\u00051\u0000\u0000\u0278\u027d"+
		"\u00050\u0000\u0000\u0279\u027d\u00051\u0000\u0000\u027a\u027d\u00052"+
		"\u0000\u0000\u027b\u027d\u0005)\u0000\u0000\u027c\u0257\u0001\u0000\u0000"+
		"\u0000\u027c\u025b\u0001\u0000\u0000\u0000\u027c\u025e\u0001\u0000\u0000"+
		"\u0000\u027c\u0261\u0001\u0000\u0000\u0000\u027c\u0263\u0001\u0000\u0000"+
		"\u0000\u027c\u0266\u0001\u0000\u0000\u0000\u027c\u0268\u0001\u0000\u0000"+
		"\u0000\u027c\u0269\u0001\u0000\u0000\u0000\u027c\u0270\u0001\u0000\u0000"+
		"\u0000\u027c\u0275\u0001\u0000\u0000\u0000\u027c\u0278\u0001\u0000\u0000"+
		"\u0000\u027c\u0279\u0001\u0000\u0000\u0000\u027c\u027a\u0001\u0000\u0000"+
		"\u0000\u027c\u027b\u0001\u0000\u0000\u0000\u027d\u0290\u0001\u0000\u0000"+
		"\u0000\u027e\u027f\n\u0013\u0000\u0000\u027f\u0280\u0005\u0012\u0000\u0000"+
		"\u0280\u028f\u0003N\'\u0014\u0281\u0282\n\u0011\u0000\u0000\u0282\u0283"+
		"\u0005\u0014\u0000\u0000\u0283\u028f\u0003N\'\u0012\u0284\u0285\n\u000f"+
		"\u0000\u0000\u0285\u0286\u0005\u0016\u0000\u0000\u0286\u028f\u0003N\'"+
		"\u0010\u0287\u0288\n\f\u0000\u0000\u0288\u0289\u0005\u0018\u0000\u0000"+
		"\u0289\u028f\u0003N\'\r\u028a\u028b\n\t\u0000\u0000\u028b\u028c\u0003"+
		"P(\u0000\u028c\u028d\u0003N\'\n\u028d\u028f\u0001\u0000\u0000\u0000\u028e"+
		"\u027e\u0001\u0000\u0000\u0000\u028e\u0281\u0001\u0000\u0000\u0000\u028e"+
		"\u0284\u0001\u0000\u0000\u0000\u028e\u0287\u0001\u0000\u0000\u0000\u028e"+
		"\u028a\u0001\u0000\u0000\u0000\u028f\u0292\u0001\u0000\u0000\u0000\u0290"+
		"\u028e\u0001\u0000\u0000\u0000\u0290\u0291\u0001\u0000\u0000\u0000\u0291"+
		"O\u0001\u0000\u0000\u0000\u0292\u0290\u0001\u0000\u0000\u0000\u0293\u0294"+
		"\u0007\u0001\u0000\u0000\u0294Q\u0001\u0000\u0000\u0000\u0295\u0296\u0005"+
		"+\u0000\u0000\u0296S\u0001\u0000\u0000\u0000KW|~\u0084\u0087\u008d\u008f"+
		"\u009f\u00a6\u00b1\u00ca\u00cf\u00d9\u00ee\u00f9\u00fe\u0103\u0107\u010b"+
		"\u010f\u0114\u011a\u011e\u0125\u012c\u0130\u0136\u013e\u0140\u014c\u0150"+
		"\u015c\u015f\u0163\u0167\u0175\u017d\u0181\u0188\u0190\u0199\u019d\u019f"+
		"\u01ad\u01b1\u01b6\u01bb\u01be\u01c2\u01c5\u01cc\u01cf\u01d2\u01d6\u01d9"+
		"\u01e0\u01e3\u01e9\u01f7\u01fe\u0202\u0206\u020c\u0213\u0217\u021e\u0223"+
		"\u022f\u0238\u023f\u024b\u0252\u027c\u028e\u0290";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}