package phd.collins.imls.agents.behaviours;

import jade.content.ContentElement;
import jade.content.onto.basic.Action;
import jade.content.onto.basic.Done;
import jade.content.onto.basic.Result;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.util.leap.ArrayList;
import phd.collins.imls.util.Info;

public class BehaviourHelper{
	
	protected Agent myAgent;

	private BehaviourHelper(Agent objAgent) {
		super();
		this.myAgent = objAgent;
	}
	
	public synchronized final static BehaviourHelper getInstance(Agent myAgent) {
		return new BehaviourHelper(myAgent);
	}
	
	public void sendNotification(Action actExpression, ACLMessage request, int performative, Object result) {
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
}
