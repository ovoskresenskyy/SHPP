package com.shpp.p2p.cs.ovoskresenskyy.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.util.ArrayList;

/**
 * Counting the number of syllables.
 * <p>
 * The class count the number of syllables using the following analysis -
 * count the number of vowels in the word (taking into account y), excluding:
 * - vowels preceded by other vowels
 * - letter 'e' if it is at the end of the word.
 */
public class Assignment5Part1 extends TextProgram {

    /* According to the attached materials, the letter 'e' will not be taken into account
     * when counting syllables if it is at the end of a word. */
    private static final char UNCOUNTABLE_CHAR = 'e';

    /**
     * The List of vowels to be taken into account when counting syllables.
     */
    private final ArrayList<Character> vowels = new ArrayList<>() {
        {
            add('a');
            add('e');
            add('i');
            add('u');
            add('o');
            add('y');
        }
    };

    /* Using this variable, we will count the number of syllables according to the rules. */
    private boolean previousIsConsonant;

    @Override
    public void run() {

        startSyllableCounting();

    }

    /**
     * The method keeps asking the user to enter any word until he enters "stop".
     * <p>
     * Having received the word, counts the number of syllables and displays the information.
     */
    private void startSyllableCounting() {
        String word = "";

        while (!word.equals("stop")) {
            word = readLine("Enter a single word or \"stop\" for exit: ");
            println("  Syllable count: " + syllablesInWord(word));
        }
    }

    /**
     * Given a word, estimates the number of syllables in that word according to the
     * heuristic specified in the handout.
     *
     * @param word - A string containing a single word.
     * @return An estimate of the number of syllables in that word.
     */
    private int syllablesInWord(String word) {
        word = word.toLowerCase();
        previousIsConsonant = true;
        int counter = 0;

        for (int i = 0; i < word.length(); i++) {
            if (isSyllable(word, i)) {
                counter++;
            }
        }

        /* We have to return at least 1 syllable for words like 'we'. */
        return counter == 0 ? 1 : counter;
    }

    /**
     * The method determines if the character at the given index of the given word
     * is a sign that it is a syllable.
     *
     * @param word        - A given word in which we count syllables
     * @param letterIndex - An index of the letter
     * @return True if it's sign to count syllable, false if not.
     */
    private boolean isSyllable(String word, int letterIndex) {
        boolean result = false;
        boolean isVowel = isVowel(word.charAt(letterIndex));

        if (isVowel && previousIsConsonant) {
            result = isCountable(word, letterIndex);
        }

        /* Change the value to correctly determine the number of syllables in the future. */
        previousIsConsonant = !isVowel;
        return result;
    }

    /**
     * The method simply determines if a letter is a vowel by looking in the list of vowels.
     *
     * @param letter - Any letter to check if it's a vowel
     * @return True if it's a vowel, false if not.
     */
    private boolean isVowel(Character letter) {
        return vowels.contains(letter);
    }


    /**
     * The method determines is that syllable can be counted, according to the attached materials.
     * It can't be counted if it's the last letter of the word, and it's the letter 'e'.
     *
     * @param word        - A given word
     * @param letterIndex - An index of the letter.
     * @return True if this syllable can be counted, false if not.
     */
    private boolean isCountable(String word, int letterIndex) {
        return letterIndex != word.length() - 1 || word.charAt(letterIndex) != UNCOUNTABLE_CHAR;
    }
}
