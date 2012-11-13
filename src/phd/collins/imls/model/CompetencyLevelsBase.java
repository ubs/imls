package phd.collins.imls.model;

import com.j256.ormlite.field.DatabaseField;

public class CompetencyLevelsBase extends ModelBase {
	@DatabaseField(generatedId = true)
	private long id;
	
	@DatabaseField
	private String level;
	
	public static final String FIELD_ID 	= "id";
	public static final String FIELD_LEVEL 	= "level";

	public CompetencyLevelsBase() { /*ORMLite needs a no-arg constructor*/ }

	public CompetencyLevelsBase(String _level) {
		this.setLevel(_level);
	}
	
	public String toString(){
		return getLevel();
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getLevel() {
		return level;
	}
}
