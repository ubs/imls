<%@page import="phd.collins.imls.model.User"%>
<%@page import="phd.collins.imls.util.LinksManager"%>
<%@page import="phd.collins.imls.util.SessionManager"%>
<%
	//Check authentication if page is to be secured
	if (!SessionManager.isAuthenticated(session)) response.sendRedirect(LinksManager.AUTH_PAGE);

	User currentUser = SessionManager.getCurrentUser(session);
	if ((currentUser == null) || (!currentUser.isStudent())){
		response.sendRedirect(LinksManager.DASHBOARD);
		return;
	}

	String viewPage = "coursemodulesView.jsp";
%>
<jsp:include page="<%= LinksManager.LAYOUT_PAGE %>" flush="true">
	<jsp:param name="viewPage" value="<%= viewPage %>" />
</jsp:include>