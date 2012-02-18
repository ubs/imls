package phd.collins.imls.model;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "administrators")
public class Admin extends AdminBase implements IModelToOtherFormats {
	public Admin(){ super(); }
	
	public Admin(String _fullname, String _contact){
		super(_fullname, _contact);
	}

	@Override
	public Object toXML() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object toJSONObject() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static Admin get(long adminID){
		Admin admin = null;
		try {
			admin = DAOManager.ADMIN_DAO.queryForId(adminID);
		} catch (SQLException e) {
			admin = null;
			e.printStackTrace();
		}
		return admin;
	}
	
	public static Admin getFirstObject(){
		Admin admin = null;
		try {
			List<Admin> results = DAOManager.ADMIN_DAO.queryBuilder().limit(1L).query();
			admin = results.iterator().next();
		} catch (SQLException e) {
			admin = null;
			e.printStackTrace();
		}
		return admin;
	}
	
	public static long create(Admin admin){
		long adminID = 0;
		try {
			DAOManager.ADMIN_DAO.create(admin);
			adminID = admin.getId();
		} catch (Exception e){
			adminID = 0;
			e.printStackTrace();
		}
		
		return adminID;
	}
	
	public void refreshUserObject(){
		try {
			DAOManager.USER_DAO.refresh(this.getUser());
		} catch (Exception e){ e.printStackTrace(); }
	}
}
