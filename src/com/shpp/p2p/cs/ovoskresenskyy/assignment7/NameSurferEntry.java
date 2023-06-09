package com.shpp.p2p.cs.ovoskresenskyy.assignment7;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */
public class NameSurferEntry implements NameSurferConstants {

    private String name;

    /**
     * Holder of popularity ratings according to the decades.
     * Index of each value = index of the decade from 0 to NDECADES
     */
    private final List<Integer> popularityRank = new ArrayList<>();

    /**
     * Creates a new NameSurferEntry from a data line as it appears
     * in the data file.  Each line begins with the name, which is
     * followed by integers giving the rank of that name for each
     * decade.
     */
    public NameSurferEntry(String line) {
        String[] values = line.split(" ");
        setName(values);
        setPopularity(values);
    }

    /**
     * This method is responsible for setting the name,
     * by getting the first value from the received array
     *
     * @param values - the row from the file with the name and the ranks, separated by space.
     */
    private void setName(String[] values) {
        if (values.length == 0) {
            System.out.println("Something went wrong! Can't set the name!");
            this.name = "";
        } else {
            this.name = values[0];
        }
    }

    /**
     * This method is responsible for filling the list with the popularity of the name
     *
     * @param values - the row from the file with the name and the ranks, separated by space.
     */
    private void setPopularity(String[] values) {
        String[] ranks = Arrays.copyOfRange(values, START_POS_POPULARITY_RANKS, values.length);
        int rank;

        for (int i = 0; i < NDECADES; i++) {
            try {
                rank = Integer.parseInt(ranks[i]);
            } catch (NumberFormatException e) {
                System.out.println("Something went wrong!"
                        + "\nCan't set rank for "
                        + name
                        + " for decade # "
                        + i);
                rank = 0;
            }
            popularityRank.add(rank);
        }
    }

    /**
     * Returns the name associated with this entry.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the rank associated with an entry for a particular
     * decade.  The decade value is an integer indicating how many
     * decades have passed since the first year in the database,
     * which is given by the constant START_DECADE.  If a name does
     * not appear in a decade, the rank value is 0.
     */
    public int getRank(int decade) {
        return popularityRank.get(decade);
    }

    /**
     * Returns a string that makes it easy to see the value of a
     * NameSurferEntry.
     */
    public String toString() {
        return name + popularityRank.stream()
                .map(Object::toString)
                .collect(Collectors.joining(" ", " [", "]"));
    }
}
