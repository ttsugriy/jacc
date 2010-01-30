package ua.kiev.univ.crypto;

import junit.framework.TestCase;

import java.math.BigInteger;
import java.util.Random;

public class WienerAttackTest extends TestCase {
    public void testWienerAttack() {
        BigInteger n = new BigInteger("9449868410449");
        BigInteger e = new BigInteger("6792605526025");
        BigInteger d = BigInteger.valueOf(569l);
        BigInteger m = new BigInteger(16, new Random());
        assertEquals(m, m.modPow(e,n).modPow(d,n));
        WienerAttack wa = new WienerAttack(n, e);
        assertEquals(d, wa.getDecryptionExponent());
    }
}
