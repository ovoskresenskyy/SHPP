package com.shpp.p2p.cs.ovoskresenskyy.assignment1;

import com.shpp.karel.KarelTheRobot;

/**
 * This program helps Karel to find the center of the South's row and mark it with beeper.
 * For rows with an even number of cells, the center can be either of the two center cells.
 */
public class Assignment1Part3 extends KarelTheRobot {

    /**
     * Precondition: Karel stands at the South-West corner and looks at the East.
     * This is the row we have to find the center.
     * Result: Center of the row is marked by one beeper. There are no more beepers at the field.
     */
    public void run() throws Exception {
        findAndMarkTheRowCenter();
    }

    /**
     * This method first will mark edges with beepers and then will move them until it finds the center.
     */
    private void findAndMarkTheRowCenter() throws Exception {
        setEdgesOfTheRow();
        moveEdgeBeepersToTheCenter();
    }

    /**
     * Precondition: Karel stands at the start of the row.
     * Result: Start and end of the row marked with beepers.
     */
    private void setEdgesOfTheRow() throws Exception {
        putBeeper();
        while (frontIsClear()) move();
        putBeeper();
    }

    /**
     * This method will move beepers to the center of the row
     * Precondition: Edges of the row is already marked with beepers, Karel stands with his back to the row,
     * Karel stands on the cell with beeper on the "floor".
     * Result: Center of the row is found and marked.
     */
    private void moveEdgeBeepersToTheCenter() throws Exception {
        turnBack();
        /*
         * If we find the center of the row, the recursion stops.
         */
        if (moveBeeperAndCheckIsCenterFound()) return;

        /*
         * After we move one beeper to the center we have to find another one and move it.
         */
        moveToAnotherBeeper();

        /*
        Repeat until the center is found.
         */
        moveEdgeBeepersToTheCenter();
    }

    /**
     * This method will move beeper one cell forward
     * Return: true if center is found
     * Precondition: Karel stands at the cell with the beeper.
     * Result: Beeper is moved one cell forward or center is found.
     */
    private boolean moveBeeperAndCheckIsCenterFound() throws Exception {
        pickBeeper();
        /*
         * Only for the case when the field is one cell wide we have to check it.
         */
        if (frontIsBlocked()) return true;
        move();

        /*
         * If there is beeper, that's mean that beepers ar met in the center of the row.
         */
        if (beepersPresent()) return true;

        putBeeper();

        /*
         * Center isn't found, so keep moving beepers.
         */
        return false;
    }

    /**
     *
     */
    private void moveToAnotherBeeper() throws Exception {
        do move();
        while (noBeepersPresent());
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
