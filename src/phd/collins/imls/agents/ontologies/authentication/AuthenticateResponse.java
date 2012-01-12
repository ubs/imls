package phd.collins.imls.agents.ontologies.authentication;

import jade.content.Concept;
import jade.content.onto.annotations.Slot;

import java.util.Date;

public class AuthenticateResponse implements Concept {
	
	private static final long serialVersionUID = 966867779503077827L;
	
	private String username;
	private String password;
	private String userType;
	private boolean isActive;
	private Date lastLoginDate=null;
	private boolean authenticated;
	
	public AuthenticateResponse(){}
	
	public boolean isAuthenticated() {
		return authenticated;
	}

	@Slot(mandatory = true)
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
	
	@Slot(mandatory = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Slot(mandatory = false)
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Slot(mandatory = false)
	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Slot(mandatory = false)
	public Date getLastLoginDate() {
		return lastLoginDate;
		//duration != null ? ("[" + duration.intValue() + " sec]") : ""
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
}
