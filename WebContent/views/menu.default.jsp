<%@page import="phd.collins.imls.util.SessionManager"%>
<%@page import="phd.collins.imls.util.LinksManager"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%
	boolean currentUserIsAdmin = SessionManager.userIsADMIN(session);
%>
<ul class="sf-menu">
    <li class="current">
        <a href="<%= LinksManager.DASHBOARD %>">Dashboard</a>
    </li>
        
    <li>
        <a href="<%= LinksManager.HASH %>">Users</a>
        <ul>
        	<li>
                <a href="<%= LinksManager.USER_PROFILE %>">My Profile</a>
			</li>
			
        	<% if (currentUserIsAdmin){ %>
            <li>
                <a href="<%= LinksManager.STUDENTS_ADMIN %>">Students</a>
			</li>
            <li>
                <a href="<%= LinksManager.MANAGE_ADMINS %>">Administrators</a>
			</li>
			<% } %>
			
			<li>
                <a href="<%= LinksManager.AUTH_LOGOUT_PAGE %>">Logout</a>
			</li>
        </ul>
    </li>
    
    <% if (SessionManager.userIsADMIN(session)){ %>
    <li>
        <a href="<%= LinksManager.HASH %>">Course Admin</a>
        <ul>
            <li>
                <a href="<%= LinksManager.STUDY_AREAS_ADMIN %>">Study Areas</a>
			</li>
            <li>
                <a href="<%= LinksManager.AREA_FIELDS_ADMIN %>">Area Fields</a>
			</li>
            <li>
                <a href="<%= LinksManager.FIELD_COURSES_ADMIN %>">Field Courses</a>
			</li>
            <li>
                <a href="<%= LinksManager.COURSE_MODULES_ADMIN %>">Course Modules</a>
			</li>
        </ul>
    </li>
    <% } else { %>
    <li>
        <a href="<%= LinksManager.HASH %>">Course Modules</a>
        <ul> 
            <li>
                <a href="<%= LinksManager.FIELD_COURSES %>">My Courses</a>
			</li>
            <li>
                <a href="<%= LinksManager.COURSE_MODULES %>">My Course Modules</a>
			</li>
        </ul>
    </li>
    <% } %>
    
    <% if (currentUserIsAdmin) { %>
    <li>
        <a href="<%= LinksManager.WSIG_ADMIN_HOME %>">WSIG Admin</a>
        <ul>
            <li>
                <a href="wsig-index.jsp">WSIG Home</a>
			</li>
            <li>
                <a href="<%= LinksManager.WSIG_ADMIN_TEST %>">WSIG Test</a>
			</li>
        </ul>
    </li>
    <% } %>
    
    <% if (SessionManager.isAuthenticated(session)){ %>
    <li>
        <a href="<%= LinksManager.AUTH_LOGOUT_PAGE %>" title="Click to logout">Logout</a>
    </li>
    <% } %>	
</ul>
