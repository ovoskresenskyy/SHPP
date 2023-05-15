package com.shpp.p2p.cs.ovoskresenskyy.assignment2;

import acm.graphics.GRect;

import java.awt.*;

/**
 * Illusory contours.
 * <p>
 * The program creates a white rectangle that overlays four circles in the created window.
 * Window size changes do not affect figure sizes.
 */
public class Assignment2Part2 extends SuperWindowProgram {

    /* The default width and height of the window.
     * These constants will tell Java to create a window whose size is *approximately* given by these dimensions.
     */
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 400;

    /* Diameter of circles.
     * This parameter also will be used for getting x and y coordinates of the rectangle.
     */
    private double circleDiameter;

    @Override
    public void run() {
        /* Diameter will be at least three time smaller than the shortest side of the window.
         * This is necessary so that all the circles fit in the window, regardless of it's size.
         *
         * Cast width to double to make it more clear.
         */
        circleDiameter = Math.min((double) getWidth(), getHeight()) / 3;
        drawCirclesOnTheCorners();

        coverCirclesWithRectangle();
    }

    /**
     * A simple nested loop to draw circles at the corners of a window.
     */
    private void drawCirclesOnTheCorners() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                drawFilledOval(
                        (getWidth() - circleDiameter) * j,
                        (getHeight() - circleDiameter) * i,
                        circleDiameter,
                        circleDiameter,
                        Color.BLACK);
            }
        }
    }

    /**
     * The method draws white rectangle that placed at the center of a window,
     * and makes our illusion real by covering the circles.
     */
    private void coverCirclesWithRectangle() {
        /* The x coordinate of the upper left corner of the rectangle. */
        double x = circleDiameter / 2;
        /* Corners of the rectangle will always be placed in the center of the circles. */
        double width = getWidth() - circleDiameter;
        double height = getHeight() - circleDiameter;

        GRect rectangle = drawFilledRectangle(x, x, width, height, Color.WHITE);
        rectangle.setColor(Color.WHITE);
    }
}
