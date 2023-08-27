package com.shpp.p2p.cs.ovoskresenskyy.assignment11.service;

import com.shpp.p2p.cs.ovoskresenskyy.assignment11.enums.Operator;
import com.shpp.p2p.cs.ovoskresenskyy.assignment11.enums.ParsingError;
import com.shpp.p2p.cs.ovoskresenskyy.assignment11.model.Expression;
import com.shpp.p2p.cs.ovoskresenskyy.assignment11.model.OperandPair;

import java.util.*;
import java.util.regex.Pattern;

import static com.shpp.p2p.cs.ovoskresenskyy.assignment11.enums.Operator.*;
import static com.shpp.p2p.cs.ovoskresenskyy.assignment11.enums.ParsingError.*;

/**
 * This class is responsible for checking the expression and validate is
 * inputted characters are correct with correct order
 */
public class ExpressionValidator {

    /**
     * The regex of all symbols that can used in the expression
     */
    private static final String VALID_EXPRESSION_SYMBOLS = "[a-zA-Z()\\d.]+";
    /**
     * The regex of symbols that can used as a variables
     */
    private static final String VARIABLE_SYMBOLS = "[a-zA-Z]+";

    /**
     * This method is responsible for checking if there are any args received
     *
     * @param args - Arguments from the user
     * @return Parsing error ARGS_EMPTY if there are no arguments present, NO_ERROR if arguments are present
     */
    public static ParsingError isArgsEmpty(String[] args) {
        return args.length == 0 ? ARGS_EMPTY : NO_ERROR;
    }

    /**
     * This method is responsible for checking if received expression is valid
     * and can be parsed
     *
     * @param expression - Previously prepared and cleaned expression
     * @return True if expression is marked as valid, false if not
     */
    public static boolean isTextExpressionValid(String expression) {
        ParsingError numberOfBracketsCorrect = isNumberOfBracketsCorrect(expression);
        showErrorMessage(numberOfBracketsCorrect);

        String[] operands = expression.split(getOperatorsAsDelimiter());

        ParsingError operandsValid = isOperandsValid(operands);
        showErrorMessage(operandsValid);

        ParsingError variablesPresent = isVariablesPresent(operands);
        showErrorMessage(variablesPresent);

        ParsingError emptyOperandsPresent = isEmptyOperandsPresent(operands);
        showErrorMessage(emptyOperandsPresent);

        return numberOfBracketsCorrect == NO_ERROR
                && operandsValid == NO_ERROR
                && variablesPresent == NO_ERROR
                && emptyOperandsPresent == NO_ERROR;
    }

    /**
     * This method is responsible for checking is there are only
     * valid characters present
     *
     * @param operands - The array of the operands
     * @return NO_ERROR if all operands match with valid symbols, OPERANDS_NOT_VALID if not
     */
    private static ParsingError isOperandsValid(String[] operands) {
        boolean operandsValid = Arrays.stream(operands)
                .filter(operand -> !operand.equals(""))
                .allMatch(operand -> Pattern.matches(VALID_EXPRESSION_SYMBOLS, operand));

        return operandsValid ? NO_ERROR : OPERANDS_NOT_VALID;
    }

    /**
     * This method is responsible for checking if there are still non-matched variables left
     *
     * @param operands - The array of the operands
     * @return VARIABLE_PRESENT if there still non-matched variables present, NO_ERROR if not
     */
    private static ParsingError isVariablesPresent(String[] operands) {
        boolean isVariablesPresent = Arrays.stream(operands)
                .anyMatch(operand -> Pattern.matches(VARIABLE_SYMBOLS, operand));

        return isVariablesPresent ? VARIABLE_PRESENT : NO_ERROR;
    }

    /**
     * This method is responsible for checking if there are any empty operands.
     * It happens when in expression are present two or more operands in a row.
     *
     * @param operands - The array of the operands to be checked
     * @return EMPTY_OPERANDS_PRESENT if there are empty operators present, NO_ERROR if not.
     */
    private static ParsingError isEmptyOperandsPresent(String[] operands) {
        return Arrays.asList(operands).contains("") ? EMPTY_OPERANDS_PRESENT : NO_ERROR;
    }

    //TODO comments
    private static ParsingError isNumberOfBracketsCorrect(String expression) {
        Stack<Character> stack = new Stack<>();
        for (char c : expression.toCharArray()) {
            Operator operator = getOperatorBySymbol(c);
            if (!isBracket(operator)) {
                continue;
            }

            if (operator == OPEN_BRACKET) {
                stack.push(c);
            } else if (operator == CLOSE_BRACKET && stack.isEmpty()) {
                return NUMBER_OF_BRACKETS_INCORRECT;
            } else {
                stack.pop();
            }
        }
        return stack.isEmpty() ? NO_ERROR : NUMBER_OF_BRACKETS_INCORRECT;
    }

    /**
     * This method is responsible for checking if parsed expression is valid
     *
     * @param expression - Parsed expression to be checked
     * @return True if it's valid, false if not
     */
    public static boolean isExpressionValid(Expression expression) {
        if (expression == null) {
            return false;
        }

        ParsingError operandsPresent = isOperandsPresent(expression.operands());
        showErrorMessage(operandsPresent);

        ParsingError operatorsPresent = isOperatorsPresent(expression.operators());
        showErrorMessage(operatorsPresent);

        return operandsPresent == NO_ERROR && operatorsPresent == NO_ERROR;
    }

    /**
     * This method is responsible for checking is any operands present in parsed expression
     *
     * @param operands - Collection of operands te be checked
     * @return NO_ERROR if any pair of operands is present, OPERANDS_NOT_PRESENT if not
     */
    private static ParsingError isOperandsPresent(HashMap<Integer, OperandPair> operands) {
        return operands.size() == 0 ? OPERANDS_NOT_PRESENT : NO_ERROR;
    }

    /**
     * This method is responsible for checking is any operators present in parsed expression
     *
     * @param operators - Collection of operators te be checked
     * @return NO_ERROR if any operator is present, OPERATORS_NOT_PRESENT if not
     */
    private static ParsingError isOperatorsPresent(TreeMap<Operator, LinkedList<Integer>> operators) {
        return operators.size() == 0 ? OPERATORS_NOT_PRESENT : NO_ERROR;
    }
}
