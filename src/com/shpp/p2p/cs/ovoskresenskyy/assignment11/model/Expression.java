package com.shpp.p2p.cs.ovoskresenskyy.assignment11.model;

import com.shpp.p2p.cs.ovoskresenskyy.assignment11.enums.Operator;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;
import java.util.TreeMap;

/**
 * This class is representing of the parsed math expression
 *
 */
public final class Expression {
    private final HashMap<Integer, OperandPair> operands;
    private final TreeMap<Operator, LinkedList<Integer>> operators;

    /**
     * @param operands  - List of operands, represented as a pairs of values, which are
     *                  placed on the sides of the operator.
     *                  <p>
     *                  Key - The index of the operator between the given operands
     *                  Value - Pair of the operands
     * @param operators - List of operators, with the indexes of their appearance on the expression.
     */
    public Expression(HashMap<Integer, OperandPair> operands,
                      TreeMap<Operator, LinkedList<Integer>> operators) {
        this.operands = operands;
        this.operators = operators;
    }

    public HashMap<Integer, OperandPair> operands() {
        return operands;
    }

    public TreeMap<Operator, LinkedList<Integer>> operators() {
        return operators;
    }


}
