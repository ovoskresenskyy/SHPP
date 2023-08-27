package com.shpp.p2p.cs.ovoskresenskyy.assignment11;

import com.shpp.p2p.cs.ovoskresenskyy.assignment11.model.Expression;
import com.shpp.p2p.cs.ovoskresenskyy.assignment11.model.OperandPair;
import com.shpp.p2p.cs.ovoskresenskyy.assignment11.model.Token;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

import static com.shpp.p2p.cs.ovoskresenskyy.assignment11.enums.Operator.getOperatorsAsDelimiter;
import static com.shpp.p2p.cs.ovoskresenskyy.assignment11.service.Calculator.calculate;
import static com.shpp.p2p.cs.ovoskresenskyy.assignment11.service.ExpressionParser.getParsedExpression;
import static com.shpp.p2p.cs.ovoskresenskyy.assignment11.service.ExpressionValidator.isExpressionValid;

/**
 * This file is an entry point of the implementation of simple calculator
 * <p>
 * It's receive the math expression,
 * placed into the first element of the received args[], and show the result of the solving.
 * <p>
 * The expression can contain variables.
 * Variables are present as next elements of the received args[].
 */
public class Assignment11Part1 {

    private static boolean parsed = false;

    public static void main(String[] args) {
        extractTokens("2+(2+3*(2+2)+(2-2)+((2+2/2+2)+2))+(2+2)+2");

//        String[] argsT = new String[]{"0 + (2 - 2) * (2 + 2)", "", ""};
//
//        Expression expression = getParsedExpression(argsT);
//
//        if (isExpressionValid(expression)) {
//            System.out.println(calculate(expression));
//        } else {
//            System.out.println("Expression isn't valid");
//        }
    }

    private static void extractTokens(String expression) {
        Stack<Integer> tokenStartIndexes = new Stack<>();
//        Stack<String> braces = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            if (parsed) {
                break;
            }

            if (expression.charAt(i) == '(') {
                tokenStartIndexes.push(i + 1);
            }

            if (expression.charAt(i) == ')') {
                Integer index = tokenStartIndexes.pop();

                String substring = expression.substring(index, i);
                String[] split = substring.split(getOperatorsAsDelimiter());
                Token token = new Token();
                token.values.addAll(Arrays.asList(split));
                System.out.println("token = " + token);

                String left = expression.substring(0, index - 1);
                String right = expression.substring(i + 1);

                expression = left + "#" + token + right;
                System.out.println(expression);

                if (expression.contains("(")) {
                    extractTokens(expression);
                } else {
                    parsed = true;
                }
            }
        }
    }

}
