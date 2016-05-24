package com.mohamad.BIGINTDEC;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

import javax.naming.spi.DirStateFactory.Result;

public class Ex_14_21_COMPLEX_NB {

	private BigDecimal a, b;
	private MathContext MC;

	public Ex_14_21_COMPLEX_NB() {
		a = new BigDecimal("0");
		b = new BigDecimal("0");
		MC = new MathContext(10);
	}

	public Ex_14_21_COMPLEX_NB(BigDecimal a, BigDecimal b) {
		this.a = a;
		this.b = b;
		MC = new MathContext(10);
	}

	public Ex_14_21_COMPLEX_NB(BigDecimal a, BigDecimal b, MathContext MC) {
		this.a = a;
		this.b = b;
		this.MC = MC;
	}

	public BigDecimal getA() {
		return a;
	}

	public void setA(BigDecimal a) {
		this.a = a;
	}

	public BigDecimal getB() {
		return b;
	}

	public void setB(BigDecimal b) {
		this.b = b;
	}

	@Override
	public String toString() {

		if (b.signum() == -1)
			return "" + a + " - " + b.abs() + "i";

		return "" + a + " + " + b + "i";
	}

	public MathContext getMC() {
		return MC;
	}

	public void setMC(MathContext mC) {
		MC = mC;
	}

	public Ex_14_21_COMPLEX_NB add(Ex_14_21_COMPLEX_NB C) {
		Ex_14_21_COMPLEX_NB result = new Ex_14_21_COMPLEX_NB();
		result.setMC(MC);
		result.a = a.add(C.a, MC);
		result.b = b.add(C.b, MC);
		return result;
	}

	public Ex_14_21_COMPLEX_NB subtract(Ex_14_21_COMPLEX_NB C) {
		Ex_14_21_COMPLEX_NB result = new Ex_14_21_COMPLEX_NB();
		result.setMC(MC);
		result.a = a.subtract(C.a, MC);
		result.b = b.subtract(C.b, MC);
		return result;
	}

	public Ex_14_21_COMPLEX_NB divide(Ex_14_21_COMPLEX_NB C) throws ArithmeticException{
		Ex_14_21_COMPLEX_NB result = new Ex_14_21_COMPLEX_NB();
		result.setMC(MC);

		// (a + bi)/(c + di) = (ac + bd)/(c^2 + d^2) + (bc - ad)i/(c^2 + d^2)
		result.a = new BigDecimal(
				((a.multiply(C.a, MC).add(b.multiply(C.b, MC))).divide((C.a.pow(2, MC)).add(C.b.pow(2, MC), MC), MC))
						.toString());
		result.b = new BigDecimal((b.multiply(C.a, MC).subtract(a.multiply(C.b, MC)))
				.divide((C.a.pow(2, MC)).add(C.b.pow(2, MC), MC), MC).toString());
		return result;
	}

	public Ex_14_21_COMPLEX_NB multiply(Ex_14_21_COMPLEX_NB C) {
		Ex_14_21_COMPLEX_NB result = new Ex_14_21_COMPLEX_NB();
		result.setMC(MC);

		// (a + bi)*(c + di) = (ac - bd) + (bc + ad)i
		result.a = new BigDecimal((a.multiply(C.a, MC).subtract(b.multiply(C.b, MC))).toString());
		result.b = new BigDecimal((b.multiply(C.a, MC).add(a.multiply(C.b, MC))).toString());
		return result;
	}

	public BigDecimal abs() {
		BigDecimal ABS = new BigDecimal("0");
		if(a.compareTo(ABS) == 0 && b.compareTo(ABS) == 0)
			return ABS;
		
		ABS = sqrt_big(a.pow(2, MC).add(b.pow(2, MC)));
		return ABS;
	}

	public BigDecimal sqrt_big(BigDecimal a) {

		/**
		 * Newton-Rapson-method-for-finding-a-square-root
		 * 
		 * let a the number that we will find sqrt for it
		 * 
		 * 1- init X0 as close as sqrt 2- f(x) = x^2 - a 3- f'(x)= 2x 4- the
		 * goal is to find x that minimize f(x) 5- the next step X1 can found
		 * by: -(x0^2 - a) = 2x0 ( x1 - x0) so 2x0 x1 =2x0^2 - x0^2 + a = x0^2
		 * +a ==> x1 = (x0^2 + a) / 2x0
		 * 
		 * 6- x(i+1) = (x(i)^2 - a ) / 2 x(i) = x(i) - f(x(i))/f'(x(i))
		 * 
		 **/
		BigDecimal X_i = newton_init(a);
		BigDecimal error = new BigDecimal(a.subtract(X_i.pow(2)).abs().toString());
		
		while(error.doubleValue() > 0.000000000000000005){
			BigDecimal F_Xi = X_i.pow(2).subtract(a);
			BigDecimal dF_Xi = X_i.multiply(BigDecimal.valueOf(2));
			
			X_i = X_i.subtract((F_Xi.divide(dF_Xi, MC)));
			error = a.subtract(X_i.pow(2)).abs();
		}
		return X_i;
	}

	public BigDecimal newton_init(BigDecimal a) {

		BigDecimal init_num = new BigDecimal(a.divide(BigDecimal.valueOf(2), MC).toString());
		BigDecimal init_num_min_2 = new BigDecimal(init_num.subtract(BigDecimal.valueOf(2)).toString());

		boolean pow_num_less_a = init_num.pow(2).compareTo(a) == -1;
		boolean pow_num_min2_less_a = init_num_min_2.pow(2).compareTo(a) == -1;

		while (pow_num_less_a == pow_num_min2_less_a) {

			if (pow_num_less_a) {
				init_num = new BigDecimal((init_num.add(BigDecimal.valueOf(1))).toString());
				init_num_min_2 = new BigDecimal((init_num_min_2.add(BigDecimal.valueOf(1))).toString());

			} else {
				init_num = new BigDecimal((init_num.subtract(BigDecimal.valueOf(1))).toString());
				init_num_min_2 = new BigDecimal((init_num_min_2.subtract(BigDecimal.valueOf(1))).toString());
			}

			pow_num_less_a = init_num.pow(2).compareTo(a) == -1;
			pow_num_min2_less_a = init_num_min_2.pow(2).compareTo(a) == -1;
		}
		
		return init_num;
	}
}
