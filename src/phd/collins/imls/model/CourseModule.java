package phd.collins.imls.model;

import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "course_modules")
public class CourseModule extends CourseModuleBase implements IModelToOtherFormats {
	public CourseModule(){ super(); }
	
	public CourseModule(FieldCourse _fieldCourse, String _module_name) {
		super(_fieldCourse, _module_name);
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
