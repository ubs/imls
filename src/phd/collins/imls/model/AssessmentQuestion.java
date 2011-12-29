package phd.collins.imls.model;

import java.util.HashMap;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "assessment_questions")
public class AssessmentQuestion extends AssessmentQuestionBase implements IModelToOtherFormats {
	public AssessmentQuestion(){ super(); }
	
	public AssessmentQuestion(FieldCourse _fieldCourse, String _question) {
		super(_fieldCourse, _question);
	}
	
	public HashMap<String, String> getAllOptions(){
		//Store the option titles as final statics in base
		throw new NotImplementedException();
	}
	
	public HashMap<String, String> getAllOptionsWithCorrectOption(){
		throw new NotImplementedException();
	}
	
	public String getOption(int optionNumber){
		String option = null;
		
		switch (optionNumber){
			case 1:
				option = getOption1();
				break;
			case 2:
				option = getOption2();
				break;
			case 3:
				option = getOption3();
				break;
			case 4:
				option = getOption4();
				break;
		}
		
		return option;
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
