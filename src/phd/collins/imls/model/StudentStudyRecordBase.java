package phd.collins.imls.model;

import java.util.Date;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;

public class StudentStudyRecordBase extends ModelBase {
	@DatabaseField(generatedId = true)
	private long id;
	
	@DatabaseField(canBeNull=false, foreign=true, columnName=FIELD_STUDENT_ID)
	private Student student;
	
	@DatabaseField(canBeNull=false, foreign=true, columnName=FIELD_FIELD_COURSE_ID)
	private FieldCourse fieldCourse;
	
	@DatabaseField(canBeNull=false, foreign=true, columnName=FIELD_COURSE_MODULE_ID)
	private CourseModule courseModule;
	
	@DatabaseField(dataType=DataType.DATE_STRING)
	private Date date_started;
	
	@DatabaseField(dataType=DataType.DATE_STRING)
	private Date date_completed;
	
	public static final String FIELD_STUDENT_ID = "student_id";
	public static final String FIELD_FIELD_COURSE_ID = "course_id";
	public static final String FIELD_COURSE_MODULE_ID = "module_id";
	public static final String FIELD_DATE_COMPLETED = "date_completed";

	public StudentStudyRecordBase() { /*ORMLite needs a no-arg constructor*/ }

	public StudentStudyRecordBase(Student _student, FieldCourse _fieldCourse, CourseModule _courseModule,
			Date _startDate, Date _completedDate) {
		this.setStudent(_student);
		this.setFieldCourse(_fieldCourse);
		this.setCourseModule(_courseModule);
		this.setDate_started(_startDate);
		this.setDate_completed(_completedDate);
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

	public void setCourseModule(CourseModule courseModule) {
		this.courseModule = courseModule;
	}

	public CourseModule getCourseModule() {
		return courseModule;
	}

	public void setDate_started(Date date_started) {
		this.date_started = date_started;
	}

	public Date getDate_started() {
		return date_started;
	}

	public void setDate_completed(Date date_completed) {
		this.date_completed = date_completed;
	}

	public Date getDate_completed() {
		return date_completed;
	}
}
