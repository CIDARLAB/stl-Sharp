// Generated from STLflat.g4 by ANTLR 4.5.3

/**
 * Copyright (C) 2015  Cristian Ioan Vasile <cvasile@bu.edu>
 * Hybrid and Networked Systems (HyNeSs) Group, BU Robotics Lab, Boston University
 * See license.txt file for license information.
 */
package hyness.stl.grammar.flat;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class STLflatParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		BOOLEAN=39, VARIABLE=40, RATIONAL=41, WS=42, NEWLINE=43;
	public static final int
		RULE_specification = 0, RULE_module = 1, RULE_moduleDescription = 2, RULE_property = 3, 
		RULE_expr = 4, RULE_booleanExpr = 5, RULE_translationMap = 6, RULE_translationPair = 7, 
		RULE_limitMap = 8, RULE_limitPair = 9;
	public static final String[] ruleNames = {
		"specification", "module", "moduleDescription", "property", "expr", "booleanExpr", 
		"translationMap", "translationPair", "limitMap", "limitPair"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'=>'", "'_'", "'&&'", "'||'", "'>>'", "'#'", "'('", "','", "')'", 
		"'='", "'!'", "'F'", "'['", "']'", "'G'", "'U'", "'-('", "'^'", "'sqrt('", 
		"'log('", "'ln('", "'abs('", "'der('", "'int('", "'*'", "'/'", "'+'", 
		"'-'", "'<'", "'<='", "'>='", "'>'", "'{'", "'}'", "'@'", "':'", "'max'", 
		"'min'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, "BOOLEAN", "VARIABLE", "RATIONAL", "WS", "NEWLINE"
	};
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
	public String getGrammarFileName() { return "STLflat.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public STLflatParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class SpecificationContext extends ParserRuleContext {
		public ModuleContext spec;
		public List<TerminalNode> NEWLINE() { return getTokens(STLflatParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(STLflatParser.NEWLINE, i);
		}
		public ModuleContext module() {
			return getRuleContext(ModuleContext.class,0);
		}
		public LimitMapContext limitMap() {
			return getRuleContext(LimitMapContext.class,0);
		}
		public List<ModuleDescriptionContext> moduleDescription() {
			return getRuleContexts(ModuleDescriptionContext.class);
		}
		public ModuleDescriptionContext moduleDescription(int i) {
			return getRuleContext(ModuleDescriptionContext.class,i);
		}
		public List<TranslationMapContext> translationMap() {
			return getRuleContexts(TranslationMapContext.class);
		}
		public TranslationMapContext translationMap(int i) {
			return getRuleContext(TranslationMapContext.class,i);
		}
		public SpecificationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_specification; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STLflatListener ) ((STLflatListener)listener).enterSpecification(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STLflatListener ) ((STLflatListener)listener).exitSpecification(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STLflatVisitor ) return ((STLflatVisitor<? extends T>)visitor).visitSpecification(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SpecificationContext specification() throws RecognitionException {
		SpecificationContext _localctx = new SpecificationContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_specification);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(20);
			((SpecificationContext)_localctx).spec = module(0);
			setState(21);
			match(NEWLINE);
			setState(22);
			match(NEWLINE);
			setState(26); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(23);
				moduleDescription();
				setState(24);
				match(NEWLINE);
				}
				}
				setState(28); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==VARIABLE );
			setState(30);
			match(NEWLINE);
			setState(34); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(31);
					translationMap();
					setState(32);
					match(NEWLINE);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(36); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			{
			setState(38);
			limitMap();
			setState(39);
			match(NEWLINE);
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

	public static class ModuleContext extends ParserRuleContext {
		public ModuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_module; }
	 
		public ModuleContext() { }
		public void copyFrom(ModuleContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ModuleLeafContext extends ModuleContext {
		public Token moduleName;
		public List<TerminalNode> VARIABLE() { return getTokens(STLflatParser.VARIABLE); }
		public TerminalNode VARIABLE(int i) {
			return getToken(STLflatParser.VARIABLE, i);
		}
		public ModuleLeafContext(ModuleContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STLflatListener ) ((STLflatListener)listener).enterModuleLeaf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STLflatListener ) ((STLflatListener)listener).exitModuleLeaf(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STLflatVisitor ) return ((STLflatVisitor<? extends T>)visitor).visitModuleLeaf(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ModuleOpContext extends ModuleContext {
		public ModuleContext left;
		public Token op;
		public Token tmap;
		public ModuleContext right;
		public List<ModuleContext> module() {
			return getRuleContexts(ModuleContext.class);
		}
		public ModuleContext module(int i) {
			return getRuleContext(ModuleContext.class,i);
		}
		public TerminalNode VARIABLE() { return getToken(STLflatParser.VARIABLE, 0); }
		public ModuleOpContext(ModuleContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STLflatListener ) ((STLflatListener)listener).enterModuleOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STLflatListener ) ((STLflatListener)listener).exitModuleOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STLflatVisitor ) return ((STLflatVisitor<? extends T>)visitor).visitModuleOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModuleContext module() throws RecognitionException {
		return module(0);
	}

	private ModuleContext module(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ModuleContext _localctx = new ModuleContext(_ctx, _parentState);
		ModuleContext _prevctx = _localctx;
		int _startState = 2;
		enterRecursionRule(_localctx, 2, RULE_module, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new ModuleLeafContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(42);
			((ModuleLeafContext)_localctx).moduleName = match(VARIABLE);
			setState(43);
			match(T__6);
			setState(44);
			match(VARIABLE);
			setState(49);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__7) {
				{
				{
				setState(45);
				match(T__7);
				setState(46);
				match(VARIABLE);
				}
				}
				setState(51);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(52);
			match(T__8);
			}
			_ctx.stop = _input.LT(-1);
			setState(81);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(79);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
					case 1:
						{
						_localctx = new ModuleOpContext(new ModuleContext(_parentctx, _parentState));
						((ModuleOpContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_module);
						setState(54);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(55);
						((ModuleOpContext)_localctx).op = match(T__0);
						setState(56);
						match(T__1);
						setState(57);
						((ModuleOpContext)_localctx).tmap = match(VARIABLE);
						setState(58);
						((ModuleOpContext)_localctx).right = module(7);
						}
						break;
					case 2:
						{
						_localctx = new ModuleOpContext(new ModuleContext(_parentctx, _parentState));
						((ModuleOpContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_module);
						setState(59);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(60);
						((ModuleOpContext)_localctx).op = match(T__2);
						setState(61);
						match(T__1);
						setState(62);
						((ModuleOpContext)_localctx).tmap = match(VARIABLE);
						setState(63);
						((ModuleOpContext)_localctx).right = module(6);
						}
						break;
					case 3:
						{
						_localctx = new ModuleOpContext(new ModuleContext(_parentctx, _parentState));
						((ModuleOpContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_module);
						setState(64);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(65);
						((ModuleOpContext)_localctx).op = match(T__3);
						setState(66);
						match(T__1);
						setState(67);
						((ModuleOpContext)_localctx).tmap = match(VARIABLE);
						setState(68);
						((ModuleOpContext)_localctx).right = module(5);
						}
						break;
					case 4:
						{
						_localctx = new ModuleOpContext(new ModuleContext(_parentctx, _parentState));
						((ModuleOpContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_module);
						setState(69);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(70);
						((ModuleOpContext)_localctx).op = match(T__4);
						setState(71);
						match(T__1);
						setState(72);
						((ModuleOpContext)_localctx).tmap = match(VARIABLE);
						setState(73);
						((ModuleOpContext)_localctx).right = module(4);
						}
						break;
					case 5:
						{
						_localctx = new ModuleOpContext(new ModuleContext(_parentctx, _parentState));
						((ModuleOpContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_module);
						setState(74);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(75);
						((ModuleOpContext)_localctx).op = match(T__5);
						setState(76);
						match(T__1);
						setState(77);
						((ModuleOpContext)_localctx).tmap = match(VARIABLE);
						setState(78);
						((ModuleOpContext)_localctx).right = module(3);
						}
						break;
					}
					} 
				}
				setState(83);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
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

	public static class ModuleDescriptionContext extends ParserRuleContext {
		public Token moduleName;
		public PropertyContext moduleFormula;
		public TerminalNode VARIABLE() { return getToken(STLflatParser.VARIABLE, 0); }
		public PropertyContext property() {
			return getRuleContext(PropertyContext.class,0);
		}
		public ModuleDescriptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_moduleDescription; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STLflatListener ) ((STLflatListener)listener).enterModuleDescription(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STLflatListener ) ((STLflatListener)listener).exitModuleDescription(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STLflatVisitor ) return ((STLflatVisitor<? extends T>)visitor).visitModuleDescription(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModuleDescriptionContext moduleDescription() throws RecognitionException {
		ModuleDescriptionContext _localctx = new ModuleDescriptionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_moduleDescription);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			((ModuleDescriptionContext)_localctx).moduleName = match(VARIABLE);
			setState(85);
			match(T__9);
			setState(86);
			((ModuleDescriptionContext)_localctx).moduleFormula = property(0);
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

	public static class PropertyContext extends ParserRuleContext {
		public PropertyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_property; }
	 
		public PropertyContext() { }
		public void copyFrom(PropertyContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BooleanPredContext extends PropertyContext {
		public BooleanExprContext booleanExpr() {
			return getRuleContext(BooleanExprContext.class,0);
		}
		public BooleanPredContext(PropertyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STLflatListener ) ((STLflatListener)listener).enterBooleanPred(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STLflatListener ) ((STLflatListener)listener).exitBooleanPred(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STLflatVisitor ) return ((STLflatVisitor<? extends T>)visitor).visitBooleanPred(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FormulaContext extends PropertyContext {
		public PropertyContext left;
		public Token op;
		public PropertyContext child;
		public Token low;
		public Token high;
		public PropertyContext right;
		public List<PropertyContext> property() {
			return getRuleContexts(PropertyContext.class);
		}
		public PropertyContext property(int i) {
			return getRuleContext(PropertyContext.class,i);
		}
		public List<TerminalNode> RATIONAL() { return getTokens(STLflatParser.RATIONAL); }
		public TerminalNode RATIONAL(int i) {
			return getToken(STLflatParser.RATIONAL, i);
		}
		public FormulaContext(PropertyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STLflatListener ) ((STLflatListener)listener).enterFormula(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STLflatListener ) ((STLflatListener)listener).exitFormula(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STLflatVisitor ) return ((STLflatVisitor<? extends T>)visitor).visitFormula(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParpropContext extends PropertyContext {
		public PropertyContext child;
		public PropertyContext property() {
			return getRuleContext(PropertyContext.class,0);
		}
		public ParpropContext(PropertyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STLflatListener ) ((STLflatListener)listener).enterParprop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STLflatListener ) ((STLflatListener)listener).exitParprop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STLflatVisitor ) return ((STLflatVisitor<? extends T>)visitor).visitParprop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PropertyContext property() throws RecognitionException {
		return property(0);
	}

	private PropertyContext property(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		PropertyContext _localctx = new PropertyContext(_ctx, _parentState);
		PropertyContext _prevctx = _localctx;
		int _startState = 6;
		enterRecursionRule(_localctx, 6, RULE_property, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				_localctx = new ParpropContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(89);
				match(T__6);
				setState(90);
				((ParpropContext)_localctx).child = property(0);
				setState(91);
				match(T__8);
				}
				break;
			case 2:
				{
				_localctx = new BooleanPredContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(93);
				booleanExpr();
				}
				break;
			case 3:
				{
				_localctx = new FormulaContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(94);
				((FormulaContext)_localctx).op = match(T__10);
				setState(95);
				((FormulaContext)_localctx).child = property(9);
				}
				break;
			case 4:
				{
				_localctx = new FormulaContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(96);
				((FormulaContext)_localctx).op = match(T__11);
				setState(97);
				match(T__12);
				setState(98);
				((FormulaContext)_localctx).low = match(RATIONAL);
				setState(99);
				match(T__7);
				setState(100);
				((FormulaContext)_localctx).high = match(RATIONAL);
				setState(101);
				match(T__13);
				setState(102);
				((FormulaContext)_localctx).child = property(8);
				}
				break;
			case 5:
				{
				_localctx = new FormulaContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(103);
				((FormulaContext)_localctx).op = match(T__14);
				setState(104);
				match(T__12);
				setState(105);
				((FormulaContext)_localctx).low = match(RATIONAL);
				setState(106);
				match(T__7);
				setState(107);
				((FormulaContext)_localctx).high = match(RATIONAL);
				setState(108);
				match(T__13);
				setState(109);
				((FormulaContext)_localctx).child = property(7);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(137);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(135);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
					case 1:
						{
						_localctx = new FormulaContext(new PropertyContext(_parentctx, _parentState));
						((FormulaContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_property);
						setState(112);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(113);
						((FormulaContext)_localctx).op = match(T__0);
						setState(114);
						((FormulaContext)_localctx).right = property(7);
						}
						break;
					case 2:
						{
						_localctx = new FormulaContext(new PropertyContext(_parentctx, _parentState));
						((FormulaContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_property);
						setState(115);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(116);
						((FormulaContext)_localctx).op = match(T__2);
						setState(117);
						((FormulaContext)_localctx).right = property(6);
						}
						break;
					case 3:
						{
						_localctx = new FormulaContext(new PropertyContext(_parentctx, _parentState));
						((FormulaContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_property);
						setState(118);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(119);
						((FormulaContext)_localctx).op = match(T__3);
						setState(120);
						((FormulaContext)_localctx).right = property(5);
						}
						break;
					case 4:
						{
						_localctx = new FormulaContext(new PropertyContext(_parentctx, _parentState));
						((FormulaContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_property);
						setState(121);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(122);
						((FormulaContext)_localctx).op = match(T__4);
						setState(123);
						((FormulaContext)_localctx).right = property(4);
						}
						break;
					case 5:
						{
						_localctx = new FormulaContext(new PropertyContext(_parentctx, _parentState));
						((FormulaContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_property);
						setState(124);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(125);
						((FormulaContext)_localctx).op = match(T__5);
						setState(126);
						((FormulaContext)_localctx).right = property(3);
						}
						break;
					case 6:
						{
						_localctx = new FormulaContext(new PropertyContext(_parentctx, _parentState));
						((FormulaContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_property);
						setState(127);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(128);
						((FormulaContext)_localctx).op = match(T__15);
						setState(129);
						match(T__12);
						setState(130);
						((FormulaContext)_localctx).low = match(RATIONAL);
						setState(131);
						match(T__7);
						setState(132);
						((FormulaContext)_localctx).high = match(RATIONAL);
						setState(133);
						match(T__13);
						setState(134);
						((FormulaContext)_localctx).right = property(2);
						}
						break;
					}
					} 
				}
				setState(139);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
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

	public static class ExprContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode RATIONAL() { return getToken(STLflatParser.RATIONAL, 0); }
		public TerminalNode VARIABLE() { return getToken(STLflatParser.VARIABLE, 0); }
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STLflatListener ) ((STLflatListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STLflatListener ) ((STLflatListener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STLflatVisitor ) return ((STLflatVisitor<? extends T>)visitor).visitExpr(this);
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
		int _startState = 8;
		enterRecursionRule(_localctx, 8, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
			switch (_input.LA(1)) {
			case T__6:
			case T__16:
				{
				setState(141);
				_la = _input.LA(1);
				if ( !(_la==T__6 || _la==T__16) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(142);
				expr(0);
				setState(143);
				match(T__8);
				}
				break;
			case T__18:
			case T__19:
			case T__20:
			case T__21:
			case T__22:
			case T__23:
				{
				setState(145);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23))) != 0)) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(146);
				expr(0);
				setState(147);
				match(T__8);
				}
				break;
			case RATIONAL:
				{
				setState(149);
				match(RATIONAL);
				}
				break;
			case VARIABLE:
				{
				setState(150);
				match(VARIABLE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(164);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(162);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(153);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(154);
						match(T__17);
						setState(155);
						expr(7);
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(156);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(157);
						_la = _input.LA(1);
						if ( !(_la==T__24 || _la==T__25) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(158);
						expr(5);
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(159);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(160);
						_la = _input.LA(1);
						if ( !(_la==T__26 || _la==T__27) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(161);
						expr(4);
						}
						break;
					}
					} 
				}
				setState(166);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
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

	public static class BooleanExprContext extends ParserRuleContext {
		public ExprContext left;
		public Token op;
		public ExprContext right;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode BOOLEAN() { return getToken(STLflatParser.BOOLEAN, 0); }
		public BooleanExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_booleanExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STLflatListener ) ((STLflatListener)listener).enterBooleanExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STLflatListener ) ((STLflatListener)listener).exitBooleanExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STLflatVisitor ) return ((STLflatVisitor<? extends T>)visitor).visitBooleanExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BooleanExprContext booleanExpr() throws RecognitionException {
		BooleanExprContext _localctx = new BooleanExprContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_booleanExpr);
		int _la;
		try {
			setState(172);
			switch (_input.LA(1)) {
			case T__6:
			case T__16:
			case T__18:
			case T__19:
			case T__20:
			case T__21:
			case T__22:
			case T__23:
			case VARIABLE:
			case RATIONAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(167);
				((BooleanExprContext)_localctx).left = expr(0);
				setState(168);
				((BooleanExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__9) | (1L << T__28) | (1L << T__29) | (1L << T__30) | (1L << T__31))) != 0)) ) {
					((BooleanExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(169);
				((BooleanExprContext)_localctx).right = expr(0);
				}
				break;
			case BOOLEAN:
				enterOuterAlt(_localctx, 2);
				{
				setState(171);
				((BooleanExprContext)_localctx).op = match(BOOLEAN);
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

	public static class TranslationMapContext extends ParserRuleContext {
		public Token tmapName;
		public List<TranslationPairContext> translationPair() {
			return getRuleContexts(TranslationPairContext.class);
		}
		public TranslationPairContext translationPair(int i) {
			return getRuleContext(TranslationPairContext.class,i);
		}
		public TerminalNode VARIABLE() { return getToken(STLflatParser.VARIABLE, 0); }
		public TranslationMapContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_translationMap; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STLflatListener ) ((STLflatListener)listener).enterTranslationMap(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STLflatListener ) ((STLflatListener)listener).exitTranslationMap(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STLflatVisitor ) return ((STLflatVisitor<? extends T>)visitor).visitTranslationMap(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TranslationMapContext translationMap() throws RecognitionException {
		TranslationMapContext _localctx = new TranslationMapContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_translationMap);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(174);
			((TranslationMapContext)_localctx).tmapName = match(VARIABLE);
			setState(175);
			match(T__32);
			setState(176);
			translationPair();
			setState(181);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__7) {
				{
				{
				setState(177);
				match(T__7);
				setState(178);
				translationPair();
				}
				}
				setState(183);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(184);
			match(T__33);
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

	public static class TranslationPairContext extends ParserRuleContext {
		public Token key;
		public Token moduleName;
		public Token value;
		public List<TerminalNode> VARIABLE() { return getTokens(STLflatParser.VARIABLE); }
		public TerminalNode VARIABLE(int i) {
			return getToken(STLflatParser.VARIABLE, i);
		}
		public TranslationPairContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_translationPair; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STLflatListener ) ((STLflatListener)listener).enterTranslationPair(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STLflatListener ) ((STLflatListener)listener).exitTranslationPair(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STLflatVisitor ) return ((STLflatVisitor<? extends T>)visitor).visitTranslationPair(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TranslationPairContext translationPair() throws RecognitionException {
		TranslationPairContext _localctx = new TranslationPairContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_translationPair);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(186);
			((TranslationPairContext)_localctx).key = match(VARIABLE);
			setState(189);
			_la = _input.LA(1);
			if (_la==T__34) {
				{
				setState(187);
				match(T__34);
				setState(188);
				((TranslationPairContext)_localctx).moduleName = match(VARIABLE);
				}
			}

			setState(191);
			match(T__35);
			setState(192);
			((TranslationPairContext)_localctx).value = match(VARIABLE);
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

	public static class LimitMapContext extends ParserRuleContext {
		public Token lmapName;
		public List<LimitPairContext> limitPair() {
			return getRuleContexts(LimitPairContext.class);
		}
		public LimitPairContext limitPair(int i) {
			return getRuleContext(LimitPairContext.class,i);
		}
		public TerminalNode VARIABLE() { return getToken(STLflatParser.VARIABLE, 0); }
		public LimitMapContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_limitMap; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STLflatListener ) ((STLflatListener)listener).enterLimitMap(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STLflatListener ) ((STLflatListener)listener).exitLimitMap(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STLflatVisitor ) return ((STLflatVisitor<? extends T>)visitor).visitLimitMap(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LimitMapContext limitMap() throws RecognitionException {
		LimitMapContext _localctx = new LimitMapContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_limitMap);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(194);
			((LimitMapContext)_localctx).lmapName = match(VARIABLE);
			setState(195);
			match(T__12);
			setState(196);
			limitPair();
			setState(201);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__7) {
				{
				{
				setState(197);
				match(T__7);
				setState(198);
				limitPair();
				}
				}
				setState(203);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(204);
			match(T__13);
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

	public static class LimitPairContext extends ParserRuleContext {
		public Token sigName;
		public Token maxValue;
		public Token minValue;
		public TerminalNode VARIABLE() { return getToken(STLflatParser.VARIABLE, 0); }
		public List<TerminalNode> RATIONAL() { return getTokens(STLflatParser.RATIONAL); }
		public TerminalNode RATIONAL(int i) {
			return getToken(STLflatParser.RATIONAL, i);
		}
		public LimitPairContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_limitPair; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STLflatListener ) ((STLflatListener)listener).enterLimitPair(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STLflatListener ) ((STLflatListener)listener).exitLimitPair(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STLflatVisitor ) return ((STLflatVisitor<? extends T>)visitor).visitLimitPair(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LimitPairContext limitPair() throws RecognitionException {
		LimitPairContext _localctx = new LimitPairContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_limitPair);
		try {
			setState(232);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(206);
				match(T__32);
				setState(207);
				((LimitPairContext)_localctx).sigName = match(VARIABLE);
				setState(208);
				match(T__35);
				setState(209);
				match(T__32);
				setState(210);
				match(T__36);
				setState(211);
				match(T__35);
				setState(212);
				((LimitPairContext)_localctx).maxValue = match(RATIONAL);
				setState(213);
				match(T__7);
				setState(214);
				match(T__37);
				setState(215);
				match(T__35);
				setState(216);
				((LimitPairContext)_localctx).minValue = match(RATIONAL);
				setState(217);
				match(T__33);
				setState(218);
				match(T__33);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(219);
				match(T__32);
				setState(220);
				((LimitPairContext)_localctx).sigName = match(VARIABLE);
				setState(221);
				match(T__35);
				setState(222);
				match(T__32);
				setState(223);
				match(T__37);
				setState(224);
				match(T__35);
				setState(225);
				((LimitPairContext)_localctx).minValue = match(RATIONAL);
				setState(226);
				match(T__7);
				setState(227);
				match(T__36);
				setState(228);
				match(T__35);
				setState(229);
				((LimitPairContext)_localctx).maxValue = match(RATIONAL);
				setState(230);
				match(T__33);
				setState(231);
				match(T__33);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 1:
			return module_sempred((ModuleContext)_localctx, predIndex);
		case 3:
			return property_sempred((PropertyContext)_localctx, predIndex);
		case 4:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean module_sempred(ModuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 6);
		case 1:
			return precpred(_ctx, 5);
		case 2:
			return precpred(_ctx, 4);
		case 3:
			return precpred(_ctx, 3);
		case 4:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean property_sempred(PropertyContext _localctx, int predIndex) {
		switch (predIndex) {
		case 5:
			return precpred(_ctx, 6);
		case 6:
			return precpred(_ctx, 5);
		case 7:
			return precpred(_ctx, 4);
		case 8:
			return precpred(_ctx, 3);
		case 9:
			return precpred(_ctx, 2);
		case 10:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 11:
			return precpred(_ctx, 6);
		case 12:
			return precpred(_ctx, 4);
		case 13:
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3-\u00ed\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\3\2\3\2\3\2\3\2\3\2\3\2\6\2\35\n\2\r\2\16\2\36\3\2\3\2\3\2\3\2\6\2"+
		"%\n\2\r\2\16\2&\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\7\3\62\n\3\f\3\16"+
		"\3\65\13\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3R\n\3\f\3\16\3U\13"+
		"\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5q\n\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7"+
		"\5\u008a\n\5\f\5\16\5\u008d\13\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\5\6\u009a\n\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\7\6\u00a5\n\6"+
		"\f\6\16\6\u00a8\13\6\3\7\3\7\3\7\3\7\3\7\5\7\u00af\n\7\3\b\3\b\3\b\3\b"+
		"\3\b\7\b\u00b6\n\b\f\b\16\b\u00b9\13\b\3\b\3\b\3\t\3\t\3\t\5\t\u00c0\n"+
		"\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\7\n\u00ca\n\n\f\n\16\n\u00cd\13\n\3"+
		"\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13"+
		"\u00eb\n\13\3\13\2\5\4\b\n\f\2\4\6\b\n\f\16\20\22\24\2\7\4\2\t\t\23\23"+
		"\3\2\25\32\3\2\33\34\3\2\35\36\4\2\f\f\37\"\u00ff\2\26\3\2\2\2\4+\3\2"+
		"\2\2\6V\3\2\2\2\bp\3\2\2\2\n\u0099\3\2\2\2\f\u00ae\3\2\2\2\16\u00b0\3"+
		"\2\2\2\20\u00bc\3\2\2\2\22\u00c4\3\2\2\2\24\u00ea\3\2\2\2\26\27\5\4\3"+
		"\2\27\30\7-\2\2\30\34\7-\2\2\31\32\5\6\4\2\32\33\7-\2\2\33\35\3\2\2\2"+
		"\34\31\3\2\2\2\35\36\3\2\2\2\36\34\3\2\2\2\36\37\3\2\2\2\37 \3\2\2\2 "+
		"$\7-\2\2!\"\5\16\b\2\"#\7-\2\2#%\3\2\2\2$!\3\2\2\2%&\3\2\2\2&$\3\2\2\2"+
		"&\'\3\2\2\2\'(\3\2\2\2()\5\22\n\2)*\7-\2\2*\3\3\2\2\2+,\b\3\1\2,-\7*\2"+
		"\2-.\7\t\2\2.\63\7*\2\2/\60\7\n\2\2\60\62\7*\2\2\61/\3\2\2\2\62\65\3\2"+
		"\2\2\63\61\3\2\2\2\63\64\3\2\2\2\64\66\3\2\2\2\65\63\3\2\2\2\66\67\7\13"+
		"\2\2\67S\3\2\2\289\f\b\2\29:\7\3\2\2:;\7\4\2\2;<\7*\2\2<R\5\4\3\t=>\f"+
		"\7\2\2>?\7\5\2\2?@\7\4\2\2@A\7*\2\2AR\5\4\3\bBC\f\6\2\2CD\7\6\2\2DE\7"+
		"\4\2\2EF\7*\2\2FR\5\4\3\7GH\f\5\2\2HI\7\7\2\2IJ\7\4\2\2JK\7*\2\2KR\5\4"+
		"\3\6LM\f\4\2\2MN\7\b\2\2NO\7\4\2\2OP\7*\2\2PR\5\4\3\5Q8\3\2\2\2Q=\3\2"+
		"\2\2QB\3\2\2\2QG\3\2\2\2QL\3\2\2\2RU\3\2\2\2SQ\3\2\2\2ST\3\2\2\2T\5\3"+
		"\2\2\2US\3\2\2\2VW\7*\2\2WX\7\f\2\2XY\5\b\5\2Y\7\3\2\2\2Z[\b\5\1\2[\\"+
		"\7\t\2\2\\]\5\b\5\2]^\7\13\2\2^q\3\2\2\2_q\5\f\7\2`a\7\r\2\2aq\5\b\5\13"+
		"bc\7\16\2\2cd\7\17\2\2de\7+\2\2ef\7\n\2\2fg\7+\2\2gh\7\20\2\2hq\5\b\5"+
		"\nij\7\21\2\2jk\7\17\2\2kl\7+\2\2lm\7\n\2\2mn\7+\2\2no\7\20\2\2oq\5\b"+
		"\5\tpZ\3\2\2\2p_\3\2\2\2p`\3\2\2\2pb\3\2\2\2pi\3\2\2\2q\u008b\3\2\2\2"+
		"rs\f\b\2\2st\7\3\2\2t\u008a\5\b\5\tuv\f\7\2\2vw\7\5\2\2w\u008a\5\b\5\b"+
		"xy\f\6\2\2yz\7\6\2\2z\u008a\5\b\5\7{|\f\5\2\2|}\7\7\2\2}\u008a\5\b\5\6"+
		"~\177\f\4\2\2\177\u0080\7\b\2\2\u0080\u008a\5\b\5\5\u0081\u0082\f\3\2"+
		"\2\u0082\u0083\7\22\2\2\u0083\u0084\7\17\2\2\u0084\u0085\7+\2\2\u0085"+
		"\u0086\7\n\2\2\u0086\u0087\7+\2\2\u0087\u0088\7\20\2\2\u0088\u008a\5\b"+
		"\5\4\u0089r\3\2\2\2\u0089u\3\2\2\2\u0089x\3\2\2\2\u0089{\3\2\2\2\u0089"+
		"~\3\2\2\2\u0089\u0081\3\2\2\2\u008a\u008d\3\2\2\2\u008b\u0089\3\2\2\2"+
		"\u008b\u008c\3\2\2\2\u008c\t\3\2\2\2\u008d\u008b\3\2\2\2\u008e\u008f\b"+
		"\6\1\2\u008f\u0090\t\2\2\2\u0090\u0091\5\n\6\2\u0091\u0092\7\13\2\2\u0092"+
		"\u009a\3\2\2\2\u0093\u0094\t\3\2\2\u0094\u0095\5\n\6\2\u0095\u0096\7\13"+
		"\2\2\u0096\u009a\3\2\2\2\u0097\u009a\7+\2\2\u0098\u009a\7*\2\2\u0099\u008e"+
		"\3\2\2\2\u0099\u0093\3\2\2\2\u0099\u0097\3\2\2\2\u0099\u0098\3\2\2\2\u009a"+
		"\u00a6\3\2\2\2\u009b\u009c\f\b\2\2\u009c\u009d\7\24\2\2\u009d\u00a5\5"+
		"\n\6\t\u009e\u009f\f\6\2\2\u009f\u00a0\t\4\2\2\u00a0\u00a5\5\n\6\7\u00a1"+
		"\u00a2\f\5\2\2\u00a2\u00a3\t\5\2\2\u00a3\u00a5\5\n\6\6\u00a4\u009b\3\2"+
		"\2\2\u00a4\u009e\3\2\2\2\u00a4\u00a1\3\2\2\2\u00a5\u00a8\3\2\2\2\u00a6"+
		"\u00a4\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\13\3\2\2\2\u00a8\u00a6\3\2\2"+
		"\2\u00a9\u00aa\5\n\6\2\u00aa\u00ab\t\6\2\2\u00ab\u00ac\5\n\6\2\u00ac\u00af"+
		"\3\2\2\2\u00ad\u00af\7)\2\2\u00ae\u00a9\3\2\2\2\u00ae\u00ad\3\2\2\2\u00af"+
		"\r\3\2\2\2\u00b0\u00b1\7*\2\2\u00b1\u00b2\7#\2\2\u00b2\u00b7\5\20\t\2"+
		"\u00b3\u00b4\7\n\2\2\u00b4\u00b6\5\20\t\2\u00b5\u00b3\3\2\2\2\u00b6\u00b9"+
		"\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00ba\3\2\2\2\u00b9"+
		"\u00b7\3\2\2\2\u00ba\u00bb\7$\2\2\u00bb\17\3\2\2\2\u00bc\u00bf\7*\2\2"+
		"\u00bd\u00be\7%\2\2\u00be\u00c0\7*\2\2\u00bf\u00bd\3\2\2\2\u00bf\u00c0"+
		"\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1\u00c2\7&\2\2\u00c2\u00c3\7*\2\2\u00c3"+
		"\21\3\2\2\2\u00c4\u00c5\7*\2\2\u00c5\u00c6\7\17\2\2\u00c6\u00cb\5\24\13"+
		"\2\u00c7\u00c8\7\n\2\2\u00c8\u00ca\5\24\13\2\u00c9\u00c7\3\2\2\2\u00ca"+
		"\u00cd\3\2\2\2\u00cb\u00c9\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc\u00ce\3\2"+
		"\2\2\u00cd\u00cb\3\2\2\2\u00ce\u00cf\7\20\2\2\u00cf\23\3\2\2\2\u00d0\u00d1"+
		"\7#\2\2\u00d1\u00d2\7*\2\2\u00d2\u00d3\7&\2\2\u00d3\u00d4\7#\2\2\u00d4"+
		"\u00d5\7\'\2\2\u00d5\u00d6\7&\2\2\u00d6\u00d7\7+\2\2\u00d7\u00d8\7\n\2"+
		"\2\u00d8\u00d9\7(\2\2\u00d9\u00da\7&\2\2\u00da\u00db\7+\2\2\u00db\u00dc"+
		"\7$\2\2\u00dc\u00eb\7$\2\2\u00dd\u00de\7#\2\2\u00de\u00df\7*\2\2\u00df"+
		"\u00e0\7&\2\2\u00e0\u00e1\7#\2\2\u00e1\u00e2\7(\2\2\u00e2\u00e3\7&\2\2"+
		"\u00e3\u00e4\7+\2\2\u00e4\u00e5\7\n\2\2\u00e5\u00e6\7\'\2\2\u00e6\u00e7"+
		"\7&\2\2\u00e7\u00e8\7+\2\2\u00e8\u00e9\7$\2\2\u00e9\u00eb\7$\2\2\u00ea"+
		"\u00d0\3\2\2\2\u00ea\u00dd\3\2\2\2\u00eb\25\3\2\2\2\22\36&\63QSp\u0089"+
		"\u008b\u0099\u00a4\u00a6\u00ae\u00b7\u00bf\u00cb\u00ea";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}