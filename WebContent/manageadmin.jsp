<%@page import="phd.collins.imls.model.Admin"%>
<%@page import="phd.collins.imls.model.User"%>
<%@page import="phd.collins.imls.util.LinksManager"%>
<%@page import="phd.collins.imls.util.SessionManager"%>
<%@page import="phd.collins.imls.util.SessionManager.FlashInfoType"%>
<%@page import="phd.collins.imls.util.ParameterNames"%>
<%@page import="phd.collins.imls.util.ViewParameters"%>
<%@page import="phd.collins.imls.util.Info"%>
<%
	//Check authentication if page is to be secured
	if (!SessionManager.isAuthenticated(session)) {
		response.sendRedirect(LinksManager.AUTH_PAGE);
		return;
	}

	String flashInfo = "";
	String viewPage = "manageadminView.jsp";
	String parID = "", parFullName = "",  parContact = "", parUserID = "", parUsername = "";
	
	Object parTestParam = request.getParameter(ParameterNames.PN_FULLNAME);
	if (parTestParam == null){
		parID = parFullName = parContact = parUserID = parUsername = "";
	}
	else{
		parID = request.getParameter(ParameterNames.PN_ID);
		parFullName = request.getParameter(ParameterNames.PN_FULLNAME);
		parContact = request.getParameter(ParameterNames.PN_CONTACT);
		parUserID = request.getParameter(ParameterNames.PN_USER_ID);
		parUsername = request.getParameter(ParameterNames.PN_AUTH_USERNAME);
		
		//TODO: If username already exists, dont add
		
		Admin admin = Admin.AddAdmin(parFullName, parContact, parUsername);
		
		if (admin != null){
			flashInfo = "Administrator (" + parFullName + ") has been successfully added. <br />" +
				"User details for this Administrator are: " +
				"Username = " + admin.getUser().getUserName() + 
				", Password = " + User.DEFAULT_PASSWORD;
			
			SessionManager.setFlashInfo(session, flashInfo, FlashInfoType.INFO);
			response.sendRedirect(LinksManager.MANAGE_ADMINS);
			return;
		}
	}
	
	ViewParameters viewParams = new ViewParameters();
	viewParams.setParameter(ParameterNames.PN_ID, parID);
	viewParams.setParameter(ParameterNames.PN_FULLNAME, parFullName);
	viewParams.setParameter(ParameterNames.PN_CONTACT, parContact);
	viewParams.setParameter(ParameterNames.PN_USER_ID, parUserID);
	viewParams.setParameter(ParameterNames.PN_AUTH_USERNAME, parUsername);
	SessionManager.setViewParameters(request, viewParams);
%>
<jsp:include page="<%= LinksManager.LAYOUT_PAGE %>" flush="true">
	<jsp:param name="viewPage" value="<%= viewPage %>" />
</jsp:include>