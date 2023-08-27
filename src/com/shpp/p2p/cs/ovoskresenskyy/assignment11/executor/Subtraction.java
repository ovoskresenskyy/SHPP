package com.shpp.p2p.cs.ovoskresenskyy.assignment11.executor;

import com.shpp.p2p.cs.ovoskresenskyy.assignment11.model.OperandPair;

/**
 * This class is responsible for subtraction from left value of received pair to the right.
 */
public class Subtraction implements Calculatable {

    @Override
    public double calculate(OperandPair operandPair) {
        return operandPair.getLeft() - operandPair.getRight();
    }
}
