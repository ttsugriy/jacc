package ua.kiev.univ.type;

import java.math.BigInteger;
import java.util.List;
import java.util.ArrayList;

/**
 * Class allows manipulations with continued fractions
 * Continued fractions look something like this [3; 2, 3, 4],
 * which describes fraction 1/(3 + 1/(2 + 1/(3 + 1/4)))
 * @author Taras Tsugriy
 **/

public class ContinuedFraction extends Object {

    /**
     * Stores the fraction in Fraction object
     **/
    private Fraction fraction;
    /**
     * Stores the fraction in expansion list
     **/
    private List<BigInteger> continuedFraction;

    /**
     * Constructor from Fraction
     **/
    public ContinuedFraction(Fraction fraction) {
        this.fraction = fraction;
        this.expand();
    }

    /**
     * construct continued fraction from two big integers
     **/

    public ContinuedFraction(BigInteger nom, BigInteger denom) {
        this(new Fraction(nom, denom));
    }

    /**
     * construct continued fraction from expansion list
     **/
    public ContinuedFraction(List<BigInteger> list) {
        this.continuedFraction = list;
        shrink();
    }

    /**
     * expands regular fraction into continued
     **/
    private void expand() {
        continuedFraction = new ArrayList();
        if (fraction.getDenominator() == BigInteger.ONE) 
            continuedFraction.add( fraction.getNominator() );
        else if (fraction.getNominator() == BigInteger.ONE) {
            continuedFraction.add( BigInteger.ZERO );
            continuedFraction.add( fraction.getDenominator() );
        } else {
            Fraction x = new Fraction(fraction);
            BigInteger quotient, remainder;
            while (x.getDenominator().compareTo(BigInteger.ONE) > 0) {
                quotient = x.getNominator().divide(x.getDenominator());
                remainder = x.getNominator().remainder(x.getDenominator());
                continuedFraction.add( quotient );
                x = new Fraction(x.getDenominator(), remainder);
                if (x.getDenominator().equals(BigInteger.ONE))
                    continuedFraction.add( x.getNominator() );
            }
        }
    }

    /**
     * shrink continued fraction to regular
     **/
    private void shrink() {           
        this.fraction = getConvergent(continuedFraction.size());
    }

    /**
     * Get ith convergent for continuedFraction
     * @param ith the number, which defines the approximation
     * @return ith convergent
     **/
    public Fraction getConvergent(int ith) {
        assert(ith <= continuedFraction.size());
        if (continuedFraction.size() == 1 || ith == 1) {
            return new Fraction(continuedFraction.get(0));
        }
        Fraction sum = null;
        Fraction f;
        BigInteger a;
        for (int i = ith - 1; i >= 1; i--) {
            a = continuedFraction.get(i - 1);
            if (sum == null) {
                Fraction f1 = new Fraction(a);
                Fraction f2 = new Fraction( BigInteger.ONE, continuedFraction.get(i) );
                sum = f1.add(f2);
            } else
                sum = sum.getInverse().add(new Fraction(a));
        }
        return sum;
    }

    public Fraction getRegularFraction () {
        return fraction;
    }

    public List<? extends BigInteger> getExpansionList() {
        return continuedFraction;
    }
}
