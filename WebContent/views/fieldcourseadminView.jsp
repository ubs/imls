<%@page import="phd.collins.imls.model.FieldCourse"%>
<%@page import="phd.collins.imls.model.AreaField"%>
<%@page import="phd.collins.imls.util.LinksManager"%>
<%@page import="phd.collins.imls.util.SessionManager"%>
<%@page import="phd.collins.imls.util.ParameterNames"%>
<%@page import="phd.collins.imls.util.ViewParameters"%>
<%@page import="phd.collins.imls.util.Info"%>
<%@page import="java.util.List"%>
<%
	String parFieldID = "", parCourseName = "", parDescription = "", parStudyOrder = "", parPassPercentage = "";
	boolean areaFieldsExist = false;

	ViewParameters viewParams = SessionManager.getViewParameters(request);

	if (viewParams != null){
		parFieldID = (String)viewParams.getParameter(ParameterNames.PN_AREA_FIELD_ID);
		parCourseName = (String)viewParams.getParameter(ParameterNames.PN_COURSE_NAME);
		parDescription = (String)viewParams.getParameter(ParameterNames.PN_DESCRIPTION);
		parStudyOrder = (String)viewParams.getParameter(ParameterNames.PN_STUDY_ORDER);
		parPassPercentage  = (String)viewParams.getParameter(ParameterNames.PN_PASS_PERCENTAGE);
		areaFieldsExist = viewParams.getBooleanParameter(ParameterNames.PN_AREA_FIELDS_EXIST);
	}
	
	//Get existing ones for listing
	List<FieldCourse> existingFieldCourses = FieldCourse.getAll();
%>
<div>
	<% if ( !areaFieldsExist ){ %>
	
	<div>&nbsp;</div>
	
	<% } else { %>
	
	<div style="margin-bottom: 10px;">
		<form name="frmAuth" id="frmAuth" method="post" action="">
            <ul class="formrow clearfix">
            	<li class="grid_3 formlabel">Area Field</li>
            	<li class="grid_3 formcontrol">
            		<select class="select" name="<%= ParameterNames.PN_AREA_FIELD_ID %>">
            			<%= AreaField.getAllAsListOptions() %>
					</select>
            	</li>
            </ul>
            
            <ul class="formrow clearfix">
            	<li class="grid_3 formlabel">Field Course Name</li>
            	<li class="grid_3 formcontrol">
            		<input class="textbox" name="<%= ParameterNames.PN_COURSE_NAME %>" value="<%= parCourseName %>" />
            	</li>
            </ul>
            
            <ul class="formrow clearfix">
            	<li class="grid_3 formlabel">Description</li>
            	<li class="grid_3 formcontrol">
            		<textarea name="<%= ParameterNames.PN_DESCRIPTION %>" class="textbox" rows="2"><%= parDescription %></textarea>
            	</li>
            </ul>
            
            <ul class="formrow clearfix">
            	<li class="grid_3 formlabel">Study Order</li>
            	<li class="grid_3 formcontrol">
            		<input class="textbox" name="<%= ParameterNames.PN_STUDY_ORDER %>" value="<%= parStudyOrder %>" />
            	</li>
            </ul>
            
            <ul class="formrow clearfix">
            	<li class="grid_3 formlabel">Pass Percentage</li>
            	<li class="grid_3 formcontrol">
            		<input class="textbox" name="<%= ParameterNames.PN_PASS_PERCENTAGE %>" value="<%= parPassPercentage %>" />
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
	
	<% if (existingFieldCourses.size() > 0) { %>
	<div id="addeditsection listsection" style="margin:20px 10px;">
		<table class="datagrid">
			<thead>
				<tr><th>Sn</th><th>Field Course</th><th>Study Order</th><th>Description</th></tr>
			</thead>
			<tbody>
			<% 
				int sn = 0;
				for (FieldCourse fieldCourse : existingFieldCourses){
			%>
				<tr>
					<td><%= ++sn %></td>
					<td><%= fieldCourse.getCourse_name() %></td>
					<td><%= fieldCourse.getStudy_order() %></td>
					<td><%= fieldCourse.getDescription() %></td>
				</tr>
			<% } %>
			</tbody>
		</table>
	</div>
	<% } %>
	
</div>
