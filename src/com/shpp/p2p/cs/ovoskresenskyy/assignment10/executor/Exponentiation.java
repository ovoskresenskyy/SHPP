package com.shpp.p2p.cs.ovoskresenskyy.assignment10.executor;

import com.shpp.p2p.cs.ovoskresenskyy.assignment10.model.OperandPair;

/**
 * This class is responsible for raising the left value of received pair to the power of the right.
 */
public class Exponentiation implements Calculatable {

    @Override
    public double calculate(OperandPair operandPair) {
        return Math.pow(operandPair.getLeft(), operandPair.getRight());
    }
}
