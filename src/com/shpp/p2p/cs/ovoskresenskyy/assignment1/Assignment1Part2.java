package com.shpp.p2p.cs.ovoskresenskyy.assignment1;

import com.shpp.karel.KarelTheRobot;

/**
 * This program will help Karel complete construction of the columns by putting "stones" where needed.
 */
public class Assignment1Part2 extends KarelTheRobot {

    /**
     * Preconditions: Karel stands in the South-West corner of the field, he looks to the East.
     * Result: Built straight equidistant columns.
     */
    public void run() throws Exception {
        buildColumns();
    }

    /**
     * Preconditions: Karel stands at the beginning cell of the future column,
     * there is a wall that limits the height of the columns,
     * Action: After building the column Karel, if it's possible, preparing to build another one.
     * Using recursion this process ends when all columns are built.
     * the last column is placed on the border of the field.
     * Result: All columns are built.
     */
    private void buildColumns() throws Exception {
        /*
         * First, we have to turn Karel so that he faces North and ready to build column at right direction.
         */
        lookAtTheNorth();
        buildColumn();
        /*
         * Only if it's not the end of the field we can build new column.
         */
        if (rightIsClear()) {
            prepareToBuildNextColumn();
            buildColumns();
        }
    }

    /**
     * This method move Karel to the cell where he will build a new column.
     * Precondition: Karel just finish building of the column and stands at the "top" of the column.
     * Result: Karel is ready to build new column, he stands at the "bottom" of future column.
     */
    private void prepareToBuildNextColumn() throws Exception {
        /*
         * First, Karel returns to the floor, to make sure he can reach next column.
         */
        returnToTheBeginningOfTheColumn();
        moveToTheNextColumn();
    }

    /**
     * This method return Karel on the "floor" of the field.
     * Precondition: Karel stands at the "top" of the column and looks at the North.
     * Result: Karel stands at the "bottom" of just built column.
     */
    private void returnToTheBeginningOfTheColumn() throws Exception {
        turnBack();
        moveToTheBeginOfTheColumn();
    }

    /**
     * This method will move Karel until he reaches next column.
     * Precondition: Karel stands at the "bottom" of the column, distance between columns 4 sells.
     * Result: Karel is ready to build new column.
     */
    private void moveToTheNextColumn() throws Exception {
        turnLeft();
        for (int i = 0; i < 4; i++) move();
    }

    /**
     * This method will move Karel forward until he reaches the border of the field.
     * Precondition: Is missing. We know that Karel's world has walls.
     * Result: He will stop at the cell in front of the wall.
     */
    private void moveToTheBeginOfTheColumn() throws Exception {
        while (frontIsClear()) move();
    }

    /**
     * This method builds a column using recursion.
     * Precondition: There is a wall that limits the height of the column.
     * Result: Column is built by putting "stones".
     */
    private void buildColumn() throws Exception {
        putStone();
        if (frontIsClear()) {
            move();
            buildColumn();
        }
    }

    /**
     * This method will put beeper if only there is no beeper yet.
     * Precondition: Is missing.
     * Result: There is a beeper at the cell Karel stands.
     */
    private void putStone() throws Exception {
        if (noBeepersPresent()) putBeeper();
    }

    /**
     * This method turn Karel to make sure he can start build the column in right direction.
     * Precondition: Is missing.
     * Result: Karel looks at the North.
     */
    private void lookAtTheNorth() throws Exception {
        while (!facingNorth()) turnLeft();
    }

    /**
     * This method will turn Karel back.
     * Precondition: Is missing.
     * Result: Karel will be rotated 180 degrees.
     */
    private void turnBack() throws Exception {
        turnLeft();
        turnLeft();
    }
}
