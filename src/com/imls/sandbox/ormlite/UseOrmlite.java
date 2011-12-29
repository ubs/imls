package com.imls.sandbox.ormlite;

import java.sql.SQLException;
import java.util.Date;

import phd.collins.imls.model.Admin;
import phd.collins.imls.model.ConnectionManager;
import phd.collins.imls.model.DAOManager;
import phd.collins.imls.model.MySQLDatabaseParams;
import phd.collins.imls.model.Student;
import phd.collins.imls.model.User;
import phd.collins.imls.util.Info;
import phd.collins.imls.util.UtilGeneral;

import com.j256.ormlite.support.ConnectionSource;

public class UseOrmlite {
	private ConnectionSource connectionSource = null;

	public static void main(String args[]) throws Exception {
		new UseOrmlite().runMe();
	}
	
	public void runMe() throws Exception {
		Info.sout("Hiya....welcome to ORM Lite Tester");
		
		connectToDatabase();
		
		//User user1 = createTestUser();
        //createTestAdministrator(user1);
        //createTestAdministrator(createTestUser());
        createTestStudent(createTestUser());
	}

	private Student createTestStudent(User user) throws SQLException {
		Student student = new Student(UtilGeneral.getRandomString(), UtilGeneral.getRandomString());
		student.setDate_registered(new Date());
		student.setField_of_study(null);
		student.setUser(user);
		DAOManager.STUDENT_DAO.create(student);
		return student;
	}
	
	private Admin createTestAdministrator(User user) throws SQLException {
		Admin admin = new Admin(UtilGeneral.getRandomString(), UtilGeneral.getRandomString());
        admin.setUser(user);
        DAOManager.ADMIN_DAO.create(admin);
		return admin;
	}

	private User createTestUser() throws SQLException {
        User user = new User();
        user.setUsername(UtilGeneral.getRandomString());
        user.setPassword(UtilGeneral.getRandomStringLowerCase());
        user.setIs_active(true);
        user.setLast_login_date( new Date() );
        
        DAOManager.USER_DAO.create(user);
        Info.sout("ORMLite: I have created a user oh " + user.toString() + " UserID: " + user.getId());
        
		return user;
	}

	private void connectToDatabase() throws Exception {
		connectionSource = ConnectionManager.getDatabaseConnection(new MySQLDatabaseParams());
		Info.sout(connectionSource.toString());
	}

}
