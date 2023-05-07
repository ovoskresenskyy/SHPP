package com.shpp.p2p.cs.ovoskresenskyy.assignment1;

import com.shpp.karel.KarelTheRobot;

/**
 * This program helps Karel to clean a nuclear reactor.
 * The nuclear reactor is composed of a sequence of vertical columns (reactor elements),
 * as well as spaced by service vertical columns (with walls).
 * Each element of the reactor has a width of 1 and a height of 3 cells.
 */
public class Assignment1PartExt extends KarelTheRobot {

    /**
     * This method determines the type of reactor and starts cleaning.
     * Speaking about the types of reactors, we mean that the reactor can start with a service column.
     * Precondition: Karel always appears in the center of the column, which is located near the left border,
     * and looks towards the "corridor" along which he will have to go.
     * Result: All trash is cleaned. Karel stands in the rightmost cell, facing East (to the right).
     */
    public void run() throws Exception {
        /*
         * Karel has to be prepared before start of cleaning.
         */
        determineTypeOfReactor();

        cleanReactor();
    }

    /**
     * This method determines if this reactor is starts with a service column.
     * If it's true Karel has to make one step forward.
     * Precondition: Karel is stands in the center of the column, facing East.
     * Result: Karel is prepared to clean reactor elements.
     */
    private void determineTypeOfReactor() throws Exception {
        turnLeft();
        if (frontIsBlocked()) {
            turnRight();
            makeOneStepForward();
        } else turnRight();
    }

    /**
     * This method helps Karel to move through the reactor and clean all elements.
     * Precondition: Karel is stands in the center of the reactor's element, facing East.
     * Result: Reactor's element is processed and Karel moved to the next element.
     */
    private void cleanReactor() throws Exception {
        while (frontIsClear()) {
            processArea();
            moveToNextElement();
        }
        /*
         * We have to try processing the last element if it's placed near the right border of the reactor.
         */
        processArea();
    }

    /**
     * This method processing the reactor's element and clean it if it's necessary.
     * Precondition: Karel stands in the center of the reactor's element, facing East.
     * Result: Reactor's element is processed, Karel returned to the starting position.
     */
    private void processArea() throws Exception {
        if (noBeepersPresent()) cleanArea();
    }

    /**
     * This method is cleaning trash in the top and bottom of reactor's element.
     * Precondition: Karel stands in the center of the reactor's element, facing East.
     * Result: Reactor's element is cleaned, Karel returned to the starting position.
     */
    private void cleanArea() throws Exception {
        /*
         * Clean top
         */
        turnLeft();
        cleanTrash();

        /*
         * Clean bottom
         */
        cleanTrash();

        /*
         * Turn East
         */
        turnRight();
    }

    /**
     * This method is cleaning trash in the cell.
     * Precondition: Karel stands in the center of the reactor's element, facing not cleaned cell.
     * Result: Cell is cleaned, Karel returned to the center, facing to the another cell.
     */
    private void cleanTrash() throws Exception {
        if (frontIsClear()) {
            move();
            while (beepersPresent()) pickBeeper();
            turnAround();
            move();
        }
    }

    /**
     * Precondition: Karel stands in the "corridor", facing East.
     * Result: Karel moved two cells forward, to the next element.
     */
    private void moveToNextElement() throws Exception {
        makeOneStepForward();
        makeOneStepForward();
    }

    /**
     * Precondition: Karel stands in the "corridor", facing East.
     * Result: Karel moved one cell forward.
     */
    private void makeOneStepForward() throws Exception {
        if (frontIsClear()) move();
    }

    /**
     * This method turns Karel around.
     * Precondition: Is missing.
     * Result: Karel will be rotated 180 degrees.
     */
    private void turnAround() throws Exception {
        turnLeft();
        turnLeft();
    }

    /**
     * This method turns Karel right.
     * Precondition: Is missing.
     * Result: Karel will be rotated 90 degrees clockwise.
     */
    private void turnRight() throws Exception {
        turnLeft();
        turnLeft();
        turnLeft();
    }
}
