package com.shpp.p2p.cs.ovoskresenskyy.assignment2;

import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class RobotFace extends WindowProgram {

    public static double HEAD_WIDTH = 100;
    public static double HEAD_HEIGHT = 200;

    public static double EYE_RADIUS = 20;

    public static double MOUTH_WIDTH = 50;
    public static double MOUTH_HEIGHT = 30;

    public void run() {
        drawHead();
    }

    private void drawHead() {
        double appWidth = getWidth();
        double appHeight = getHeight();

        double x = (appWidth - HEAD_WIDTH) / 2;
        double y = (appHeight - HEAD_HEIGHT) / 2;

        drawRectangle(x, y, HEAD_WIDTH, HEAD_HEIGHT);

        drawEyes(x, y);
        drawMouth(x, y);
    }

    private void drawEyes(double headX, double headY) {

        double y = (headY + HEAD_HEIGHT / 4) - EYE_RADIUS;

        /* The first eye */
        double x = (headX + HEAD_WIDTH / 4) - EYE_RADIUS;
        drawCircle(x, y);

        /* The second eye */
        x = (headX + HEAD_WIDTH / 4 * 3) - EYE_RADIUS;
        drawCircle(x, y);

    }

    private void drawMouth(double headX, double headY) {

        double x = (getWidth() - MOUTH_WIDTH) / 2;
        double y = (headY + HEAD_HEIGHT / 4 * 3) - MOUTH_HEIGHT / 2;

        drawRectangle(x, y, MOUTH_WIDTH, MOUTH_HEIGHT);
    }

    private void drawRectangle(double x, double y, double width, double height) {
        GRect rectangle = new GRect(x, y, width, height);
        rectangle.setColor(Color.BLACK);
        add(rectangle);
    }

    private void drawCircle(double x, double y) {
        GOval oval = new GOval(x, y, EYE_RADIUS * 2, EYE_RADIUS * 2);
        oval.setColor(Color.RED);
        add(oval);
    }
}
