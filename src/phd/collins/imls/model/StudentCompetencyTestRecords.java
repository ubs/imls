package phd.collins.imls.model;

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
	
}
