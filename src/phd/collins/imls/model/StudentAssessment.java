package phd.collins.imls.model;

import java.util.Date;

import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "student_assessments")
public class StudentAssessment extends StudentAssessmentBase implements IModelToOtherFormats {
	public StudentAssessment(){ super(); }
	
	public StudentAssessment(Student _student, FieldCourse _fieldCourse) {
		super(_student, _fieldCourse);
	}
	
	public StudentAssessment(Student _student, FieldCourse _fieldCourse, int _availablePoints,
			int _studentPoints, int _studentPercentage, Date _assessmentDate) {
		super(_student, _fieldCourse, _availablePoints, _studentPoints, _studentPercentage, _assessmentDate);
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
