package phd.collins.imls.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import phd.collins.imls.exceptions.DataAccessException;
import phd.collins.imls.util.Info;

import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "competency_levels")
public class CompetencyLevels extends CompetencyLevelsBase implements IModelToOtherFormats {
	public enum CLevelTypes {
		BASIC("BASIC"),
		INTERMEDIATE("INTERMEDIATE"),
		ADVANCED("ADVANCED");
		
		private String description;
		
		private CLevelTypes(String desc){
			description = desc;
		}
		
		@Override
		public String toString(){ return description; }
		
		public String getDescription() { return description; }
		
		public List<String> getAllCLevelTypes(){
			List<String> lstAllItems = Arrays.asList(
				CLevelTypes.BASIC.toString(),
				CLevelTypes.INTERMEDIATE.toString(), 
				CLevelTypes.ADVANCED.toString()
			);
			
			return lstAllItems;
		}
	}

	public CompetencyLevels(){ super(); }
	
	public CompetencyLevels(String _level){
		super(_level);
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
	
	public static void create(String cLevel) throws DataAccessException {
		create(new CompetencyLevels(cLevel));
	}
	
	public static void create(CompetencyLevels cl) throws DataAccessException {
		try {
			DAOManager.COMPETENCY_LEVELS_DAO.createIfNotExists(cl);
		} catch (SQLException e) {
			Info.serr(e.getMessage());
			throw new DataAccessException("Error saving competency level");
		}
	}
	
	public static void deleteAll(){
		try {
			DeleteBuilder<CompetencyLevels, Long> deleteBuilder = DAOManager.COMPETENCY_LEVELS_DAO.deleteBuilder();
			DAOManager.COMPETENCY_LEVELS_DAO.delete(deleteBuilder.prepare());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static CompetencyLevels get(long ID){
		CompetencyLevels obj = null;
		try {
			obj = DAOManager.COMPETENCY_LEVELS_DAO.queryForId(ID);
		} catch (SQLException e) {
			obj = null;
			e.printStackTrace();
		}
		return obj;
	}
	
	public static CompetencyLevels getByCLevel(String strCLevel){
		CompetencyLevels obj = null;
		try {
			obj = DAOManager.COMPETENCY_LEVELS_DAO.queryForFirst(
					DAOManager.COMPETENCY_LEVELS_DAO.queryBuilder()
					.where().like(CompetencyLevels.FIELD_LEVEL, strCLevel)
					.prepare()
			);
		} catch (SQLException e) {
			obj = null;
			e.printStackTrace();
		}
		return obj;
	}
	
	public static List<CompetencyLevels> getAll() throws DataAccessException{
		List<CompetencyLevels> allItems = new ArrayList<CompetencyLevels>();
		
		try {
			allItems = DAOManager.COMPETENCY_LEVELS_DAO.queryBuilder()
				.orderBy(CompetencyLevels.FIELD_ID, true).query();
		} catch (SQLException e) {
			Info.serr(e.getMessage());
			throw new DataAccessException("Error retrieving field courses");
		}
		
		return allItems;
	}
	
	public static String getAllAsListOptions() throws DataAccessException{
		List<CompetencyLevels> allItems = getAll();
		StringBuilder sb = new StringBuilder();
		
		for (CompetencyLevels cLevel : allItems){
			sb.append("<option value=\"")
				.append(cLevel.getId()).append("\">")
				.append(cLevel.getLevel())
				.append("</option>");
		}
		
		return sb.toString();
	}
	
	public static String getCompetencyLevel(CompetencyLevels cLevel) {
		String competencyLevel = "";
		
		try {
			DAOManager.COMPETENCY_LEVELS_DAO.refresh(cLevel); //Refresh Foreign Field
		} catch (SQLException e) { }
		
		if (cLevel != null){
			competencyLevel = cLevel.getLevel();
			if (competencyLevel == null) { competencyLevel = "N/A"; }
		}

		return competencyLevel;
	}

	public static CompetencyLevels getDefaultCompetencyLevel() {
		return getByCLevel(CLevelTypes.BASIC.toString());
	}
}
