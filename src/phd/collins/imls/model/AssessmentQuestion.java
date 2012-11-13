package phd.collins.imls.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import phd.collins.imls.exceptions.DataAccessException;
import phd.collins.imls.util.Info;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "assessment_questions")
public class AssessmentQuestion extends AssessmentQuestionBase implements IModelToOtherFormats {
	private static int totalOptionsAvailable = 4;
	private static int questionSummaryLength = 30;

	public AssessmentQuestion(){ super(); }
	
	public AssessmentQuestion(FieldCourse _fieldCourse, String _question, String _option1, String _option2, String _option3, String _option4, String _correctOption, CompetencyLevels _cLevel) {
		super(_fieldCourse, _question, _option1, _option2, _option3, _option4, _correctOption, _cLevel);
	}
	
	public HashMap<String, String> getAllOptions(){
		//Store the option titles as final statics in base
		throw new NotImplementedException();
	}
	
	public HashMap<String, String> getAllOptionsWithCorrectOption(){
		throw new NotImplementedException();
	}
	
	public String getQuestionSummarised(){
		String strQ = getQuestion();
		if (strQ.length() > questionSummaryLength ){
			strQ = strQ.substring(0, questionSummaryLength) + "...";
		}
		
		return strQ;
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
			case 999:
			default:
				option = getCorrect_option();
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
	
	public static String getPossibleOptionsAsSelectList() {
		StringBuilder sb = new StringBuilder();
		
		for (int k = 1; k <= totalOptionsAvailable ; k++){
			sb.append("<option value=\"")
				.append(k).append("\">").append(k).append("</option>");
		}
		
		return sb.toString();
	}
	
	public static AssessmentQuestion AddAssessmentQuestion(String parCourseID, String parQuestion, String parCompetencyLevelID, 
			String parOption1, String parOption2, String parOption3, String parOption4, String parCorrectOption, String parPoint){
		return null;
	}
	
	public static List<AssessmentQuestion> getAll() throws DataAccessException{
		List<AssessmentQuestion> allItems = new ArrayList<AssessmentQuestion>();
		
		try {
			allItems = DAOManager.ASSESSMENT_QUESTION_DAO.queryForAll();
		} catch (SQLException e) {
			Info.serr(e.getMessage());
			throw new DataAccessException("Error retrieving assessment questions");
		}
		
		return allItems;
	}
}
