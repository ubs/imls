<%@page import="phd.collins.imls.util.Info"%>
<%@page import="phd.collins.imls.util.FrontController"%>

<%
	String pageLayout="";
	String requestController="";
	
	pageLayout = FrontController.getLayout(request);
	requestController = FrontController.getAppInitsController();
	
	Info.sout("Context Path: " + request.getContextPath() +  
			"  Layout: " + pageLayout + "  requestController: " + requestController);
%>

<jsp:include page="<%= pageLayout %>">
	<jsp:param name="requestController" value="<%= requestController %>" />
</jsp:include>