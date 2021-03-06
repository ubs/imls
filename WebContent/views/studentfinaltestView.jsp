<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="phd.collins.imls.util.LinksManager"%>
<%@page import="phd.collins.imls.model.AreaField"%>
<%@page import="phd.collins.imls.model.AssessmentQuestion"%>
<%@page import="phd.collins.imls.util.SessionManager"%>
<%@page import="phd.collins.imls.util.ParameterNames"%>
<%@page import="phd.collins.imls.util.ViewParameters"%>
<%@page import="phd.collins.imls.util.Info"%>
<%
	String parAreaFieldID = "", parAreaFieldName = "";
	
	boolean parShowQuestions = false, parShowResults = false;
	
	List<AssessmentQuestion> assessmentQuestions = new ArrayList<AssessmentQuestion>();
	String parAssessmentQuestionIDs = ""; String parPercentageScore = ""; String parAssessedCompetencyLevel = "";

	ViewParameters viewParams = SessionManager.getViewParameters(request);

	if (viewParams != null){
		parAreaFieldID = (String)viewParams.getParameter(ParameterNames.PN_AREA_FIELD_ID);
		parAreaFieldName = (String)viewParams.getParameter(ParameterNames.PN_AREA_FIELD_NAME);
		parShowQuestions = viewParams.getBooleanParameter(ParameterNames.PN_SHOW_QUESTIONS);
		parShowResults = viewParams.getBooleanParameter(ParameterNames.PN_SHOW_RESULTS);
		assessmentQuestions = viewParams.getParameterAsList(ParameterNames.PN_ASSESSMENT_QUESTIONS);
		parAssessmentQuestionIDs = (String)viewParams.getParameter(ParameterNames.PN_ASSESSMENT_QUESTION_IDS);
		parPercentageScore = (String)viewParams.getParameter(ParameterNames.PN_ASSESSMENT_RESULT_PSCORE);
		parAssessedCompetencyLevel = (String)viewParams.getParameter(ParameterNames.PN_ASSESSMENT_RESULT_LEVEL);
	}
%>
<div>
	<% if (parShowQuestions && (assessmentQuestions != null)) { %>
	<div style="margin-bottom: 10px;">
		<div>
			<h3>Final Assessment Test for <%= parAreaFieldName %>.</h3>
			<br />
		</div>
		
		<form name="frmAuth" id="frmAuth" method="post" action="">
			<% int snCount = 0; String strRadioGroupName = ""; %>
			<% for (AssessmentQuestion assQ : assessmentQuestions) { %>
			<% strRadioGroupName = ParameterNames.PN_OPTION_PREFIX + assQ.getId(); %>
	           <ul class="formrow clearfix">
	           	<li class="grid_11 formlabel">
	           		<div style="margin-bottom:5px;font-weight:bold;">Question <%= ++snCount %>.</div>
	           		<div><%= assQ.getQuestion() %></div>
	           	</li>
	           </ul>
	           
	           <ul class="formrow clearfix" style="margin-bottom:10px;">
	           	<li class="grid_11 formlabel">
	           		<div style="margin-top:5px;">
	           			<label>
					      <input type="radio" name="<%= strRadioGroupName %>" value="<%= assQ.getOption1() %>" id="<%= strRadioGroupName %>1" />
					      <%= assQ.getOption1() %>
					    </label>
					    <br />
					    
					    <label>
					      <input type="radio" name="<%= strRadioGroupName %>" value="<%= assQ.getOption2() %>" id="<%= strRadioGroupName %>2" />
					      <%= assQ.getOption2() %>
					    </label>
					    <br />
					    
					    <label>
					      <input type="radio" name="<%= strRadioGroupName %>" value="<%= assQ.getOption3() %>" id="<%= strRadioGroupName %>3" />
					      <%= assQ.getOption3() %>
					    </label>
					    <br />
					    
					    <label>
					      <input type="radio" name="<%= strRadioGroupName %>" value="<%= assQ.getOption4() %>" id="<%= strRadioGroupName %>4" />
					      <%= assQ.getOption4() %>
					    </label>
	           		</div>
	           	</li>
	           </ul>
	           <% } %>
	           
	           <ul class="formrow formButtons clearfix">
	           	<li class="grid_3 formcontrol">
	           		<br />
					<input name="<%= ParameterNames.PN_AREA_FIELD_ID %>" type="hidden" value="<%= parAreaFieldID %>" />
					<input name="<%= ParameterNames.PN_ASSESSMENT_QUESTION_IDS %>" type="hidden" value="<%= parAssessmentQuestionIDs %>" />
	           		<input name="<%= ParameterNames.PN_FLOW_CONTROL_PARAM %>" type="hidden" value="<%= ParameterNames.PN_SHOW_RESULTS %>" />
	           		<input class="button" name="btnSignIn" type="submit" id="btnSubmit" value="Submit Answers" />
				</li>
	           </ul>
		</form>
		
		<p>&nbsp;</p>
	</div>
	<% } %>
	
	<% if (parShowResults) { %>
		<div style="margin-bottom: 10px;">
			<div>
				<h3>Your Final Assessment Result for <%= parAreaFieldName %>.</h3>
				<br />
				<div style="line-height: 20px;">Total Percentage Score is <span style="font-weight:bold;"><%= parPercentageScore %></span></div>
				<div style="line-height: 20px;">
					Your final assessed Competency Level for this course is <span style="font-weight:bold;"><%= parAssessedCompetencyLevel %></span>
				</div>
				
				<div style="line-height: 20px;">
					Thank you for studying with us.
				</div>
			</div>
		</div>
	<% } %>
</div>
