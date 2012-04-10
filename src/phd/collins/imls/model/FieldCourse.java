package phd.collins.imls.model;

import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "field_courses")
public class FieldCourse extends FieldCourseBase implements IModelToOtherFormats {
	public FieldCourse(){ super(); }
	
	public FieldCourse(String _coursename){
		super(_coursename);
	}
	
	public FieldCourse(String _coursename, String _description){
		super(_coursename, _description);
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
	public static boolean fieldCoursesExist(){
		return (new FieldCourse().countAll() != 0);
	}
}
