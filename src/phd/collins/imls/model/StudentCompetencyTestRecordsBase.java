package phd.collins.imls.model;

import java.util.Date;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;

public class StudentCompetencyTestRecordsBase extends ModelBase {
	@DatabaseField(generatedId = true)
	private long id;
	
	@DatabaseField(canBeNull=true, foreign=true, columnName=FIELD_STUDENT_ID)
	private Student student;
	
	@DatabaseField(canBeNull=true, foreign=true, columnName=FIELD_COMPETENCY_LEVEL_ID)
	private CompetencyLevels competencyLevel;
	
	@DatabaseField(dataType=DataType.DATE_STRING)
	private Date date_completed;
	
	public static final String FIELD_STUDENT_ID 		 = "student_id";
	public static final String FIELD_COMPETENCY_LEVEL_ID = "competency_level_id";

	public StudentCompetencyTestRecordsBase() { /*ORMLite needs a no-arg constructor*/ }

	public StudentCompetencyTestRecordsBase(Student _student, CompetencyLevels _competencyLevel) {
		this.setStudent(_student);
		this.setCompetencyLevel(_competencyLevel);
		this.setDate_completed(new Date());
	}
	
	public String toString(){
		return getCompetencyLevel().toString();
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

	public void setCompetencyLevel(CompetencyLevels competencyLevel) {
		this.competencyLevel = competencyLevel;
	}

	public CompetencyLevels getCompetencyLevel() {
		return competencyLevel;
	}

	public void setDate_completed(Date date_completed) {
		this.date_completed = date_completed;
	}

	public Date getDate_completed() {
		return date_completed;
	}
}
