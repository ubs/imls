<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="phd.collins.imls.model.StudentStudyRecord"%>
<%@page import="phd.collins.imls.model.CompetencyLevels"%>
<%@page import="phd.collins.imls.util.LinksManager"%>
<%@page import="phd.collins.imls.model.AreaField"%>
<%@page import="phd.collins.imls.model.FieldCourse"%>
<%@page import="phd.collins.imls.model.Student"%>
<%@page import="phd.collins.imls.model.User"%>
<%@page import="phd.collins.imls.util.SessionManager"%>
<%@page import="phd.collins.imls.model.CourseModule"%>
<%@page import="com.j256.ormlite.dao.ForeignCollection"%>
<%
	ForeignCollection<StudentStudyRecord> myStudyRecords = null;
	
	Map<Long, List<StudentStudyRecord>> myStudyRecordsGrouped = null;
	
	User currentUser = SessionManager.getCurrentUser(session);
	
	Student student = null;
	if (currentUser != null) {
		student = Student.getByRegNumber(currentUser.getUserName());
		
		if (student != null){
			myStudyRecords = student.getColMyStudyRecords();
			myStudyRecordsGrouped = StudentStudyRecord.groupStudentStudyRecordsByFieldCourse(myStudyRecords);
		}
	}
%>
<div>
	<% if (myStudyRecordsGrouped.size() <= 0) { %>
	<div id="addeditsection listsection" style="margin:20px 10px;">
		<p style="line-height:25px;">
			No course modules found for you, Please ensure you have taken the 
			competency test and have been assigned course modules.
		</p>
	</div>
	<% } else { %>
	<div id="addeditsection listsection" style="margin:20px 10px;">
		<%
			for (Map.Entry<Long, List<StudentStudyRecord>> entry : myStudyRecordsGrouped.entrySet()) {
			    Long key = entry.getKey();
			    List<StudentStudyRecord> lstSSR = entry.getValue();
			    FieldCourse fc = FieldCourse.get(key);
		%>
		<h4 style="margin-bottom: 5px;">
			<%= fc.getCourse_name() %> Modules
		</h4>
		<% 
			if (lstSSR.size() <= 0) {
		%>
		<div style="font-style: italic;"><p>No course modules found</p></div>
		<%
			} else {
		%>
		<table class="datagrid">
			<thead>
				<tr>
					<th>Sn</th><th>Course Module</th><th>Study Order</th>
					<th>C. Level</th><th>Description</th><th>Actions</th>
				</tr>
			</thead>
			
			<tbody>
			<%
				int sn = 0;
				for (StudentStudyRecord ssr : lstSSR) {
					CourseModule courseModule = CourseModule.refresh(ssr.getCourseModule());
					
					String strLink = LinksManager.VIEW_MODULE_CONTENT + LinksManager.QMARK 
						+ "moduleid=" +  courseModule.getId();
					String viewContentLink = "<a href=" + strLink + 
						" title=\"Click to view module content\">View</a>";
					
					String strMarkCompleteLink = "";
					if (ssr.isNotCompleted()) { 
						strMarkCompleteLink = LinksManager.MARK_MODULE_CONTENT_COMPLETED + LinksManager.QMARK 
							+ "moduleid=" + courseModule.getId();
						
						strMarkCompleteLink = "<a href=" + strMarkCompleteLink + 
							" title=\"Click to mark this module as completed\">Completed</a>";
					}
			%>
				<tr>
					<td><%= ++sn %></td>
					<td><%= courseModule.getModule_name() %></td>
					<td><%= courseModule.getStudy_order() %></td>
					<td><%= CompetencyLevels.getCompetencyLevel(courseModule.getCompetencyLevel()) %></td>
					<td><%= courseModule.getDescription() %></td>
					<td width="120">
						<%= viewContentLink %> |
						<%= strMarkCompleteLink %>   
					</td>
				</tr>
			<% 	} %>
			</tbody>
			
		</table>
		<% } %>
		<% } %>
	</div>
	<% } %>
	
</div>
