<%@page import="java.util.Date"%>
<%@page import="phd.collins.imls.model.Student"%>
<%@page import="phd.collins.imls.model.AreaField"%>
<%@page import="phd.collins.imls.model.User"%>
<%@page import="phd.collins.imls.util.SessionManager"%>
<%
	//Get current user
	User currentUser = SessionManager.getCurrentUser(session);
%>
<div>
	<% if (currentUser == null) { %>
	<div class="">
		User profile cannot be retrieved at the moment, please try again later.
	</div>
	<% } else { %>
	<div id="addeditsection listsection" style="margin:20px 10px;">
		<table id="box-table-a">
			<thead>
				<tr><th colspan="2">My Profile</th></tr>
			</thead>
			<tbody>
				<tr>
					<td>Username</td>
					<td><%= currentUser.getUserName() %></td>
				</tr>
				
				<tr>
					<td>User Level</td>
					<td><%= currentUser.getUser_type() %></td>
				</tr>
				
				<% if (currentUser.isStudent()) { %>
				<%
					Student student = Student.getByRegNumber(currentUser.getUserName());
					String fullName = ""; String areaFieldName = "";
					if (student != null) {
						fullName = student.getFullname();
						areaFieldName = AreaField.getAreaFieldName(student.getField_of_study());
					}
				%>
				<tr>
					<td>Full name</td>
					<td><%= fullName %></td>
				</tr>
				
				<tr>
					<td>Field of Study</td>
					<td><%= areaFieldName %></td>
				</tr>
				<% } %>
				
				<%
					Date dttLastLoginDate = currentUser.getLast_login_date();
					String strLastLoginDate = (dttLastLoginDate == null) ? "N/A" : dttLastLoginDate.toString();
				%>
				<tr>
					<td>Last Login Date</td>
					<td><%= strLastLoginDate %></td>
				</tr>
			</tbody>
		</table>
	</div>
	<% } %>
	
</div>