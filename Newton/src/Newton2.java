import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Square Root Calculator using Newton's Method.
 *
 * @author Harith Madani
 *
 */
public final class Newton2 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Newton2() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *
     *            positive number to compute square root of
     * @return estimate of square root
     */
    private static double sqrt(double x) {
        // variable initialization
        final double maxError = 0.0001;
        double r = x;

        /*
         * While our current error is greater than the error threshold, the loop
         * keeps going
         */

        if (x > 0) {
            while (Math.abs(Math.pow(r, 2) - x) / x > Math.pow(maxError, 2)) {

                // The equation provided to get closer to the actual square root
                r = (r + x / r) / 2;
            }
        }
        return r;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        // set "playAgain" to y so the loop runs at least once
        String playAgain;

        out.println("Do you wish to calculate a square root? (y or n) ");
        playAgain = in.nextLine();

        // while the user keeps entering "y" the loop keeps running
        while (playAgain.equals("y")) {

            // prompt the user for a number and store it in "x"
            out.print("Enter a number: ");
            double x = in.nextDouble();

            // store what the method "sqrt()" returns into result
            double result = sqrt(x);

            // outputs the square root
            out.println("The square root is " + result);

            // prompt the user whether they would like to play again
            out.println(
                    "Do you wish to calculate another square root? (y or n) ");
            playAgain = in.nextLine();
        }
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
