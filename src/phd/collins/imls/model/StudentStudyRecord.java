package phd.collins.imls.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import phd.collins.imls.exceptions.DataAccessException;
import phd.collins.imls.model.CompetencyLevels.CLevelTypes;
import phd.collins.imls.util.Info;
import phd.collins.imls.util.UtilGeneral;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "student_study_records")
public class StudentStudyRecord extends StudentStudyRecordBase implements IModelToOtherFormats {
	public StudentStudyRecord(){ super(); }
	
	public StudentStudyRecord(Student _student, FieldCourse _fieldCourse, CourseModule _courseModule) {
		super(_student, _fieldCourse, _courseModule, null, null);
	}
	
	public StudentStudyRecord(Student _student, FieldCourse _fieldCourse, CourseModule _courseModule,
			Date _startDate) {
		super(_student, _fieldCourse, _courseModule, _startDate, null);
	}
	
	public StudentStudyRecord(Student _student, FieldCourse _fieldCourse, CourseModule _courseModule,
			Date _startDate, Date _CompletedDate) {
		super(_student, _fieldCourse, _courseModule, _startDate, _CompletedDate);
	}
	
	public boolean isCompleted(){
		return (getDate_completed() != null);
	}
	
	public boolean isNotCompleted(){
		return !isCompleted();
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
	
	public static void create(StudentStudyRecord ssr) throws DataAccessException {
		try {
			DAOManager.STUDENT_STUDY_RECORD.createIfNotExists(ssr);
		} catch (SQLException e) {
			Info.serr(e.getMessage());
			throw new DataAccessException("Error saving student study test record");
		}
	}
	
	private static StudentStudyRecord getStudentStudyRecord(String studentID, String courseModuleID) {
		StudentStudyRecord obj = null;
		
		try{
			obj = DAOManager.STUDENT_STUDY_RECORD.queryForFirst(
					DAOManager.STUDENT_STUDY_RECORD.queryBuilder()
					.where().eq(StudentStudyRecord.FIELD_COURSE_MODULE_ID, courseModuleID)
					.and().eq(StudentStudyRecord.FIELD_STUDENT_ID, studentID)
					.prepare()
			);
		} catch (Exception e){
			obj = null;
			e.printStackTrace();
		}
		return obj;
	}
	
	public static Map<Long, List<StudentStudyRecord>> groupStudentStudyRecordsByFieldCourse(ForeignCollection<StudentStudyRecord> lstSSR){
		Map<Long, List<StudentStudyRecord>> mapGroupedSSR = new HashMap<Long, List<StudentStudyRecord>>();
		
		for (StudentStudyRecord ssr : lstSSR){
			Long mkey = ssr.getFieldCourse().getId();
			
			if (mapGroupedSSR.containsKey(mkey)){
				mapGroupedSSR.get(mkey).add(ssr);
			}
			else {
				List<StudentStudyRecord> lstTemp = new ArrayList<StudentStudyRecord>();
				lstTemp.add(ssr);
				mapGroupedSSR.put(mkey, lstTemp);
			}
		}
		
		return mapGroupedSSR;
	}
	
	public static boolean AssignDefaultCourseModulesToStudent(User user) throws DataAccessException{
		boolean pssReturn = false;
		
		if (user.isStudent()){
			Student student = Student.getByRegNumber(user.getUserName());

			if (student != null) {
				AreaField areaField = student.getField_of_study();
				AreaField.refresh(areaField);
				ForeignCollection<FieldCourse> myFieldCourses = areaField.getColFieldCourses();
				
				pssReturn = StudentCompetencyTestRecords.setDefaultCompetencyAccepted(student);
				pssReturn = AssignCourseModulesToStudent(student, myFieldCourses, null);
			}
		}
		return pssReturn;
	}
	
	public static boolean AssignCourseModulesToStudentBasedOnScore(User user, String assessedCLevel) throws DataAccessException {
		boolean pssReturn = false;
		
		if (user.isStudent()){
			CompetencyLevels cLevel = CompetencyLevels.getByCLevel(assessedCLevel);
			
			List<String> lstCLevels = new ArrayList<String>();
			lstCLevels.add(cLevel.getLevel());
			
			if (assessedCLevel.equalsIgnoreCase(CLevelTypes.BASIC.toString())){
				lstCLevels = null;
			}
			else if (assessedCLevel.equalsIgnoreCase(CLevelTypes.INTERMEDIATE.toString())){
				lstCLevels.add(CLevelTypes.ADVANCED.toString());
			}
			
			Student student = Student.getByRegNumber(user.getUserName());

			if (student != null) {
				AreaField areaField = student.getField_of_study();
				AreaField.refresh(areaField);
				ForeignCollection<FieldCourse> myFieldCourses = areaField.getColFieldCourses();
				
				pssReturn = StudentCompetencyTestRecords.setCompetencyTestRecordResult(student, cLevel);
				pssReturn = AssignCourseModulesToStudent(student, myFieldCourses, lstCLevels);
			}
		}
		
		return pssReturn;
	}
	
	private static boolean AssignCourseModulesToStudent(Student student, ForeignCollection<FieldCourse> myFieldCourses, List<String> lstCLevels) throws DataAccessException {
		List<FieldCourse> lstFieldCourses = UtilGeneral.ForeignCollectionToList(myFieldCourses);
		return AssignCourseModulesToStudent(student, lstFieldCourses, lstCLevels);
	}
	
	private static boolean AssignCourseModulesToStudent(Student student, List<FieldCourse> myFieldCourses, List<String> lstCLevels) throws DataAccessException {
		boolean pssReturn = false;
		
		try{
			ForeignCollection<CourseModule> myCourseModules = null;
			for (FieldCourse fc : myFieldCourses) {
				myCourseModules = fc.getColCourseModules();
				for (CourseModule cm : myCourseModules){
					StudentStudyRecord ssr = null;
					
					if (lstCLevels != null) {
						String strCmCLevel = CompetencyLevels.getCompetencyLevel(cm.getCompetencyLevel());
						
						if (lstCLevels.contains(strCmCLevel)) {
							ssr = new StudentStudyRecord(student, fc, cm, new Date());
						}
					}
					else {
						ssr = new StudentStudyRecord(student, fc, cm, new Date());
					}
					
					if (ssr != null) {
						StudentStudyRecord.create(ssr);
					}
				}
			}
			
			pssReturn = true;
		} catch (Exception e){
			Info.serr(e.getMessage());
			throw new DataAccessException("Error assigning course modules to student");
		}
		
		return pssReturn;
	}

	public static boolean markStudentCourseModuleCompleted(Student student, String courseModuleID) throws DataAccessException{
		boolean pssReturn = false;
		StudentStudyRecord ssr = null;
		
		try {
			String studentID = student.getId() + "";
			ssr = StudentStudyRecord.getStudentStudyRecord(studentID, courseModuleID);
			
			if (ssr != null) {
				ssr.setDate_completed(new Date());
				int rowsAffected = DAOManager.STUDENT_STUDY_RECORD.update(ssr);
				pssReturn = (rowsAffected > 0);
			}
		} catch (Exception e){
			Info.serr(e.getMessage());
			throw new DataAccessException("Error saving student study record");
		}
		
		return pssReturn;
	}
	
	public static boolean allStudyRecordsCompleted(Student student){
		boolean pssReturn = false;
		StudentStudyRecord obj = null;
		
		try{
			String studentID = student.getId() + "";
			
			obj = DAOManager.STUDENT_STUDY_RECORD.queryForFirst(
					DAOManager.STUDENT_STUDY_RECORD.queryBuilder()
					.where().eq(StudentStudyRecord.FIELD_STUDENT_ID, studentID)
					.and().isNull(StudentStudyRecord.FIELD_DATE_COMPLETED)
					.prepare()
			);
			
			pssReturn = (obj == null);
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return pssReturn;
	}
	
}
