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

	String viewPage = "fieldadminView.jsp";
	
	String SOAPResponse = "";
	String parCourseID = "", parModuleName = "", parStudyOrder = "", parDescription = "", parModuleContent = "";
	//AuthenticateResponse authResponse = null;
	boolean studyAreasExist = false;
	
	Info.sout("Context Path: " + request.getContextPath() +  "  viewPage: " + viewPage);
	
	if ( !StudyArea.studyAreasExist() ){
		studyAreasExist = false;
		String flashInfo = "Sorry, you must first create Study Areas before you can manage Fields. " +
			"&nbsp;&laquo<a href=\"" + LinksManager.STUDY_AREAS_ADMIN +
			"\" title=\"Click to manage study areas\">Manage Study Areas</a>&raquo ";
		SessionManager.setFlashInfo(session, flashInfo, FlashInfoType.WARNING);
	}
	else {
		studyAreasExist = true;
		Object parTestParam = request.getParameter(ParameterNames.PN_COURSE_ID);
		if (parTestParam == null){
			parCourseID = parModuleName = parStudyOrder = parDescription = parModuleContent = "";
		}
		else{
			parCourseID = request.getParameter(ParameterNames.PN_COURSE_ID);
			parModuleName = request.getParameter(ParameterNames.PN_MODULE_NAME);
			parStudyOrder = request.getParameter(ParameterNames.PN_STUDY_ORDER);
			parDescription = request.getParameter(ParameterNames.PN_MODULE_DESCRIPTION);
			parModuleContent = request.getParameter(ParameterNames.PN_MODULE_CONTENT);
		}
		//
	}
	
	ViewParameters viewParams = new ViewParameters();
	viewParams.setParameter(ParameterNames.PN_STUDY_AREAS_EXIST, studyAreasExist);
	viewParams.setParameter(ParameterNames.PN_COURSE_ID, parCourseID);
	viewParams.setParameter(ParameterNames.PN_MODULE_NAME, parModuleName);
	viewParams.setParameter(ParameterNames.PN_STUDY_ORDER, parStudyOrder);
	viewParams.setParameter(ParameterNames.PN_MODULE_DESCRIPTION, parDescription);
	viewParams.setParameter(ParameterNames.PN_MODULE_CONTENT, parModuleContent);
	SessionManager.setViewParameters(request, viewParams);
%>
<jsp:include page="<%= LinksManager.LAYOUT_PAGE %>" flush="true">
	<jsp:param name="viewPage" value="<%= viewPage %>" />
</jsp:include>