<%@page import="phd.collins.imls.model.User"%>
<%@page import="phd.collins.imls.agents.actions.Authentication.AuthenticateResponse"%>
<%@page import="phd.collins.imls.util.SessionManager.FlashInfoType"%>
<%@page import="phd.collins.imls.util.ViewParameters"%>
<%@page import="phd.collins.imls.util.SessionManager"%>
<%@page import="phd.collins.imls.util.ParameterNames"%>
<%@page import="phd.collins.imls.util.LinksManager"%>
<%@page import="phd.collins.imls.util.Info"%>
<%
	String viewPage = "authView.jsp";

	String parUsername, parPassword, parLogout;
	AuthenticateResponse authResponse = null;

	//Logout, if that is what is requested
	parLogout = request.getParameter(ParameterNames.PN_AUTH_LOGOUT);
	if ((parLogout != null) && SessionManager.isAuthenticated(session)) {
		//Logout and Send 2 Auth Page
		SessionManager.logOutCurrentUser(session);
		response.sendRedirect(LinksManager.AUTH_PAGE);
		return;
	}

	//If already authenticated, send to home page
	if (SessionManager.isAuthenticated(session)) {
		response.sendRedirect(LinksManager.HOME_PAGE);
		return;
	}

	Object parTestParam = request.getParameter(ParameterNames.PN_AUTH_USERNAME);
	if (parTestParam == null) {
		parUsername = parPassword = "";
	}
	else {
		parUsername = request.getParameter(ParameterNames.PN_AUTH_USERNAME);
		parPassword = request.getParameter(ParameterNames.PN_AUTH_PASSWORD);

		Info.sout("Authentication without agent: <Username, Password> : " + parUsername + " " + parPassword);

		User user = User.authenticateUser(parUsername, parPassword);

		//If authentication is successful, do this
		if (user != null) {
			authResponse = new AuthenticateResponse(user);

			Info.sout("Authentication successful, now redirecting...");
			SessionManager.setIsAuthenticated(session);
			SessionManager.setUserAuthResponse(session, authResponse);
			response.sendRedirect(LinksManager.HOME_PAGE);
			return;
		} else {
			parPassword = "";
			Info.sout("Sorry, Authentication NOT successful.");
			String flashInfo = "Invalid Login details, Please try again.";
			SessionManager.setFlashInfo(session, flashInfo, FlashInfoType.ERROR);
		}
	}

	ViewParameters viewParams = new ViewParameters();
	viewParams.setParameter(ParameterNames.PN_AUTH_USERNAME, parUsername);
	viewParams.setParameter(ParameterNames.PN_AUTH_PASSWORD, parPassword);
	viewParams.setParameter(ParameterNames.PN_AUTH_RESPONSE, authResponse);
	viewParams.setParameter(ParameterNames.PN_AUTH_NO_AGENT, true);
	SessionManager.setViewParameters(request, viewParams);
%>
<jsp:include page="<%= LinksManager.LAYOUT_PAGE %>" flush="true">
	<jsp:param name="viewPage" value="<%= viewPage %>" />
</jsp:include>