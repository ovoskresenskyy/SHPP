package com.shpp.p2p.cs.ovoskresenskyy.assignment5;

import com.shpp.cs.a.console.TextProgram;

/**
 * Addition algorithm
 * <p>
 * The class will ask the user to enter two strings of numbers
 * and return the result of the addition.
 * <p>
 * - strings will be non-negative
 * - input strings will only contain numbers, it can't be "1,000,000" or " "
 * - the input strings are not necessarily the same length.
 */
public class Assignment5Part2 extends TextProgram {

    /* This word will be used to stop the program. */
    public static final String WORD_TO_STOP = "stop";

    @Override
    public void run() {

        startAddingNumbers();

    }

    /**
     * The method keeps asking the user to enter two numbers until he enters "stop".
     * <p>
     * Having received the numbers, counts and displays the sum.
     */
    private void startAddingNumbers() {
        String n1 = "";
        String n2 = "";

        /* Sit in a loop, reading numbers and adding them. */
        while (!n1.equals(WORD_TO_STOP) && !n2.equals(WORD_TO_STOP)) {
            n1 = readLine("Enter first number or \"" + WORD_TO_STOP + "\" for exit: ");
            n2 = readLine("Enter second number or \"" + WORD_TO_STOP + "\" for exit: ");
            println(n1 + " + " + n2 + " = " + addNumericStrings(n1, n2) + "\n");
        }
    }

    /**
     * Given two string representations of non-negative integers, adds the
     * numbers represented by those strings and returns the result.
     * <p>
     * The method will add the digits from right to left, each time adding a carry
     * if the addition in the previous step exceeded 10.
     * <p>
     * The result of the addition will be added in reverse order
     * and will be reversed just before returning the final result.
     *
     * @param n1 - The first number.
     * @param n2 - The second number.
     * @return A String representation of n1 + n2
     */
    private String addNumericStrings(String n1, String n2) {
        StringBuilder result = new StringBuilder();

        /* The carry to count tens. */
        int carry = 0;

        /* Separate counters for two numbers. */
        int counter1 = n1.length() - 1;
        int counter2 = n2.length() - 1;

        while (counter1 >= 0 || counter2 >= 0) {
            int sum = countSum(n1, n2, counter1, counter2, carry);

            /* We add only units to the sum.
             * To do this, we take the remainder of the division by 10. */
            result.append(sum % 10);

            /* An integer division of the sum by 10
             * will give '1' if the sum is greater than 10
             * and '0' if it is less. */
            carry = sum / 10;

            counter1--;
            counter2--;
        }

        if (carry == 1) {
            result.append(carry);
        }

        /* We added the folding results in reverse order. Need to reverse to get correct result. */
        return result.reverse().toString();
    }

    /**
     * The method simply adds the digits and the carry.
     *
     * @param n1       - First given number.
     * @param n2       - Second given number.
     * @param counter1 - Index of the digit of the first number.
     * @param counter2 - Index of the digit of the second number.
     * @param carry    - The numeric value of the carry. Can be 0 or 1
     * @return The sum of the digits and the carry.
     */
    private int countSum(String n1, String n2, int counter1, int counter2, int carry) {
        return getNumberByIndex(n1, counter1)
                + getNumberByIndex(n2, counter2)
                + carry;
    }

    /**
     * The method get the digit from the received number by the index
     * and convert it to the integer.
     *
     * @param number - The given number
     * @param index  - The index of digit the given number.
     * @return The numeric value of the digit or 0 if it's not found.
     */
    private int getNumberByIndex(String number, int index) {
        if (index < 0) {
            return 0;
        }
        return number.charAt(index) - '0';
    }
}
