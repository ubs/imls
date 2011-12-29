package phd.collins.imls.model;

import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "users")
public class User extends UserBase implements IModelToOtherFormats {
	public User(){ super(); }
	
	public User(String _username, String _password){
		super(_username, _password);
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
}
