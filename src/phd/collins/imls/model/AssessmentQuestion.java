package phd.collins.imls.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import phd.collins.imls.exceptions.DataAccessException;
import phd.collins.imls.util.Info;
import phd.collins.imls.util.UtilGeneral;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "assessment_questions")
public class AssessmentQuestion extends AssessmentQuestionBase implements IModelToOtherFormats {
	private static int totalOptionsAvailable = 4;
	private static int questionSummaryLength = 30;

	public AssessmentQuestion(){ super(); }
	
	public AssessmentQuestion(
			FieldCourse _fieldCourse, String _question, 
			String _option1, String _option2, String _option3, String _option4, 
			String _correctOption, String _Point, CompetencyLevels _cLevel) {
		super(_fieldCourse, _question, _option1, _option2, _option3, _option4, _correctOption, _Point, _cLevel);
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
	
	public static boolean assessmentQuestionsExist(){
		return (new AssessmentQuestion().countAll() != 0);
	}
	
	public static String getPossibleOptionsAsSelectList() {
		StringBuilder sb = new StringBuilder();
		
		for (int k = 1; k <= totalOptionsAvailable ; k++){
			sb.append("<option value=\"")
				.append(k).append("\">").append(k).append("</option>");
		}
		
		return sb.toString();
	}
	
	public static AssessmentQuestion AddAssessmentQuestion(String parCourseID, String parQuestion,
			String parCompetencyLevelID, String parOption1, String parOption2, String parOption3, String parOption4,
			String parCorrectOption, String parPoint) throws DataAccessException {
		
			FieldCourse fc = FieldCourse.get(Long.parseLong(parCourseID));
			CompetencyLevels cLevel = CompetencyLevels.get(Long.parseLong(parCompetencyLevelID));
			
			return AddAssessmentQuestion(
					fc, parQuestion, cLevel, parOption1, parOption2, parOption3, parOption4,
					parCorrectOption, parPoint);
	}
	
	public static AssessmentQuestion AddAssessmentQuestion(FieldCourse parFieldCourse, String parQuestion,
			CompetencyLevels parCompetencyLevel, String parOption1, String parOption2, String parOption3, String parOption4,
			String parCorrectOption, String parPoint) throws DataAccessException{
		AssessmentQuestion obj;
		
		try {
			obj = new AssessmentQuestion(
					parFieldCourse, parQuestion, parOption1, parOption2, parOption3, parOption4,
					parCorrectOption, parPoint, parCompetencyLevel);
			
			DAOManager.ASSESSMENT_QUESTION_DAO.create(obj);
		} catch (Exception e){
			Info.serr(e.getMessage());
			throw new DataAccessException("Error saving assessment question");
		}
		
		return obj;
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
	
	public static ForeignCollection<AssessmentQuestion> getAllForSpecifiedCourse(String parCourseID) throws DataAccessException{
		ForeignCollection<AssessmentQuestion> allItems = null;
		
		try {
			Long courseID = -1L;
			try{ courseID = Long.parseLong(parCourseID); } catch (Exception e) { courseID = null; }
			
			if (courseID != null) {
				FieldCourse fc = FieldCourse.get(courseID);
				allItems = fc.getColAssessmentQuestions();
			}
		} catch (Exception e) {
			Info.serr(e.getMessage());
			throw new DataAccessException("Error retrieving assessment questions for specified course");
		}
		
		return allItems;
	}
	
	public static List<AssessmentQuestion> getRandomAssessmentQuestions(String parCourseID) throws DataAccessException {
		return getRandomAssessmentQuestions(parCourseID, 10);
	}
	public static List<AssessmentQuestion> getRandomAssessmentQuestions(String parCourseID, int numQuestions) throws DataAccessException{
		List<AssessmentQuestion> allItems = new ArrayList<AssessmentQuestion>();
		ForeignCollection<AssessmentQuestion> randomItems = null;
		
		try {
			randomItems = getAllForSpecifiedCourse(parCourseID);
			
			if (randomItems != null) {
				List<Integer> randomIndices = UtilGeneral.getRandomIndices(numQuestions, randomItems.size(), 1);
				int currentIndex = 0;
				
				for (AssessmentQuestion assQ : randomItems){
					if (randomIndices.contains(++currentIndex)) {
						allItems.add(assQ);
					}
				}
			}
		} catch (Exception e) {
			Info.serr(e.getMessage());
			throw new DataAccessException("Error retrieving random assessment questions");
		}
		
		return allItems;
	}
}
