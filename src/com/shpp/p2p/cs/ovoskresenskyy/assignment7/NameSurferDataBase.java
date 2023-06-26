package com.shpp.p2p.cs.ovoskresenskyy.assignment7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */
public class NameSurferDataBase implements NameSurferConstants {

    /* Holder of all entries from the file. Where Key - the name of the entry. */
    private final Map<String, NameSurferEntry> entries = new HashMap<>();

    /**
     * Creates a new NameSurferDataBase and initializes it using the
     * data in the specified file.  The constructor throws an error
     * exception if the requested file does not exist or if an error
     * occurs as the file is being read.
     */
    public NameSurferDataBase(String filename) {
        readFile(filename);
    }

    /**
     * The method read the file from the received path.
     * Parse it if it's present.
     *
     * @param filename - The path to the csv file.
     */
    private void readFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            parseFile(br);
        } catch (IOException e) {
            System.out.println("Can't read the file.");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * The method handle the file with the buffered reader.
     * It goes through the file and adds each record to the collection of records.
     *
     * @param br - Buffered reader
     */
    private void parseFile(BufferedReader br) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            NameSurferEntry nameSurferEntry = new NameSurferEntry(line);
            entries.put(nameSurferEntry.getName().toLowerCase(), nameSurferEntry);
        }
    }


    /**
     * Returns the NameSurferEntry associated with this name, if one
     * exists.  If the name does not appear in the database, this
     * method returns null.
     */
    public NameSurferEntry findEntry(String name) {
        return entries.get(name.toLowerCase());
    }
}

