package phd.collins.imls.appsetup;

import java.sql.SQLException;

import phd.collins.imls.util.Info;

public class DefaultAppSetupImpl {
	public static void main(String args[]) throws Exception {
		new DefaultAppSetupImpl().setupApp();
	}

	private void setupApp() {
		try {
			Info.sout("Setting up competency levels...");
			new SetupCompetencyLevels().setUp();
			
			Info.sout("Setting up default Administrator...");
			new SetupAdministrator().setUp();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
