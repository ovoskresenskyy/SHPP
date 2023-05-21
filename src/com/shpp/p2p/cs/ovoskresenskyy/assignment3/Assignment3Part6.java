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


    /* The default width and height of the billiard table's base.
     * These constants will tell Java to create a billiard table's base with these dimensions. */
    private static final double BASE_WIDTH = 300;
    private static final double BASE_HEIGHT = 500;

    /* The default width of the billiard table board. */
    private static final double BOARD_WIDTH = 20;

    /* The default width and height of the billiard field.
     * These constants will tell Java to create a billiard field with these dimensions. */
    private static final double FIELD_WIDTH = BASE_WIDTH - BOARD_WIDTH * 2;
    private static final double FIELD_HEIGHT = BASE_HEIGHT - BOARD_WIDTH * 2;

    /* The default colors of the base and the field. */
    private static final Color BASE_BROWN = new Color(150, 75, 0);
    private static final Color FIELD_GREEN = new Color(0, 100, 0);


    /* The default size of the holes on the billiard table. */
    private static final double HOLE_SIZE = 25;


    /* The default width and length of the billiard cue.
     * These constants will tell Java to create a billiard cue with these dimensions. */
    private static final double CUE_WIDTH = 5;
    private static final double CUE_LENGTH = 230;

    /* The default colors of the billiard cue. */
    private static final Color CUE_BROWN = new Color(144, 130, 72);


    /* The default sizes of the billiard balls. */
    private static final double BALL_SIZE = 15;

    /* The default distance between the cue and cue ball. */
    private static final double BALL_DISTANCE = 10;


    /* The amount of time to pause between frames (48fps). */
    private static final double PAUSE_TIME = 1000.0 / 48;


    /* Constants for controlling the speed of objects */
    private static final double DRAWING_TABLE_SPEED = 8;
    private static final double DRAWING_HOLES_SPEED = 1;
    private static final double CUE_PULLING_SPEED = 0.3;
    private static final double CUE_HITTING_SPEED = 1.6;
    private static final double CUE_BALL_SPEED = 0.45;
    private static final double BALLS_SPEED = 1.25;


    /* The x and y coordinates of the base of the billiard table. */
    private double baseX;
    private double baseY;

    /* The x and y coordinates of the field of the billiard table. */
    private double fieldX;
    private double fieldY;

    /* The y coordinate of the middle of the billiard field.
     * Used to draw the holes in relation to the middle. */
    private double fieldMiddleY;

    /* The object of the billiard clue. */
    private GObject cue;

    /* The object of the clue ball. */
    private GObject cueBall;

    /* The objects of the left and right balls. */
    private GObject leftBall;
    private GObject rightBall;

    /* A variable that will record the start of the application.
     * Will be used to calculate the duration. */
    private long startTime;
    private long timeSpent;

    /* The objects of our label which shows duration and count of frames done. */
    private GLabel tracker;

    /* Variable that will count the number of frames. */
    private int frameCounter;

    @Override
    public void run() {
        /* We start by determining the current time in milliseconds */
        this.startTime = System.currentTimeMillis();

        /* Let's create a label that will display the number and duration of frames in ms. */
        this.tracker = new GLabel("", 30, 30);
        add(tracker);

        /* Let's draw all the elements of billiards. */
        drawBilliardElements();

        /* Start the imitation of the motion of putting balls into the holes. */
        startGame();

        updateTracker();
    }

    /**
     * The method draws all elements of billiard:
     * - table
     * - cue
     * - balls
     */
    private void drawBilliardElements() {
        /* Let's draw a table according to constants. */
        drawBilliardTable();

        /* Let's draw a cue according to constants. */
        drawCue();

        /* Let's draw all balls according to constants. */
        drawBalls();
    }

    /**
     * The method draws billiard table which consists of a base and a field.
     */
    private void drawBilliardTable() {

        /* Let's calculate coordinates of base and field according to constants and real window size. */
        calculateTableCoordinates();

        /* Let's draw the base of the billiard table. */
        drawTablePart(baseX, baseY, BASE_WIDTH, BASE_HEIGHT, BASE_BROWN);

        /* Let's draw the field of the billiard table. */
        drawTablePart(fieldX, fieldY, FIELD_WIDTH, FIELD_HEIGHT, FIELD_GREEN);

        /* Let's draw the holes on the drawn billiard table. */
        drawHoles();
    }

    /**
     * The methods calculate x and y coordinates of the elements of the billiard table.
     */
    private void calculateTableCoordinates() {
        /* The x and y coordinates of the base to center it in the window. */
        this.baseX = (getWidth() - BASE_WIDTH) / 2.0;
        this.baseY = (getHeight() - BASE_HEIGHT) / 2.0;

        /* The x and y coordinates of the field to place it inside the base. */
        this.fieldX = baseX + BOARD_WIDTH;
        this.fieldY = baseY + BOARD_WIDTH;

        this.fieldMiddleY = fieldY + FIELD_HEIGHT / 2;
    }

    /**
     * The method draws the parts of the billiard table (the base and the field),
     * according to the received parameters.
     * <p>
     * Height will gradually increase to imitate animation.
     *
     * @param x      - The x coordinate of the part
     * @param y      - The y coordinate of the part
     * @param width  - Width of the part being drawn
     * @param height - Max height of the part being drawn
     * @param color  - Color of the part
     */
    private void drawTablePart(double x, double y, double width, double height, Color color) {
        double currentHeight = 1;

        while (currentHeight < height) {
            drawFilledRectangle(x, y, width, currentHeight, color);

            currentHeight += DRAWING_TABLE_SPEED;

            updateTracker();
        }
    }

    /**
     * The method draws the holes on the billiard table.
     * <p>
     * Diameter will gradually increase to imitate animation.
     */
    private void drawHoles() {
        double currentDiameter = 1;

        while (currentDiameter < HOLE_SIZE) {
            redrawHoles(currentDiameter);

            currentDiameter += DRAWING_HOLES_SPEED;

            updateTracker();
        }
    }

    /**
     * The method draws holes at the four corners of the table
     * according to the constants and the received diameter.
     *
     * @param diameter - Current diameter of the hole.
     */
    private void redrawHoles(double diameter) {
        for (int i = 0; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                /* The x and y coordinates of the bounding box for the hole. */
                double x = fieldX + FIELD_WIDTH * i - diameter / 2;
                double y = fieldMiddleY + (FIELD_HEIGHT / 2 * j) - diameter / 2;

                drawFilledOval(x, y, diameter, diameter, Color.BLACK);
            }
        }
    }

    /**
     * The method draws the billiard cue according to the constants.
     */
    private void drawCue() {
        /* The cue is placed horizontally in the center of the table. */
        double x = fieldX + FIELD_WIDTH / 2 - CUE_WIDTH / 2;
        /* The coordinate is placed below the white ball at a distance. */
        double y = fieldY + FIELD_HEIGHT * 2 / 3 + BALL_SIZE + BALL_DISTANCE;

        this.cue = drawFilledRectangle(x, y, CUE_WIDTH, CUE_LENGTH, CUE_BROWN);
    }

    /**
     * The method draws the billiard balls.
     */
    private void drawBalls() {
        /* The x coordinate of the middle of the billiard field. */
        double fieldMiddleX = fieldX + FIELD_WIDTH / 2;

        double fieldThirdPart = FIELD_HEIGHT / 3;
        double ballRadius = BALL_SIZE / 2;

        /* The main ball which will hit another two balls.
         * It is placed in the center horizontally and lowered 2/3 vertically relative to the field */
        this.cueBall = drawFilledOval(
                fieldMiddleX - ballRadius,
                fieldY + fieldThirdPart * 2 - ballRadius,
                BALL_SIZE, BALL_SIZE, Color.WHITE);

        /* Two billiard balls that we will launch into the holes. */
        this.rightBall = drawFilledOval(
                fieldMiddleX - BALL_SIZE,
                fieldY + fieldThirdPart - ballRadius,
                BALL_SIZE, BALL_SIZE, Color.BLUE);

        this.leftBall = drawFilledOval(
                fieldMiddleX,
                fieldY + fieldThirdPart - ballRadius,
                BALL_SIZE, BALL_SIZE, Color.CYAN);
    }

    /**
     * The method imitates a cue hit on a ball.
     * First, the cue is pulled back, then it hits the cue ball.
     */
    private void startGame() {
        pullBackCue();
        hitTheCueBall();
    }

    /**
     * The method imitates pulling the cue before hitting
     * <p>
     * Cue will be pulled back by 1 / 6 of its length.
     */
    private void pullBackCue() {
        /* Tracks the downward speed of the cue. */
        double dy = 0;
        /* The y coordinate of starting position of the cue. */
        double startY = cue.getY();

        while (cue.getY() < startY + cue.getHeight() / 6) {
            cue.move(0, dy);

            dy += CUE_PULLING_SPEED;

            updateTracker();
        }

        /* Hold the cue to simulate aiming. */
        pause(PAUSE_TIME * 10);
    }

    /**
     * The method imitates hitting the cue ball
     * <p>
     * The cue will push until the cue ball hits.
     */
    private void hitTheCueBall() {
        /* Tracks the upward speed of the cue. */
        double dy = 0;
        double cueBallLocation = cueBall.getY() + BALL_SIZE;

        while (cue.getY() > cueBallLocation) {
            cue.move(0, dy);

            dy -= CUE_HITTING_SPEED;

            updateTracker();

            /* When cue reaches the cue ball, the cue ball begins its movement. */
            if (cue.getY() <= cueBallLocation) {
                moveCueBall();
                break;
            }
        }
    }

    /**
     * The method imitates movement of the cue ball after the cue hits.
     * <p>
     * The cue ball will move until it reaches the other two balls.
     */
    private void moveCueBall() {
        /* Tracks the upward speed of the cue ball. */
        double dy = 0;
        /* The y coordinate of the another two balls location. */
        double ballsLocation = fieldY + FIELD_HEIGHT / 3 + BALL_SIZE;

        while (cueBall.getY() > ballsLocation) {
            cueBall.move(0, dy);

            dy -= CUE_BALL_SPEED;

            updateTracker();

            /* When cue balls reaches other two balls, they begin their movements. */
            if (cueBall.getY() <= ballsLocation) {
                moveBalls();
                break;
            }
        }
    }

    /**
     * The method imitates movement of the balls after the cue ball hits.
     * <p>
     * The balls will move until they reach the holes.
     */
    private void moveBalls() {
        /* Tracks the upward speed of the balls. */
        double dy = 0;
        /* Tracks shifting of the balls horizontally towards the holes. */
        double leftBallDx = 0;
        double rightBallDx = 0;

        while (leftBall.getY() > fieldY && rightBall.getY() > fieldY) {
            leftBall.move(leftBallDx++, dy);
            rightBall.move(rightBallDx--, dy);

            dy -= BALLS_SPEED;

            updateTracker();
        }

        /* When the balls reach the holes, they are removed to simulate a hit. */
        remove(leftBall);
        remove(rightBall);
    }

    /**
     * The method counts the number of frames and the time spent on drawing them.
     */
    private void updateTracker() {
        frameCounter++;
        pause(PAUSE_TIME);
        tracker.setLabel("Frames: " + frameCounter + ", " + (System.currentTimeMillis() - startTime) + " ms spent.");
    }
}
