package phd.collins.imls.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import phd.collins.imls.exceptions.DataAccessException;
import phd.collins.imls.util.Info;

import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "course_modules")
public class CourseModule extends CourseModuleBase implements IModelToOtherFormats {
	public CourseModule(){ super(); }
	
	public CourseModule(String _moduleName, String _description, String _moduleContent, int _studyOrder) {
		super(_moduleName, _description, _moduleContent, _studyOrder);
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
	public static boolean courseModulesExist(){
		return (new CourseModule().countAll() != 0);
	}
	
	public static CourseModule get(long ID){
		CourseModule obj = null;
		try {
			obj = DAOManager.COURSE_MODULE_DAO.queryForId(ID);
		} catch (SQLException e) {
			obj = null;
			e.printStackTrace();
		}
		return obj;
	}
	
	public static CourseModule refresh(CourseModule cm) throws DataAccessException {
		try {
			DAOManager.COURSE_MODULE_DAO.refresh(cm);
			return cm;
		} catch (SQLException e) { throw new DataAccessException(e.getMessage()); }
	}
	
	public static List<CourseModule> getAll() throws DataAccessException{
		List<CourseModule> allItems = new ArrayList<CourseModule>();
		
		try {
			allItems = DAOManager.COURSE_MODULE_DAO.queryForAll();
		} catch (SQLException e) {
			Info.serr(e.getMessage());
			throw new DataAccessException("Error retrieving course modules");
		}
		
		return allItems;
	}
	
	public static List<CourseModule> getMyFieldCourses(AreaField areaField) throws DataAccessException{
		List<CourseModule> allItems = new ArrayList<CourseModule>();
		
		try {
			allItems = DAOManager.COURSE_MODULE_DAO.queryBuilder()
						.where().eq(CourseModule.FIELD_COURSE_ID, areaField.getId()).query();
		} catch (SQLException e) {
			Info.serr(e.getMessage());
			throw new DataAccessException("Error retrieving field courses");
		}
		
		return allItems;
	}
	
	public static CourseModule AddCourseModule(String courseID, String moduleName, String studyOrder, String competencyLevelID, String description, String moduleContent) throws DataAccessException {
		CourseModule obj;
		
		try {
			int intStudyOrder = Integer.parseInt(studyOrder);
			
			obj = new CourseModule(moduleName, description, moduleContent, intStudyOrder);
			obj.setFieldCourse(FieldCourse.get(Long.parseLong(courseID)));
			
			CompetencyLevels cLevel = null; Long cLevelID = -1L;
			try{ cLevelID = Long.parseLong(competencyLevelID); } catch (Exception e) { cLevelID = null; }
			
			if (cLevelID != null) { cLevel = CompetencyLevels.get(cLevelID); }
			obj.setCompetencyLevel(cLevel);
			
			DAOManager.COURSE_MODULE_DAO.create(obj);
		} catch (Exception e){
			Info.serr(e.getMessage());
			throw new DataAccessException("Error saving course module");
		}
		
		return obj;
	}
}
