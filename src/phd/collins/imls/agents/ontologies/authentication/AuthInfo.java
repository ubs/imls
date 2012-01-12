package phd.collins.imls.agents.ontologies.authentication;

import jade.content.Concept;

import java.util.Date;

public class AuthInfo implements Concept {
	
	private static final long serialVersionUID = 966867779503077827L;
	
	private String username;
	private String password;
	private String userType;
	private boolean isActive;
	private Date lastLoginDate;
	private boolean authenticated;
	
	public AuthInfo(){}
	
	public boolean isAuthenticated() {
		return authenticated;
	}

	public boolean getAuthenticated() {
		return isAuthenticated();
	}
	
	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
}
