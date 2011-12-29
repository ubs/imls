package phd.collins.imls.model;

import phd.collins.imls.util.Info;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

public class ConnectionManager {
	private static ConnectionSource connectionSource;
	private static String dbaseURL;
	private static String dbaseUsername;
	private static String dbasePassword;
	private static DatabaseType dbaseType;
	
	private static IDatabaseParams dbaseParams;
	private static boolean refreshDBConnection;	
	
	public static void setConnectionParameters(IDatabaseParams dbParams){
		dbaseType = (DatabaseType)dbParams.getDBType();
		dbaseURL = dbParams.getDatabaseURL();
		dbaseUsername = dbParams.getDBUsername();
		dbasePassword = dbParams.getDBPassword();
		Info.sout(dbaseURL + " " + dbaseUsername + dbasePassword);
	}
	
	public static void setConnectionParameters(String _dbURL, String _dbUsername, String _dbPassword) {
		dbaseURL = _dbURL;
		dbaseUsername = _dbUsername;
		dbasePassword = _dbPassword;
	}
	
	public static ConnectionSource getDatabaseConnection() throws Exception {
		if (connectionSource == null || refreshDBConnection){
			connectionSource = new JdbcConnectionSource(dbaseURL, dbaseUsername, dbasePassword, dbaseType);
			refreshDBConnection = false;
		}
		return connectionSource;
	}
	
	public static ConnectionSource getDatabaseConnection(boolean _refreshConnection) throws Exception {
		refreshDBConnection = _refreshConnection;
		return getDatabaseConnection();
	}
	
	public static ConnectionSource getDatabaseConnection(IDatabaseParams dbParams) throws Exception {
		dbaseParams = dbParams;
		setConnectionParameters(dbaseParams);
		return getDatabaseConnection();
	}
	
	public static ConnectionSource getDatabaseConnection(String _dbURL, String _dbUsername, String _dbPassword) throws Exception {
		setConnectionParameters(_dbURL, _dbUsername, _dbPassword);
		return getDatabaseConnection();
	}	
}
