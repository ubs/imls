package phd.collins.imls.agents.behaviours;

import jade.content.AgentAction;
import jade.content.onto.basic.Action;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import phd.collins.imls.agents.actions.StudyArea.AddStudyArea;
import phd.collins.imls.agents.actions.StudyArea.AddStudyAreaResponse;
import phd.collins.imls.agents.ontologies.StudyAreaOntology;
import phd.collins.imls.model.StudyArea;
import phd.collins.imls.util.Info;

public class CyclicAddStudyAreaBehaviour extends CyclicBehaviour {

	private static final long serialVersionUID = 1763495100623127018L;

	AddStudyAreaResponse actionResponse;
	
	private MessageTemplate msgTemplate = MessageTemplate.MatchOntology(StudyAreaOntology.getInstance().getName());
	
	public CyclicAddStudyAreaBehaviour(){
		super();
	}

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
			} catch (Exception e) {
				Info.serr("Exception in Agent Behaviour: " + getClassName() + ". Details: " + e.getMessage());
			}
		} else {
			Info.sout("ENTERING BLOCKING MODE, Behaviour: " + getClassName());
			block();
		}
	}
	
	private void performAddStudyAreaAction(AddStudyArea parStudyArea, Action actExpression, ACLMessage msg) {
		Info.sout(myAgent.getName() + ".performAddStudyAreaAction");
		
		StudyArea studyArea = StudyArea.AddStudyArea(parStudyArea.getStudyAreaName(), parStudyArea.getDescription());
		Info.sout("Study Area Object Returned = " + studyArea);
		boolean isSuccessful = (studyArea != null && studyArea instanceof StudyArea);
		
		//REMEMBER: add UserID to Response so we can store it in session
		actionResponse = new AddStudyAreaResponse();
		actionResponse.setDefaults();
		
		if (isSuccessful){
			String strID = studyArea.getId() + "";
			actionResponse.setId(strID);
			actionResponse.setStudyAreaName(studyArea.getArea_name());
			actionResponse.setDescription(studyArea.getDescription());
			actionResponse.setSuccessStatus(isSuccessful);
		}
		
		BehaviourHelper.getInstance(myAgent).sendNotification(actExpression, msg, ACLMessage.INFORM, actionResponse);
	}
	
	public AddStudyAreaResponse getResult(){
		return actionResponse;
	}
	
	public Agent getMyAgent(){
		return myAgent;
	}
	
	public String getClassName(){
		return this.getClass().getName();
	}
}