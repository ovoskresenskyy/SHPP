package com.shpp.p2p.cs.ovoskresenskyy.assignment8;

import acm.graphics.GRect;

/**
 * This class is responsible for the imitation the animation of the square *
 */
public class Animator implements Runnable {

    /* The sizes range of the animation */
    private static final double MAX_SQUARE_SIZE = 15;
    private static final double MIN_SQUARE_SIZE = 10;

    /* Pause time between the frames */
    private static final long PAUSE_TIME = 1000 / 48;

    /* The speed of changing the size */
    private static final double SIZE_CHANGING_STEP = 1;

    /* The offset of moving the square while it animated */
    private static final double OFFSET = SIZE_CHANGING_STEP / 2;

    /* The square to animate */
    private final GRect square;

    /* The current size of the animated square*/
    private double size;

    private boolean isAnimate = true;

    public Animator(GRect square) {
        this.square = square;
        this.size = square.getHeight();
    }

    /**
     * Simple setter of the field
     */
    public void setAnimate(boolean animate) {
        isAnimate = animate;
    }

    @Override
    public void run() {
        boolean decrease = true;

        while (isAnimate) {
            if (decrease) {
                decreaseSquare();
                decrease = false;
            } else {
                increaseSquare();
                decrease = true;
            }
        }
    }

    /**
     * This method is responsible for increasing size of the square
     */
    private void increaseSquare() {
        while (size <= MAX_SQUARE_SIZE) {
            size += 1;
            square.setSize(size, size);
            square.move(-OFFSET, -OFFSET);
            pause();
        }
    }

    /**
     * This method is responsible for decreasing size of the square
     */
    private void decreaseSquare() {
        while (size >= MIN_SQUARE_SIZE) {
            size -= SIZE_CHANGING_STEP;
            square.setSize(size, size);
            square.move(OFFSET, OFFSET);
            pause();
        }
    }

    /**
     * The pause between the frames
     */
    private void pause() {
        try {
            Thread.sleep(PAUSE_TIME);
        } catch (InterruptedException e) {
            throw new RuntimeException("Current thread was interrupted.", e);
        }
    }
}
