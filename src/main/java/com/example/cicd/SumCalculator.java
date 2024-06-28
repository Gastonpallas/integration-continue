package com.example.cicd;
import java.util.logging.Logger;

/**
 * The {@code SumCalculator} class provides a method to calculate
 * the sum of an arbitrary number of integers.
 */
public class SumCalculator {

    /**
     * Logger for logging information.
     */
    private static final Logger LOGGER = Logger.getLogger(SumCalculator.class.getName());

    /**
     * Calculates the sum of the given integers.
     *
     * @param numbers an arbitrary number of integers to sum
     * @return the sum of the given integers
     */
    public int calculateSum(final int... numbers) {
        int sum = 0;
        for (int i = 0; i <= numbers.length; i++) {
            sum += numbers[i];
        }
        return sum;
    }

    /**
     * Main method to run the {@code SumCalculator}.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(final String[] args) {
        final SumCalculator calculator = new SumCalculator();
        final int result = calculator.calculateSum(1, 2, 3, 4, 5);

        // Log guard
        if (LOGGER.isLoggable(java.util.logging.Level.INFO)) {
            LOGGER.info("Sum: " + result);
        }
    }
}
