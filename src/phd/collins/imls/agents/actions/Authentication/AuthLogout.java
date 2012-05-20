package phd.collins.imls.agents.actions.Authentication;

import jade.content.AgentAction;
import jade.content.onto.annotations.Result;

@Result(type=AuthLogoutResponse.class)
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
