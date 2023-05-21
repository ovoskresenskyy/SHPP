package com.shpp.p2p.cs.ovoskresenskyy.assignment3;

import acm.graphics.GLabel;
import acm.graphics.GObject;

import java.awt.*;

/**
 * Five seconds of fame
 * <p>
 * Billiard
 * The program will draw the billiard table, cue and some billiard balls.
 * <p>
 * Then the picture comes to life.
 * We will hit the white ball with a cue and drive two another balls into different holes at the same time.
 * <p>
 * The program will have more than frames and will last 5 seconds +/-150ms
 */
public class Assignment3Part6 extends SuperWindowProgram {

    /* The default width and height of the window.
     * These constants will tell Java to create a window whose size is *approximately* given by these dimensions. */
    public static final int APPLICATION_WIDTH = 500;
    public static final int APPLICATION_HEIGHT = 800;

    /* The default width and height of the billiard table.
     * These constants will tell Java to create a billiard table with these dimensions. */
    private static final double TABLE_WIDTH = 300;
    private static final double TABLE_HEIGHT = 500;

    /* The default width of the billiard table board. */
    private static final double BOARD_WIDTH = 20;

    /* The default width and height of the billiard field.
     * These constants will tell Java to create a billiard field with these dimensions. */
    private static final double FIELD_WIDTH = TABLE_WIDTH - BOARD_WIDTH * 2;
    private static final double FIELD_HEIGHT = TABLE_HEIGHT - BOARD_WIDTH * 2;

    /* The default size of the holes on the billiard table. */
    private static final double HOLE_SIZE = 25;

    /* The default colors of the board and the field. */
    private static final Color TABLE_BROWN = new Color(150, 75, 0);
    private static final Color TABLE_GREEN = new Color(0, 100, 0);

    /* The default width and length of the billiard cue.
     * These constants will tell Java to create a billiard cue with these dimensions. */
    private static final double CUE_WIDTH = 5;
    private static final double CUE_LENGTH = 230;
    private static final Color CUE_BROWN = new Color(144, 130, 72);

    /* The default width and length of the billiard balls.
     * These constants will tell Java to create a billiard balls with these dimensions. */
    private static final double BALL_SIZE = 15;

    /* The amount of time to pause between frames (48fps). */
    private static final double PAUSE_TIME = 1000.0 / 48;

    /* The x and y coordinates of the billiard table. */
    private double tableX;
    private double tableY;

    /* The x and y coordinates of the billiard field. */
    private double fieldX;
    private double fieldY;

    /* The object of the billiard clue. */
    private GObject cue;

    /* The object of the clue ball. */
    private GObject cueBall;

    /* The objects of the red and blue balls. */
    private GObject redBall;
    private GObject blueBall;

    /* A variable that will record the start of the application.
     * Will be used to calculate the duration. */
    private long startTime;

    @Override
    public void run() {

        /* We start by determining the current time in milliseconds */
        this.startTime = System.currentTimeMillis();

        /* Let's create a label that will display the number and duration of frames in ms. */
        addTracker();

        this.tableX = (getWidth() - TABLE_WIDTH) / 2.0;
        this.tableY = (getHeight() - TABLE_HEIGHT) / 2.0;

        this.fieldX = tableX + BOARD_WIDTH;
        this.fieldY = tableY + BOARD_WIDTH;

        drawTable();

        this.cue = drawCue();
        this.cueBall = drawMainBall();
        this.redBall = drawRedBall();
        this.blueBall = drawYellowBall();

        moveCue();

        System.out.println("frameCounter = " + frameCounter);
        showInfo();
    }

    private GLabel tracker;

    private void addTracker() {
        tracker = new GLabel("text", 30, 30);
        add(tracker);
    }

    private int frameCounter;

    private void showInfo() {
        tracker.setLabel("Frames: " + frameCounter + ", " + (System.currentTimeMillis() - startTime) + " ms spent.");
    }

    private void moveCue() {
        pullBackCue();
        pause(PAUSE_TIME * 20);
        hitTheBall();
    }

    private void hitTheBall() {
        double dy = 0;
        double speed = 1.75;
        double endPoint = cueBall.getY() + BALL_SIZE;

        while (cue.getY() > endPoint) {
            cue.move(0, dy);
            dy -= speed;
            pause(PAUSE_TIME);
            frameCounter++;

            if (cue.getY() <= endPoint) moveMainBall();

            showInfo();
        }
    }

