package phd.collins.imls.agents.behaviours;

import jade.content.AgentAction;
import jade.content.onto.basic.Action;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.util.Date;

import phd.collins.imls.agents.actions.Authentication.Authenticate;
import phd.collins.imls.agents.actions.Authentication.AuthenticateResponse;
import phd.collins.imls.agents.ontologies.AuthenticationOntology;
import phd.collins.imls.model.User;
import phd.collins.imls.util.Info;

public class CyclicAuthenticationBehaviour extends CyclicBehaviour {

	private static final long serialVersionUID = 758895961070498074L;
	
	AuthenticateResponse authResult;
	
	private MessageTemplate msgTemplate = MessageTemplate.MatchOntology(AuthenticationOntology.getInstance().getName());
	
	public CyclicAuthenticationBehaviour(){
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
				
				if (action instanceof Authenticate) {
					performAuthenticateAction((Authenticate) action, actExpression, msg);
				}
			} catch (Exception e) {
				Info.serr("Exception in Agent Behaviour: " + getClassName() + ". Details: " + e.getMessage());
			}
		} else {
			Info.sout("ENTERING BLOCKING MODE, Behaviour: " + getClassName());
			block();
		}
	}
	
	private void performAuthenticateAction(Authenticate authenticate, Action actExpression, ACLMessage msg) {
		Info.sout(myAgent.getName() + ".perfromAuthenticateAction");
		
		User authUser = User.authenticateUser(authenticate.getUsername(), authenticate.getPassword());
		Info.sout("User Auth Object Returned = " + authUser);
		boolean authSuccess = (authUser != null && authUser instanceof User);
		
		//REMEMBER: add UserID to Response so we can store it in session
		authResult = new AuthenticateResponse();
		authResult.setDefaults();
		
		if (authSuccess){ 
			authResult.setUsername(authenticate.getUsername());
			authResult.setUserType(authUser.getUser_type());
			authResult.setIsActive(authUser.getIs_active());
			authResult.setLastLoginDate("" + authUser.getLast_login_date());
			authResult.setAuthenticated(authSuccess);
			
			try{
				authUser.setLast_login_date(new Date());
				authUser.updateUser();
			}
			catch(Exception ex) { Info.sout("Exception in  updating authenticated user" + ex.getMessage()); }
		}
		
		BehaviourHelper.getInstance(myAgent).sendNotification(actExpression, msg, ACLMessage.INFORM, authResult);
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