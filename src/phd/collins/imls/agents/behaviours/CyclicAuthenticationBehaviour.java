package phd.collins.imls.agents.behaviours;

import jade.content.AgentAction;
import jade.content.ContentElement;
import jade.content.onto.basic.Action;
import jade.content.onto.basic.Done;
import jade.content.onto.basic.Result;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.util.leap.ArrayList;
import phd.collins.imls.agents.Actions.Authenticate;
import phd.collins.imls.agents.ontologies.AuthenticationOntology;
import phd.collins.imls.agents.schemas.AuthenticationInfo;
import phd.collins.imls.model.User;
import phd.collins.imls.util.Info;

public class CyclicAuthenticationBehaviour extends CyclicBehaviour {

	private static final long serialVersionUID = 758895961070498074L;
	
	private MessageTemplate msgTemplate = MessageTemplate.MatchOntology(
			AuthenticationOntology.getInstance().getName());

	@Override
	public void action() {
		Info.sout("Action called for Behaviour: " + this.getClass().getName());
		Info.sout("Message Template in Use: " + msgTemplate.toString());
		
		ACLMessage msg = myAgent.receive(msgTemplate);
		if (msg != null) {
			Action actExpression;
			try {
				actExpression = (Action) myAgent.getContentManager().extractContent(msg);
				AgentAction action = (AgentAction) actExpression.getAction();
				
				Info.sout("In " + getClassName() + " actExpression: " + actExpression + ", action: " + action);
				
				if (action instanceof Authenticate) {
					perfromAuthenticateAction((Authenticate) action, actExpression, msg);
				}
				else if (action instanceof Authenticate) {
					perfromAuthenticateAction((Authenticate) action, actExpression, msg);
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
		AuthenticationInfo result = new AuthenticationInfo();
		//User u1 = new User(authenticate.getUsername(), authenticate.getPassword());
		//result.setUser(u1);
		result.setUsername(authenticate.getUsername());
		result.setUserType(authenticate.getPassword());
		result.setAuthenticated(false);
		sendNotification(actExpression, msg, ACLMessage.INFORM, result);
	}
	
	protected void sendNotification(Action actExpression, ACLMessage request, int performative, Object result) {
		// Send back a proper reply to the requester
		ACLMessage reply = request.createReply();
		
		if (performative == ACLMessage.INFORM) {
			reply.setPerformative(ACLMessage.INFORM);
			
			try {
				ContentElement ce = null;
				
				if (result != null) {
					// If the result is a java.util.List, convert it into a jade.util.leap.List to make the ontology "happy"
					if (result instanceof java.util.List) {
						ArrayList l = new ArrayList();
						l.fromList((java.util.List<?>) result);
						result = l;
					}
					ce = new Result(actExpression, result);
				} else {
					ce = new Done(actExpression);
				}
				myAgent.getContentManager().fillContent(reply, ce);
			}
			catch (Exception e) {
				Info.serr("Error: Agent " + myAgent.getName() + ", Unable to send notification" + e.getMessage());
				e.printStackTrace();
			}
		} else {
			reply.setPerformative(performative);
		}
		
		reply.addUserDefinedParameter(ACLMessage.IGNORE_FAILURE, "true");
		myAgent.send(reply);
	}
	
	private String getClassName(){
		return this.getClass().getName();
	}
}