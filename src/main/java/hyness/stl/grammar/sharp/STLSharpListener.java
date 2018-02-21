// Generated from STLSharp.g4 by ANTLR 4.7

/**
 * Copyright (C) 2015  Cristian Ioan Vasile <cvasile@bu.edu>, Prashant Vaidyanathan <prash@bu.edu>, Curtis Madsen <ckmadsen@bu.edu>
 * Hybrid and Networked Systems (HyNeSs) Group, BU Robotics Lab, Boston University
 * Cross Disciplinary Integration for Design Automation Research (CIDAR Lab), Boston University
 * See license.txt file for license information.
 */
package hyness.stl.grammar.sharp;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link STLSharpParser}.
 */
public interface STLSharpListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link STLSharpParser#specification}.
	 * @param ctx the parse tree
	 */
	void enterSpecification(STLSharpParser.SpecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link STLSharpParser#specification}.
	 * @param ctx the parse tree
	 */
	void exitSpecification(STLSharpParser.SpecificationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code moduleLeaf}
	 * labeled alternative in {@link STLSharpParser#module}.
	 * @param ctx the parse tree
	 */
	void enterModuleLeaf(STLSharpParser.ModuleLeafContext ctx);
	/**
	 * Exit a parse tree produced by the {@code moduleLeaf}
	 * labeled alternative in {@link STLSharpParser#module}.
	 * @param ctx the parse tree
	 */
	void exitModuleLeaf(STLSharpParser.ModuleLeafContext ctx);
	/**
	 * Enter a parse tree produced by the {@code moduleOp}
	 * labeled alternative in {@link STLSharpParser#module}.
	 * @param ctx the parse tree
	 */
	void enterModuleOp(STLSharpParser.ModuleOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code moduleOp}
	 * labeled alternative in {@link STLSharpParser#module}.
	 * @param ctx the parse tree
	 */
	void exitModuleOp(STLSharpParser.ModuleOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link STLSharpParser#moduleDescription}.
	 * @param ctx the parse tree
	 */
	void enterModuleDescription(STLSharpParser.ModuleDescriptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link STLSharpParser#moduleDescription}.
	 * @param ctx the parse tree
	 */
	void exitModuleDescription(STLSharpParser.ModuleDescriptionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code booleanPred}
	 * labeled alternative in {@link STLSharpParser#property}.
	 * @param ctx the parse tree
	 */
	void enterBooleanPred(STLSharpParser.BooleanPredContext ctx);
	/**
	 * Exit a parse tree produced by the {@code booleanPred}
	 * labeled alternative in {@link STLSharpParser#property}.
	 * @param ctx the parse tree
	 */
	void exitBooleanPred(STLSharpParser.BooleanPredContext ctx);
	/**
	 * Enter a parse tree produced by the {@code formula}
	 * labeled alternative in {@link STLSharpParser#property}.
	 * @param ctx the parse tree
	 */
	void enterFormula(STLSharpParser.FormulaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code formula}
	 * labeled alternative in {@link STLSharpParser#property}.
	 * @param ctx the parse tree
	 */
	void exitFormula(STLSharpParser.FormulaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parprop}
	 * labeled alternative in {@link STLSharpParser#property}.
	 * @param ctx the parse tree
	 */
	void enterParprop(STLSharpParser.ParpropContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parprop}
	 * labeled alternative in {@link STLSharpParser#property}.
	 * @param ctx the parse tree
	 */
	void exitParprop(STLSharpParser.ParpropContext ctx);
	/**
	 * Enter a parse tree produced by {@link STLSharpParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(STLSharpParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link STLSharpParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(STLSharpParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link STLSharpParser#booleanExpr}.
	 * @param ctx the parse tree
	 */
	void enterBooleanExpr(STLSharpParser.BooleanExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link STLSharpParser#booleanExpr}.
	 * @param ctx the parse tree
	 */
	void exitBooleanExpr(STLSharpParser.BooleanExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link STLSharpParser#translationMap}.
	 * @param ctx the parse tree
	 */
	void enterTranslationMap(STLSharpParser.TranslationMapContext ctx);
	/**
	 * Exit a parse tree produced by {@link STLSharpParser#translationMap}.
	 * @param ctx the parse tree
	 */
	void exitTranslationMap(STLSharpParser.TranslationMapContext ctx);
	/**
	 * Enter a parse tree produced by {@link STLSharpParser#translationPair}.
	 * @param ctx the parse tree
	 */
	void enterTranslationPair(STLSharpParser.TranslationPairContext ctx);
	/**
	 * Exit a parse tree produced by {@link STLSharpParser#translationPair}.
	 * @param ctx the parse tree
	 */
	void exitTranslationPair(STLSharpParser.TranslationPairContext ctx);
	/**
	 * Enter a parse tree produced by {@link STLSharpParser#limitMap}.
	 * @param ctx the parse tree
	 */
	void enterLimitMap(STLSharpParser.LimitMapContext ctx);
	/**
	 * Exit a parse tree produced by {@link STLSharpParser#limitMap}.
	 * @param ctx the parse tree
	 */
	void exitLimitMap(STLSharpParser.LimitMapContext ctx);
	/**
	 * Enter a parse tree produced by {@link STLSharpParser#limitPair}.
	 * @param ctx the parse tree
	 */
	void enterLimitPair(STLSharpParser.LimitPairContext ctx);
	/**
	 * Exit a parse tree produced by {@link STLSharpParser#limitPair}.
	 * @param ctx the parse tree
	 */
	void exitLimitPair(STLSharpParser.LimitPairContext ctx);
}