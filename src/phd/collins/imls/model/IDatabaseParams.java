package phd.collins.imls.model;

public interface IDatabaseParams {
	public void setDatabaseURL();
	public void setDatabaseURL(String dbURL);
	public String getDatabaseURL();
	
	public void setDBUsername(String dbUsername);
	public String getDBUsername();
	
	public void setDBPassword(String dbPassword);	
	public String getDBPassword();
	
	public void setDBType(Object dbType);	
	public Object getDBType();
}
