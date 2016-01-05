// Generated from C:\Users\Cristian\Dropbox\work\workspace\STL\src\hyness\stl\STLflat.g4 by ANTLR 4.5.1

/**
 * Copyright (C) 2015  Cristian Ioan Vasile <cvasile@bu.edu>
 * Hybrid and Networked Systems (HyNeSs) Group, BU Robotics Lab, Boston University
 * See license.txt file for license information.
 */
package hyness.stl;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link STLflatParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface STLflatVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link STLflatParser#specification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpecification(STLflatParser.SpecificationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code moduleLeaf}
	 * labeled alternative in {@link STLflatParser#module}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModuleLeaf(STLflatParser.ModuleLeafContext ctx);
	/**
	 * Visit a parse tree produced by the {@code moduleOp}
	 * labeled alternative in {@link STLflatParser#module}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModuleOp(STLflatParser.ModuleOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link STLflatParser#moduleDescription}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModuleDescription(STLflatParser.ModuleDescriptionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code booleanPred}
	 * labeled alternative in {@link STLflatParser#property}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanPred(STLflatParser.BooleanPredContext ctx);
	/**
	 * Visit a parse tree produced by the {@code formula}
	 * labeled alternative in {@link STLflatParser#property}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormula(STLflatParser.FormulaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parprop}
	 * labeled alternative in {@link STLflatParser#property}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParprop(STLflatParser.ParpropContext ctx);
	/**
	 * Visit a parse tree produced by {@link STLflatParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(STLflatParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link STLflatParser#booleanExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanExpr(STLflatParser.BooleanExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link STLflatParser#translationMap}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTranslationMap(STLflatParser.TranslationMapContext ctx);
	/**
	 * Visit a parse tree produced by {@link STLflatParser#translationPair}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTranslationPair(STLflatParser.TranslationPairContext ctx);
}