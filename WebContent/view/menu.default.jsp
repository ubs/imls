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
    
    <li>
        <a href="<%= LinksManager.TUTORIAL_ADMIN %>">Tutorial Admin</a>
    </li>	
</ul>
