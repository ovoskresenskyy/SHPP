package com.shpp.p2p.cs.ovoskresenskyy.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * CSV parsing.
 * <p>
 * The class read the *.csv file and return all values from the received column number.
 * <p>
 * - All input files are properly formatted CSV files.
 * - The index of a particular column will always be correct
 * - Every row in any input file has the same number of columns
 * - Quotes can be contained not only in a string containing commas
 */
public class Assignment5Part4 extends TextProgram {

    /* Path to the *.csv file to process. */
    private static final String FILE_PATH = "assignment5_csv.csv";

    /* Character of the delimiter between fields. */
    private static final Character DELIMITER = ',';

    /* Character denoting the beginning and end of a quote. */
    private static final Character QUOTE_CHAR = '"';

    @Override
    public void run() {

        ArrayList<String> fields = extractColumn(FILE_PATH, 1);
        printFields(fields);

    }


    /**
     * The method gets all values from the received column from the  given csv file.
     *
     * @param filename    - The path to the csv file.
     * @param columnIndex - The index of the column to extract.
     * @return The list of the values from the chosen column or null if it can't read the file.
     */
    private ArrayList<String> extractColumn(String filename, int columnIndex) {
        List<List<String>> valueTable = readFile(filename);
        if (valueTable == null) {
            return null;
        }
        return getValues(valueTable, columnIndex);
    }

    /**
     * The method read the file from the received path.
     * Parse it if it's present.
     *
     * @param filename - The path to the csv file.
     * @return The parsed file if it's present or null if not.
     */
    private List<List<String>> readFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            return parseFile(br);
        } catch (IOException e) {
            println("Can't read the file!");
            return null;
        }
    }

    /**
     * The method handle the file with the buffered reader.
     * It goes through the file and adds each record to the collection of records.
     *
     * @param br - Buffered reader
     * @return Table with the records from the file.
     */
    private List<List<String>> parseFile(BufferedReader br) throws IOException {
        List<List<String>> records = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            records.add(fieldsIn(line));
        }
        return records;
    }

    /**
     * The method gets the values from the line and put it into the collection of the fields.
     * <p>
     * It goes through all characters into the line and separate fields
     * by determining the delimiter.
     *
     * @param line - String from the file, separated by the delimiter.
     * @return The list of the values from the fields.
     */
    private List<String> fieldsIn(String line) {
        final ArrayList<String> fields = new ArrayList<>();
        final StringBuilder field = new StringBuilder();
        boolean isQuoteOpened = false;

        for (int index = 0; index < line.length(); index++) {
            char character = line.charAt(index);

            if (character == QUOTE_CHAR) {
                if (isDoubleQuote(line, index, isQuoteOpened)) {
                    /* Double quote means that we need to add char of the quote into the value. */
                    field.append(character);

                    /* Skip the next quote character. */
                    index++;
                } else {
                    /* Determine the current state of the quote. */
                    boolean endOfQuote = isEndOfQuote(line, index);
                    boolean isNewField = field.length() == 0;

                    isQuoteOpened = getQuoteState(isQuoteOpened, endOfQuote, isNewField);
                }
            } else if (character == DELIMITER && !isQuoteOpened) {
                /* Add the value of the field into the collection of the fields. */
                fields.add(field.toString());
                /* Resetting the field value. */
                field.setLength(0);
            } else {
                field.append(character);
            }
        }
        fields.add(field.toString());

        return fields;
    }

    /**
     * The method defines if the current character is the sign of the double quote.
     *
     * @param line          - Current line from the file.
     * @param currentIndex  - Index of the current character.
     * @param isQuoteOpened - Current state of the quote.
     * @return True if it's the double quote, false if not.
     */
    private boolean isDoubleQuote(String line, int currentIndex, boolean isQuoteOpened) {
        return isQuoteOpened
                && !isEndOfLine(line, currentIndex)
                && getNextChar(line, currentIndex) == QUOTE_CHAR;
    }

    /**
     * The method defines if the current character located at the end of the line.
     *
     * @param line         - Current line from the file.
     * @param currentIndex - Index of the current character.
     * @return True if it's the last char in the line, false if not.
     */
    private boolean isEndOfLine(String line, int currentIndex) {
        return currentIndex == line.length() - 1;
    }

    /**
     * The method returns the next character in the current line.
     *
     * @param line         - Current line from the file.
     * @param currentIndex - Index of the current character.
     * @return The character which placed after the current.
     */
    private char getNextChar(String line, int currentIndex) {
        return line.charAt(currentIndex + 1);
    }

    /**
     * The method determines what state of the quote should be set to.
     *
     * @param isOpen       - Current state of the quote.
     * @param isEndOfQuote - Is it's the end of the quote.
     * @param isNewField   - Is it's the first char of the new field.
     * @return True if it must be open, false if not.
     */
    private boolean getQuoteState(boolean isOpen, boolean isEndOfQuote, boolean isNewField) {
        if (isOpen && isEndOfQuote) {
            /* Close quote if it's opened, and it's the end of the quote. */
            return false;
        } else if (isNewField) {
            /* Open the quote if it's the new field. */
            return true;
        }
        /* Current state if there are no other conditions. */
        return isOpen;
    }

    /**
     * The method defines if it's the end of the previously opened quote.
     *
     * @param line         - Current line from the file.
     * @param currentIndex - Index of the current character.
     * @return True if it's the end of the quote, false if not.
     */
    private boolean isEndOfQuote(String line, int currentIndex) {
        return isEndOfLine(line, currentIndex) || isEndOfField(line, currentIndex);
    }

    /**
     * The method check if it's the end of the field
     * by checking is the next character is delimiter.
     *
     * @param line         - Current line from the file.
     * @param currentIndex - Index of the current character.
     * @return True if it's the end of the field, false if not.
     */
    private boolean isEndOfField(String line, int currentIndex) {
        return getNextChar(line, currentIndex) == DELIMITER;
    }

    /**
     * The method gets the values from the received number of the column from the collection.
     *
     * @param records     - The table with the records
     * @param columnIndex - The number of the column to return
     * @return The list of the fields from the chosen column of the table
     */
    private ArrayList<String> getValues(List<List<String>> records, int columnIndex) {
        ArrayList<String> fields = new ArrayList<>();

        for (List<String> record : records) {
            fields.add(record.get(columnIndex));
        }
        return fields;
    }

    /**
     * The method simply prints all values from the received list
     *
     * @param fields - The list of the fields to print.
     */
    private void printFields(ArrayList<String> fields) {
        if (fields != null) {
            for (String field : fields) {
                println("[" + field + "]");
            }
        }
    }
}
