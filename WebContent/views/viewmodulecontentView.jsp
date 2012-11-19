<%@page import="phd.collins.imls.model.CompetencyLevels"%>
<%@page import="phd.collins.imls.util.LinksManager"%>
<%@page import="phd.collins.imls.util.ParameterNames"%>
<%@page import="phd.collins.imls.util.SessionManager"%>
<%@page import="phd.collins.imls.util.ViewParameters"%>
<%@page import="phd.collins.imls.model.CourseModule"%>
<%
	String parModuleID = "";
	String strMarkCompleteLink = "";
	CourseModule courseModule = null;
	
	ViewParameters viewParams = SessionManager.getViewParameters(request);

	if (viewParams != null){
		parModuleID = (String)viewParams.getParameter(ParameterNames.PN_MODULE_ID);
		Long moduleID = Long.parseLong(parModuleID);
		courseModule = CourseModule.get(moduleID);
		
		strMarkCompleteLink = LinksManager.MARK_MODULE_CONTENT_COMPLETED + LinksManager.QMARK 
			+ "moduleid=" + parModuleID;
	}
%>
<div>
	<p style="float: right;margin: 10px 5px 10px auto;">
		<a href="<%= LinksManager.COURSE_MODULES %>">Back to Course Modules</a>
	</p>
	
	<% if (courseModule == null) { %>
		<div></div>
	<% } else { %>
		<p><span class="caption">Course Module Name</span></p>
		<p><%= courseModule.getModule_name() %></p>
		
		<p><span class="caption">Competency Level</span></p>
		<p><%= CompetencyLevels.getCompetencyLevel(courseModule.getCompetencyLevel()) %></p>
		
		<p><span class="caption">Course Module Content</span></p>
		<p style="line-height: 15px;">
			<%= courseModule.getModule_content() %>
		</p>
		
		<p>&nbsp;</p>
		
		<p>
			<a href="<%= strMarkCompleteLink %>" style="text-decoration:none;" title="Click to mark this module as completed">
				<input class="button" name="btnMarkCompleted" type="button" id="btnMarkCompleted" value="Mark As Completed" />
			</a>
		</p>
		
		<p>&nbsp;</p>
		
	<% } %>
</div>
