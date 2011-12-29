package com.tilab.wsig.examples;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.util.Date;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Agent processing a factorial number by using slave "multiplicator" agents.
 */
public class FactorialorAgent extends Agent {

	private static final long serialVersionUID = 1698307428587801713L;

	// The list of known multiplicator agents
	private AID[] multiplicatorAgents = {new AID("MultiplicatorAgent1", AID.ISLOCALNAME), new AID("MultiplicatorAgent1", AID.ISLOCALNAME)};

	protected void setup() {
		System.out.println("Starting "+getLocalName()+"...");

		Object[] args = getArguments();
		if (args != null && args.length > 0) {
			// Add the behaviour proceeding to the calculating task by giving work to multiplicator agents
			addBehaviour( new RequestPerformer() );
		}
		else {
			// Make the agent terminate
			System.out.println("No number specified");
			doDelete();
		}
	}

	protected void takeDown() {
		// Printout a dismissal message
		System.out.println("Factorialor-agent "+getAID().getName()+" terminating.");
	}

	/**
	 * Inner class RequestPerformer.
	 * This is the behaviour used by Factorial agent to request multiplicator 
	 * agents to compute two numbers.
	 * @author seb
	 *
	 */
	private class RequestPerformer extends Behaviour {

		private MessageTemplate mt; // The template to receive replies
		private int mulStack = 0; // The counter of replies from multiplicator agents
		private int step = 0;
		private ConcurrentLinkedQueue<Double> tokens = new ConcurrentLinkedQueue<Double>(); // temp results
		private ConcurrentLinkedQueue<String> transIds = new ConcurrentLinkedQueue<String>(); // transactions id (avoid duplication in broadcasting mode) 
		private final boolean broadcastMode = true; // used by delegateWork() to broadcast requests or not
		private AID sender;
		
		
		@Override
		public void action() {
			switch (step) {
			case 0:
				// Send the initial requests to the multiplicator agents
				//Object args[] = myAgent.getArguments();
				//if(args != null) {
				ACLMessage msg = receive();
				if(msg != null){
					sender = msg.getSender();
					Integer arg0 = new Integer( msg.getContent().toString() );
					System.out.println("Calculating "+arg0+"!");
	
					// Initialize
					mulStack = 0;
	
					// Prepare the template to get replies
					ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
					cfp.setConversationId("data-process");
					mt = MessageTemplate.MatchConversationId("data-process");
	
					// Build initial tokens and send them to multiplicator agents
					for(int i=arg0 ; i>1 ; i=i-2) {
						delegateWork(new Double(i), new Double(i-1));
					}
	
					step = 1;
				}
				break;
			case 1:
				// Receive all results from multiplicator agents
				if(receiveResult(myAgent.receive(mt))) {
					// and if possible, sent another working task
					if(tokens.size() >= 2) {
						Iterator<Double> i = tokens.iterator();
						while(i.hasNext()) {
							Double a = i.next();
							if(i.hasNext()) {
								Double b = i.next();
								tokens.remove(a);
								tokens.remove(b);
								delegateWork(a, b);
							}
						}
					}
					if(mulStack == 0) {
						// wait for all replies before terminating
						step = 2;
					}
				}
				break;
			case 2:
				// Terminate
				Iterator<Double> i = tokens.iterator();
				String result;
				if(!i.hasNext()){
					System.err.println("Agent error : no result found.");
					result = "0";
				}else{
					result = i.next().toString();
					System.out.println("\n"+result+" successfully computed.\n");
					
					ACLMessage rep = new ACLMessage(ACLMessage.INFORM);
					rep.addReceiver(sender);
					rep.setContent(result);
					send(rep);
				}
				myAgent.doDelete();
				break;
			}

		}

		/**
		 * Give two numbers to multiply to an agent, arbitrary or by broadcasting.
		 * @param i
		 * @param j
		 */
		private void delegateWork(Double i, Double j) {
			ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
			if(multiplicatorAgents.length == 2 && !broadcastMode) {
				// random agents
				if(Math.random() < 0.5)
					cfp.addReceiver(multiplicatorAgents[0]);
				else
					cfp.addReceiver(multiplicatorAgents[1]);
			}
			else if(multiplicatorAgents.length == 2) {
				// broadcasting
				cfp.addReceiver(multiplicatorAgents[0]);
				cfp.addReceiver(multiplicatorAgents[1]);
			} else if(multiplicatorAgents.length == 1) {
				cfp.addReceiver(multiplicatorAgents[0]);
			} else {
				System.err.println("No multiplicator agent found !");
				block();
			}

			cfp.setContent(i.toString()+" "+j.toString());
			cfp.setConversationId("data-process");
			String transId = "cfp"+System.currentTimeMillis()*Math.random();  // Unique value
			cfp.setReplyWith(transId);
			transIds.add(cfp.getReplyWith());
			myAgent.send(cfp);
			mulStack++;
			if(broadcastMode) {
				Timer t = new Timer();
				Timeout task = new Timeout(transId, i, j);
				t.schedule(task, new Date(System.currentTimeMillis() + task.timeoutValue));
			}
		}

		private class Timeout extends TimerTask {
			private String transId;
			private Double i;
			private Double j;
			public final int timeoutValue = 40000; // 40 seconds

			public Timeout(String transId, Double i, Double j) {
				this.transId = transId;
				this.i = i;
				this.j = j;
			}
			
			public void run() {
				if(transIds.contains(transId)) {
					// Timeout exceeded
					System.out.println("Timeout exceeded for "+transId+"("+i+","+j+"), sending another request...");
					transIds.remove(transId);
					mulStack--;
					delegateWork(i,j);
				}
			}
		} 

		/**
		 * Serve the replies and store the given results.
		 * @param reply
		 * @return
		 */
		private boolean receiveResult(ACLMessage reply) {
			if (reply != null) {
				// Result reply received
				if (reply.getPerformative() == ACLMessage.INFORM) {
					if(transIds.contains(reply.getReplyWith())) {
						transIds.remove(reply.getReplyWith());
						String result = reply.getContent();
						// store temporary results
						tokens.add(new Double(result));
						mulStack--;
						System.out.println(result+" received from "+reply.getSender().getName());
						return true;
					} else {
						System.out.println("Result alrady received or timeout exceed. Agent "+reply.getSender().getName()+" you loose the run !");
					}
				}
				else {
					System.out.println("Attempt failed.");
				}
			}
			else {
				block();
			}
			return false;
		}

		@Override
		public boolean done() {
			return false;
		}

	}

}
