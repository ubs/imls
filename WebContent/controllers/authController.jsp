<%@page import="phd.collins.imls.util.ParameterNames"%>
<%@page import="phd.collins.imls.util.WebServiceNames"%>
<%@page import="phd.collins.imls.util.XML2Hash"%>
<%@page import="com.tilab.wsig.soap.SoapClient"%>
<%@page import="phd.collins.imls.agents.ontologies.authentication.AuthenticateResponse"%>
<%@page import="phd.collins.imls.agents.xmlrequests.XMLRequestTemplater"%>
<%@page import="phd.collins.imls.util.IMLSYellowPages"%>
<%@page import="com.tilab.wsig.WSIGConfiguration"%>
<%@page import="com.tilab.wsig.store.WSIGStore"%>
<%@page import="phd.collins.imls.model.User"%>
<%@page import="phd.collins.imls.util.ViewParameters"%>
<%@page import="phd.collins.imls.util.SessionManager"%>
<%@page import="phd.collins.imls.util.Info"%>
<%@page import="phd.collins.imls.util.FrontController"%>
<%
	String SOAPResponse = "";
	AuthenticateResponse authResponse = null;

	if (SessionManager.isAuthenticated(session)){
		//Redirect to user home based on identity
	}

	String parUsername = request.getParameter(ParameterNames.PN_AUTH_USERNAME);
	String parPassword = request.getParameter(ParameterNames.PN_AUTH_PASSWORD);
	
	Info.sout("<Username, Password> : " + parUsername + " " + parPassword);
	
	if (parUsername != null && parPassword != null){
		Info.sout("Now calling Authentication Agent to Process Authentication");
		
		WSIGStore wsigStore = (WSIGStore)application.getAttribute("WSIGStore");
		WSIGConfiguration wsigConfig = (WSIGConfiguration)application.getAttribute("WSIGConfiguration");
		String strServiceName = IMLSYellowPages.findService(wsigStore, WebServiceNames.WS_AUTHENTICATE);
		
		String xmlRequest = XMLRequestTemplater.getAuthenticateRequestXML(strServiceName, parUsername, parPassword);
		String SOAPUrl = IMLSYellowPages.getWSIGServiceURL(wsigConfig);
		Info.sout("Service Name = " + strServiceName + " Web Service URL = " + SOAPUrl);
		
		if (xmlRequest != null && !"".equals(xmlRequest)) {
			Info.sout(xmlRequest);
			SOAPResponse = SoapClient.sendStringMessage(SOAPUrl, xmlRequest);
			
			authResponse = new AuthenticateResponse(XML2Hash.XML2HashTable(SOAPResponse));
			Info.sout("See It there::: " + authResponse);
		}
	}

	String viewPage = FrontController.getViewPage("auth");
	ViewParameters viewParams = new ViewParameters();
	viewParams.setParameter(ParameterNames.PN_AUTH_RESPONSE, authResponse);
	//request.setAttribute("viewParameters", viewParams);
	SessionManager.setViewParameters(request, viewParams);
%>
<jsp:include page="<%= viewPage %>"></jsp:include>