package phd.collins.imls.util;

import java.util.Hashtable;

import phd.collins.imls.model.Admin;
import phd.collins.imls.model.ModelTypes.UserType;
import phd.collins.imls.model.User;


public class AppInits {
	private static AppInits instance = new AppInits();
	
	private static Hashtable<String, Object> appInitsHash = new Hashtable<String, Object>();
	
	public static final String INIT_USER_STS = "appInit_User_status";
	public static final String INIT_USER_MSG = "appInit_User_Message";
	public static final String INIT_USER_ID = "appInit_User_ID";
	
	public static final String INIT_ERROR = "appInit_General_Error";
	
	private AppInits(){}
	
	public static AppInits getInstance(){
		return instance;
	}
	
	public boolean appInitsAlreadyDone(){
		boolean appInitsDone = (new Admin().countAll() != 0)  || 
			(new User().countAll() != 0); //Based on init Default Admin User
		return appInitsDone;
	}
	
	public Hashtable<String, Object> initApplication(){
		appInitsHash.clear();
		boolean status = true;
		
		//Init Default Admin User
		status = appInit_InitialiseAdminUserAccount();
		if (!status){ setErrorOccurredDuringAppInits(); }
		
		return appInitsHash;
	}
	
	private boolean appInit_InitialiseAdminUserAccount(){
		boolean status = false;
		String msg = "Error in setting up default admin user";
		long userID = initDefaultAdminUser();
		if (userID == 0){}
		else {
			long adminID = initDefaultAdministrator(User.get(userID));
			if (adminID == 0){}
			else{
				status=true; //Success
				msg = "Admin User successfully setup";
				appInitsHash.put(INIT_USER_STS, status);
				appInitsHash.put(INIT_USER_MSG, msg);
				appInitsHash.put(INIT_USER_ID, adminID);
			}
		}
		return status;
	}
	
	private long initDefaultAdminUser(){
		long userID = 0;
		User adminUser = getDefaultAdminUser();
		userID = User.create(adminUser);
		return userID;
	}
	
	private long initDefaultAdministrator(User user){
		long adminID = 0;
		Admin admin = getDefaultAdministrator(user);
		adminID = Admin.create(admin);
		return adminID;
	}
	
	private Admin getDefaultAdministrator(User user){
		Admin admin = new Admin("Administrator 1", "imls@phd.collins.edu");
		admin.setUser(user);
		return admin;
	}
	
	private User getDefaultAdminUser(){
		User defaultUser = new User("admin", User.DEFAULT_PASSWORD);
		defaultUser.setUser_type(UserType.USER_TYPE_ADMIN);
		defaultUser.setIs_active(true);
		return defaultUser;
	}
	
	private void setErrorOccurredDuringAppInits(){
		if (!appInitsHash.containsKey(INIT_ERROR)){
			appInitsHash.put(INIT_ERROR, true);
		}
	}
}
