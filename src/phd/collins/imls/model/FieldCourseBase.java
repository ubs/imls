package phd.collins.imls.model;

import com.j256.ormlite.field.DatabaseField;

public class FieldCourseBase extends ModelBase {
	@DatabaseField(generatedId = true)
	private long id;
	
	@DatabaseField(canBeNull=true, foreign=true) //Column_Name: field_id//Just incase
	private AreaField areaField;
	
	@DatabaseField
	private String course_name;
	
	@DatabaseField
	private String description;
	
	@DatabaseField
	private int study_order;
	
	@DatabaseField
	private int pass_percentage;
	
	public FieldCourseBase() { /*ORMLite needs a no-arg constructor*/ }
	
	public FieldCourseBase(String _coursename){
		this(_coursename, "");
	}
	
	public FieldCourseBase(String _coursename, String _description){
		this.setCourse_name(_coursename);
		this.setDescription(_description);
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
}