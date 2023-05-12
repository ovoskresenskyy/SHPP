package com.shpp.p2p.cs.ovoskresenskyy.assignment2;

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * Caterpillars.
 * <p>
 * The program draws the most enchanting caterpillar in the world.
 */
public class Assignment2Part6 extends WindowProgram {

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
            /* Only odd sections will be shifted vertically. */
            double y = (i % 2 == 0) ? Y_OFFSET : 0;

            drawCircle(x, y);

            /* Each section will be shifted horizontally. */
            x += X_OFFSET;
        }
    }

    /**
     * The method draws filled circle according to the received parameters
     *
     * @param x - The x coordinate of the upper-left corner of the bounding box of circle.
     * @param y - The y coordinate of the upper-left corner of the bounding box of circle.
     */
    private void drawCircle(double x, double y) {
        GOval oval = new GOval(x, y, DIAMETER, DIAMETER);
        oval.setFilled(true);
        oval.setFillColor(Color.GREEN);
        oval.setColor(Color.RED);
        add(oval);
    }
}
