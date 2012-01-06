package phd.collins.imls.agents;

import jade.content.ContentElement;
import jade.content.lang.sl.SLCodec;
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
import phd.collins.imls.agents.behaviours.CyclicAuthenticationBehaviour;
import phd.collins.imls.agents.behaviours.OneShotAuthenticationBehaviour;
import phd.collins.imls.agents.ontologies.AuthenticationOntology;
import phd.collins.imls.util.Info;

public class AuthenticationAgent extends Agent {

	private static final long serialVersionUID = -5255344699286504500L;
	
	public static AID myAID = null;
	private SLCodec codec = new SLCodec();
	
	private Object[] args;
	
	protected void setup() {
		Info.sout("Starting Agent: " + this.getClass().getName() + " <Agent name: " + getLocalName() + ">");

		// Get agent arguments
		args = getArguments();
		
		// Register codec/ontology(ies)
		getContentManager().registerLanguage(codec);
		getContentManager().registerOntology(FIPAManagementOntology.getInstance());
		getContentManager().registerOntology(AuthenticationOntology.getInstance());
		
		// Prepare a DFAgentDescription
		DFAgentDescription dfAgentDesc = new DFAgentDescription();
		dfAgentDesc.setName(this.getAID());
		dfAgentDesc.addLanguages(codec.getName());
		dfAgentDesc.addProtocols(FIPANames.InteractionProtocol.FIPA_REQUEST);
		
		ServiceDescription serviceDesc = new ServiceDescription();
		serviceDesc.addLanguages(codec.getName());
		serviceDesc.addProtocols(FIPANames.InteractionProtocol.FIPA_REQUEST);
		serviceDesc.setType("AuthenticationAgent");
		serviceDesc.setOwnership("AuthenticationAgentOwner");
		serviceDesc.addOntologies(AuthenticationOntology.getInstance().getName());

		// WSIG properties
		serviceDesc.addProperties(new Property(WSIGPropertyConstants.WSIG_FLAG, "true"));
		
		// Service name
		String wsigServiceName = "IMLS_Authentication" + Math.random();
		String argServiceName = getArgument(1);
		if ( argServiceName != null){ wsigServiceName = argServiceName; }
		serviceDesc.setName(wsigServiceName);
		
		Info.sout("Agent Service name: " + wsigServiceName);
		Info.sout("Agent Service name from SD Object: " + serviceDesc.getName());
		
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
		//this.addBehaviour(new OneShotAuthenticationBehaviour());
		this.addBehaviour(new CyclicAuthenticationBehaviour());
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
	
	private String getArgument(int argNumber){
		String argument = null; 
		if (args.length >= argNumber) { argument = (String)args[argNumber-1]; }
		return argument;
	}
	
	@SuppressWarnings("unused")
	private boolean useOntologyMapper(){
		boolean useOntologyMapper = false;
		String argument = getArgument(2);
		if (argument != null){ useOntologyMapper = Boolean.valueOf(argument); }
		return useOntologyMapper;
	}

}
