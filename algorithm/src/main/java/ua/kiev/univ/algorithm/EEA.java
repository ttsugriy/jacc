package ua.kiev.univ.algorithm;

import java.math.BigInteger;

public class EEA extends Object {
	
	private BigInteger _a;
	private BigInteger _b;
	private BigInteger _x;
	private BigInteger _y;
	private BigInteger _gcd;
	private boolean processed = false;

	public EEA (BigInteger a, BigInteger b) {
		this._a = a;
		this._b = b;
	}

	public void process() {
		BigInteger x = BigInteger.ZERO;
		BigInteger y = BigInteger.ONE;
		BigInteger u = BigInteger.ONE;
		BigInteger v = BigInteger.ZERO;
		BigInteger m, n, a, b, q, r;
		n = m = r = BigInteger.ZERO;

		for (a = _a, b = _b;
			       !a.equals(BigInteger.ZERO);
			       b = a, a = r, x = u, y = v, u = m, v = n) {
			q = b.divide(a);
			r = b.remainder(a);

			m = x.subtract(u.multiply(q));
			n = y.subtract(v.multiply(q));
		}

		_x = x;
		_y = y;

		_gcd = b;
		processed = true;
	}

	public BigInteger getX() {
		if (!processed)
			process();
		return this._x;
	}

	public BigInteger getY() {
		if (!processed)
			process();
		return this._y;
	}

	public BigInteger getGCD() {
		if (!processed)
			process();
		return this._gcd;
	}
}
