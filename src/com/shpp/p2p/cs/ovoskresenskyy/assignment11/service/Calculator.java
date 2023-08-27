package com.shpp.p2p.cs.ovoskresenskyy.assignment11.service;

import com.shpp.p2p.cs.ovoskresenskyy.assignment11.enums.Operator;
import com.shpp.p2p.cs.ovoskresenskyy.assignment11.executor.*;
import com.shpp.p2p.cs.ovoskresenskyy.assignment11.model.Expression;
import com.shpp.p2p.cs.ovoskresenskyy.assignment11.model.OperandPair;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * This class is responsible for calculating received expressions
 */
public class Calculator {

    /**
     * Collection of executors, one for each known operator
     */
    private static final HashMap<Operator, Calculatable> EXECUTORS = new HashMap<>();

    /* Used static init area to fill the map with the executors
     * This area init once at first class using. */
    static {
        for (Operator operator : Operator.values()) {
            EXECUTORS.put(operator,
                    switch (operator) {
                        case ADDITION -> new Addition();
                        case SUBTRACTION -> new Subtraction();
                        case MULTIPLICATION -> new Multiplication();
                        case DIVISION -> new Division();
                        case EXPONENT -> new Exponentiation();
                    });
        }
    }

    /**
     * This method goes through the received expression and calculate all
     * operands pairs in correct order
     *
     * @param expression - Parsed and prepared expression
     * @return The result of the calculation
     */
    public static double calculate(Expression expression) {
        double result = 0;

        for (Operator operator : expression.operators().keySet()) {
            result = performActions(expression, operator);
        }

        return result;
    }

    /**
     * This method is responsible for performing the action for each operator
     * going through the all position of the received operator in the expression.
     * <p>
     * After each iteration relation pair of the operands will be updated
     * with the result of the current calculation
     *
     * @param expression - Received expression to be calculated
     * @param operator   - Current operator. Each of them comes in the correct order
     * @return The result of the calculation
     */
    private static double performActions(Expression expression, Operator operator) {
        HashMap<Integer, OperandPair> operands = expression.operands();
        LinkedList<Integer> operatorPositions = expression.operators().get(operator);

        Calculatable executor = EXECUTORS.get(operator);

        double result = 0;

        for (Integer position : operatorPositions) {
            OperandPair operandPair = operands.get(position);

            /* Calculating the current pair of the operands using interface. */
            result = executor.calculate(operandPair);

            /* Updating all relative pairs with the result of the current calculation. */
            ExpressionService.updateOperandRelations(operands, position, result);

            /* Marking current pair as calculating. */
            operandPair.setCalculated(true);
        }

        return result;
    }
}
