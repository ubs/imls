package phd.collins.imls.sandbox.ormlite;

import java.io.File;
import java.sql.SQLException;
import java.util.Date;

import phd.collins.imls.model.Admin;
import phd.collins.imls.model.ConnectionManager;
import phd.collins.imls.model.DAOManager;
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
		new StudyArea().countAll();
		
		//User user1 = createTestUser();
        //createTestAdministrator(user1);
        //createTestAdministrator(createTestUser());
        //createTestStudent(createTestUser());
		
		//Test Password Digest
		testDigestPassword("admin");
	}

	@SuppressWarnings("unused")
	private Student createTestStudent(User user) throws SQLException {
		Student student = new Student(UtilGeneral.getRandomString(), UtilGeneral.getRandomString());
		student.setDate_registered(new Date());
		student.setField_of_study(null);
		student.setUser(user);
		DAOManager.STUDENT_DAO.create(student);
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
	
	@SuppressWarnings("unused")
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
		//IMLSConfiguration.init(new File("WebContent\\conf\\imls.properties").getAbsolutePath());
		IMLSConfiguration.dumpConfiguration();
	}

}
