package com.practice.algos.recursion;

public class Fibonacci {
	
	static void fiboncci(int n1, int n2, int num,int cnt){
		if(num>cnt){
			System.out.println((n1+n2)+" ");
			fiboncci(n2, n1+n2, num, ++cnt);
		}else{
			return;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(0);
		System.out.println(1);
		fiboncci(0, 1,10, 0);
	}

}
