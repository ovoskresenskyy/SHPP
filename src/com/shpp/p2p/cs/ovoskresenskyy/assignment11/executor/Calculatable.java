package com.shpp.p2p.cs.ovoskresenskyy.assignment11.executor;

import com.shpp.p2p.cs.ovoskresenskyy.assignment11.model.OperandPair;

/**
 * This class is a contract to all classes which can calculate
 */
@FunctionalInterface
public interface Calculatable {

    double calculate(OperandPair operandPair);
}

