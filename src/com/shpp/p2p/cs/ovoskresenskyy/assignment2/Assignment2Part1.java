package com.shpp.p2p.cs.ovoskresenskyy.assignment2;

import com.shpp.cs.a.console.TextProgram;

public class Assignment2Part1 extends TextProgram {

    private double a, b, c;
    private double discriminant;

    @Override
    public void run() {
        getVariables();
        findRoots();
    }

    private void getVariables() {
        this.a = readDouble("Please enter a: ");
        this.b = readDouble("Please enter b: ");
        this.c = readDouble("Please enter c: ");
    }

    private void findRoots() {
        if (rootsIsPresent()) printRoots();
        else println("There are no real roots");
    }

    private void printRoots() {
        println(discriminant == 0
                ? "There is one root: " + getFirstRoot()
                : "There are two roots: " + getFirstRoot() + " and " + getSecondRoot());
    }

    private boolean rootsIsPresent() {
        calculateDiscriminant();
        return discriminant >= 0;
    }

    private void calculateDiscriminant() {
        this.discriminant = b * b - 4 * a * c;
    }

    private double getFirstRoot() {
        return (-b + Math.sqrt(discriminant)) / (2 * a);
    }

    private double getSecondRoot() {
        return (-b - Math.sqrt(discriminant)) / (2 * a);
    }
}
