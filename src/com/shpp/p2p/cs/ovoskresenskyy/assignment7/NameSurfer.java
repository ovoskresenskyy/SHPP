package com.shpp.p2p.cs.ovoskresenskyy.assignment7;

import com.shpp.cs.a.simple.SimpleProgram;

import java.awt.event.*;
import javax.swing.*;

/**
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */
public class NameSurfer extends SimpleProgram implements NameSurferConstants {

    private NameSurferDataBase nameSurferDataBase;
    private JTextField nameInputField;
    private NameSurferGraph graphField;

    /**
     * This method is responsible for initializing the form, specifically:
     * - reading from the database;
     * - initializing the interactors at the top of the window;
     * - drawing the graph;
     * - adding action listeners.
     */
    public void init() {

        nameSurferDataBase = new NameSurferDataBase(NAMES_DATA_FILE);

        initializeInteractors();

        graphField = new NameSurferGraph();
        add(graphField);

        addActionListeners();
    }

    /**
     * This method is responsible for adding the interactors on the window
     */
    private void initializeInteractors() {
        addNameInputField();
        addButtons();
    }

    /**
     * This method is responsible for adding the text field with its description.
     */
    private void addNameInputField() {
        /* Field description */
        add(new JLabel("Name: "), NORTH);

        nameInputField = new JTextField("", NUM_COLUMNS);
        nameInputField.setActionCommand(NAME_FIELD_ACTION_COMMAND);
        nameInputField.addActionListener(this);
        add(nameInputField, NORTH);
    }

    /**
     * This method is responsible for adding the buttons on the window.
     */
    private void addButtons() {
        addButton("Graph", GRAPH_BUTTON_PRESSED, NORTH);
        addButton("Clear", CLEAR_BUTTON_PRESSED, NORTH);
    }

    /**
     * This method simply adds the button with received command
     * on the received location on the window.
     *
     * @param name     - Button name
     * @param command  - Action commnd
     * @param location - Location (NORTH, SOUTH etc.)
     */
    private void addButton(String name, String command, String location) {
        JButton button = new JButton(name);
        button.setActionCommand(command);
        add(button, location);
    }

    /**
     * This class is responsible for detecting when the buttons are
     * clicked, so you will have to define a method to respond to
     * button actions.
     */
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.equals(GRAPH_BUTTON_PRESSED)
                || cmd.equals(NAME_FIELD_ACTION_COMMAND)) {

            drawNamePopularity();

            /* Clear the name field after drawing the graph. */
            nameInputField.setText("");
        } else if (cmd.equals(CLEAR_BUTTON_PRESSED)) {
            graphField.clear();
            graphField.update();
        }
    }

    /**
     * This method is responsible for drawing the graph
     * of the popularity of the entered name.
     */
    private void drawNamePopularity() {
        NameSurferEntry entry = nameSurferDataBase.findEntry(nameInputField.getText().trim());
        if (entry == null) {
            System.out.println("No information found for the entered name.");
        } else {
            graphField.addEntry(entry);
            graphField.update();
        }
    }
}
