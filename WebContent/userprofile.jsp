<%@page import="phd.collins.imls.util.LinksManager"%>
<%@page import="phd.collins.imls.util.SessionManager"%>
<%
	//Check authentication if page is to be secured
	if (!SessionManager.isAuthenticated(session)) {
		response.sendRedirect(LinksManager.AUTH_PAGE);
		return;
	}

	String viewPage = "userprofileView.jsp";
%>
<jsp:include page="<%= LinksManager.LAYOUT_PAGE %>" flush="true">
	<jsp:param name="viewPage" value="<%= viewPage %>" />
</jsp:include>