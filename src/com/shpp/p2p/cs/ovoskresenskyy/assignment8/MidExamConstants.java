package com.shpp.p2p.cs.ovoskresenskyy.assignment8;

import java.awt.*;

/**
 * This file declares several constants that are shared by the
 * different modules in the GreenSquares application.
 * <p>
 * Any class that implements this interface can use these constants.
 */
public class MidExamConstants {

    /* The size of each square in a column */
    public static double SQUARE_SIZE = 40;

    /* The number of the squares in a baseline */
    public static double SQUARE_NUMBER = 10;

    /* Colors of the squares */
    public static Color DEFAULT_SQUARE_COLOR = Color.BLACK;
    public static Color COLORED_SQUARE_COLOR = Color.GREEN;
    public static Color SQUARE_BORDER_COLOR = Color.WHITE;

    /* The number of the squares in a column, after which the shimmer will start working. */
    public static int SQUARE_NUMBER_UNTIL_SHIMMER = 3;

    /* Pause between swapping colors inside the column */
    public static long PAUSE_TIME = 1000 / 10;
}
