package phd.collins.imls.util;

import javax.servlet.http.HttpServletRequest;

public class FrontController {
	public static final String JSP_MIME = ".jsp";
	
	public static String getCurrentPage(HttpServletRequest request){
		String mainpage = "";
		String parPage = request.getParameter("page");
		
		if (UtilGeneral.isNull(parPage) || parPage.isEmpty()){
			mainpage = getCurrentPage();
		}
		else{
			mainpage = parPage + JSP_MIME;
		}
		return mainpage;
	}
	
	public static String getCurrentPage(){
		String mainpage = "index" + JSP_MIME;		
		return mainpage;
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
	
	public static String getViewsPath(){
		return "views/"; //For Now
	}
}
