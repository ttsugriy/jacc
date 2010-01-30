package ua.kiev.univ.crypto;

import java.math.BigInteger;
import java.util.Random;
import ua.kiev.univ.type.ContinuedFraction;

/**
 * Wiener attack allows to break RSA
 * based on materials from article at http://naukoved.ru/content/view/1330/45/
 * @author Taras Tsugriy
 **/

public class WienerAttack extends Object {

    /**
     * decryption exponent
     **/
    private BigInteger d = null;

    /**
     * n = p*q
     **/
    private BigInteger n;

    /**
     * e - encryption exponent
     **/
    private BigInteger e;

    /*
     * Constructor 
     **/
    public WienerAttack(BigInteger n, BigInteger e) {
        this.n = n;
        this.e = e;
    }

    /**
     * @return decryption exponent after applying Wiener attack or null in case of failure
     **/
    public BigInteger getDecryptionExponent() {
        if (d != null)
            return d;
        ContinuedFraction cf = new ContinuedFraction(e, n);
        BigInteger p; // our pivot
        BigInteger m; // message to test our assumption
        for (int i = 1; i <= cf.getExpansionList().size(); i++) {
            p = cf.getConvergent(i).getDenominator();
            m = new BigInteger(16, new Random()); //random message
            if (m.modPow(e, n).modPow(p, n).equals(m)) {
                d = p;
                return d;
            }
        }
        return null;
    }

}
