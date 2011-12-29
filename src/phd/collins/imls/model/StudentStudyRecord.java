package phd.collins.imls.model;

import java.util.Date;

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
