package phd.collins.imls.model;

import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "study_areas")
public class StudyArea extends StudyAreaBase implements IModelToOtherFormats {
	public StudyArea(){ super(); }
	
	public StudyArea(String _areaName, String _description) {
		super(_areaName, _description);
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
	
	//Static Field and Methods
	public static boolean studyAreasExist(){
		return (new StudyArea().countAll() != 0);
	}
}
