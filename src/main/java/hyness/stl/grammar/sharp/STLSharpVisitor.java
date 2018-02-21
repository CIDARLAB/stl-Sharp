// Generated from STLSharp.g4 by ANTLR 4.7

/**
 * Copyright (C) 2015  Cristian Ioan Vasile <cvasile@bu.edu>, Prashant Vaidyanathan <prash@bu.edu>, Curtis Madsen <ckmadsen@bu.edu>
 * Hybrid and Networked Systems (HyNeSs) Group, BU Robotics Lab, Boston University
 * Cross Disciplinary Integration for Design Automation Research (CIDAR Lab), Boston University
 * See license.txt file for license information.
 */
package hyness.stl.grammar.sharp;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link STLSharpParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface STLSharpVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link STLSharpParser#specification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpecification(STLSharpParser.SpecificationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code moduleLeaf}
	 * labeled alternative in {@link STLSharpParser#module}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModuleLeaf(STLSharpParser.ModuleLeafContext ctx);
	/**
	 * Visit a parse tree produced by the {@code moduleOp}
	 * labeled alternative in {@link STLSharpParser#module}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModuleOp(STLSharpParser.ModuleOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link STLSharpParser#moduleDescription}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModuleDescription(STLSharpParser.ModuleDescriptionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code booleanPred}
	 * labeled alternative in {@link STLSharpParser#property}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanPred(STLSharpParser.BooleanPredContext ctx);
	/**
	 * Visit a parse tree produced by the {@code formula}
	 * labeled alternative in {@link STLSharpParser#property}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormula(STLSharpParser.FormulaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parprop}
	 * labeled alternative in {@link STLSharpParser#property}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParprop(STLSharpParser.ParpropContext ctx);
	/**
	 * Visit a parse tree produced by {@link STLSharpParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(STLSharpParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link STLSharpParser#booleanExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanExpr(STLSharpParser.BooleanExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link STLSharpParser#translationMap}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTranslationMap(STLSharpParser.TranslationMapContext ctx);
	/**
	 * Visit a parse tree produced by {@link STLSharpParser#translationPair}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTranslationPair(STLSharpParser.TranslationPairContext ctx);
	/**
	 * Visit a parse tree produced by {@link STLSharpParser#limitMap}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLimitMap(STLSharpParser.LimitMapContext ctx);
	/**
	 * Visit a parse tree produced by {@link STLSharpParser#limitPair}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLimitPair(STLSharpParser.LimitPairContext ctx);
}