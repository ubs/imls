<%@page import="phd.collins.imls.util.ViewParameters"%>
<%@page import="phd.collins.imls.util.SessionManager.FlashInfoType"%>
<%@page import="phd.collins.imls.util.ParameterNames"%>
<%@page import="phd.collins.imls.model.User"%>
<%@page import="phd.collins.imls.util.LinksManager"%>
<%@page import="phd.collins.imls.util.SessionManager"%>
<%
	//Check authentication if page is to be secured
	if (!SessionManager.isAuthenticated(session)) response.sendRedirect(LinksManager.AUTH_PAGE);
	
	String flashInfo = "";
	String viewPage = "viewmodulecontentView.jsp";
	String parModuleID = request.getParameter(ParameterNames.PN_MODULE_ID);
	
	//User currentUser = SessionManager.getCurrentUser(session);
	if (parModuleID == null){
		flashInfo = "Sorry, invalid course module specified, content cannot be retrieved.";
		
		SessionManager.setFlashInfo(session, flashInfo, FlashInfoType.ERROR);
		response.sendRedirect(LinksManager.COURSE_MODULES);
		return;
	}
	
	ViewParameters viewParams = new ViewParameters();
	viewParams.setParameter(ParameterNames.PN_MODULE_ID, parModuleID);
	SessionManager.setViewParameters(request, viewParams);
%>
<jsp:include page="<%= LinksManager.LAYOUT_PAGE %>" flush="true">
	<jsp:param name="viewPage" value="<%= viewPage %>" />
</jsp:include>