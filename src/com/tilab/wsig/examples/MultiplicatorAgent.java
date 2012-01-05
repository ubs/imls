package com.tilab.wsig.examples;
import java.util.Date;

import org.apache.log4j.Logger;

import jade.content.lang.sl.SLCodec;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

/**
 * Agent waiting for two numbers to multiply.
 */
public class MultiplicatorAgent extends Agent {

	private static final long serialVersionUID = -8789824731604720145L;
	
	public static final String WSIG_FLAG = "wsig";
	public static final String WSIG_MAPPER = "wsig-mapper";
	public static final String WSIG_PREFIX = "wsig-prefix";
	
	@SuppressWarnings("unused")
	private Logger log = Logger.getLogger(FacAgent.class.getName());
	
	public static AID myAID = null;
	
	@SuppressWarnings("unused")
	private SLCodec codec = new SLCodec();
	
	@SuppressWarnings("unused")
	private Date startDate;

	protected void setup() {
		System.out.println("Starting "+getLocalName()+"...");

		// Add the behaviour proceeding to the calculating task by giving work to multiplicator agents
		addBehaviour( new ProcessingServer() );
	}


	// Put agent clean-up operations here
	protected void takeDown() {
		// Printout a dismissal message
		System.out.println("Multiplicator-agent "+getAID().getName()+" terminating.");
	}


	/**
	 * Inner class ProcessingServer.
	 * This is the behavior used by multiplicator agents to
	 * serve incoming requests from factorial agents and
	 * multiply the given numbers.
	 * @author seb
	 *
	 */
	private class ProcessingServer extends CyclicBehaviour {

		private static final long serialVersionUID = 1L;

		@Override
		public void action() {
			MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.CFP);
			ACLMessage msg = myAgent.receive(mt);
			if (msg != null) {;
				// CFP Message received. Process it
				String numbers[] = msg.getContent().split(" ");
				ACLMessage reply = msg.createReply();

				if (numbers.length == 2) {
					reply.setPerformative(ACLMessage.INFORM);
					reply.setReplyWith(msg.getReplyWith());
					
					// The big thing
					Double result = new Double(numbers[0]) * new Double(numbers[1]);

					reply.setContent(String.valueOf(result.doubleValue()));
					System.out.println(result+" computed from "+numbers[0]+" and "+numbers[1]);
				}
				else {
					// The request is not well-formed
					reply.setPerformative(ACLMessage.FAILURE);
					reply.setContent("not-well-formed");
				}

				// Wait some random time between 0.5 and 2.5 seconds
				try {
					
					Thread.sleep( (int)(500 + 2000 * Math.random()) );
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				myAgent.send(reply);
			}
			else {
				block();
			}
		}
	}
}
