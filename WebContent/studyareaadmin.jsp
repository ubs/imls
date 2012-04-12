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
	//AuthenticateResponse authResponse = null;
	boolean studyAreasExist = false;
	
	Info.sout("Context Path: " + request.getContextPath() +  "  viewPage: " + viewPage);
	
	if ( !StudyArea.studyAreasExist() ){
		studyAreasExist = false;
		String flashInfo = "There are no Study Areas on record, use the tools below to create Study Areas";
		SessionManager.setFlashInfo(session, flashInfo, FlashInfoType.INFO);
	}
	else {
		studyAreasExist = true;
		Object parTestParam = request.getParameter(ParameterNames.PN_STUDY_AREA_NAME);
		if (parTestParam == null){
			parID = parStudyAreaName = parDescription = "";
		}
		else{
			parID = request.getParameter(ParameterNames.PN_ID);
			parStudyAreaName = request.getParameter(ParameterNames.PN_STUDY_AREA_NAME);
			parDescription = request.getParameter(ParameterNames.PN_DESCRIPTION);
		}
		//
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