package phd.collins.imls.util;

import javax.servlet.http.HttpServletRequest;

public class FrontController {
	public static final String JSP_MIME = ".jsp";
	public static final String CONTROLLER_SUFFIX = "Controller";
	
	private static StringBuilder strBuilder = new StringBuilder();
	
	public static String getRequestController(HttpServletRequest request){
		String pageController = "";
		String parPage = request.getParameter("page");
		
		if (UtilGeneral.isNull(parPage) || parPage.isEmpty()){
			pageController = getRequestController();
		}
		else{
			pageController = getControllerFileName(parPage);
		}
		return pageController;
	}
	
	public static String getRequestController(){
		return getControllerFileName("index");
	}
	
	public static String getAuthenticationController(){
		return getControllerFileName("auth");
	}
	
	public static String getLayout(HttpServletRequest request){
		return getLayout(); //For Now
	}
	
	public static String getLayout(){
		return getLayoutPath() + "layout.default" + JSP_MIME;
	}
	
	public static String getLayoutPath(){
		return getViewsPath()+ "layout/"; //For Now
	}
	
	public static String getControllersPath(){
		return "controllers/"; //For Now
	}
	
	public static String getViewsPath(){
		return "views/"; //For Now
	}
	
	private static String getControllerFileName(String strPrefix){
		strBuilder.setLength(0);
		return strBuilder.append(getControllersPath())
			.append(strPrefix).append(CONTROLLER_SUFFIX).append(JSP_MIME).toString();
	}
}
