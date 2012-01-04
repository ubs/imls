<%@page import="phd.collins.imls.util.SessionManager"%>

<%
	HashMap viewParams = new HashMap();
	authParameters = request.getParameters();
	if (UtilGeneral.isNull(authParameters)){
		//
	}
%>

<jsp:include page="<%= authViewPage %>">
	<jsp:param name="viewParams" value="<%= viewParams %>" />
</jsp:include>