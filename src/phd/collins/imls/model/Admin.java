package phd.collins.imls.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import phd.collins.imls.exceptions.DataAccessException;
import phd.collins.imls.model.User.UserTypes;
import phd.collins.imls.util.Info;

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
	
	
	public static List<Admin> getAll() throws DataAccessException{
		List<Admin> allItems = new ArrayList<Admin>();
		
		try {
			allItems = DAOManager.ADMIN_DAO.queryForAll();
		} catch (SQLException e) {
			Info.serr(e.getMessage());
			throw new DataAccessException("Error retrieving administrators");
		}
		
		return allItems;
	}
	
	public static String getAllAsListOptions(boolean addOptionalNull) throws DataAccessException{
		List<Admin> allItems = getAll();
		StringBuilder sb = new StringBuilder();
		if (addOptionalNull){
			sb.append("<option value=''>--</option>");
		}
		
		for (Admin admin : allItems){
			sb.append("<option value=\"")
				.append(admin.getId()).append("\">")
				.append(admin.getFullname())
				.append("</option>");
		}
		
		return sb.toString();
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
	
	public static Admin AddAdmin(String fullName, String contact, String username) throws DataAccessException {
		Admin obj;
		
		try {		
			User user = User.AddUser(username, User.DEFAULT_PASSWORD, true, UserTypes.ADMIN, true);
			
			obj = new Admin(fullName, contact);
			obj.setUser(user);
			
			DAOManager.ADMIN_DAO.create(obj);
		} catch (Exception e){
			Info.serr(e.getMessage());
			throw new DataAccessException("Error saving admin");
		}
		
		return obj;
	}
}
