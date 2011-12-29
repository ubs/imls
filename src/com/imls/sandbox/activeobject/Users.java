package com.imls.sandbox.activeobject;

import java.sql.Date;

import net.java.ao.Entity;
import net.java.ao.schema.Default;

public interface Users extends Entity {
	public final String USERNAME_FIELD = "username";
	public final String PASSWORD_FIELD = "password";
	
	@Default(value="peter")
	public String getUsername();

	@Default(value="peter")
	public void setUsername(String username);
	
	@Default(value="password")
	public String getPassword();
	public void setPassword(String password);
	
	public String getUser_type();
	public void setUser_type(String user_type);
	
	public boolean getIs_active();
	public void setIs_active(boolean is_active);
	
	public Date getLast_login_date();
	public void setLast_login_date(Date last_login_date);
}
