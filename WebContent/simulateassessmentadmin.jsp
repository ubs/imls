<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="phd.collins.imls.model.AssessmentQuestion"%>
<%@page import="phd.collins.imls.model.FieldCourse"%>
<%@page import="phd.collins.imls.util.LinksManager"%>
<%@page import="phd.collins.imls.util.SessionManager"%>
<%@page import="phd.collins.imls.util.SessionManager.FlashInfoType"%>
<%@page import="phd.collins.imls.util.ParameterNames"%>
<%@page import="phd.collins.imls.util.ViewParameters"%>
<%@page import="phd.collins.imls.util.Info"%>
<%
//If parameter show assessment, show results, keep choice displayed during test, keep cancel button on test
// Keep choice during result
	//Check authentication if page is to be secured
	if (!SessionManager.isAuthenticated(session)) response.sendRedirect(LinksManager.AUTH_PAGE);

	String flashInfo = "";
	String viewPage = "simulateassessmentadminView.jsp";
	String parCourseID = "";
	boolean parShowOptions = true, parShowQuestions = false, parShowResults = false;
	
	List<AssessmentQuestion> assessmentQuestions = new ArrayList<AssessmentQuestion>();
	
	boolean assessmentQuestionsExist = AssessmentQuestion.assessmentQuestionsExist();
	
	if ( !assessmentQuestionsExist ){
		flashInfo = "Sorry, there are no assessment questions for simulations, create questions first. ";
		SessionManager.setFlashInfo(session, flashInfo, FlashInfoType.WARNING);
		response.sendRedirect(LinksManager.MANAGE_ASSESSMENT_ADMIN);
		return;
	}
	else {
		Object parFlowControlParam = request.getParameter(ParameterNames.PN_FLOW_CONTROL_PARAM);
		Info.sout("ParFlowControlParam : " + parFlowControlParam);
		
		if (parFlowControlParam == null){
			parCourseID = "";
		}
		else{
			String strParFlowControlParam = (String)parFlowControlParam;
			Info.sout("Okay, strParFlowControlParam : " + parFlowControlParam
					+ " Equal showQuestions? "
					+ (strParFlowControlParam.equalsIgnoreCase(ParameterNames.PN_SHOW_QUESTIONS))
					+ " Equal showResults? "
					+ (strParFlowControlParam.equalsIgnoreCase(ParameterNames.PN_SHOW_RESULTS))
					);
			
			if (strParFlowControlParam.equalsIgnoreCase(ParameterNames.PN_SHOW_QUESTIONS)){
				//Show Questions
				parShowQuestions = true;
				parShowOptions = parShowResults = false;
				parCourseID = request.getParameter(ParameterNames.PN_FIELD_COURSE_ID);
				
				assessmentQuestions = AssessmentQuestion.getRandomAssessmentQuestions(parCourseID);
			}
			else if (strParFlowControlParam.equalsIgnoreCase(ParameterNames.PN_SHOW_RESULTS)){
				//Show Results
				parShowResults = parShowOptions = true;
				parShowQuestions = false;
				parCourseID = request.getParameter(ParameterNames.PN_FIELD_COURSE_ID);
			}
			else {
				response.sendRedirect(LinksManager.HOME_PAGE);
				return;
			}
		}
	}
	
	ViewParameters viewParams = new ViewParameters();
	viewParams.setParameter(ParameterNames.PN_FIELD_COURSE_ID, parCourseID);
	viewParams.setParameter(ParameterNames.PN_ASSESSMENT_QUESTIONS_EXIST, assessmentQuestionsExist);
	viewParams.setParameter(ParameterNames.PN_SHOW_OPTIONS, parShowOptions);
	viewParams.setParameter(ParameterNames.PN_SHOW_QUESTIONS, parShowQuestions);
	viewParams.setParameter(ParameterNames.PN_SHOW_RESULTS, parShowResults);
	viewParams.setParameter(ParameterNames.PN_ASSESSMENT_QUESTIONS, assessmentQuestions);
	
	SessionManager.setViewParameters(request, viewParams);
%>
<jsp:include page="<%= LinksManager.LAYOUT_PAGE %>" flush="true">
	<jsp:param name="viewPage" value="<%= viewPage %>" />
</jsp:include>