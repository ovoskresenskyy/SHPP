package com.shpp.p2p.cs.ovoskresenskyy.assignment1;

import com.shpp.karel.KarelTheRobot;

/**
 * This program helps Karel pick up the newspaper at the doorstep
 * and return him to the starting position.
 */
public class Assignment1Part1 extends KarelTheRobot {

    /**
     * Preconditions: Karel lives in the square house and stands in the North-West corner of the house.
     * Result: Karel picks up the newspaper and returns to the starting position.
     */
    public void run() throws Exception {
        moveToNewspaper();
        pickUpNewspaper();
        returnToStartingPosition();
    }

    /**
     * Preconditions: Karel looks to the East, newspaper is present and lay at the doorstep.
     * Result: Karel reaches the cell with newspaper.
     */
    private void moveToNewspaper() throws Exception {
        stepRight();
        moveForwardToNewspaper();
    }

    /**
     * This method will just pick newspaper
     * Precondition: Is missing
     * Result: Newspaper is picked up
     */
    private void pickUpNewspaper() throws Exception {
        /*
         * Because we didn't say about precondition we have to check is newspaper is present at that cell.
         */
        if (beepersPresent()) pickBeeper();
    }

    /**
     * Preconditions: Karel has already picked up the newspaper,
     * he looks to the East and ready to return to the starting position.
     * Result: Karel stands in the starting position turned to the starting side.
     */
    private void returnToStartingPosition() throws Exception {
        turnAround();
        moveToTheWall();
        stepRight();
        /*
         * Starting position means correct direction, so we will turn around to get it.
         */
        turnAround();
    }

    /**
     * This method will move Karel forward until he reaches the cell with the newspaper.
     * Precondition: Newspaper is present.
     * Result: He will stop at the cell with the newspaper.
     */
    private void moveForwardToNewspaper() throws Exception {
        while (noBeepersPresent()) move();
    }

    /**
     * This method will move Karel forward until he reaches the wall.
     * Precondition: Is missing. We know that Karel's world has walls.
     * Result: He will stop at the cell in front of the wall.
     */
    private void moveToTheWall() throws Exception {
        while (frontIsClear()) move();
    }

    /**
     * This method will move Karel one cell to the right without changing direction.
     * Precondition: Is missing.
     * Result: If it's possible Karel will stay one cell to the right of the previous one.
     */
    private void stepRight() throws Exception {
        /*
         * Because we didn't say about preconditions we have to check if it's possible to move right
         */
        if (rightIsClear()) {
            turnRight();
            move();
            turnLeft();
        }
    }

    /**
     * This method will turn Karel right.
     * Precondition: Is missing.
     * Result: Karel will be rotated 90 degrees clockwise.
     */
    private void turnRight() throws Exception {
        turnAround();
        turnLeft();
    }

    /**
     * This method will turn Karel around.
     * Precondition: Is missing.
     * Result: Karel will be rotated 180 degrees.
     */
    private void turnAround() throws Exception {
        turnLeft();
        turnLeft();
    }
}
