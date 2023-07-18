package com.shpp.p2p.cs.ovoskresenskyy.assignment10;

import com.shpp.p2p.cs.ovoskresenskyy.assignment10.model.Expression;

import static com.shpp.p2p.cs.ovoskresenskyy.assignment10.service.Calculator.calculate;
import static com.shpp.p2p.cs.ovoskresenskyy.assignment10.service.ExpressionParser.getParsedExpression;
import static com.shpp.p2p.cs.ovoskresenskyy.assignment10.service.ExpressionValidator.isExpressionValid;

/**
 * This file is an entry point of the implementation of simple calculator
 * <p>
 * It's receive the math expression,
 * placed into the first element of the received args[], and show the result of the solving.
 * <p>
 * The expression can contain variables.
 * Variables are present as next elements of the received args[].
 */
public class Assignment10Part1 {

    public static void main(String[] args) {
        Expression expression = getParsedExpression(args);

        if (isExpressionValid(expression)) {
            System.out.println(calculate(expression));
        } else {
            System.out.println("Expression isn't valid");
        }
    }
}
