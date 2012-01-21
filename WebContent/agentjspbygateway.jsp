<%@page import="jade.util.leap.Properties"%>
<%@page import="jade.core.Profile"%>
<%@page import="jade.wrapper.gateway.JadeGateway"%>
<%@page import="phd.collins.imls.agents.behaviours.OneShotAuthenticationBehaviour"%>
<%@page import="phd.collins.imls.util.Info"%>

<%
	String strAgentIdentity = "";
	String strResult = "";

	Info.sout("Connecting to an agent via JadeGateway....");
	
	OneShotAuthenticationBehaviour osb = new OneShotAuthenticationBehaviour();
	try{
		Properties props = new Properties();
		props.setProperty(Profile.MAIN, "false");
		
		JadeGateway.init(null, props);
		JadeGateway.execute(osb, 1200);
		
		strResult = osb.getResult();
	} catch (InterruptedException e){
		Info.serr("Time out ..... ");
	} catch (Exception e){
		Info.serr("Exception Occured while executing whatever");
	}
	
%>

<html>
	<head><title>JSP JADE Tester via Gateway</title></head>
	<body>
		<h1>JSP JADE TESTER VIA JADEGATEWAY</h1>
		<div><h3>Result of our handwork: <%= strResult %></h3></div>
	</body>
</html>