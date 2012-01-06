package phd.collins.imls.agents.schemas;

import java.util.Date;

import jade.content.Concept;
import phd.collins.imls.model.User;

public class AuthenticationInfo implements Concept {
	
	private static final long serialVersionUID = 966867779503077827L;
	
	private String username;
	private String userType;
	private boolean isActive;
	private Date lastLoginDate;
	private boolean authenticated;
	
	private User user;
	
	public AuthenticationInfo(){}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}
}
