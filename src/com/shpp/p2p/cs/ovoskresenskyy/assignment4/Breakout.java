package com.shpp.p2p.cs.ovoskresenskyy.assignment4;

import acm.graphics.*;
import acm.util.RandomGenerator;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Breakout
 * <p>
 * The program implements the classic game "Breakout".
 */
public class Breakout extends SuperWindowProgram {

    /* Width and height of application window in pixels */
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 600;

    /**
     * Paddle
     */
    /* Dimensions of the paddle */
    public static final int PADDLE_WIDTH = 60;
    public static final int PADDLE_HEIGHT = 20;
    /* Color of the paddle */
    public static final Color PADDLE_COLOR = Color.BLACK;
    /* Offset of the paddle up from the bottom */
    public static final int PADDLE_Y_OFFSET = 30;

    /**
     * Bricks
     */
    /* Height of a brick */
    public static final int BRICK_HEIGHT = 8;
    /* Separation between bricks */
    public static final double BRICK_SEP = 4;
    /* Offset of the top brick row from the top */
    public static final int BRICK_Y_OFFSET = 70;

    /* Colors of the bricks */
    public static final Color[] BRICKS_COLORS = new Color[]{
            Color.RED,
            Color.ORANGE,
            Color.YELLOW,
            Color.GREEN,
            Color.CYAN
    };

    /* Number of rows of bricks */
    public static final int NBRICK_ROWS = 10;
    /* Number of bricks per row */
    public static final int NBRICKS_PER_ROW = 10;

    /**
     * Ball
     */
    /* Diameter of the ball in pixels */
    public static final double BALL_SIZE = 20;
    /* Radius of the ball in pixels */
    public static final double BALL_RADIUS = BALL_SIZE / 2.0;
    /* Color of the ball */
    public static final Color BALL_COLOR = Color.BLACK;

    /* Offset of the touch-points from the ball.
     * Distance between touch-points and the ball. */
    public static final double CIRCLE_POINT_OFFSET = 0.1;

    /* Degrees between touch-points around the ball */
    public static final int DEGREES_BETWEEN_CIRCLE_POINT = 45;
    /* Complete circle around the ball in degrees */
    public static final int FULL_CIRCLE_ROTATION_DEGREES = 360;

    /* Default y velocity of the ball */
    public static final int BALL_Y_VELOCITY = 3;
    /* The limits of the x velocity of the ball */
    public static final int MIN_X_VELOCITY = 2;
    public static final int MAX_X_VELOCITY = 5;

    /**
     * General
     */
    /* Number of turns */
    public static final int NTURNS = 3;
    /* The amount of time to pause between frames (60 fps). */
    public static final double PAUSE_TIME = 1000.0 / 48;

    /* The link to the paddle */
    private GRect paddle;
    /* The link to the ball */
    private GOval ball;

    /* The ball horizontal displacement tracker. Dx - is short for Delta-x */
    private double ballDx;
    /* The ball vertical displacement tracker. Dy - is short for Delta-y */
    private double ballDy;

    /* The number of bricks left in the game */
    private int bricksCounter;

    public void run() {
        createObjects();

        addMouseListeners();

        int attemptsNumber = startGame();

        printFinalMessage(attemptsNumber);
    }

    /**
     * The method creates the paddle and the wall of the bricks.
     */
    private void createObjects() {
        createPaddle();
        createBricks();
    }

    /**
     * The method creates the paddle and adds it on the window.
     * <p>
     * The paddle placed in the center of the window horizontally
     * And with offset from the bottom of the window vertically.
     */
    private void createPaddle() {
        double x = (getWidth() - PADDLE_WIDTH) / 2.0;
        double y = getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT;
        paddle = createFilledRectangle(x, y, PADDLE_WIDTH, PADDLE_HEIGHT, PADDLE_COLOR);
        add(paddle);
    }

    /**
     * The method creates the wall of the bricks
     * <p>
     * The first row starts with the offset from the top border od the window.
     */
    private void createBricks() {
        /* The width of the bricks will be calculated according to the windows size. */
        double brickWidth = getBrickWidth();

        /* The y coordinate of the first row. */
        double y = BRICK_Y_OFFSET;

        for (int rowNumber = 0; rowNumber < NBRICK_ROWS; rowNumber++) {
            /* For each row will get own color according to the rules. */
            Color rowColor = getRowColor(rowNumber);

            drawBrickRow(y, brickWidth, rowColor);

            /* The y coordinate will be shifted for each row. */
            y += BRICK_HEIGHT + BRICK_SEP;
        }
    }

