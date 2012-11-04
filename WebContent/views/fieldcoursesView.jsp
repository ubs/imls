<%@page import="java.util.ArrayList"%>
<%@page import="phd.collins.imls.model.FieldCourse"%>
<%@page import="phd.collins.imls.model.Student"%>
<%@page import="phd.collins.imls.util.SessionManager"%>
<%@page import="phd.collins.imls.model.User"%>
<%@page import="java.util.List"%>
<%
	//Get my field courses
	List<FieldCourse> myFieldCourses = new ArrayList<FieldCourse>();
	User currentUser = SessionManager.getCurrentUser(session);
	
	//Student student = null;
	if (currentUser != null) {
		Student student = Student.getByRegNumber(currentUser.getUserName());
		if (student != null){
			myFieldCourses = FieldCourse.getMyFieldCourses(student.getField_of_study());
		}
	}
%>
<div>
	<% if (myFieldCourses.size() > 0) { %>
	<div id="addeditsection listsection" style="margin:20px 10px;">
		<table class="datagrid">
			<thead>
				<tr>
					<th>Sn</th><th>Field Course</th><th>Study Order</th>
					<th>Pass %</th><th>Description</th>
				</tr>
			</thead>
			<tbody>
			<% 
				int sn = 0;
				for (FieldCourse fieldCourse : myFieldCourses){
			%>
				<tr>
					<td><%= ++sn %></td>
					<td><%= fieldCourse.getCourse_name() %></td>
					<td><%= fieldCourse.getStudy_order() %></td>
					<td><%= fieldCourse.getPass_percentage() %></td>
					<td><%= fieldCourse.getDescription() %></td>
				</tr>
			<% } %>
			</tbody>
		</table>
	</div>
	<% } %>
	
</div>
