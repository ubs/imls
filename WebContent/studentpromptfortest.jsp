<%@page import="phd.collins.imls.util.SessionManager.FlashInfoType"%>
<%@page import="phd.collins.imls.model.StudentStudyRecord"%>
<%@page import="phd.collins.imls.util.ParameterNames"%>
<%@page import="phd.collins.imls.util.ViewParameters"%>
<%@page import="phd.collins.imls.model.StudentCompetencyTestRecords"%>
<%@page import="phd.collins.imls.model.User"%>
<%@page import="phd.collins.imls.util.SessionManager"%>
<%@page import="phd.collins.imls.util.LinksManager"%>
<%@page import="phd.collins.imls.util.Info"%>
<%
	//Check authentication if page is to be secured
	if (!SessionManager.isAuthenticated(session)) {
		response.sendRedirect(LinksManager.AUTH_PAGE);
		return;
	}

	String flashInfo = "";
	User currentUser = SessionManager.getCurrentUser(session);
	
	if (currentUser.isStudent() && (!StudentCompetencyTestRecords.competencyAssessmentDone(currentUser))){
		//Check for parameter accept default
		String parAcceptDefaultCourseModules = (String)request.getParameter(ParameterNames.PN_ACCEPT_DEFAULT_COURSE_MODULES);
		
		if (parAcceptDefaultCourseModules != null){
			boolean pss = StudentStudyRecord.AssignDefaultCourseModulesToStudent(currentUser);
			
			if (pss) {
				flashInfo = "Default Course Modules Successfully assigned. Please proceed with your studies.";
				SessionManager.setFlashInfo(session, flashInfo, FlashInfoType.INFO);
				response.sendRedirect(LinksManager.COURSE_MODULES);
				return;
			}
			else {
				flashInfo = "Sorry, The system cannot assign default course modules at the moment, try again later.";
				SessionManager.setFlashInfo(session, flashInfo, FlashInfoType.ERROR);
				response.sendRedirect(LinksManager.HOME_PAGE);
				return;
			}
		}
		
		//Proceed to prompt student otherwise
	}
	else {
		response.sendRedirect(LinksManager.HOME_PAGE);
		return;
	}

	String viewPage = "studentpromptfortestView.jsp";
	
	//ViewParameters viewParams = new ViewParameters();
	//viewParams.setParameter(ParameterNames.PN_AREA_FIELD_ID, "");
	
	//SessionManager.setViewParameters(request, viewParams);
%>
<jsp:include page="<%= LinksManager.LAYOUT_PAGE %>" flush="true">
	<jsp:param name="viewPage" value="<%= viewPage %>" />
</jsp:include>