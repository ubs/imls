package phd.collins.imls.agents.schemas;

import jade.content.Concept;
import phd.collins.imls.model.User;

public class AuthenticationInfo implements Concept {
	
	private static final long serialVersionUID = 966867779503077827L;
	
//	private String username;
//	private boolean authenticated;
//	private Date lastLoginDate;
	
	private User user;

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}
}
