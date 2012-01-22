package phd.collins.imls.agents.ontologies.authentication;

import jade.content.Concept;
import jade.content.onto.annotations.Slot;

import java.util.Date;
import java.util.Hashtable;

import phd.collins.imls.util.DateTime;

public class AuthenticateResponse implements Concept {
	
	private static final long serialVersionUID = 966867779503077827L;
	
	private String username;
	private String userType;
	private boolean isActive;
	private Date lastLoginDate=null;
	private boolean authenticated;
	
	public AuthenticateResponse(){}
	
	public AuthenticateResponse(Hashtable<String, String> attrHash){
		this();
		setUsername(attrHash.get("username"));
		setUserType(attrHash.get("userType"));
		setIsActive(Boolean.valueOf(attrHash.get("isActive")));
		String strDate = attrHash.get("lastLoginDate");
		setLastLoginDate( DateTime.getDateFromISO8601String(strDate) );
		setAuthenticated(Boolean.valueOf(attrHash.get("authenticated")));
	}
	
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
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append(getClass().getName())
		.append("(")
		.append(" username: ").append(getUsername())
		.append(" userType: ").append(getUserType())
		.append(" isActive: ").append(getIsActive())
		.append(" lastLoginDate: ").append(getLastLoginDate())
		.append(" authenticated: ").append(getAuthenticated())
		.append(")");
		
		return sb.toString();
	}
}
