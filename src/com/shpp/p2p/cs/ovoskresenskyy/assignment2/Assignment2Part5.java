package com.shpp.p2p.cs.ovoskresenskyy.assignment2;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part5 extends WindowProgram {

    /* The number of rows and columns in the grid, respectively. */
    private static final int NUM_ROWS = 5;
    private static final int NUM_COLS = 6;

    /* The width and height of each box. */
    private static final double BOX_SIZE = 50;

    /* The horizontal and vertical spacing between the boxes. */
    private static final double BOX_SPACING = 10;

    @Override
    public void run() {

        double offset = BOX_SIZE + BOX_SPACING;
        double left = (getWidth() - offset * NUM_COLS) / 2;
        double top = (getHeight() - offset * NUM_ROWS) / 2;

        for (int i = 0; i < NUM_COLS; i++) {
            for (int j = 0; j < NUM_ROWS; j++) {
                drawBox(left + offset * i, top + offset * j);
            }
        }
    }

    private void drawBox(double x, double y) {
        GRect rectangle = new GRect(x, y, BOX_SIZE, BOX_SIZE);
        rectangle.setFilled(true);
        rectangle.setFillColor(Color.BLACK);
        add(rectangle);
    }
}
