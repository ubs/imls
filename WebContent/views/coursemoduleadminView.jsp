<%@page import="phd.collins.imls.model.CourseModule"%>
<%@page import="phd.collins.imls.model.FieldCourse"%>
<%@page import="phd.collins.imls.util.LinksManager"%>
<%@page import="phd.collins.imls.util.SessionManager"%>
<%@page import="phd.collins.imls.util.ParameterNames"%>
<%@page import="phd.collins.imls.util.ViewParameters"%>
<%@page import="phd.collins.imls.util.Info"%>
<%@page import="java.util.List"%>
<%
	String parCourseID = "", parModuleName = "", parStudyOrder = "", parDescription = "", parModuleContent = "";
	boolean fieldCoursesExist = false;

	ViewParameters viewParams = SessionManager.getViewParameters(request);

	if (viewParams != null){
		parCourseID = (String)viewParams.getParameter(ParameterNames.PN_FIELD_COURSE_ID);
		parModuleName = (String)viewParams.getParameter(ParameterNames.PN_MODULE_NAME);
		parStudyOrder = (String)viewParams.getParameter(ParameterNames.PN_STUDY_ORDER);
		parDescription = (String)viewParams.getParameter(ParameterNames.PN_DESCRIPTION);
		parModuleContent  = (String)viewParams.getParameter(ParameterNames.PN_MODULE_CONTENT);
		fieldCoursesExist = viewParams.getBooleanParameter(ParameterNames.PN_FIELD_COURSES_EXIST);
	}
	
	//Get existing ones for listing
	List<CourseModule> existingCourseModules = CourseModule.getAll();
%>
<div>
	<% if ( !fieldCoursesExist ){ %>
	
	<div>&nbsp;</div>
	
	<% } else { %>
	
	<div style="margin-bottom: 10px;">
		<form name="frmAuth" id="frmAuth" method="post" action="">
            <ul class="formrow clearfix">
            	<li class="grid_3 formlabel">Field Course</li>
            	<li class="grid_3 formcontrol">
            		<select class="select" name="<%= ParameterNames.PN_FIELD_COURSE_ID %>">
            			<%= FieldCourse.getAllAsListOptions() %>
					</select>
            	</li>
            </ul>
            
            <ul class="formrow clearfix">
            	<li class="grid_3 formlabel">Course Module Name</li>
            	<li class="grid_3 formcontrol">
            		<input class="textbox" name="<%= ParameterNames.PN_MODULE_NAME %>" value="<%= parModuleName %>" />
            	</li>
            </ul>
            
            <ul class="formrow clearfix">
            	<li class="grid_3 formlabel">Study Order</li>
            	<li class="grid_3 formcontrol">
            		<input class="textbox" name="<%= ParameterNames.PN_STUDY_ORDER %>" value="<%= parStudyOrder %>" />
            	</li>
            </ul>
            
            <ul class="formrow clearfix">
            	<li class="grid_3 formlabel">Description</li>
            	<li class="grid_3 formcontrol">
            		<textarea name="<%= ParameterNames.PN_DESCRIPTION %>" class="textbox" rows="2"><%= parDescription %></textarea>
            	</li>
            </ul>
            
            <ul class="formrow clearfix">
            	<li class="grid_3 formlabel">Module Content</li>
            	<li class="grid_3 formcontrol">
            		<textarea name="<%= ParameterNames.PN_MODULE_CONTENT %>" class="textbox" rows="2"><%= parModuleContent %></textarea>
            	</li>
            </ul>
            
            <ul class="formrow formButtons clearfix">
            	<li class="grid_3 formlabel">&nbsp;</li>
            	<li class="grid_3 formcontrol">
            		<input class="button" name="btnSignIn" type="submit" id="btnSubmit" value="Submit" />
            		<input class="button" name="btnReset" type="reset" id="btnReset" value="Reset Fields" />
				</li>
            </ul>
		</form>
	</div>
	<% } %>
	
	<% if (existingCourseModules.size() > 0) { %>
	<div id="addeditsection listsection" style="margin:20px 10px;">
		<table class="datagrid">
			<thead>
				<tr><th>Sn</th><th>Course Module</th><th>Study Order</th><th>Description</th></tr>
			</thead>
			<tbody>
			<% 
				int sn = 0;
				for (CourseModule courseModule : existingCourseModules){
			%>
				<tr>
					<td><%= ++sn %></td>
					<td><%= courseModule.getModule_name() %></td>
					<td><%= courseModule.getStudy_order() %></td>
					<td><%= courseModule.getDescription() %></td>
				</tr>
			<% } %>
			</tbody>
		</table>
	</div>
	<% } %>
	
</div>
