package com.shpp.p2p.cs.ovoskresenskyy.assignment3;

import com.shpp.cs.a.console.TextProgram;

/**
 * Exponentiation
 * <p>
 * The program will raise a number to a power without using libraries.
 */
public class Assignment3Part3 extends TextProgram {

    @Override
    public void run() {
        /* Variables for testing. */
        double base = 3;
        int exponent = -3;

        println("The number " + base
                + " raised to the power of " + exponent
                + " is equals " + raiseToPower(base, exponent));
    }

    /**
     * The method raises base to power of exponent.
     * <p>
     * Precondition: If exponent equals 0 we will return 1 without calculating.
     *
     * @param base     - Number to raise
     * @param exponent - Power of number
     * @return - Received number raised to power
     */
    private double raiseToPower(double base, int exponent) {
        if (exponent == 0) {
            return 1;
        } else if (exponent < 0) {
            return 1 / calculateExponentiation(base, -exponent);
        } else {
            return calculateExponentiation(base, exponent);
        }
    }

    /**
     * The method calculates the raising to the power
     *
     * @param base     - Number to raise
     * @param exponent - Power of number
     * @return - Received number raised to power
     */
    private double calculateExponentiation(double base, int exponent) {
        double result = base;
        for (int i = 1; i < exponent; i++) {
            result *= base;
        }
        return result;
    }
}
