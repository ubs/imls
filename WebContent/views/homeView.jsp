<%@page import="phd.collins.imls.util.LinksManager"%>
<%@page import="phd.collins.imls.model.StudentStudyRecord"%>
<%@page import="phd.collins.imls.model.Student"%>
<%@page import="phd.collins.imls.agents.actions.Authentication.AuthenticateResponse"%>
<%@page import="phd.collins.imls.util.SessionManager"%>
<%@page import="phd.collins.imls.util.ParameterNames"%>
<%@page import="phd.collins.imls.model.User"%>
<%@page import="phd.collins.imls.util.ViewParameters"%>
<%@page import="phd.collins.imls.util.Info"%>
<%
	String username = "", usertype = "", userLastLoginDate = "";
	AuthenticateResponse authResponse = (AuthenticateResponse)SessionManager.getUserAuthResponse(session);
	if (authResponse != null){
		usertype = authResponse.getUserType();
		username = authResponse.getUsername();
		userLastLoginDate = authResponse.getLastLoginDate();
	}
	
	User currentUser = SessionManager.getCurrentUser(session);
	Student student = null;
	boolean studentHasCompletedAllStudyRecords = false;
	
	if ((currentUser != null) && (currentUser.isStudent())) {
		student = Student.getByRegNumber(currentUser.getUserName());
		if (student != null) {
			studentHasCompletedAllStudyRecords = StudentStudyRecord.allStudyRecordsCompleted(student);
		}
	}
%>
<div>
	<h1>Welcome Home <%= username %> (Access Level: <%= usertype %>)!</h1>
	<div style="min-height:30px;">
		<p>&nbsp;</p>
		<p>Your last login date was: <%= userLastLoginDate %></p>
	</div>
	
	<% if (studentHasCompletedAllStudyRecords) { %>
	<p>&nbsp;</p>
	
	<div>
		<h3>Final Assessment Test</h3>
		<p style="line-height:20px; margin-top:10px;">
			Hi <%= student.getFullname() %>, your record shows you have completed all course modules 
			assigned to you. <br />You are therefore ready to take your final assessment. If you will like to do
			so, Please click the button below. 
		</p>
		
		<a href="<%= LinksManager.STUDENT_TAKE_FINAL_ASSESSMENT_TEST %>" style="text-decoration:none;">
			<input class="button" name="btnTakeTest" type="button" id="btnTakeTest" value="Take Final Assessment Test" />
		</a>
	</div>
	<% } %>
	
	<p>&nbsp;</p>
</div>