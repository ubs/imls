package phd.collins.imls.agents;

import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPANames;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.FIPAManagementOntology;
import jade.domain.FIPAAgentManagement.Property;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import phd.collins.imls.agents.behaviours.CyclicIMLSGeneralBehaviour;
import phd.collins.imls.agents.ontologies.AuthenticationOntology;
import phd.collins.imls.agents.vocabularies.IMLSAgentsVocabulary;
import phd.collins.imls.util.Info;

public class IMLSGeneralAgent extends Agent implements IMLSAgentsVocabulary {

	private static final long serialVersionUID = -5255344699286504500L;
	
	public static AID myAID = null;
	private SLCodec codec = new SLCodec();
	
	private Object[] args;
	
	private Ontology imlsOntology;
	
	protected void setup() {
		Info.sout("Starting Agent: " + this.getClass().getName() 
				+ " <Agent name: " + getLocalName() + ">" + " AID()" + this.getAID());

		// Get agent arguments
		args = getArguments();
		
		//Get Ontology
		imlsOntology = AuthenticationOntology.getInstance();
		
		// Register codec/ontology(ies)
		getContentManager().registerLanguage(codec);
		getContentManager().registerOntology(FIPAManagementOntology.getInstance());
		getContentManager().registerOntology(imlsOntology);
		
		// Prepare a DFAgentDescription
		DFAgentDescription dfAgentDesc = new DFAgentDescription();
		dfAgentDesc.setName(this.getAID());
		dfAgentDesc.addLanguages(codec.getName());
		dfAgentDesc.addProtocols(FIPANames.InteractionProtocol.FIPA_REQUEST);
		
		ServiceDescription serviceDesc = new ServiceDescription();
		serviceDesc.addLanguages(codec.getName());
		serviceDesc.addProtocols(FIPANames.InteractionProtocol.FIPA_REQUEST);
		serviceDesc.setType(IMLS_GENERAL_AGENT);
		serviceDesc.setOwnership(IMLS_GENERAL_AGENT_OWNER);
		serviceDesc.addOntologies(imlsOntology.getName());

		// WSIG properties
		serviceDesc.addProperties(new Property(WSIGPropertyConstants.WSIG_FLAG, TRUE));
		
		// Service name
		String wsigServiceName = IMLS_GENERAL_AGENT_BASENAME + ((int)(Math.random() * 100) + 1);
		String argServiceName = getArgument(1);
		if ( (argServiceName != null) && (!argServiceName.isEmpty()) ){ wsigServiceName = argServiceName; }
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
		this.addBehaviour(new CyclicIMLSGeneralBehaviour());
	}
	
	protected void takeDown() {
		//deregister itself from the DF
		try {
			DFService.deregister(this);
		} catch (Exception e) { Info.serr(e.getMessage()); }

		Info.sout("Agent is being taken down: " + this.getClass().getName());
	}
	
	private String getArgument(int argNumber){
		String argument = null; 
		if (args.length >= argNumber) { argument = (String)args[argNumber-1]; }
		return argument;
	}
}
