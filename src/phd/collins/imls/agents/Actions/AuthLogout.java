package phd.collins.imls.agents.Actions;

import jade.content.AgentAction;

public class AuthLogout implements AgentAction {

	private static final long serialVersionUID = 1087832909763933988L;
	
	private String logoutType;
	
	@Override
	public String toString() {
		return new StringBuffer().append("AuthLogout (").append(logoutType).append(")").toString();
	}

	public void setLogoutType(String logoutType) {
		this.logoutType = logoutType;
	}

	public String getLogoutType() {
		return logoutType;
	}
}
