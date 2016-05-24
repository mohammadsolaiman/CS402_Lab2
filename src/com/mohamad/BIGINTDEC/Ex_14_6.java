package com.mohamad.BIGINTDEC;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

/**
 * approximates e using the following series:
 * e = 1 + 1/1! + 1/2! + ... + 1/i!
 * 
 * In order to get better precision, use BigDecimal with 25 digits of precision in
 *	the computation. Write a program that displays the e value for i = 100, 200,
 *	and 1000.
 * 
 * **/
public class Ex_14_6 {

	private int steps;
	private BigDecimal result;
	
	public Ex_14_6(int steps){
		this.steps = steps;
	}
	
	public BigDecimal RUN(){
	    MathContext	mc = new MathContext(26);
		result = new BigDecimal("1",mc);
		
		for(int i = 1; i<= steps ; i++){
			BigDecimal div = new BigDecimal("1", mc) , I =new BigDecimal(Factorial(i).toString(), mc);
			
			div = div.divide(I, mc);
			result = result.add(div, mc);
		}
		
		return result;
	}
	
	public BigInteger Factorial(int x){
		BigInteger f = new BigInteger("1");
		
		for(int i = x; i>0; i--){
			f = f.multiply(new BigInteger(""+i));
		}
		return f;
	}
}
