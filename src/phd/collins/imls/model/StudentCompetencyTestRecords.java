package phd.collins.imls.model;

import java.sql.SQLException;

import phd.collins.imls.exceptions.DataAccessException;
import phd.collins.imls.util.Info;

import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "student_competency_test_records")
public class StudentCompetencyTestRecords extends StudentCompetencyTestRecordsBase implements IModelToOtherFormats {
	
	public StudentCompetencyTestRecords(){ super(); }
	
	public StudentCompetencyTestRecords(Student _student, CompetencyLevels _competencyLevel){
		super(_student, _competencyLevel);
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
	
	public static void create(StudentCompetencyTestRecords stdCTR) throws DataAccessException {
		try {
			DAOManager.STUDENT_COMPETENCY_TEST_RECORDS.createIfNotExists(stdCTR);
		} catch (SQLException e) {
			Info.serr(e.getMessage());
			throw new DataAccessException("Error saving student competency test record");
		}
	}
	
	private static StudentCompetencyTestRecords getCompetencyTestRecordByStudentID(long studentID) {
		StudentCompetencyTestRecords obj = null;
		
		try{
			obj = DAOManager.STUDENT_COMPETENCY_TEST_RECORDS.queryForFirst(
					DAOManager.STUDENT_COMPETENCY_TEST_RECORDS.queryBuilder()
					.where().eq(StudentCompetencyTestRecords.FIELD_STUDENT_ID, studentID)
					.prepare()
			);
		} catch (Exception e){
			obj = null;
			e.printStackTrace();
		}
		return obj;
	}
	
	public static boolean competencyAssessmentDone(User user){
		boolean ctDone = false;
		StudentCompetencyTestRecords stdCTR = null;
		
		if (user.isStudent()){
			Student student = Student.getByRegNumber(user.getUserName());

			if (student != null) {
				long studentID = student.getId();
				stdCTR = getCompetencyTestRecordByStudentID(studentID);
				ctDone = (stdCTR != null);
			}
		}
		return ctDone;
	}
	
	public static boolean setDefaultCompetencyAccepted(Student student){
		boolean pssReturn = false;
		try {
			CompetencyLevels cLevel = CompetencyLevels.getDefaultCompetencyLevel();
			StudentCompetencyTestRecords stdCTR = new StudentCompetencyTestRecords(student, cLevel);
			StudentCompetencyTestRecords.create(stdCTR);
			pssReturn = true;
		} catch (Exception e){
			e.printStackTrace();
		}
		return pssReturn;
	}
	
	public static boolean setCompetencyTestRecordResult(Student student, CompetencyLevels cLevel){
		boolean pssReturn = false;
		try {
			StudentCompetencyTestRecords stdCTR = new StudentCompetencyTestRecords(student, cLevel);
			StudentCompetencyTestRecords.create(stdCTR);
			pssReturn = true;
		} catch (Exception e){
			e.printStackTrace();
		}
		return pssReturn;
	}
	
}
