package phd.collins.imls.model;

import java.sql.SQLException;

import phd.collins.imls.util.Info;

import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "users")
public class User extends UserBase implements IModelToOtherFormats {
	public User(){ super(); }
	
	public User(String _username, String _password){
		super(_username, _password);
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
	public static final String DEFAULT_PASSWORD = "p@$$w0rd";
	
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
	
	public static User authenticateUser(String username, String password){
		User user = null;
		try{
			user = DAOManager.USER_DAO.queryForFirst(
					DAOManager.USER_DAO.queryBuilder()
					.where().eq(User.FIELD_USERNAME, username)
					.and().eq(User.FIELD_PASSWORD, User.digestUserPassword(password))
					.prepare()
			);
			
			Info.sout("User.java: authenticateUser, user object from Query Result[queryForFirst] = " + user);
		} catch (Exception e){
			user = null;
			e.printStackTrace();
		}
		return user;
	}
}












