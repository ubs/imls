package phd.collins.imls.agents.ontologies;

import phd.collins.imls.agents.ontologies.authentication.AuthInfo;
import jade.content.AgentAction;
import jade.content.onto.annotations.Result;

@Result(type=AuthInfo.class)
public class TestAction implements AgentAction {

	private static final long serialVersionUID = 5320897416272777844L;
	
	private String utility;
	private String austerity;
	
	public TestAction() {
		super();
	}
	
	@Override
	public String toString() {
		return new StringBuffer().append("TestAction (").append(utility)
			.append(",").append(austerity).append(")").toString();
	}

	public void setUtility(String utility) {
		this.utility = utility;
	}

	public String getUtility() {
		return utility;
	}

	public void setAusterity(String austerity) {
		this.austerity = austerity;
	}

	public String getAusterity() {
		return austerity;
	}
}
