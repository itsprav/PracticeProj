package com.practice.algos.GreatestCommonDivisor;

/**
 * @author PraveenK
 * @since  Aug 24, 2012
 */
public class EuclideanGCD {
	
	public static int findGCD(int num1, int num2){
		int m = -1;
		while(num1%num2!=0){
			m = num1%num2;
			num1 = num2;
			num2 = m;
		}
		return m;
	}
	
	public static void main(String[] args) {
		System.out.println(findGCD(48, 18));
		System.out.println(findGCD(434, 966));
		System.out.println(findGCD(18, 48));
		System.out.println(findGCD(6542344, 6546454));
	}

}
