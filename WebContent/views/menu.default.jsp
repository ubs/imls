<%@page import="phd.collins.imls.util.SessionManager"%>
<%@page import="phd.collins.imls.util.LinksManager"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<ul class="sf-menu">
    <li class="current">
        <a href="<%= LinksManager.DASHBOARD %>">Dashboard</a>
    </li>
        
    <li>
        <a href="<%= LinksManager.USERS %>">Users</a>
        <ul>
            <li>
                <a href="<%= LinksManager.USERS_STUDENTS %>">Students</a>
			</li>
            <li>
                <a href="<%= LinksManager.USERS_ADMINS %>">Administrators</a>
			</li>
        </ul>
    </li>
    
    <% if (SessionManager.userIsADMIN(session)){ %>
    <li>
        <a href="<%= LinksManager.COURSE_ADMIN %>">Course Admin</a>
        <ul>
            <li>
                <a href="<%= LinksManager.STUDY_AREAS_ADMIN %>">Study Areas Admin</a>
			</li>
            <li>
                <a href="<%= LinksManager.AREA_FIELDS_ADMIN %>">Area Fields Admin</a>
			</li>
            <li>
                <a href="<%= LinksManager.FIELD_COURSES_ADMIN %>">Field Courses Admin</a>
			</li>
            <li>
                <a href="<%= LinksManager.COURSE_MODULES_ADMIN %>">Course Modules Admin</a>
			</li>
        </ul>
    </li>
    <% } %>
    
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
    
    <% if (SessionManager.isAuthenticated(session)){ %>
    <li>
        <a href="<%= LinksManager.AUTH_LOGOUT_PAGE %>" title="Click to logout">Logout</a>
    </li>
    <% } %>	
</ul>
