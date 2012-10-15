<%@page import="phd.collins.imls.model.StudyArea"%>
<%@page import="phd.collins.imls.util.LinksManager"%>
<%@page import="phd.collins.imls.util.SessionManager"%>
<%@page import="phd.collins.imls.util.SessionManager.FlashInfoType"%>
<%@page import="phd.collins.imls.util.ParameterNames"%>
<%@page import="phd.collins.imls.util.ViewParameters"%>
<%@page import="phd.collins.imls.util.Info"%>
<%
	//Check authentication if page is to be secured
	if (!SessionManager.isAuthenticated(session)) {
		response.sendRedirect(LinksManager.AUTH_PAGE);
		return;
	}

	String flashInfo = "";
	String viewPage = "studyareaadminView.jsp";
	String parID = "", parStudyAreaName = "", parDescription = "";
	
	boolean studyAreasExist = false;
	
	studyAreasExist = StudyArea.studyAreasExist();
	if ( !studyAreasExist ){
		flashInfo = "There are no Study Areas on record, use the tools below to create Study Areas";
		SessionManager.setFlashInfo(session, flashInfo, FlashInfoType.WARNING);
	}
	
	Object parTestParam = request.getParameter(ParameterNames.PN_STUDY_AREA_NAME);
	if (parTestParam == null){
		parID = parStudyAreaName = parDescription = "";
	}
	else{
		parID = request.getParameter(ParameterNames.PN_ID);
		parStudyAreaName = request.getParameter(ParameterNames.PN_STUDY_AREA_NAME);
		parDescription = request.getParameter(ParameterNames.PN_DESCRIPTION);
		
		StudyArea studyArea = StudyArea.AddStudyArea(parStudyAreaName, parDescription);
		
		if (studyArea != null){
			flashInfo = "Study area (" + parStudyAreaName + ") has been successfully created.";
			SessionManager.setFlashInfo(session, flashInfo, FlashInfoType.INFO);
			response.sendRedirect(LinksManager.STUDY_AREAS_ADMIN);
			return;
		}
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