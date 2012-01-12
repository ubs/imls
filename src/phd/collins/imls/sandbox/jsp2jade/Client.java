package phd.collins.imls.sandbox.jsp2jade;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Client extends Agent {
	
	private static final long serialVersionUID = 3265079306120052675L;
	
	// Used to display the messages
	private JTextArea result;

	public Client() {
		result = new JTextArea();
	}

	/**
	 * Wait for a message from the buffer. Confirm the reception.
	 */
	class MyBehaviour extends CyclicBehaviour {

		private static final long serialVersionUID = 4688134814605027465L;
		
		private MessageTemplate m1;

		public MyBehaviour(Agent a) {
			super(a);
			m1 = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
		}

		public void action() {
			// Wait for a message from the Buffer
			ACLMessage msg = receive(m1);
			if (msg != null) {
				// display the message
				result.append(msg.getContent());
				// confirm that the message has been received.
				ACLMessage reply = msg.createReply();
				reply.setPerformative(ACLMessage.CONFIRM);
				send(reply);
			} else {
				block();
			}
		}
	}

	protected void unsubscribe() {
		ACLMessage msg = new ACLMessage(ACLMessage.CANCEL);
		// JADE 2.3
		msg.addReceiver(new AID("buffer", AID.ISLOCALNAME));
		// JADE 2.0:
		// msg.addReceiver(new AID("buffer@"+getHap()));
		// JADE 1.4:
		// msg.addDest("buffer");
		send(msg);
	}

	protected void setup() {
		addBehaviour(new MyBehaviour(this));
		// create a GUI that display the messages
		JFrame frame = new JFrame("Snooper client");
		// When the window is closed, unsubscribe from the buffer.
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				unsubscribe();
				doDelete();
				System.exit(0);
				// kill all the container because there is also 1 client running.
				// doDelete();
			}
		});
		JScrollPane scp = new JScrollPane();
		scp.getViewport().add(result);
		frame.getContentPane().add(scp);
		frame.setSize(300, 200);
		frame.setVisible(true);

		// ask to the buffer stored messages.
		ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
		// JADE 2.3
		msg.addReceiver(new AID("buffer", AID.ISLOCALNAME));
		// JADE 2.0:
		// msg.addReceiver(new AID("buffer@"+getHap()));
		// JADE 1.4:
		// msg.addDest("buffer");
		send(msg);
	}
}
