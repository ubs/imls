package phd.collins.imls.model;

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
	
	//Static Field and Methods
	public static boolean areaFieldsExist(){
		return (new AreaField().countAll() != 0);
	}
}
