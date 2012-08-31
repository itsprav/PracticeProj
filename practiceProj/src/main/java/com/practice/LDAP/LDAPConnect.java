package com.practice.LDAP;

import java.util.ArrayList;

public class LDAPConnect {

	public static void main(String[] args) {
		LDAPServerData sdo = new LDAPServerData();
		sdo.setServerURL("ldap://10.10.1.11:389");
		sdo.setSecurityPrincipal("dc=talentica-all,dc=com");
		try {
			ArrayList<LDAPServerData> ldapServers = new ArrayList<LDAPServerData>();
			ldapServers.add(sdo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
