<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="phd.collins.imls.model.StudentStudyRecord"%>
<%@page import="phd.collins.imls.model.Student"%>
<%@page import="phd.collins.imls.model.User"%>
<%@page import="phd.collins.imls.model.AssessmentAnswer"%>
<%@page import="phd.collins.imls.model.AreaField"%>
<%@page import="phd.collins.imls.model.AssessmentQuestion"%>
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
	String viewPage = "studentfinaltestView.jsp";
	String parAreaFieldID = "", parAreaFieldName = "";
	boolean parShowQuestions = false, parShowResults = false;
	
	List<AssessmentQuestion> assessmentQuestions = new ArrayList<AssessmentQuestion>();
	String parAssessmentQuestionIDs = ""; String parPercentageScore = ""; String parAssessedCompetencyLevel = "";
	
	User currentUser = SessionManager.getCurrentUser(session);
	Student student = null;
	AreaField areaField = null;
	
	if (currentUser == null) {
		response.sendRedirect(LinksManager.HOME_PAGE);
	}
	else {
		student = Student.getByRegNumber(currentUser.getUserName());
		if (student != null) {
			areaField = AreaField.refresh(student.getField_of_study());
		}
	}
	
	if ((currentUser == null) || (student == null) || (areaField == null)) {
		flashInfo = "Sorry, assessment test cannot be started at the moment. please try again later. ";
		SessionManager.setFlashInfo(session, flashInfo, FlashInfoType.WARNING);
		response.sendRedirect(LinksManager.HOME_PAGE);
		return;
	}
	
	parAreaFieldID = areaField.getId() + "";
	parAreaFieldName = areaField.getField_name();
	
	Object parFlowControlParam = request.getParameter(ParameterNames.PN_FLOW_CONTROL_PARAM);
	String strParFlowControlParam = (String)parFlowControlParam;
	
	if (parFlowControlParam == null){
		//Show Questions Immediately
		//TODO Change to Agent getting the Questions
		assessmentQuestions = AssessmentQuestion.getRandomAssessmentQuestions(parAreaFieldID);
		
		if (assessmentQuestions.size() > 0) {
			parShowQuestions = true; parShowResults = false;
			parAssessmentQuestionIDs = AssessmentQuestion.getAssessmentQuestionIDs(assessmentQuestions);
		}
		else {
			flashInfo = "Sorry, assessment cannot be done. No assessment questions found for " + parAreaFieldName;
			SessionManager.setFlashInfo(session, flashInfo, FlashInfoType.WARNING);
		}
	}
	else if (strParFlowControlParam.equalsIgnoreCase(ParameterNames.PN_SHOW_RESULTS)){
		//Show Results
		parAssessmentQuestionIDs = (String)request.getParameter(ParameterNames.PN_ASSESSMENT_QUESTION_IDS);
		List<String> lstAssQIDs = AssessmentQuestion.getAssessmentQuestionIDsFromString(parAssessmentQuestionIDs);
		List<AssessmentAnswer> lstAssAnswer = new ArrayList<AssessmentAnswer>();
		String strChosenOption = "";
		
		for (String strAssQID : lstAssQIDs){
			strChosenOption = (String)request.getParameter(ParameterNames.PN_OPTION_PREFIX + strAssQID);
			lstAssAnswer.add(new AssessmentAnswer(strAssQID, strChosenOption));
		}
		
		//TODO, Let Agents Score this
		int assScore = AssessmentQuestion.scoreAssessmentAnswers(lstAssAnswer);
		parPercentageScore = "" + assScore;
		parAssessedCompetencyLevel = AssessmentQuestion.determineAssessedCompetencyLevel(assScore);
		
		//Record Final Scores?
		//TODO TODO
		
		parShowResults = true; parShowQuestions = false;
	}
	else {
		response.sendRedirect(LinksManager.HOME_PAGE);
		return;
	}
	
	ViewParameters viewParams = new ViewParameters();
	viewParams.setParameter(ParameterNames.PN_AREA_FIELD_ID, parAreaFieldID);
	viewParams.setParameter(ParameterNames.PN_AREA_FIELD_NAME, parAreaFieldName);
	viewParams.setParameter(ParameterNames.PN_SHOW_QUESTIONS, parShowQuestions);
	viewParams.setParameter(ParameterNames.PN_SHOW_RESULTS, parShowResults);
	viewParams.setParameter(ParameterNames.PN_ASSESSMENT_QUESTIONS, assessmentQuestions);
	viewParams.setParameter(ParameterNames.PN_ASSESSMENT_QUESTION_IDS, parAssessmentQuestionIDs);
	viewParams.setParameter(ParameterNames.PN_ASSESSMENT_RESULT_PSCORE, parPercentageScore);
	viewParams.setParameter(ParameterNames.PN_ASSESSMENT_RESULT_LEVEL, parAssessedCompetencyLevel);
	
	SessionManager.setViewParameters(request, viewParams);
%>
<jsp:include page="<%= LinksManager.LAYOUT_PAGE %>" flush="true">
	<jsp:param name="viewPage" value="<%= viewPage %>" />
</jsp:include>