package ua.kiev.univ.algorithm;

import java.math.BigInteger;

import junit.framework.TestCase;

public class EEATest extends TestCase {

    private EEA eea;
    private BigInteger a1, b1;

    public void setUp() {
        a1 = BigInteger.valueOf(120l);
        b1 = BigInteger.valueOf(23l);
        eea = new EEA(a1, b1);
    }

    public void testEEA() {
        assertEquals(eea.getX(), BigInteger.valueOf(-9l));
        assertEquals(eea.getY(), BigInteger.valueOf(47l));
        assertEquals(eea.getGCD(), BigInteger.ONE);
    }
}
