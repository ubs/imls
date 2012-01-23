package phd.collins.imls.model;

import java.sql.SQLException;

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
			DAOManager.USER_DAO.queryForId(userID);
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
}
