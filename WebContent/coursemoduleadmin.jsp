<%@page import="phd.collins.imls.model.FieldCourse"%>
<%@page import="phd.collins.imls.model.CourseModule"%>
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
	String viewPage = "coursemoduleadminView.jsp";
	String parCourseID = "", parModuleName = "", parStudyOrder = "", parCompetencyLevelID = "", parDescription = "", parModuleContent = "";
	
	boolean fieldCoursesExist = FieldCourse.fieldCoursesExist();
	
	if ( !fieldCoursesExist ){
		flashInfo = "Sorry, you must first create field courses before you can manage course modules. " +
		"&nbsp;&laquo<a href=\"" + LinksManager.FIELD_COURSES_ADMIN +
		"\" title=\"Click to manage field courses\">Manage Field Courses</a>&raquo ";
		
		SessionManager.setFlashInfo(session, flashInfo, FlashInfoType.WARNING);
	}
	else {
		Object parTestParam = request.getParameter(ParameterNames.PN_FIELD_COURSE_ID);
		if (parTestParam == null){
			parCourseID = parModuleName = parStudyOrder = parCompetencyLevelID = parDescription = parModuleContent = "";
		}
		else{
			parCourseID = request.getParameter(ParameterNames.PN_FIELD_COURSE_ID);
			parModuleName = request.getParameter(ParameterNames.PN_MODULE_NAME);
			parStudyOrder = request.getParameter(ParameterNames.PN_STUDY_ORDER);
			parCompetencyLevelID = request.getParameter(ParameterNames.PN_COMPETENCY_LEVEL_ID);
			parDescription = request.getParameter(ParameterNames.PN_DESCRIPTION);
			parModuleContent = request.getParameter(ParameterNames.PN_MODULE_CONTENT);
			
			Info.sout("About to add Course Module. Parameters: " +
					parCourseID + ", " + parModuleName + ", " + parDescription + ", " +
					parStudyOrder + ", " + parModuleContent );
			
			CourseModule courseModule = CourseModule.AddCourseModule(
					parCourseID, parModuleName, parStudyOrder, parCompetencyLevelID, parDescription, parModuleContent);
			
			if (courseModule != null){
				flashInfo = "Course module (" + parModuleName + ") has been successfully created.";
				SessionManager.setFlashInfo(session, flashInfo, FlashInfoType.INFO);
				response.sendRedirect(LinksManager.COURSE_MODULES_ADMIN);
				return;
			}
		}
	}
	
	ViewParameters viewParams = new ViewParameters();
	viewParams.setParameter(ParameterNames.PN_FIELD_COURSE_ID, parCourseID);
	viewParams.setParameter(ParameterNames.PN_MODULE_NAME, parModuleName);
	viewParams.setParameter(ParameterNames.PN_STUDY_ORDER, parStudyOrder);
	viewParams.setParameter(ParameterNames.PN_DESCRIPTION, parDescription);
	viewParams.setParameter(ParameterNames.PN_MODULE_CONTENT, parModuleContent);
	viewParams.setParameter(ParameterNames.PN_FIELD_COURSES_EXIST, fieldCoursesExist);
	SessionManager.setViewParameters(request, viewParams);
%>
<jsp:include page="<%= LinksManager.LAYOUT_PAGE %>" flush="true">
	<jsp:param name="viewPage" value="<%= viewPage %>" />
</jsp:include>