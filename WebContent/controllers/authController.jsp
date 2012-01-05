<%@page import="phd.collins.imls.model.User"%>
<%@page import="phd.collins.imls.util.ViewParameters"%>
<%@page import="phd.collins.imls.util.SessionManager"%>
<%@page import="phd.collins.imls.util.Info"%>
<%@page import="phd.collins.imls.util.FrontController"%>
<%
	if (SessionManager.isAuthenticated()){
		//Redirect to user home based on identity
	}

	String parUsername = request.getParameter("username");
	String parPassword = request.getParameter("password");
	
	Info.sout("<Username, Password> : " + parUsername + " " + parPassword);
	
	if (parUsername != null && parPassword != null){
		Info.sout("Call Authentication Agent to Process Authentication");
	}

	String viewPage = FrontController.getViewPage("auth");
	ViewParameters viewParams = new ViewParameters();
	viewParams.setParameter("TestUser", new User("Peter", "Pan"));
	request.setAttribute("viewParameters", viewParams);
%>
<jsp:include page="<%= viewPage %>"></jsp:include>