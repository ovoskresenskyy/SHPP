package com.shpp.p2p.cs.ovoskresenskyy.assignment1;

import com.shpp.karel.KarelTheRobot;

/**
 * This program helps Karel complete construction of the columns by putting "stones" in a row.
 * The last column is placed on the border of the field.
 * Columns are starts from the "floor".
 */
public class Assignment1Part2 extends KarelTheRobot {

    /**
     * This method starts building of columns.
     * Preconditions: Karel stands in the South-West corner of the field, he looks to the East.
     * Result: Built straight equidistant columns with beepers.
     */
    @Override
    public void run() throws Exception {
        buildColumn();
    }

    /**
     * This method, using recursion, builds columns and ends when it's no more to build.
     * Karel, after building the column, if it's possible, preparing to build another one.
     * Preconditions: Karel stands at the beginning cell of the future column,
     * there is a wall that limits the height of the column,
     * there are already some placed beepers.
     * Result: All columns are built.
     */
    private void buildColumn() throws Exception {
        /*
         * First, we have to turn Karel so that he faces North and ready to build column at right direction.
         */
        lookAtTheNorth();
        fillMissedStones();
        /*
         * Only if it's not the end of the field we can build new a column.
         */
        if (rightIsClear()) {
            prepareToBuildNextColumn();
            buildColumn();
        }
    }

    /**
     * This method fill in the missing stoned in the column.
     * Precondition: There is a wall that limits the height of the column.
     * Result: Column is built by putting "stones".
     */
    private void fillMissedStones() throws Exception {
        putStone();
        if (frontIsClear()) {
            move();
            fillMissedStones();
        }
    }

    /**
     * This method will put beeper only if there is no beeper yet.
     * Precondition: Is missing.
     * Result: The beeper is placed at the cell Karel stands.
     */
    private void putStone() throws Exception {
        if (noBeepersPresent()) putBeeper();
    }

    /**
     * This method move Karel to the starting position for the building a new column.
     * Precondition: Karel just finish building of the previous column,
     * he stands at the "top" of the column, faces to the top of the column.
     * Result: Karel is ready to build new column, he stands at the "bottom" of future column.
     */
    private void prepareToBuildNextColumn() throws Exception {
        /*
         * First, Karel returns to the floor, to make sure he can reach next column.
         */
        goDownTheColumn();
        moveToTheNextColumn();
    }

    /**
     * This method return Karel to the beginning of just built column.
     * Precondition: Karel stands at the "top" of the column,
     * faces to the top of the column.
     * Result: Karel stands at the "bottom" of just built column.
     * faces to the bottom of the column.
     */
    private void goDownTheColumn() throws Exception {
        turnAround();
        while (frontIsClear()) move();
    }

    /**
     * This method moves forward Karel until he reaches next column.
     * Precondition: Karel stands at the "bottom" of the column, distance between columns 4 cells.
     * Result: Karel reaches the cell where he will build the next column.
     */
    private void moveToTheNextColumn() throws Exception {
        turnLeft();
        for (int i = 0; i < 4; i++) move();
    }

    /**
     * This method turn Karel to make sure he can start build the column in right direction.
     * Precondition: Is missing.
     * Result: Karel faces North.
     */
    private void lookAtTheNorth() throws Exception {
        while (!facingNorth()) turnLeft();
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
}
