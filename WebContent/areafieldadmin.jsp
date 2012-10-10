<%@page import="phd.collins.imls.model.AreaField"%>
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

	String viewPage = "areafieldadminView.jsp";
	String parStudyAreaID = "", parFieldName = "", parDescription = "";
	
	boolean studyAreasExist = false;
	
	if ( !StudyArea.studyAreasExist() ){
		studyAreasExist = false;
		String flashInfo = "Sorry, you must first create Study Areas before you can manage Fields. " +
			"&nbsp;&laquo<a href=\"" + LinksManager.STUDY_AREAS_ADMIN +
			"\" title=\"Click to manage study areas\">Manage Study Areas</a>&raquo ";
			
		SessionManager.setFlashInfo(session, flashInfo, FlashInfoType.WARNING);
		return;
	}
	else {
		studyAreasExist = true;
		
		Object parTestParam = request.getParameter(ParameterNames.PN_STUDY_AREA_ID);
		if (parTestParam == null){
			parStudyAreaID = parFieldName = parDescription = "";
		}
		else{
			parStudyAreaID = request.getParameter(ParameterNames.PN_STUDY_AREA_ID);
			parFieldName = request.getParameter(ParameterNames.PN_AREA_FIELD_NAME);
			parDescription = request.getParameter(ParameterNames.PN_DESCRIPTION);
			
			AreaField areaField = AreaField.AddAreaField(parStudyAreaID, parFieldName, parDescription);
			
			if (areaField != null){
				String flashInfo = "Area Field (" + parFieldName + ") has been successfully created.";
				SessionManager.setFlashInfo(session, flashInfo, FlashInfoType.INFO);
				response.sendRedirect(LinksManager.AREA_FIELDS_ADMIN);
				return;
			}
		}
	}
	
	ViewParameters viewParams = new ViewParameters();
	viewParams.setParameter(ParameterNames.PN_STUDY_AREA_ID, parStudyAreaID);
	viewParams.setParameter(ParameterNames.PN_AREA_FIELD_NAME, parFieldName);
	viewParams.setParameter(ParameterNames.PN_DESCRIPTION, parDescription);
	viewParams.setParameter(ParameterNames.PN_STUDY_AREAS_EXIST, studyAreasExist);
	SessionManager.setViewParameters(request, viewParams);
%>
<jsp:include page="<%= LinksManager.LAYOUT_PAGE %>" flush="true">
	<jsp:param name="viewPage" value="<%= viewPage %>" />
</jsp:include>