package com.shpp.p2p.cs.ovoskresenskyy.assignment11.service;

import static com.shpp.p2p.cs.ovoskresenskyy.assignment11.enums.Operator.*;

/**
 * This class is responsible for the cleaning and fixing the text value
 * of the received expression without changing the meaning.
 */
public class ExpressionCleaner {

    /**
     * The delimiter between received name of the variable, and it's value
     */
    private final static String VARIABLE_DELIMITER = "=";

    /**
     * This method is responsible for fix the received expression,
     * so it can be read by the parser.
     *
     * @param args - Received parameters from the user,
     *             where expression is always placed as the first element.
     * @return Clean expression to be parsed.
     */
    public static String prepareExpression(String[] args) {
        String expression = args[0];

        expression = cleanExpression(expression);
        expression = replaceVariables(expression, args);

        if (needToAddLeadingZero(expression)) {
            expression = "0" + expression;
        }

        return expression;
    }

    /**
     * This method is responsible for the cleaning redundant spaces
     * and replace comas with dot into the numbers.
     *
     * @param expression - Text of the expression to be cleaned
     * @return Clean expression without redundant symbols.
     */
    private static String cleanExpression(String expression) {
        return expression
                .toLowerCase()
                .replaceAll(" ", "")
                .replaceAll(",", ".");
    }

    /**
     * This method is responsible for replacing variables with those received values
     *
     * @param expression - Expression with the variables
     * @param args       - Names of variables and those values.
     *                   Variables are coming as "name = value".
     *                   Value can be negative.
     * @return Expression without variables
     */
    private static String replaceVariables(String expression, String[] args) {
        if (args.length == 1) {
            return expression;
        }

        for (int i = 1; i < args.length; i++) {
            String[] valueVariable = cleanExpression(args[i]).split(VARIABLE_DELIMITER);

            if (valueVariable.length < 2) {
                continue;
            }

            String variable = valueVariable[0].toLowerCase();
            String value = valueVariable[1];

            expression = expression.replaceAll(variable, value);
        }

        return expression;
    }

    /**
     * This method is responsible for checking is the given expression starts with the
     * leading operator '-' or '+'.
     * <p>
     * In those cases will add leading '0'.
     *
     * @param expression - Received expression to check
     * @return Expression with the leading '0' if it's necessary
     */
    private static boolean needToAddLeadingZero(String expression) {
        if (expression.length() == 0) {
            return false;
        }

        char firstChar = expression.charAt(0);
        return getOperatorBySymbol(firstChar) == ADDITION || getOperatorBySymbol(firstChar) == SUBTRACTION;
    }
}
