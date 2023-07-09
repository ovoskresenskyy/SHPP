package com.shpp.p2p.cs.ovoskresenskyy.assignment8;

import acm.graphics.GObject;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import static com.shpp.p2p.cs.ovoskresenskyy.assignment8.MidExamConstants.*;

/**
 * Implementing of interactive window with squares.
 * After clicking on any square will growth the column
 * and imitate the shimmering by coloring the one square in a column.
 */
public class MidExam extends WindowProgram {

    /**
     * This map is responsible for holding the list of the squares of each column
     * matched with the x-coordinate of the first square in the baseline.
     * <p>
     * Key - x-coordinate of the first square in the baseline
     * Value - column of the squares, represented as SquareColumn.
     * <p>
     * SquareColumn - simple implementation of the LinkedList
     */
    private final Map<Double, SquareColumn> columns = new HashMap<>();

    @Override
    public void run() {
        drawBaseline();
        addMouseListeners();
    }

    /**
     * Overridden to keep track of which object was clicked.
     * Then will process the clicked object.
     *
     * @param mouseEvent the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        GObject selectedSquare = getElementAt(mouseEvent.getX(), mouseEvent.getY());

        if (selectedSquare != null) {
            double key = selectedSquare.getX();
            SquareColumn column = columns.get(key);

            enlargeColumn(column);
            column.animate();
        }
    }

    /**
     * This method is responsible for drawing the baseline of squares
     * and collect them into the holder.
     */
    private void drawBaseline() {
        double baseLineWidth = SQUARE_NUMBER * SQUARE_SIZE;
        double startX = (getWidth() - baseLineWidth) / 2;

        for (int i = 0; i < SQUARE_NUMBER; i++) {
            double xOffset = SQUARE_SIZE * i;
            double x = startX + xOffset;

            GRect square = getDefaultSquare(x, 0);
            add(square);

            columns.put(square.getX(), new SquareColumn(square));
        }
    }

    /**
     * This method is responsible for growing the size of the columns,
     * by adding new square below.
     *
     * @param column - The column of the squares to be grown.
     */
    public void enlargeColumn(SquareColumn column) {
        GRect lastSquare = column.getLastSquare();
        GRect newSquare = getDefaultSquare(lastSquare.getX(), lastSquare.getY() + SQUARE_SIZE);
        add(newSquare);

        column.add(newSquare);
        column.updateColorOfLastSquare();
    }

    /**
     * The method init default square, with default size and color.
     *
     * @param x - The x coordinate of the upper-left corner of the square.
     * @param y - The y coordinate of the upper-left corner of the square.
     * @return Just created square
     */
    public GRect getDefaultSquare(double x, double y) {
        GRect square = new GRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
        square.setFilled(true);
        square.setFillColor(DEFAULT_SQUARE_COLOR);
        square.setColor(SQUARE_BORDER_COLOR);
        return square;
    }
}
