package ua.kiev.univ.type;

/**
 * This class describes the behaviour of the fraction
 * @author Taras Tsugriy
 **/

import java.math.BigInteger;
import ua.kiev.univ.algorithm.EEA;

public class Fraction extends Object {

    private BigInteger nominator;
    private BigInteger denominator;

    private void normalize() {
        EEA eea = new EEA(nominator, denominator);
        this.nominator = getNominator().divide(eea.getGCD());
        this.denominator = getDenominator().divide(eea.getGCD());
    }

    /**
     * construct fraction from integer
     **/
    public Fraction ( BigInteger nominator ) {
        this(nominator, BigInteger.ONE);
    }

    public Fraction( Fraction fraction ) {
        this(fraction.getNominator(), fraction.getDenominator());
    }

    public Fraction(BigInteger nominator, BigInteger denominator) {
        this.nominator = nominator;
        this.denominator = denominator;
        this.normalize();
    }

    public Fraction(long nominator, long denominator) {
        this(BigInteger.valueOf(nominator), BigInteger.valueOf(denominator));
    }

    public BigInteger getNominator() {
        return this.nominator;
    }

    public BigInteger getDenominator() {
        return this.denominator;
    }


    public Fraction getInverse() {
        return new Fraction(this.getDenominator(),
                this.getNominator());
    }

    public Fraction add (Fraction other) {
        return new Fraction(getNominator().
                multiply(other.getDenominator()).
                add(getDenominator().multiply(other.getNominator())),
                getDenominator().multiply(other.getDenominator()));
    }

    public Fraction subtract (Fraction other) {
        return this.add(new Fraction(
                    BigInteger.ZERO.subtract(other.getNominator()),
                    other.getDenominator()));
    }

    public Fraction multiply ( Fraction other) {
        return new Fraction( this.getNominator().multiply(other.getNominator()),
                this.getDenominator().multiply(other.getDenominator()));
    }

    public Fraction divide ( Fraction other ) {
        return this.multiply( other.getInverse() );
    }

    @Override public boolean equals( Object obj ) {
        if (this == obj)
            return true;
        if ( obj == null || !(obj instanceof Fraction) )
            return false;
        Fraction other = (Fraction) obj;
        return this.getNominator().equals(other.getNominator())
                && this.getDenominator().equals(other.getDenominator());
    }

    @Override public int hashCode() {
        return this.getNominator().hashCode() ^
            this.getDenominator().hashCode() * 13;
    }

    @Override public String toString() {
        if (this.getDenominator() == BigInteger.ONE)
            return this.getNominator().toString();
        else
            return this.getNominator().toString() +
                "/" + this.getDenominator();
    }

}

