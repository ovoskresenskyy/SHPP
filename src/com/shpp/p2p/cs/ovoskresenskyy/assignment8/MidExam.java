package com.shpp.p2p.cs.ovoskresenskyy.assignment8;

import acm.graphics.GObject;
import acm.graphics.GPoint;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Midexam: Chessboard
 */
public class MidExam extends WindowProgram {

    /* The default width and height of the window.
     * These constants will tell Java to create a window
     * whose size is *approximately* given by these dimensions. */
    public static final int APPLICATION_WIDTH = 800;
    public static final int APPLICATION_HEIGHT = 600;

    /* The default color of the drawn squares. */
    private static final Color DEFAULT_SQUARE_COLOR = Color.BLACK;
    /* The size of the squares. */
    private static final double SQUARE_SIZE = 20;

    /* The radius around the placed cursor, which trigger squares animation. */
    private static final double ANIMATION_RADIUS = 30;

    /* The holder of the all drawn squares.
     * - Key: Center point of each square
     * - Value: square */
    private final Map<GPoint, GRect> squares = new HashMap<>();

    /* The holder of the all animated squares
     * - Key: square
     * - Value: instance of the Animator class */
    private final Map<GRect, Animator> animators = new HashMap<>();

    @Override
    public void run() {
        drawSquares();
        addMouseListeners();
    }

    /**
     * This method is responsible for drawing the squares
     * in the chessboard order
     */
    private void drawSquares() {
        int rows = (int) (getWidth() / SQUARE_SIZE);
        int columns = (int) (getHeight() / SQUARE_SIZE);

        /* The x coordinate of the upper-left corner of the first box. */
        double x = (getWidth() - SQUARE_SIZE * rows) / 2;
        /* The y coordinate of the upper-left corner of the first box. */
        double y = (getHeight() - SQUARE_SIZE * columns) / 2;

        boolean isColumnStartWithBlack = true;
        boolean isSquareBlack = true;

        for (int i = 0; i < rows; i++) {
            if (isSquareBlack == isColumnStartWithBlack) {
                isSquareBlack = !isSquareBlack;
            }

            for (int j = 0; j < columns; j++) {
                if (isSquareBlack) {
                    GRect square = drawSquare(x + SQUARE_SIZE * i, y + SQUARE_SIZE * j, SQUARE_SIZE);
                    squares.put(getSquareCenter(square), square);
                }
                isSquareBlack = !isSquareBlack;
            }

            isColumnStartWithBlack = !isColumnStartWithBlack;
        }
    }

    /**
     * This method is simply finds the center point of the
     * received square
     *
     * @param square - The square to find the center
     * @return The point of the center of the square
     */
    private GPoint getSquareCenter(GRect square) {
        return new GPoint(square.getX() + SQUARE_SIZE / 2, square.getY() + SQUARE_SIZE / 2);
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        ArrayList<GRect> nearestSquares = getCloseToCursorSquares(mouseEvent.getX(), mouseEvent.getY());
        animateNearestSquares(nearestSquares);
    }

    /**
     * This method is responsible for animating the nearest to cursor
     * squares if it's necessary.
     *
     * @param nearestSquares - The list of the squares to be animated
     */
    private void animateNearestSquares(ArrayList<GRect> nearestSquares) {
        for (GRect square : squares.values()) {
            boolean isAnimated = animators.containsKey(square);
            boolean isNeedToAnimate = nearestSquares.contains(square);

            if (isNeedToAnimate && isAnimated) {
                return;
            }

            if (isNeedToAnimate) {
                Animator animator = new Animator(square);
                new Thread(animator).start();

                animators.put(square, animator);
            } else if (isAnimated) {
                Animator animator = animators.get(square);
                animator.setAnimate(false);
                animators.remove(square);
            }
        }
    }

    /**
     * This method is simply collect all squares that are placed
     * inside the circle around the cursor
     *
     * @param cursorX - The current x-coordinate of the cursor
     * @param cursorY - The current y-coordinate of the cursor
     * @return The list of the nearest squares
     */
    private ArrayList<GRect> getCloseToCursorSquares(double cursorX, double cursorY) {
        return squares.entrySet()
                .stream()
                .filter(s -> s.getKey().getX() > cursorX - ANIMATION_RADIUS
                        && s.getKey().getX() < cursorX + ANIMATION_RADIUS
                        && s.getKey().getY() > cursorY - ANIMATION_RADIUS
                        && s.getKey().getY() < cursorY + ANIMATION_RADIUS)
                .map(Map.Entry::getValue)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        GObject selectedSquare = getElementAt(mouseEvent.getX(), mouseEvent.getY());

        if (selectedSquare != null) {
            animateClickedSquare((GRect) selectedSquare);
        }

    }

    /**
     * This method is responsible for start and stop the animation
     * of the received square
     *
     * @param square - The square to be processed
     */
    private void animateClickedSquare(GRect square) {
        if (animators.containsKey(square)) {
            Animator animator = animators.get(square);
            animator.setAnimate(false);
            animators.remove(square);
        } else {
            Animator animator = new Animator(square);
            new Thread(animator).start();
            animators.put(square, animator);
        }
    }

    /**
     * The method init default square, with default size and color.
     *
     * @param x - The x coordinate of the upper-left corner of the square.
     * @param y - The y coordinate of the upper-left corner of the square.
     * @return Just created square
     */
    public GRect drawSquare(double x, double y, double size) {
        GRect square = new GRect(x, y, size, size);
        square.setFilled(true);
        square.setFillColor(DEFAULT_SQUARE_COLOR);
        add(square);
        return square;
    }
}
