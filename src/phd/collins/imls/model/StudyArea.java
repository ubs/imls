package phd.collins.imls.model;

import java.sql.SQLException;

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
	
	public static StudyArea get(long studyAreaID){
		StudyArea studyArea = null;
		try {
			studyArea = DAOManager.STUDY_AREA_DAO.queryForId(studyAreaID);
		} catch (SQLException e) {
			studyArea = null;
			e.printStackTrace();
		}
		return studyArea;
	}
	
	public static long create(String studyAreaName, String description){
		long ID = 0;
		try {
			StudyArea studyArea = new StudyArea(studyAreaName, description);
			DAOManager.STUDY_AREA_DAO.create(studyArea);
			ID = studyArea.getId();
		} catch (Exception e){
			ID = 0;
			e.printStackTrace();
		}
		
		return ID;
	}

	public static StudyArea AddStudyArea(String studyAreaName, String description) {
		long studyAreaID = create(studyAreaName, description);
		return get(studyAreaID);
	}
}
