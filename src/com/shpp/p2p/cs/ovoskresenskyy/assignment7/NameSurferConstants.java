package com.shpp.p2p.cs.ovoskresenskyy.assignment7;

import java.awt.*;

/**
 * File: NameSurferConstants.java
 * ------------------------------
 * This file declares several constants that are shared by the
 * different modules in the NameSurfer application.  Any class
 * that implements this interface can use these constants.
 */

public interface NameSurferConstants {

    /* The width of the application window */
    int APPLICATION_WIDTH = 800;

    /* The height of the application window */
    int APPLICATION_HEIGHT = 600;

    /* The name of the file containing the data */
    String NAMES_DATA_FILE = "assets/names-data.txt";

    /* The first decade in the database */
    int START_DECADE = 1900;

    /* The number of decades */
    int NDECADES = 12;
    /* The value of the one decade, in days */
    int ONE_DECADE = 10;

    /* The maximum rank in the database */
    int MAX_RANK = 1000;

    /* The number of pixels to reserve at the top and bottom */
    int GRAPH_MARGIN_SIZE = 20;

    /* The description of the 'name field' */
    String NAME_FIELD_ACTION_COMMAND = "NameFieldEnterPressed";
    /* The width of the 'name field' */
    int NUM_COLUMNS = 20;

    /* The names of the action commands of the buttons */
    String GRAPH_BUTTON_PRESSED = "GraphButtonPressed";
    String DELETE_BUTTON_PRESSED = "DeleteButtonPressed";
    String CLEAR_BUTTON_PRESSED = "ClearButtonPressed";

    String DECADE_LABEL_FONT = "Verdana-18";
    Color DECADE_LABEL_COLOR = Color.BLACK;

    String RATE_LABEL_FONT = "Verdana-10";


    /* The color of the background grid */
    Color GRAPH_GRID_COLOR = Color.BLACK;

    /* Colors of the graphs */
    Color[] GRAPH_COLORS = {Color.BLUE, Color.RED, Color.MAGENTA, Color.BLACK};

    /* The index of the first value of the ranks in a row */
    int START_POS_POPULARITY_RANKS = 1;
}
