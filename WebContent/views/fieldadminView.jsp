<%@page import="phd.collins.imls.model.StudyArea"%>
<%@page import="phd.collins.imls.util.LinksManager"%>
<%@page import="phd.collins.imls.util.SessionManager"%>
<%@page import="phd.collins.imls.util.ParameterNames"%>
<%@page import="phd.collins.imls.util.ViewParameters"%>
<%@page import="phd.collins.imls.util.Info"%>
<%
	String parCourseID = "", parModuleName = "", parStudyOrder = "";
	String parDescription = "", parModuleContent = "", strTemp = "";
	boolean studyAreasExist = false;

	ViewParameters viewParams = SessionManager.getViewParameters(request);

	if (viewParams != null){
		Info.sout("In fieldadminView.jsp View Params Keys: " + viewParams.getAllParameterKeys());
	}
	
	if (viewParams != null){
		parCourseID = (String)viewParams.getParameter(ParameterNames.PN_COURSE_ID);
		parModuleName = (String)viewParams.getParameter(ParameterNames.PN_MODULE_NAME);
		parStudyOrder = (String)viewParams.getParameter(ParameterNames.PN_STUDY_ORDER);
		parDescription = (String)viewParams.getParameter(ParameterNames.PN_MODULE_DESCRIPTION);
		parModuleContent = (String)viewParams.getParameter(ParameterNames.PN_MODULE_CONTENT);
		studyAreasExist = viewParams.getBooleanParameter(ParameterNames.PN_STUDY_AREAS_EXIST); 
	}
	
%>
<div>
	<% if ( !studyAreasExist ){ %>
	
	<div>&nbsp;</div>
	
	<% } else { %>
	
	<div style="margin-bottom: 10px;">
		<form name="frmAuth" id="frmAuth" method="post" action="">
            <ul class="formrow clearfix">
            	<li class="grid_2 formlabel">Study Area</li>
            	<li class="grid_3 formcontrol">
            		<select class="select">
            			<%= StudyArea.getAllAsListOptions() %>
					</select>
            	</li>
            </ul>
            
            <ul class="formrow clearfix">
            	<li class="grid_2 formlabel">Field Name</li>
            	<li class="grid_3 formcontrol">
            		<input class="textbox" name="<%= ParameterNames.PN_AREA_FIELD_NAME %>" value="<%= parModuleName %>" />
            	</li>
            </ul>
            
            <ul class="formrow clearfix">
            	<li class="grid_2 formlabel">Description</li>
            	<li class="grid_3 formcontrol">
            		<input class="textbox" name="<%= ParameterNames.PN_DESCRIPTION %>" value="<%= parDescription %>" />
            	</li>
            </ul>
            
            <ul class="formrow formButtons clearfix">
            	<li class="grid_2 formlabel">&nbsp;</li>
            	<li class="grid_3 formcontrol">
            		<input class="button" name="btnSignIn" type="submit" id="btnSubmit" value="Submit" />
            		<input class="button" name="btnReset" type="reset" id="btnReset" value="Reset Fields" />
				</li>
            </ul>
		</form>
	</div>
	<% } %>
	
</div>
