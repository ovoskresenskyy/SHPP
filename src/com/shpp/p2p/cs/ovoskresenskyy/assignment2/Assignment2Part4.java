package com.shpp.p2p.cs.ovoskresenskyy.assignment2;

import acm.graphics.GLabel;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.util.ArrayList;

public class Assignment2Part4 extends WindowProgram {

    private static final int FLAG_WIDTH = 400;
    private static final int FLAG_HEIGHT = 250;

    @Override
    public void run() {
        setWindowsSize();
        drawEstonia();
    }

    private void drawEstonia() {
        ArrayList<Color> colors = new ArrayList<>();
        colors.add(Color.BLUE);
        colors.add(Color.BLACK);
        colors.add(Color.WHITE);

        drawFlag(colors, true);
        drawLabel("Estonia");
    }

    private void setWindowsSize() {
        int appWidth = getWidth();
        int appHeight = getHeight();
        if (appWidth < FLAG_WIDTH) appWidth = FLAG_WIDTH + 100;
        if (appHeight < FLAG_HEIGHT) appHeight = FLAG_HEIGHT + 100;

        setSize(appWidth, appHeight);
    }

    private void drawFlag(ArrayList<Color> colors, boolean horizontalStripes) {
        if (colors.isEmpty()) return;

        int width = horizontalStripes ? FLAG_WIDTH : FLAG_WIDTH / colors.size();
        int height = horizontalStripes ? FLAG_HEIGHT / colors.size() : FLAG_HEIGHT;

        int x = (getWidth() - FLAG_WIDTH) / 2;
        int y = (getHeight() - FLAG_HEIGHT) / 2;

        for (Color color : colors) {
            drawStripe(x, y, width, height, color);
            y += horizontalStripes ? height : 0;
            x += horizontalStripes ? 0 : width;
        }
    }

    private void drawStripe(int x, int y, int width, int height, Color color) {
        GRect rectangle = new GRect(x, y, width, height);
        rectangle.setFilled(true);
        rectangle.setFillColor(color);
        rectangle.setColor(Color.BLACK);
        add(rectangle);
    }

    private void drawLabel(String name) {
        GLabel label = new GLabel("Flag of " + name);
        label.setFont("Verdana-20");
        add(label, getWidth() - label.getWidth(), getHeight() - label.getDescent());
    }
}
