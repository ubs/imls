package phd.collins.imls.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import phd.collins.imls.exceptions.DataAccessException;
import phd.collins.imls.util.Info;

import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "users")
public class User extends UserBase implements IModelToOtherFormats {
	public enum UserTypes {
		STUDENT("STUDENT"),
		ADMIN("ADMIN");
		
		private String description;
		
		private UserTypes(String desc){
			description = desc;
		}
		
		@Override
		public String toString(){ return description; }
		
		public String getDescription() { return description; }
	}
	
	public User(){ super(); }
	
	public User(String _username, String _password, UserTypes _userType, boolean _isActive){
		super(_username, _password, _userType, _isActive);
	}
	
	public boolean updateUser(){
		boolean successStatus = false;
		try {
			DAOManager.USER_DAO.update(this);
			successStatus = true;
		} catch (Exception e){
			successStatus = false;
			e.printStackTrace();
		}
		
		return successStatus;
	}

	@Override
	public Object toXML() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object toJSONObject() {
		// TODO Auto-generated method stub
		return null;
	}
	
	//Static Field and Methods
	public static final String DEFAULT_PASSWORD = "password";
	
	public static User get(long userID){
		User user = null;
		try {
			user = DAOManager.USER_DAO.queryForId(userID);
		} catch (SQLException e) {
			user = null;
			e.printStackTrace();
		}
		return user;
	}
	
	public static User getByUsername(String username) {
		User user = null;
		try{
			user = DAOManager.USER_DAO.queryForFirst(
					DAOManager.USER_DAO.queryBuilder()
					.where().eq(User.FIELD_USERNAME, username)
					.prepare()
			);
		} catch (Exception e){
			user = null;
			e.printStackTrace();
		}
		return user;
	}
	
	public static List<User> getAll() throws DataAccessException{
		List<User> allItems = new ArrayList<User>();
		
		try {
			allItems = DAOManager.USER_DAO.queryForAll();
		} catch (SQLException e) {
			Info.serr(e.getMessage());
			throw new DataAccessException("Error retrieving users");
		}
		
		return allItems;
	}
	
	public static long create(User user){
		long userID = 0;
		try {
			DAOManager.USER_DAO.create(user);
			userID = user.getId();
		} catch (Exception e){
			userID = 0;
			e.printStackTrace();
		}
		
		return userID;
	}
	
	public static String getUsername(User user) {
		String username = "";
		
		try {
			DAOManager.USER_DAO.refresh(user); //Refresh Foreign Field
		} catch (SQLException e) { }
		
		if (user != null){
			username = user.getUserName();
			if (username == null) { username = ""; }
		}

		return username;
	}
	
	public static User AddUser(String username, String plainPassword, UserTypes userType, boolean isActive) throws DataAccessException {		
		User user = new User(username, plainPassword, userType, isActive);
        user.setLast_login_date(null);
		create(user);
		return user;
	}
	
	public static User authenticateUser(String username, String password){
		User user = null;
		try{
			user = DAOManager.USER_DAO.queryForFirst(
					DAOManager.USER_DAO.queryBuilder()
					.where().eq(User.FIELD_USERNAME, username)
					.and().eq(User.FIELD_PASSWORD, User.digestUserPassword(password))
					.prepare()
			);
		} catch (Exception e){
			user = null;
			e.printStackTrace();
		}
		return user;
	}

}


