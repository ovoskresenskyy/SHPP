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
     * There are no beepers and walls on the field.
     * Result: Center of the South's row is marked by one beeper.
     * There are no more beepers on the field.
     */
    public void run() throws Exception {
        findAndMarkTheRowCenter();
    }

    /**
     * First, Karel will mark edges of the row with 2 beepers,
     * then will move them towards each other until he finds the center.
     */
    private void findAndMarkTheRowCenter() throws Exception {
        setEdgesOfTheRow();
        moveEdgesTowardsEachOther();
    }

    /**
     * Precondition: Karel stands at the start of the row, looking towards the end of the row.
     * Result: Start and end of the row marked with beepers.
     */
    private void setEdgesOfTheRow() throws Exception {
        putBeeper();
        while (frontIsClear()) move();
        putBeeper();
    }

    /**
     * This method, using recursion, will move beepers towards each other until found the center of the row.
     * Precondition: Edges of the row is already marked with beepers, Karel stands with his back to the row,
     * Karel stands on the cell with beeper on the "floor".
     * Result: Center of the row is found and marked.
     */
    private void moveEdgesTowardsEachOther() throws Exception {
        turnAround();
        pickBeeper();
        /*
         * We have to check if the front is clear for case of the world with one cell wide.
         */
        if (frontIsClear()) move();
        /*
         * If beeper is present, that means that center is found.
         */
        if (noBeepersPresent()) {
            putBeeper();
            moveToAnotherEdge();
            /*
             * Keep repeating this action until center is found.
             */
            moveEdgesTowardsEachOther();
        }
    }

    /**
     * This method will move Karel forward till he reaches another "edge-beeper".
     * Precondition: Karel stands at the cell with placed beeper and looks towards the second beeper.
     * Result: Karel reaches the second beeper without changing direction.
     */
    private void moveToAnotherEdge() throws Exception {
        do move();
        while (noBeepersPresent());
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
