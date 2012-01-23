package phd.collins.imls.model;

import java.sql.SQLException;

public abstract class ModelBase {
	//Mother class for all Models
		
	
	public long countAll(){
		long count = 0;
		try {
			count = DAOManager.getDAO(this.getClass()).countOf();
		} catch (SQLException e) {
			count = 0;
			e.printStackTrace();
		}
		System.out.println("Do Count of : " + this.getClass() + " " + count);
		return count;
	}
	
	public void truncateTable(){
		//Object xx = DAOManager.getDAO(this.getClass()).
	}
}
