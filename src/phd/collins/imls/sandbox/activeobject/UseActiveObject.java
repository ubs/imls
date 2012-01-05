package phd.collins.imls.sandbox.activeobject;

import java.sql.Date;

import phd.collins.imls.util.Info;

import net.java.ao.EntityManager;

public class UseActiveObject {
	
	public static void main (String args[]) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		
		// retrieves an EntityManager relevant to the specified URI
		// if available (i.e. the classpath set appropriately), the connection will be pooled
		EntityManager manager = new EntityManager("jdbc:mysql://localhost/imlsdb", "root", "");
		
		//DBParam values[] = {new DBParam("username", ""), new DBParam("password", "")};
		//Users user = manager.create(Users.class, values);
		
		Users u2[] = manager.find(Users.class, "id");
		Info.sout("See result " + u2);
		
//		Users user = manager.create(Users.class, ModelDefaults.getUserDefaults());
//		user.setUsername("Test User 1");
//		user.setPassword("12345");
//		user.setUser_type("ADMIN");
//		user.setIs_active(true);
//		user.setLast_login_date(new Date(0));
//		user.save();
		
		Info.sout("I have created a user oh @ " + new Date(new java.util.Date().getTime()));
	}
}
