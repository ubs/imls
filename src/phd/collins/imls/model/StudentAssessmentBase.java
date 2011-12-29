package phd.collins.imls.model;

import java.util.Date;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;

public class StudentAssessmentBase extends ModelBase {
	@DatabaseField(generatedId = true)
	private long id;
	
	@DatabaseField(canBeNull=false, foreign=true)
	private Student student;
	
	@DatabaseField(canBeNull=false, foreign=true)
	private FieldCourse fieldCourse;
	
	@DatabaseField
	private int available_points;
	
	@DatabaseField
	private int student_points;
	
	@DatabaseField
	private int student_percentage;
	
	@DatabaseField(dataType=DataType.DATE_STRING)
	private Date assessment_date;

	public StudentAssessmentBase() { /*ORMLite needs a no-arg constructor*/ }

	public StudentAssessmentBase(Student _student, FieldCourse _fieldCourse) {
		this.setStudent(_student);
		this.setFieldCourse(_fieldCourse);
	}
	
	public StudentAssessmentBase(Student _student, FieldCourse _fieldCourse, int _availablePoints,
			int _studentPoints, int _studentPercentage, Date _assessmentDate) {
		this.setStudent(_student);
		this.setFieldCourse(_fieldCourse);
		this.setAvailable_points(available_points);
		this.setStudent_points(_studentPoints);
		this.setStudent_percentage(_studentPercentage);
		this.setAssessment_date(_assessmentDate);
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Student getStudent() {
		return student;
	}

	public void setFieldCourse(FieldCourse fieldCourse) {
		this.fieldCourse = fieldCourse;
	}

	public FieldCourse getFieldCourse() {
		return fieldCourse;
	}

	public void setAvailable_points(int available_points) {
		this.available_points = available_points;
	}

	public int getAvailable_points() {
		return available_points;
	}

	public void setStudent_points(int student_points) {
		this.student_points = student_points;
	}

	public int getStudent_points() {
		return student_points;
	}

	public void setStudent_percentage(int student_percentage) {
		this.student_percentage = student_percentage;
	}

	public int getStudent_percentage() {
		return student_percentage;
	}

	public void setAssessment_date(Date assessment_date) {
		this.assessment_date = assessment_date;
	}

	public Date getAssessment_date() {
		return assessment_date;
	}
}
