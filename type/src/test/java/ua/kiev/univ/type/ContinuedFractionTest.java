package ua.kiev.univ.type;

import java.util.List;
import java.util.ArrayList;
import java.math.BigInteger;
import junit.framework.TestCase;

public class ContinuedFractionTest extends TestCase {

    private Fraction f1, f2;
    private ContinuedFraction cf1, cf2;

    public void setUp() {
        f1 = new Fraction(BigInteger.valueOf(649l), BigInteger.valueOf(200l));
        f2 = new Fraction(BigInteger.valueOf(585l), BigInteger.valueOf(1000l));
        cf1 = new ContinuedFraction(f1);
        cf2 = new ContinuedFraction(f2);
    }

    public void testExpansion() {
        List expansion = cf1.getExpansionList();
        List res = new ArrayList() {{ add(BigInteger.valueOf(3l));
            add(BigInteger.valueOf(4l)); add(BigInteger.valueOf(12l));
            add(BigInteger.valueOf(4l)); }};
        assertEquals(res, expansion);
        assertEquals(new ContinuedFraction(BigInteger.valueOf(9l), BigInteger.valueOf(4l)).
                getExpansionList(), new ArrayList() {{ add(BigInteger.valueOf(2l));
                    add(BigInteger.valueOf(4l)); }});
    }

    public void testGetConvergent() {
        assertEquals(new Fraction(BigInteger.valueOf(3l)), cf1.getConvergent(1));
        assertEquals(new Fraction(BigInteger.valueOf(3l)).
                add(new Fraction(BigInteger.ONE, BigInteger.valueOf(4l))), 
                cf1.getConvergent(2));
        List exp = new ArrayList() {{ add(BigInteger.valueOf(3l));
            add(BigInteger.valueOf(7l)); add(BigInteger.valueOf(15l)); }};
        cf2 = new ContinuedFraction(exp);
        assertEquals(new Fraction(BigInteger.valueOf(22l), BigInteger.valueOf(7l)),
                cf2.getConvergent(2));
        assertEquals(new Fraction(BigInteger.valueOf(333l), BigInteger.valueOf(106l)),
                cf2.getConvergent(3));

    }

    public void testShrink() {
        List expansion = cf1.getExpansionList();
        assertEquals(f1, new ContinuedFraction(expansion).getRegularFraction());
        final BigInteger bi = BigInteger.valueOf(3l);
        expansion = new ArrayList() {{ add(bi); }};
        assertEquals(new Fraction(bi), new ContinuedFraction(expansion).getRegularFraction());
    }
}
