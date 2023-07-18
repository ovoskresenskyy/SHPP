package com.shpp.p2p.cs.ovoskresenskyy.assignment10.service;

import com.shpp.p2p.cs.ovoskresenskyy.assignment10.enums.ParsingError;
import com.shpp.p2p.cs.ovoskresenskyy.assignment10.model.Expression;
import com.shpp.p2p.cs.ovoskresenskyy.assignment10.model.OperandPair;
import com.shpp.p2p.cs.ovoskresenskyy.assignment10.enums.Operator;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeMap;

import static com.shpp.p2p.cs.ovoskresenskyy.assignment10.enums.Operator.*;
import static com.shpp.p2p.cs.ovoskresenskyy.assignment10.enums.ParsingError.NO_ERROR;
import static com.shpp.p2p.cs.ovoskresenskyy.assignment10.enums.ParsingError.showErrorMessage;
import static com.shpp.p2p.cs.ovoskresenskyy.assignment10.service.ExpressionCleaner.prepareExpression;
import static com.shpp.p2p.cs.ovoskresenskyy.assignment10.service.ExpressionValidator.isArgsEmpty;
import static com.shpp.p2p.cs.ovoskresenskyy.assignment10.service.ExpressionValidator.isTextExpressionValid;

/**
 * This class is responsible for parsing received arguments.
 */
public class ExpressionParser {

    /**
     * This method is responsible for the converting the received arguments into
     * the class Expression
     *
     * @param args - Arguments from the user
     * @return Instance of the class Expression
     */
    public static Expression getParsedExpression(String[] args) {
        ParsingError argsEmpty = isArgsEmpty(args);
        if (argsEmpty != NO_ERROR) {
            showErrorMessage(argsEmpty);
            return null;
        }

        String preparedExpression = prepareExpression(args);

        if (!isTextExpressionValid(preparedExpression)) {
            return null;
        }

        return new Expression(extractOperands(preparedExpression), extractOperators(preparedExpression));
    }

    /**
     * This method is responsible for the extracting the operators from the given expression
     * and hold those positions in the expression.
     *
     * @param expression - Prepared expression to be parsed
     * @return Collection with all present operators with their places
     */
    private static TreeMap<Operator, LinkedList<Integer>> extractOperators(String expression) {
        TreeMap<Operator, LinkedList<Integer>> operators = new TreeMap<>(priorityComparator);

        int counter = 0;

        for (Character symbol : expression.toCharArray()) {
            if (isOperator(symbol)) {
                Operator operator = getOperatorBySymbol(symbol);

                LinkedList<Integer> positions = operators.getOrDefault(operator, new LinkedList<>());
                positions.add(counter++);

                operators.put(operator, positions);
            }
        }

        return operators;
    }

    /**
     * This method is responsible for the extracting the operand from the given expression
     * and creating the pairs to be calculated later.
     *
     * @param expression - Prepared expression to be parsed
     * @return Collection with all present operands, saved as pairs with their places
     */
    private static HashMap<Integer, OperandPair> extractOperands(String expression) {
        HashMap<Integer, OperandPair> operands = new HashMap<>();
        String[] separatedOperands = expression.split(getOperatorsAsDelimiter());

        int operatorIndex = 0;

        for (int i = 0; i < separatedOperands.length - 1; i++) {
            double leftOperand = Double.parseDouble(separatedOperands[i]);
            double rightOperand = Double.parseDouble(separatedOperands[i + 1]);

            operands.put(operatorIndex++, new OperandPair(leftOperand, rightOperand));
        }

        return operands;
    }
}
