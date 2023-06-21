package com.shpp.p2p.cs.ovoskresenskyy.assignment6.tm;

public class ToneMatrixLogic {

    private final static double DEFAULT_INTENSITY = 1;

    /**
     * Given the contents of the tone matrix, returns a string of notes that should be played
     * to represent that matrix.
     *
     * @param toneMatrix The contents of the tone matrix.
     * @param column     The column number that is currently being played.
     * @param samples    The sound samples associated with each row.
     * @return A sound sample corresponding to all notes currently being played.
     */
    public static double[] matrixToMusic(boolean[][] toneMatrix, int column, double[][] samples) {
        double[] result = new double[ToneMatrixConstants.sampleSize()];

        for (int row = 0; row < toneMatrix.length; row++) {
            boolean isCellOn = toneMatrix[row][column];
            if (isCellOn) {
                fillResult(result, samples[row]);
            }
        }

        return normalize(result);
    }


    private static void fillResult(double[] result, double[] sampleRow) {
        for (int i = 0; i < sampleRow.length; i++) {
            result[i] += sampleRow[i];
        }
    }

    /**
     * Method for sound normalization
     * When we divide the sample value by a negative number, we get a change in the phase of the sound
     * (the graph of our sinusoid changes direction, and if it started up, then after normalization
     * it will start with a downward movement)
     *
     * @param result the whole array
     * @return normalized sound
     */
    private static double[] normalize(double[] result) {
        double maxIntensity = getMaxIntensity(result);

        if (Math.abs(maxIntensity) > 1) {
            for (int i = 0; i < result.length; i++) {
                result[i] /= maxIntensity;
            }
        }

        return result;
    }

    /**
     * The method finds the absolute maximum intensity value
     *
     * @param result - Given array of tones
     * @return Maximum intensity value
     */
    private static double getMaxIntensity(double[] result) {
        double maxIntensity = DEFAULT_INTENSITY;

        for (double tone : result) {
            if (Math.abs(tone) > Math.abs(maxIntensity)) {
                maxIntensity = tone;
            }
        }

        return maxIntensity;
    }
}
