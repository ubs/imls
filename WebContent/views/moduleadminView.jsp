<%@page import="phd.collins.imls.util.LinksManager"%>
<%@page import="phd.collins.imls.util.SessionManager"%>
<%@page import="phd.collins.imls.util.ParameterNames"%>
<%@page import="phd.collins.imls.model.User"%>
<%@page import="phd.collins.imls.util.ViewParameters"%>
<%@page import="phd.collins.imls.util.Info"%>
<%
	String parCourseID = "", parModuleName = "", parStudyOrder = "";
	String parDescription = "", parModuleContent = "", strTemp = "";
	boolean fieldCoursesExist = false;

	ViewParameters viewParams = SessionManager.getViewParameters(request);

	if (viewParams != null){
		Info.sout("In moduleadminView.jsp View Params Keys: " + viewParams.getAllParameterKeys());
	}
	
	if (viewParams != null){
		parCourseID = (String)viewParams.getParameter(ParameterNames.PN_COURSE_ID);
		parModuleName = (String)viewParams.getParameter(ParameterNames.PN_MODULE_NAME);
		parStudyOrder = (String)viewParams.getParameter(ParameterNames.PN_STUDY_ORDER);
		parDescription = (String)viewParams.getParameter(ParameterNames.PN_MODULE_DESCRIPTION);
		parModuleContent = (String)viewParams.getParameter(ParameterNames.PN_MODULE_CONTENT);
		fieldCoursesExist = Boolean.valueOf(
				viewParams.getParameter(ParameterNames.PN_FIELD_COURSES_EXIST).toString() );
	}
	
%>
<div>
	<% if ( !fieldCoursesExist ){ %>
	<div>&nbsp;</div>
	<% } else { %>
	
	<div style="margin-bottom: 10px;">
		<form name="frmAuth" id="frmAuth" method="post" action="">
            <ul class="formrow clearfix">
            	<li class="grid_1a formlabel">Field Course</li>
            	<li class="grid_3 formcontrol">
            		<input class="textbox" name="<%= ParameterNames.PN_COURSE_ID %>" type="text" value="<%= parCourseID %>" />
            	</li>
            </ul>
            
            <ul class="formrow clearfix">
            	<li class="grid_1a formlabel">Module Name</li>
            	<li class="grid_3 formcontrol">
            		<input class="textbox" name="<%= ParameterNames.PN_MODULE_NAME %>" type="password" value="<%= parModuleName %>" />
            	</li>
            </ul>
            
            <ul class="formrow clearfix">
            	<li class="grid_1a formlabel">Study Order</li>
            	<li class="grid_3 formcontrol">
            		<input class="textbox" name="<%= ParameterNames.PN_STUDY_ORDER %>" type="password" value="<%= parStudyOrder %>" />
            	</li>
            </ul>
            
            <ul class="formrow clearfix">
            	<li class="grid_1a formlabel">Description</li>
            	<li class="grid_3 formcontrol">
            		<input class="textbox" name="<%= ParameterNames.PN_MODULE_DESCRIPTION %>" type="password" value="<%= parDescription %>" />
            	</li>
            </ul>
            
            <ul class="formrow clearfix">
            	<li class="grid_1a formlabel">Module Content</li>
            	<li class="grid_3 formcontrol">
            		<input class="textbox" name="<%= ParameterNames.PN_MODULE_CONTENT %>" type="password" value="<%= parModuleContent %>" />
            	</li>
            </ul>
            
            <ul class="formrow formButtons clearfix">
            	<li class="grid_1a formlabel">&nbsp;</li>
            	<li class="grid_3 formcontrol">
            		<input class="button" name="btnSignIn" type="submit" id="btnSubmit" value="Submit" />
            		<input class="button" name="btnReset" type="reset" id="btnReset" value="Reset Fields" />
				</li>
            </ul>
		</form>
	</div>
	<% } %>
	
</div>