package phd.collins.imls.sandbox.ormlite;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import phd.collins.imls.appsetup.SetupRandomAssessmentQuestions;
import phd.collins.imls.exceptions.DataAccessException;
import phd.collins.imls.model.Admin;
import phd.collins.imls.model.AreaField;
import phd.collins.imls.model.AssessmentAnswer;
import phd.collins.imls.model.AssessmentQuestion;
import phd.collins.imls.model.ConnectionManager;
import phd.collins.imls.model.DAOManager;
import phd.collins.imls.model.FieldCourse;
import phd.collins.imls.model.MySQLDatabaseParams;
import phd.collins.imls.model.Student;
import phd.collins.imls.model.StudyArea;
import phd.collins.imls.model.User;
import phd.collins.imls.util.IMLSConfiguration;
import phd.collins.imls.util.Info;
import phd.collins.imls.util.UtilGeneral;

import com.j256.ormlite.support.ConnectionSource;

public class TestModels {
	private ConnectionSource connectionSource = null;

	public static void main(String args[]) throws Exception {
		new TestModels().runMe();
	}
	
	public void runMe() throws Exception {
		Info.sout("Hiya....welcome to ORM Lite Tester");
		
		Info.sout(new File("").getAbsolutePath());
		
		//runIMLSConfig();
		
		connectToDatabase();
		
		//User user1 = createTestUser();
        //createTestAdministrator(user1);
        //createTestAdministrator(createTestUser());
        //createTestStudent(createTestUser());
		//createTestStudent2();
		
		//createTestStudyArea();
		//new StudyArea().countAll();
		
		//createTestAreaField();
		//new AreaField().countAll();
		
		//createTestFieldCourse();
		//new FieldCourse().countAll();
		
		//createTest();
		//new FieldCourse().countAll();
		
		//Test Password Digest
		testDigestPassword("collins");
		
		//testGenerateRegNumbers();
		//testTemp();
		//testGetRandomIndices();
		
		//testArrayRand();
		
		//testScoringAssessment();
	}

	@SuppressWarnings("unused")
	private void testArrayRand() {
		for (int h=0; h < 50; h++){
			
			int numOfItems = UtilGeneral.getRandomNumber(1, h);
			List<Integer> lstSource = UtilGeneral.getRandomIndices(h, h, 1);
			
			Info.sout("\nTest of getRandomItemsFromList, h = " + h + 
				", Source List Length = " + lstSource.size() + ", numOfItems = " + numOfItems + 
				" : { " + 
				UtilGeneral.displayListAsString(
						UtilGeneral.getRandomItemsFromList(lstSource, numOfItems)
				) + "}"
			);
		}
	}

