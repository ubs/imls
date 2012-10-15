<%@page import="phd.collins.imls.model.FieldCourse"%>
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

	String flashInfo = "";
	String viewPage = "fieldcourseadminView.jsp";
	String parFieldID = "", parCourseName = "", parDescription = "", parStudyOrder = "", parPassPercentage = "";
	
	boolean areaFieldsExist = AreaField.areaFieldsExist();
	
	if ( !areaFieldsExist ){
		flashInfo = "Sorry, you must first create area fields before you can manage courses. " +
		"&nbsp;&laquo<a href=\"" + LinksManager.AREA_FIELDS_ADMIN +
		"\" title=\"Click to manage area fields\">Manage Area Fields</a>&raquo ";
		
		SessionManager.setFlashInfo(session, flashInfo, FlashInfoType.WARNING);
	}
	else {
		Object parTestParam = request.getParameter(ParameterNames.PN_AREA_FIELD_ID);
		if (parTestParam == null){
			parFieldID = parCourseName = parDescription = parStudyOrder = parPassPercentage = "";
		}
		else{
			parFieldID = request.getParameter(ParameterNames.PN_AREA_FIELD_ID);
			parCourseName = request.getParameter(ParameterNames.PN_COURSE_NAME);
			parDescription = request.getParameter(ParameterNames.PN_DESCRIPTION);
			parStudyOrder = request.getParameter(ParameterNames.PN_STUDY_ORDER);
			parPassPercentage = request.getParameter(ParameterNames.PN_PASS_PERCENTAGE);
			
			FieldCourse fieldCourse = FieldCourse.AddFieldCourse(
					parFieldID, parCourseName, parDescription, parStudyOrder, parPassPercentage);
			
			if (fieldCourse != null){
				flashInfo = "Field Course (" + parCourseName + ") has been successfully created.";
				SessionManager.setFlashInfo(session, flashInfo, FlashInfoType.INFO);
				response.sendRedirect(LinksManager.FIELD_COURSES_ADMIN);
				return;
			}
		}
	}
	
	ViewParameters viewParams = new ViewParameters();
	viewParams.setParameter(ParameterNames.PN_AREA_FIELD_ID, parFieldID);
	viewParams.setParameter(ParameterNames.PN_COURSE_NAME, parCourseName);
	viewParams.setParameter(ParameterNames.PN_DESCRIPTION, parDescription);
	viewParams.setParameter(ParameterNames.PN_STUDY_ORDER, parStudyOrder);
	viewParams.setParameter(ParameterNames.PN_PASS_PERCENTAGE, parPassPercentage);
	viewParams.setParameter(ParameterNames.PN_AREA_FIELDS_EXIST, areaFieldsExist);
	SessionManager.setViewParameters(request, viewParams);
%>
<jsp:include page="<%= LinksManager.LAYOUT_PAGE %>" flush="true">
	<jsp:param name="viewPage" value="<%= viewPage %>" />
</jsp:include>