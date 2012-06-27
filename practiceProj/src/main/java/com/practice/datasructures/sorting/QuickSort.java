package com.practice.datasructures.sorting;
/**
 * @author PraveenK
 * @since  Jun 25, 2012
 */
public class QuickSort {
	public static void main(String[] args) {
		//int[] A = {10,45,89,2,25,0,-6,32,65487,385,687413416,95,15};
		int[] A = {10,45,89,2,5,0,-6,32,65487,5,687413416,5,5};
		//int[] A = {10,45,89,2,5};
		/*int[] B = new int[999999999];
		int[] C = new int[999999999];
		Random randomGenerator = new Random();
		for (int i = 0; i < 999999999; i++) {
			B[i]=randomGenerator.nextInt(99999);
		}
		for (int i = 0; i < 999999999; i++) {
			C[i]=i;
		}*/
		quickSort(A, 0, A.length-1);
		for (int i = 0; i < A.length; i++) {
			System.out.println(A[i]);
		}
		//quickSort(B, 0, B.length);
		//quickSort(C, 0, C.length);
	}
	
	static int partition(int[] A, int p, int q){
		if(p<q){
			int pivot = A[q];
			while(p<=q){
				while(A[p]<pivot){
					p++;
				}
					
				while(A[q]>pivot){
					q--;
				}
				if(p<=q){
					swap(A,p,q);
					p++;
					q--;
				}else{
					return p;
				}
			}
		}
		return 0;
	}
	
	static void swap(int[] A, int i, int j){
		int temp = A[i];
		A[i] = A[j];
		A[j]=temp;
	}
	
	static void quickSort(int[] A, int p, int q){
		int pivotElem = partition(A, p, q);
		if(pivotElem>0){
			quickSort(A, p, pivotElem-1);
			quickSort(A, pivotElem, q);			
		}
	}
}
