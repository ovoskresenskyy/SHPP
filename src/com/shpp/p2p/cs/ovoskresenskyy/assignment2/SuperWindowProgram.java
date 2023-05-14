package com.shpp.p2p.cs.ovoskresenskyy.assignment2;

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
    GRect drawRectangle(double x, double y, double width, double height) {
        GRect rectangle = new GRect(x, y, width, height);
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
     */
    GOval drawOval(double x, double y, double width, double height) {
        GOval oval = new GOval(x, y, width, height);
        add(oval);
        return oval;
    }

    /**
     * The method fill received object with received color.
     *
     * @param object - Any objects that implements GFillable.
     */
    void fillObject(GFillable object, Color color) {
        object.setFilled(true);
        object.setFillColor(color);
    }

}
