package com.practice.LDAP;

import java.util.Hashtable;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.Name;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

public class TestLDAP {

	public static void main(String[] args) {
		
		TestLDAP lq = new TestLDAP();
		
		LDAPServerData serverDO = new LDAPServerData();
		serverDO.setServerURL("ldap://172.19.1.11:389");
		serverDO.setSecurityPrincipal("dc=talentica-all");
			try {
				LdapContext ctx = lq.getInitialLdapContext(serverDO, "praveenk", "Monday01");
				
				Attributes matchAttrs = new BasicAttributes(true);
				//matchAttrs.put(new BasicAttribute("uid", "praveenk"));
				matchAttrs.put(new BasicAttribute("CN","Praveen Kumar"));
				NamingEnumeration results = ctx.search("CN=Users,DC=talentica-all,DC=com",matchAttrs);

				System.out.println(results);
				
				 while (results.hasMore()) {
					 System.out.println("hello");
					       SearchResult sr = (SearchResult)results.next();
					  printAttributes(sr.getName(), sr.getAttributes()) ; 
					  }
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	 private  static void printAttributes(String entry, Attributes attrs) {
		    System.out.println("entry: " + entry);
		     System.out.println("    " + attrs) ; 
		   }
	
	private LdapContext getInitialLdapContext(LDAPServerData serverDO, String userName, String password) throws NamingException {
		Hashtable<String, String> env = getLDAPEnv(serverDO, userName, password);
		LdapContext ctx = null;
		try {
			ctx = new InitialLdapContext(env, null);
		} catch (AuthenticationException e) {
			String securityPrincipal = getModifiedSecurityPrincipal(serverDO.getSecurityPrincipal());
			env.put(Context.SECURITY_PRINCIPAL, userName + "@" + securityPrincipal);
			ctx = new InitialLdapContext(env, null);
		}
		return ctx;
	}
	private Hashtable<String, String> getLDAPEnv(LDAPServerData serverDO, String userName, String password) {
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, LDAPConstants.INITIAL_CONTEXT_FACTORY);
		env.put(Context.PROVIDER_URL, serverDO.getServerURL());
		// set security credentials, note using simple cleartext authentication
		String adminName = "CN=" + userName + ",CN=Users";
		//if (!Utils.isBlankOrNull(serverDO.getSecurityPrincipal())) {
			adminName += "," + serverDO.getSecurityPrincipal();
		//}
		env.put(Context.SECURITY_AUTHENTICATION, LDAPConstants.SECURITY_AUTHENTICATION);
		env.put(Context.SECURITY_PRINCIPAL, adminName);
		env.put(Context.SECURITY_CREDENTIALS, password);
		return env;
	}
	
	private String getModifiedSecurityPrincipal(String securityPrincipal) {
		String userPrinicipalName = "";
		try {
			int index = 0;
			while (index <= securityPrincipal.length()) {
				index = securityPrincipal.indexOf("=", index);
				if (index > 0) {
					int beginIndex = index + 1;
					index = securityPrincipal.indexOf(",", index);
					if (index == -1) {
						index = securityPrincipal.length();
					}
					int lastIndex = index;
					userPrinicipalName = userPrinicipalName + (userPrinicipalName.length() > 0 ? "." : "") + securityPrincipal.substring(beginIndex, lastIndex).trim();
				} else {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userPrinicipalName;
	}
}
