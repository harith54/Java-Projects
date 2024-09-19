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
public final class Newton3 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Newton3() {
    }

    /**
     * Computes estimate of square root of x to within the user's relative
     * error.
     *
     * @param x
     * @param e
     *            positive number to compute square root of
     * @return estimate of square root
     */
    private static double sqrt(double x, double e) {
        // variable initialization

        double r = x;

        /*
         * While our current error is greater than the error threshold, the loop
         * keeps going
         */

        if (x > 0) {
            while (Math.abs(Math.pow(r, 2) - x) / x > Math.pow(e, 2)) {

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
        double e = 0;

        // Ask if user wants to play
        out.println("Do you wish to calculate a square root? (y or n) ");
        playAgain = in.nextLine();

        // If user wants to play ask for epsilon
        if (playAgain.equals("y")) {
            out.println("Enter an epsilon value (error): ");
            e = in.nextDouble();
        }
        // while the user keeps entering "y" the loop keeps running
        while (playAgain.equals("y")) {

            // prompt the user for a number and store it in "x"
            out.print("Enter a number: ");
            double x = in.nextDouble();

            // store what the method "sqrt()" returns into result
            double result = sqrt(x, e);

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
