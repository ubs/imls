package phd.collins.imls.appsetup;

import java.sql.SQLException;

import phd.collins.imls.model.Admin;
import phd.collins.imls.model.User;
import phd.collins.imls.model.User.UserTypes;
import phd.collins.imls.util.Info;

public class SetupAdministrator implements IAppSetup {

	@Override
	public boolean setUp() throws SQLException {
		boolean success = false;
		cleanTable();
		setUpAdministrator();
		success = true;
		return success;
	}

	private void setUpAdministrator() {
		try {
			Admin admin = new Admin("Administrator 1", "imls@phd.collins.edu");
			User userAccount = new User("admin", User.DEFAULT_PASSWORD, UserTypes.ADMIN, true);
			User.create(userAccount);
			admin.setUser(userAccount);
			
			Admin.create(admin);

			Info.sout("Successfully created administrator: " + admin.getFullname() + 
					", user name: " + admin.getUser().getUserName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void cleanTable() throws SQLException {
		//Admin.deleteAll();
	}

}
