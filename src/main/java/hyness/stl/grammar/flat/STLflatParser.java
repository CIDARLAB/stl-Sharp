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
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, BOOLEAN=37, VARIABLE=38, 
		RATIONAL=39, WS=40, NEWLINE=41;
	public static final int
		RULE_specification = 0, RULE_module = 1, RULE_moduleDescription = 2, RULE_property = 3, 
		RULE_expr = 4, RULE_booleanExpr = 5, RULE_translationMap = 6, RULE_translationPair = 7;
	public static final String[] ruleNames = {
		"specification", "module", "moduleDescription", "property", "expr", "booleanExpr", 
		"translationMap", "translationPair"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'=>'", "'_'", "'&&'", "'||'", "'>>'", "'#'", "'('", "','", "')'", 
		"'='", "'!'", "'F'", "'['", "']'", "'G'", "'U'", "'-('", "'^'", "'sqrt('", 
		"'log('", "'ln('", "'abs('", "'der('", "'int('", "'*'", "'/'", "'+'", 
		"'-'", "'<'", "'<='", "'>='", "'>'", "'{'", "'}'", "'@'", "':'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, "BOOLEAN", "VARIABLE", "RATIONAL", "WS", "NEWLINE"
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
			enterOuterAlt(_localctx, 1);
			{
			setState(16);
			((SpecificationContext)_localctx).spec = module(0);
			setState(17);
			match(NEWLINE);
			setState(18);
			match(NEWLINE);
			setState(22); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(19);
				moduleDescription();
				setState(20);
				match(NEWLINE);
				}
				}
				setState(24); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==VARIABLE );
			setState(26);
			match(NEWLINE);
			setState(30); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(27);
				translationMap();
				setState(28);
				match(NEWLINE);
				}
				}
				setState(32); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==VARIABLE );
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

			setState(35);
			((ModuleLeafContext)_localctx).moduleName = match(VARIABLE);
			setState(36);
			match(T__6);
			setState(37);
			match(VARIABLE);
			setState(42);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__7) {
				{
				{
				setState(38);
				match(T__7);
				setState(39);
				match(VARIABLE);
				}
				}
				setState(44);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(45);
			match(T__8);
			}
			_ctx.stop = _input.LT(-1);
			setState(74);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(72);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
					case 1:
						{
						_localctx = new ModuleOpContext(new ModuleContext(_parentctx, _parentState));
						((ModuleOpContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_module);
						setState(47);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(48);
						((ModuleOpContext)_localctx).op = match(T__0);
						setState(49);
						match(T__1);
						setState(50);
						((ModuleOpContext)_localctx).tmap = match(VARIABLE);
						setState(51);
						((ModuleOpContext)_localctx).right = module(7);
						}
						break;
					case 2:
						{
						_localctx = new ModuleOpContext(new ModuleContext(_parentctx, _parentState));
						((ModuleOpContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_module);
						setState(52);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(53);
						((ModuleOpContext)_localctx).op = match(T__2);
						setState(54);
						match(T__1);
						setState(55);
						((ModuleOpContext)_localctx).tmap = match(VARIABLE);
						setState(56);
						((ModuleOpContext)_localctx).right = module(6);
						}
						break;
					case 3:
						{
						_localctx = new ModuleOpContext(new ModuleContext(_parentctx, _parentState));
						((ModuleOpContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_module);
						setState(57);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(58);
						((ModuleOpContext)_localctx).op = match(T__3);
						setState(59);
						match(T__1);
						setState(60);
						((ModuleOpContext)_localctx).tmap = match(VARIABLE);
						setState(61);
						((ModuleOpContext)_localctx).right = module(5);
						}
						break;
					case 4:
						{
						_localctx = new ModuleOpContext(new ModuleContext(_parentctx, _parentState));
						((ModuleOpContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_module);
						setState(62);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(63);
						((ModuleOpContext)_localctx).op = match(T__4);
						setState(64);
						match(T__1);
						setState(65);
						((ModuleOpContext)_localctx).tmap = match(VARIABLE);
						setState(66);
						((ModuleOpContext)_localctx).right = module(4);
						}
						break;
					case 5:
						{
						_localctx = new ModuleOpContext(new ModuleContext(_parentctx, _parentState));
						((ModuleOpContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_module);
						setState(67);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(68);
						((ModuleOpContext)_localctx).op = match(T__5);
						setState(69);
						match(T__1);
						setState(70);
						((ModuleOpContext)_localctx).tmap = match(VARIABLE);
						setState(71);
						((ModuleOpContext)_localctx).right = module(3);
						}
						break;
					}
					} 
				}
				setState(76);
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
			setState(77);
			((ModuleDescriptionContext)_localctx).moduleName = match(VARIABLE);
			setState(78);
			match(T__9);
			setState(79);
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
			setState(103);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				_localctx = new ParpropContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(82);
				match(T__6);
				setState(83);
				((ParpropContext)_localctx).child = property(0);
				setState(84);
				match(T__8);
				}
				break;
			case 2:
				{
				_localctx = new BooleanPredContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(86);
				booleanExpr();
				}
				break;
			case 3:
				{
				_localctx = new FormulaContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(87);
				((FormulaContext)_localctx).op = match(T__10);
				setState(88);
				((FormulaContext)_localctx).child = property(9);
				}
				break;
			case 4:
				{
				_localctx = new FormulaContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(89);
				((FormulaContext)_localctx).op = match(T__11);
				setState(90);
				match(T__12);
				setState(91);
				((FormulaContext)_localctx).low = match(RATIONAL);
				setState(92);
				match(T__7);
				setState(93);
				((FormulaContext)_localctx).high = match(RATIONAL);
				setState(94);
				match(T__13);
				setState(95);
				((FormulaContext)_localctx).child = property(8);
				}
				break;
			case 5:
				{
				_localctx = new FormulaContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(96);
				((FormulaContext)_localctx).op = match(T__14);
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
				((FormulaContext)_localctx).child = property(7);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(130);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(128);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
					case 1:
						{
						_localctx = new FormulaContext(new PropertyContext(_parentctx, _parentState));
						((FormulaContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_property);
						setState(105);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(106);
						((FormulaContext)_localctx).op = match(T__0);
						setState(107);
						((FormulaContext)_localctx).right = property(7);
						}
						break;
					case 2:
						{
						_localctx = new FormulaContext(new PropertyContext(_parentctx, _parentState));
						((FormulaContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_property);
						setState(108);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(109);
						((FormulaContext)_localctx).op = match(T__2);
						setState(110);
						((FormulaContext)_localctx).right = property(6);
						}
						break;
					case 3:
						{
						_localctx = new FormulaContext(new PropertyContext(_parentctx, _parentState));
						((FormulaContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_property);
						setState(111);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(112);
						((FormulaContext)_localctx).op = match(T__3);
						setState(113);
						((FormulaContext)_localctx).right = property(5);
						}
						break;
					case 4:
						{
						_localctx = new FormulaContext(new PropertyContext(_parentctx, _parentState));
						((FormulaContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_property);
						setState(114);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(115);
						((FormulaContext)_localctx).op = match(T__4);
						setState(116);
						((FormulaContext)_localctx).right = property(4);
						}
						break;
					case 5:
						{
						_localctx = new FormulaContext(new PropertyContext(_parentctx, _parentState));
						((FormulaContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_property);
						setState(117);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(118);
						((FormulaContext)_localctx).op = match(T__5);
						setState(119);
						((FormulaContext)_localctx).right = property(3);
						}
						break;
					case 6:
						{
						_localctx = new FormulaContext(new PropertyContext(_parentctx, _parentState));
						((FormulaContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_property);
						setState(120);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(121);
						((FormulaContext)_localctx).op = match(T__15);
						setState(122);
						match(T__12);
						setState(123);
						((FormulaContext)_localctx).low = match(RATIONAL);
						setState(124);
						match(T__7);
						setState(125);
						((FormulaContext)_localctx).high = match(RATIONAL);
						setState(126);
						match(T__13);
						setState(127);
						((FormulaContext)_localctx).right = property(2);
						}
						break;
					}
					} 
				}
				setState(132);
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
			setState(144);
			switch (_input.LA(1)) {
			case T__6:
			case T__16:
				{
				setState(134);
				_la = _input.LA(1);
				if ( !(_la==T__6 || _la==T__16) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(135);
				expr(0);
				setState(136);
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
				setState(138);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23))) != 0)) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(139);
				expr(0);
				setState(140);
				match(T__8);
				}
				break;
			case RATIONAL:
				{
				setState(142);
				match(RATIONAL);
				}
				break;
			case VARIABLE:
				{
				setState(143);
				match(VARIABLE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(157);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(155);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(146);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(147);
						match(T__17);
						setState(148);
						expr(7);
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(149);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(150);
						_la = _input.LA(1);
						if ( !(_la==T__24 || _la==T__25) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(151);
						expr(5);
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(152);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(153);
						_la = _input.LA(1);
						if ( !(_la==T__26 || _la==T__27) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(154);
						expr(4);
						}
						break;
					}
					} 
				}
				setState(159);
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
			setState(165);
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
				setState(160);
				((BooleanExprContext)_localctx).left = expr(0);
				setState(161);
				((BooleanExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__9) | (1L << T__28) | (1L << T__29) | (1L << T__30) | (1L << T__31))) != 0)) ) {
					((BooleanExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(162);
				((BooleanExprContext)_localctx).right = expr(0);
				}
				break;
			case BOOLEAN:
				enterOuterAlt(_localctx, 2);
				{
				setState(164);
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
			setState(167);
			((TranslationMapContext)_localctx).tmapName = match(VARIABLE);
			setState(168);
			match(T__32);
			setState(169);
			translationPair();
			setState(174);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__7) {
				{
				{
				setState(170);
				match(T__7);
				setState(171);
				translationPair();
				}
				}
				setState(176);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(177);
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
			setState(179);
			((TranslationPairContext)_localctx).key = match(VARIABLE);
			setState(182);
			_la = _input.LA(1);
			if (_la==T__34) {
				{
				setState(180);
				match(T__34);
				setState(181);
				((TranslationPairContext)_localctx).moduleName = match(VARIABLE);
				}
			}

			setState(184);
			match(T__35);
			setState(185);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3+\u00be\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\6\2\31\n\2\r\2\16\2\32\3\2\3\2\3\2\3\2\6\2!\n\2\r\2\16\2\"\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\7\3+\n\3\f\3\16\3.\13\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\7\3K\n\3\f\3\16\3N\13\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\5\5j\n\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5\u0083\n\5\f\5\16\5\u0086\13\5\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u0093\n\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\7\6\u009e\n\6\f\6\16\6\u00a1\13\6\3\7\3\7\3\7\3"+
		"\7\3\7\5\7\u00a8\n\7\3\b\3\b\3\b\3\b\3\b\7\b\u00af\n\b\f\b\16\b\u00b2"+
		"\13\b\3\b\3\b\3\t\3\t\3\t\5\t\u00b9\n\t\3\t\3\t\3\t\3\t\2\5\4\b\n\n\2"+
		"\4\6\b\n\f\16\20\2\7\4\2\t\t\23\23\3\2\25\32\3\2\33\34\3\2\35\36\4\2\f"+
		"\f\37\"\u00d0\2\22\3\2\2\2\4$\3\2\2\2\6O\3\2\2\2\bi\3\2\2\2\n\u0092\3"+
		"\2\2\2\f\u00a7\3\2\2\2\16\u00a9\3\2\2\2\20\u00b5\3\2\2\2\22\23\5\4\3\2"+
		"\23\24\7+\2\2\24\30\7+\2\2\25\26\5\6\4\2\26\27\7+\2\2\27\31\3\2\2\2\30"+
		"\25\3\2\2\2\31\32\3\2\2\2\32\30\3\2\2\2\32\33\3\2\2\2\33\34\3\2\2\2\34"+
		" \7+\2\2\35\36\5\16\b\2\36\37\7+\2\2\37!\3\2\2\2 \35\3\2\2\2!\"\3\2\2"+
		"\2\" \3\2\2\2\"#\3\2\2\2#\3\3\2\2\2$%\b\3\1\2%&\7(\2\2&\'\7\t\2\2\',\7"+
		"(\2\2()\7\n\2\2)+\7(\2\2*(\3\2\2\2+.\3\2\2\2,*\3\2\2\2,-\3\2\2\2-/\3\2"+
		"\2\2.,\3\2\2\2/\60\7\13\2\2\60L\3\2\2\2\61\62\f\b\2\2\62\63\7\3\2\2\63"+
		"\64\7\4\2\2\64\65\7(\2\2\65K\5\4\3\t\66\67\f\7\2\2\678\7\5\2\289\7\4\2"+
		"\29:\7(\2\2:K\5\4\3\b;<\f\6\2\2<=\7\6\2\2=>\7\4\2\2>?\7(\2\2?K\5\4\3\7"+
		"@A\f\5\2\2AB\7\7\2\2BC\7\4\2\2CD\7(\2\2DK\5\4\3\6EF\f\4\2\2FG\7\b\2\2"+
		"GH\7\4\2\2HI\7(\2\2IK\5\4\3\5J\61\3\2\2\2J\66\3\2\2\2J;\3\2\2\2J@\3\2"+
		"\2\2JE\3\2\2\2KN\3\2\2\2LJ\3\2\2\2LM\3\2\2\2M\5\3\2\2\2NL\3\2\2\2OP\7"+
		"(\2\2PQ\7\f\2\2QR\5\b\5\2R\7\3\2\2\2ST\b\5\1\2TU\7\t\2\2UV\5\b\5\2VW\7"+
		"\13\2\2Wj\3\2\2\2Xj\5\f\7\2YZ\7\r\2\2Zj\5\b\5\13[\\\7\16\2\2\\]\7\17\2"+
		"\2]^\7)\2\2^_\7\n\2\2_`\7)\2\2`a\7\20\2\2aj\5\b\5\nbc\7\21\2\2cd\7\17"+
		"\2\2de\7)\2\2ef\7\n\2\2fg\7)\2\2gh\7\20\2\2hj\5\b\5\tiS\3\2\2\2iX\3\2"+
		"\2\2iY\3\2\2\2i[\3\2\2\2ib\3\2\2\2j\u0084\3\2\2\2kl\f\b\2\2lm\7\3\2\2"+
		"m\u0083\5\b\5\tno\f\7\2\2op\7\5\2\2p\u0083\5\b\5\bqr\f\6\2\2rs\7\6\2\2"+
		"s\u0083\5\b\5\7tu\f\5\2\2uv\7\7\2\2v\u0083\5\b\5\6wx\f\4\2\2xy\7\b\2\2"+
		"y\u0083\5\b\5\5z{\f\3\2\2{|\7\22\2\2|}\7\17\2\2}~\7)\2\2~\177\7\n\2\2"+
		"\177\u0080\7)\2\2\u0080\u0081\7\20\2\2\u0081\u0083\5\b\5\4\u0082k\3\2"+
		"\2\2\u0082n\3\2\2\2\u0082q\3\2\2\2\u0082t\3\2\2\2\u0082w\3\2\2\2\u0082"+
		"z\3\2\2\2\u0083\u0086\3\2\2\2\u0084\u0082\3\2\2\2\u0084\u0085\3\2\2\2"+
		"\u0085\t\3\2\2\2\u0086\u0084\3\2\2\2\u0087\u0088\b\6\1\2\u0088\u0089\t"+
		"\2\2\2\u0089\u008a\5\n\6\2\u008a\u008b\7\13\2\2\u008b\u0093\3\2\2\2\u008c"+
		"\u008d\t\3\2\2\u008d\u008e\5\n\6\2\u008e\u008f\7\13\2\2\u008f\u0093\3"+
		"\2\2\2\u0090\u0093\7)\2\2\u0091\u0093\7(\2\2\u0092\u0087\3\2\2\2\u0092"+
		"\u008c\3\2\2\2\u0092\u0090\3\2\2\2\u0092\u0091\3\2\2\2\u0093\u009f\3\2"+
		"\2\2\u0094\u0095\f\b\2\2\u0095\u0096\7\24\2\2\u0096\u009e\5\n\6\t\u0097"+
		"\u0098\f\6\2\2\u0098\u0099\t\4\2\2\u0099\u009e\5\n\6\7\u009a\u009b\f\5"+
		"\2\2\u009b\u009c\t\5\2\2\u009c\u009e\5\n\6\6\u009d\u0094\3\2\2\2\u009d"+
		"\u0097\3\2\2\2\u009d\u009a\3\2\2\2\u009e\u00a1\3\2\2\2\u009f\u009d\3\2"+
		"\2\2\u009f\u00a0\3\2\2\2\u00a0\13\3\2\2\2\u00a1\u009f\3\2\2\2\u00a2\u00a3"+
		"\5\n\6\2\u00a3\u00a4\t\6\2\2\u00a4\u00a5\5\n\6\2\u00a5\u00a8\3\2\2\2\u00a6"+
		"\u00a8\7\'\2\2\u00a7\u00a2\3\2\2\2\u00a7\u00a6\3\2\2\2\u00a8\r\3\2\2\2"+
		"\u00a9\u00aa\7(\2\2\u00aa\u00ab\7#\2\2\u00ab\u00b0\5\20\t\2\u00ac\u00ad"+
		"\7\n\2\2\u00ad\u00af\5\20\t\2\u00ae\u00ac\3\2\2\2\u00af\u00b2\3\2\2\2"+
		"\u00b0\u00ae\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00b3\3\2\2\2\u00b2\u00b0"+
		"\3\2\2\2\u00b3\u00b4\7$\2\2\u00b4\17\3\2\2\2\u00b5\u00b8\7(\2\2\u00b6"+
		"\u00b7\7%\2\2\u00b7\u00b9\7(\2\2\u00b8\u00b6\3\2\2\2\u00b8\u00b9\3\2\2"+
		"\2\u00b9\u00ba\3\2\2\2\u00ba\u00bb\7&\2\2\u00bb\u00bc\7(\2\2\u00bc\21"+
		"\3\2\2\2\20\32\",JLi\u0082\u0084\u0092\u009d\u009f\u00a7\u00b0\u00b8";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}