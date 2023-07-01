package com.shpp.p2p.cs.ovoskresenskyy.assignment7;

import acm.graphics.GCanvas;
import acm.graphics.GLabel;
import acm.graphics.GLine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.*;
import java.util.List;

/**
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */
public class NameSurferGraph extends GCanvas
        implements NameSurferConstants, ComponentListener {

    /**
     * The holder of the all added entries.
     */
    private final List<NameSurferEntry> entries = new ArrayList<>();

    /**
     * The holder of the all lines for each entry according to the decades
     * Key - decade
     * Value - graph line
     */
    private final Map<NameSurferEntry, Map<Integer, GLine>> lines = new HashMap<>();

    /**
     * The holder of the all labels for each entry according to the decades
     * Key - decade
     * Value - label
     */
    private final Map<NameSurferEntry, Map<Integer, GLabel>> labels = new HashMap<>();

    /**
     * Creates a new NameSurferGraph object that displays the data.
     */
    public NameSurferGraph() {
        addComponentListener(this);
    }

    /**
     * Clears the list of name surfer entries stored inside this class.
     */
    public void clear() {
        entries.clear();
        lines.clear();
        labels.clear();
    }

    /**
     * Adds a new NameSurferEntry to the list of entries on the display.
     * Note that this method does not actually draw the graph, but
     * simply stores the entry; the graph is drawn by calling update.
     */
    public boolean addEntry(NameSurferEntry entry) {
        if (entry == null) {
            JOptionPane.showMessageDialog(new JFrame(), "No information found about entered name.");
            return false;
        }

        if (entries.contains(entry)) {
            JOptionPane.showMessageDialog(new JFrame(), "Entered name is already exist.");
            return false;
        }

        return entries.add(entry);
    }


    /**
     * Updates the display image by deleting all the graphical objects
     * from the canvas and then reassembling the display according to
     * the list of entries. Your application must call update after
     * calling either clear or addEntry; update is also called whenever
     * the size of the canvas changes.
     */
    public void update() {
        removeAll();
        drawGrid();
        drawGraphs();
    }

    /**
     * This method is responsible for drawing the background grid on the window.
     */
    private void drawGrid() {
        drawBackgroundColumns();
        drawHorizontalLines();
    }

    /**
     * This method is draws vertical lines and names for each decade
     */
    private void drawBackgroundColumns() {
        /* Offset between the lines according to the current size of the window */
        double xOffset = getWidth() / (double) NDECADES;

        for (int i = 0; i < NDECADES; i++) {
            /* Horizontal line to divide the decades */
            drawLine(xOffset * i,
                    0,
                    xOffset * i,
                    getHeight(),
                    GRAPH_GRID_COLOR);

            /* Label with the name of the decade */
            drawLabel(Integer.toString(START_DECADE + ONE_DECADE * i),
                    xOffset * i,
                    getHeight(),
                    DECADE_LABEL_FONT,
                    DECADE_LABEL_COLOR);
        }
    }

    /**
     * This method is draws two horizontal lines
     * to divide the graph field from other labels and interactors.
     */
    private void drawHorizontalLines() {
        /* Top line */
        drawLine(0,
                GRAPH_MARGIN_SIZE,
                getWidth(),
                GRAPH_MARGIN_SIZE,
                GRAPH_GRID_COLOR);

        /* Bottom line */
        drawLine(0,
                getHeight() - GRAPH_MARGIN_SIZE,
                getWidth(),
                getHeight() - GRAPH_MARGIN_SIZE,
                GRAPH_GRID_COLOR);
    }

    /**
     * This method is responsible for drawing graphs lines and labels
     * for each entered name.
     * <p>
     * Graphs will get the different colors.
     */
    private void drawGraphs() {
        for (NameSurferEntry entry : entries) {
            if (entry == null) {
                continue;
            }
            drawGraph(entry);
        }
    }

    /**
     * This method is responsible for drawing the rank graph for
     * the received entry.
     * <p>
     * The method will go through all given decades and draw lines to complete
     * drawing the graph.
     * <p>
     * Each drawn line and label will be added into the collections.
     *
     * @param entry - Received entry, which got from the entered name.
     */
    public void drawGraph(NameSurferEntry entry) {
        Color color = getGraphColor(entries.indexOf(entry));

        /* Create a new or get the existing holder of lines */
        Map<Integer, GLine> rankLines = lines.getOrDefault(entry, new HashMap<>());
        /* Create a new or get the existing holder of labels */
        Map<Integer, GLabel> rankLabels = labels.getOrDefault(entry, new HashMap<>());

        int decade = 0;

        /* Offset between the lines according to the current size of the window */
        double xOffset = getWidth() / (double) NDECADES;
        double xStart = xOffset * decade;
        double yStart = getYCoordinate(entry.getRank(decade));

        /* The label with the name and the rank for the start of the first decade */
        GLabel rankLabel = drawLabel(getRankLabelName(entry, decade),
                xStart,
                yStart,
                RATE_LABEL_FONT,
                color);
        rankLabels.put(decade, rankLabel);

        for (decade = 1; decade < NDECADES; decade++) {
            double xEnd = xOffset * decade;
            double yEnd = getYCoordinate(entry.getRank(decade));

            /* The graph line between the start and the end of the decade */
            GLine rankLine = drawLine(xStart, yStart, xEnd, yEnd, color);
            rankLines.put(decade, rankLine);

            /* The label with the name and the rank for the current decade */
            rankLabel = drawLabel(getRankLabelName(entry, decade),
                    xEnd,
                    yEnd,
                    RATE_LABEL_FONT,
                    color);
            rankLabels.put(decade, rankLabel);

            /* The end of the decade becomes the start for the next one */
            xStart = xEnd;
            yStart = yEnd;
        }

        /* Map the filled holders with the current entry */
        lines.put(entry, rankLines);
        labels.put(entry, rankLabels);
    }

    /**
     * This method is responsible for finding the y coordinate
     * according to the received rank.
     * <p>
     * Finds the y coordinate relative to the height of the graph
     * and moves it on the size of the margin
     *
     * @param rank - Received value of the rank
     * @return the y coordinate on the graph field
     */
    private double getYCoordinate(int rank) {
        double graphHeight = getHeight() - GRAPH_MARGIN_SIZE * 2;
        double coefficient = getRankCoefficient(rank);
        return graphHeight - graphHeight * coefficient + GRAPH_MARGIN_SIZE;
    }

    /**
     * This method is responsible for calculation the coefficient of the rank
     * according to the max value.
     * <p>
     * Will not calculate coefficient and return 0 if rank == 0
     *
     * @param rank - Received value of the rank
     * @return coefficient in percents
     */
    private double getRankCoefficient(int rank) {
        if (rank == 0) {
            return 0;
        }
        return 1 - rank / (double) MAX_RANK;
    }

    /**
     * This method simply returns the name and the rank of the current decade
     *
     * If rank is 0, print '*' instead of rank
     *
     * @param entry  - Entry to be processed
     * @param decade - Current index of the decade
     * @return the name with the rank to be placed on the label
     */
    private String getRankLabelName(NameSurferEntry entry, int decade) {
        int rank = entry.getRank(decade);

        return entry.getName()
                + " "
                + (rank == 0 ? "*" : rank);
    }

    /**
     * The method draws colored line according to the received parameters.
     *
     * @param x1    - The x coordinate of the start point of the line
     * @param y1    - The y coordinate of the start point of the line
     * @param x2    - The x coordinate of the end point of the line
     * @param y2    - The y coordinate of the end point of the line
     * @param color - The color of the line
     */
    private GLine drawLine(double x1, double y1, double x2, double y2, Color color) {
        GLine line = new GLine(x1, y1, x2, y2);
        line.setColor(color);
        add(line);
        return line;
    }

    /**
     * The method draws colored label according to the received parameters.
     *
     * @param name  - The name of the label
     * @param x     - The x coordinate of the box around the label
     * @param y     - The y coordinate of the box around the label
     * @param font  - The font of the label
     * @param color - The color of the label
     */
    private GLabel drawLabel(String name, double x, double y, String font, Color color) {
        GLabel label = new GLabel(name);
        label.setFont(font);
        label.setColor(color);
        add(label, x, y - label.getDescent());
        return label;
    }

    /**
     * The method get the color from the presented array of the colors
     *
     * @param index - Index of added name on the graph
     * @return - Color of the graph line and label
     */
    private Color getGraphColor(int index) {
        return GRAPH_COLORS[index % GRAPH_COLORS.length];
    }

    /**
     * This method is responsible for deleting the graph for received name
     *
     * @param entry - Entry to be processed
     */
    public void deleteGraph(NameSurferEntry entry) {
        if (entry == null || !entries.contains(entry)) {
            JOptionPane.showMessageDialog(new JFrame(), "No such graph! Nothing to delete!");
            return;
        }

        removeEntryLines(entry);
        removeEntryLabels(entry);

        /* To not change the order of the colors, will set null instead of entry */
        entries.set(entries.indexOf(entry), null);
    }

    /**
     * This method is responsible for deleting the graph lines from the window
     *
     * @param entry - Entry to be processed
     */
    private void removeEntryLines(NameSurferEntry entry) {
        Map<Integer, GLine> graphLines = lines.get(entry);
        for (GLine line : graphLines.values()) {
            remove(line);
        }
        lines.remove(entry);
    }

    /**
     * This method is responsible for deleting the graph labels from the window
     *
     * @param entry - Entry to be processed
     */
    private void removeEntryLabels(NameSurferEntry entry) {
        Map<Integer, GLabel> graphLabels = labels.get(entry);
        for (GLabel label : graphLabels.values()) {
            remove(label);
        }
        labels.remove(entry);
    }

    /* Implementation of the ComponentListener interface */
    public void componentHidden(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentResized(ComponentEvent e) {
        update();
    }

    public void componentShown(ComponentEvent e) {
    }
}
