package com.shpp.p2p.cs.ovoskresenskyy.assignment2;

import java.awt.*;

/**
 * Another optical illusion.
 * <p>
 * The program draw a matrix of black boxes separated by "streets".
 * Drawn boxes are centered in the window.
 */
public class Assignment2Part5 extends SuperWindowProgram {

    /* The number of rows and columns in the grid, respectively. */
    private static final int NUM_ROWS = 5;
    private static final int NUM_COLS = 7;

    /* The width and height of each box. */
    private static final double BOX_SIZE = 50;

    /* The horizontal and vertical spacing between the boxes. */
    private static final double BOX_SPACING = 10;

    @Override
    public void run() {

        /* The position of each box relative to the upper-left corner of the previous one. */
        double offset = BOX_SIZE + BOX_SPACING;
        /* The x coordinate of the upper-left corner of the first box. */
        double x = (getWidth() - offset * NUM_COLS) / 2;
        /* The y coordinate of the upper-left corner of the first box. */
        double y = (getHeight() - offset * NUM_ROWS) / 2;

        for (int i = 0; i < NUM_COLS; i++) {
            for (int j = 0; j < NUM_ROWS; j++) {
                drawFilledRectangle(x + offset * i, y + offset * j, BOX_SIZE, BOX_SIZE, Color.BLACK);
            }
        }
    }
}
