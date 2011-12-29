package phd.collins.imls.model;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.db.MysqlDatabaseType;

public class MySQLDatabaseParams implements IDatabaseParams {
	private DatabaseType _dbType;
	private String _dbURL;
	private String _dbHost;
	private String _dbPort;
	private String _dbDriver;
	private String _dbUsername;
	private String _dbPassword;
	private String _dbName;
	
	private String _backslash = "/";
	
	public MySQLDatabaseParams(){
		initDBSettings();
	}
	
	protected void initDBSettings(){
		_dbDriver = "jdbc:mysql://";
		_dbHost = "localhost";
		_dbPort = "";
		_dbName = "imlsdb";
		setDBUsername("root");
		setDBPassword("");
		setDatabaseURL();
		setDBType(new MysqlDatabaseType());
	}
	
	@Override
	public void setDatabaseURL() {
		StringBuffer sb = new StringBuffer();
		sb = sb.append(_dbDriver).append(_dbHost).append(_dbPort).append(_backslash).append(_dbName);
		_dbURL = sb.toString();
	}

	@Override
	public void setDatabaseURL(String dbURL) {
		_dbURL = dbURL;
	}

	@Override
	public String getDatabaseURL() {
		return _dbURL;
	}

	@Override
	public void setDBUsername(String dbUsername) {
		_dbUsername = dbUsername;
	}

	@Override
	public String getDBUsername() {
		return _dbUsername;
	}

	@Override
	public void setDBPassword(String dbPassword) {
		_dbPassword = dbPassword;
	}

	@Override
	public String getDBPassword() {
		return _dbPassword;
	}

	@Override
	public void setDBType(Object dbType) {
		_dbType = (DatabaseType)dbType;
	}

	@Override
	public Object getDBType() {
		return _dbType;
	}

}
