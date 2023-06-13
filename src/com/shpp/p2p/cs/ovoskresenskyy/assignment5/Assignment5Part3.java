package com.shpp.p2p.cs.ovoskresenskyy.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Game on the road.
 * <p>
 * The program prompts the user for a string of three letters
 * and then outputs words that can be made from those letters.
 */
public class Assignment5Part3 extends TextProgram {

    /* Path to dictionary file. */
    private static final String DICTIONARY_PATH = "en-dictionary.txt";

    /* Number of letters expected from the users input. */
    public static final int NUMBER_OF_LETTERS = 3;

    /* This word will be used to stop the game. */
    public static final String WORD_TO_STOP = "stop";

    @Override
    public void run() {

        /* Init dictionary from the file. */
        ArrayList<String> dictionary = initDictionary();
        if (!dictionary.isEmpty()) {
            startTheRoadGame(dictionary);
        }

    }

    /**
     * The method opens and reads the given file with the word and
     * collect it to the list.
     *
     * @return The list of the words from the given file.
     */
    private ArrayList<String> initDictionary() {
        ArrayList<String> dictionary = new ArrayList<>();
        String word;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(DICTIONARY_PATH))) {
            while ((word = bufferedReader.readLine()) != null) {
                dictionary.add(word);
            }
        } catch (IOException e) {
            println("Can't read the file!");
        }

        return dictionary;
    }

    /**
     * The method keeps asking the user to enter 3 letters until he enters "stop".
     * <p>
     * Having received the letters, show the words matched with inputted letters,
     * according to the attached materials.
     *
     * @param dictionary - List of words in the given file.
     */
    private void startTheRoadGame(ArrayList<String> dictionary) {
        String carNumber = "";

        /* Sit in a loop, reading car numbers and finding matched words. */
        while (!carNumber.equals(WORD_TO_STOP)) {
            carNumber = getCarNumber();

            if (isCorrectInput(carNumber)) {
                List<String> matchedWords = findWords(dictionary, carNumber);
                showResult(matchedWords);
            }
        }
    }

    /**
     * The method asks the user to input letters.
     *
     * @return User input.
     */
    private String getCarNumber() {
        return readLine(
                "Please input "
                        + NUMBER_OF_LETTERS
                        + " letters to find the matched words in the dictionary"
                        + "\n or '"
                        + WORD_TO_STOP
                        + "' to end the game: ");
    }

    /**
     * The method checks is users input is correct according to the attached materials.
     *
     * @param userInput - The string entered by the user.
     * @return True if it's correct, false if not.
     */
    private boolean isCorrectInput(String userInput) {
        boolean isInputCorrect = userInput.length() == NUMBER_OF_LETTERS;

        if (!isInputCorrect && !userInput.equals(WORD_TO_STOP)) {
            println("The input must contains "
                    + NUMBER_OF_LETTERS
                    + " letters!");
        }

        return isInputCorrect;
    }

    /**
     * The method goes through the dictionary and finds all matched words.
     *
     * @param userInput  - The letters entered by the user.
     * @param dictionary - The list of the words from the file.
     * @return The list of the matched words according to the inputted letters.
     */
    private List<String> findWords(ArrayList<String> dictionary, String userInput) {
        ArrayList<String> matchedWords = new ArrayList<>();
        char[] chars = userInput.toLowerCase().toCharArray();
        int charIndex = 0;

        for (String word : dictionary) {
            if (isWordMatches(word, chars, charIndex)) {
                matchedWords.add(word);
            }
        }

        return matchedWords;
    }

    /**
     * A recursive method that iterates over all the given letters
     * and determines if they are in the correct order in the given word.
     *
     * @param word    - The word (part of the word) to check if it matches.
     * @param letters - The entered letters.
     * @param index   - The index of the current letter to check the order.
     * @return True if the word (part of the word) is matches, false if not.
     */
    private boolean isWordMatches(String word, char[] letters, int index) {
        /* If index gets the number of entered letters, it's mean that the word is matches. */
        if (index == NUMBER_OF_LETTERS) {
            return true;
        }

        /* Found the index of the letter in the given word. */
        int letterIndexInTheWord = word.indexOf(letters[index]);

        /* If it's == -1 - word doesn't match. */
        if (letterIndexInTheWord == -1) {
            return false;
        }

        /* Getting the next part of the word,
         * which starts from the found letters index,
         * and check if the next given letter is present. */
        String restOfTheWord = word.substring(++letterIndexInTheWord);
        return isWordMatches(restOfTheWord, letters, ++index);
    }

    /**
     * The method simply prints results.
     *
     * @param words - All found words, matched to the inputted letters.
     */
    private void showResult(List<String> words) {
        if (words.isEmpty()) {
            println("There are no matched words for your letters.\n");
        } else {
            words.forEach(System.out::println);
        }
    }
}
