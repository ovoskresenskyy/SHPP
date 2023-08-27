package com.shpp.p2p.cs.ovoskresenskyy.assignment11.enums;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This class is representing of all operators which are supported in our calculator.
 */
public enum Operator {

    EXPONENT('^', 1),
    MULTIPLICATION('*', 2),
    DIVISION('/', 3),
    SUBTRACTION('-', 4),
    ADDITION('+', 5),

    OPEN_BRACKET('(', 0),
    CLOSE_BRACKET(')', 0);

    /**
     * Text representing of the operator
     */
    final private char symbol;

    /**
     * Priority of the execution inside the expression.
     * ^ >> * / >> + -
     */
    final private int priority;

    /**
     * Comparator, to be sure that operator held in the correct order.
     */
    public static final Comparator<Operator> priorityComparator = Comparator.comparingInt(o -> o.priority);

    /**
     * Simple collection to use it instead of values[]
     */
    private static final Map<Character, Operator> BY_SYMBOL = new HashMap<>();

    /* Used static init area to fill the collection. */
    static {
        for (Operator e : values()) {
            BY_SYMBOL.put(e.symbol, e);
        }
    }

    Operator(Character symbol, int priority) {
        this.symbol = symbol;
        this.priority = priority;
    }

    /**
     * This method is responsible for determination is received symbol can be matched
     * with any supported operator.
     *
     * @param symbol - Char to be checked if it's operator
     * @return True if received character is operator, false if not.
     */
    public static boolean isOperator(Character symbol) {
        return BY_SYMBOL.containsKey(symbol);
    }

    //TODO
    public static boolean isBracket(Operator operator) {
        return operator == OPEN_BRACKET
                || operator == CLOSE_BRACKET;
    }

    /**
     * This method is responsible for finding the operator by the given character.
     *
     * @param symbol - Character to find matched operator into the Map
     * @return Operator if it's found by the received character, null if not.
     */
    public static Operator getOperatorBySymbol(Character symbol) {
        return BY_SYMBOL.get(symbol);
    }

    /**
     * This method is responsible for creating the Regex from the all
     * supported operators.
     *
     * @return Something like that [\\+\\-\\* ...] according to the supported operators.
     */
    public static String getOperatorsAsDelimiter() {
        return BY_SYMBOL.values().stream()
                .filter(operator -> operator != OPEN_BRACKET && operator != CLOSE_BRACKET)
                .map(operator -> operator.symbol)
                .map(Object::toString)
                .collect(Collectors.joining("\\", "[", "]"));
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}
