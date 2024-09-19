import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * @author Harith Madani
 *
 */
public class CryptoUtilitiesTest {

    /*
     * Tests of reduceToGCD
     */

    /**
     * Test with 0s
     */
    @Test
    public void testReduceToGCD_0_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(0);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /**
     * Test with ordinary numbers
     */
    @Test
    public void testReduceToGCD_30_21() {
        NaturalNumber n = new NaturalNumber2(30);
        NaturalNumber nExpected = new NaturalNumber2(3);
        NaturalNumber m = new NaturalNumber2(21);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /*
     * Tests of isEven
     */

    /**
     * Test with 0 (Even)
     */
    @Test
    public void testIsEven_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    /**
     * Test with odd number
     */
    @Test
    public void testIsEven_1() {
        NaturalNumber n = new NaturalNumber2(1);
        NaturalNumber nExpected = new NaturalNumber2(1);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    /**
     * Test with odd number
     */
    @Test
    public void testIsEven_largeNumber() {
        NaturalNumber n = new NaturalNumber2(12333312);
        NaturalNumber nExpected = new NaturalNumber2(12333312);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    /*
     * Tests of powerMod
     */

    /**
     * Test 0s
     */
    @Test
    public void testPowerMod_0_0_2() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(0);
        NaturalNumber pExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(2);
        NaturalNumber mExpected = new NaturalNumber2(2);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    /**
     * Test ordinary numbers
     */
    @Test
    public void testPowerMod_17_18_19() {
        NaturalNumber n = new NaturalNumber2(17);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(18);
        NaturalNumber pExpected = new NaturalNumber2(18);
        NaturalNumber m = new NaturalNumber2(19);
        NaturalNumber mExpected = new NaturalNumber2(19);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    /*
     * Tests of isWitnessToCompositeness
     */

    /**
     * Test for true case
     */
    @Test
    public void isWitnessTest_3_232() {
        NaturalNumber w = new NaturalNumber2(3);
        NaturalNumber n = new NaturalNumber2(232);
        NaturalNumber wExpected = new NaturalNumber2(w);
        NaturalNumber nExpected = new NaturalNumber2(n);
        boolean returned = CryptoUtilities.isWitnessToCompositeness(w, n);
        boolean answer = true;
        assertEquals(wExpected, w);
        assertEquals(nExpected, n);
        assertEquals(returned, answer);
    }

    /**
     * Test for false case
     */
    @Test
    public void isWitnessTest_42_321331() {
        NaturalNumber w = new NaturalNumber2(42);
        NaturalNumber n = new NaturalNumber2(321331);
        NaturalNumber wExpected = new NaturalNumber2(w);
        NaturalNumber nExpected = new NaturalNumber2(n);
        boolean returned = CryptoUtilities.isWitnessToCompositeness(w, n);
        boolean answer = false;
        assertEquals(wExpected, w);
        assertEquals(nExpected, n);
        assertEquals(returned, answer);
    }

    /**
     * Test with big number.
     */
    @Test
    public void isWitnessTest_23123_2312344() {
        NaturalNumber w = new NaturalNumber2(23123);
        NaturalNumber n = new NaturalNumber2(2312344);

        NaturalNumber wExpected = new NaturalNumber2(w);
        NaturalNumber nExpected = new NaturalNumber2(n);

        boolean returned = CryptoUtilities.isWitnessToCompositeness(w, n);
        boolean answer = true;
        assertEquals(wExpected, w);
        assertEquals(nExpected, n);
        assertEquals(returned, answer);
    }

    /**
     * Test for false case
     */
    @Test
    public void isPrime2Test_2312344() {
        NaturalNumber n = new NaturalNumber2(2312344);
        NaturalNumber nExpected = new NaturalNumber2(n);
        boolean returned = CryptoUtilities.isPrime2(n);
        boolean answer = false;
        assertEquals(nExpected, n);
        assertEquals(returned, answer);
    }

    /**
     * Test false case with a large number
     */
    @Test
    public void isPrime2Test_largeNonPrimeNumber() {
        NaturalNumber n = new NaturalNumber2("233142132133321");
        NaturalNumber nExpected = new NaturalNumber2(n);
        boolean returned = CryptoUtilities.isPrime2(n);
        boolean answer = false;
        assertEquals(nExpected, n);
        assertEquals(returned, answer);
    }

    /**
     * Test ordinary prime number
     */
    @Test
    public void isPrime2Test_7() {
        NaturalNumber n = new NaturalNumber2("7");
        NaturalNumber nExpected = new NaturalNumber2(n);
        boolean returned = CryptoUtilities.isPrime2(n);
        boolean answer = true;
        assertEquals(nExpected, n);
        assertEquals(returned, answer);
    }

    /**
     * Test large prime number
     */
    @Test
    public void isPrime2Test_largePrime() {
        NaturalNumber n = new NaturalNumber2("3411343444313213237");
        NaturalNumber nExpected = new NaturalNumber2(n);
        boolean returned = CryptoUtilities.isPrime2(n);
        boolean answer = true;
        assertEquals(nExpected, n);
        assertEquals(returned, answer);
    }

    /**
     * Test with ordinary number
     */
    @Test
    public void generateNextPrime_24() {
        NaturalNumber n = new NaturalNumber2(24);
        CryptoUtilities.generateNextLikelyPrime(n);
        NaturalNumber nExpected = new NaturalNumber2(29);
        assertEquals(nExpected, n);
    }

    /**
     * Test with large number
     */
    @Test
    public void generateNextPrime_largeNumber() {
        NaturalNumber n = new NaturalNumber2(23121322);
        CryptoUtilities.generateNextLikelyPrime(n);
        NaturalNumber nextNum = new NaturalNumber2(23121331);
        assertEquals(nextNum, n);
    }

}
