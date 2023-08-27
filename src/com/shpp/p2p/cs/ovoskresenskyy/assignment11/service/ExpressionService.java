package com.shpp.p2p.cs.ovoskresenskyy.assignment11.service;

import com.shpp.p2p.cs.ovoskresenskyy.assignment11.model.OperandPair;

import java.util.HashMap;

/**
 * This class is responsible for the updating relations between pairs of operands
 * inside the expression.
 * <p>
 * After calculating each of pairs, need to update values of the neighbors.
 */
public class ExpressionService {

    /**
     * This method is goes through all neighbor pairs and update
     * their values according to the result of the previous calculation.
     *
     * @param operands     - List of all operands inside the expression
     * @param currentIndex - Index of the calculated pair of the operands
     * @param result       - Result of the calculating current pair
     */
    public static void updateOperandRelations(HashMap<Integer, OperandPair> operands,
                                              Integer currentIndex,
                                              double result) {


        OperandPair nextLeftOperand = getNextOperand(operands, currentIndex, false);
        if (nextLeftOperand != null) {
            nextLeftOperand.setRight(result);
        }

        OperandPair nextRightOperand = getNextOperand(operands, currentIndex, true);
        if (nextRightOperand != null) {
            nextRightOperand.setLeft(result);
        }
    }

    /**
     * This method can go through all pairs from both sides of the current,
     * check if it's not already calculated, and if not - change the value,
     * according to the result of calculating the current one.
     * <p>
     * This method is recursive, so we will get the next pair if found pair is calculated.
     *
     * @param operands     - List of all operands inside the expression
     * @param currentIndex - Index of the calculated pair of the operands
     * @param searchRight  - Search direction. Left if false, right if true.
     * @return Operand pair if not calculated found,
     * next pair  if calculated found
     * or null if end reaches
     */
    private static OperandPair getNextOperand(HashMap<Integer, OperandPair> operands,
                                              Integer currentIndex,
                                              boolean searchRight) {

        if (endReaches(operands.size(), currentIndex, searchRight)) {
            return null;
        }

        int nextIndex = searchRight ? currentIndex + 1 : currentIndex - 1;
        OperandPair operandPair = operands.get(nextIndex);

        if (operandPair.isCalculated()) {
            return getNextOperand(operands, nextIndex, searchRight);
        }

        return operandPair;
    }

    /**
     * This method is responsible for checking if the end of expression reaches
     *
     * @param operandsSize - The size of the list of the operands
     * @param currentIndex - Index of the calculated pair of the operands
     * @param searchRight  - Search direction. Left if false, right if true.
     * @return True if end reaches, false if not
     */
    private static boolean endReaches(int operandsSize, int currentIndex, boolean searchRight) {
        return searchRight && currentIndex == operandsSize - 1 || !searchRight && currentIndex == 0;
    }
}
