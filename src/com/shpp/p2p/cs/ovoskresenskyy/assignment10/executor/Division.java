package com.shpp.p2p.cs.ovoskresenskyy.assignment10.executor;

import com.shpp.p2p.cs.ovoskresenskyy.assignment10.model.OperandPair;

/**
 * This class is responsible for dividing left value of received pair by the right.
 */
public class Division implements Calculatable {

    @Override
    public double calculate(OperandPair operandPair) {
        return operandPair.getLeft() / operandPair.getRight();
    }
}
