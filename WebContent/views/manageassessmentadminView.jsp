<%@page import="phd.collins.imls.model.AssessmentQuestion"%>
<%@page import="phd.collins.imls.model.CompetencyLevels"%>
<%@page import="phd.collins.imls.model.FieldCourse"%>
<%@page import="phd.collins.imls.util.LinksManager"%>
<%@page import="phd.collins.imls.util.SessionManager"%>
<%@page import="phd.collins.imls.util.ParameterNames"%>
<%@page import="phd.collins.imls.util.ViewParameters"%>
<%@page import="phd.collins.imls.util.Info"%>
<%@page import="java.util.List"%>
<%
	String parCourseID = "", parQuestion = "", parCompetencyLevelID = "";
	String parOption1 = "", parOption2 = "", parOption3 = "", parOption4 = "", parCorrectOption = "", parPoint = "";
	boolean fieldCoursesExist = false;

	ViewParameters viewParams = SessionManager.getViewParameters(request);

	if (viewParams != null){
		parCourseID = (String)viewParams.getParameter(ParameterNames.PN_FIELD_COURSE_ID);
		parQuestion = (String)viewParams.getParameter(ParameterNames.PN_QUESTION);
		parCompetencyLevelID = (String)viewParams.getParameter(ParameterNames.PN_COMPETENCY_LEVEL_ID);
		parOption1 = (String)viewParams.getParameter(ParameterNames.PN_OPTION_1);
		parOption2 = (String)viewParams.getParameter(ParameterNames.PN_OPTION_2);
		parOption3  = (String)viewParams.getParameter(ParameterNames.PN_OPTION_3);
		parOption4  = (String)viewParams.getParameter(ParameterNames.PN_OPTION_4);
		parCorrectOption  = (String)viewParams.getParameter(ParameterNames.PN_CORRECT_OPTION);
		parPoint  = (String)viewParams.getParameter(ParameterNames.PN_QUESTION_POINT);
		
		fieldCoursesExist = viewParams.getBooleanParameter(ParameterNames.PN_FIELD_COURSES_EXIST);
	}
	
	//Get existing ones for listing
	List<AssessmentQuestion> existingAssessmentQuestions = AssessmentQuestion.getAll();
%>
<div>
	<% if ( !fieldCoursesExist ){ %>
	
	<div>&nbsp;</div>
	
	<% } else { %>
	
	<div style="margin-bottom: 10px;">
		<form name="frmAuth" id="frmAuth" method="post" action="">
            <ul class="formrow clearfix">
            	<li class="grid_3 formlabel">Field Course</li>
            	<li class="grid_3 formcontrol">
            		<select class="select" name="<%= ParameterNames.PN_FIELD_COURSE_ID %>">
            			<%= FieldCourse.getAllAsListOptions() %>
					</select>
            	</li>
            </ul>
            
            <ul class="formrow clearfix">
            	<li class="grid_3 formlabel">Question</li>
            	<li class="grid_3 formcontrol">
            		<textarea name="<%= ParameterNames.PN_QUESTION %>" class="textbox" rows="2"><%= parQuestion %></textarea>
            	</li>
            </ul>
            
            <ul class="formrow clearfix">
            	<li class="grid_3 formlabel">Competency Level</li>
            	<li class="grid_3 formcontrol">
            		<select class="select" name="<%= ParameterNames.PN_COMPETENCY_LEVEL_ID %>">
            			<%= CompetencyLevels.getAllAsListOptions() %>
					</select>
            	</li>
            </ul>
            
            <ul class="formrow clearfix">
            	<li class="grid_3 formlabel">Option 1</li>
            	<li class="grid_3 formcontrol">
            		<input class="textbox" name="<%= ParameterNames.PN_OPTION_1 %>" value="<%= parOption1 %>" />
            	</li>
            </ul>
            
            <ul class="formrow clearfix">
            	<li class="grid_3 formlabel">Option 2</li>
            	<li class="grid_3 formcontrol">
            		<input class="textbox" name="<%= ParameterNames.PN_OPTION_2 %>" value="<%= parOption2 %>" />
            	</li>
            </ul>
            
            <ul class="formrow clearfix">
            	<li class="grid_3 formlabel">Option 3</li>
            	<li class="grid_3 formcontrol">
            		<input class="textbox" name="<%= ParameterNames.PN_OPTION_3 %>" value="<%= parOption3 %>" />
            	</li>
            </ul>
            
            <ul class="formrow clearfix">
            	<li class="grid_3 formlabel">Option 4</li>
            	<li class="grid_3 formcontrol">
            		<input class="textbox" name="<%= ParameterNames.PN_OPTION_4 %>" value="<%= parOption4 %>" />
            	</li>
            </ul>
            
            <ul class="formrow clearfix">
            	<li class="grid_3 formlabel">Correct Option</li>
            	<li class="grid_3 formcontrol">
	            	<select class="select" name="<%= ParameterNames.PN_CORRECT_OPTION %>">
	           			<%= AssessmentQuestion.getPossibleOptionsAsSelectList() %>
					</select>
				</li>
            </ul>
            
            <ul class="formrow clearfix">
            	<li class="grid_3 formlabel">Points Available</li>
            	<li class="grid_3 formcontrol">
            		<input class="textbox" name="<%= ParameterNames.PN_QUESTION_POINT %>" value="<%= parPoint %>" />
            	</li>
            </ul>
            
            <ul class="formrow formButtons clearfix">
            	<li class="grid_3 formlabel">&nbsp;</li>
            	<li class="grid_3 formcontrol">
            		<input class="button" name="btnSignIn" type="submit" id="btnSubmit" value="Submit" />
            		<input class="button" name="btnReset" type="reset" id="btnReset" value="Reset Fields" />
				</li>
            </ul>
		</form>
	</div>
	<% } %>
	
	<% if (existingAssessmentQuestions.size() > 0) { %>
	<div id="addeditsection listsection" style="margin:20px 10px;">
		<table class="datagrid">
			<thead>
				<tr>
					<th>Sn</th><th>Field Course</th><th>Question</th><th>C. Level</th><th>Points</th>
				</tr>
			</thead>
			<tbody>
			<% 
				int sn = 0;
				for (AssessmentQuestion assessmentQuestion : existingAssessmentQuestions){
			%>
				<tr>
					<td><%= ++sn %></td>
					<td><%= FieldCourse.getFieldCourseName(assessmentQuestion.getFieldCourse()) %></td>
					<td><%= assessmentQuestion.getQuestionSummarised() %></td>
					<td><%= CompetencyLevels.getCompetencyLevel(assessmentQuestion.getCompetencyLevel()) %></td>
					<td><%= assessmentQuestion.getPoint() %></td>
				</tr>
			<% } %>
			</tbody>
		</table>
	</div>
	<% } %>
	
</div>
