package phd.collins.imls.util;

import javax.servlet.http.HttpSession;

public class SessionManager {
	public static final String IS_AUTHENTICATED = "_isAuthenticated";
	
	public static boolean isAuthenticated(HttpSession session){
		boolean isAuthenticated = false;
		Object ssItem = getSessionItem(session, IS_AUTHENTICATED);
		Info.sout("Item from Session[" + session.getId() + "]: " + ssItem);
		if (ssItem != null){ isAuthenticated = (Boolean)ssItem; }
		return isAuthenticated;
	}
	
	public static void setIsAuthenticated(HttpSession session){
		setSessionItem(session, IS_AUTHENTICATED, true);
	}
	
	public static void setSessionItem(HttpSession session, String key, Object val){
		session.setAttribute(key, val);
	}
	
	public static Object getSessionItem(HttpSession session, String key){
		return session.getAttribute(key);
	}
	
	public static void deleteSessionItem(HttpSession session, String key){
		session.removeAttribute(key);
	}
	
}
