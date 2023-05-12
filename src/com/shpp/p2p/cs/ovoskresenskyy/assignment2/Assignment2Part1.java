package com.shpp.p2p.cs.ovoskresenskyy.assignment2;

import com.shpp.cs.a.console.TextProgram;

/**
 * Quadratic equation
 * <p>
 * A console program that will accept 3 numbers of type double (a, b, c) as input,
 * and output the square root of the equation.
 */
public class Assignment2Part1 extends TextProgram {

    /* Coefficients */
    private double a, b, c;
    /* We will calculate the discriminant to understand the number of roots */
    private double discriminant;

    @Override
    public void run() {
        getCoefficientsFromUser();
        findRoots();
    }

    /**
     * Entering coefficient values by the user.
     */
    private void getCoefficientsFromUser() {
        this.a = readDouble("Please enter a: ");
        this.b = readDouble("Please enter b: ");
        this.c = readDouble("Please enter c: ");
    }

    /**
     * The method checks if it's possible to find the roots, and display if possible.
     * To do this, we need to calculate the discriminant.
     */
    private void findRoots() {
        this.discriminant = b * b - 4 * a * c;

        if (rootsIsPresent()) printRoots();
        else println("There are no real roots.");
    }

    /**
     * The Method checks if it's possible to get roots.
     * Condition: If (a = 0) OR (discriminant < 0) - no roots for this coefficients
     */
    private boolean rootsIsPresent() {
        return a != 0 && discriminant >= 0;
    }

    /**
     * The method prints the found roots.
     */
    private void printRoots() {
        println(discriminant == 0
                ? "There is one root: " + getFirstRoot()
                : "There are two roots: " + getFirstRoot() + " and " + getSecondRoot());
    }

    /**
     * Simple formula for getting the first root.
     * Precondition: a != 0
     */
    private double getFirstRoot() {
        return (-b + Math.sqrt(discriminant)) / (2 * a);
    }

    /**
     * Simple formula for getting the second root.
     * Precondition: a != 0
     */
    private double getSecondRoot() {
        return (-b - Math.sqrt(discriminant)) / (2 * a);
    }
}
