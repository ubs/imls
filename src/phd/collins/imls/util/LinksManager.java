package phd.collins.imls.util;

public class LinksManager {
	public static String LINK_BASE = "";
	
	public static String QMARK = "?";
	public static String HASH = "#";
	public static String AMPS = "&amp;";
	
	public static String LAYOUT_PAGE = "views/layout/layout.default.jsp";
	
	public static String AUTH_PAGE = "auth.jsp";
	public static String AUTH_PAGE_NO_AGENT = "authnoagent.jsp";
	public static String AUTH_CHECK_PAGE = "authcheck.jsp";
	public static String AUTH_LOGOUT_PAGE = AUTH_PAGE + QMARK + "logout=1";
	
	public static String HOME_PAGE = "index.jsp";
	public static String DASHBOARD = "index.jsp";
	public static String USERS = "users.jsp";
	public static String STUDENTS_ADMIN = "studentadmin.jsp";
	public static String MANAGE_ADMINS = "manageadmin.jsp";
	
	public static String COURSE_ADMIN = "courseadminall.jsp";
	public static String STUDY_AREAS_ADMIN = "studyareaadmin.jsp";
	public static String AREA_FIELDS_ADMIN = "areafieldadmin.jsp";
	public static String FIELD_COURSES = "fieldcourses.jsp";
	public static String FIELD_COURSES_ADMIN = "fieldcourseadmin.jsp";
	public static String COURSE_MODULES = "coursemodules.jsp";
	public static String COURSE_MODULES_ADMIN = "coursemoduleadmin.jsp";
	
	public static String MANAGE_ASSESSMENT_ADMIN = "manageassessmentadmin.jsp";
	public static String SIMULATE_ASSESSMENT_ADMIN = "simulateassessmentadmin.jsp";
	
	public static String VIEW_MODULE_CONTENT = "viewmodulecontent.jsp";
	
	public static String USER_PROFILE = "userprofile.jsp";
	public static String STUDENT_REGISTRATION = "studentregister.jsp";
	
	public static String WSIG_ADMIN_HOME = "wsig-index.jsp";
	public static String WSIG_ADMIN_TEST = "wsig-test.jsp";
}
