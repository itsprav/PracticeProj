package com.practice.algos.search;

class BinarySearch
{
	public static void main(String[] args)

	{

		int[] a = {1,2,3,4,5,6,7,8,9,10};
		System.out.println("fsdfasf"+findElement(a,6));
	}
	static int findElement(int[] a ,int ele)
	{
		int low =0;
		int high = a.length-1;
		while(high>=low)
		{
			int middle = (low+high)/2;
			if(a[middle] == ele)
			{
				return middle;
			}
			else if(a[middle]<ele)
			{
				low = middle+1;
			
			}
			else 
			{
				high = middle-1;
			}
		}
		return -1;
	}
};