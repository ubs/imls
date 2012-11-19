<%@page import="phd.collins.imls.model.Student"%>
<%@page import="phd.collins.imls.util.LinksManager"%>
<%@page import="phd.collins.imls.agents.actions.Authentication.AuthenticateResponse"%>
<%@page import="phd.collins.imls.util.SessionManager"%>
<%@page import="phd.collins.imls.util.ParameterNames"%>
<%@page import="phd.collins.imls.model.User"%>
<%@page import="phd.collins.imls.util.ViewParameters"%>
<%@page import="phd.collins.imls.util.Info"%>
<%
	String strCurrentUserFullName = "";
	Student student = null;

	User currentUser = SessionManager.getCurrentUser(session);
	//ViewParameters viewParams = SessionManager.getViewParameters(request);
	
	if (currentUser != null){
		student = Student.getByRegNumber(currentUser.getUserName());
		
		if (student != null) {
			strCurrentUserFullName = student.getFullname();
		}
	}
	
	String acceptAllLink = LinksManager.STUDENT_PROMPT_FOR_INITIAL_TEST + LinksManager.QMARK +
		ParameterNames.PN_ACCEPT_DEFAULT_COURSE_MODULES + "=yes";
%>
<div>
	<h1>Hello <%= strCurrentUserFullName %>...</h1>
	<div style="min-height: 100px;">
		<p>&nbsp;</p>
		<p style="line-height: 20px;">
			We have identified that you are yet to take a competency test to assess your level of study.
			You may either take the short test so you can be assigned relevant courses or accept the default 
			set of courses relevant to your chosen field of study. <br />
			Please make a choice by selecting one of the buttons below. 
		</p>
		
		<a href="<%= LinksManager.STUDENT_TAKE_INITIAL_ASSESSMENT_TEST %>" style="text-decoration:none;">
			<input class="button" name="btnTakeTest" type="button" id="btnTakeTest" value="Take Competency Test" />
		</a>
		
		<a href="<%= acceptAllLink %>" style="text-decoration:none;">
			<input class="button" name="btnAcceptDefault" type="button" id="btnAcceptDefault" value="Accept Default Course Modules" />
		</a>
		
		<p>&nbsp;</p>
	</div>
</div>