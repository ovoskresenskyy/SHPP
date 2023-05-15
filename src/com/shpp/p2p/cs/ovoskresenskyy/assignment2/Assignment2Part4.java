package com.shpp.p2p.cs.ovoskresenskyy.assignment2;

import acm.graphics.GLabel;
import acm.graphics.GRect;

import java.awt.*;
import java.util.ArrayList;

/**
 * Tricolor-flags.
 * <p>
 * The program writes a program that draws flag of Estonia
 * and in the lower right corner of the window print the inscription "Flag of Estonia".
 */
public class Assignment2Part4 extends SuperWindowProgram {

    /* Constants to control the width and height of the flag. */
    private static final int FLAG_WIDTH = 400;
    private static final int FLAG_HEIGHT = 250;

    @Override
    public void run() {
        /* Want to be sure that the window size is larger than the flag. */
        setWindowsSize();

        drawFlagOfEstonia();
    }

    /**
     * The methods automatically increases the windows size if flag larger.
     * This is necessary so that Label will be placed correctly.
     */
    private void setWindowsSize() {
        int appWidth = getWidth();
        int appHeight = getHeight();

        /* Add empty space around the flag if window is small. */
        appWidth = appWidth < FLAG_WIDTH ? FLAG_WIDTH + 100 : appWidth;
        appHeight = appHeight < FLAG_HEIGHT ? FLAG_HEIGHT + 100 : appHeight;

        setSize(appWidth, appHeight);
    }

    /**
     * The method draws flag of Estonia and print label.
     */
    private void drawFlagOfEstonia() {
        /* Stripe colors */
        ArrayList<Color> colors = new ArrayList<>();
        colors.add(Color.BLUE);
        colors.add(Color.BLACK);
        colors.add(Color.WHITE);

        /* x coordinate to be sure that flag is placed in the center of the window. */
        int x = (getWidth() - FLAG_WIDTH) / 2;
        /* y coordinate to be sure that flag is placed in the center of the window. */
        int y = (getHeight() - FLAG_HEIGHT) / 2;

        drawFlag(x, y, colors, true);
        drawLabel("Flag of Estonia");
    }

    /**
     * The method draws the flag with received parameters.
     * The method can draw a flag with horizontal or vertical stripes.
     * Yes, I know that it's always horizontal, but I wanted to make universal method.
     *
     * @param x            - The x coordinate of the upper-left corner of the flag.
     * @param y            - The y coordinate of the upper-left corner of the flag.
     * @param colors       - List of colored stripes to be drawn one by one.
     * @param isHorizontal - true if stripes are Horizontal, false if Vertical.
     */
    private void drawFlag(int x, int y, ArrayList<Color> colors, boolean isHorizontal) {
        /* No stripes - no flag. */
        if (colors.isEmpty()) return;

        double stripeWidth = getStripeWidth(isHorizontal, colors.size());
        double stripeHeight = getStripeHeight(isHorizontal, colors.size());

        for (Color color : colors) {
            GRect stripe = drawFilledRectangle(x, y, stripeWidth, stripeHeight, color);
            stripe.setColor(Color.BLACK);

            /* Shift stripes location depending on the type of flag. */
            y += isHorizontal ? stripeHeight : 0;
            x += isHorizontal ? 0 : stripeWidth;
        }
    }

    /**
     * The method calculate stripe width depending on the type of flag and number of the strips.
     * Used a separate method to isolate the stripe size calculation from the method of drawing the flag.
     *
     * @param isHorizontal   - true if stripes are Horizontal, false if Vertical.
     * @param numberOfStrips - number of stripes on the flag.
     */
    private double getStripeWidth(boolean isHorizontal, int numberOfStrips) {
        return isHorizontal
                ? FLAG_WIDTH
                : (double) FLAG_WIDTH / numberOfStrips;
    }

    /**
     * The method calculate stripe height depending on the type of flag and number of the strips.
     * Used a separate method to isolate the stripe size calculation from the method of drawing the flag.
     *
     * @param isHorizontal   - true if stripes are Horizontal, false if Vertical.
     * @param numberOfStrips - number of stripes on the flag.
     */
    private double getStripeHeight(boolean isHorizontal, int numberOfStrips) {
        return isHorizontal
                ? (double) FLAG_HEIGHT / numberOfStrips
                : FLAG_HEIGHT;
    }

    /**
     * The method draws label according to the received name.
     * Label will always be placed in the right bottom corner of the window.
     * <p>
     * Yes, I know that it's always the same name, but I wanted to make universal method.
     */
    private void drawLabel(String name) {
        GLabel label = new GLabel(name);
        label.setFont("Verdana-20");

        /* The x coordinate of the upper-left corner of the bounding box for the label. */
        double x = getWidth() - label.getWidth();
        /* The y coordinate of the upper-left corner of the bounding box for the label. */
        double y = getHeight() - label.getDescent();
        add(label, x, y);
    }
}
