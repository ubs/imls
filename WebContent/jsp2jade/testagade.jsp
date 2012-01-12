<%@ taglib uri="/WEB-INF/jade.tld" prefix="jade" %>
<jade:container id="moncontainer" />

<jade:useAgent id="snooper" classname="examples.jsp.Snooper" container="moncontainer" scope="application"/>

<% snooper.putO2AObject(request.getRemoteHost()+" "+(new java.util.Date())+" "+request.getRequestURI()+" METHOD 1",false); %>

<jade:useAgent id="snooper2" classname="examples.jsp.Snooper" container="moncontainer" scope="application"/>

<!-- BEWARE HERE: CR used to format the code are sent to the agent-->
<jade:sendObject id="snooper2" blocking="false"><%= request.getRemoteHost()+" "+(new java.util.Date())+" "+request.getRequestURI()+" METHOD2" %></jade:sendObject>
<HTML>
	<BODY>
	It works !!!!<br>Messages sent.<br>
	<%=snooper%>
	</BODY>
</HTML>
