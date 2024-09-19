import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.Reporter;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author Harith Madani
 *
 */
public final class XMLTreeIntExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeIntExpressionEvaluator() {
    }

    /**
     * Evaluate the given expression.
     *
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires <pre>
     * [exp is a subtree of a well-formed XML arithmetic expression]  and
     *  [the label of the root of exp is not "expression"]
     * </pre>
     * @ensures evaluate = [the value of the expression]
     */
    private static int evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";
        int num = 0;

        // If the tag node is labeled "number", parse the value
        if (exp.label().equals("number")) {
            num = Integer.parseInt(exp.attributeValue("value"));
        }

        // If the tag's label is "plus", recursively call evaluate to
        // parse the values and add them together

        if (exp.label().equals("plus")) {
            num = evaluate(exp.child(0)) + evaluate(exp.child(1));
        }

        // If the tag's label is "minus", recursively call evaluate to
        // parse the values and subtract
        if (exp.label().equals("minus")) {
            num = evaluate(exp.child(0)) - evaluate(exp.child(1));
        }

        // If the tag's label is "times", recursively call evaluate to
        // the values and multiply
        if (exp.label().equals("times")) {
            num = evaluate(exp.child(0)) * evaluate(exp.child(1));
        }

        // If the tag's label is "plus", recursively call evaluate to
        // parse the values and divide
        if (exp.label().equals("divide")) {
            int divisor = evaluate(exp.child(1));
            // Make sure the divisor is not 0
            if ((divisor) != 0) {
                num = evaluate(exp.child(0)) / divisor;
                Reporter.fatalErrorToConsole("Error! Cannot divide by 0");
            }
        }

        return num;
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

        // Prompt the user for an XML file name
        out.print("Enter the name of an expression XML file: ");
        String file = in.nextLine();
        // While the file name is not empty, keep looping
        while (!file.equals("")) {
            // Process the filename into an XML file and run the evaluate method
            XMLTree exp = new XMLTree1(file);
            out.println(evaluate(exp.child(0)));
            out.print("Enter the name of an expression XML file: ");
            file = in.nextLine();
        }

        in.close();
        out.close();
    }

}
