import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

public class NNCalcModel1Test {

    /**
     * Test with 0
     */
    @Test
    public void top1() {
        NNCalcModel1 model = new NNCalcModel1();
        NaturalNumber expected = new NaturalNumber2();
        assertEquals(expected, model.top());
    }

    /**
     * Test with small number
     */
    @Test
    public void top2() {
        NNCalcModel1 model = new NNCalcModel1();
        model.top().setFromInt(6);
        NaturalNumber expected = new NaturalNumber2();
        expected.setFromInt(6);
        assertEquals(expected, model.top());
    }

    /**
     * Test with large number
     */
    @Test
    public void top3() {
        NNCalcModel1 model = new NNCalcModel1();
        model.top().setFromInt(1453890);
        NaturalNumber expected = new NaturalNumber2();
        expected.setFromInt(1453890);
        assertEquals(expected, model.top());
    }

    /**
     * Test with 0
     */
    @Test
    public void bottom1() {
        NNCalcModel1 model = new NNCalcModel1();
        NaturalNumber expected = new NaturalNumber2();
        assertEquals(expected, model.bottom());
    }

    /**
     * Test with small number
     */
    @Test
    public void bottom2() {
        NNCalcModel1 model = new NNCalcModel1();
        model.bottom().setFromInt(9);
        NaturalNumber expected = new NaturalNumber2();
        expected.setFromInt(9);
        assertEquals(expected, model.bottom());
    }

    /**
     * Test with large number
     */
    @Test
    public void bottom3() {
        NNCalcModel1 model = new NNCalcModel1();
        model.bottom().setFromInt(9432311);
        NaturalNumber expected = new NaturalNumber2();
        expected.setFromInt(9432311);
        assertEquals(expected, model.bottom());
    }

}
