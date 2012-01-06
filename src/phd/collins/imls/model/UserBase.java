package phd.collins.imls.model;

import java.util.Date;

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

	public UserBase() { /*ORMLite needs a no-arg constructor*/ }

	public UserBase(String _username, String _password) {
		this.username = _username;
		this.password = _password;
	}
	
	public String toString(){
		return username + password + is_active + last_login_date;
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

	public boolean isIs_active() {
		return is_active;
	}
	
	public String digestPassword(String plainPassword){
		return UtilGeneral.digestStringToMD5(plainPassword);
	}
}
