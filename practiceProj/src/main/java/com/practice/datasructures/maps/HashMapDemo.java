package com.practice.datasructures.maps;


import java.util.HashMap;

public class HashMapDemo {

	static String[] names = { "praveen", "bob", "hhh", "shawn", "scot","shan", "keeth","pavan" };
	private static HashMap counter = new HashMap();
	private static Integer cnt = null;
	public static void main(String args[]) {
		for (int i = 0; i < names.length; i++) {
			cnt = (Integer) (counter.get(new Character(names[i].charAt(0))));
			if (cnt == null) {
				counter.put(new Character(names[i].charAt(0)),new Integer("1"));
			} else {
				counter.put(new Character(names[i].charAt(0)),
 

						new Integer(cnt.intValue() + 1));
			}
		}
		System.out.println("\nnumber of words beginning with each letter is shown below ");
		System.out.println(counter);
	}
}

