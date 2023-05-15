package com.shpp.p2p.cs.ovoskresenskyy.assignment2;

import acm.graphics.GOval;

import java.awt.*;

/**
 * Caterpillars.
 * <p>
 * The program draws the most enchanting caterpillar in the world.
 */
public class Assignment2Part6 extends SuperWindowProgram {

    /* The default width and height of the window.
     * These constants will tell Java to create a window whose size is *approximately* given by these dimensions.
     */
    public static final int APPLICATION_WIDTH = 900;
    public static final int APPLICATION_HEIGHT = 300;

    /* The number of caterpillar "sections". */
    public static final int SECTION_NUMBER = 10;

    /* Each section of caterpillar is a circle with this diameter. */
    public static final double DIAMETER = 100;

    /* Constants controlling the relative positions of each caterpillar section. */
    public static final double X_OFFSET = 50;
    public static final double Y_OFFSET = 20;

    public void run() {
        /* The x coordinate of the upper-left corner of the bounding box for the first section. */
        double x = 0;

        for (int i = 0; i < SECTION_NUMBER; i++) {
            /* Each odd section will be shifted vertically. */
            double y = (i % 2 == 0) ? Y_OFFSET : 0;

            GOval circle = drawFilledOval(x, y, DIAMETER, DIAMETER, Color.GREEN);
            circle.setColor(Color.RED);

            /* Each section will be shifted horizontally. */
            x += X_OFFSET;
        }
    }
}
