package com.shpp.p2p.cs.ovoskresenskyy.assignment2;

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part6 extends WindowProgram {

    public static final int APPLICATION_WIDTH = 900;
    public static final int APPLICATION_HEIGHT = 300;

    public static final int COUNT = 10;
    public static final double DIAMETER = 100;
    public static final double X_OFFSET = 10;
    public static final double Y_OFFSET = -30;

    public void run() {

        double x = 0;
        double radius = DIAMETER / 2;

        for (int i = 0; i < COUNT; i++) {
            double y = (i % 2 == 0) ? radius + Y_OFFSET: 0;
            drawCircle(x, y);

            x += (radius + X_OFFSET);
        }
    }


    private void drawCircle(double x, double y) {

        GOval oval = new GOval(x, y, DIAMETER, DIAMETER);
        oval.setFilled(true);
        oval.setFillColor(Color.GREEN);
        oval.setColor(Color.RED);
        add(oval);

    }
}
