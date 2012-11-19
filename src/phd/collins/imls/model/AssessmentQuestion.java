package phd.collins.imls.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import phd.collins.imls.exceptions.DataAccessException;
import phd.collins.imls.util.Info;
import phd.collins.imls.util.UtilGeneral;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "assessment_questions")
public class AssessmentQuestion extends AssessmentQuestionBase implements IModelToOtherFormats {
	private static int totalOptionsAvailable = 4;
	private static int questionSummaryLength = 30;

	public AssessmentQuestion(){ super(); }
	
	public AssessmentQuestion(
			AreaField _areaField, String _question, 
			String _option1, String _option2, String _option3, String _option4, 
			String _correctOption, String _Point, CompetencyLevels _cLevel) {
		super(_areaField, _question, _option1, _option2, _option3, _option4, _correctOption, _Point, _cLevel);
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
	
	public static AssessmentQuestion AddAssessmentQuestion(String parAreaFieldID, String parQuestion,
			String parCompetencyLevelID, String parOption1, String parOption2, String parOption3, String parOption4,
			String parCorrectOption, String parPoint) throws DataAccessException {
		
			AreaField areaField = AreaField.get(Long.parseLong(parAreaFieldID));
			CompetencyLevels cLevel = CompetencyLevels.get(Long.parseLong(parCompetencyLevelID));
			
			return AddAssessmentQuestion(
					areaField, parQuestion, cLevel, parOption1, parOption2, parOption3, parOption4,
					parCorrectOption, parPoint);
	}
	
	public static AssessmentQuestion AddAssessmentQuestion(AreaField parAreaField, String parQuestion,
			CompetencyLevels parCompetencyLevel, String parOption1, String parOption2, String parOption3, String parOption4,
			String parCorrectOption, String parPoint) throws DataAccessException{
		AssessmentQuestion obj;
		
		try {
			obj = new AssessmentQuestion(
					parAreaField, parQuestion, parOption1, parOption2, parOption3, parOption4,
					parCorrectOption, parPoint, parCompetencyLevel);
			
			DAOManager.ASSESSMENT_QUESTION_DAO.create(obj);
		} catch (Exception e){
			Info.serr(e.getMessage());
			throw new DataAccessException("Error saving assessment question");
		}
		
		return obj;
	}
	
	public static AssessmentQuestion get(long assQID){
		AssessmentQuestion assQ = null;
		try {
			assQ = DAOManager.ASSESSMENT_QUESTION_DAO.queryForId(assQID);
		} catch (SQLException e) {
			assQ = null;
			e.printStackTrace();
		}
		return assQ;
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
	
	public static List<AssessmentQuestion> getAllForSpecifiedAreaField(String parAreaFieldID) throws DataAccessException{
		ForeignCollection<AssessmentQuestion> allItems = null;
		
		try {
			Long areaFieldID = -1L;
			try{ areaFieldID = Long.parseLong(parAreaFieldID); } catch (Exception e) { areaFieldID = null; }
			
			if (areaFieldID != null) {
				AreaField areaField = AreaField.get(areaFieldID);
				allItems = areaField.getColAssessmentQuestions();
			}
		} catch (Exception e) {
			Info.serr(e.getMessage());
			throw new DataAccessException("Error retrieving assessment questions for specified course");
		}
		
		return UtilGeneral.ForeignCollectionToList(allItems);
	}
	
	public static List<AssessmentQuestion> getRandomAssessmentQuestions(String parAreaFieldID) throws DataAccessException {
		return getRandomAssessmentQuestions(parAreaFieldID, 10);
	}
	
	public static List<AssessmentQuestion> getRandomAssessmentQuestions(String parAreaFieldID, int numQuestions) throws DataAccessException{
		List<AssessmentQuestion> lstRandomAssQs = new ArrayList<AssessmentQuestion>();
		List<AssessmentQuestion> lstAllQs = null;
		
		try {
			lstAllQs = getAllForSpecifiedAreaField(parAreaFieldID);
			
			if (lstAllQs != null) {
				lstRandomAssQs = UtilGeneral.getRandomItemsFromList(lstAllQs, numQuestions);
			}
		} catch (Exception e) {
			Info.serr(e.getMessage());
			throw new DataAccessException("Error retrieving random assessment questions");
		}
		
		return lstRandomAssQs;
	}
	
	public static String getAssessmentQuestionIDs(List<AssessmentQuestion> lstAssQ) {
		StringBuilder sb = new StringBuilder();
		for (AssessmentQuestion assQ : lstAssQ){
			sb.append(assQ.getId()).append(",");
		}
		return sb.toString();
	}
	
	public static List<String> getAssessmentQuestionIDsFromString(String strAssQIDs) {
		List<String> lstAssQIDs = new ArrayList<String>();
		lstAssQIDs = Arrays.asList(strAssQIDs.split(","));
		return lstAssQIDs;
	}
	
	public static int scoreAssessmentAnswers(List<AssessmentAnswer> lstAnswers){
		int possibleScore = 0, totalScore = 0, percentageScore = 0;
		
		AssessmentQuestion assQ = null;
		
		for (AssessmentAnswer aa : lstAnswers){
			assQ = AssessmentQuestion.get(Long.parseLong(aa.getStrAssessmentQID()));
			
			if (assQ != null){
				possibleScore += assQ.getPoint();
				String correctOption = assQ.getOption(Integer.parseInt(assQ.getCorrect_option()));
				Info.sout("Correct Option: " + correctOption + ", Chosen Option: " + aa.getStrChosenOption());
				
				if (correctOption.equalsIgnoreCase(aa.getStrChosenOption())) {
					totalScore += assQ.getPoint();
					Info.sout("Correct! Total Score: " + totalScore + " out of " + possibleScore);
				}
			}
		}
		
		percentageScore = (int)((float)totalScore * 100.0 / (float)possibleScore);
		Info.sout("totalScore : " + totalScore + ", possibleScore : " + possibleScore + 
				", percentageScore : " + percentageScore);
		
		return percentageScore;
	}
	
	public static String determineAssessedCompetencyLevel(int score){
		String cLevel = "BASIC";
		
		if (score > 70) { cLevel = "ADVANCED"; }
		else if (score > 50) { cLevel = "INTERMEDIATE"; }
		else { cLevel = "BASIC"; }
		
		return cLevel;
	}
}
