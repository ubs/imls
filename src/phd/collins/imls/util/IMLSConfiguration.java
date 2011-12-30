package phd.collins.imls.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class IMLSConfiguration extends Properties {
	private static final long serialVersionUID = 4764519220048494123L;
	
	private static IMLSConfiguration IMLSConfigInstance;
	
	private static Logger logger = Logger.getLogger(IMLSConfiguration.class.getName());

	public static final String IMLS_DEFAULT_CONFIGURATION_FILE = "conf/imls.properties";
	private static String imlsConfigPath;

	//DATABASE CONFIGURATION FOR IMLS	
	public static final String KEY_IMLS_DATABASE_DRIVER = "imls.dbdriver";
	public static final String KEY_IMLS_DATABASE_HOST = "imls.dbhost";
	public static final String KEY_IMLS_DATABASE_PORT = "imls.dbport";
	public static final String KEY_IMLS_DATABASE_NAME = "imls.dbname";
	public static final String KEY_IMLS_DATABASE_USERNAME = "imls.dbuser";
	public static final String KEY_IMLS_DATABASE_USER_PASSWORD = "imls.dbpass";
	
	/**
	 * Return an instance of the class. Only one instance is reasonable to use.
	 *
	 * @return a instance
	 */
	public static synchronized IMLSConfiguration getInstance() {
		if (IMLSConfigInstance == null) {
			IMLSConfigInstance = new IMLSConfiguration();
			load();
		}
		return IMLSConfigInstance;
	}

   	public static void init(String _imlsConfPath){
		imlsConfigPath = _imlsConfPath;
   	}


	//GETTERS: DATABASE CONFIGURATION FOR IMLS
	public synchronized String getDatabaseDriver() {
		return getProperty(KEY_IMLS_DATABASE_DRIVER);
	}
	public synchronized String getDatabaseHost() {
		return getProperty(KEY_IMLS_DATABASE_HOST);
	}
	public synchronized String getDatabasePort() {
		return getProperty(KEY_IMLS_DATABASE_PORT);
	}
	public synchronized String getDatabaseName() {
		return getProperty(KEY_IMLS_DATABASE_NAME);
	}
	public synchronized String getDatabaseUser() {
		return getProperty(KEY_IMLS_DATABASE_USERNAME);
	}
	public synchronized String getDatabasePassword() {
		return getProperty(KEY_IMLS_DATABASE_USER_PASSWORD);
	}
	
	/**
	 * adds properties missed.
	 */
	private void setDefaultProperties() {
		logger.info("Setting Default IMLS Properties...");
		
		setProperty(IMLSConfiguration.KEY_IMLS_DATABASE_DRIVER, "jdbc:mysql://");
		setProperty(IMLSConfiguration.KEY_IMLS_DATABASE_HOST, "localhost");
		setProperty(IMLSConfiguration.KEY_IMLS_DATABASE_PORT, "");
		setProperty(IMLSConfiguration.KEY_IMLS_DATABASE_NAME, "imlsdb");
		setProperty(IMLSConfiguration.KEY_IMLS_DATABASE_USERNAME, "root");
		setProperty(IMLSConfiguration.KEY_IMLS_DATABASE_USER_PASSWORD, "");
	}

	/**
	 * Retrieves configuration.
	 * An internal instance is loaded.
	 */
	public static void load() {
		logger.info("Loading IMLS configuration file...");
		IMLSConfiguration imlsConfig = getInstance();
		InputStream inputStream;
		
		synchronized (imlsConfig) {
			imlsConfig.setDefaultProperties();
			
			InputStream input = null;
			if (imlsConfigPath != null) {
				try {
					input = new FileInputStream(imlsConfigPath);
				} catch (FileNotFoundException e) {
					logger.error("IMLS configuration file <<" + imlsConfigPath + ">> not found, " +
							"The default configuration will be used", e);
					return;
				}
			}
			else {
				input = ClassLoader.getSystemResourceAsStream(IMLS_DEFAULT_CONFIGURATION_FILE);
				if (input == null) {
					logger.error("IMLS configuration file <<" + IMLS_DEFAULT_CONFIGURATION_FILE + ">> not found, " +
							"The default configuration will be used" + 
							"Current Path is: " + new File("").getAbsolutePath());
					return;
				}
			}
			
			try {
				inputStream = new BufferedInputStream(input);
				imlsConfig.load(inputStream);
				inputStream.close();
				logger.info("IMLS configuration file successfully loaded");
				logger.debug("IMLS configuration file successfully loaded");

			} catch (IOException e) {
				logger.error("An error occurred while reading IMLS configuration file", e);
			}
		}
	}
}
