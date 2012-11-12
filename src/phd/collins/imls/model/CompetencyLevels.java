package phd.collins.imls.model;

import java.sql.SQLException;

import phd.collins.imls.exceptions.DataAccessException;
import phd.collins.imls.util.Info;

import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "competency_levels")
public class CompetencyLevels extends CompetencyLevelsBase implements IModelToOtherFormats {

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
		CompetencyLevels cl = new CompetencyLevels(cLevel);
		try {
			DAOManager.COMPETENCY_LEVEL_DAO.createIfNotExists(cl);
		} catch (SQLException e) {
			Info.serr(e.getMessage());
			throw new DataAccessException("Error saving competency level");
		}
	}
	
	public static void deleteAll(){
		try {
			DeleteBuilder<CompetencyLevels, Long> deleteBuilder = DAOManager.COMPETENCY_LEVEL_DAO.deleteBuilder();
			DAOManager.COMPETENCY_LEVEL_DAO.delete(deleteBuilder.prepare());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
