package phd.collins.imls.model;

import java.util.Date;

import phd.collins.imls.model.User.UserTypes;
import phd.collins.imls.util.UtilGeneral;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;

public class UserBase extends ModelBase {
	@DatabaseField(generatedId = true)
	private long id;
	
	@DatabaseField
	private String username;
	
	@DatabaseField
	private String password;
	
	@DatabaseField
	private String user_type;
	
	@DatabaseField
	private boolean is_active;
	
	@DatabaseField(dataType=DataType.DATE_STRING)
	private Date last_login_date;
	
	public static final String FIELD_USERNAME 	= "username";
	public static final String FIELD_PASSWORD 	= "password";
	public static final String FIELD_USER_TYPE 	= "user_type";
	public static final String FIELD_IS_ACTIVE 	= "is_active";

	public UserBase() { /*ORMLite needs a no-arg constructor*/ }

	public UserBase(String _username, String _password, UserTypes _userType, boolean _isActive) {
		this.setUsername(_username);
		this.setPassword(_password);
		this.setUser_type(_userType.getDescription());
		this.setIs_active(_isActive);
	}
	
	public String toString(){
		return getUserName();
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUserName() {
		return username;
	}
	
	public void setPassword(String password) {
		this.password = digestPassword(password);
	}

	public String getPassword() {
		return password;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setLast_login_date(Date last_login_date) {
		this.last_login_date = last_login_date;
	}

	public Date getLast_login_date() {
		return last_login_date;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

	public boolean getIs_active() {
		return is_active;
	}
	
	public boolean isStudent(){
		return (this.getUser_type().trim().equalsIgnoreCase(UserTypes.STUDENT.toString()));
	}
	
	public boolean isAdmin(){
		return (this.getUser_type().trim().equalsIgnoreCase(UserTypes.ADMIN.toString()));
	}
	
	public String digestPassword(String plainPassword){
		return digestUserPassword(plainPassword);
	}
	
	public static String digestUserPassword(String plainPassword){
		return UtilGeneral.digestStringToMD5(plainPassword);
	}
}
