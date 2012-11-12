package phd.collins.imls.agents.actions.Tutorial;

import jade.content.Concept;
import jade.content.onto.annotations.Slot;

import java.util.Date;
import java.util.Hashtable;
import phd.collins.imls.model.User;

public class AuthenticateResponse implements Concept {
	
	private static final long serialVersionUID = 966867779503077827L;
	
	private String username;
	private String userType;
	private boolean isActive;
	private String lastLoginDate;
	private boolean authenticated;
	
	public AuthenticateResponse(){}
	
	public AuthenticateResponse(Hashtable<String, String> attrHash){
		this();
		setUsername(attrHash.get("username"));
		setUserType(attrHash.get("userType"));
		setIsActive(Boolean.valueOf(attrHash.get("isActive")));
		String strDate = attrHash.get("lastLoginDate");
		//setLastLoginDate( DateTime.getDateFromISO8601String(strDate) );
		setLastLoginDate( strDate );
		setAuthenticated(Boolean.valueOf(attrHash.get("authenticated")));
	}
	
	public AuthenticateResponse(User user){
		this(user, true);
	}
	
	public AuthenticateResponse(User user, boolean isAuthenticated){
		this();
		setUsername(user.getUserName());
		setUserType(user.getUser_type());
		setIsActive(user.getIs_active());
		
		Date dttLastLoginDate = user.getLast_login_date();
		String strLastLoginDate = (dttLastLoginDate == null) ? "" : dttLastLoginDate.toString(); 
		setLastLoginDate(strLastLoginDate);
		setAuthenticated(isAuthenticated);
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
	public String getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(String lastLoginDate) {
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
	
	public void setDefaults(){
		this.setUsername("");
		this.setUserType("");
		this.setIsActive(false);
		this.setLastLoginDate("");
		this.setAuthenticated(false);
	}
}
