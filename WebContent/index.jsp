<%@page import="phd.collins.imls.model.StudentCompetencyTestRecords"%>
<%@page import="phd.collins.imls.model.User"%>
<%@page import="phd.collins.imls.util.SessionManager"%>
<%@page import="phd.collins.imls.util.LinksManager"%>
<%@page import="phd.collins.imls.util.Info"%>
<%
	//Check authentication if page is to be secured
	if (!SessionManager.isAuthenticated(session)) {
		response.sendRedirect(LinksManager.AUTH_PAGE);
		return;
	}

	User currentUser = SessionManager.getCurrentUser(session);
	if (currentUser.isStudent() && (!StudentCompetencyTestRecords.competencyAssessmentDone(currentUser))){
		response.sendRedirect(LinksManager.STUDENT_PROMPT_FOR_INITIAL_TEST);
		return;
	}

	String viewPage = "homeView.jsp";	
	Info.sout("In index.jsp >> Context Path: " + request.getContextPath() +  "  viewPage: " + viewPage);
%>
<jsp:include page="<%= LinksManager.LAYOUT_PAGE %>" flush="true">
	<jsp:param name="viewPage" value="<%= viewPage %>" />
</jsp:include>