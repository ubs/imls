package phd.collins.imls.agents.actions;

import jade.content.Concept;

public class AuthLogoutResponse implements Concept {

	private static final long serialVersionUID = -1783118103451008803L;
	
	private boolean logoutSuccessful;
	private String logoutType;
	private String redirectURL;
	
	public AuthLogoutResponse(){}
	
	public boolean isLogoutSuccessful() {
		return logoutSuccessful;
	}
	
	public boolean getLogoutSuccessful() {
		return isLogoutSuccessful();
	}
	
	public void setLogoutSuccessful(boolean logoutSuccessful) {
		this.logoutSuccessful = logoutSuccessful;
	}
	
	public String getLogoutType() {
		return logoutType;
	}
	
	public void setLogoutType(String logoutType) {
		this.logoutType = logoutType;
	}
	
	public String getRedirectURL() {
		return redirectURL;
	}
	
	public void setRedirectURL(String redirectURL) {
		this.redirectURL = redirectURL;
	}
}
