package com.shpp.p2p.cs.ovoskresenskyy.assignment3;

import com.shpp.cs.a.console.TextProgram;

/**
 * Aerobics
 * <p>
 * The program asks the user how many minutes he spent for last 7 days for exercising.
 * Then shows is that was enough for cardiovascular health and for blood pressure.
 * <p>
 * Used abbreviations:
 * CVH - Cardiovascular Health
 * BP - Blood Pressure
 */
public class Assignment3Part1 extends TextProgram {

    /* Minimum minutes a day exercising for good cardiovascular health. */
    public final static int MINUTES_FOR_CVH = 30;
    /* Minimum minutes a day exercising for good blood pressure. */
    public final static int MINUTES_FOR_BP = 40;

    /* Minimum days a week exercising for good cardiovascular health. */
    public final static int DAYS_ENOUGH_CVH = 5;
    /* Minimum days a week exercising for good blood pressure. */
    public final static int DAYS_ENOUGH_BP = 3;

    /* The number of days in which the user trained enough for good cardiovascular health. */
    private int daysTrainedCVH;
    /* The number of days in which the user trained enough for good blood pressure. */
    private int daysTrainedBP;

    @Override
    public void run() {
        countTrainingDays();

        printCVH();
        printBP();
    }

    /**
     * The method asks the user about his exercising for last 7 days
     * And count days in which lesson duration was enough for good cardiovascular health and good blood pressure.
     */
    private void countTrainingDays() {
        for (int dayNumber = 1; dayNumber <= 7; dayNumber++) {
            int minutesTrained = readInt("How many minutes did you work out on day " + dayNumber + "? ");
            if (minutesTrained >= MINUTES_FOR_CVH) daysTrainedCVH++;
            if (minutesTrained >= MINUTES_FOR_BP) daysTrainedBP++;
        }
    }

    /**
     * The method prints information whether there were enough days for good cardiovascular health.
     */
    private void printCVH() {
        println("Cardiovascular health:");
        if (daysTrainedCVH >= DAYS_ENOUGH_CVH) {
            println("Great job! You've done enough exercise for cardiovascular health.");
        } else {
            printTryHarder(DAYS_ENOUGH_CVH - daysTrainedCVH);
        }
    }

    /**
     * The method prints information whether there were enough days for good blood pressure.
     */
    private void printBP() {
        println("Blood pressure:");
        if (daysTrainedBP >= DAYS_ENOUGH_BP) {
            println("Great job! You've done enough exercise to keep a low blood pressure.");
        } else {
            printTryHarder(DAYS_ENOUGH_BP - daysTrainedBP);
        }
    }

    /**
     * The method prints that user has to exercise more.
     *
     * @param amountOfDays - Amount of days that are not enough to get the achievement.
     */
    private void printTryHarder(int amountOfDays) {
        println("You needed to train harder for at least " + amountOfDays + " more day(s) a week!.");
    }
}
