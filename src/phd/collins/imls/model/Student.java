package phd.collins.imls.model;

import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "student_data")
public class Student extends StudentBase implements IModelToOtherFormats {
	public Student(){ super(); }
	
	public Student(String _regno, String _fullname){
		super(_regno, _fullname);
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
