package com.shpp.p2p.cs.ovoskresenskyy.assignment4;

import acm.graphics.GFillable;
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
     */
    static GRect createFilledRectangle(double x, double y, double width, double height, Color color) {
        GRect gRect = new GRect(x, y, width, height);
        fillObject(gRect, color);
        return gRect;
    }

    /**
     * The method draws oval according to the received parameters
     *
     * @param x      - The x coordinate of the upper-left corner of the bounding box of the oval.
     * @param y      - The y coordinate of the upper-left corner of the bounding box of the oval.
     * @param width  - Oval width.
     * @param height - Oval height.
     */
    static GOval createFilledOval(double x, double y, double width, double height, Color color) {
        GOval gOval = new GOval(x, y, width, height);
        fillObject(gOval, color);
        return gOval;
    }

    static void fillObject(GFillable object, Color color) {
        object.setFilled(true);
        object.setFillColor(color);
    }
}
