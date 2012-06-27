/**
 * 
 */
package com.practice.datasructures.maps;

import java.util.HashMap;
import java.util.Map;

/**
 * @author praveenk
 * @since  Feb 21, 2012
 */
public class MapDataLoadTest {

	public static void main(String[] args) {
		MapDataLoadTest mp = new MapDataLoadTest(); 
		long time = System.currentTimeMillis();
		long heapSize1 = Runtime.getRuntime().totalMemory();
		System.out.println("heapSize: "+heapSize1);
		Map<String,ApplicantData> appMap = new HashMap<String, ApplicantData>(100000,1);
		for (int i = 0; i < 100000; i++) {
			ApplicantData appData = mp.new ApplicantData("asdhdgassaghdljsagh", "asdasdfsadas.lkhas", "ASDLKSAJDKASJHG", "aSDASDKHBAUEHQWKDSAKND", "adasdasd;kjyqwenqw;ledhja", "asdasdasfas;fasj;sfashfas;gsaf");
			appMap.put("adasdsaasd"+i, appData);
		}
		System.out.println("timeTaken: "+(System.currentTimeMillis()-time));
		long heapSize = Runtime.getRuntime().totalMemory();
		long heapMaxSize = Runtime.getRuntime().maxMemory();
		long heapFreeSize = Runtime.getRuntime().freeMemory();
		System.out.println("heapSize: "+heapSize);
		System.out.println("heapMaxSize: "+heapMaxSize);
		System.out.println("heapFreeSize: "+heapFreeSize);
		System.out.println("usedSize: "+(heapSize-heapSize1));
		//System.out.println(appMap);
		
	}
	
	public class ApplicantData {
		private String applicantName;
		private String applicantJoiningDate;
		private String applicantStatus;
		private String applicantStatus1;
		private String applicantStatus2;
		private String applicantStatus3;
		

		/**
		 * @param applicantName
		 * @param applicantJoiningDate
		 * @param applicantStatus
		 * @param applicantStatus1
		 * @param applicantStatus2
		 * @param applicantStatus3
		 */
		public ApplicantData(String applicantName, String applicantJoiningDate,
				String applicantStatus, String applicantStatus1,
				String applicantStatus2, String applicantStatus3) {
			super();
			this.applicantName = applicantName;
			this.applicantJoiningDate = applicantJoiningDate;
			this.applicantStatus = applicantStatus;
			this.applicantStatus1 = applicantStatus1;
			this.applicantStatus2 = applicantStatus2;
			this.applicantStatus3 = applicantStatus3;
		}
		/**
		 * @return the applicantName
		 */
		public String getApplicantName() {
			return applicantName;
		}
		/**
		 * @param applicantName the applicantName to set
		 */
		public void setApplicantName(String applicantName) {
			this.applicantName = applicantName;
		}
		/**
		 * @return the applicantJoiningDate
		 */
		public String getApplicantJoiningDate() {
			return applicantJoiningDate;
		}
		/**
		 * @param applicantJoiningDate the applicantJoiningDate to set
		 */
		public void setApplicantJoiningDate(String applicantJoiningDate) {
			this.applicantJoiningDate = applicantJoiningDate;
		}
		/**
		 * @return the applicantStatus
		 */
		public String getApplicantStatus() {
			return applicantStatus;
		}
		/**
		 * @param applicantStatus the applicantStatus to set
		 */
		public void setApplicantStatus(String applicantStatus) {
			this.applicantStatus = applicantStatus;
		}
		/**
		 * @return the applicantStatus1
		 */
		public String getApplicantStatus1() {
			return applicantStatus1;
		}
		/**
		 * @param applicantStatus1 the applicantStatus1 to set
		 */
		public void setApplicantStatus1(String applicantStatus1) {
			this.applicantStatus1 = applicantStatus1;
		}
		/**
		 * @return the applicantStatus2
		 */
		public String getApplicantStatus2() {
			return applicantStatus2;
		}
		/**
		 * @param applicantStatus2 the applicantStatus2 to set
		 */
		public void setApplicantStatus2(String applicantStatus2) {
			this.applicantStatus2 = applicantStatus2;
		}
		/**
		 * @return the applicantStatus3
		 */
		public String getApplicantStatus3() {
			return applicantStatus3;
		}
		/**
		 * @param applicantStatus3 the applicantStatus3 to set
		 */
		public void setApplicantStatus3(String applicantStatus3) {
			this.applicantStatus3 = applicantStatus3;
		}
		
	}
}
