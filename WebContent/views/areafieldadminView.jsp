<%@page import="phd.collins.imls.model.AreaField"%>
<%@page import="phd.collins.imls.model.StudyArea"%>
<%@page import="phd.collins.imls.util.LinksManager"%>
<%@page import="phd.collins.imls.util.SessionManager"%>
<%@page import="phd.collins.imls.util.ParameterNames"%>
<%@page import="phd.collins.imls.util.ViewParameters"%>
<%@page import="phd.collins.imls.util.Info"%>
<%@page import="java.util.List"%>
<%
	String parStudyAreaID = "", parFieldName = "", parDescription = "";
	boolean studyAreasExist = false;

	ViewParameters viewParams = SessionManager.getViewParameters(request);

	if (viewParams != null){
		parStudyAreaID = (String)viewParams.getParameter(ParameterNames.PN_STUDY_AREA_ID);
		parFieldName = (String)viewParams.getParameter(ParameterNames.PN_AREA_FIELD_NAME);
		parDescription = (String)viewParams.getParameter(ParameterNames.PN_DESCRIPTION);
		studyAreasExist = viewParams.getBooleanParameter(ParameterNames.PN_STUDY_AREAS_EXIST); 
	}
	
	//Get existing ones for listing
	List<AreaField> existingAreaFields = AreaField.getAll();
%>
<div>
	<% if ( !studyAreasExist ){ %>
	
	<div>&nbsp;</div>
	
	<% } else { %>
	
	<div style="margin-bottom: 10px;">
		<form name="frmAuth" id="frmAuth" method="post" action="">
            <ul class="formrow clearfix">
            	<li class="grid_3 formlabel">Study Area</li>
            	<li class="grid_3 formcontrol">
            		<select class="select" name="<%= ParameterNames.PN_STUDY_AREA_ID %>">
            			<%= StudyArea.getAllAsListOptions() %>
					</select>
            	</li>
            </ul>
            
            <ul class="formrow clearfix">
            	<li class="grid_3 formlabel">Area Field Name</li>
            	<li class="grid_3 formcontrol">
            		<input class="textbox" name="<%= ParameterNames.PN_AREA_FIELD_NAME %>" value="<%= parFieldName %>" />
            	</li>
            </ul>
            
            <ul class="formrow clearfix">
            	<li class="grid_3 formlabel">Description</li>
            	<li class="grid_3 formcontrol">
            		<textarea name="<%= ParameterNames.PN_DESCRIPTION %>" class="textbox" rows="2"><%= parDescription %></textarea>
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
	
	<% if (existingAreaFields.size() > 0) { %>
	<div id="addeditsection listsection" style="margin:20px 10px;">
		<table class="datagrid">
			<thead>
				<tr><th>Sn</th><th>Area Field</th><th>Study Area</th><th>Description</th></tr>
			</thead>
			<tbody>
			<% 
				int sn = 0;
				for (AreaField areaField : existingAreaFields){
			%>
				<tr>
					<td><%= ++sn %></td>
					<td><%= areaField.getField_name() %></td>
					<td><%= areaField.getStudyAreaName() %></td>
					<td><%= areaField.getDescription() %></td>
				</tr>
			<% } %>
			</tbody>
		</table>
	</div>
	<% } %>
	
</div>