    /**
     * The method calculates the width of the bricks to place them in the center of the screen.
     * <p>
     * The calculation also takes into account the distance between the bricks.
     */
    private double getBrickWidth() {
        return (getWidth() - BRICK_SEP * (NBRICKS_PER_ROW - 1)) / NBRICKS_PER_ROW;
    }

    /**
     * The method draws the row of the bricks.
     * This method also calculates the total amount of the brick on the screen.
     *
     * @param y          - The y coordinate of each brick in the row
     * @param brickWidth - Brick width calculated earlier
     * @param color      - Color of each brick in the row
     */
    private void drawBrickRow(double y, double brickWidth, Color color) {
        for (int brickNumber = 0; brickNumber < NBRICKS_PER_ROW; brickNumber++) {
            double x = brickNumber * (brickWidth + BRICK_SEP);
            GRect brick = createFilledRectangle(x, y, brickWidth, BRICK_HEIGHT, color);
            add(brick);

            /* Number of the created bricks. */
            bricksCounter++;
        }
    }

    /**
     * The method get the number from the presented list of the colors
     * <p>
     * If there are less than 5 colors in the list, will get the random color.
     *
     * @param rowNumber - Need to get a new color for each second row
     * @return - Color of the bricks in the presented row.
     */
    private Color getRowColor(int rowNumber) {
        Color color;
        try {
            /* According to this formula will get the same color for each two rows. */
            color = BRICKS_COLORS[rowNumber % 10 / 2];
        } catch (IndexOutOfBoundsException e) {
            /* If there are less than 5 color in the presented list. */
            color = RandomGenerator.getInstance().nextColor();
        }
        return color;
    }

