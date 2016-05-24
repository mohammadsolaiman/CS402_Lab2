package com.mohamad.Test;

import java.math.BigDecimal;

import com.mohamad.BIGINTDEC.Ex_14_21_COMPLEX_NB;
import com.mohamad.BIGINTDEC.Ex_14_6;

public class TestClass {

	public static void main(String[] args) {
		Ex_14_6 ex_14 = new Ex_14_6(1000);
		BigDecimal e = ex_14.RUN();
		
		System.out.println("e = "+e.toString());
		
		Ex_14_21_COMPLEX_NB c1 = new Ex_14_21_COMPLEX_NB(new BigDecimal("2"), new BigDecimal("5"));
		Ex_14_21_COMPLEX_NB c2 = new Ex_14_21_COMPLEX_NB(new BigDecimal("9"), new BigDecimal("3"));
	
		System.out.println(c2.toString());
		
		System.out.println(c1.divide(c2));
		
		System.out.println("INIT: "+c1.sqrt_big(c2.getA()));
		System.out.println("ABS_c1: "+c2.abs());
	}

}
//e = 2.7182818284590452353602875