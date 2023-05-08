package com.shpp.p2p.cs.ovoskresenskyy.assignment2;

import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part2 extends WindowProgram {

    public static final int APPLICATION_WIDTH = 800;
    public static final int APPLICATION_HEIGHT = 500;
    private double diameter;

    @Override
    public void run() {
        //noinspection IntegerDivisionInFloatingPointContext
        this.diameter = Math.min(getWidth(), getHeight()) / 3;

        drawCircles();
        drawRectangle();
    }

    private void drawCircles() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                drawCircle(
                        (getWidth() - diameter) * j,
                        (getHeight() - diameter) * i);
            }
        }
    }

    private void drawCircle(double x, double y) {
        GOval circle = new GOval(x, y, diameter, diameter);
        circle.setFilled(true);
        circle.setFillColor(Color.BLACK);
        add(circle);
    }

    private void drawRectangle() {
        GRect rectangle = new GRect(
                diameter / 2,
                diameter / 2,
                getWidth() - diameter,
                getHeight() - diameter);
        rectangle.setFilled(true);
        rectangle.setFillColor(Color.WHITE);
        rectangle.setColor(Color.WHITE);
        add(rectangle);
    }
}
