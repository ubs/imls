package phd.collins.imls.sandbox.ormlite;

import phd.collins.imls.model.ConnectionManager;
import phd.collins.imls.model.ModelBase;
import phd.collins.imls.model.MySQLDatabaseParams;
import phd.collins.imls.util.Info;

import com.j256.ormlite.support.ConnectionSource;

public class MiscDatabaseOperations {
	private ConnectionSource connectionSource = null;
	
	public static void main(String args[]) throws Exception {
		new MiscDatabaseOperations().runMe();
	}
	
	public void runMe() throws Exception {
		Info.sout("Hiya....welcome to " + this.getClass().getName());
		connectToDatabase();
	}
	
	public void truncateTable(ModelBase model){
		model.truncateTable();
	}
	
	private void connectToDatabase() throws Exception {
		connectionSource = ConnectionManager.getDatabaseConnection(new MySQLDatabaseParams());
		Info.sout(connectionSource.toString());
	}	
	
}
