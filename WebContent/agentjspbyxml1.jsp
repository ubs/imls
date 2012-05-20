<%@page import="phd.collins.imls.util.XML2Hash"%>
<%@page import="phd.collins.imls.agents.actions.Authentication.AuthenticateResponse"%>
<%@page import="com.tilab.wsig.WSIGConfiguration"%>
<%@page import="com.tilab.wsig.store.WSIGStore"%>
<%@page import="phd.collins.imls.util.IMLSYellowPages"%>
<%@page import="com.tilab.wsig.soap.SoapClient"%>
<%@page import="phd.collins.imls.agents.vocabularies.IMLSAgentsVocabulary"%>
<%@page import="java.util.Date"%>
<%@page import="phd.collins.imls.agents.xmlrequests.XMLRequestTemplater"%>
<%@page import="phd.collins.imls.util.Info"%>

<%
	WSIGStore wsigStore = (WSIGStore)application.getAttribute("WSIGStore");
	WSIGConfiguration wsigConfig = (WSIGConfiguration)application.getAttribute("WSIGConfiguration");

	Info.sout("Connecting to an agent via xml soap service from WSIG Part 1....");
	String strServiceName, strUsername, strPassword;

	strServiceName = IMLSYellowPages.findService(wsigStore, "authenticate");
	strUsername = "buberology" + new Date();
	strPassword = "passworxy";
	
	String xmlRequest = XMLRequestTemplater.getAuthenticateRequestXML(strServiceName, strUsername, strPassword);
	String SOAPUrl = IMLSYellowPages.getWSIGServiceURL(wsigConfig);
	Info.sout("Service Name = " + strServiceName + " Web Service URL = " + SOAPUrl);
	
	String SOAPResponse = "";
	AuthenticateResponse authResponse = null;
	
	if (xmlRequest != null && !"".equals(xmlRequest)) {
		Info.sout(xmlRequest);
		SOAPResponse = SoapClient.sendStringMessage(SOAPUrl, xmlRequest);
		
		authResponse = new AuthenticateResponse(XML2Hash.XML2HashTable(SOAPResponse));
		Info.sout("See It there::: " + authResponse);
	}
%>

<html>
	<head><title>JSP JADE Tester via XML</title></head>
	<body>
		<h1>JSP JADE TESTER VIA XML PART 1</h1>
		<div><h3>Result of our handwork: <%= SOAPResponse %></h3></div>
		<div>AuthResponse Object to String: <%= authResponse %></div>
	</body>
</html>