    private void moveMainBall() {
        double dy = 0;
        double speed = 1.1;

        double endPoint = fieldY + FIELD_HEIGHT / 3 + BALL_SIZE / 2;

        while (cueBall.getY() > endPoint) {
            cueBall.move(0, dy);
            dy -= speed;
            pause(PAUSE_TIME);
            frameCounter++;

            if (cueBall.getY() <= endPoint) moveBalls();

            showInfo();
        }
    }

    private void moveBalls() {
        double dy = 0;
        double redDeltaX = 0;
        double yellowDeltaX = 0;
        double speed = 1.1;

        while (redBall.getY() > fieldY && blueBall.getY() > fieldY) {
            redBall.move(redDeltaX--, dy);
            blueBall.move(yellowDeltaX++, dy);
            dy -= speed;
            pause(PAUSE_TIME);
            frameCounter++;

            showInfo();
        }

        remove(redBall);
        remove(blueBall);
    }

    private void pullBackCue() {
        double dy = 0;
        double speed = 0.3;
        double startY = cue.getY();

        while (cue.getY() < startY + cue.getHeight() / 6) {
            /* Move the ball by the current velocity. */
            cue.move(0, dy);

            dy += speed;

            pause(PAUSE_TIME);
            frameCounter++;

            showInfo();
        }
    }

    private GObject drawYellowBall() {
        double redX = fieldX + FIELD_WIDTH / 2 - BALL_SIZE;
        double redY = fieldY + FIELD_HEIGHT / 3 - BALL_SIZE / 2;
        return drawFilledOval(redX, redY, BALL_SIZE, BALL_SIZE, Color.RED);
    }

    private GObject drawRedBall() {
        double yellowX = fieldX + FIELD_WIDTH / 2;
        double yellowY = fieldY + FIELD_HEIGHT / 3 - BALL_SIZE / 2;
        return drawFilledOval(yellowX, yellowY, BALL_SIZE, BALL_SIZE, Color.YELLOW);
    }

    private GObject drawMainBall() {
        double x = fieldX + FIELD_WIDTH / 2 - BALL_SIZE / 2;
        double y = fieldY + FIELD_HEIGHT * 2 / 3 - BALL_SIZE / 2;
        return drawFilledOval(x, y, BALL_SIZE, BALL_SIZE, Color.WHITE);
    }

    private GObject drawCue() {

        double x = fieldX + FIELD_WIDTH / 2 - CUE_WIDTH / 2;
        double y = fieldY + FIELD_HEIGHT * 2 / 3 + BALL_SIZE + 10;

        return drawFilledRectangle(x, y, CUE_WIDTH, CUE_LENGTH, CUE_BROWN);
    }

    private void drawTable() {
        double speed = 8;
        double currentTableHeight = 1;

        while (currentTableHeight <= TABLE_HEIGHT) {
            drawFilledRectangle(tableX, tableY, TABLE_WIDTH, currentTableHeight, TABLE_BROWN);
            currentTableHeight += speed;

            pause(PAUSE_TIME);
            frameCounter++;

            showInfo();
        }

        double currentFieldHeight = 1;

        while (currentFieldHeight <= FIELD_HEIGHT) {
            drawFilledRectangle(fieldX, fieldY, FIELD_WIDTH, currentFieldHeight, TABLE_GREEN);
            currentFieldHeight += speed;

            pause(PAUSE_TIME);
            frameCounter++;

            showInfo();
        }

        double currentDiameter = 1;
        while (currentDiameter <= HOLE_SIZE) {
            drawHoles(fieldX, fieldY, currentDiameter);
            currentDiameter += 1;

            pause(PAUSE_TIME);
            frameCounter++;

            showInfo();
        }

    }

    private void drawHoles(double x, double y, double diameter) {
        double fieldVerticalCenter = y + FIELD_HEIGHT / 2;
        double r = diameter / 2;

        for (int i = 0; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                double holeX = x + FIELD_WIDTH * i - r;
                double holeY = fieldVerticalCenter + (FIELD_HEIGHT / 2 * j) - r;

                drawFilledOval(holeX, holeY, diameter, diameter, Color.BLACK);

                showInfo();
            }
        }
    }


}
