package phd.collins.imls.agents.behaviours;

import java.util.Date;

import jade.core.behaviours.OneShotBehaviour;
import phd.collins.imls.util.Info;

public class OneShotAuthenticationBehaviour extends OneShotBehaviour {

	private static final long serialVersionUID = 758895961070498074L;

	@Override
	public void action() {
		Info.sout("Action called for Behaviour: " + this.getClass().getName());
	}
	
	public String getResult(){
		return "@" + new Date() + ">> What kinda result were you actually expecting?...";
	}
}
