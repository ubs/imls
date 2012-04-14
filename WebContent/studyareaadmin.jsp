<%@page import="com.tilab.wsig.soap.SoapClient"%>
<%@page import="phd.collins.imls.agents.xmlrequests.XMLRequestTemplater"%>
<%@page import="phd.collins.imls.util.WebServiceNames"%>
<%@page import="phd.collins.imls.util.IMLSYellowPages"%>
<%@page import="com.tilab.wsig.WSIGConfiguration"%>
<%@page import="com.tilab.wsig.store.WSIGStore"%>
<%@page import="phd.collins.imls.agents.IMLSGeneralAgent"%>
<%@page import="phd.collins.imls.model.StudyArea"%>
<%@page import="phd.collins.imls.util.LinksManager"%>
<%@page import="phd.collins.imls.util.SessionManager"%>
<%@page import="phd.collins.imls.util.SessionManager.FlashInfoType"%>
<%@page import="phd.collins.imls.util.ParameterNames"%>
<%@page import="phd.collins.imls.util.ViewParameters"%>
<%@page import="phd.collins.imls.util.Info"%>
<%
	//Check authentication if page is to be secured
	if (!SessionManager.isAuthenticated(session)) response.sendRedirect(LinksManager.AUTH_PAGE);

	String viewPage = "studyareaadminView.jsp";
	
	String SOAPResponse = "";
	String parID = "", parStudyAreaName = "", parDescription = "";
	IMLSGeneralAgent imlsAgent = null;
	boolean studyAreasExist = false;
	
	Info.sout("Context Path: " + request.getContextPath() +  "  viewPage: " + viewPage);
	
	studyAreasExist = StudyArea.studyAreasExist();
	if ( !studyAreasExist ){
		String flashInfo = "There are no Study Areas on record, use the tools below to create Study Areas";
		SessionManager.setFlashInfo(session, flashInfo, FlashInfoType.INFO);
	}
	
	Object parTestParam = request.getParameter(ParameterNames.PN_STUDY_AREA_NAME);
	if (parTestParam == null){
		parID = parStudyAreaName = parDescription = "";
	}
	else{
		parID = request.getParameter(ParameterNames.PN_ID);
		parStudyAreaName = request.getParameter(ParameterNames.PN_STUDY_AREA_NAME);
		parDescription = request.getParameter(ParameterNames.PN_DESCRIPTION);
		
		WSIGStore wsigStore = (WSIGStore)application.getAttribute("WSIGStore");
		WSIGConfiguration wsigConfig = (WSIGConfiguration)application.getAttribute("WSIGConfiguration");
		String strServiceName = IMLSYellowPages.findService(wsigStore, WebServiceNames.WS_AUTHENTICATE);
		
		String xmlRequest = XMLRequestTemplater.getAuthenticateRequestXML(strServiceName, parStudyAreaName, parStudyAreaName);
		String SOAPUrl = IMLSYellowPages.getWSIGServiceURL(wsigConfig);
		Info.sout("Service Name = " + strServiceName + " Web Service URL = " + SOAPUrl);
		
		if (xmlRequest != null && !"".equals(xmlRequest)) {
			Info.sout(xmlRequest);
			SOAPResponse = SoapClient.sendStringMessage(SOAPUrl, xmlRequest);
			
			//authResponse = new AuthenticateResponse(XML2Hash.XML2HashTable(SOAPResponse));
			//Info.sout("See It there::: " + authResponse);
		} //
	}
	
	ViewParameters viewParams = new ViewParameters();
	viewParams.setParameter(ParameterNames.PN_STUDY_AREAS_EXIST, studyAreasExist);
	viewParams.setParameter(ParameterNames.PN_ID, parID);
	viewParams.setParameter(ParameterNames.PN_STUDY_AREA_NAME, parStudyAreaName);
	viewParams.setParameter(ParameterNames.PN_DESCRIPTION, parDescription);
	SessionManager.setViewParameters(request, viewParams);
%>
<jsp:include page="<%= LinksManager.LAYOUT_PAGE %>" flush="true">
	<jsp:param name="viewPage" value="<%= viewPage %>" />
</jsp:include>