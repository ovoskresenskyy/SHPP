package com.shpp.p2p.cs.ovoskresenskyy.assignment3;

import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class SuperWindowProgram extends WindowProgram {

    /**
     * The method draws rectangle according to the received parameters.
     *
     * @param x      - The x coordinate of the upper-left corner of the rectangle.
     * @param y      - The y coordinate of the upper-left corner of the rectangle.
     * @param width  - Rectangle width.
     * @param height - Rectangle height.
     * @param color  - Rectangle color.
     */
    GRect drawFilledRectangle(double x, double y, double width, double height, Color color) {
        GRect rectangle = new GRect(x, y, width, height);
        rectangle.setFilled(true);
        rectangle.setFillColor(color);
        add(rectangle);
        return rectangle;
    }

    /**
     * The method draws oval according to the received parameters
     *
     * @param x      - The x coordinate of the upper-left corner of the bounding box of the oval.
     * @param y      - The y coordinate of the upper-left corner of the bounding box of the oval.
     * @param width  - Oval width.
     * @param height - Oval height.
     * @param color  - Oval color.
     */
    GOval drawFilledOval(double x, double y, double width, double height, Color color) {
        GOval oval = new GOval(x, y, width, height);
        oval.setFilled(true);
        oval.setFillColor(color);
        add(oval);
        return oval;
    }
}
