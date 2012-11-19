<%@page import="phd.collins.imls.model.Student"%>
<%@page import="phd.collins.imls.model.StudentStudyRecord"%>
<%@page import="phd.collins.imls.util.ViewParameters"%>
<%@page import="phd.collins.imls.util.SessionManager.FlashInfoType"%>
<%@page import="phd.collins.imls.util.ParameterNames"%>
<%@page import="phd.collins.imls.model.User"%>
<%@page import="phd.collins.imls.util.LinksManager"%>
<%@page import="phd.collins.imls.util.SessionManager"%>
<%
	//Check authentication if page is to be secured
	if (!SessionManager.isAuthenticated(session)) response.sendRedirect(LinksManager.AUTH_PAGE);
	
	String flashInfo = "";
	String viewPage = "coursemodulesView.jsp";
	String parModuleID = (String)request.getParameter(ParameterNames.PN_MODULE_ID);
	
	if (parModuleID == null){
		flashInfo = "Sorry, invalid course module specified, action cannot be performed.";
		SessionManager.setFlashInfo(session, flashInfo, FlashInfoType.ERROR);
		response.sendRedirect(LinksManager.COURSE_MODULES);
		return;
	}
	else {
		boolean pss = false;
		User currentUser = SessionManager.getCurrentUser(session);
		
		if (currentUser == null) {
			response.sendRedirect(LinksManager.COURSE_MODULES);
		}
		else {
			Student student = Student.getByRegNumber(currentUser.getUserName());
			if (student != null){
				pss = StudentStudyRecord.markStudentCourseModuleCompleted(student, parModuleID);
			}
		
			if (pss){
				flashInfo = "Course Module successfully marked completed.";
				SessionManager.setFlashInfo(session, flashInfo, FlashInfoType.INFO);
				response.sendRedirect(LinksManager.COURSE_MODULES);
				return;
			}
			else {
				flashInfo = "Sorry, Module could not be marked completed at the moment. Please try again later.";
				SessionManager.setFlashInfo(session, flashInfo, FlashInfoType.ERROR);
				response.sendRedirect(LinksManager.COURSE_MODULES);
				return;
			}
		}
	}
%>
<jsp:include page="<%= LinksManager.LAYOUT_PAGE %>" flush="true">
	<jsp:param name="viewPage" value="<%= viewPage %>" />
</jsp:include>