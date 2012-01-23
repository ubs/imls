package phd.collins.imls.util;

import javax.servlet.http.HttpServletRequest;

public class FrontController {
	public static final String SLASH = "/";
	public static final String JSP_MIME = ".jsp";
	public static final String CONTROLLER_SUFFIX = "Controller";
	public static final String VIEW_SUFFIX = "View";
	
	public static String getViewPage(String strPrefix){
		return getViewFileName(strPrefix, true);
	}
	
	public static String getRequestController(HttpServletRequest request){
		String pageController = "";
		String parPage = request.getParameter("page");
		
		if (UtilGeneral.isNull(parPage) || parPage.isEmpty()){
			parPage = "index";
		}
		
		pageController = getControllerFileName(parPage, true);
		return pageController;
	}
	
	public static String getAuthenticationController(){
		return getControllerFileName("auth", true);
	}
	
	public static String getAppInitsController(){
		return getControllerFileName("init", true);
	}
	
	public static String getLayout(HttpServletRequest request){
		return new StringBuffer().append(getLayoutPath())
			.append("layout.default").append(JSP_MIME).toString();
	}
	
	public static String getLayoutPath(){		
		return new StringBuffer().append(getViewsPath()).append("layout").append(SLASH).toString();
	}
	
	public static String getControllersPathForLayoutPage(){
		return new StringBuffer().append("../../controllers").append(SLASH).toString();
	}
	
	public static String getControllersPath(){
		return new StringBuffer().append("controllers").append(SLASH).toString();
	}
	
	public static String getViewsPathForController(){
		return new StringBuffer().append("../views").append(SLASH).toString();
	}
	
	public static String getViewsPath(){
		return new StringBuffer().append("views").append(SLASH).toString();
	}
	
	private static String getControllerFileName(String strPrefix, boolean forLayout){
		String controllerPath = (forLayout) ? getControllersPathForLayoutPage() : getControllersPath();
		return new StringBuffer().append(controllerPath).append(strPrefix)
		.append(CONTROLLER_SUFFIX).append(JSP_MIME).toString();
	}
	
	private static String getViewFileName(String strPrefix, boolean forController){
		String viewsPath = (forController) ? getViewsPathForController() : getViewsPath();
		return new StringBuffer().append(viewsPath).append(strPrefix)
			.append(VIEW_SUFFIX).append(JSP_MIME).toString();
	}
}
