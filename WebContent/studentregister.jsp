<%@page import="phd.collins.imls.model.User"%>
<%@page import="phd.collins.imls.model.Student"%>
<%@page import="phd.collins.imls.util.LinksManager"%>
<%@page import="phd.collins.imls.util.SessionManager"%>
<%@page import="phd.collins.imls.util.SessionManager.FlashInfoType"%>
<%@page import="phd.collins.imls.util.ParameterNames"%>
<%@page import="phd.collins.imls.util.ViewParameters"%>
<%@page import="phd.collins.imls.util.Info"%>
<%
	String flashInfo = "";
	String viewPage = "studentregisterView.jsp";
	String parID = "", parRegNo = "", parStudentName = "",  parAddress = "", parFieldOfStudyID = "", parUserID = "";
	
	Object parTestParam = request.getParameter(ParameterNames.PN_REG_NUMBER);
	if (parTestParam == null){
		parRegNo = Student.getRandomRegNumber();
		parID = parStudentName = parAddress = parFieldOfStudyID = parUserID = "";
	}
	else{
		parID = request.getParameter(ParameterNames.PN_ID);
		parRegNo = request.getParameter(ParameterNames.PN_REG_NUMBER);
		parStudentName = request.getParameter(ParameterNames.PN_STUDENT_NAME);
		parAddress = request.getParameter(ParameterNames.PN_ADDRESS);
		parFieldOfStudyID = request.getParameter(ParameterNames.PN_AREA_FIELD_ID);
		parUserID = request.getParameter(ParameterNames.PN_USER_ID);
		
		Student student =  Student.AddStudent(parRegNo, parStudentName, parAddress, parFieldOfStudyID);
		
		if (student != null){
			flashInfo = "Student Registration successfully completed for &quot;" + parStudentName + "&quot; <br />" +
				"Your user details are as follows: " +
				"Username = " + student.getUser().getUserName() +  ", Password = " + User.DEFAULT_PASSWORD;
			
			SessionManager.setFlashInfo(session, flashInfo, FlashInfoType.INFO);
			response.sendRedirect(LinksManager.AUTH_PAGE_NO_AGENT);
			return;
		}
		else {
			flashInfo = "Student Registration for &quot;" + parStudentName + "&quot; cannot be completed at the moment. Please try again later.<br />";
			SessionManager.setFlashInfo(session, flashInfo, FlashInfoType.ERROR);
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