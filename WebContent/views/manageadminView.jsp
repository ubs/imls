<%@page import="phd.collins.imls.model.User"%>
<%@page import="phd.collins.imls.model.Admin"%>
<%@page import="phd.collins.imls.util.LinksManager"%>
<%@page import="phd.collins.imls.util.SessionManager"%>
<%@page import="phd.collins.imls.util.ParameterNames"%>
<%@page import="phd.collins.imls.util.ViewParameters"%>
<%@page import="phd.collins.imls.util.Info"%>
<%@page import="java.util.List"%>
<%
	String parID = "", parFullName = "",  parContact = "", parUserID = "", parUsername = "";

	ViewParameters viewParams = SessionManager.getViewParameters(request);
	
	if (viewParams != null){
		parID = (String)viewParams.getParameter(ParameterNames.PN_ID);
		parFullName = (String)viewParams.getParameter(ParameterNames.PN_FULLNAME);
		parContact = (String)viewParams.getParameter(ParameterNames.PN_CONTACT);
		parUserID = (String)viewParams.getParameter(ParameterNames.PN_USER_ID);
		parUsername = (String)viewParams.getParameter(ParameterNames.PN_AUTH_USERNAME);
	}
	
	//Get existing ones for listing
	List<Admin> existingAdmins = Admin.getAll();
%>
<div>
	
	<div style="margin:10px;">&raquo; Add/Edit Administrator's Data</div>
	<div id="addeditsection" style="margin-bottom: 10px;">
		<form name="form1" id="form1" method="post" action="">
            <ul class="formrow clearfix">
            	<li class="grid_3 formlabel">Full Name</li>
            	<li class="grid_3 formcontrol">
            		<input class="textbox" name="<%= ParameterNames.PN_FULLNAME %>" type="text" value="<%= parFullName %>" />
            	</li>
            </ul>
            
            <ul class="formrow clearfix">
            	<li class="grid_3 formlabel">Admin Contact</li>
            	<li class="grid_3 formcontrol">
            		<input class="textbox" title="email, phone number, or any contact details" 
            			name="<%= ParameterNames.PN_CONTACT %>" type="text" value="<%= parContact %>" />
            	</li>
            </ul>
            
            <ul class="formrow clearfix">
            	<li class="grid_3 formlabel">Admin Username</li>
            	<li class="grid_3 formcontrol">
            		<input class="textbox" name="<%= ParameterNames.PN_AUTH_USERNAME %>" type="text" value="<%= parUsername %>" />
            	</li>
            </ul>
            
            <ul class="formrow formButtons clearfix">
            	<li class="grid_3 formlabel">&nbsp;</li>
            	<li class="grid_3 formcontrol">
            		<input name="<%= ParameterNames.PN_ID %>" type="hidden" value="<%= parID %>" />
            		<input class="button" name="btnSignIn" type="submit" id="btnSubmit" value="Submit" />
            		<input class="button" name="btnReset" type="reset" id="btnReset" value="Reset Fields" />
				</li>
            </ul>
		</form>
	</div>
	
	<% if (existingAdmins.size() > 0) { %>
	<div id="addeditsection listsection" style="margin:20px 10px;">
		<table class="datagrid">
			<thead>
				<tr><th>Sn</th><th>Fullname</th><th>Username</th><th>Contact</th></tr>
			</thead>
			<tbody>
			<% 
				int sn = 0;
				for (Admin admin : existingAdmins){
			%>
				<tr>
					<td><%= ++sn %></td>
					<td><%= admin.getFullname() %></td>
					<td><%= User.getUsername(admin.getUser()) %></td>
					<td><%= admin.getContact() %></td>
				</tr>
			<% } %>
			</tbody>
		</table>
	</div>
	<% } %>
	
</div>