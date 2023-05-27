package com.shpp.p2p.cs.ovoskresenskyy.assignment3;

import acm.graphics.GRect;

import java.awt.*;

/**
 * Pyramid
 * <p>
 * The program builds pyramids from bricks.
 */
public class Assignment3Part4 extends SuperWindowProgram {

    /* Constants controlling the size of the bricks */
    public static final double BRICK_HEIGHT = 20;
    public static final double BRICK_WIDTH = 50;

    /* Colors of bricks */
    public static final Color BRICK_COLOR = Color.BLACK;
    public static final Color BRICK_BORDER_COLOR = Color.WHITE;

    /* Amount of bricks which will be placed at the pyramids base.
     * At each next level of the pyramid there will be one brick less.  */
    public static final int BRICKS_IN_BASE = 10;

    @Override
    public void run() {
        /* The x coordinate of the first brick, to place the pyramid in the center horizontally. */
        double x = (getWidth() - (BRICK_WIDTH * BRICKS_IN_BASE)) / 2;
        /* The y coordinate of the first brick, to place the pyramid on the bottom side of the window. */
        double y = getHeight() - BRICK_HEIGHT;

        buildPyramid(x, y, BRICKS_IN_BASE);
    }

    /**
     * Recursive method which builds the pyramid from base row to the last one
     * decreasing the number of bricks in the row by one for each method call.
     *
     * @param x            - The x coordinate of the first brick in the row.
     * @param y            - The y coordinate of the first brick in the row.
     * @param bricksAmount - Amount of the brick in the row.
     */
    private void buildPyramid(double x, double y, int bricksAmount) {
        /* We will stop the pyramids building when bricks amount becomes 0. */
        if (bricksAmount == 0) return;

        /* The x coordinate of the bricks in the row. Will be shifted horizontally to the right. */
        double bricksX = x;

        for (int i = 0; i < bricksAmount; i++) {
            GRect gRect = drawFilledRectangle(bricksX, y, BRICK_WIDTH, BRICK_HEIGHT, BRICK_COLOR);
            gRect.setColor(BRICK_BORDER_COLOR);

            /* Horizontal bricks shifting in the current row. */
            bricksX += BRICK_WIDTH;
        }

        /* Te get pyramid visibility:
         *  - the x coordinate will be shifted to the right on a half of the brick's width
         *  - the y coordinate will be shifted up by the height of the brick
         *  - the number of bricks will decrease by one at each level of the pyramid. */
        buildPyramid(x + BRICK_WIDTH / 2, y - BRICK_HEIGHT, --bricksAmount);
    }
}
