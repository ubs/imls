<%@page import="phd.collins.imls.util.FrontController" %>

<% 
	String currentPage = FrontController.getCurrentPage(request);
	String pageLayout = FrontController.getLayout(request); 
%>

<jsp:include page="<%= pageLayout %>">
	<jsp:param name="currentPage" value="<%= currentPage %>" />
</jsp:include>