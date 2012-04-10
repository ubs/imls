<%@page import="phd.collins.imls.util.ParameterNames"%>
<%@page import="phd.collins.imls.util.ViewParameters"%>
<%@page import="phd.collins.imls.util.SessionManager.FlashInfoType"%>
<%@page import="phd.collins.imls.model.FieldCourse"%>
<%@page import="phd.collins.imls.util.SessionManager"%>
<%@page import="phd.collins.imls.util.LinksManager"%>
<%@page import="phd.collins.imls.util.Info"%>
<%
	//Check authentication if page is to be secured
	if (!SessionManager.isAuthenticated(session)) response.sendRedirect(LinksManager.AUTH_PAGE);

	String viewPage = "moduleadminView.jsp";
	
	String SOAPResponse = "";
	String parCourseID = "", parModuleName = "", parStudyOrder = "", parDescription = "", parModuleContent = "";
	//AuthenticateResponse authResponse = null;
	boolean fieldCoursesExist = false;
	
	Info.sout("Context Path: " + request.getContextPath() +  "  viewPage: " + viewPage);
	
	if ( !FieldCourse.fieldCoursesExist() ){
		fieldCoursesExist = false;
		String flashInfo = "Sorry, you must first create courses before you can manage modules. " +
			"&nbsp;&laquo<a href=\"" + LinksManager.FIELD_COURSES_ADMIN +
			"\" title=\"Click to manage field courses\">Manage Field Courses</a>&raquo ";
		SessionManager.setFlashInfo(session, flashInfo, FlashInfoType.WARNING);
	}
	else {
		fieldCoursesExist = true;
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
	viewParams.setParameter(ParameterNames.PN_FIELD_COURSES_EXIST, fieldCoursesExist);
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