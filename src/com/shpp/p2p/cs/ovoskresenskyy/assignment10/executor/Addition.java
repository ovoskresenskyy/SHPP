package com.shpp.p2p.cs.ovoskresenskyy.assignment10.executor;

import com.shpp.p2p.cs.ovoskresenskyy.assignment10.model.OperandPair;

/**
 * This class is responsible for adding left value of received pair with the right.
 */
public class Addition implements Calculatable {

    @Override
    public double calculate(OperandPair operandPair) {
        return operandPair.getLeft() + operandPair.getRight();
    }
}
