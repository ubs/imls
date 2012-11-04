package phd.collins.imls.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import phd.collins.imls.exceptions.DataAccessException;
import phd.collins.imls.model.User.UserTypes;
import phd.collins.imls.util.Info;

import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "student_data")
public class Student extends StudentBase implements IModelToOtherFormats {
	public Student(){ super(); }
	
	public Student(String _regno, String _fullname){
		super(_regno, _fullname);
	}

	public Student(String _regno, String _fullname, String _address) {
		super(_regno, _fullname, _address);
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
	
	//Static Field and Methods
	public static boolean studentsExist(){
		return (new Student().countAll() != 0);
	}
	
	public static Student get(long ID){
		Student obj = null;
		try {
			obj = DAOManager.STUDENT_DAO.queryForId(ID);
		} catch (SQLException e) {
			obj = null;
			e.printStackTrace();
		}
		return obj;
	}
	
	public static Student getByRegNumber(String regNumber) {
		Student obj = null;
		try{
			obj = DAOManager.STUDENT_DAO.queryForFirst(
					DAOManager.STUDENT_DAO.queryBuilder()
					.where().eq(Student.FIELD_REGNUMBER, regNumber)
					.prepare()
			);
		} catch (Exception e){
			obj = null;
			e.printStackTrace();
		}
		return obj;
	}
	
	public static List<Student> getAll() throws DataAccessException{
		List<Student> allItems = new ArrayList<Student>();
		
		try {
			allItems = DAOManager.STUDENT_DAO.queryForAll();
		} catch (SQLException e) {
			Info.serr(e.getMessage());
			throw new DataAccessException("Error retrieving students");
		}
		
		return allItems;
	}
	
	public static String getAllAsListOptions(boolean addOptionalNull) throws DataAccessException{
		List<Student> allItems = getAll();
		StringBuilder sb = new StringBuilder();
		if (addOptionalNull){
			sb.append("<option value=''>--</option>");
		}
		
		for (Student student : allItems){
			sb.append("<option value=\"")
				.append(student.getId()).append("\">")
				.append(student.getFullname())
				.append("</option>");
		}
		
		return sb.toString();
	}
	
	public static Student AddStudent(String regNo, String studentName, String address, String fieldOfStudyID) throws DataAccessException {
		Student obj;
		
		try {		
			User user = User.AddUser(regNo, User.DEFAULT_PASSWORD, UserTypes.STUDENT, true);
			
			obj = new Student(regNo, studentName, address);
			obj.setDate_registered(new Date());
			obj.setUser(user);
			
			if ((fieldOfStudyID != null) && ("" != fieldOfStudyID)) {
				obj.setField_of_study(AreaField.get(Long.parseLong(fieldOfStudyID)));
			}
			
			DAOManager.STUDENT_DAO.create(obj);
		} catch (Exception e){
			Info.serr(e.getMessage());
			throw new DataAccessException("Error saving student");
		}
		
		return obj;
	}
}
