package phd.collins.imls.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import phd.collins.imls.agents.actions.Authentication.AuthenticateResponse;
import phd.collins.imls.model.User;
import phd.collins.imls.model.User.UserTypes;

public class SessionManager {
	public static final String IS_AUTHENTICATED = "_isAuthenticated";
	public static final String STR_VIEW_PARAMETERS = "viewParameters";
	
	public static final String FLASH_INFO = "FlashInfo";
	public static final String CUSER_AUTHRESPONSE = "CUserAuthResponse";
	
	public class FlashInfoType{
		public static final String INFO = "normalinfo";
		public static final String NORMAL = INFO; //"normalinfo";
		public static final String WARNING = "warninginfo";
		public static final String ERROR = "errorinfo";
	}
	
	public static boolean isAuthenticated(HttpSession _httpsession){
		boolean isAuthenticated = false;
		Object ssItem = getSessionItem(_httpsession, IS_AUTHENTICATED);
		//Info.sout("Item " + IS_AUTHENTICATED + " from Session[" + _httpsession.getId() + "]: " + ssItem);
		if (ssItem != null){ isAuthenticated = (Boolean)ssItem; }
		return isAuthenticated;
	}
	
	public static void setIsAuthenticated(HttpSession _httpsession){
		setSessionItem(_httpsession, IS_AUTHENTICATED, true);
	}
	
	public static Object getUserAuthResponse(HttpSession _httpsession){
		return getSessionItem(_httpsession, CUSER_AUTHRESPONSE);
	}
	
	public static User getCurrentUser(HttpSession _httpsession){
		User currentUser = null;
		Object objAuthResponse = getUserAuthResponse(_httpsession);
		
		if (objAuthResponse != null){
			AuthenticateResponse authResponse = (AuthenticateResponse)objAuthResponse;
			String username = "" + authResponse.getUsername();
			currentUser = User.getByUsername(username);
		}
		
		return currentUser;
	}
	
	public static void setUserAuthResponse(HttpSession _httpsession, Object authResponse){
		setSessionItem(_httpsession, CUSER_AUTHRESPONSE, authResponse);
	}
	
	public static void deleteUserAuthResponse(HttpSession _httpsession){
		deleteSessionItem(_httpsession, CUSER_AUTHRESPONSE);
	}
	
	public static void logOutCurrentUser(HttpSession _httpsession){
		deleteSessionItem(_httpsession, IS_AUTHENTICATED);
		deleteUserAuthResponse(_httpsession);
	}
	
	public static boolean userIsADMIN(HttpSession _httpsession){
		boolean userIsAdmin = false;
		Object objAuthResponse = getUserAuthResponse(_httpsession);
		if (objAuthResponse != null){
			AuthenticateResponse authResponse = (AuthenticateResponse)objAuthResponse;
			userIsAdmin = (authResponse.getUserType().trim().equalsIgnoreCase(UserTypes.ADMIN.toString()));
		}
		return userIsAdmin;
	}
	
	public static boolean flashInfoExist(HttpSession _httpsession){
		boolean flashInfoExistInSession = false;
		Object ssItem = getSessionItem(_httpsession, FLASH_INFO);
		Info.sout("Item " + FLASH_INFO + " from Session[" + _httpsession.getId() + "]: " + ssItem);
		if (ssItem != null){ flashInfoExistInSession = true; }
		return flashInfoExistInSession;
	}
	
	public static Object getFlashInfo(HttpSession _httpsession){
		return getFlashInfo(_httpsession, true);
	}
	
	public static Object getFlashInfo(HttpSession _httpsession, boolean deleteInfo){
		Object ssItem = getSessionItem(_httpsession, FLASH_INFO);
		if (deleteInfo){ deleteFlashInfo(_httpsession); }
		return ssItem;
	}
	
	public static void setFlashInfo(HttpSession _httpsession, String info, String infoType){
		if (infoType == null) infoType = FlashInfoType.NORMAL;
		String[] FlashInfo = {info, infoType};
		setSessionItem(_httpsession, FLASH_INFO, FlashInfo);
	}
	
	public static void deleteFlashInfo(HttpSession _httpsession){
		deleteSessionItem(_httpsession, FLASH_INFO);
	}
	
	
	//Main setters and getters
	public static void setSessionItem(HttpSession _httpsession, String key, Object val){
		_httpsession.setAttribute(key, val);
	}
	
	public static Object getSessionItem(HttpSession _httpsession, String key){
		return _httpsession.getAttribute(key);
	}
	
	public static void deleteSessionItem(HttpSession _httpsession, String key){
		_httpsession.removeAttribute(key);
	}
	
	public static void setViewParameters(HttpServletRequest request, ViewParameters viewParams){
		request.setAttribute(STR_VIEW_PARAMETERS, viewParams);
	}
	
	public static ViewParameters getViewParameters(HttpServletRequest request){
		return (ViewParameters)request.getAttribute(STR_VIEW_PARAMETERS);
	}
	
}
