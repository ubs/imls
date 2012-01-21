<%@page import="phd.collins.imls.agents.vocabularies.IMLSAgentsVocabulary"%>
<%@page import="jade.core.AID"%>
<%@page import="com.tilab.wsig.store.WSIGService"%>
<%@page import="com.tilab.wsig.store.WSIGStore"%>
<%@page import="com.tilab.wsig.agent.WSIGBehaviour"%>
<%@page import="phd.collins.imls.agents.ontologies.authentication.Authenticate"%>
<%@page import="phd.collins.imls.agents.ontologies.IMLSOntology"%>
<%@page import="jade.lang.acl.ACLMessage"%>
<%@page import="jade.content.lang.sl.SLCodec"%>
<%@page import="phd.collins.imls.agents.behaviours.CyclicAuthenticationBehaviour"%>
<%@page import="jade.util.leap.Properties"%>
<%@page import="jade.core.Profile"%>
<%@page import="jade.wrapper.gateway.JadeGateway"%>
<%@page import="phd.collins.imls.agents.behaviours.OneShotAuthenticationBehaviour"%>
<%@page import="phd.collins.imls.util.Info"%>

<%
	String strAgentIdentity = "";
	Object objResult = "";

	Info.sout("Connecting to an agent via JadeGateway Part 2....");
	
	CyclicAuthenticationBehaviour cab = new CyclicAuthenticationBehaviour();
	
	try{
		Properties props = new Properties();
		props.setProperty(Profile.MAIN, "false");
		JadeGateway.init(null, props);
		
		ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
		SLCodec codec = new SLCodec();
		//msg.addReceiver(sellerAID) // sellerAID is the AID of the Seller agent
		msg.setLanguage(codec.getName());
		msg.setOntology(IMLSOntology.getInstance().getName());
		
		Authenticate authenticate = new Authenticate();
		authenticate.setUsername("PrinceWill");
		authenticate.setPassword("Perfecto");
		
		//JadeGateway.execute(cab, 1200);
		
		//cab.getMyAgent().getContentManager().fillContent(msg, authenticate);
		//cab.getMyAgent().putO2AObject(msg, false);
		
		ServletContext context = getServletContext();
		WSIGStore wsigStore = (WSIGStore)context.getAttribute("WSIGStore");
		
		String parSuffix = request.getParameter("suffix");
		WSIGService wsigService = wsigStore.getService(IMLSAgentsVocabulary.AUTHENTICATION_AGENT_BASENAME + parSuffix);
		AID agentReceiver = wsigService.getAid();
		
		WSIGBehaviour wsigBehavior = new WSIGBehaviour(agentReceiver, authenticate, IMLSOntology.getInstance(), 1200);
		JadeGateway.execute(wsigBehavior, 1200);
		objResult = cab.getResult();
		Info.sout("Is output objResult NULL? " + (objResult == null));
		
	} catch (InterruptedException e){
		Info.serr("Time out ..... ");
	} catch (Exception e){
		Info.serr("Exception Occured while executing whatever" + e.getMessage());
		e.printStackTrace();
	}
	
%>

<html>
	<head><title>JSP JADE Tester via Gateway</title></head>
	<body>
		<h1>JSP JADE TESTER VIA JADEGATEWAY PART 2</h1>
		<div><h3>Result of our handwork: <%= objResult %></h3></div>
	</body>
</html>