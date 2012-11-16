package phd.collins.imls.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

public class FieldCourseBase extends ModelBase {
	@DatabaseField(generatedId = true)
	private long id;
	
	@DatabaseField(canBeNull=true, foreign=true, columnName=FIELD_AREA_FIELD_ID)
	private AreaField areaField;
	
	@DatabaseField
	private String course_name;
	
	@DatabaseField
	private String description;
	
	@DatabaseField
	private int study_order;
	
	@DatabaseField
	private int pass_percentage;
	
	@ForeignCollectionField(eager = false, orderColumnName=CourseModule.FIELD_STUDY_ORDER)
	private ForeignCollection<CourseModule> colCourseModules;
	
	@ForeignCollectionField(eager = false, orderColumnName=AssessmentQuestion.FIELD_ID)
	private ForeignCollection<AssessmentQuestion> colAssessmentQuestions;
	
	public static final String FIELD_AREA_FIELD_ID 	= "field_id";
	public static final String FIELD_STUDY_ORDER = "study_order";
	
	public FieldCourseBase() { /*ORMLite needs a no-arg constructor*/ }
	
	public FieldCourseBase(String _coursename, String _description, int _studyOrder, int _passPercentage){
		this.setCourse_name(_coursename);
		this.setDescription(_description);
		this.setStudy_order(_studyOrder);
		this.setPass_percentage(_passPercentage);
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setAreaField(AreaField areaField) {
		this.areaField = areaField;
	}

	public AreaField getAreaField() {
		return areaField;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public String getCourse_name() {
		return course_name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setStudy_order(int study_order) {
		this.study_order = study_order;
	}

	public int getStudy_order() {
		return study_order;
	}

	public void setPass_percentage(int pass_percentage) {
		this.pass_percentage = pass_percentage;
	}

	public int getPass_percentage() {
		return pass_percentage;
	}

	public ForeignCollection<CourseModule> getColCourseModules() {
		return colCourseModules;
	}

	public ForeignCollection<AssessmentQuestion> getColAssessmentQuestions() {
		return colAssessmentQuestions;
	}
}
