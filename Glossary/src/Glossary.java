import java.util.Arrays;

import components.map.Map;
import components.map.Map1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * HTML Glossary page generation program.
 *
 * @author Harith Madani
 */
public final class Glossary {

    /**
     * Private no-argument constructor to prevent instantiation of this utility
     * class.
     */
    private Glossary() {

    }

    /**
     * Generates and outputs an HTML page for a given term, including its
     * definition and links to other terms within the glossary if mentioned in
     * the definition.
     *
     * @param allTerms
     * @param term
     * @param definition
     * @param out
     */

    public static void outputPage(String[] allTerms, String term,
            String definition, SimpleWriter out) {
        // Create an array of all the words in the definition
        String[] words = definition.split("[ ,]+");

        // Print required tags
        out.println("<html><head><title>" + term + "</title></head><body>");
        // Print the term in Red, Bolded, Italicized font
        out.println("<h1 style=\"color:red;\"><b><i>" + term + "</i></b></h1>");
        out.print("<p>");
        // Iterate through the words of the definition
        for (String word : words) {

            // Use a loop to check if the current word is a term in the glossary
            boolean isInGlossary = false;
            for (int i = 0; i < allTerms.length && !isInGlossary; i++) {
                if (word.equals(allTerms[i])) {
                    isInGlossary = true;

                }
            }

            // If the word is in the glossary, print it as a link,
            // otherwise print normally
            if (isInGlossary) {
                out.print(
                        "<a href=\"" + word + ".html\">" + word + "</a>" + " ");
            } else {
                out.print(word + " ");
            }
        }

        // Print closing tags
        out.println(
                ".</p> <hr> <p> Return to <a href= \" index.html\">index </a>"
                        + " </body></html>");
    }

    /**
     * Reads terms and their definitions from a specified file.
     *
     * @param fileName
     * @param termMap
     */
    public static void getTermMap(String fileName,
            Map<String, String> termMap) {
        SimpleReader inFile = new SimpleReader1L(fileName);

        // While the end of file stream hasn't been reached
        while (!inFile.atEOS()) {
            String term = inFile.nextLine();
            String definition = "";
            String line = inFile.nextLine();
            Boolean continueReading = true;

            // While the line's empty and continueReading is true
            while (!line.isEmpty() && continueReading) {
                // The line is concatenated onto definition along with a space.
                definition += line + " ";
                // if the file stream hasn't been reached, line is the next line
                if (!inFile.atEOS()) {
                    line = inFile.nextLine();
                    // otherwise continueReading becomes false to end the loop
                } else {

                    continueReading = false;
                }
            }
            // Add the term and definition to the map as a pair
            termMap.add(term, definition);
        }
        inFile.close();
    }

    /**
     * Extracts terms from a map and sorts them alphabetically.
     *
     * @param termMap
     * @return termsArray
     */
    public static String[] sortTerms(Map<String, String> termMap) {
        // Make an array the same size as our map
        String[] termsArray = new String[termMap.size()];
        int i = 0;
        // Add all map keys to the array using a loop
        for (Map.Pair<String, String> pair : termMap) {
            termsArray[i] = pair.key();
            i++;
        }

        // Use the sort tool to sort the array alphabetically
        Arrays.sort(termsArray);
        return termsArray;
    }

    /**
     * Main method.
     *
     * @param args
     */

    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        SimpleReader in = new SimpleReader1L();

        // Prompt the user for an input file and folder path
        out.print("Enter the input file path: ");
        String filePath = in.nextLine();
        out.print("Enter the output folder path: ");
        String folderPath = in.nextLine();

        // Make a map of the terms and definitions
        Map<String, String> termMap = new Map1L<>();
        getTermMap(filePath, termMap);

        // Sort terms alphabetically
        String[] sortedTerms = sortTerms(termMap);

        // Generate index.html
        SimpleWriter indexOut = new SimpleWriter1L(folderPath + "/index.html");
        indexOut.println("<html><head><title>Glossary</title></head><body><h1>"
                + "Harith's Glossary</h1><hr><h2>Index</h2> <ul>");

        // Create a bullet point of each term linking to its own page
        for (String term : sortedTerms) {
            indexOut.println("<li> <p><a href=\"" + term + ".html\">" + term
                    + "</a></p> </li>");
        }
        indexOut.println("</ul> </body></html>");
        indexOut.close();

        // Generate the term pages
        for (String term : sortedTerms) {
            SimpleWriter termOut = new SimpleWriter1L(
                    folderPath + "/" + term + ".html");
            outputPage(sortedTerms, term, termMap.value(term), termOut);
            termOut.close();
        }

        out.close();
        in.close();
    }
}
