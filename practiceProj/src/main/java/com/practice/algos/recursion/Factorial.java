package com.practice.algos.recursion;

public class Factorial {
	
	static int fact(int n){
		if(n!=1){
			return n * fact(n-1);
		}else{
			return 1;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(fact(1));
		System.out.println(fact(5));
		System.out.println(fact(12));
	}

}
