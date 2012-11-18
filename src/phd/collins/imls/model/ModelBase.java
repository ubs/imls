package phd.collins.imls.model;

import java.sql.SQLException;

public abstract class ModelBase {
	//Mother class for all Models
	public static final String FIELD_ID	= "id";
	
	public long countAll(){
		long count = 0;
		try {
			count = DAOManager.getDAO(this.getClass()).countOf();
		} catch (SQLException e) {
			count = 0;
			e.printStackTrace();
		}
		
		//System.out.println("Do Count of : " + this.getClass() + " " + count);
		return count;
	}
	
	public ModelBase findByID(long id){
		ModelBase objModel = null;
		try {
			objModel = DAOManager.getDAO(this.getClass()).queryForId(id);
		} catch (SQLException e) {
			objModel = null;
			e.printStackTrace();
		}
		//System.out.println("Interestingly, the type of ObjModel Retrieved is: " + objModel.getClass() + " as " + objModel);
		return objModel;
	}
	
	public void truncateTable(){
		//Object xx = DAOManager.getDAO(this.getClass()).
	}
}
