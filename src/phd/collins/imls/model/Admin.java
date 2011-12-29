package phd.collins.imls.model;

import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "administrators")
public class Admin extends AdminBase implements IModelToOtherFormats {
	public Admin(){ super(); }
	
	public Admin(String _fullname, String _contact){
		super(_fullname, _contact);
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
