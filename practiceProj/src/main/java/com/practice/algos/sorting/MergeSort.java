package com.practice.algos.sorting;

public class MergeSort 
{
	static int count = 0;
	
	public static void main(String[] args) 
	{
/*		int[] arr = new int[100];

		for (int i = 0; i < arr.length; i++) 
		{
			arr[i] = Random
		}
		*/
		
		int[] arr = {12,1,3,99,120,4,10};
		
		printArray(arr);
		
		mergeSort(arr,0,arr.length);
		
		printArray(arr);
		
		int[] arr1 = {1,2,8,10};
		int[] arr2 = {3,7,9,26};
	}
	
	static void printArray(int[] arr)
	{
		for (int i = 0; i < arr.length; i++) 
		{
			System.out.print("  "+arr[i]);
		}
		System.out.println();
	}
	
	static void mergeSort(int[] arr,int low,int high)
	{
		int temp[] = arr.clone();
		recMergeSort(arr,temp,low,high-1);
	}
	
	static void recMergeSort(int[] arr,int[] temp,int low,int high)
	{
		if(low==high)
			return;
		else
		{
			int middle = (high+low)/2; 
			recMergeSort(arr,temp,low,middle);
			recMergeSort(arr,temp,middle+1,high);
			merge(arr,temp,low,middle+1,high);
		}
	}
	

	
	/**
	 * Merge two sorted array
	 * @author Written by Prav
	 * @since 21st Aug 2010
	 * */
	static void merge(int[] arr,int[] temp1,int lowptr,int highptr,int upperbound)
	{
		int tempArrPtr=0;
		int middleIndex=highptr;
		int lowerIndex = lowptr;
		int higherIndex = upperbound-lowptr+1;

/*
		total 3 pointers 
		temp arr pointer - tempArrPtr
		left array pointer - lowptr
		right array pointer - highptr
*/		
/*		int temp1[] = new int[higherIndex];
		System.out.println("temp--"+(count=count+temp1.length));
		System.out.println("temp1--"+temp1.length);*/
		
		while(lowptr<middleIndex && highptr<=upperbound)
		{
			if(arr[lowptr]>arr[highptr])
				temp1[tempArrPtr++] = arr[lowptr++];
			else
				temp1[tempArrPtr++] = arr[highptr++];
		}
		while(lowptr<middleIndex)
		{
			temp1[tempArrPtr++] = arr[lowptr++];
		}
		while(highptr<=upperbound)
		{
			temp1[tempArrPtr++] = arr[highptr++];
		}
		for (int l = 0; l < higherIndex; l++) 
		{
			arr[lowerIndex+l]=temp1[l];
		}
	}
	
	/* Provided by rose inida
	*/
	public static void mergeSort_srt(int array[],int lo, int n)
	{
	    int low = lo;
	    int high = n;
	    if (low >= high) {
	      return;
	    }

	    int middle = (low + high) / 2;
	    mergeSort_srt(array, low, middle);
	        mergeSort_srt(array, middle + 1, high);
	    int end_low = middle;
	    int start_high = middle + 1;
	    while ((lo <= end_low) && (start_high <= high)) {
	      if (array[low] < array[start_high]) {
	        low++;
	            } else {
	        int Temp = array[start_high];
	        for (int k = start_high- 1; k >= low; k--) {
	          array[k+1] = array[k];
	        }
	                array[low] = Temp;
	                low++;
	                end_low++;
	                start_high++;
	            }
	        }
	}
	
}
