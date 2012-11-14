<%@page import="phd.collins.imls.model.AssessmentQuestion"%>
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
	String viewPage = "manageassessmentadminView.jsp";
	String parCourseID = "", parQuestion = "", parCompetencyLevelID = "";
	String parOption1 = "", parOption2 = "", parOption3 = "", parOption4 = "", parCorrectOption = "", parPoint = "";
	
	boolean fieldCoursesExist = FieldCourse.fieldCoursesExist();
	
	if ( !fieldCoursesExist ){
		flashInfo = "Sorry, you must first create field courses before you can manage assessments. " +
		"&nbsp;&laquo<a href=\"" + LinksManager.FIELD_COURSES_ADMIN +
		"\" title=\"Click to manage field courses\">Manage Field Courses</a>&raquo ";
		
		SessionManager.setFlashInfo(session, flashInfo, FlashInfoType.WARNING);
	}
	else {
		Object parTestParam = request.getParameter(ParameterNames.PN_FIELD_COURSE_ID);
		if (parTestParam == null){
			parCourseID = parQuestion = parCompetencyLevelID = "";
			parOption1 = parOption2 = parOption3 = parOption4 = parCorrectOption = parPoint = "";
		}
		else{
			parCourseID = request.getParameter(ParameterNames.PN_FIELD_COURSE_ID);
			parQuestion = request.getParameter(ParameterNames.PN_QUESTION);
			parCompetencyLevelID = request.getParameter(ParameterNames.PN_COMPETENCY_LEVEL_ID);
			parOption1 = request.getParameter(ParameterNames.PN_OPTION_1);
			parOption2 = request.getParameter(ParameterNames.PN_OPTION_2);
			parOption3 = request.getParameter(ParameterNames.PN_OPTION_3);
			parOption4 = request.getParameter(ParameterNames.PN_OPTION_4);
			parCorrectOption = request.getParameter(ParameterNames.PN_CORRECT_OPTION);
			parPoint = request.getParameter(ParameterNames.PN_QUESTION_POINT);
			
			Info.sout("About to add assessment question. Parameters: " +
					parCourseID + ", " + parQuestion + ", " + parCompetencyLevelID + ", " +
					parOption1 + ", " + parOption2  + ", " + parOption3 + ", " + parOption4 + ", " + 
					parCorrectOption + ", " + parPoint);
			
			AssessmentQuestion assessmentQuestion = AssessmentQuestion.AddAssessmentQuestion(
					parCourseID, parQuestion, parCompetencyLevelID,
					parOption1, parOption2, parOption3, parOption4, parCorrectOption, parPoint);
			
			if (assessmentQuestion != null){
				flashInfo = "Assessment Question has been successfully created.";
				SessionManager.setFlashInfo(session, flashInfo, FlashInfoType.INFO);
				response.sendRedirect(LinksManager.MANAGE_ASSESSMENT_ADMIN);
				return;
			}
		}
	}
	
	ViewParameters viewParams = new ViewParameters();
	viewParams.setParameter(ParameterNames.PN_FIELD_COURSE_ID, parCourseID);
	viewParams.setParameter(ParameterNames.PN_QUESTION, parQuestion);
	viewParams.setParameter(ParameterNames.PN_COMPETENCY_LEVEL_ID, parCompetencyLevelID);
	viewParams.setParameter(ParameterNames.PN_OPTION_1, parOption1);
	viewParams.setParameter(ParameterNames.PN_OPTION_2, parOption2);
	viewParams.setParameter(ParameterNames.PN_OPTION_3, parOption3);
	viewParams.setParameter(ParameterNames.PN_OPTION_4, parOption4);
	viewParams.setParameter(ParameterNames.PN_CORRECT_OPTION, parCorrectOption);
	viewParams.setParameter(ParameterNames.PN_QUESTION_POINT, parPoint);
	viewParams.setParameter(ParameterNames.PN_FIELD_COURSES_EXIST, fieldCoursesExist);
	SessionManager.setViewParameters(request, viewParams);
%>
<jsp:include page="<%= LinksManager.LAYOUT_PAGE %>" flush="true">
	<jsp:param name="viewPage" value="<%= viewPage %>" />
</jsp:include>