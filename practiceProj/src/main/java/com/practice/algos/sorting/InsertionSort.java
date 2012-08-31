package com.practice.algos.sorting;

public class InsertionSort{
	  public static void main(String a[]){
	    int i;
	    int array[] = {12,9,4,99,120,1,3,10};

	    System.out.println("Values Before the sort:\n");    
	    
	    printarray(array);
	    
	    insertion_srt(array, array.length);        
	    
	    System.out.print("Values after the sort:\n");    
	    
	    printarray(array);
	    
	  }

	  public static void insertion_srt(int array[], int n)
	  {
	    for (int i = 1; i < n; i++)
	    {
	      int j = i;
	      int B = array[i];
	      while ((j > 0) && (array[j-1] > B)){
	        array[j] = array[j-1];
	        j--;
	      }
	      array[j] = B;
	    }
	  }
	  
	  public static void printarray(int[] array) 
	  {
		  for(int i = 0; i < array.length; i++)
			    System.out.print( array[i]+"  ");
		  
		  System.out.println();
	  }
}