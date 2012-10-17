<%@page import="phd.collins.imls.model.AreaField"%>
<%@page import="phd.collins.imls.model.Student"%>
<%@page import="phd.collins.imls.util.LinksManager"%>
<%@page import="phd.collins.imls.util.SessionManager"%>
<%@page import="phd.collins.imls.util.ParameterNames"%>
<%@page import="phd.collins.imls.util.ViewParameters"%>
<%@page import="phd.collins.imls.util.Info"%>
<%@page import="java.util.List"%>
<%
	String parID = "", parRegNo = "", parStudentName = "",  parAddress = "", parFieldOfStudyID = "", parUserID = "";

	ViewParameters viewParams = SessionManager.getViewParameters(request);
	
	if (viewParams != null){
		parID = (String)viewParams.getParameter(ParameterNames.PN_ID);
		parRegNo = (String)viewParams.getParameter(ParameterNames.PN_REG_NUMBER);
		parStudentName = (String)viewParams.getParameter(ParameterNames.PN_STUDENT_NAME);
		parAddress = (String)viewParams.getParameter(ParameterNames.PN_ADDRESS);
		parFieldOfStudyID = (String)viewParams.getParameter(ParameterNames.PN_AREA_FIELD_ID);
		parUserID = (String)viewParams.getParameter(ParameterNames.PN_USER_ID);
	}
	
	//Get existing ones for listing
	List<Student> existingStudents = Student.getAll();
%>
<div>
	
	<div style="margin:10px;">&raquo; Add/Edit Student Data</div>
	<div id="addeditsection" style="margin-bottom: 10px;">
		<form name="form1" id="form1" method="post" action="">
            <ul class="formrow clearfix">
            	<li class="grid_3 formlabel">Student Reg. Number</li>
            	<li class="grid_3 formcontrol">
            		<input class="textbox" name="<%= ParameterNames.PN_REG_NUMBER %>" type="text" value="<%= parRegNo %>" />
            	</li>
            </ul>
            
            <ul class="formrow clearfix">
            	<li class="grid_3 formlabel">Student Name</li>
            	<li class="grid_3 formcontrol">
            		<input class="textbox" name="<%= ParameterNames.PN_STUDENT_NAME %>" type="text" value="<%= parStudentName %>" />
            	</li>
            </ul>
            
            <ul class="formrow clearfix">
            	<li class="grid_3 formlabel">Field of Study</li>
            	<li class="grid_3 formcontrol">
            		<select class="select" name="<%= ParameterNames.PN_AREA_FIELD_ID %>">
            			<%= AreaField.getAllAsListOptions() %>
					</select>
            	</li>
            </ul>
            
            <ul class="formrow clearfix">
            	<li class="grid_3 formlabel">Contact Address</li>
            	<li class="grid_3 formcontrol">
            		<textarea name="<%= ParameterNames.PN_ADDRESS %>" class="textbox" rows="2"><%= parAddress %></textarea>
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
	
	<% if (existingStudents.size() > 0) { %>
	<div id="addeditsection listsection" style="margin:20px 10px;">
		<table class="datagrid">
			<thead>
				<tr><th>Sn</th><th>Reg number</th><th>Full Name</th><th>Field of Study</th></tr>
			</thead>
			<tbody>
			<% 
				int sn = 0;
				for (Student student : existingStudents){
			%>
				<tr>
					<td><%= ++sn %></td>
					<td><%= student.getRegno() %></td>
					<td><%= student.getFullname() %></td>
					<td><%= AreaField.getAreaFieldName(student.getField_of_study()) %></td>
				</tr>
			<% } %>
			</tbody>
		</table>
	</div>
	<% } %>
	
</div>