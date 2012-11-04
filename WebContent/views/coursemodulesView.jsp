<%@page import="phd.collins.imls.util.LinksManager"%>
<%@page import="phd.collins.imls.model.AreaField"%>
<%@page import="phd.collins.imls.model.FieldCourse"%>
<%@page import="phd.collins.imls.model.Student"%>
<%@page import="phd.collins.imls.model.User"%>
<%@page import="phd.collins.imls.util.SessionManager"%>
<%@page import="phd.collins.imls.model.CourseModule"%>
<%@page import="com.j256.ormlite.dao.ForeignCollection"%>
<%
	//Get my field courses
	ForeignCollection<FieldCourse> myFieldCourses = null;
	ForeignCollection<CourseModule> myCourseModules = null;
	User currentUser = SessionManager.getCurrentUser(session);
	
	Student student = null;
	if (currentUser != null) {
		student = Student.getByRegNumber(currentUser.getUserName());
		if (student != null){
			AreaField areaField = student.getField_of_study();
			AreaField.RefreshAreaField(areaField);
			myFieldCourses = areaField.getColFieldCourses();
		}
	}
%>
<div>
	<% if (myFieldCourses.size() > 0) { %>
	<div id="addeditsection listsection" style="margin:20px 10px;">
		
		<% for (FieldCourse fieldCourse : myFieldCourses) { %>
		<h4 style="margin-bottom: 5px;">
			<%= fieldCourse.getCourse_name() %> Modules
		</h4>
		<% 
			myCourseModules = fieldCourse.getColCourseModules();
			if (myCourseModules.size() <= 0) {
		%>
		<div style="font-style: italic;"><p>No course modules found</p></div>
		<%
			} else {
		%>
		<table class="datagrid">
			<thead>
				<tr>
					<th>Sn</th><th>Course Module</th><th>Study Order</th>
					<th>Description</th><th>Actions</th>
				</tr>
			</thead>
			
			<tbody>
			<%
				int sn = 0;
				for (CourseModule courseModule : myCourseModules) {
					String strLink = LinksManager.VIEW_MODULE_CONTENT + LinksManager.QMARK 
						+ "moduleid=" + courseModule.getId();
					String viewContentLink = "<a href=" + strLink + ">View Content</a>";
			%>
				<tr>
					<td><%= ++sn %></td>
					<td><%= courseModule.getModule_name() %></td>
					<td><%= courseModule.getStudy_order() %></td>
					<td><%= courseModule.getDescription() %></td>
					<td><%= viewContentLink %></td>
				</tr>
			<% 	} %>
			</tbody>
			
		</table>
		<% } %>
		<% } %>
	</div>
	<% } %>
	
</div>
