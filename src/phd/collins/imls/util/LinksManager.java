package phd.collins.imls.util;

public class LinksManager {
	public static String LINK_BASE = "";
	
	public static String QMARK = "?";
	public static String HASH = "#";
	public static String AMPS = "&amp;";
	
	public static String LAYOUT_PAGE = "views/layout/layout.default.jsp";
	
	public static String AUTH_PAGE = "auth.jsp";
	public static String AUTH_CHECK_PAGE = "authcheck.jsp";
	public static String AUTH_LOGOUT_PAGE = AUTH_PAGE + QMARK + "logout=1";
	
	public static String HOME_PAGE = "index.jsp";
	public static String DASHBOARD = "dashboard.jsp";
	public static String USERS = "users.jsp";
	public static String USERS_STUDENTS = "?page=students";
	public static String USERS_ADMINS = "?page=admins";
	public static String TUTORIAL_ADMIN = "?page=admins";
	
	public static String WSIG_ADMIN_HOME = "wsig-index.jsp";
	public static String WSIG_ADMIN_TEST = "wsig-test.jsp";
}
