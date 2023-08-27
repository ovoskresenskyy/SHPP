package com.shpp.p2p.cs.ovoskresenskyy.assignment11.enums;

/**
 * This class is hold all known errors
 */
public enum ParsingError {
    ARGS_EMPTY("There are no any arguments present!"),

    /**
     * Text expression errors
     */
    NUMBER_OF_BRACKETS_INCORRECT("The number of brackets is incorrect!"),
    OPERANDS_NOT_VALID("Expression contains forbidden symbols!"),
    VARIABLE_PRESENT("Expression contains don't matched variables!"),
    EMPTY_OPERANDS_PRESENT("Number of operators doesn't match with the given operands!"),

    /**
     * Parsed expression errors
     */
    OPERANDS_NOT_PRESENT("There are no operands present in the expression!"),
    OPERATORS_NOT_PRESENT("There are no operators present in the expression!"),

    NO_ERROR("");

    private final String errorMessage;

    ParsingError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * This method is simply prints the error message to the user
     *
     * @param error - Parsing error to be printed
     */
    public static void showErrorMessage(ParsingError error) {
        if (error != NO_ERROR) {
            System.out.println(error.errorMessage);
        }
    }
}
