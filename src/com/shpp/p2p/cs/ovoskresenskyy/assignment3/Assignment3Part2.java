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
        /* Get a number from the user and process it. */
        processNumber(readInt("Enter a number: "));
    }

    /**
     * The method receives the number and processes it according to the rules:
     * - divide it by 2 if it's even
     * - or multiplies by 3 and adds 1 if it's odd.
     * The process continues until it becomes 1.
     * <p>
     * Precondition: The number must be more than 0.
     * Result: The number becomes 1.
     *
     * @param number - The number to process.
     */
    private void processNumber(int number) {
        if (number < 1) {
            println("The number must be more than 0");
            return;
        }

        while (number != 1) {
            if (number % 2 == 0) {
                println(number + " - is even. So I take a half: " + (number = number / 2));
            } else {
                println(number + " - is odd. So I make 3n + 1: " + (number = number * 3 + 1));
            }
        }
        println("The number is finally becomes 1");
    }
}
