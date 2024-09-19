import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Program for approximating a constant using the de Jager formula with
 * user-provided values for exponent bases.
 *
 * @author Harith Madani
 *
 */
public final class ABCDGuesser1 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private ABCDGuesser1() {
    }

    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {

        // prompt the user for a positive number
        out.print("Enter a positive number:  ");
        String userNum = in.nextLine();
        // set the number as a negative value so the loop runs at least once
        double returnNum = -1; //

        // loop iterates while the input is either negative or not a number
        while (returnNum < 0 || !FormatChecker.canParseDouble(userNum)) {
            while (!FormatChecker.canParseDouble(userNum)) {
                // keep prompting the user to enter a number if a string is input
                out.print("Error! Enter a number: ");
                userNum = in.nextLine();
            }
            //make the return value = to what was input
            returnNum = Double.parseDouble(userNum);

            // if the number is negative, re-prompt the user for a positive value
            if (returnNum < 0) {

                out.print("Error! Enter a positive number: ");
                userNum = in.nextLine();
            }
        }

        return returnNum;
    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number not equal to 1.0 entered by the user
     */
    private static double getPositiveDoubleNotOne(SimpleReader in,
            SimpleWriter out) {

        double num = 1;

        //loop continues to iterate until a value that isn't "1" is input
        while (num == 1) {
            // prompts the user for a positive number using the method above
            num = getPositiveDouble(in, out);

            // If the value is "1" print an error message and re-run the loop
            if (num == 1) {
                out.print("Error! Number cannot be 1");
                out.println();
            }
        }

        return num;
    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param u
     * @param w
     * @param x
     * @param y
     * @param z
     *
     *
     * @return an array of the correct de Jager formula values
     *
     */
    private static double[] deJagerFormula(double u, double w, double x,
            double y, double z) {

        // initialize an array with the exponent values for the de Jager formula
        final double[] formulaValues = { -5, -4, -3, -2, -1, (-1.0 / 2),
                (-1.0 / 3), (-1.0 / 4), 0, (1.0 / 4), (1.0 / 3), (1.0 / 2), 1,
                2, 3, 4, 5 };

        final int three = 3;
        final int four = 4;
        final int five = 5;

        int length = formulaValues.length;
        double[] bestValues = new double[five];
        double bestResult = Double.MAX_VALUE;

        // four nested loops so that every possible combination of exponents is checked
        int i = 0;
        while (i < length) {
            int j = 0;
            while (j < length) {
                int k = 0;
                while (k < length) {
                    int l = 0;
                    while (l < length) {

                        // set a, b, c, and d to the current combination of exponents
                        double a = formulaValues[i];
                        double b = formulaValues[j];
                        double c = formulaValues[k];
                        double d = formulaValues[l];

                        // calculate the result of the current combination of exponents
                        double result = Math.pow(w, a) * Math.pow(x, b)
                                * Math.pow(y, c) * Math.pow(z, d);

                        // check if the current result is closer to the actual number than
                        // the previous best result
                        if (Math.abs(result - u) < Math.abs(bestResult - u)) {
                            // if this is true, set the "bestValues" array indices to
                            // each of the current exponent values, as well as setting
                            // "bestResult" to the current result
                            bestResult = result;
                            bestValues[0] = a;
                            bestValues[1] = b;
                            bestValues[2] = c;
                            bestValues[three] = d;
                        }

                        l++;
                    }
                    k++;
                }
                j++;
            }
            i++;
        }

        bestValues[four] = bestResult;
        // return all of the "best" values in an array
        return bestValues;
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
        /*
         * Put your main program code here; it may call myMethod as shown
         */

        // prompt the user for a constant (μ value)
        out.println("Enter a constant");
        double yourConstant = getPositiveDouble(in, out);

        // prompt the user for a w, x, y, and z, along with a
        // message so the user knows which they are entering
        out.println();
        out.println("Now enter 4 values for w, x, y, and z not equal to 1");
        out.println("Enter a W value");
        double w = getPositiveDoubleNotOne(in, out);

        out.println("Enter an X value");
        double x = getPositiveDoubleNotOne(in, out);
        out.println();

        out.println("Enter a Y value");
        double y = getPositiveDoubleNotOne(in, out);
        out.println();

        out.println("Enter a Z value");
        double z = getPositiveDoubleNotOne(in, out);
        out.println();

        // call the de Jager formula method for the entered values
        double[] finalValues = deJagerFormula(yourConstant, w, x, y, z);

        final int three = 3;
        final int four = 4;
        final int oneHundred = 100;

        // calculate the final error percentage
        double error = (Math.abs((finalValues[four] - yourConstant))
                / yourConstant) * oneHundred;

        // out the user's approximated value, corresponding exponent values,
        // and final error percentage
        out.println("Your approximated value is: " + finalValues[four]);
        out.println("Your exponent values are " + finalValues[0] + ", "
                + finalValues[1] + ", " + finalValues[2] + ", "
                + finalValues[three]);
        out.print("Error percentage: ");
        out.print(error, 2, false);
        out.println("%");
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
