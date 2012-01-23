<%@page import="phd.collins.imls.util.AppInits"%>
<%@page import="phd.collins.imls.model.Admin"%>
<%@page import="java.util.Hashtable"%>
<%@page import="phd.collins.imls.util.ParameterNames"%>
<%@page import="phd.collins.imls.util.SessionManager"%>
<%@page import="phd.collins.imls.model.User"%>
<%@page import="phd.collins.imls.util.ViewParameters"%>
<%@page import="phd.collins.imls.util.Info"%>
<%
	Object objParameter = null;
	boolean initAlreadyDone = false;
	Hashtable<String, Object> appInitsResponse = new Hashtable<String, Object>();
	Admin admin = null;
	
	ViewParameters viewParams = SessionManager.getViewParameters(request);
	if (viewParams != null){
		objParameter = viewParams.getParameter(ParameterNames.PN_APP_INIT_ALREADY_DONE);
		initAlreadyDone = (objParameter != null) ? (Boolean)objParameter : false;
		
		if (initAlreadyDone){
			objParameter = viewParams.getParameter(ParameterNames.PN_APP_INIT_RESPONSE);

			if (objParameter != null){
				appInitsResponse = (Hashtable<String, Object>)objParameter;
				String item = String.valueOf(appInitsResponse.get(AppInits.INIT_USER_ID));
				long adminID = Long.valueOf(item);
				admin = Admin.get(adminID);
			}
		}
	}
	
	Info.sout("In authView.jspView Params Keys: " + viewParams.getAllParameterKeys());
%>

<div id="inituser" class="grid_16">
	<h3 class="titleheader">Application Initializations</h3>
    <p class="br">&nbsp;</p>
    
    <% if (initAlreadyDone){ %>
    
	<div id="initalreadydone" class="grid_15 warninginfo">Application Initializations already performed</div>
	
	<% } else { %>
    
    <div class="borderedgray" style="width:350px;">
    	<ul class="ultrow clearfix">
            <li class="ulthead">Default User Account</li>
        </ul>
        
    	<ul class="ultrow ulborder1 clearfix">
            <li class="grid_1a ultdata">Fullname</li>
            <li class="grid_3 ultdata"><%= admin.getFullname() %></li>
        </ul>
        
    	<ul class="ultrow ulborder1 clearfix">
            <li class="grid_1a ultdata">Contact</li>
            <li class="grid_3 ultdata"><%= admin.getContact() %></li>
        </ul>
        
    	<ul class="ultrow ulborder1 clearfix">
            <li class="grid_1a ultdata">Username</li>
            <li class="grid_3 ultdata"><%= admin.getUser().getUserName() %></li>
        </ul>
        
        <ul class="ultrow ulborder1 clearfix">
            <li class="grid_1a ultdata">Password</li>
            <li class="grid_3 ultdata"><%= User.DEFAULT_PASSWORD %></li>
        </ul>
        
        <ul class="ultrow ulborder1 clearfix">
            <li class="grid_1a ultdata">Access Level</li>
            <li class="grid_3 ultdata"><%= admin.getUser().getUser_type() %></li>
        </ul>
    </div>
    
    <% } %>
</div>

<div class="clearfix">&nbsp;</div>