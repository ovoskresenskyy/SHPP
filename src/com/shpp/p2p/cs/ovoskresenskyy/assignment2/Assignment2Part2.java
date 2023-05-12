package com.shpp.p2p.cs.ovoskresenskyy.assignment2;

import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * Illusory contours.
 * <p>
 * The program creates a white rectangle that overlays four circles in the created window.
 * Window size changes do not affect figure sizes.
 */
public class Assignment2Part2 extends WindowProgram {

    /* The default width and height of the window.
     * These constants will tell Java to create a window whose size is *approximately* given by these dimensions.
     */
    public static final int APPLICATION_WIDTH = 800;
    public static final int APPLICATION_HEIGHT = 400;

    /* Diameter of circles.
     * This parameter also will be used for getting x-coordinate and y-coordinate of rectangle.
     */
    private double circleDiameter;

    @Override
    public void run() {
        /* Diameter will be at least three time smaller than the shortest side of the window.
         * This is necessary so that all the circles fit in the window, regardless of it's size.
         */
        circleDiameter = Math.min(getWidth(), getHeight()) / 3;

        drawCircles();
        drawRectangle();
    }

    /**
     * A simple 4 step nested loop to draw circles at the corners of a window.
     */
    private void drawCircles() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                double x = (getWidth() - circleDiameter) * j;
                double y = (getHeight() - circleDiameter) * i;
                drawCircle(x, y);
            }
        }
    }

    /**
     * The method draws filled circle according to the received parameters
     *
     * @param x - horizontal coordinate
     * @param y - vertical coordinate
     */
    private void drawCircle(double x, double y) {
        GOval circle = new GOval(x, y, circleDiameter, circleDiameter);
        circle.setFilled(true);
        circle.setFillColor(Color.BLACK);
        add(circle);
    }

    /**
     * The method draws white rectangle in the center of the window, that overlays circles.
     */
    private void drawRectangle() {
        /* The coordinate of the upper left corner of the rectangle. */
        double x = circleDiameter / 2;

        /* Corners of the rectangle will always be placed in the center of the circles. */
        double width = getWidth() - circleDiameter;
        double height = getHeight() - circleDiameter;

        GRect rectangle = new GRect(x, x, width, height);
        rectangle.setFilled(true);
        rectangle.setFillColor(Color.WHITE);
        rectangle.setColor(Color.WHITE);
        add(rectangle);
    }
}
