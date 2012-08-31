package com.practice.LDAP;

public class LDAPServerData {

	private String ServerURL;
	
	public String getServerURL() {
		return ServerURL;
	}

	public void setServerURL(String serverURL) {
		ServerURL = serverURL;
	}

	public String getSecurityPrincipal() {
		return SecurityPrincipal;
	}

	public void setSecurityPrincipal(String securityPrincipal) {
		SecurityPrincipal = securityPrincipal;
	}

	private String SecurityPrincipal;
}
