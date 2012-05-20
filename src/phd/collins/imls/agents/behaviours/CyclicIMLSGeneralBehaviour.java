package phd.collins.imls.agents.behaviours;

import jade.content.AgentAction;
import jade.content.onto.basic.Action;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import phd.collins.imls.agents.actions.AddStudyArea;
import phd.collins.imls.agents.actions.AddStudyAreaResponse;
import phd.collins.imls.model.StudyArea;
import phd.collins.imls.util.Info;

public class CyclicIMLSGeneralBehaviour extends CyclicBehaviour {

	private static final long serialVersionUID = -2554089872175172311L;
	
	private MessageTemplate msgTemplate = MessageTemplate.MatchOntology("imls-another-random-ontology");
			//AnotherRandomOntology.getInstance().getName());

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
				
				if (action instanceof AddStudyArea) {
					performAddStudyAreaAction((AddStudyArea) action, actExpression, msg);
				}
				//else if (action instanceof TestAction) {
					//perfromTestAction((TestAction) action, actExpression, msg);
				//}
			} catch (Exception e) {
				Info.serr("Exception in Agent Behaviour: " + getClassName() + ". Details: " + e.getMessage());
			}
		} else {
			Info.sout("ENTERING BLOCKING MODE, Behaviour: " + getClassName());
			block();
		}
	}
	
	private void performAddStudyAreaAction(AddStudyArea addStudyArea, Action actExpression, ACLMessage msg) {
		Info.sout(myAgent.getName() + ".performAddStudyAreaAction");
		
		long studyAreaID = StudyArea.create(addStudyArea.getStudyAreaName(), addStudyArea.getDescription());
		boolean isSuccessful = ( studyAreaID > 0 );
		
		Info.sout("StudyArea Object Returned = " + studyAreaID);
		
		AddStudyAreaResponse studyAreaResponse = new AddStudyAreaResponse();
		studyAreaResponse.setDefaults();
		
		if (isSuccessful){
			studyAreaResponse.setId(String.valueOf(studyAreaID));
			studyAreaResponse.setStudyAreaName( addStudyArea.getStudyAreaName() );
			studyAreaResponse.setDescription( addStudyArea.getDescription() );
			studyAreaResponse.setSuccessStatus(isSuccessful);
		}
		
		BehaviourHelper.getInstance(myAgent).sendNotification(actExpression, msg, ACLMessage.INFORM, studyAreaResponse);
	}

	public Agent getMyAgent(){
		return myAgent;
	}
	
	public String getClassName(){
		return this.getClass().getName();
	}
}