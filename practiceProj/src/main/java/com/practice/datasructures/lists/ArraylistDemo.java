package com.practice.datasructures.lists;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Collections;
 

import java.util.Random;


public class ArraylistDemo {

	public static void main(String[] args) {
		//        ArrayList Creation
		List<Integer> arraylistA = new ArrayList<Integer>();
		List<String> arraylistB = new ArrayList<String>();
		//        Adding elements to the ArrayList
		for (int i = 0; i < 5; i++) {
			arraylistA.add(new Integer(i));
		}
		arraylistB.add("beginner");
		arraylistB.add("java");
		arraylistB.add("tutorial");
		arraylistB.add(".");
		arraylistB.add("com");
		arraylistB.add("java");
		arraylistB.add("site");
		System.out.println("before remove"+arraylistB);
		arraylistB.remove("tutorial");
		System.out.println("after remove"+arraylistB);
		//      Iterating through the ArrayList to display the Contents.
		Iterator i1 = arraylistA.iterator();
		System.out.print("ArrayList arraylistA --> ");
		while (i1.hasNext()) {
			System.out.print(i1.next() + " , ");
		}
		System.out.println();
		System.out.print("ArrayList arraylistA --> ");
		for (int j = 0; j < arraylistA.size(); j++) {
			System.out.print(arraylistA.get(j) + " , ");
		}
		System.out.println();
		Iterator i2 = arraylistB.iterator();
		System.out.println("ArrayList arraylistB --> ");
		while (i2.hasNext()) {
			System.out.print(i2.next() + " , ");
		}
		System.out.println();
		System.out.println();
		System.out
				.println("Using ListIterator to retrieve ArrayList Elements");
		System.out.println();
		ListIterator li1 = arraylistA.listIterator();
		//       next(), hasPrevious(), hasNext(), hasNext() nextIndex() can be used with a
		//        ListIterator interface implementation
		System.out.println("ArrayList arraylistA --> ");
		while (li1.hasNext()) {
			System.out.print(li1.next() + " , ");
		}
		System.out.println();
		//        Searching for an element in the ArrayList
		
		int index = arraylistA.indexOf(6);
		System.out.println("'6' was found at : " + index);
		//System.out.println("'java' was found at : " + index);
		int lastIndex = arraylistB.lastIndexOf("java");
		System.out.println("'java' was found at : " + lastIndex
				+ " from the last");
		System.out.println();
		//        Getting the subList from the original List
		List subList = arraylistA.subList(3, arraylistA.size());
		System.out.println("New Sub-List(arraylistA) from index 3 to "
				+ arraylistA.size() + ": " + subList);
		System.out.println();
		//        Sort an ArrayList
		System.out.print("Sorted ArrayList arraylistA --> ");
		Collections.sort(arraylistB);
		System.out.print(arraylistB);
		System.out.println();
		//      Reversing an ArrayList
		System.out.print("Reversed ArrayList arraylistA --> ");
		Collections.reverse(arraylistA);
		System.out.println(arraylistA);
		System.out.println();
		//		Checking emptyness of an ArrayList
		System.out.println("Is arraylistA empty?   "
				+ arraylistA.isEmpty());
		System.out.println();
		//        Checking for Equality of ArrayLists
		ArrayList arraylistC = new ArrayList(arraylistA);
		System.out.println("arraylistA.equals(arraylistC)? "
				+ arraylistA.equals(arraylistC));
		System.out.println();
		//      Shuffling the elements of an ArrayList in Random Order
		Collections.shuffle(arraylistA, new Random());
		System.out
				.print("ArrayList arraylistA after shuffling its elements--> ");
		i1 = arraylistA.iterator();
		while (i1.hasNext()) {
			System.out.print(i1.next() + " , ");
		}
		System.out.println();
		System.out.println();
		//        Converting an ArrayList to an Array
		Object[] array = arraylistA.toArray();
		for (int i = 0; i < array.length; i++) {
			System.out.println("Array Element [" + i + "] = " + array[i]);
		}
		System.out.println();
		//        Clearing ArrayList Elements
		arraylistA.clear();
		System.out.println("arraylistA after clearing  : " + arraylistA);
		System.out.println();
		
		
		
		String str="praveen\"\"";
		System.out.println("str---->"+str);
		if(str.indexOf("\"")!=-1)
		{
			str=str.replaceAll("\"", "\\\\\"");
			System.out.println("str0---->"+str);
		}
		System.out.println("str1---->"+str);
		char quote='"';
		str="praveen\"";
		//str = dequote(str,quote);
		System.out.println("str2---->"+str);
		DecimalFormat		dF	= new	DecimalFormat("#,##0.0;(#,##0.0)");	
		float temp =  (new Float(-56859280354512259.28)).floatValue();
		double ttt = 5389307554086368644.4354;
		BigDecimal bg = new BigDecimal(5389307554086368644.4354);
		String str1 = ""+temp;
		System.out.println("max dbl"+Double.MAX_VALUE);
		System.out.println("chck double--->"+str1);
		System.out.println("chck double--->"+dF.format(Double.parseDouble(str1)));
		System.out.println("chck double--->"+temp);
		System.out.println("chck double--->"+ttt+"----"+bg);
		processDataForWeeks(7);
	}
	public static String dequote(String str,
		    char quote) 
		{
		        // Is there anything to dequote?
		        if (str == null)
		        {
		            return null;
		        }
		        return dequote(str, 0, str.length(), quote);
		}
	public static String dequote(String str,
		    int begin,
		    int end,
		    char quote) 
		{
		        // Is there anything to dequote?
		        if (begin == end)
		        {
		            return "";
		        }
		        int end_ = str.indexOf(quote, begin);
		        // If no quotes, return the original string
		        // and save StringBuffer allocation/char copying
		        if (end_ <  0)
		        {
		            return str.substring(begin, end);
		        }
		        StringBuffer sb     = new StringBuffer(end - begin);
		        int          begin_ = begin; // need begin later
		        for (; (end_  >= 0) && (end_ <  end);
		            end_ = str.indexOf(quote, begin_ = end_ + 2))
		        {
		            if (((end_ + 1)  >= end) || (str.charAt(end_ + 1) != quote))
		            {
		                throw new IllegalArgumentException(
		                    "Internal quote not doubled in string '"
		                    + str.substring(begin, end) + "'");
		            }
		            sb.append(substring(str, begin_, end_)).append(quote);
		        }
		        return sb.append(substring(str, begin_, end)).toString();
		}
	public static String substring(String str,
		    int begin,
		    int end) 
		{
		        if (begin == end)
		        {
		            return "";
		        }
		        return str.substring(begin, end);
		}
	
	
	 public static void processDataForWeeks(int noOfDays)
	   {
		 WeekGenUtil WGU=new WeekGenUtil();
		 SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		   SimpleDateFormat	odf= new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat	mdf= new SimpleDateFormat("yyyyMMdd");
			Integer maxWeekNo = 317;
			//SttSummaryStatusDB sttSummaryStatusDB=new SttSummaryStatusDB(dbConn);
		//	HashMap hStoreWeekMap=sttSummaryStatusDB.getMaxWkNosForAllStores();
		//	System.out.println("hStoreWeekMap"+hStoreWeekMap);
			//Iterator storeIt=hStoreWeekMap.keySet().iterator();
			int endDateFrm1=0;
			//while (storeIt.hasNext())
			//{
		//		Integer store=(Integer)storeIt.next();
			//	System.out.println("store"+store);
				//Integer maxWeekNo=(Integer)hStoreWeekMap.get(store);
				 Calendar cal = Calendar.getInstance();
				 cal.clear();
				 cal.setTime(WGU.getStDate(maxWeekNo.intValue()));
				 if (noOfDays>7)
				 cal.add(Calendar.DATE,7-(cal.get(Calendar.DAY_OF_WEEK))+7);
				 else 
					 cal.add(Calendar.DATE,7-(cal.get(Calendar.DAY_OF_WEEK)));	 
				 try {
					System.out.println("!!this ??"+Integer.parseInt(mdf.format(odf.parse(odf.format(cal.getTime())))));
					endDateFrm1=Integer.parseInt(mdf.format(odf.parse(odf.format(cal.getTime()))));
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("1st time--"+endDateFrm1);
				 cal.add(Calendar.DATE,noOfDays);
				 List toWkNolist=new ArrayList();
					try {
						int endDateFrm = Integer.parseInt(mdf.format(odf.parse(odf.format(cal.getTime()))));
						System.out.println("!!!!! !!!!!"+endDateFrm);
						 Calendar cal1 = Calendar.getInstance();
						 int currDate=Integer.parseInt(mdf.format(odf.parse(odf.format(cal1.getTime()))));
						 if (currDate>=endDateFrm)
						 {
							 Long diff=new Long(0);
							 if (currDate>endDateFrm)
							 {
								  diff=new Long((cal1.getTimeInMillis()-cal.getTimeInMillis())/(24*60*60*1000));
							 }
							 System.out.println("difff!!!!"+diff);
							 //noOfDays=noOfDays+diff.intValue();
							 int noOfWeeks=(noOfDays+diff.intValue())/7;
							 System.out.println("noOfWeeks"+noOfWeeks+noOfDays);
							 if (((noOfDays/7)==0)&&noOfDays%7>0)
							 {
								 noOfWeeks=noOfWeeks+1;
							 }
							 Calendar caltemp = Calendar.getInstance(); 
							 if(caltemp.get(Calendar.DAY_OF_WEEK)<4)
							 {
								 caltemp.add(Calendar.DATE,-((caltemp.get(Calendar.DAY_OF_WEEK))+7));
							 }
							 else
							 {
								 caltemp.add(Calendar.DATE,-(caltemp.get(Calendar.DAY_OF_WEEK)));
							 }
							 int tempdt = Integer.parseInt(mdf.format(odf.parse(odf.format(caltemp.getTime()))));
							 System.out.println("caltemp----"+Integer.parseInt(mdf.format(odf.parse(odf.format(caltemp.getTime())))));
							 Long diff2 = new Long(0);
							 System.out.println("diff2---"+diff2);
							 System.out.println("noOfWeeks"+noOfWeeks+noOfDays);
							 int wkno=0;
							 cal1.add(Calendar.DATE,-noOfDays);
							 int currDateGap=Integer.parseInt(mdf.format(odf.parse(odf.format(cal1.getTime()))));
							 System.out.println("currDateGap!!!"+currDateGap);
							 if (noOfWeeks>0)
							 {	 
								
								 while (currDateGap>=endDateFrm1)
								 {	 
									 toWkNolist.add(new Integer(maxWeekNo.intValue()+wkno+1));
					  				 Calendar cal2 = Calendar.getInstance();
										cal2.clear();
										cal2.setTime(odf.parse(odf.format(mdf.parse(""+endDateFrm1))));
										 cal2.add(Calendar.DATE,7);
								//		cal2.add(Calendar.DATE,noOfDays);
										endDateFrm1 = Integer.parseInt(mdf.format(odf.parse(odf.format(cal2.getTime()))));
										if(endDateFrm1>tempdt)
											break;
										
										System.out.println("endDateFrm"+endDateFrm1+" "+(maxWeekNo.intValue()+wkno+1)+"toWkNolist---"+toWkNolist);
										wkno++;
								 }
							 }
						
							
							 
						 }
						 else
						 {
							 System.out.println("Returning from here since the "+noOfDays+" days are not complete since the last data load");
						 }
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
	   }
}

