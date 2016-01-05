// Generated from C:\Users\Cristian\Dropbox\work\workspace\STL\src\hyness\stl\STLflat.g4 by ANTLR 4.5.1

/**
 * Copyright (C) 2015  Cristian Ioan Vasile <cvasile@bu.edu>
 * Hybrid and Networked Systems (HyNeSs) Group, BU Robotics Lab, Boston University
 * See license.txt file for license information.
 */
package hyness.stl;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link STLflatParser}.
 */
public interface STLflatListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link STLflatParser#specification}.
	 * @param ctx the parse tree
	 */
	void enterSpecification(STLflatParser.SpecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link STLflatParser#specification}.
	 * @param ctx the parse tree
	 */
	void exitSpecification(STLflatParser.SpecificationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code moduleLeaf}
	 * labeled alternative in {@link STLflatParser#module}.
	 * @param ctx the parse tree
	 */
	void enterModuleLeaf(STLflatParser.ModuleLeafContext ctx);
	/**
	 * Exit a parse tree produced by the {@code moduleLeaf}
	 * labeled alternative in {@link STLflatParser#module}.
	 * @param ctx the parse tree
	 */
	void exitModuleLeaf(STLflatParser.ModuleLeafContext ctx);
	/**
	 * Enter a parse tree produced by the {@code moduleOp}
	 * labeled alternative in {@link STLflatParser#module}.
	 * @param ctx the parse tree
	 */
	void enterModuleOp(STLflatParser.ModuleOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code moduleOp}
	 * labeled alternative in {@link STLflatParser#module}.
	 * @param ctx the parse tree
	 */
	void exitModuleOp(STLflatParser.ModuleOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link STLflatParser#moduleDescription}.
	 * @param ctx the parse tree
	 */
	void enterModuleDescription(STLflatParser.ModuleDescriptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link STLflatParser#moduleDescription}.
	 * @param ctx the parse tree
	 */
	void exitModuleDescription(STLflatParser.ModuleDescriptionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code booleanPred}
	 * labeled alternative in {@link STLflatParser#property}.
	 * @param ctx the parse tree
	 */
	void enterBooleanPred(STLflatParser.BooleanPredContext ctx);
	/**
	 * Exit a parse tree produced by the {@code booleanPred}
	 * labeled alternative in {@link STLflatParser#property}.
	 * @param ctx the parse tree
	 */
	void exitBooleanPred(STLflatParser.BooleanPredContext ctx);
	/**
	 * Enter a parse tree produced by the {@code formula}
	 * labeled alternative in {@link STLflatParser#property}.
	 * @param ctx the parse tree
	 */
	void enterFormula(STLflatParser.FormulaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code formula}
	 * labeled alternative in {@link STLflatParser#property}.
	 * @param ctx the parse tree
	 */
	void exitFormula(STLflatParser.FormulaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parprop}
	 * labeled alternative in {@link STLflatParser#property}.
	 * @param ctx the parse tree
	 */
	void enterParprop(STLflatParser.ParpropContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parprop}
	 * labeled alternative in {@link STLflatParser#property}.
	 * @param ctx the parse tree
	 */
	void exitParprop(STLflatParser.ParpropContext ctx);
	/**
	 * Enter a parse tree produced by {@link STLflatParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(STLflatParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link STLflatParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(STLflatParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link STLflatParser#booleanExpr}.
	 * @param ctx the parse tree
	 */
	void enterBooleanExpr(STLflatParser.BooleanExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link STLflatParser#booleanExpr}.
	 * @param ctx the parse tree
	 */
	void exitBooleanExpr(STLflatParser.BooleanExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link STLflatParser#translationMap}.
	 * @param ctx the parse tree
	 */
	void enterTranslationMap(STLflatParser.TranslationMapContext ctx);
	/**
	 * Exit a parse tree produced by {@link STLflatParser#translationMap}.
	 * @param ctx the parse tree
	 */
	void exitTranslationMap(STLflatParser.TranslationMapContext ctx);
	/**
	 * Enter a parse tree produced by {@link STLflatParser#translationPair}.
	 * @param ctx the parse tree
	 */
	void enterTranslationPair(STLflatParser.TranslationPairContext ctx);
	/**
	 * Exit a parse tree produced by {@link STLflatParser#translationPair}.
	 * @param ctx the parse tree
	 */
	void exitTranslationPair(STLflatParser.TranslationPairContext ctx);
}