package phd.collins.imls.sandbox.jsp2jade;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.util.Vector;

/**
 * This agent manage all the messages received from the JSP page. If there is an
 * agent listening somewhere, the received messages are forwarded to this agent,
 * either they are buffered.
 */
public class Buffer extends Agent {

	private static final long serialVersionUID = -8280514354993030740L;

	private Vector<String> buffer;
	private boolean online;
	// JADE 2.0
	private AID clientName;

	// JADE 1.4
	// private String clientName;
	public Buffer() {
		//buffer = new Vector();
		buffer = new Vector<String>();
		online = false;
	}

	/**
	 * When an inform message is received (from the JSP page), then the message
	 * is forwarded to an online listener, or either cached. A reception
	 * confirmation is needed before deleting the forwarded message.
	 */
	class ReceiveBehaviour extends CyclicBehaviour {

		private static final long serialVersionUID = -5665884989935622424L;
		
		private MessageTemplate m1;
		private ACLMessage msg;

		public ReceiveBehaviour(Agent a) {
			super(a);
			m1 = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
		}

		public void action() {
			// Wait for an inform message from the snooper and store it
			msg = receive(m1);
			if (msg != null) {
				buffer.add(msg.getContent() + "\n");
				if (online) {
					addBehaviour(new SendAndWaitConfirmBehaviour(myAgent));
				}
			} else {
				// block if there is no message for this behaviour
				block();
			}
		}
	}

	/**
	 * Wait during 10 seconds a receiving confirmation of the client agent. If a
	 * confirmation is received, the buffer is cleared.
	 */
	class SendAndWaitConfirmBehaviour extends SimpleBehaviour {

		private static final long serialVersionUID = -6921740154618628163L;
		
		private boolean finished;
		private static final long TIMEOUT = 10000; // 10 seconds
		private MessageTemplate m1;
		private long maximumTime;

		public SendAndWaitConfirmBehaviour(Agent a) {
			super(a);
			ACLMessage msg2 = new ACLMessage(ACLMessage.INFORM);
			// JADE 2.0:
			msg2.addReceiver(clientName);
			// JADE 1.4:
			// msg2.addDest(clientName);
			StringBuffer stb = new StringBuffer();
			for (int i = 0; i < buffer.size(); i++)
				stb.append(buffer.get(i));
			msg2.setContent(stb.toString());
			send(msg2);
			finished = false;
			m1 = MessageTemplate.MatchPerformative(ACLMessage.CONFIRM);
			maximumTime = System.currentTimeMillis() + TIMEOUT;
		}

		/**
		 * This task is finished only when a confirmation is received or the
		 * timeout is reached.
		 */
		public boolean done() {
			return finished;
		}

		public void action() {
			ACLMessage msg = myAgent.receive(m1);
			if (msg != null) {
				// A confirmation is received
				buffer.clear();
				finished = true; // the behaviour is finished
			} else {
				if (System.currentTimeMillis() >= maximumTime)
					// timeout is elapsed
					// the behaviour is finished without clearing the buufer
					finished = true;
				else
					// timout not yet elapsed
					block(maximumTime - System.currentTimeMillis());
			}
		}
	}

	/**
	 * Wait a connection from the Client.
	 */
	class WaitRequestBehaviour extends CyclicBehaviour {

		private static final long serialVersionUID = 4365683598959509649L;
		
		MessageTemplate m1;
		ACLMessage msg;

		public WaitRequestBehaviour(Agent a) {
			super(a);
			m1 = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
		}

		public void action() {
			// wait for a request message from the client and send it
			// the content of the buffer. Then the buffer is cleaned.
			msg = receive(m1);
			if (msg != null) {
				online = true;
				// JADE 2.0
				clientName = msg.getSender();
				// JADE 1.4
				// clientName = msg.getSource();
				addBehaviour(new SendAndWaitConfirmBehaviour(myAgent));
			} else
				// block if there is no message for this behaviour
				block();
		}
	}

	/**
	 * Wait a disconnection from the client.
	 */
	class NotOnlineBehaviour extends CyclicBehaviour {
		
		private static final long serialVersionUID = -5656857082480180774L;
		
		MessageTemplate m1;
		ACLMessage msg;

		public NotOnlineBehaviour(Agent a) {
			super(a);
			m1 = MessageTemplate.MatchPerformative(ACLMessage.CANCEL);
		}

		public void action() {
			msg = receive(m1);
			if (msg != null) {
				online = false;
			} else
				// block if there is no message for this behaviour
				block();
		}
	}

	protected void setup() {
		addBehaviour(new ReceiveBehaviour(this));
		addBehaviour(new WaitRequestBehaviour(this));
		addBehaviour(new NotOnlineBehaviour(this));
	}
}
