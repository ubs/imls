<%@page import="phd.collins.imls.model.AreaField"%>
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
%>
<div>
	
	<div style="margin:10px;"><h3>Register as a new student</h3></div>
	<div id="addeditsection" style="margin-bottom: 10px;">
		<form name="form1" id="form1" method="post" action="">
            <ul class="formrow clearfix">
            	<li class="grid_3 formlabel">Reg. Number</li>
            	<li class="grid_3 formcontrol">
            		<input disabled="disabled" class="textbox" name="RegNumberDisplay" type="text" value="<%= parRegNo %>" />
            		<input name="<%= ParameterNames.PN_REG_NUMBER %>" type="hidden" value="<%= parRegNo %>" />
            	</li>
            </ul>
            
            <ul class="formrow clearfix">
            	<li class="grid_3 formlabel">Full Name</li>
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
	
	<div style="margin:20px 10px;">
		<h3 style="margin-bottom: 5px;">Already Registered?</h3>
		<a href="<%= LinksManager.AUTH_PAGE %>" title="Click to sign in">&raquo;Click here to sign in</a>
	</div>
	
</div>