	@SuppressWarnings("unused")
	private void testScoringAssessment() {
		List<AssessmentQuestion> lstAssQs;
		List<AssessmentAnswer> lstAssAns = new ArrayList<AssessmentAnswer>();
		
		try {
			lstAssQs = AssessmentQuestion.getRandomAssessmentQuestions("" + UtilGeneral.getRandomNumber(7, 8));
		
			for (AssessmentQuestion aq : lstAssQs){
				lstAssAns.add(new AssessmentAnswer(
						aq.getId() + "", aq.getOption(UtilGeneral.getRandomNumber(1, 4))));
			}
			
			int intPScore = AssessmentQuestion.scoreAssessmentAnswers(lstAssAns);
			String strAssessedLevel = AssessmentQuestion.determineAssessedCompetencyLevel(intPScore);
			Info.sout("After your beautiful score of " + intPScore + 
					", here is your assessed level: " + strAssessedLevel);
			
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private void testGetRandomIndices() {
		String str = "";
		for (int k=1; k<=10; k++){
			str = UtilGeneral.displayListAsString( UtilGeneral.getRandomIndices(k, 10, 0) );
			Info.sout("Random Indices from zero = " + str);
			
			str = UtilGeneral.displayListAsString( UtilGeneral.getRandomIndices(k, 10, 1) );
			Info.sout("Random Indices from one = " + str);
		}
	}

	@SuppressWarnings("unused")
	private void testTemp() {
		try {
			new SetupRandomAssessmentQuestions().setUp();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private void testGenerateRegNumbers() {
		String randRegNumber = "";
		
		for (int ctr=0; ctr<777; ctr++){
			randRegNumber = Student.getRandomRegNumber();
			Info.sout("Random Generated Student Reg Number: " + randRegNumber + 
					", Already Exist: " + Student.regNumberAlreadyExists(randRegNumber) +
					", Length of Reg: " + randRegNumber.length());
		}
	}
	
	@SuppressWarnings("unused")
	private void createTestFieldCourse() throws DataAccessException {
		FieldCourse fieldCourse = FieldCourse.AddFieldCourse(
				"7", "Introduction to Broadcasting", "Introductory course to broadcasting and media",
				"1", "75");
		Info.sout("FieldCourse Details: " + fieldCourse.getCourse_name() + ", " + 
				fieldCourse.getDescription()+ ", " + fieldCourse.getId());
	}

	@SuppressWarnings("unused")
	private void createTestAreaField() throws DataAccessException {
		AreaField areaField = AreaField.AddAreaField("10", "Test Administration", "Test Administration");
		Info.sout("AreaField Details: " + areaField.getField_name() + ", " + 
				areaField.getDescription()+ ", " + areaField.getId());
	}

	@SuppressWarnings("unused")
	private StudyArea createTestStudyArea() throws SQLException {
		StudyArea std = new StudyArea();
		std.setArea_name("Test Study Area");
		std.setDescription("Testing Models for Study Area");
		int numRowsAffected = DAOManager.STUDY_AREA_DAO.create(std);
		Info.sout("Number of rows affected: " + numRowsAffected + " Study Area ID Created from the object: " + std.getId());
		return std;
	}

	@SuppressWarnings("unused")
	private Student createTestStudent(User user) throws SQLException, DataAccessException {
		Student student = new Student("SN111", UtilGeneral.getRandomString(), UtilGeneral.getRandomString());
		student.setDate_registered(new Date());
		student.setField_of_study(null);
		student.setUser(user);
		DAOManager.STUDENT_DAO.create(student);
		return student;
	}
	
	@SuppressWarnings("unused")
	private Student createTestStudent2() throws SQLException, DataAccessException {
		Student student = Student.AddStudent("SN991", UtilGeneral.getRandomString(), UtilGeneral.getRandomString(), "5");
		return student;
	}
	
	@SuppressWarnings("unused")
	private Admin createTestAdministrator(User user) throws SQLException {
		Admin admin = new Admin(UtilGeneral.getRandomString(), UtilGeneral.getRandomString());
        admin.setUser(user);
        DAOManager.ADMIN_DAO.create(admin);
		return admin;
	}

	@SuppressWarnings("unused")
	private User createTestUser() throws SQLException {
        User user = new User();
        user.setUsername(UtilGeneral.getRandomString().toLowerCase());
        user.setPassword("password".toLowerCase());
        user.setIs_active(true);
        user.setLast_login_date( new Date() );
        Info.sout("Before DAO.create(), User ID is: " + user.getId());
        
        DAOManager.USER_DAO.create(user);
        Info.sout("ORMLite: I have created a user oh " + user.toString() + " UserID: " + user.getId());
        
		return user;
	}
	
	//@SuppressWarnings("unused")
	private String testDigestPassword(String plainPassword){
		String digestedPassword = User.digestUserPassword(plainPassword);
		Info.sout("In testDigestPassword: [plainPassword, digestedPassword] == [" + 
				plainPassword + ", " + digestedPassword + "]");
		return digestedPassword;
	}

	private void connectToDatabase() throws Exception {
		connectionSource = ConnectionManager.getDatabaseConnection(new MySQLDatabaseParams());
		Info.sout(connectionSource.toString());
	}
	
	@SuppressWarnings("unused")
	private void runIMLSConfig(){
		IMLSConfiguration.init(null);
		IMLSConfiguration.dumpConfiguration();
	}

}
