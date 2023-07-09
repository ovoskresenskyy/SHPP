package com.shpp.p2p.cs.ovoskresenskyy.assignment8;

import acm.graphics.GRect;

import java.awt.*;

import static com.shpp.p2p.cs.ovoskresenskyy.assignment8.MidExamConstants.*;

/**
 * This class is a simple implementation of the LinkedList
 * <p>
 * There are two nested classes:
 * - Node:
 * The class-holder, which hold the link to the previous and to the next elements in the column
 * <p>
 * - SquareColumnAnimator:
 * The class which will imitate shimmer of the colors if the given column of the squares
 */
public class SquareColumn {

    /**
     * The link to the first element in the column
     * This element will also be linked with the last one, to make the loop
     */
    private final Node first;
    /**
     * The link to the last element in the column
     * This element will also be linked with the first one, to make the loop
     */
    private Node last;

    /**
     * The number of added elements in the column
     */
    private int size;

    /**
     * The indicator is this column is already animated
     */
    private boolean animated;

    /**
     * The constructor will create the new Node and set it as a first and the last element
     *
     * @param square - The object which was just added on the window
     */
    public SquareColumn(GRect square) {
        Node newNode = new Node(square);

        this.first = newNode;
        this.last = newNode;
        this.animated = false;

        size++;
    }

    /**
     * This method is responsible for the creating the new Node
     * and putting it in the end of the list
     * <p>
     * - Previous last node and first nodes will be linked with the just created one
     *
     * @param square - The object which was just added on the window
     */
    public void add(GRect square) {
        Node newNode = new Node(square, first, last);

        last.next = newNode;
        last = newNode;
        first.previous = last;

        size++;
    }

    /**
     * This method is responsible fot starting the imitation of the animation
     */
    public void animate() {
        if (isNeedToAnimate()) {
            Thread animator = new Thread(new SquareColumnAnimator(this));
            animator.start();

            animated = true;
        }
    }

    /**
     * This method checks is current column need to be animated
     * according to the attached materials
     *
     * @return True if it's need to start animation, false if not.
     */
    private boolean isNeedToAnimate() {
        return !animated && size > SQUARE_NUMBER_UNTIL_SHIMMER;
    }

    /**
     * This method is responsible for fixing the edge case.
     * <p>
     * Because our program has a pause between color changes,
     * it is possible to add a square to a column
     * when the iterator has already reached the end of the column.
     * <p>
     * In such a case, the colored square is stuck
     * <p>
     * So we will swap the colors of the two last squares if it needs.
     */
    public void updateColorOfLastSquare() {
        GRect previousToLastSquare = last.previous.square;

        if (squareIsColored(previousToLastSquare)) {
            swapColors(previousToLastSquare, last.square);
        }
    }

    /**
     * This method is simply checks if the given square is colored
     *
     * @param square - The square to be checked
     * @return - True if the fill color of the given square isn't matched with the default color
     */
    private boolean squareIsColored(GRect square) {
        return square.getFillColor() != DEFAULT_SQUARE_COLOR;
    }

    /**
     * This method is responsible to simply swapping the colors of the two given squares
     *
     * @param firstSquare  - The first of the two squares te be swapped the color
     * @param secondSquare - The second of the two squares te be swapped the color
     */
    private void swapColors(GRect firstSquare, GRect secondSquare) {
        Color tempColor = firstSquare.getFillColor();

        firstSquare.setFillColor(secondSquare.getFillColor());
        secondSquare.setFillColor(tempColor);
    }

    /**
     * This method is simply returns the square of the last element in a column
     *
     * @return - The square of the last element in a column
     */
    public GRect getLastSquare() {
        return last.square;
    }

    /**
     * This nested class iss responsible for holding the links to the previous
     * and the next elements in the column, and the link to the placed square.
     */
    private static class Node {
        /**
         * The square placed on the window
         */
        private final GRect square;
        /**
         * The link to the next element in the column
         */
        private Node next;
        /**
         * The link to the previous element in the column
         */
        private Node previous;

        public Node(GRect square) {
            this.square = square;
            this.next = this;
            this.previous = this;
        }

        public Node(GRect square, Node next, Node previous) {
            this.square = square;
            this.next = next;
            this.previous = previous;
        }
    }

    /**
     * This nested class is responsible for the imitating the animation of the given column
     *
     * @param column - The column to be animated
     */
    private record SquareColumnAnimator(SquareColumn column) implements Runnable {

        @Override
        public void run() {

            Node currentNode = column.first;
            Node previousNode;

            while (true) {
                currentNode.square.setFillColor(COLORED_SQUARE_COLOR);

                previousNode = currentNode.previous;
                previousNode.square.setFillColor(DEFAULT_SQUARE_COLOR);

                currentNode = currentNode.next;

                pause();
            }
        }

        /**
         * This method is simply imitate frames updating of the current Thread
         */
        private void pause() {
            try {
                Thread.sleep(PAUSE_TIME);
            } catch (InterruptedException e) {
                throw new RuntimeException("Current thread was interrupted.", e);
            }
        }
    }
}

