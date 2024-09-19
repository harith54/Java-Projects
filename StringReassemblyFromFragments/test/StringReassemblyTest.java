import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class StringReassemblyTest {

    // Combination with no overlap
    @Test
    public void combination1() {
        String str1 = "123";
        String str2 = "456";
        int overlap = StringReassembly.overlap(str1, str2);
        String combo = StringReassembly.combination(str1, str2, overlap);
        String expected = "123456";
        assertEquals(expected, combo);
    }

    // Combination with overlap
    @Test
    public void combination2() {
        String str1 = "1234";
        String str2 = "45";
        int overlap = StringReassembly.overlap(str1, str2);
        String combo = StringReassembly.combination(str1, str2, overlap);
        String expected = "12345";
        assertEquals(expected, combo);
    }

    // Combination with overlap
    @Test
    public void combination3() {
        String str1 = "abcdefg";
        String str2 = "cdefghijk";
        int overlap = StringReassembly.overlap(str1, str2);
        String combo = StringReassembly.combination(str1, str2, overlap);
        String expected = "abcdefghijk";
        assertEquals(expected, combo);
    }

    // Combination with overlap
    @Test
    public void combination4() {
        String str1 = "WowIWentToTheGroceryStore";
        String str2 = "TheGroceryStoreIsCool";
        int overlap = StringReassembly.overlap(str1, str2);
        String combo = StringReassembly.combination(str1, str2, overlap);
        String expected = "WowIWentToTheGroceryStoreIsCool";
        assertEquals(expected, combo);
    }

    // String that should be added
    @Test
    public void addToSetAvoidingSubstrings1() {
        String str = "jkl";
        Set<String> strSet = new Set1L<>();
        strSet.add("abc");
        strSet.add("def");
        strSet.add("ghi");
        StringReassembly.addToSetAvoidingSubstrings(strSet, str);
        Set<String> expectedSet = new Set1L<>();
        expectedSet.add("abc");
        expectedSet.add("def");
        expectedSet.add("ghi");
        expectedSet.add("jkl");
        assertEquals(expectedSet, strSet);
    }

    // String that should be added with some removed from set
    @Test
    public void addToSetAvoidingSubstrings2() {
        String str = "abcdef";
        Set<String> strSet = new Set1L<>();
        strSet.add("abc");
        strSet.add("def");
        strSet.add("ghi");
        StringReassembly.addToSetAvoidingSubstrings(strSet, str);
        Set<String> expectedSet = new Set1L<>();
        expectedSet.add("abcdef");
        expectedSet.add("ghi");
        assertEquals(expectedSet, strSet);
    }

    // String that shouldn't be added
    @Test
    public void addToSetAvoidingSubstrings3() {
        String str = "ab";
        Set<String> strSet = new Set1L<>();
        strSet.add("abc");
        strSet.add("def");
        strSet.add("ghi");
        StringReassembly.addToSetAvoidingSubstrings(strSet, str);
        Set<String> expectedSet = new Set1L<>();
        expectedSet.add("abc");
        expectedSet.add("def");
        expectedSet.add("ghi");
        assertEquals(expectedSet, strSet);
    }

    /*
     * Tests for printWithLineSeparators
     */

    /**
     * tests line separation of a small string
     */
    @Test
    public void testPrintWithLineSeparators1() {
        SimpleWriter out = new SimpleWriter1L("testcase1.txt");
        StringReassembly.printWithLineSeparators("~1", out);
        out.close();
        SimpleReader in = new SimpleReader1L("testcase1.txt");
        String line1 = in.nextLine();
        String line2 = in.nextLine();
        String actual = "";
        String actual2 = "1";
        in.close();
        assertEquals(actual, line1);
        assertEquals(actual2, line2);

    }

    /**
     * tests line separation of a large string
     */
    @Test
    public void testPrintWithLineSeparators2() {
        SimpleWriter out = new SimpleWriter1L("testcase2.txt");

        StringReassembly.printWithLineSeparators("Hello~World~This~Is~A~Test",
                out);
        out.close();

        SimpleReader in = new SimpleReader1L("testcase2.txt");

        String line1 = in.nextLine();
        String line2 = in.nextLine();
        String line3 = in.nextLine();
        String line4 = in.nextLine();
        String line5 = in.nextLine();
        String line6 = in.nextLine();
        in.close();

        assertEquals("Hello", line1);
        assertEquals("World", line2);
        assertEquals("This", line3);
        assertEquals("Is", line4);
        assertEquals("A", line5);
        assertEquals("Test", line6);
    }

    /*
     * Test cases for linesFromInput
     */
    /**
     * tests 2 words from an input file
     */
    @Test
    public void testlinesFromInput1() {
        SimpleWriter out = new SimpleWriter1L("testcase1.txt");
        out.println("Hello");
        out.println("World");
        out.close();
        Set<String> test = new Set1L<>();
        SimpleReader in = new SimpleReader1L("testcase1.txt");
        test.add(StringReassembly.linesFromInput(in));

        Set<String> actual = new Set1L<>();
        actual.add("Hello");
        actual.add("World");
        in.close();
        assertEquals(actual, test);
    }

    /**
     * tests a few lines of input
     */
    @Test
    public void testlinesFromInput2() {
        SimpleWriter out = new SimpleWriter1L("testcase1.txt");
        out.println("Hello");

        out.println("My");
        out.println("name is");
        out.println("Harith");
        out.close();
        Set<String> test = new Set1L<>();
        SimpleReader in = new SimpleReader1L("testcase1.txt");
        test.add(StringReassembly.linesFromInput(in));
        Set<String> actual = new Set1L<>();
        actual.add("Hello");

        actual.add("My");
        actual.add("name is");
        actual.add("Harith");
        in.close();
        assertEquals(actual, test);
    }

    /**
     * test multiple lines of input
     */
    @Test
    public void testlinesFromInput3() {
        SimpleWriter out = new SimpleWriter1L("testcase1.txt");
        out.println("This is the first line test");
        out.println("This is the second line test");
        out.println("This is the third line test");
        out.println("This is the fourth line test");
        out.println("This is the fifth line test");
        out.close();
        Set<String> test = new Set1L<>();
        SimpleReader in = new SimpleReader1L("testcase1.txt");
        test.add(StringReassembly.linesFromInput(in));

        Set<String> actual = new Set1L<>();
        actual.add("This is the first line test");
        actual.add("This is the second line test");
        actual.add("This is the third line test");
        actual.add("This is the fourth line test");
        actual.add("This is the fifth line test");
        in.close();
        assertEquals(actual, test);
    }

}
