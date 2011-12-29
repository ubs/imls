package phd.collins.imls.model;

import com.j256.ormlite.field.DatabaseField;

public class StudyAreaBase extends ModelBase {
	@DatabaseField(generatedId = true)
	private long id;
	
	@DatabaseField
	private String area_name;
	
	@DatabaseField
	private String description;

	public StudyAreaBase() { /*ORMLite needs a no-arg constructor*/ }

	public StudyAreaBase(String _areaName, String _description) {
		this.setArea_name(_areaName);
		this.setDescription(_description);
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}

	public String getArea_name() {
		return area_name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
