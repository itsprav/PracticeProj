package com.practice.datasructures.search;


class LinearSearch
{
	public static void main(String[] args)

	{

		int[] a = {1,4,6,7,9,67};
		System.out.println("fsdfasf"+findElement(a,6));
	}
	static int findElement(int[] a ,int ele)
	{
		for(int i=0;i<a.length;i++)
		{
			System.out.println("i"+i);
			if(a[i] == ele)
			{
				return i;
			}
		}
		return -1;
	}
};