    /**
     * The method tracks the mouse movement
     * and moves the paddle following the mouse pointer.
     *
     * @param mouseEvent the event to be processed
     */
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        movePaddle(mouseEvent.getX());
    }

    /**
     * The method moves the paddle following the mouse pointer.
     * The paddle cannot move outside the window horizontally.
     * The paddle always moves with the same y coordinate.
     *
     * @param mouseX - The current x coordinate of the mouse pointer.
     */
    private void movePaddle(double mouseX) {
        double x = mouseX - PADDLE_WIDTH / 2.0;

        /* To prevent the coordinate from being less than zero. */
        x = Math.max(0, x);
        /* To prevent the coordinate from being more than windows size. */
        x = Math.min(x, getWidth() - PADDLE_WIDTH);

        paddle.setLocation(x, paddle.getY());
    }

    /**
     * The method initialize the start of the game.
     * The game continues up to three attempts or until there are no bricks left.
     */
    private int startGame() {
        int tryNumber = 0;
        while (tryNumber < NTURNS && bricksCounter > 0) {
            tryNumber++;
            nextTry();
        }

        return tryNumber;
    }

    /**
     * The method initialize the attempt.
     * <p>
     * At the first of each attempt need to initialize the ball.
     */
    private void nextTry() {
        initBall();

        /* The game will not start until the user click the mouse. */
        waitForClick();

        moveBall();
    }

    /**
     * The method initialize the ball.
     * <p>
     * Set the default velocity and if the ball is already presented
     * moves it at the default position.
     */
    private void initBall() {
        setDefaultBallVelocity();

        double x = (getWidth() - BALL_SIZE) / 2.0;
        double y = (getHeight() - BALL_SIZE) / 2.0;

        if (ball == null) {
            createBall(x, y);
        } else {
            ball.setLocation(x, y);
        }
    }

    /**
     * The method creates the new ball.
     *
     * @param x - The x coordinate of the ball
     * @param y - The y coordinate of the ball
     */
    private void createBall(double x, double y) {
        ball = createFilledOval(x, y, BALL_SIZE, BALL_SIZE, BALL_COLOR);
        add(ball);
    }


    /**
     * The method set the default values of the ball velocity.
     * <p>
     * The y velocity always will be the same at the start
     * The x velocity will be randomly generated according the constant.
     */
    private void setDefaultBallVelocity() {
        ballDy = BALL_Y_VELOCITY;
        ballDx = getRandomXVelocity();
    }

    /**
     * The method randomly generates the x velocity according to the limits.
     * To make it more different will use the random boolean to get the sign of the velocity.
     *
     * @return - The x velocity of the ball
     */
    private double getRandomXVelocity() {
        RandomGenerator randomGenerator = RandomGenerator.getInstance();
        double dx = randomGenerator.nextDouble(MIN_X_VELOCITY, MAX_X_VELOCITY);
        if (randomGenerator.nextBoolean()) {
            dx = -dx;
        }
        return dx;
    }

    /**
     * The method imitates the ball movement, checking if the ball still in the game
     * and if there are bricks in the game.
     * <p>
     * After each movement will check if the ball collide with the objects ar with the boards.
     */
    private void moveBall() {
        while (isBallInGame() && bricksCounter > 0) {
            ball.move(ballDx, ballDy);

            /* Only if the ball didn't collide with the objects will check
             * if we need to change the properties. */
            if (!collidedWithObject()) {
                changeBallProperties();
            }

            pause(PAUSE_TIME);
        }
    }

    /**
     * The method check is the ball is still on the screen.
     *
     * @return - True is the ball on the screen, false if not.
     */
    private boolean isBallInGame() {
        return ball.getY() < getHeight();
    }

    /**
     * The method checks if the paddle has collided with an object
     *
     * @return - True if collided, false if not.
     */
    private boolean collidedWithObject() {
        /* There are points around the ball to check if there is an object in that coordinates. */
        ArrayList<GPoint> touchPoints = getBallTouchPoints();

        for (GPoint touchPoint : touchPoints) {
            if (isCollision(touchPoint)) {
                return true;
            }
        }
        return false;
    }

    /**
     * The method goes around the ball and create the touch points
     * These points will be used for checking if the ball has collides with other objects.
     *
     * @return - The list of the touch points around the ball.
     */
    private ArrayList<GPoint> getBallTouchPoints() {
        /* The coordinates of the ball center. */
        GPoint ballCenter = getBallCenter();

        ArrayList<GPoint> points = new ArrayList<>();

        int degree = 0;
        /* Full circle rotation is always 360 :) */
        while (degree < FULL_CIRCLE_ROTATION_DEGREES) {
            GPoint touchPoint = new GPoint(
                    getXCoordinate(ballCenter.getX(), degree),
                    getYCoordinate(ballCenter.getY(), degree)
            );

            points.add(touchPoint);

            /* The distance between the points will be shifted according to the constant. */
            degree += DEGREES_BETWEEN_CIRCLE_POINT;
        }

        return points;
    }

    /**
     * The method finds the x coordinate of the touch point.
     * x = centerX + cos(2 * PI * degree / 360) * radius
     * <p>
     * The radius will be increased by circle point offset to not get the point of the ball.
     *
     * @param centerX - The x coordinate of the center of the ball
     * @param degree  - The degree of the point on the circle of the ball
     * @return - The x coordinate of the touch point
     */
    private static double getXCoordinate(double centerX, int degree) {
        return centerX
                + Math.cos(2 * Math.PI * degree / FULL_CIRCLE_ROTATION_DEGREES)
                * (BALL_RADIUS + CIRCLE_POINT_OFFSET);
    }

    /**
     * The method finds the y coordinate of the touch point.
     * y = centerY + sin(2 * PI * degree / 360) * radius
     * <p>
     * The radius will be increased by circle point offset to not get the point of the ball.
     *
     * @param centerY - The coordinate of the center of the ball
     * @param degree  - The degree of the point on the circle of the ball
     * @return - The y coordinate of the touch point
     */
    private static double getYCoordinate(double centerY, int degree) {
        return centerY
                + Math.sin(2 * Math.PI * degree / FULL_CIRCLE_ROTATION_DEGREES)
                * (BALL_RADIUS + CIRCLE_POINT_OFFSET);
    }

    /**
     * The method checks is there is any other objects in the received coordinates.
     *
     * @param touchPoint - The x and y coordinates of the touch point around the ball
     * @return - True if there is some object, false if not.
     */
    private boolean isCollision(GPoint touchPoint) {
        GObject element = getElementAt(touchPoint.getX(), touchPoint.getY());

        if (element != null) {
            /* f the element is found, we imitate a bounce from it. */
            hitElement(element, touchPoint);
            return true;
        }
        return false;
    }

    /**
     * The method imitates the bouncing from the found object
     *
     * @param element    - The found object
     * @param touchPoint - The coordinates where the collider found.
     */
    private void hitElement(GObject element, GPoint touchPoint) {
        GPoint ballCenter = getBallCenter();

        /* Change the properties of the ball after collision. */
        setBallDxAfterCollision(touchPoint, ballCenter);
        setBallDyAfterCollision(touchPoint, element, ballCenter);

        /* Decrement counter if hit object is a brick. */
        if (element != paddle) {
            remove(element);
            bricksCounter--;
        }
    }

    /**
     * @return - The coordinates of the center of the ball.
     */
    private GPoint getBallCenter() {
        double ballCenterX = ball.getX() + BALL_RADIUS;
        double ballCenterY = ball.getY() + BALL_RADIUS;
        return new GPoint(ballCenterX, ballCenterY);
    }

    /**
     * The method moves the ball up until it reaches the coordinate.
     *
     * @param y - The y coordinate to be reached by the bottom side of the ball.
     */
    private void raiseBall(double y) {
        while (ball.getY() + ball.getHeight() > y) {
            ball.move(ballDx, ballDy);
            pause(PAUSE_TIME);
        }
    }

    /**
     * The method moves the ball down until it reaches the coordinate.
     *
     * @param y - The y coordinate to be reached by the top side of the ball.
     */
    private void lowerBall(double y) {
        while (ball.getY() < y) {
            ball.move(ballDx, ballDy);
            pause(PAUSE_TIME);
        }
    }

    /**
     * The method changes the ball dx properties after it has collided with the object.
     *
     * @param touchPoint - The coordinated of the touch point where collider has found.
     * @param ballCenter - The coordinates of the ball center.
     */
    private void setBallDxAfterCollision(GPoint touchPoint, GPoint ballCenter) {
        if (touchPoint.getX() < ballCenter.getX()) {
            ballDx = ballDx > 0 ? ballDx : -ballDx;
        } else if (touchPoint.getX() > ballCenter.getX()) {
            ballDx = ballDx < 0 ? ballDx : -ballDx;
        }
    }

    /**
     * The method changes the ball dy properties after it has collided with the object.
     *
     * @param touchPoint - The coordinated of the touch point where collider has found.
     * @param ballCenter - The coordinates of the ball center.
     */
    private void setBallDyAfterCollision(GPoint touchPoint, GObject collider, GPoint ballCenter) {
        if (touchPoint.getY() > ballCenter.getY()) {
            ballDy = -BALL_Y_VELOCITY;
            raiseBall(collider.getY());
        } else if (touchPoint.getY() < ballCenter.getY()) {
            ballDy = BALL_Y_VELOCITY;
            lowerBall(collider.getY() + collider.getHeight());
        }
    }

    /**
     * The method changes the balls dy and dx properties after it reaches the board
     */
    public void changeBallProperties() {
        changeBallDy();
        changeBallDx();
    }

    /**
     * The method changes the y velocity of the ball
     */
    private void changeBallDy() {
        if (ball.getY() <= 0) {
            ballDy *= -1;
            lowerBall(0);
        }
    }

    /**
     * The method changes the x velocity of the ball
     */
    private void changeBallDx() {
        if (ball.getX() + ball.getWidth() >= getWidth()) {
            ball.setLocation(getWidth() - BALL_SIZE, ball.getY());
            ballDx *= -1;
        } else if (ball.getX() <= 0) {
            ball.setLocation(0, ball.getY());
            ballDx *= -1;
        }
    }

    private void printFinalMessage(int attemptsNumber) {
        removeAll();

        String msg;
        if (attemptsNumber == NTURNS) {
            msg = "Sad, but you lose.";
        } else {
            msg = "Congratulations you won.";
        }

        GLabel label = new GLabel(msg);
        label.setFont("Verdana-20");
        double x = (getWidth() - label.getWidth()) / 2;
        double y = (getHeight() - label.getHeight()) / 2;
        label.setLocation(x, y);
        add(label);
    }
}
