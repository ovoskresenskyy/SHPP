package com.shpp.p2p.cs.ovoskresenskyy.assignment1;

import com.shpp.karel.KarelTheRobot;

/**
 * This program helps Karel to draw a Checker Board.
 * Karel will use a cell-by-cell traversal of the field, moving to a new row at the end of the previous one.
 * Marking every second cell starting with the South-West corner.
 */
public class Assignment1Part4 extends KarelTheRobot {

    /**
     * Precondition: Karel stands at the South-West corner and looks at the East.
     * There are no beepers and walls on the field.
     * Given world is rectangular.
     * Result: Checker board is drawn on the given field.
     */
    public void run() throws Exception {
        markCurrentCellAndJumpToTheNext();
    }

    /**
     * This method will mark the cell where Karel is standing and "jump over" to the next one.
     * "Jump over" means go throw the first cell to the second.
     * Precondition: Is missing.
     * Result: Previous cell is marked and Karel moved two cells forward.
     */
    private void markCurrentCellAndJumpToTheNext() throws Exception {
        putBeeper();
        jumpOverTheCell();
    }

    /**
     * This method simulates a jump: Moving Karel two cells forward or to the next row if previous is ended.
     * Precondition: Karel standing facing the row.
     * Result: Karel moved two cells forward
     */
    private void jumpOverTheCell() throws Exception {
        firstStep();
        secondStep();
    }

    /**
     * This method simply moves Karel forward or to the next row if current is ended.
     * Precondition: Karel standing facing the row.
     * Result: Karel moved one cell forward.
     */
    private void firstStep() throws Exception {
        if (frontIsClear()) move();
        else moveUpOneRow();
    }

    /**
     * This method moves Karel forward AND call method which mark reached cell,
     * or moves him to the next row if current is ended.
     * Precondition: Karel standing facing the row.
     * Result: Karel moved one cell forward, reached cell is marked with beeper.
     */
    private void secondStep() throws Exception {
        if (frontIsClear()) {
            move();
            /*
             * Repeat until there are cells where to move
             */
            markCurrentCellAndJumpToTheNext();
        } else moveUpOneRowAndMarkCell();
    }

    /**
     * This method moves Karel to the next row and turns him in the correct direction.
     * Precondition: Karel standing in front of the wall (at the end of the row) facing the wall.
     * Result: Karel moved up one cell (to the next row) and turned back to the wall.
     */
    private void moveUpOneRow() throws Exception {
        turnNorth();
        if (frontIsClear()) {
            move();
            turnBackToTheWall();
        }
    }

    /**
     * This method moves Karel to the next row if it presents,
     * turns him in the correct direction,
     * mark cell and jump to the next one.
     * Precondition: Karel standing in front of the wall (at the end of the row) facing the wall.
     * Result: Karel moved up one cell (to the next row),
     * turned back to the wall and reached cell is marked.
     */
    private void moveUpOneRowAndMarkCell() throws Exception {
        turnNorth();
        if (frontIsClear()) {
            move();
            turnBackToTheWall();
            /*
             * Repeat until there are cells where to move
             */
            markCurrentCellAndJumpToTheNext();
        }
    }

    /**
     * This method turns Karel in right direction as he moves from West to East and then reverse.
     * Precondition: Karel stands holding his hand against the wall.
     * Result: Karel stands turned back to the wall.
     */
    private void turnBackToTheWall() throws Exception {
        if (rightIsBlocked()) turnLeft();
        else turnRight();
    }

    /**
     * This method turns Karel to face north according to our movement from south to north.
     * Precondition: Is missing.
     * Result: Karel faces North.
     */
    private void turnNorth() throws Exception {
        while (notFacingNorth()) turnLeft();
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
