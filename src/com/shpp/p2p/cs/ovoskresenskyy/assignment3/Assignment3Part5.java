package com.shpp.p2p.cs.ovoskresenskyy.assignment3;


import acm.util.RandomGenerator;
import com.shpp.cs.a.console.TextProgram;

/**
 * Saint Petersburg game.
 * <p>
 * This is a hypothetical casino game with a simple ideology.
 * - Two people play: the lucky one and the sweaty one.
 * - The lucky person leaves the casino when he earns $20 or more.
 * - The sweaty one puts $1 on the table, and the lucky one starts flipping a coin.
 * - If it is an eagle, then the sweaty person adds the same amount to the amount on the table.
 * - If the tail is all that is on the table, it goes to the lucky person.
 * - If the lucky person has less than $20 as a result, the game is repeated.
 */
public class Assignment3Part5 extends TextProgram {

    /* The amount of money currently in play. */
    private final static int AMOUNT_TO_WIN = 20;

    /* The amount of money currently in play. */
    private int moneyOnTheTable;
    /* The amount of money the player has already won. */
    private int earnedTotal;
    /* Game counter. */
    private int gamesCounter;

    @Override
    public void run() {

        /* Game starts with $1 on the table. */
        moneyOnTheTable = 1;

        playTheGame();

        println("It took " + gamesCounter + " games to earn $" + AMOUNT_TO_WIN + ".");
    }

    /**
     * Recursive method which simulates tossing a coin.
     * Will continue until user wins $20 or more.
     * <p>
     * Precondition: There is $1 on the table
     * Result: Lucky person earned $20 or more.
     */
    private void playTheGame() {
        /* Signal to stop the game. */
        if (earnedTotal >= AMOUNT_TO_WIN) {
            return;
        }

        /* Coin toss simulator.
         * Conditions:
         * true == eagle
         * false == tail. */
        if (RandomGenerator.getInstance().nextBoolean()) {
            /* Sweaty person adds exactly the same amount to the amount on the table. */
            moneyOnTheTable += moneyOnTheTable;
        } else {
            /* All the money on the table goes to the lucky person. */
            earnedTotal += moneyOnTheTable;

            println("This game, you earned $"
                    + moneyOnTheTable
                    + "\nYour total is $"
                    + earnedTotal);

            moneyOnTheTable = 1;
            gamesCounter++;
        }

        playTheGame();
    }
}
