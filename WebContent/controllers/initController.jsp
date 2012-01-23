<%@page import="java.util.Hashtable"%>
<%@page import="phd.collins.imls.util.AppInits"%>
<%@page import="phd.collins.imls.util.ParameterNames"%>
<%@page import="phd.collins.imls.util.ViewParameters"%>
<%@page import="phd.collins.imls.util.SessionManager"%>
<%@page import="phd.collins.imls.util.Info"%>
<%@page import="phd.collins.imls.util.FrontController"%>
<%
	//Variables
	String strServiceName = "";
	String SOAPResponse = "";
	Hashtable<String, Object> appInitsResponse = new Hashtable<String, Object>();
	
	//Check if Initialisations have already been performed
	boolean initAlreadyDone = AppInits.getInstance().appInitsAlreadyDone();
	
	if (!initAlreadyDone){
		//If Initialisations have not already been performed, perform it
		appInitsResponse = AppInits.getInstance().initApplication();
	}

	String viewPage = FrontController.getViewPage("init");
	
	ViewParameters viewParams = new ViewParameters();
	viewParams.setParameter(ParameterNames.PN_APP_INIT_ALREADY_DONE, initAlreadyDone);
	viewParams.setParameter(ParameterNames.PN_APP_INIT_RESPONSE, appInitsResponse);
	SessionManager.setViewParameters(request, viewParams);
%>
<jsp:include page="<%= viewPage %>"></jsp:include>