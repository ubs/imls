package phd.collins.imls.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;

public class CourseModuleBase extends ModelBase {
	@DatabaseField(generatedId = true)
	private long id;
	
	@DatabaseField(canBeNull=true, foreign=true, columnName=FIELD_COURSE_ID)
	private FieldCourse fieldCourse;
	
	@DatabaseField
	private String module_name;
	
	@DatabaseField
	private int study_order;
	
	@DatabaseField(canBeNull=true, foreign=true, columnName=FIELD_COMPETENCY_LEVEL_ID)
	private CompetencyLevels competencyLevel;
	
	@DatabaseField
	private String description;
	
	@DatabaseField(dataType=DataType.LONG_STRING)
	private String module_content;

	public static final String FIELD_COURSE_ID 	= "course_id";
	public static final String FIELD_STUDY_ORDER = "study_order";
	public static final String FIELD_COMPETENCY_LEVEL_ID = "competency_level_id";
	
	public CourseModuleBase() { /*ORMLite needs a no-arg constructor*/ }

	public CourseModuleBase(String _moduleName, String _description, String _moduleContent, int _studyOrder) {
		this.setModule_name(_moduleName);
		this.setDescription(_description);
		this.setModule_content(_moduleContent);
		this.setStudy_order(_studyOrder);
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setFieldCourse(FieldCourse fieldCourse) {
		this.fieldCourse = fieldCourse;
	}

	public FieldCourse getFieldCourse() {
		return fieldCourse;
	}

	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}

	public String getModule_name() {
		return module_name;
	}

	public void setStudy_order(int study_order) {
		this.study_order = study_order;
	}

	public int getStudy_order() {
		return study_order;
	}
	
	public void setCompetencyLevel(CompetencyLevels competencyLevel) {
		this.competencyLevel = competencyLevel;
	}

	public CompetencyLevels getCompetencyLevel() {
		return competencyLevel;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setModule_content(String module_content) {
		this.module_content = module_content;
	}

	public String getModule_content() {
		return module_content;
	}

}
