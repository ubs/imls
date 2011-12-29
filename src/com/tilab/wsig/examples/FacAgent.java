/*****************************************************************
JADE - Java Agent DEvelopment Framework is a framework to develop 
multi-agent systems in compliance with the FIPA specifications.
Copyright (C) 2002 TILAB

GNU Lesser General Public License

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation, 
version 2.1 of the License. 

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the
Free Software Foundation, Inc., 59 Temple Place - Suite 330,
Boston, MA  02111-1307, USA.
*****************************************************************/

package com.tilab.wsig.examples;

import java.util.Date;

import jade.content.AgentAction;
import jade.content.ContentElement;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.basic.Action;
import jade.content.onto.basic.Done;
import jade.content.onto.basic.Result;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPANames;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.FIPAManagementOntology;
import jade.domain.FIPAAgentManagement.Property;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.util.leap.ArrayList;
import jade.util.leap.List;

import org.apache.log4j.Logger;

//import FactorialorAgent.RequestPerformer;

public class FacAgent extends Agent {

	public static final String WSIG_FLAG = "wsig";
	public static final String WSIG_MAPPER = "wsig-mapper";
	public static final String WSIG_PREFIX = "wsig-prefix";
	
	private Logger log = Logger.getLogger(FacAgent.class.getName());
	public static AID myAID = null;
	private SLCodec codec = new SLCodec();
	private Date startDate;

	protected int etat;
	
	public FacAgent() {
		etat = 0;
	}
	
	protected void setup() {
		log.info("A FacAgent is starting...");
		log.info("Agent name: "+getLocalName());

		// Get agent arguments
		Object[] args = getArguments();
		if (args != null && args.length > 0) {

			// Register codec/onto
			getContentManager().registerLanguage(codec);
			getContentManager().registerOntology(FIPAManagementOntology.getInstance());
			getContentManager().registerOntology(FacOntology.getInstance());
	
			// Prepare a DFAgentDescription
			DFAgentDescription dfad = new DFAgentDescription();
			dfad.setName(this.getAID());
			dfad.addLanguages(codec.getName());
			dfad.addProtocols(FIPANames.InteractionProtocol.FIPA_REQUEST);
			ServiceDescription sd;
			sd = new ServiceDescription();
			sd.addLanguages(codec.getName());
			sd.addProtocols(FIPANames.InteractionProtocol.FIPA_REQUEST);
			sd.setType("FacAgent");
			sd.setOwnership("FacOwner");
			sd.addOntologies(FacOntology.getInstance().getName());
	
			// WSIG properties
			sd.addProperties(new Property(WSIG_FLAG, "true"));
			
			// Service name
			String wsigServiceName = "Fac"; 
			if (args.length >= 1) {
				wsigServiceName = (String)args[0];
			}
			log.info("Service name: "+wsigServiceName);
			sd.setName(wsigServiceName);
			
			// Mapper
			boolean isMapperPresent = false; 
			if (args.length >= 2) {
				isMapperPresent = Boolean.parseBoolean((String)args[1]);
			}
			log.info("Mapper present: "+isMapperPresent);
			if (isMapperPresent) {
				sd.addProperties(new Property(WSIG_MAPPER, "com.tilab.wsig.examples.FacOntologyMapper"));
			}
			
			// Prefix
			String wsigPrefix = ""; 
			if (args.length >= 3) {
				wsigPrefix = (String)args[2];
			}
			log.info("Prefix: "+wsigPrefix);
			if (wsigPrefix != null && !wsigPrefix.equals("")) {
				sd.addProperties(new Property(WSIG_PREFIX, wsigPrefix));
			}
	
			dfad.addServices(sd);
	
			// DF registration
			try {
				DFService.register(this, dfad);
			} catch (Exception e) {
				log.error("Problem during DF registration", e);
				doDelete();
			}
	
			log.debug("A FacAgent is started.");
			startDate = new Date();
				
			// Add math behaviour
			this.addBehaviour(new CyclicBehaviour(this) {
				
				private MessageTemplate template; 
				private Action actExpr;
				private ACLMessage msg;
	
				public void action() {

					switch (etat){	
					case 0:					
						template = MessageTemplate.MatchOntology(FacOntology.getInstance().getName());
						
						msg = myAgent.receive(template);
						if (msg != null) {
							try {
								actExpr = (Action) myAgent.getContentManager().extractContent(msg);
								AgentAction action = (AgentAction) actExpr.getAction();
								if (action instanceof Fac) {
									serveFacReqAction((Fac) action, actExpr, msg);
								}
							} catch (Exception e) {
								log.error("Exception: " + e.getMessage(), e);
							}
						} else {
							block();
						}
					
						break;
						
					case 1:
						// recoit le résultat
						ACLMessage rep = receive();
						if(rep != null){
							//Integer result = new Integer( rep.getContent().toString() );
		
							sendNotification(actExpr, msg, ACLMessage.INFORM, rep.getContent());
						}
						break;
					}
				}	
			});
		}else{
			// Make the agent terminate
			System.out.println("No number specified");
			doDelete();
		}
	}

	private void serveFacReqAction(Fac fac, Action actExpr, ACLMessage msg) {
		log.debug("FacAgent.serveFacAction");
		
		Integer num = new Integer(fac.getNumber());
		
		ACLMessage req = new ACLMessage(ACLMessage.REQUEST);
        req.addReceiver( new AID( "FactorialorAgent1", AID.ISLOCALNAME ) );
        req.setContent(num.toString());
        send(req);

        etat = 1;
	}

	private void sendNotification(Action actExpr, ACLMessage request, int performative, Object result) {
		// Send back a proper reply to the requester
		ACLMessage reply = request.createReply();
		if (performative == ACLMessage.INFORM) {
			reply.setPerformative(ACLMessage.INFORM);
			try {
				ContentElement ce = null;
				if (result != null) {
					// If the result is a java.util.List, convert it into a jade.util.leap.List t make the ontology "happy"
					if (result instanceof java.util.List) {
						ArrayList l = new ArrayList();
						l.fromList((java.util.List) result);
						result = l;
					}
					ce = new Result(actExpr, result);
				} else {
					ce = new Done(actExpr);
				}
				getContentManager().fillContent(reply, ce);
			}
			catch (Exception e) {
				log.error("Agent " + getName() + ": Unable to send notification" + e);
				e.printStackTrace();
			}
		} else {
			reply.setPerformative(performative);

		}
		reply.addUserDefinedParameter(ACLMessage.IGNORE_FAILURE, "true");
		send(reply);
	}

	protected void takeDown() {
		//deregister itself from the DF
		try {
			DFService.deregister(this);
		} catch (Exception e) {
			log.error(e);
		}

		log.debug("A FacAgent is taken down now.");
	}
}
