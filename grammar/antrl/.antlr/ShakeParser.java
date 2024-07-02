// Generated from /Users/nicolasschmidt/repositories/shake/grammar/antrl/ShakeParser.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class ShakeParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WS=1, LINE_SEPARATOR=2, SEMICOLON=3, COMMA=4, COLON=5, DOT=6, FLOAT=7, 
		HEX_NUMBER=8, OCT_NUMBER=9, BIN_NUMBER=10, INTEGER=11, KEYWORD_ABSTRACT=12, 
		KEYWORD_AS=13, KEYWORD_CLASS=14, KEYWORD_CONST=15, KEYWORD_CONSTRUCTOR=16, 
		KEYWORD_DO=17, KEYWORD_ELSE=18, KEYWORD_ENUM=19, KEYWORD_FALSE=20, KEYWORD_FINAL=21, 
		KEYWORD_FOR=22, KEYWORD_FUN=23, KEYWORD_IF=24, KEYWORD_IMPORT=25, KEYWORD_IN=26, 
		KEYWORD_INLINE=27, KEYWORD_INSTANCEOF=28, KEYWORD_INTERFACE=29, KEYWORD_NATIVE=30, 
		KEYWORD_NULL=31, KEYWORD_OBJECT=32, KEYWORD_OPERATOR=33, KEYWORD_OVERRIDE=34, 
		KEYWORD_PACKAGE=35, KEYWORD_PRIVATE=36, KEYWORD_PROTECTED=37, KEYWORD_PUBLIC=38, 
		KEYWORD_RETURN=39, KEYWORD_STATIC=40, KEYWORD_SUPER=41, KEYWORD_SYNCHRONIZED=42, 
		KEYWORD_THIS=43, KEYWORD_TRUE=44, KEYWORD_VAL=45, KEYWORD_VAR=46, KEYWORD_WHILE=47, 
		IDENTIFIER=48, IDENTIFIER2=49, STRING=50, CHAR=51, SINGLE_LINE_COMMENT=52, 
		MULTILINE_COMMENT=53, POW_ASSIGN=54, MOD_ASSIGN=55, DIV_ASSIGN=56, MUL_ASSIGN=57, 
		SUB_ASSIGN=58, ADD_ASSIGN=59, INCR=60, DECR=61, POW=62, MOD=63, DIV=64, 
		MUL=65, SUB=66, ADD=67, LOGIC_OR=68, LOGIC_AND=69, LOGIC_XOR=70, EQUALS=71, 
		GTE=72, LTE=73, NEQ=74, GT=75, LT=76, LOGIC_NOT=77, BIT_NAND=78, BIT_NOR=79, 
		BIT_XNOR=80, BIT_NOT=81, BIT_AND=82, BIT_OR=83, BIT_XOR=84, ASSIGN=85, 
		LPAREN=86, RPAREN=87, LCURLY=88, RCURLY=89, LBRACK=90, RBRACK=91;
	public static final int
		RULE_shakeModifier = 0, RULE_shakeModifierList = 1, RULE_shakeNamespace = 2, 
		RULE_shakeType = 3, RULE_shakeTypeList = 4, RULE_genericDeclaration = 5, 
		RULE_genericsDeclaration = 6, RULE_funArgs = 7, RULE_funArg = 8, RULE_shakeBlock = 9, 
		RULE_blockChild = 10, RULE_shakeFile = 11, RULE_shakeFileChild = 12, RULE_shakePackageDeclaration = 13, 
		RULE_shakeImportDeclaration = 14, RULE_shakeFunDeclaration = 15, RULE_shakeFieldDeclaration = 16, 
		RULE_shakeStatement = 17, RULE_shakeVarDeclaration = 18, RULE_shakeWhile = 19, 
		RULE_shakeDoWhile = 20, RULE_shakeFor = 21, RULE_shakeIf = 22, RULE_shakeValue = 23, 
		RULE_shakeAccess = 24, RULE_shakeMandory = 25, RULE_shakeSign = 26, RULE_shakePriority = 27, 
		RULE_shakeLiteral = 28;
	private static String[] makeRuleNames() {
		return new String[] {
			"shakeModifier", "shakeModifierList", "shakeNamespace", "shakeType", 
			"shakeTypeList", "genericDeclaration", "genericsDeclaration", "funArgs", 
			"funArg", "shakeBlock", "blockChild", "shakeFile", "shakeFileChild", 
			"shakePackageDeclaration", "shakeImportDeclaration", "shakeFunDeclaration", 
			"shakeFieldDeclaration", "shakeStatement", "shakeVarDeclaration", "shakeWhile", 
			"shakeDoWhile", "shakeFor", "shakeIf", "shakeValue", "shakeAccess", "shakeMandory", 
			"shakeSign", "shakePriority", "shakeLiteral"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, "'\\n'", "';'", "','", "':'", "'.'", null, null, null, null, 
			null, "'abstract'", "'as'", "'class'", "'const'", "'constructor'", "'do'", 
			"'else'", "'enum'", "'false'", "'final'", "'for'", "'fun'", "'if'", "'import'", 
			"'in'", "'inline'", "'instanceof'", "'interface'", "'native'", "'null'", 
			"'object'", "'operator'", "'override'", "'package'", "'private'", "'protected'", 
			"'public'", "'return'", "'static'", "'super'", "'synchronized'", "'this'", 
			"'true'", "'val'", "'var'", "'while'", null, null, null, null, null, 
			null, "'**='", "'%='", "'/='", "'*='", "'-='", "'+='", "'++'", "'--'", 
			"'**'", "'%'", "'/'", "'*'", "'-'", "'+'", "'||'", "'&&'", "'^^'", "'=='", 
			"'>='", "'<='", "'!='", "'>'", "'<'", "'!'", "'~&'", "'~|'", "'~^'", 
			"'~'", "'&'", "'|'", "'^'", "'='", "'('", "')'", "'{'", "'}'", "'['", 
			"']'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "WS", "LINE_SEPARATOR", "SEMICOLON", "COMMA", "COLON", "DOT", "FLOAT", 
			"HEX_NUMBER", "OCT_NUMBER", "BIN_NUMBER", "INTEGER", "KEYWORD_ABSTRACT", 
			"KEYWORD_AS", "KEYWORD_CLASS", "KEYWORD_CONST", "KEYWORD_CONSTRUCTOR", 
			"KEYWORD_DO", "KEYWORD_ELSE", "KEYWORD_ENUM", "KEYWORD_FALSE", "KEYWORD_FINAL", 
			"KEYWORD_FOR", "KEYWORD_FUN", "KEYWORD_IF", "KEYWORD_IMPORT", "KEYWORD_IN", 
			"KEYWORD_INLINE", "KEYWORD_INSTANCEOF", "KEYWORD_INTERFACE", "KEYWORD_NATIVE", 
			"KEYWORD_NULL", "KEYWORD_OBJECT", "KEYWORD_OPERATOR", "KEYWORD_OVERRIDE", 
			"KEYWORD_PACKAGE", "KEYWORD_PRIVATE", "KEYWORD_PROTECTED", "KEYWORD_PUBLIC", 
			"KEYWORD_RETURN", "KEYWORD_STATIC", "KEYWORD_SUPER", "KEYWORD_SYNCHRONIZED", 
			"KEYWORD_THIS", "KEYWORD_TRUE", "KEYWORD_VAL", "KEYWORD_VAR", "KEYWORD_WHILE", 
			"IDENTIFIER", "IDENTIFIER2", "STRING", "CHAR", "SINGLE_LINE_COMMENT", 
			"MULTILINE_COMMENT", "POW_ASSIGN", "MOD_ASSIGN", "DIV_ASSIGN", "MUL_ASSIGN", 
			"SUB_ASSIGN", "ADD_ASSIGN", "INCR", "DECR", "POW", "MOD", "DIV", "MUL", 
			"SUB", "ADD", "LOGIC_OR", "LOGIC_AND", "LOGIC_XOR", "EQUALS", "GTE", 
			"LTE", "NEQ", "GT", "LT", "LOGIC_NOT", "BIT_NAND", "BIT_NOR", "BIT_XNOR", 
			"BIT_NOT", "BIT_AND", "BIT_OR", "BIT_XOR", "ASSIGN", "LPAREN", "RPAREN", 
			"LCURLY", "RCURLY", "LBRACK", "RBRACK"
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
	public String getGrammarFileName() { return "ShakeParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ShakeParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ShakeModifierContext extends ParserRuleContext {
		public TerminalNode KEYWORD_PUBLIC() { return getToken(ShakeParser.KEYWORD_PUBLIC, 0); }
		public TerminalNode KEYWORD_PRIVATE() { return getToken(ShakeParser.KEYWORD_PRIVATE, 0); }
		public TerminalNode KEYWORD_PROTECTED() { return getToken(ShakeParser.KEYWORD_PROTECTED, 0); }
		public TerminalNode KEYWORD_STATIC() { return getToken(ShakeParser.KEYWORD_STATIC, 0); }
		public TerminalNode KEYWORD_FINAL() { return getToken(ShakeParser.KEYWORD_FINAL, 0); }
		public TerminalNode KEYWORD_ABSTRACT() { return getToken(ShakeParser.KEYWORD_ABSTRACT, 0); }
		public TerminalNode KEYWORD_NATIVE() { return getToken(ShakeParser.KEYWORD_NATIVE, 0); }
		public TerminalNode KEYWORD_SYNCHRONIZED() { return getToken(ShakeParser.KEYWORD_SYNCHRONIZED, 0); }
		public TerminalNode KEYWORD_INLINE() { return getToken(ShakeParser.KEYWORD_INLINE, 0); }
		public ShakeModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shakeModifier; }
	}

	public final ShakeModifierContext shakeModifier() throws RecognitionException {
		ShakeModifierContext _localctx = new ShakeModifierContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_shakeModifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 5979804536832L) != 0)) ) {
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
	public static class ShakeModifierListContext extends ParserRuleContext {
		public List<ShakeModifierContext> shakeModifier() {
			return getRuleContexts(ShakeModifierContext.class);
		}
		public ShakeModifierContext shakeModifier(int i) {
			return getRuleContext(ShakeModifierContext.class,i);
		}
		public ShakeModifierListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shakeModifierList; }
	}

	public final ShakeModifierListContext shakeModifierList() throws RecognitionException {
		ShakeModifierListContext _localctx = new ShakeModifierListContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_shakeModifierList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 5979804536832L) != 0)) {
				{
				{
				setState(60);
				shakeModifier();
				}
				}
				setState(65);
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
	public static class ShakeNamespaceContext extends ParserRuleContext {
		public Token identifier;
		public Token dot;
		public ShakeNamespaceContext child;
		public TerminalNode IDENTIFIER() { return getToken(ShakeParser.IDENTIFIER, 0); }
		public TerminalNode DOT() { return getToken(ShakeParser.DOT, 0); }
		public ShakeNamespaceContext shakeNamespace() {
			return getRuleContext(ShakeNamespaceContext.class,0);
		}
		public ShakeNamespaceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shakeNamespace; }
	}

	public final ShakeNamespaceContext shakeNamespace() throws RecognitionException {
		ShakeNamespaceContext _localctx = new ShakeNamespaceContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_shakeNamespace);
		try {
			setState(70);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(66);
				((ShakeNamespaceContext)_localctx).identifier = match(IDENTIFIER);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(67);
				((ShakeNamespaceContext)_localctx).identifier = match(IDENTIFIER);
				}
				{
				setState(68);
				((ShakeNamespaceContext)_localctx).dot = match(DOT);
				}
				{
				setState(69);
				((ShakeNamespaceContext)_localctx).child = shakeNamespace();
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
	public static class ShakeTypeContext extends ParserRuleContext {
		public ShakeNamespaceContext namespace;
		public Token lt;
		public ShakeTypeListContext typeArgs;
		public ShakeNamespaceContext shakeNamespace() {
			return getRuleContext(ShakeNamespaceContext.class,0);
		}
		public TerminalNode LT() { return getToken(ShakeParser.LT, 0); }
		public ShakeTypeListContext shakeTypeList() {
			return getRuleContext(ShakeTypeListContext.class,0);
		}
		public TerminalNode GT() { return getToken(ShakeParser.GT, 0); }
		public ShakeTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shakeType; }
	}

	public final ShakeTypeContext shakeType() throws RecognitionException {
		ShakeTypeContext _localctx = new ShakeTypeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_shakeType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(72);
			((ShakeTypeContext)_localctx).namespace = shakeNamespace();
			}
			setState(77);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LT) {
				{
				{
				setState(73);
				((ShakeTypeContext)_localctx).lt = match(LT);
				}
				{
				setState(74);
				((ShakeTypeContext)_localctx).typeArgs = shakeTypeList();
				}
				{
				setState(75);
				((ShakeTypeContext)_localctx).lt = match(GT);
				}
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
	public static class ShakeTypeListContext extends ParserRuleContext {
		public List<ShakeTypeContext> shakeType() {
			return getRuleContexts(ShakeTypeContext.class);
		}
		public ShakeTypeContext shakeType(int i) {
			return getRuleContext(ShakeTypeContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(ShakeParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ShakeParser.COMMA, i);
		}
		public ShakeTypeListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shakeTypeList; }
	}

	public final ShakeTypeListContext shakeTypeList() throws RecognitionException {
		ShakeTypeListContext _localctx = new ShakeTypeListContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_shakeTypeList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			shakeType();
			setState(84);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(80);
				match(COMMA);
				setState(81);
				shakeType();
				}
				}
				setState(86);
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
	public static class GenericDeclarationContext extends ParserRuleContext {
		public Token name;
		public Token colon;
		public ShakeTypeContext type;
		public TerminalNode IDENTIFIER() { return getToken(ShakeParser.IDENTIFIER, 0); }
		public TerminalNode COLON() { return getToken(ShakeParser.COLON, 0); }
		public ShakeTypeContext shakeType() {
			return getRuleContext(ShakeTypeContext.class,0);
		}
		public GenericDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_genericDeclaration; }
	}

	public final GenericDeclarationContext genericDeclaration() throws RecognitionException {
		GenericDeclarationContext _localctx = new GenericDeclarationContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_genericDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(87);
			((GenericDeclarationContext)_localctx).name = match(IDENTIFIER);
			}
			{
			setState(88);
			((GenericDeclarationContext)_localctx).colon = match(COLON);
			}
			{
			setState(89);
			((GenericDeclarationContext)_localctx).type = shakeType();
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
	public static class GenericsDeclarationContext extends ParserRuleContext {
		public Token lt;
		public Token gt;
		public List<GenericDeclarationContext> genericDeclaration() {
			return getRuleContexts(GenericDeclarationContext.class);
		}
		public GenericDeclarationContext genericDeclaration(int i) {
			return getRuleContext(GenericDeclarationContext.class,i);
		}
		public TerminalNode LT() { return getToken(ShakeParser.LT, 0); }
		public TerminalNode GT() { return getToken(ShakeParser.GT, 0); }
		public List<TerminalNode> COMMA() { return getTokens(ShakeParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ShakeParser.COMMA, i);
		}
		public GenericsDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_genericsDeclaration; }
	}

	public final GenericsDeclarationContext genericsDeclaration() throws RecognitionException {
		GenericsDeclarationContext _localctx = new GenericsDeclarationContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_genericsDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(91);
			((GenericsDeclarationContext)_localctx).lt = match(LT);
			}
			{
			setState(92);
			genericDeclaration();
			setState(97);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(93);
				match(COMMA);
				setState(94);
				genericDeclaration();
				}
				}
				setState(99);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			{
			setState(100);
			((GenericsDeclarationContext)_localctx).gt = match(GT);
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
	public static class FunArgsContext extends ParserRuleContext {
		public Token lt;
		public FunArgContext args;
		public Token gt;
		public TerminalNode LPAREN() { return getToken(ShakeParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(ShakeParser.RPAREN, 0); }
		public List<FunArgContext> funArg() {
			return getRuleContexts(FunArgContext.class);
		}
		public FunArgContext funArg(int i) {
			return getRuleContext(FunArgContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(ShakeParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ShakeParser.COMMA, i);
		}
		public FunArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funArgs; }
	}

	public final FunArgsContext funArgs() throws RecognitionException {
		FunArgsContext _localctx = new FunArgsContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_funArgs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(102);
			((FunArgsContext)_localctx).lt = match(LPAREN);
			}
			{
			{
			setState(103);
			((FunArgsContext)_localctx).args = funArg();
			setState(108);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(104);
				match(COMMA);
				setState(105);
				funArg();
				}
				}
				setState(110);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			}
			{
			setState(111);
			((FunArgsContext)_localctx).gt = match(RPAREN);
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
	public static class FunArgContext extends ParserRuleContext {
		public Token name;
		public Token colon;
		public ShakeTypeContext type;
		public TerminalNode IDENTIFIER() { return getToken(ShakeParser.IDENTIFIER, 0); }
		public TerminalNode COLON() { return getToken(ShakeParser.COLON, 0); }
		public ShakeTypeContext shakeType() {
			return getRuleContext(ShakeTypeContext.class,0);
		}
		public FunArgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funArg; }
	}

	public final FunArgContext funArg() throws RecognitionException {
		FunArgContext _localctx = new FunArgContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_funArg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(113);
			((FunArgContext)_localctx).name = match(IDENTIFIER);
			}
			{
			setState(114);
			((FunArgContext)_localctx).colon = match(COLON);
			}
			{
			setState(115);
			((FunArgContext)_localctx).type = shakeType();
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
	public static class ShakeBlockContext extends ParserRuleContext {
		public Token lt;
		public Token gt;
		public TerminalNode LCURLY() { return getToken(ShakeParser.LCURLY, 0); }
		public List<BlockChildContext> blockChild() {
			return getRuleContexts(BlockChildContext.class);
		}
		public BlockChildContext blockChild(int i) {
			return getRuleContext(BlockChildContext.class,i);
		}
		public TerminalNode RCURLY() { return getToken(ShakeParser.RCURLY, 0); }
		public ShakeBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shakeBlock; }
	}

	public final ShakeBlockContext shakeBlock() throws RecognitionException {
		ShakeBlockContext _localctx = new ShakeBlockContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_shakeBlock);
		int _la;
		try {
			setState(126);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LCURLY:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(117);
				((ShakeBlockContext)_localctx).lt = match(LCURLY);
				}
				setState(121);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 111532920803328L) != 0)) {
					{
					{
					setState(118);
					blockChild();
					}
					}
					setState(123);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				{
				setState(124);
				((ShakeBlockContext)_localctx).gt = match(RCURLY);
				}
				}
				break;
			case KEYWORD_ABSTRACT:
			case KEYWORD_FINAL:
			case KEYWORD_INLINE:
			case KEYWORD_NATIVE:
			case KEYWORD_PRIVATE:
			case KEYWORD_PROTECTED:
			case KEYWORD_PUBLIC:
			case KEYWORD_STATIC:
			case KEYWORD_SYNCHRONIZED:
			case KEYWORD_VAL:
			case KEYWORD_VAR:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(125);
				blockChild();
				}
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
	public static class BlockChildContext extends ParserRuleContext {
		public ShakeStatementContext shakeStatement() {
			return getRuleContext(ShakeStatementContext.class,0);
		}
		public BlockChildContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blockChild; }
	}

	public final BlockChildContext blockChild() throws RecognitionException {
		BlockChildContext _localctx = new BlockChildContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_blockChild);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128);
			shakeStatement();
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
	public static class ShakeFileContext extends ParserRuleContext {
		public List<ShakeFileChildContext> shakeFileChild() {
			return getRuleContexts(ShakeFileChildContext.class);
		}
		public ShakeFileChildContext shakeFileChild(int i) {
			return getRuleContext(ShakeFileChildContext.class,i);
		}
		public ShakeFileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shakeFile; }
	}

	public final ShakeFileContext shakeFile() throws RecognitionException {
		ShakeFileContext _localctx = new ShakeFileContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_shakeFile);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 111567322484736L) != 0)) {
				{
				{
				setState(130);
				shakeFileChild();
				}
				}
				setState(135);
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
	public static class ShakeFileChildContext extends ParserRuleContext {
		public ShakePackageDeclarationContext shakePackageDeclaration() {
			return getRuleContext(ShakePackageDeclarationContext.class,0);
		}
		public ShakeImportDeclarationContext shakeImportDeclaration() {
			return getRuleContext(ShakeImportDeclarationContext.class,0);
		}
		public ShakeFunDeclarationContext shakeFunDeclaration() {
			return getRuleContext(ShakeFunDeclarationContext.class,0);
		}
		public ShakeFieldDeclarationContext shakeFieldDeclaration() {
			return getRuleContext(ShakeFieldDeclarationContext.class,0);
		}
		public ShakeFileChildContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shakeFileChild; }
	}

	public final ShakeFileChildContext shakeFileChild() throws RecognitionException {
		ShakeFileChildContext _localctx = new ShakeFileChildContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_shakeFileChild);
		try {
			setState(140);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(136);
				shakePackageDeclaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(137);
				shakeImportDeclaration();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(138);
				shakeFunDeclaration();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(139);
				shakeFieldDeclaration();
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
	public static class ShakePackageDeclarationContext extends ParserRuleContext {
		public TerminalNode KEYWORD_PACKAGE() { return getToken(ShakeParser.KEYWORD_PACKAGE, 0); }
		public ShakeNamespaceContext shakeNamespace() {
			return getRuleContext(ShakeNamespaceContext.class,0);
		}
		public ShakePackageDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shakePackageDeclaration; }
	}

	public final ShakePackageDeclarationContext shakePackageDeclaration() throws RecognitionException {
		ShakePackageDeclarationContext _localctx = new ShakePackageDeclarationContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_shakePackageDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(142);
			match(KEYWORD_PACKAGE);
			setState(143);
			shakeNamespace();
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
	public static class ShakeImportDeclarationContext extends ParserRuleContext {
		public TerminalNode KEYWORD_IMPORT() { return getToken(ShakeParser.KEYWORD_IMPORT, 0); }
		public ShakeNamespaceContext shakeNamespace() {
			return getRuleContext(ShakeNamespaceContext.class,0);
		}
		public ShakeImportDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shakeImportDeclaration; }
	}

	public final ShakeImportDeclarationContext shakeImportDeclaration() throws RecognitionException {
		ShakeImportDeclarationContext _localctx = new ShakeImportDeclarationContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_shakeImportDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			match(KEYWORD_IMPORT);
			setState(146);
			shakeNamespace();
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
	public static class ShakeFunDeclarationContext extends ParserRuleContext {
		public Token fun;
		public GenericsDeclarationContext generics;
		public ShakeNamespaceContext expanding;
		public Token name;
		public FunArgsContext args;
		public Token colon;
		public ShakeTypeContext returnType;
		public ShakeBlockContext block;
		public ShakeModifierListContext shakeModifierList() {
			return getRuleContext(ShakeModifierListContext.class,0);
		}
		public TerminalNode KEYWORD_FUN() { return getToken(ShakeParser.KEYWORD_FUN, 0); }
		public TerminalNode DOT() { return getToken(ShakeParser.DOT, 0); }
		public TerminalNode IDENTIFIER() { return getToken(ShakeParser.IDENTIFIER, 0); }
		public FunArgsContext funArgs() {
			return getRuleContext(FunArgsContext.class,0);
		}
		public ShakeBlockContext shakeBlock() {
			return getRuleContext(ShakeBlockContext.class,0);
		}
		public GenericsDeclarationContext genericsDeclaration() {
			return getRuleContext(GenericsDeclarationContext.class,0);
		}
		public ShakeNamespaceContext shakeNamespace() {
			return getRuleContext(ShakeNamespaceContext.class,0);
		}
		public TerminalNode COLON() { return getToken(ShakeParser.COLON, 0); }
		public ShakeTypeContext shakeType() {
			return getRuleContext(ShakeTypeContext.class,0);
		}
		public ShakeFunDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shakeFunDeclaration; }
	}

	public final ShakeFunDeclarationContext shakeFunDeclaration() throws RecognitionException {
		ShakeFunDeclarationContext _localctx = new ShakeFunDeclarationContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_shakeFunDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148);
			shakeModifierList();
			{
			setState(149);
			((ShakeFunDeclarationContext)_localctx).fun = match(KEYWORD_FUN);
			}
			setState(151);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LT) {
				{
				setState(150);
				((ShakeFunDeclarationContext)_localctx).generics = genericsDeclaration();
				}
			}

			setState(156);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				{
				setState(153);
				((ShakeFunDeclarationContext)_localctx).expanding = shakeNamespace();
				}
				setState(154);
				match(DOT);
				}
				break;
			}
			{
			setState(158);
			((ShakeFunDeclarationContext)_localctx).name = match(IDENTIFIER);
			}
			{
			setState(159);
			((ShakeFunDeclarationContext)_localctx).args = funArgs();
			}
			setState(162);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				{
				setState(160);
				((ShakeFunDeclarationContext)_localctx).colon = match(COLON);
				}
				{
				setState(161);
				((ShakeFunDeclarationContext)_localctx).returnType = shakeType();
				}
				}
			}

			{
			setState(164);
			((ShakeFunDeclarationContext)_localctx).block = shakeBlock();
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
	public static class ShakeFieldDeclarationContext extends ParserRuleContext {
		public Token var;
		public ShakeNamespaceContext expanding;
		public Token name;
		public Token colon;
		public ShakeTypeContext variableType;
		public Token assign;
		public ShakeValueContext value;
		public ShakeModifierListContext shakeModifierList() {
			return getRuleContext(ShakeModifierListContext.class,0);
		}
		public TerminalNode KEYWORD_VAR() { return getToken(ShakeParser.KEYWORD_VAR, 0); }
		public TerminalNode KEYWORD_VAL() { return getToken(ShakeParser.KEYWORD_VAL, 0); }
		public TerminalNode DOT() { return getToken(ShakeParser.DOT, 0); }
		public TerminalNode IDENTIFIER() { return getToken(ShakeParser.IDENTIFIER, 0); }
		public ShakeNamespaceContext shakeNamespace() {
			return getRuleContext(ShakeNamespaceContext.class,0);
		}
		public TerminalNode COLON() { return getToken(ShakeParser.COLON, 0); }
		public ShakeTypeContext shakeType() {
			return getRuleContext(ShakeTypeContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(ShakeParser.ASSIGN, 0); }
		public ShakeValueContext shakeValue() {
			return getRuleContext(ShakeValueContext.class,0);
		}
		public ShakeFieldDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shakeFieldDeclaration; }
	}

	public final ShakeFieldDeclarationContext shakeFieldDeclaration() throws RecognitionException {
		ShakeFieldDeclarationContext _localctx = new ShakeFieldDeclarationContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_shakeFieldDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			shakeModifierList();
			setState(169);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case KEYWORD_VAR:
				{
				setState(167);
				((ShakeFieldDeclarationContext)_localctx).var = match(KEYWORD_VAR);
				}
				break;
			case KEYWORD_VAL:
				{
				setState(168);
				((ShakeFieldDeclarationContext)_localctx).var = match(KEYWORD_VAL);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(174);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				{
				{
				setState(171);
				((ShakeFieldDeclarationContext)_localctx).expanding = shakeNamespace();
				}
				setState(172);
				match(DOT);
				}
				break;
			}
			{
			setState(176);
			((ShakeFieldDeclarationContext)_localctx).name = match(IDENTIFIER);
			}
			setState(179);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				{
				setState(177);
				((ShakeFieldDeclarationContext)_localctx).colon = match(COLON);
				}
				{
				setState(178);
				((ShakeFieldDeclarationContext)_localctx).variableType = shakeType();
				}
				}
			}

			setState(183);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				{
				setState(181);
				((ShakeFieldDeclarationContext)_localctx).assign = match(ASSIGN);
				}
				{
				setState(182);
				((ShakeFieldDeclarationContext)_localctx).value = shakeValue();
				}
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
	public static class ShakeStatementContext extends ParserRuleContext {
		public ShakeVarDeclarationContext shakeVarDeclaration() {
			return getRuleContext(ShakeVarDeclarationContext.class,0);
		}
		public ShakeStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shakeStatement; }
	}

	public final ShakeStatementContext shakeStatement() throws RecognitionException {
		ShakeStatementContext _localctx = new ShakeStatementContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_shakeStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(185);
			shakeVarDeclaration();
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
	public static class ShakeVarDeclarationContext extends ParserRuleContext {
		public Token var;
		public ShakeNamespaceContext expanding;
		public Token name;
		public Token colon;
		public ShakeTypeContext variableType;
		public Token assign;
		public ShakeValueContext value;
		public ShakeModifierListContext shakeModifierList() {
			return getRuleContext(ShakeModifierListContext.class,0);
		}
		public TerminalNode DOT() { return getToken(ShakeParser.DOT, 0); }
		public TerminalNode IDENTIFIER() { return getToken(ShakeParser.IDENTIFIER, 0); }
		public TerminalNode KEYWORD_VAR() { return getToken(ShakeParser.KEYWORD_VAR, 0); }
		public TerminalNode KEYWORD_VAL() { return getToken(ShakeParser.KEYWORD_VAL, 0); }
		public ShakeNamespaceContext shakeNamespace() {
			return getRuleContext(ShakeNamespaceContext.class,0);
		}
		public TerminalNode COLON() { return getToken(ShakeParser.COLON, 0); }
		public ShakeTypeContext shakeType() {
			return getRuleContext(ShakeTypeContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(ShakeParser.ASSIGN, 0); }
		public ShakeValueContext shakeValue() {
			return getRuleContext(ShakeValueContext.class,0);
		}
		public ShakeVarDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shakeVarDeclaration; }
	}

	public final ShakeVarDeclarationContext shakeVarDeclaration() throws RecognitionException {
		ShakeVarDeclarationContext _localctx = new ShakeVarDeclarationContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_shakeVarDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(187);
			shakeModifierList();
			setState(190);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case KEYWORD_VAR:
				{
				{
				setState(188);
				((ShakeVarDeclarationContext)_localctx).var = match(KEYWORD_VAR);
				}
				}
				break;
			case KEYWORD_VAL:
				{
				{
				setState(189);
				((ShakeVarDeclarationContext)_localctx).var = match(KEYWORD_VAL);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(195);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				{
				{
				setState(192);
				((ShakeVarDeclarationContext)_localctx).expanding = shakeNamespace();
				}
				setState(193);
				match(DOT);
				}
				break;
			}
			{
			setState(197);
			((ShakeVarDeclarationContext)_localctx).name = match(IDENTIFIER);
			}
			setState(200);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				{
				setState(198);
				((ShakeVarDeclarationContext)_localctx).colon = match(COLON);
				}
				{
				setState(199);
				((ShakeVarDeclarationContext)_localctx).variableType = shakeType();
				}
				}
			}

			setState(204);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				{
				setState(202);
				((ShakeVarDeclarationContext)_localctx).assign = match(ASSIGN);
				}
				{
				setState(203);
				((ShakeVarDeclarationContext)_localctx).value = shakeValue();
				}
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
	public static class ShakeWhileContext extends ParserRuleContext {
		public ShakeValueContext condition;
		public ShakeBlockContext body;
		public TerminalNode KEYWORD_WHILE() { return getToken(ShakeParser.KEYWORD_WHILE, 0); }
		public TerminalNode LPAREN() { return getToken(ShakeParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(ShakeParser.RPAREN, 0); }
		public ShakeValueContext shakeValue() {
			return getRuleContext(ShakeValueContext.class,0);
		}
		public ShakeBlockContext shakeBlock() {
			return getRuleContext(ShakeBlockContext.class,0);
		}
		public ShakeWhileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shakeWhile; }
	}

	public final ShakeWhileContext shakeWhile() throws RecognitionException {
		ShakeWhileContext _localctx = new ShakeWhileContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_shakeWhile);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206);
			match(KEYWORD_WHILE);
			setState(207);
			match(LPAREN);
			{
			setState(208);
			((ShakeWhileContext)_localctx).condition = shakeValue();
			}
			setState(209);
			match(RPAREN);
			{
			setState(210);
			((ShakeWhileContext)_localctx).body = shakeBlock();
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
	public static class ShakeDoWhileContext extends ParserRuleContext {
		public ShakeBlockContext body;
		public ShakeValueContext condition;
		public TerminalNode KEYWORD_DO() { return getToken(ShakeParser.KEYWORD_DO, 0); }
		public TerminalNode KEYWORD_WHILE() { return getToken(ShakeParser.KEYWORD_WHILE, 0); }
		public TerminalNode LPAREN() { return getToken(ShakeParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(ShakeParser.RPAREN, 0); }
		public ShakeBlockContext shakeBlock() {
			return getRuleContext(ShakeBlockContext.class,0);
		}
		public ShakeValueContext shakeValue() {
			return getRuleContext(ShakeValueContext.class,0);
		}
		public ShakeDoWhileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shakeDoWhile; }
	}

	public final ShakeDoWhileContext shakeDoWhile() throws RecognitionException {
		ShakeDoWhileContext _localctx = new ShakeDoWhileContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_shakeDoWhile);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(212);
			match(KEYWORD_DO);
			{
			setState(213);
			((ShakeDoWhileContext)_localctx).body = shakeBlock();
			}
			setState(214);
			match(KEYWORD_WHILE);
			setState(215);
			match(LPAREN);
			{
			setState(216);
			((ShakeDoWhileContext)_localctx).condition = shakeValue();
			}
			setState(217);
			match(RPAREN);
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
	public static class ShakeForContext extends ParserRuleContext {
		public ShakeStatementContext init;
		public ShakeValueContext condition;
		public ShakeValueContext round;
		public ShakeBlockContext body;
		public TerminalNode KEYWORD_FOR() { return getToken(ShakeParser.KEYWORD_FOR, 0); }
		public TerminalNode LPAREN() { return getToken(ShakeParser.LPAREN, 0); }
		public List<TerminalNode> SEMICOLON() { return getTokens(ShakeParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(ShakeParser.SEMICOLON, i);
		}
		public ShakeStatementContext shakeStatement() {
			return getRuleContext(ShakeStatementContext.class,0);
		}
		public List<ShakeValueContext> shakeValue() {
			return getRuleContexts(ShakeValueContext.class);
		}
		public ShakeValueContext shakeValue(int i) {
			return getRuleContext(ShakeValueContext.class,i);
		}
		public ShakeBlockContext shakeBlock() {
			return getRuleContext(ShakeBlockContext.class,0);
		}
		public ShakeForContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shakeFor; }
	}

	public final ShakeForContext shakeFor() throws RecognitionException {
		ShakeForContext _localctx = new ShakeForContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_shakeFor);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(219);
			match(KEYWORD_FOR);
			setState(220);
			match(LPAREN);
			{
			setState(221);
			((ShakeForContext)_localctx).init = shakeStatement();
			}
			setState(222);
			match(SEMICOLON);
			{
			setState(223);
			((ShakeForContext)_localctx).condition = shakeValue();
			}
			setState(224);
			match(SEMICOLON);
			{
			setState(225);
			((ShakeForContext)_localctx).round = shakeValue();
			}
			{
			setState(226);
			((ShakeForContext)_localctx).body = shakeBlock();
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
	public static class ShakeIfContext extends ParserRuleContext {
		public ShakeValueContext condition;
		public ShakeBlockContext thenBlock;
		public ShakeBlockContext elseBlock;
		public TerminalNode KEYWORD_IF() { return getToken(ShakeParser.KEYWORD_IF, 0); }
		public TerminalNode LPAREN() { return getToken(ShakeParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(ShakeParser.RPAREN, 0); }
		public ShakeValueContext shakeValue() {
			return getRuleContext(ShakeValueContext.class,0);
		}
		public List<ShakeBlockContext> shakeBlock() {
			return getRuleContexts(ShakeBlockContext.class);
		}
		public ShakeBlockContext shakeBlock(int i) {
			return getRuleContext(ShakeBlockContext.class,i);
		}
		public TerminalNode KEYWORD_ELSE() { return getToken(ShakeParser.KEYWORD_ELSE, 0); }
		public ShakeIfContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shakeIf; }
	}

	public final ShakeIfContext shakeIf() throws RecognitionException {
		ShakeIfContext _localctx = new ShakeIfContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_shakeIf);
		try {
			setState(242);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(228);
				match(KEYWORD_IF);
				setState(229);
				match(LPAREN);
				{
				setState(230);
				((ShakeIfContext)_localctx).condition = shakeValue();
				}
				setState(231);
				match(RPAREN);
				{
				setState(232);
				((ShakeIfContext)_localctx).thenBlock = shakeBlock();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(234);
				match(KEYWORD_IF);
				setState(235);
				match(LPAREN);
				{
				setState(236);
				((ShakeIfContext)_localctx).condition = shakeValue();
				}
				setState(237);
				match(RPAREN);
				{
				setState(238);
				((ShakeIfContext)_localctx).thenBlock = shakeBlock();
				}
				setState(239);
				match(KEYWORD_ELSE);
				{
				setState(240);
				((ShakeIfContext)_localctx).elseBlock = shakeBlock();
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
	public static class ShakeValueContext extends ParserRuleContext {
		public TerminalNode KEYWORD_ABSTRACT() { return getToken(ShakeParser.KEYWORD_ABSTRACT, 0); }
		public ShakeValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shakeValue; }
	}

	public final ShakeValueContext shakeValue() throws RecognitionException {
		ShakeValueContext _localctx = new ShakeValueContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_shakeValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(244);
			match(KEYWORD_ABSTRACT);
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
	public static class ShakeAccessContext extends ParserRuleContext {
		public ShakeMandoryContext left;
		public ShakeAccessContext right;
		public ShakeMandoryContext shakeMandory() {
			return getRuleContext(ShakeMandoryContext.class,0);
		}
		public TerminalNode DOT() { return getToken(ShakeParser.DOT, 0); }
		public ShakeAccessContext shakeAccess() {
			return getRuleContext(ShakeAccessContext.class,0);
		}
		public ShakeAccessContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shakeAccess; }
	}

	public final ShakeAccessContext shakeAccess() throws RecognitionException {
		ShakeAccessContext _localctx = new ShakeAccessContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_shakeAccess);
		try {
			setState(251);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(246);
				shakeMandory(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(247);
				((ShakeAccessContext)_localctx).left = shakeMandory(0);
				}
				setState(248);
				match(DOT);
				{
				setState(249);
				((ShakeAccessContext)_localctx).right = shakeAccess();
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
	public static class ShakeMandoryContext extends ParserRuleContext {
		public ShakeSignContext shakeSign() {
			return getRuleContext(ShakeSignContext.class,0);
		}
		public TerminalNode INCR() { return getToken(ShakeParser.INCR, 0); }
		public ShakeMandoryContext shakeMandory() {
			return getRuleContext(ShakeMandoryContext.class,0);
		}
		public TerminalNode DECR() { return getToken(ShakeParser.DECR, 0); }
		public ShakeMandoryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shakeMandory; }
	}

	public final ShakeMandoryContext shakeMandory() throws RecognitionException {
		return shakeMandory(0);
	}

	private ShakeMandoryContext shakeMandory(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ShakeMandoryContext _localctx = new ShakeMandoryContext(_ctx, _parentState);
		ShakeMandoryContext _prevctx = _localctx;
		int _startState = 50;
		enterRecursionRule(_localctx, 50, RULE_shakeMandory, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(259);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FLOAT:
			case HEX_NUMBER:
			case OCT_NUMBER:
			case BIN_NUMBER:
			case INTEGER:
			case KEYWORD_FALSE:
			case KEYWORD_SUPER:
			case KEYWORD_THIS:
			case KEYWORD_TRUE:
			case IDENTIFIER:
			case STRING:
			case CHAR:
			case SUB:
			case ADD:
			case LOGIC_NOT:
			case BIT_NOT:
			case LPAREN:
				{
				setState(254);
				shakeSign();
				}
				break;
			case INCR:
				{
				setState(255);
				match(INCR);
				setState(256);
				shakeMandory(4);
				}
				break;
			case DECR:
				{
				setState(257);
				match(DECR);
				setState(258);
				shakeMandory(2);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(267);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(265);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
					case 1:
						{
						_localctx = new ShakeMandoryContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_shakeMandory);
						setState(261);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(262);
						match(INCR);
						}
						break;
					case 2:
						{
						_localctx = new ShakeMandoryContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_shakeMandory);
						setState(263);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(264);
						match(DECR);
						}
						break;
					}
					} 
				}
				setState(269);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
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
	public static class ShakeSignContext extends ParserRuleContext {
		public Token sign;
		public ShakeSignContext content;
		public ShakePriorityContext shakePriority() {
			return getRuleContext(ShakePriorityContext.class,0);
		}
		public TerminalNode SUB() { return getToken(ShakeParser.SUB, 0); }
		public ShakeSignContext shakeSign() {
			return getRuleContext(ShakeSignContext.class,0);
		}
		public TerminalNode ADD() { return getToken(ShakeParser.ADD, 0); }
		public TerminalNode LOGIC_NOT() { return getToken(ShakeParser.LOGIC_NOT, 0); }
		public TerminalNode BIT_NOT() { return getToken(ShakeParser.BIT_NOT, 0); }
		public ShakeSignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shakeSign; }
	}

	public final ShakeSignContext shakeSign() throws RecognitionException {
		ShakeSignContext _localctx = new ShakeSignContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_shakeSign);
		try {
			setState(279);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FLOAT:
			case HEX_NUMBER:
			case OCT_NUMBER:
			case BIN_NUMBER:
			case INTEGER:
			case KEYWORD_FALSE:
			case KEYWORD_SUPER:
			case KEYWORD_THIS:
			case KEYWORD_TRUE:
			case IDENTIFIER:
			case STRING:
			case CHAR:
			case LPAREN:
				enterOuterAlt(_localctx, 1);
				{
				setState(270);
				shakePriority();
				}
				break;
			case SUB:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(271);
				((ShakeSignContext)_localctx).sign = match(SUB);
				}
				{
				setState(272);
				((ShakeSignContext)_localctx).content = shakeSign();
				}
				}
				break;
			case ADD:
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(273);
				((ShakeSignContext)_localctx).sign = match(ADD);
				}
				{
				setState(274);
				((ShakeSignContext)_localctx).content = shakeSign();
				}
				}
				break;
			case LOGIC_NOT:
				enterOuterAlt(_localctx, 4);
				{
				{
				setState(275);
				((ShakeSignContext)_localctx).sign = match(LOGIC_NOT);
				}
				{
				setState(276);
				((ShakeSignContext)_localctx).content = shakeSign();
				}
				}
				break;
			case BIT_NOT:
				enterOuterAlt(_localctx, 5);
				{
				{
				setState(277);
				((ShakeSignContext)_localctx).sign = match(BIT_NOT);
				}
				{
				setState(278);
				((ShakeSignContext)_localctx).content = shakeSign();
				}
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
	public static class ShakePriorityContext extends ParserRuleContext {
		public ShakeLiteralContext shakeLiteral() {
			return getRuleContext(ShakeLiteralContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(ShakeParser.LPAREN, 0); }
		public ShakeValueContext shakeValue() {
			return getRuleContext(ShakeValueContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(ShakeParser.RPAREN, 0); }
		public ShakePriorityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shakePriority; }
	}

	public final ShakePriorityContext shakePriority() throws RecognitionException {
		ShakePriorityContext _localctx = new ShakePriorityContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_shakePriority);
		try {
			setState(286);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FLOAT:
			case HEX_NUMBER:
			case OCT_NUMBER:
			case BIN_NUMBER:
			case INTEGER:
			case KEYWORD_FALSE:
			case KEYWORD_SUPER:
			case KEYWORD_THIS:
			case KEYWORD_TRUE:
			case IDENTIFIER:
			case STRING:
			case CHAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(281);
				shakeLiteral();
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 2);
				{
				setState(282);
				match(LPAREN);
				setState(283);
				shakeValue();
				setState(284);
				match(RPAREN);
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
	public static class ShakeLiteralContext extends ParserRuleContext {
		public ShakeLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shakeLiteral; }
	 
		public ShakeLiteralContext() { }
		public void copyFrom(ShakeLiteralContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BinNumberLiteralContext extends ShakeLiteralContext {
		public Token literal;
		public TerminalNode BIN_NUMBER() { return getToken(ShakeParser.BIN_NUMBER, 0); }
		public BinNumberLiteralContext(ShakeLiteralContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class OctNumberLiteralContext extends ShakeLiteralContext {
		public Token literal;
		public TerminalNode OCT_NUMBER() { return getToken(ShakeParser.OCT_NUMBER, 0); }
		public OctNumberLiteralContext(ShakeLiteralContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ThisLiteralContext extends ShakeLiteralContext {
		public Token literal;
		public TerminalNode KEYWORD_THIS() { return getToken(ShakeParser.KEYWORD_THIS, 0); }
		public ThisLiteralContext(ShakeLiteralContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CharLiteralContext extends ShakeLiteralContext {
		public Token literal;
		public TerminalNode CHAR() { return getToken(ShakeParser.CHAR, 0); }
		public CharLiteralContext(ShakeLiteralContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StringLiteralContext extends ShakeLiteralContext {
		public Token literal;
		public TerminalNode STRING() { return getToken(ShakeParser.STRING, 0); }
		public StringLiteralContext(ShakeLiteralContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TrueLiteralContext extends ShakeLiteralContext {
		public Token literal;
		public TerminalNode KEYWORD_TRUE() { return getToken(ShakeParser.KEYWORD_TRUE, 0); }
		public TrueLiteralContext(ShakeLiteralContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FloatLiteralContext extends ShakeLiteralContext {
		public Token literal;
		public TerminalNode FLOAT() { return getToken(ShakeParser.FLOAT, 0); }
		public FloatLiteralContext(ShakeLiteralContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IdentifierLiteralContext extends ShakeLiteralContext {
		public Token literal;
		public TerminalNode IDENTIFIER() { return getToken(ShakeParser.IDENTIFIER, 0); }
		public IdentifierLiteralContext(ShakeLiteralContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SuperLiteralContext extends ShakeLiteralContext {
		public Token literal;
		public TerminalNode KEYWORD_SUPER() { return getToken(ShakeParser.KEYWORD_SUPER, 0); }
		public SuperLiteralContext(ShakeLiteralContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class HexNumberLiteralContext extends ShakeLiteralContext {
		public Token literal;
		public TerminalNode HEX_NUMBER() { return getToken(ShakeParser.HEX_NUMBER, 0); }
		public HexNumberLiteralContext(ShakeLiteralContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IntegerLiteralContext extends ShakeLiteralContext {
		public Token literal;
		public TerminalNode INTEGER() { return getToken(ShakeParser.INTEGER, 0); }
		public IntegerLiteralContext(ShakeLiteralContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FalseLiteralContext extends ShakeLiteralContext {
		public Token literal;
		public TerminalNode KEYWORD_FALSE() { return getToken(ShakeParser.KEYWORD_FALSE, 0); }
		public FalseLiteralContext(ShakeLiteralContext ctx) { copyFrom(ctx); }
	}

	public final ShakeLiteralContext shakeLiteral() throws RecognitionException {
		ShakeLiteralContext _localctx = new ShakeLiteralContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_shakeLiteral);
		try {
			setState(300);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CHAR:
				_localctx = new CharLiteralContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(288);
				((CharLiteralContext)_localctx).literal = match(CHAR);
				}
				}
				break;
			case STRING:
				_localctx = new StringLiteralContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(289);
				((StringLiteralContext)_localctx).literal = match(STRING);
				}
				}
				break;
			case INTEGER:
				_localctx = new IntegerLiteralContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(290);
				((IntegerLiteralContext)_localctx).literal = match(INTEGER);
				}
				}
				break;
			case FLOAT:
				_localctx = new FloatLiteralContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				{
				setState(291);
				((FloatLiteralContext)_localctx).literal = match(FLOAT);
				}
				}
				break;
			case BIN_NUMBER:
				_localctx = new BinNumberLiteralContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				{
				setState(292);
				((BinNumberLiteralContext)_localctx).literal = match(BIN_NUMBER);
				}
				}
				break;
			case HEX_NUMBER:
				_localctx = new HexNumberLiteralContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				{
				setState(293);
				((HexNumberLiteralContext)_localctx).literal = match(HEX_NUMBER);
				}
				}
				break;
			case OCT_NUMBER:
				_localctx = new OctNumberLiteralContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				{
				setState(294);
				((OctNumberLiteralContext)_localctx).literal = match(OCT_NUMBER);
				}
				}
				break;
			case IDENTIFIER:
				_localctx = new IdentifierLiteralContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				{
				setState(295);
				((IdentifierLiteralContext)_localctx).literal = match(IDENTIFIER);
				}
				}
				break;
			case KEYWORD_TRUE:
				_localctx = new TrueLiteralContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				{
				setState(296);
				((TrueLiteralContext)_localctx).literal = match(KEYWORD_TRUE);
				}
				}
				break;
			case KEYWORD_FALSE:
				_localctx = new FalseLiteralContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				{
				setState(297);
				((FalseLiteralContext)_localctx).literal = match(KEYWORD_FALSE);
				}
				}
				break;
			case KEYWORD_THIS:
				_localctx = new ThisLiteralContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				{
				setState(298);
				((ThisLiteralContext)_localctx).literal = match(KEYWORD_THIS);
				}
				}
				break;
			case KEYWORD_SUPER:
				_localctx = new SuperLiteralContext(_localctx);
				enterOuterAlt(_localctx, 12);
				{
				{
				setState(299);
				((SuperLiteralContext)_localctx).literal = match(KEYWORD_SUPER);
				}
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 25:
			return shakeMandory_sempred((ShakeMandoryContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean shakeMandory_sempred(ShakeMandoryContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 3);
		case 1:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001[\u012f\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0001\u0000\u0001\u0000\u0001\u0001\u0005\u0001"+
		">\b\u0001\n\u0001\f\u0001A\t\u0001\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0003\u0002G\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0003\u0003N\b\u0003\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0005\u0004S\b\u0004\n\u0004\f\u0004V\t\u0004\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0005\u0006`\b\u0006\n\u0006\f\u0006c\t\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0005\u0007"+
		"k\b\u0007\n\u0007\f\u0007n\t\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\t\u0001\t\u0005\tx\b\t\n\t\f\t{\t\t\u0001\t\u0001"+
		"\t\u0003\t\u007f\b\t\u0001\n\u0001\n\u0001\u000b\u0005\u000b\u0084\b\u000b"+
		"\n\u000b\f\u000b\u0087\t\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0003\f"+
		"\u008d\b\f\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0003\u000f\u0098\b\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0003\u000f\u009d\b\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0003\u000f\u00a3\b\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0003\u0010\u00aa\b\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0003\u0010\u00af\b\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0003\u0010\u00b4\b\u0010\u0001\u0010\u0001\u0010\u0003\u0010"+
		"\u00b8\b\u0010\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0003\u0012\u00bf\b\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0003\u0012"+
		"\u00c4\b\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0003\u0012\u00c9\b"+
		"\u0012\u0001\u0012\u0001\u0012\u0003\u0012\u00cd\b\u0012\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0003\u0016\u00f3"+
		"\b\u0016\u0001\u0017\u0001\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0003\u0018\u00fc\b\u0018\u0001\u0019\u0001\u0019\u0001"+
		"\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0003\u0019\u0104\b\u0019\u0001"+
		"\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0005\u0019\u010a\b\u0019\n"+
		"\u0019\f\u0019\u010d\t\u0019\u0001\u001a\u0001\u001a\u0001\u001a\u0001"+
		"\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0003"+
		"\u001a\u0118\b\u001a\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001"+
		"\u001b\u0003\u001b\u011f\b\u001b\u0001\u001c\u0001\u001c\u0001\u001c\u0001"+
		"\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001"+
		"\u001c\u0001\u001c\u0001\u001c\u0003\u001c\u012d\b\u001c\u0001\u001c\u0000"+
		"\u00012\u001d\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016"+
		"\u0018\u001a\u001c\u001e \"$&(*,.02468\u0000\u0001\u0007\u0000\f\f\u0015"+
		"\u0015\u001b\u001b\u001e\u001e$&((**\u013e\u0000:\u0001\u0000\u0000\u0000"+
		"\u0002?\u0001\u0000\u0000\u0000\u0004F\u0001\u0000\u0000\u0000\u0006H"+
		"\u0001\u0000\u0000\u0000\bO\u0001\u0000\u0000\u0000\nW\u0001\u0000\u0000"+
		"\u0000\f[\u0001\u0000\u0000\u0000\u000ef\u0001\u0000\u0000\u0000\u0010"+
		"q\u0001\u0000\u0000\u0000\u0012~\u0001\u0000\u0000\u0000\u0014\u0080\u0001"+
		"\u0000\u0000\u0000\u0016\u0085\u0001\u0000\u0000\u0000\u0018\u008c\u0001"+
		"\u0000\u0000\u0000\u001a\u008e\u0001\u0000\u0000\u0000\u001c\u0091\u0001"+
		"\u0000\u0000\u0000\u001e\u0094\u0001\u0000\u0000\u0000 \u00a6\u0001\u0000"+
		"\u0000\u0000\"\u00b9\u0001\u0000\u0000\u0000$\u00bb\u0001\u0000\u0000"+
		"\u0000&\u00ce\u0001\u0000\u0000\u0000(\u00d4\u0001\u0000\u0000\u0000*"+
		"\u00db\u0001\u0000\u0000\u0000,\u00f2\u0001\u0000\u0000\u0000.\u00f4\u0001"+
		"\u0000\u0000\u00000\u00fb\u0001\u0000\u0000\u00002\u0103\u0001\u0000\u0000"+
		"\u00004\u0117\u0001\u0000\u0000\u00006\u011e\u0001\u0000\u0000\u00008"+
		"\u012c\u0001\u0000\u0000\u0000:;\u0007\u0000\u0000\u0000;\u0001\u0001"+
		"\u0000\u0000\u0000<>\u0003\u0000\u0000\u0000=<\u0001\u0000\u0000\u0000"+
		">A\u0001\u0000\u0000\u0000?=\u0001\u0000\u0000\u0000?@\u0001\u0000\u0000"+
		"\u0000@\u0003\u0001\u0000\u0000\u0000A?\u0001\u0000\u0000\u0000BG\u0005"+
		"0\u0000\u0000CD\u00050\u0000\u0000DE\u0005\u0006\u0000\u0000EG\u0003\u0004"+
		"\u0002\u0000FB\u0001\u0000\u0000\u0000FC\u0001\u0000\u0000\u0000G\u0005"+
		"\u0001\u0000\u0000\u0000HM\u0003\u0004\u0002\u0000IJ\u0005L\u0000\u0000"+
		"JK\u0003\b\u0004\u0000KL\u0005K\u0000\u0000LN\u0001\u0000\u0000\u0000"+
		"MI\u0001\u0000\u0000\u0000MN\u0001\u0000\u0000\u0000N\u0007\u0001\u0000"+
		"\u0000\u0000OT\u0003\u0006\u0003\u0000PQ\u0005\u0004\u0000\u0000QS\u0003"+
		"\u0006\u0003\u0000RP\u0001\u0000\u0000\u0000SV\u0001\u0000\u0000\u0000"+
		"TR\u0001\u0000\u0000\u0000TU\u0001\u0000\u0000\u0000U\t\u0001\u0000\u0000"+
		"\u0000VT\u0001\u0000\u0000\u0000WX\u00050\u0000\u0000XY\u0005\u0005\u0000"+
		"\u0000YZ\u0003\u0006\u0003\u0000Z\u000b\u0001\u0000\u0000\u0000[\\\u0005"+
		"L\u0000\u0000\\a\u0003\n\u0005\u0000]^\u0005\u0004\u0000\u0000^`\u0003"+
		"\n\u0005\u0000_]\u0001\u0000\u0000\u0000`c\u0001\u0000\u0000\u0000a_\u0001"+
		"\u0000\u0000\u0000ab\u0001\u0000\u0000\u0000bd\u0001\u0000\u0000\u0000"+
		"ca\u0001\u0000\u0000\u0000de\u0005K\u0000\u0000e\r\u0001\u0000\u0000\u0000"+
		"fg\u0005V\u0000\u0000gl\u0003\u0010\b\u0000hi\u0005\u0004\u0000\u0000"+
		"ik\u0003\u0010\b\u0000jh\u0001\u0000\u0000\u0000kn\u0001\u0000\u0000\u0000"+
		"lj\u0001\u0000\u0000\u0000lm\u0001\u0000\u0000\u0000mo\u0001\u0000\u0000"+
		"\u0000nl\u0001\u0000\u0000\u0000op\u0005W\u0000\u0000p\u000f\u0001\u0000"+
		"\u0000\u0000qr\u00050\u0000\u0000rs\u0005\u0005\u0000\u0000st\u0003\u0006"+
		"\u0003\u0000t\u0011\u0001\u0000\u0000\u0000uy\u0005X\u0000\u0000vx\u0003"+
		"\u0014\n\u0000wv\u0001\u0000\u0000\u0000x{\u0001\u0000\u0000\u0000yw\u0001"+
		"\u0000\u0000\u0000yz\u0001\u0000\u0000\u0000z|\u0001\u0000\u0000\u0000"+
		"{y\u0001\u0000\u0000\u0000|\u007f\u0005Y\u0000\u0000}\u007f\u0003\u0014"+
		"\n\u0000~u\u0001\u0000\u0000\u0000~}\u0001\u0000\u0000\u0000\u007f\u0013"+
		"\u0001\u0000\u0000\u0000\u0080\u0081\u0003\"\u0011\u0000\u0081\u0015\u0001"+
		"\u0000\u0000\u0000\u0082\u0084\u0003\u0018\f\u0000\u0083\u0082\u0001\u0000"+
		"\u0000\u0000\u0084\u0087\u0001\u0000\u0000\u0000\u0085\u0083\u0001\u0000"+
		"\u0000\u0000\u0085\u0086\u0001\u0000\u0000\u0000\u0086\u0017\u0001\u0000"+
		"\u0000\u0000\u0087\u0085\u0001\u0000\u0000\u0000\u0088\u008d\u0003\u001a"+
		"\r\u0000\u0089\u008d\u0003\u001c\u000e\u0000\u008a\u008d\u0003\u001e\u000f"+
		"\u0000\u008b\u008d\u0003 \u0010\u0000\u008c\u0088\u0001\u0000\u0000\u0000"+
		"\u008c\u0089\u0001\u0000\u0000\u0000\u008c\u008a\u0001\u0000\u0000\u0000"+
		"\u008c\u008b\u0001\u0000\u0000\u0000\u008d\u0019\u0001\u0000\u0000\u0000"+
		"\u008e\u008f\u0005#\u0000\u0000\u008f\u0090\u0003\u0004\u0002\u0000\u0090"+
		"\u001b\u0001\u0000\u0000\u0000\u0091\u0092\u0005\u0019\u0000\u0000\u0092"+
		"\u0093\u0003\u0004\u0002\u0000\u0093\u001d\u0001\u0000\u0000\u0000\u0094"+
		"\u0095\u0003\u0002\u0001\u0000\u0095\u0097\u0005\u0017\u0000\u0000\u0096"+
		"\u0098\u0003\f\u0006\u0000\u0097\u0096\u0001\u0000\u0000\u0000\u0097\u0098"+
		"\u0001\u0000\u0000\u0000\u0098\u009c\u0001\u0000\u0000\u0000\u0099\u009a"+
		"\u0003\u0004\u0002\u0000\u009a\u009b\u0005\u0006\u0000\u0000\u009b\u009d"+
		"\u0001\u0000\u0000\u0000\u009c\u0099\u0001\u0000\u0000\u0000\u009c\u009d"+
		"\u0001\u0000\u0000\u0000\u009d\u009e\u0001\u0000\u0000\u0000\u009e\u009f"+
		"\u00050\u0000\u0000\u009f\u00a2\u0003\u000e\u0007\u0000\u00a0\u00a1\u0005"+
		"\u0005\u0000\u0000\u00a1\u00a3\u0003\u0006\u0003\u0000\u00a2\u00a0\u0001"+
		"\u0000\u0000\u0000\u00a2\u00a3\u0001\u0000\u0000\u0000\u00a3\u00a4\u0001"+
		"\u0000\u0000\u0000\u00a4\u00a5\u0003\u0012\t\u0000\u00a5\u001f\u0001\u0000"+
		"\u0000\u0000\u00a6\u00a9\u0003\u0002\u0001\u0000\u00a7\u00aa\u0005.\u0000"+
		"\u0000\u00a8\u00aa\u0005-\u0000\u0000\u00a9\u00a7\u0001\u0000\u0000\u0000"+
		"\u00a9\u00a8\u0001\u0000\u0000\u0000\u00aa\u00ae\u0001\u0000\u0000\u0000"+
		"\u00ab\u00ac\u0003\u0004\u0002\u0000\u00ac\u00ad\u0005\u0006\u0000\u0000"+
		"\u00ad\u00af\u0001\u0000\u0000\u0000\u00ae\u00ab\u0001\u0000\u0000\u0000"+
		"\u00ae\u00af\u0001\u0000\u0000\u0000\u00af\u00b0\u0001\u0000\u0000\u0000"+
		"\u00b0\u00b3\u00050\u0000\u0000\u00b1\u00b2\u0005\u0005\u0000\u0000\u00b2"+
		"\u00b4\u0003\u0006\u0003\u0000\u00b3\u00b1\u0001\u0000\u0000\u0000\u00b3"+
		"\u00b4\u0001\u0000\u0000\u0000\u00b4\u00b7\u0001\u0000\u0000\u0000\u00b5"+
		"\u00b6\u0005U\u0000\u0000\u00b6\u00b8\u0003.\u0017\u0000\u00b7\u00b5\u0001"+
		"\u0000\u0000\u0000\u00b7\u00b8\u0001\u0000\u0000\u0000\u00b8!\u0001\u0000"+
		"\u0000\u0000\u00b9\u00ba\u0003$\u0012\u0000\u00ba#\u0001\u0000\u0000\u0000"+
		"\u00bb\u00be\u0003\u0002\u0001\u0000\u00bc\u00bf\u0005.\u0000\u0000\u00bd"+
		"\u00bf\u0005-\u0000\u0000\u00be\u00bc\u0001\u0000\u0000\u0000\u00be\u00bd"+
		"\u0001\u0000\u0000\u0000\u00bf\u00c3\u0001\u0000\u0000\u0000\u00c0\u00c1"+
		"\u0003\u0004\u0002\u0000\u00c1\u00c2\u0005\u0006\u0000\u0000\u00c2\u00c4"+
		"\u0001\u0000\u0000\u0000\u00c3\u00c0\u0001\u0000\u0000\u0000\u00c3\u00c4"+
		"\u0001\u0000\u0000\u0000\u00c4\u00c5\u0001\u0000\u0000\u0000\u00c5\u00c8"+
		"\u00050\u0000\u0000\u00c6\u00c7\u0005\u0005\u0000\u0000\u00c7\u00c9\u0003"+
		"\u0006\u0003\u0000\u00c8\u00c6\u0001\u0000\u0000\u0000\u00c8\u00c9\u0001"+
		"\u0000\u0000\u0000\u00c9\u00cc\u0001\u0000\u0000\u0000\u00ca\u00cb\u0005"+
		"U\u0000\u0000\u00cb\u00cd\u0003.\u0017\u0000\u00cc\u00ca\u0001\u0000\u0000"+
		"\u0000\u00cc\u00cd\u0001\u0000\u0000\u0000\u00cd%\u0001\u0000\u0000\u0000"+
		"\u00ce\u00cf\u0005/\u0000\u0000\u00cf\u00d0\u0005V\u0000\u0000\u00d0\u00d1"+
		"\u0003.\u0017\u0000\u00d1\u00d2\u0005W\u0000\u0000\u00d2\u00d3\u0003\u0012"+
		"\t\u0000\u00d3\'\u0001\u0000\u0000\u0000\u00d4\u00d5\u0005\u0011\u0000"+
		"\u0000\u00d5\u00d6\u0003\u0012\t\u0000\u00d6\u00d7\u0005/\u0000\u0000"+
		"\u00d7\u00d8\u0005V\u0000\u0000\u00d8\u00d9\u0003.\u0017\u0000\u00d9\u00da"+
		"\u0005W\u0000\u0000\u00da)\u0001\u0000\u0000\u0000\u00db\u00dc\u0005\u0016"+
		"\u0000\u0000\u00dc\u00dd\u0005V\u0000\u0000\u00dd\u00de\u0003\"\u0011"+
		"\u0000\u00de\u00df\u0005\u0003\u0000\u0000\u00df\u00e0\u0003.\u0017\u0000"+
		"\u00e0\u00e1\u0005\u0003\u0000\u0000\u00e1\u00e2\u0003.\u0017\u0000\u00e2"+
		"\u00e3\u0003\u0012\t\u0000\u00e3+\u0001\u0000\u0000\u0000\u00e4\u00e5"+
		"\u0005\u0018\u0000\u0000\u00e5\u00e6\u0005V\u0000\u0000\u00e6\u00e7\u0003"+
		".\u0017\u0000\u00e7\u00e8\u0005W\u0000\u0000\u00e8\u00e9\u0003\u0012\t"+
		"\u0000\u00e9\u00f3\u0001\u0000\u0000\u0000\u00ea\u00eb\u0005\u0018\u0000"+
		"\u0000\u00eb\u00ec\u0005V\u0000\u0000\u00ec\u00ed\u0003.\u0017\u0000\u00ed"+
		"\u00ee\u0005W\u0000\u0000\u00ee\u00ef\u0003\u0012\t\u0000\u00ef\u00f0"+
		"\u0005\u0012\u0000\u0000\u00f0\u00f1\u0003\u0012\t\u0000\u00f1\u00f3\u0001"+
		"\u0000\u0000\u0000\u00f2\u00e4\u0001\u0000\u0000\u0000\u00f2\u00ea\u0001"+
		"\u0000\u0000\u0000\u00f3-\u0001\u0000\u0000\u0000\u00f4\u00f5\u0005\f"+
		"\u0000\u0000\u00f5/\u0001\u0000\u0000\u0000\u00f6\u00fc\u00032\u0019\u0000"+
		"\u00f7\u00f8\u00032\u0019\u0000\u00f8\u00f9\u0005\u0006\u0000\u0000\u00f9"+
		"\u00fa\u00030\u0018\u0000\u00fa\u00fc\u0001\u0000\u0000\u0000\u00fb\u00f6"+
		"\u0001\u0000\u0000\u0000\u00fb\u00f7\u0001\u0000\u0000\u0000\u00fc1\u0001"+
		"\u0000\u0000\u0000\u00fd\u00fe\u0006\u0019\uffff\uffff\u0000\u00fe\u0104"+
		"\u00034\u001a\u0000\u00ff\u0100\u0005<\u0000\u0000\u0100\u0104\u00032"+
		"\u0019\u0004\u0101\u0102\u0005=\u0000\u0000\u0102\u0104\u00032\u0019\u0002"+
		"\u0103\u00fd\u0001\u0000\u0000\u0000\u0103\u00ff\u0001\u0000\u0000\u0000"+
		"\u0103\u0101\u0001\u0000\u0000\u0000\u0104\u010b\u0001\u0000\u0000\u0000"+
		"\u0105\u0106\n\u0003\u0000\u0000\u0106\u010a\u0005<\u0000\u0000\u0107"+
		"\u0108\n\u0001\u0000\u0000\u0108\u010a\u0005=\u0000\u0000\u0109\u0105"+
		"\u0001\u0000\u0000\u0000\u0109\u0107\u0001\u0000\u0000\u0000\u010a\u010d"+
		"\u0001\u0000\u0000\u0000\u010b\u0109\u0001\u0000\u0000\u0000\u010b\u010c"+
		"\u0001\u0000\u0000\u0000\u010c3\u0001\u0000\u0000\u0000\u010d\u010b\u0001"+
		"\u0000\u0000\u0000\u010e\u0118\u00036\u001b\u0000\u010f\u0110\u0005B\u0000"+
		"\u0000\u0110\u0118\u00034\u001a\u0000\u0111\u0112\u0005C\u0000\u0000\u0112"+
		"\u0118\u00034\u001a\u0000\u0113\u0114\u0005M\u0000\u0000\u0114\u0118\u0003"+
		"4\u001a\u0000\u0115\u0116\u0005Q\u0000\u0000\u0116\u0118\u00034\u001a"+
		"\u0000\u0117\u010e\u0001\u0000\u0000\u0000\u0117\u010f\u0001\u0000\u0000"+
		"\u0000\u0117\u0111\u0001\u0000\u0000\u0000\u0117\u0113\u0001\u0000\u0000"+
		"\u0000\u0117\u0115\u0001\u0000\u0000\u0000\u01185\u0001\u0000\u0000\u0000"+
		"\u0119\u011f\u00038\u001c\u0000\u011a\u011b\u0005V\u0000\u0000\u011b\u011c"+
		"\u0003.\u0017\u0000\u011c\u011d\u0005W\u0000\u0000\u011d\u011f\u0001\u0000"+
		"\u0000\u0000\u011e\u0119\u0001\u0000\u0000\u0000\u011e\u011a\u0001\u0000"+
		"\u0000\u0000\u011f7\u0001\u0000\u0000\u0000\u0120\u012d\u00053\u0000\u0000"+
		"\u0121\u012d\u00052\u0000\u0000\u0122\u012d\u0005\u000b\u0000\u0000\u0123"+
		"\u012d\u0005\u0007\u0000\u0000\u0124\u012d\u0005\n\u0000\u0000\u0125\u012d"+
		"\u0005\b\u0000\u0000\u0126\u012d\u0005\t\u0000\u0000\u0127\u012d\u0005"+
		"0\u0000\u0000\u0128\u012d\u0005,\u0000\u0000\u0129\u012d\u0005\u0014\u0000"+
		"\u0000\u012a\u012d\u0005+\u0000\u0000\u012b\u012d\u0005)\u0000\u0000\u012c"+
		"\u0120\u0001\u0000\u0000\u0000\u012c\u0121\u0001\u0000\u0000\u0000\u012c"+
		"\u0122\u0001\u0000\u0000\u0000\u012c\u0123\u0001\u0000\u0000\u0000\u012c"+
		"\u0124\u0001\u0000\u0000\u0000\u012c\u0125\u0001\u0000\u0000\u0000\u012c"+
		"\u0126\u0001\u0000\u0000\u0000\u012c\u0127\u0001\u0000\u0000\u0000\u012c"+
		"\u0128\u0001\u0000\u0000\u0000\u012c\u0129\u0001\u0000\u0000\u0000\u012c"+
		"\u012a\u0001\u0000\u0000\u0000\u012c\u012b\u0001\u0000\u0000\u0000\u012d"+
		"9\u0001\u0000\u0000\u0000\u001d?FMTaly~\u0085\u008c\u0097\u009c\u00a2"+
		"\u00a9\u00ae\u00b3\u00b7\u00be\u00c3\u00c8\u00cc\u00f2\u00fb\u0103\u0109"+
		"\u010b\u0117\u011e\u012c";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}