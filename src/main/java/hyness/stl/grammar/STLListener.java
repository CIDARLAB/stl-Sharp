// Generated from C:\Users\Cristian\Dropbox\work\workspace\STL\src\hyness\stl\STL.g4 by ANTLR 4.5.1

/**
 * Copyright (C) 2015  Cristian Ioan Vasile <cvasile@bu.edu>
 * Hybrid and Networked Systems (HyNeSs) Group, BU Robotics Lab, Boston University
 * See license.txt file for license information.
 */
package hyness.stl.grammar;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link STLParser}.
 */
public interface STLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code booleanPred}
	 * labeled alternative in {@link STLParser#property}.
	 * @param ctx the parse tree
	 */
	void enterBooleanPred(STLParser.BooleanPredContext ctx);
	/**
	 * Exit a parse tree produced by the {@code booleanPred}
	 * labeled alternative in {@link STLParser#property}.
	 * @param ctx the parse tree
	 */
	void exitBooleanPred(STLParser.BooleanPredContext ctx);
	/**
	 * Enter a parse tree produced by the {@code formula}
	 * labeled alternative in {@link STLParser#property}.
	 * @param ctx the parse tree
	 */
	void enterFormula(STLParser.FormulaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code formula}
	 * labeled alternative in {@link STLParser#property}.
	 * @param ctx the parse tree
	 */
	void exitFormula(STLParser.FormulaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parprop}
	 * labeled alternative in {@link STLParser#property}.
	 * @param ctx the parse tree
	 */
	void enterParprop(STLParser.ParpropContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parprop}
	 * labeled alternative in {@link STLParser#property}.
	 * @param ctx the parse tree
	 */
	void exitParprop(STLParser.ParpropContext ctx);
	/**
	 * Enter a parse tree produced by {@link STLParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(STLParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link STLParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(STLParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link STLParser#booleanExpr}.
	 * @param ctx the parse tree
	 */
	void enterBooleanExpr(STLParser.BooleanExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link STLParser#booleanExpr}.
	 * @param ctx the parse tree
	 */
	void exitBooleanExpr(STLParser.BooleanExprContext ctx);
}