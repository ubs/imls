package phd.collins.imls.agents.behaviours;

import jade.content.AgentAction;
import jade.content.onto.basic.Action;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.util.Date;

import phd.collins.imls.agents.ontologies.IMLSOntology;
import phd.collins.imls.agents.ontologies.TestAction;
import phd.collins.imls.agents.ontologies.authentication.AuthInfo;
import phd.collins.imls.agents.ontologies.authentication.Authenticate;
import phd.collins.imls.agents.ontologies.authentication.AuthenticateResponse;
import phd.collins.imls.util.Info;

public class CyclicAuthenticationBehaviour extends CyclicBehaviour {

	private static final long serialVersionUID = 758895961070498074L;
	
	AuthenticateResponse authResult;
	
	private MessageTemplate msgTemplate = MessageTemplate.MatchOntology(
			IMLSOntology.getInstance().getName());

	@Override
	public void action() {
		Info.sout("Action called for Behaviour: " + this.getClass().getName());
		Info.sout("Message Template in Use: " + msgTemplate.toString());
		
		ACLMessage msg = myAgent.receive(msgTemplate);
		Info.sout("Is ACL Message NULL? : " + (msg == null));
		
		if (msg != null) {
			Action actExpression;
			try {
				actExpression = (Action) myAgent.getContentManager().extractContent(msg);
				AgentAction action = (AgentAction) actExpression.getAction();
				
				Info.sout("In " + getClassName() + " actExpression: " + actExpression + ", action: " + action);
				
				if (action instanceof Authenticate) {
					perfromAuthenticateAction((Authenticate) action, actExpression, msg);
				}
				else if (action instanceof TestAction) {
					perfromTestAction((TestAction) action, actExpression, msg);
				}
			} catch (Exception e) {
				Info.serr("Exception in Agent Behaviour: " + getClassName() + ". Details: " + e.getMessage());
			}
		} else {
			Info.sout("ENTERING BLOCKING MODE, Behaviour: " + getClassName());
			block();
		}
	}
	
	private void perfromAuthenticateAction(Authenticate authenticate, Action actExpression, ACLMessage msg) {
		Info.sout(myAgent.getName() + ".perfromAuthenticateAction");
		
		authResult = new AuthenticateResponse();
		authResult.setUsername(authenticate.getUsername());
		authResult.setPassword(authenticate.getPassword());
		authResult.setAuthenticated(false);
		authResult.setIsActive(false);
		authResult.setUserType("ADMIN");
		authResult.setLastLoginDate(new Date());
		
		BehaviourHelper.getInstance(myAgent).sendNotification(actExpression, msg, ACLMessage.INFORM, authResult);
	}
	
	private void perfromTestAction(TestAction testAction, Action actExpression, ACLMessage msg) {
		Info.sout(myAgent.getName() + ".perfromTestAction");
		AuthInfo result = new AuthInfo();
		result.setAuthenticated(false);
		result.setIsActive(true);
		result.setLastLoginDate(new Date());
		result.setPassword(testAction.getAusterity());
		result.setUsername(testAction.getUtility());
		result.setUserType("USER");
		
		BehaviourHelper.getInstance(myAgent).sendNotification(actExpression, msg, ACLMessage.INFORM, result);
	}
	
	public AuthenticateResponse getResult(){
		return authResult;
	}
	
	public Agent getMyAgent(){
		return myAgent;
	}
	
	public String getClassName(){
		return this.getClass().getName();
	}
}