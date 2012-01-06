package phd.collins.imls.agents.Actions;

import jade.content.AgentAction;

public class Authenticate implements AgentAction {

	private static final long serialVersionUID = 5320897416272777844L;
	
	private String username;
	private String password;
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
	@Override
	public String toString() {
		return new StringBuffer().append("Authenticate (").append(username)
			.append(",").append(password).append(")").toString();
	}
}
