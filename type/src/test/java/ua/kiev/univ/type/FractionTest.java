package ua.kiev.univ.type;

import junit.framework.TestCase;
import java.math.BigInteger;

public class FractionTest extends TestCase {

    private BigInteger nom1, nom2, denom1, denom2;

    private Fraction f, f2;

    public void setUp() {
        nom1 = BigInteger.valueOf(20);
        denom1 = BigInteger.valueOf(10);
        nom2 = BigInteger.valueOf(2);
        denom2 = BigInteger.valueOf(1);
        f = new Fraction(nom1, denom1);
        f2 = new Fraction(1, 2);
    }

    public void testNormalizationOnCreation() {
        assertTrue("Nominator must be normalized", f.getNominator().equals(nom2));
        assertTrue("Denominator must be normalized", f.getDenominator().equals(denom2));
    }

    public void testEquals() {
        assertEquals(f, f);
        assertEquals(f, new Fraction(nom2, denom2));
    }

    public void testInverse() {
        assertEquals(f.getInverse(), f2);
        f2 = new Fraction(1l, 1l);
        assertEquals(f2, f2.getInverse());
    }

    public void testAddition() {
        Fraction res = f.add(f2);
        assertEquals(res.getNominator(), BigInteger.valueOf(5l));
        assertEquals(res.getDenominator(), BigInteger.valueOf(2l));
    }

    public void testSubtraction() {
        Fraction res = f.subtract(f2);
        assertEquals(res.getNominator(), BigInteger.valueOf(3l));
        assertEquals(res.getDenominator(), BigInteger.valueOf(2l));
    }

    public void testMultiplication() {
        Fraction res = f.multiply(f2);
        assertEquals(res.getNominator(), BigInteger.ONE);
        assertEquals(res.getDenominator(), BigInteger.ONE);
    }

    public void testDivision() {
        Fraction res = f.divide(f2);
        assertEquals(res.getNominator(), BigInteger.valueOf(4l));
        assertEquals(res.getDenominator(), BigInteger.valueOf(1l));
    }

}
