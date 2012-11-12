package phd.collins.imls.appsetup;

import java.sql.SQLException;

import phd.collins.imls.exceptions.DataAccessException;
import phd.collins.imls.model.CompetencyLevels;

public class SetupCompetencyLevels implements IAppSetup {

	@Override
	public boolean setUp() throws SQLException {
		boolean success = false;
		cleanTable();
		setUpCompetencyLevels();
		success = true;
		return success;
	}

	private void setUpCompetencyLevels() {
		try {
			CompetencyLevels.create("BASIC");
			CompetencyLevels.create("INTERMEDIATE");
			CompetencyLevels.create("ADVANCED");
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	private void cleanTable() throws SQLException {
		CompetencyLevels.deleteAll();
	}

}
