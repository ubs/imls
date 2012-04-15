<%@page import="phd.collins.imls.util.SessionManager.FlashInfoType"%>
<%@page import="phd.collins.imls.util.XML2Hash"%>
<%@page import="com.tilab.wsig.soap.SoapClient"%>
<%@page import="phd.collins.imls.agents.xmlrequests.XMLRequestTemplater"%>
<%@page import="phd.collins.imls.util.WebServiceNames"%>
<%@page import="phd.collins.imls.util.IMLSYellowPages"%>
<%@page import="com.tilab.wsig.WSIGConfiguration"%>
<%@page import="com.tilab.wsig.store.WSIGStore"%>
<%@page import="phd.collins.imls.util.ViewParameters"%>
<%@page import="phd.collins.imls.util.SessionManager"%>
<%@page import="phd.collins.imls.util.ParameterNames"%>
<%@page import="phd.collins.imls.agents.actions.AuthenticateResponse"%>
<%@page import="phd.collins.imls.util.LinksManager"%>
<%@page import="phd.collins.imls.util.Info"%>
<%
	String viewPage = "authView.jsp";
	
	String SOAPResponse = "";
	String parUsername, parPassword, parLogout;
	AuthenticateResponse authResponse = null;
	
	//Logout, if that is what is requested
	parLogout = request.getParameter(ParameterNames.PN_AUTH_LOGOUT);
	if ( (parLogout != null) && SessionManager.isAuthenticated(session) ){
		//Logout and Send 2 Auth Page
		SessionManager.logOutCurrentUser(session);
		response.sendRedirect(LinksManager.AUTH_PAGE);
	}
	
	//If already authenticated, send to home page
	if (SessionManager.isAuthenticated(session)){
		response.sendRedirect( LinksManager.HOME_PAGE );
	}

	Object parTestParam = request.getParameter(ParameterNames.PN_AUTH_USERNAME);
	if (parTestParam == null){
		parUsername = parPassword = "";
	}
	else{
		parUsername = request.getParameter(ParameterNames.PN_AUTH_USERNAME);
		parPassword = request.getParameter(ParameterNames.PN_AUTH_PASSWORD);
		
		Info.sout("<Username, Password> : " + parUsername + " " + parPassword);
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
			
			//If authentication is successful, do this
			if (authResponse.isAuthenticated()){
				Info.sout("I SHALL NOW REDIRECT YOU!!!!!");
				SessionManager.setIsAuthenticated(session);
				SessionManager.setUserAuthResponse(session, authResponse);
				response.sendRedirect( LinksManager.HOME_PAGE );
			}
			else {
				Info.sout("I COULD NOT SEND RE-DIRECT FOR SOME REASONS: " + authResponse.isAuthenticated());
				String flashInfo = "Invalid Login details, Please try again.";
				SessionManager.setFlashInfo(session, flashInfo, FlashInfoType.ERROR);
			}
		}
		else{
			String flashInfo = "Error while processing your request. Please try again later.";
			SessionManager.setFlashInfo(session, flashInfo, FlashInfoType.ERROR);
		}
	}
	
	ViewParameters viewParams = new ViewParameters();
	viewParams.setParameter(ParameterNames.PN_AUTH_USERNAME, parUsername);
	viewParams.setParameter(ParameterNames.PN_AUTH_PASSWORD, parPassword);
	viewParams.setParameter(ParameterNames.PN_AUTH_RESPONSE, authResponse);
	SessionManager.setViewParameters(request, viewParams);
%>
<jsp:include page="<%= LinksManager.LAYOUT_PAGE %>" flush="true">
	<jsp:param name="viewPage" value="<%= viewPage %>" />
</jsp:include>