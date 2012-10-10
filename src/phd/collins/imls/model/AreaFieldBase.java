package phd.collins.imls.model;

import com.j256.ormlite.field.DatabaseField;

public class AreaFieldBase extends ModelBase {
	@DatabaseField(generatedId = true)
	private long id;
	
	@DatabaseField
	private String field_name;
	
	@DatabaseField
	private String description;
	
	@DatabaseField(canBeNull=true, foreign=true, columnName="study_area_id")
	private StudyArea studyArea;

	public AreaFieldBase() { /*ORMLite needs a no-arg constructor*/ }

	public AreaFieldBase(String _fieldname, String _description) {
		this(_fieldname, _description, null);
	}
	
	public AreaFieldBase(String _fieldname, String _description, StudyArea _studyArea) {
		this.setField_name(_fieldname);
		this.setDescription(_description);
		this.setStudyArea(_studyArea);
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setField_name(String field_name) {
		this.field_name = field_name;
	}

	public String getField_name() {
		return field_name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setStudyArea(StudyArea studyArea) {
		this.studyArea = studyArea;
	}

	public StudyArea getStudyArea() {
		return studyArea;
	}
}
