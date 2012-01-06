package phd.collins.imls.agents;

import jade.content.ContentElement;
import jade.content.onto.basic.Action;
import jade.content.onto.basic.Done;
import jade.content.onto.basic.Result;
import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPANames;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.FIPAManagementOntology;
import jade.domain.FIPAAgentManagement.Property;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.util.leap.ArrayList;
import phd.collins.imls.agents.behaviours.OneShotAuthenticationBehaviour;
import phd.collins.imls.agents.ontologies.AuthenticationOntology;
import phd.collins.imls.util.Info;

public class AuthenticationAgent extends Agent {

	private static final long serialVersionUID = -5255344699286504500L;
	
	public static AID myAID = null;
	
	protected void setup() {
		Info.sout("Starting Agent: " + this.getClass().getName() + " <Agent name: " + getLocalName() + ">");

		// Get agent arguments
		Object[] args = getArguments();
		
		// Register codec/ontology(ies)
		getContentManager().registerOntology(FIPAManagementOntology.getInstance());
		getContentManager().registerOntology(AuthenticationOntology.getInstance());
		
		// Prepare a DFAgentDescription
		DFAgentDescription dfAgentDesc = new DFAgentDescription();
		dfAgentDesc.setName(this.getAID());
		dfAgentDesc.addProtocols(FIPANames.InteractionProtocol.FIPA_REQUEST);
		
		ServiceDescription serviceDesc = new ServiceDescription();
		serviceDesc.addProtocols(FIPANames.InteractionProtocol.FIPA_REQUEST);
		serviceDesc.setType("AuthenticationAgent");
		serviceDesc.setOwnership("AuthenticationAgentOwner");
		serviceDesc.addOntologies(AuthenticationOntology.getInstance().getName());

		// WSIG properties
		serviceDesc.addProperties(new Property(WSIGPropertyConstants.WSIG_FLAG, "true"));
		
		// Service name
		String wsigServiceName = "IMLS_Authentication";
		serviceDesc.setName(wsigServiceName);
		
		dfAgentDesc.addServices(serviceDesc);
		
		// DF registration
		try {
			DFService.register(this, dfAgentDesc);
		} catch (Exception e) {
			Info.serr("Problem during DF registration for " + this.getClass().getName() + 
					" <Agent name: " + getLocalName() + "> Error Message: " + e.getMessage());
			doDelete();
		}
		
		//Add Behaviour
		this.addBehaviour(new OneShotAuthenticationBehaviour());
	}
	
	protected void takeDown() {
		//deregister itself from the DF
		try {
			DFService.deregister(this);
		} catch (Exception e) { Info.serr(e.getMessage()); }

		Info.sout("Agent is being taken down: " + this.getClass().getName());
	}
	
	private void sendNotification(Action actExpr, ACLMessage request, int performative, Object result) {
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
					ce = new Result(actExpr, result);
				} else {
					ce = new Done(actExpr);
				}
				getContentManager().fillContent(reply, ce);
			}
			catch (Exception e) {
				Info.serr("Error: Agent " + getName() + ", Unable to send notification" + e.getMessage());
				e.printStackTrace();
			}
		} else {
			reply.setPerformative(performative);
		}
		
		reply.addUserDefinedParameter(ACLMessage.IGNORE_FAILURE, "true");
		send(reply);
	}

}
