<%@page import="phd.collins.imls.model.AssessmentAnswer"%>
<%@page import="phd.collins.imls.util.UtilGeneral"%>
<%@page import="java.util.Enumeration"%>
<%@page import="phd.collins.imls.model.AreaField"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
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
	String viewPage = "simulateassessmentadminView.jsp";
	String parAreaFieldID = "", parAreaFieldName = "";
	boolean parShowOptions = true, parShowQuestions = false, parShowResults = false;
	
	List<AssessmentQuestion> assessmentQuestions = new ArrayList<AssessmentQuestion>();
	String parAssessmentQuestionIDs = ""; String parPercentageScore = ""; String parAssessedCompetencyLevel = "";
	
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
			parAreaFieldID = "";
		}
		else{
			String strParFlowControlParam = (String)parFlowControlParam;
			parAreaFieldID = (String)request.getParameter(ParameterNames.PN_AREA_FIELD_ID);
			
			if (parAreaFieldID != null){
				AreaField areaField = AreaField.get(Long.parseLong(parAreaFieldID));
				parAreaFieldName = (areaField == null) ? "chosen field" : areaField.getField_name();
			}
			
			if (strParFlowControlParam.equalsIgnoreCase(ParameterNames.PN_SHOW_QUESTIONS)){
				//Show Questions, if any
				assessmentQuestions = AssessmentQuestion.getRandomAssessmentQuestions(parAreaFieldID);
				
				if (assessmentQuestions.size() > 0) {
					parShowQuestions = true;
					parShowOptions = parShowResults = false;
					parAssessmentQuestionIDs = AssessmentQuestion.getAssessmentQuestionIDs(assessmentQuestions);
				}
				else {
					flashInfo = "Sorry, Simulation cannot be done. No assessment questions found for " + parAreaFieldName;
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
				
				int assScore = AssessmentQuestion.scoreAssessmentAnswers(lstAssAnswer);
				parPercentageScore = "" + assScore;
				parAssessedCompetencyLevel = AssessmentQuestion.determineAssessedCompetencyLevel(assScore);
				
				parShowResults = parShowOptions = true;
				parShowQuestions = false;
				
			}
			else {
				response.sendRedirect(LinksManager.HOME_PAGE);
				return;
			}
		}
	}
	
	ViewParameters viewParams = new ViewParameters();
	viewParams.setParameter(ParameterNames.PN_AREA_FIELD_ID, parAreaFieldID);
	viewParams.setParameter(ParameterNames.PN_AREA_FIELD_NAME, parAreaFieldName);
	viewParams.setParameter(ParameterNames.PN_ASSESSMENT_QUESTIONS_EXIST, assessmentQuestionsExist);
	viewParams.setParameter(ParameterNames.PN_SHOW_OPTIONS, parShowOptions);
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