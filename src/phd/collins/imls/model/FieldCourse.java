package phd.collins.imls.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import phd.collins.imls.exceptions.DataAccessException;
import phd.collins.imls.util.Info;

import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "field_courses")
public class FieldCourse extends FieldCourseBase implements IModelToOtherFormats {
	public FieldCourse(){
		super();
	}
	
	public FieldCourse(String _coursename, String _description, int _studyOrder, int _passPercentage){
		super(_coursename, _description, _studyOrder, _passPercentage);
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
	public static boolean fieldCoursesExist(){
		return (new FieldCourse().countAll() != 0);
	}
	
	public static FieldCourse get(long ID){
		FieldCourse obj = null;
		try {
			obj = DAOManager.FIELD_COURSE_DAO.queryForId(ID);
		} catch (SQLException e) {
			obj = null;
			e.printStackTrace();
		}
		return obj;
	}
	
	public static List<FieldCourse> getAll() throws DataAccessException{
		List<FieldCourse> allItems = new ArrayList<FieldCourse>();
		
		try {
			allItems = DAOManager.FIELD_COURSE_DAO.queryForAll();
		} catch (SQLException e) {
			Info.serr(e.getMessage());
			throw new DataAccessException("Error retrieving field courses");
		}
		
		return allItems;
	}
	
	public static FieldCourse AddFieldCourse(String fieldID, String courseName, String description, String studyOrder, String passPercentage) throws DataAccessException {
		FieldCourse obj;
		
		try {
			int intStudyOrder = Integer.parseInt(studyOrder);
			int intPassPercentage = Integer.parseInt(passPercentage);
			
			Info.sout("\n Parameters: " + fieldID + ", " + courseName +
					", " + description + ", " + studyOrder + ", " +
					passPercentage);
			
			obj = new FieldCourse(courseName, description, intStudyOrder, intPassPercentage);
			obj.setAreaField(AreaField.get(Long.parseLong(fieldID)));
			DAOManager.FIELD_COURSE_DAO.create(obj);
		} catch (Exception e){
			Info.serr(e.getMessage());
			throw new DataAccessException("Error saving field course");
		}
		
		return obj;
	}
}
