<%@page import="phd.collins.imls.model.User"%>
<%@page import="phd.collins.imls.model.Student"%>
<%@page import="phd.collins.imls.util.LinksManager"%>
<%@page import="phd.collins.imls.util.SessionManager"%>
<%@page import="phd.collins.imls.util.SessionManager.FlashInfoType"%>
<%@page import="phd.collins.imls.util.ParameterNames"%>
<%@page import="phd.collins.imls.util.ViewParameters"%>
<%@page import="phd.collins.imls.util.Info"%>
<%
	//Check authentication if page is to be secured
	if (!SessionManager.isAuthenticated(session)) {
		response.sendRedirect(LinksManager.AUTH_PAGE);
		return;
	}

	String flashInfo = "";
	String viewPage = "studentadminView.jsp";
	String parID = "", parRegNo = "", parStudentName = "",  parAddress = "", parFieldOfStudyID = "", parUserID = "";
	
	Object parTestParam = request.getParameter(ParameterNames.PN_REG_NUMBER);
	if (parTestParam == null){
		parID = parRegNo = parStudentName = parAddress = parFieldOfStudyID = parUserID = "";
	}
	else{
		parID = request.getParameter(ParameterNames.PN_ID);
		parRegNo = request.getParameter(ParameterNames.PN_REG_NUMBER);
		parStudentName = request.getParameter(ParameterNames.PN_STUDENT_NAME);
		parAddress = request.getParameter(ParameterNames.PN_ADDRESS);
		parFieldOfStudyID = request.getParameter(ParameterNames.PN_AREA_FIELD_ID);
		parUserID = request.getParameter(ParameterNames.PN_USER_ID);
		
		//TODO: If regNo already exists, dont add
		
		Student student = Student.AddStudent(parRegNo, parStudentName, parAddress, parFieldOfStudyID);
		
		if (student != null){
			flashInfo = "Student (" + parStudentName + ") has been successfully added. <br />" +
				"User details for this student are: " +
				"Username = " + student.getUser().getUserName() + 
				", Password = " + User.DEFAULT_PASSWORD;
			
			SessionManager.setFlashInfo(session, flashInfo, FlashInfoType.INFO);
			response.sendRedirect(LinksManager.STUDENTS_ADMIN);
			return;
		}
	}
	
	ViewParameters viewParams = new ViewParameters();
	viewParams.setParameter(ParameterNames.PN_ID, parID);
	viewParams.setParameter(ParameterNames.PN_REG_NUMBER, parRegNo);
	viewParams.setParameter(ParameterNames.PN_STUDENT_NAME, parStudentName);
	viewParams.setParameter(ParameterNames.PN_ADDRESS, parAddress);
	viewParams.setParameter(ParameterNames.PN_AREA_FIELD_ID, parFieldOfStudyID);
	viewParams.setParameter(ParameterNames.PN_USER_ID, parUserID);
	SessionManager.setViewParameters(request, viewParams);
%>
<jsp:include page="<%= LinksManager.LAYOUT_PAGE %>" flush="true">
	<jsp:param name="viewPage" value="<%= viewPage %>" />
</jsp:include>