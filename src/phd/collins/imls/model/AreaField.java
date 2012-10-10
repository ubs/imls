package phd.collins.imls.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import phd.collins.imls.exceptions.DataAccessException;
import phd.collins.imls.util.Info;

import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "area_fields")
public class AreaField extends AreaFieldBase implements IModelToOtherFormats {
	public AreaField(){ super(); }
	
	public AreaField(String _fieldname, String _description){
		super(_fieldname, _description);
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
	
	public String getStudyAreaName() {
		String studyAreaName = "";
		
		try {
			DAOManager.STUDY_AREA_DAO.refresh(this.getStudyArea()); //Refresh Foreign Field
		} catch (SQLException e) { }
		
		StudyArea studyArea = this.getStudyArea();
		if (studyArea != null){ studyAreaName = studyArea.getArea_name(); }
		if (studyAreaName == null) { studyAreaName = "---"; }
		
		return studyAreaName;
	}
	
	//Static Field and Methods
	public static boolean areaFieldsExist(){
		return (new AreaField().countAll() != 0);
	}
	
	public static AreaField get(long ID){
		AreaField areaField = null;
		try {
			areaField = DAOManager.AREA_FIELD_DAO.queryForId(ID);
		} catch (SQLException e) {
			areaField = null;
			e.printStackTrace();
		}
		return areaField;
	}
	
	public static List<AreaField> getAll() throws DataAccessException{
		List<AreaField> allAreaFields = new ArrayList<AreaField>();
		
		try {
			allAreaFields = DAOManager.AREA_FIELD_DAO.queryForAll();
		} catch (SQLException e) {
			Info.serr(e.getMessage());
			throw new DataAccessException("Error retrieving area fields");
		}
		
		return allAreaFields;
	}
	
	public static AreaField AddAreaField(String studyAreaID, String areaFieldName, String description) throws DataAccessException {
		AreaField obj; 
		
		try {
			obj = new AreaField(areaFieldName, description);
			obj.setStudyArea(StudyArea.get(Long.parseLong(studyAreaID)));
			DAOManager.AREA_FIELD_DAO.create(obj);
		} catch (Exception e){
			Info.serr(e.getMessage());
			throw new DataAccessException("Error saving area field");
		}
		
		return obj;
	}
}
