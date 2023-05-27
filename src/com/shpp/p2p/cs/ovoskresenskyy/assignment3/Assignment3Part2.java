package com.shpp.p2p.cs.ovoskresenskyy.assignment3;

import com.shpp.cs.a.console.TextProgram;

/**
 * Hail-numbers
 * <p>
 * The program gets the positive number and process it until it becomes 1.
 */
public class Assignment3Part2 extends TextProgram {

    @Override
    public void run() {
        /* Get an any positive number from the user. */
        int number = getNumberFromUser();

        int processedNumber = processNumber(number);
        println("The processed number is equals " + processedNumber);
    }

    /**
     * The method receives the number and processes it according to the rules:
     * - divide it by 2 if it's even
     * - or multiplies by 3 and adds 1 if it's odd.
     * The process continues until it becomes 1.
     * <p>
     * Precondition: Is missed.
     * Result: The number becomes 1.
     *
     * @param number - The number to process.
     * @return - 1
     */
    private int processNumber(int number) {
        while (number != 1) {
            if (number % 2 == 0) {
                number = processEvenNumber(number);
            } else {
                number = processOddNumber(number);
            }
        }
        return number;
    }

    /**
     * The method receives the number and divide it by 2
     *
     * @param number - The even number to process.
     * @return - Processed number
     */
    private int processEvenNumber(int number) {
        int processedNumber = number / 2;
        println(number + " - is even. So I take a half: " + processedNumber);
        return processedNumber;
    }

    /**
     * The method receives the number multiplies it by 3 and adds 1
     *
     * @param number - The odd number to process.
     * @return - Processed number
     */
    private int processOddNumber(int number) {
        int processedNumber = number * 3 + 1;
        println(number + " - is odd. So I make 3n + 1: " + processedNumber);
        return processedNumber;
    }

    /**
     * The method asks the user to input any positive number.
     * Prevents the user from entering an negative number.
     *
     * @return - Positive number
     */
    private int getNumberFromUser() {
        int number = readInt("Enter a positive number: ");

        if (number < 0) {
            return getNumberFromUser();
        } else {
            return number;
        }
    }
}
