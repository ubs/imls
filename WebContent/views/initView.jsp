<%@page import="phd.collins.imls.model.User"%>
<%@page import="phd.collins.imls.model.Admin"%>
<%@page import="phd.collins.imls.util.ParameterNames"%>
<%@page import="phd.collins.imls.util.SessionManager"%>
<%@page import="phd.collins.imls.util.ViewParameters"%>
<%@page import="phd.collins.imls.util.Info"%>
<%
	Object objParameter = null;	
	ViewParameters viewParams = SessionManager.getViewParameters(request);
	objParameter = viewParams.getParameter(ParameterNames.PN_APP_INIT_ALREADY_DONE);
	//boolean initAlreadyDone = (objParameter != null) ? (Boolean)objParameter : false;
	Admin adminUser = (Admin)viewParams.getParameter(ParameterNames.PN_APP_INIT_ADMIN_USER);
	
	Boolean initAlreadyDone = (Boolean)viewParams.getParameter(ParameterNames.PN_APP_INIT_ALREADY_DONE);
	
	Info.sout("In authView.jspView Params Keys: " + viewParams.getAllParameterKeys());
	Info.sout("In authView.jspView Params values are : " 
			+ initAlreadyDone + ", " + adminUser + ", " + initAlreadyDone.booleanValue());
%>

<div id="inituser" class="grid_16">
	<h3 class="titleheader">Application Initializations</h3>
    <p class="br">&nbsp;</p>
    
    <% if (initAlreadyDone){ %>
    
	<div id="initalreadydone" class="grid_15 warninginfo">Application Initializations already performed</div>
	
	<% } else if (9 > 25) { %>
    
    <div class="borderedgray" style="width:350px;">
    	<ul class="ultrow clearfix">
            <li class="ulthead">Default User Account</li>
        </ul>
        
    	<ul class="ultrow ulborder1 clearfix">
            <li class="grid_1a ultdata">Fullname</li>
            <li class="grid_3 ultdata"><%= adminUser.getFullname() %></li>
        </ul>
        
    	<ul class="ultrow ulborder1 clearfix">
            <li class="grid_1a ultdata">Contact</li>
            <li class="grid_3 ultdata"><%= adminUser.getContact() %></li>
        </ul>
        
    	<ul class="ultrow ulborder1 clearfix">
            <li class="grid_1a ultdata">Username</li>
            <li class="grid_3 ultdata"><%= adminUser.getUser().getUserName() %></li>
        </ul>
        
        <ul class="ultrow ulborder1 clearfix">
            <li class="grid_1a ultdata">Password</li>
            <li class="grid_3 ultdata"><%= User.DEFAULT_PASSWORD %></li>
        </ul>
        
        <ul class="ultrow ulborder1 clearfix">
            <li class="grid_1a ultdata">Access Level</li>
            <li class="grid_3 ultdata"><%= adminUser.getUser().getUser_type() %></li>
        </ul>
    </div>
    
    <% } %>
</div>

<div class="clearfix">&nbsp;</div>