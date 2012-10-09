<%@page import="phd.collins.imls.model.StudyArea"%>
<%@page import="phd.collins.imls.util.LinksManager"%>
<%@page import="phd.collins.imls.util.SessionManager"%>
<%@page import="phd.collins.imls.util.ParameterNames"%>
<%@page import="phd.collins.imls.util.ViewParameters"%>
<%@page import="phd.collins.imls.util.Info"%>
<%@page import="java.util.List"%>
<%
	String parID = "", parStudyAreaName = "", parDescription = "";
	String strTemp = "";
	boolean studyAreasExist = false;

	ViewParameters viewParams = SessionManager.getViewParameters(request);

	if (viewParams != null){
		Info.sout("In studyareaadminView.jsp View Params Keys: " + viewParams.getAllParameterKeys());
	}
	
	if (viewParams != null){
		parID = (String)viewParams.getParameter(ParameterNames.PN_ID);
		parStudyAreaName = (String)viewParams.getParameter(ParameterNames.PN_STUDY_AREA_NAME);
		parDescription = (String)viewParams.getParameter(ParameterNames.PN_DESCRIPTION);
		
		studyAreasExist = viewParams.getBooleanParameter(ParameterNames.PN_STUDY_AREAS_EXIST);
		Info.sout ("The parameter studyAreasExist is now: " + studyAreasExist);
	}
	
	//Get study areas for listing
	List<StudyArea> existingStudyAreas = StudyArea.getAll();
%>
<div>
	
	<div style="margin:10px;">&raquo; Add/Edit Study Area</div>
	<div id="addeditsection" style="margin-bottom: 10px;">
		<form name="form1" id="form1" method="post" action="">
            <ul class="formrow clearfix">
            	<li class="grid_3 formlabel">Study Area Name</li>
            	<li class="grid_3 formcontrol">
            		<input class="textbox" name="<%= ParameterNames.PN_STUDY_AREA_NAME %>" type="text" value="<%= parStudyAreaName %>" />
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
            		<input name="<%= ParameterNames.PN_ID %>" type="hidden" value="<%= parID %>" />
            		<input class="button" name="btnSignIn" type="submit" id="btnSubmit" value="Submit" />
            		<input class="button" name="btnReset" type="reset" id="btnReset" value="Reset Fields" />
				</li>
            </ul>
		</form>
	</div>
	
	<% if (existingStudyAreas.size() > 0) { %>
	<div id="addeditsection listsection" style="margin:20px 10px;">
		<table border="1">
			<thead>
				<tr><th>Sn</th><th>Study Area</th><th>Description</th></tr>
			</thead>
			<tbody>
			<% 
				int sn = 0;
				for (StudyArea studyArea : existingStudyAreas){
			%>
				<tr>
					<td><%= ++sn %></td>
					<td><%= studyArea.getArea_name() %></td>
					<td><%= studyArea.getDescription() %></td>
				</tr>
			<% } %>
			</tbody>
		</table>
	</div>
	<% } %>
	
</div>