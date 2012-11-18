package phd.collins.imls.appsetup;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import phd.collins.imls.exceptions.DataAccessException;
import phd.collins.imls.model.AreaField;
import phd.collins.imls.model.AssessmentQuestion;
import phd.collins.imls.model.CompetencyLevels;
import phd.collins.imls.util.Info;

public class SetupRandomAssessmentQuestions implements IAppSetup {
	private Random rnd = new Random();
	
	@Override
	public boolean setUp() throws SQLException {
		boolean success = false;
		cleanTable();
		setUpRandomAssessmentQuestions();
		success = true;
		return success;
	}
	
	private void setUpRandomAssessmentQuestions() {
		try {
			List<AreaField> allAreaFields = AreaField.getAll();
	
			if (allAreaFields.size() > 0){
				List<CompetencyLevels> allCLevels = CompetencyLevels.getAll();
				
				if (allCLevels.size() > 0){
					for (AreaField areaField : allAreaFields){
						for (CompetencyLevels cl : allCLevels){
							Info.sout("Generating " + cl.getLevel() + 
									" level random assessment questions for " + areaField.getField_name());
							addRandomAssessmentQuestions(areaField, cl, 5);
						}
					}
				}
			}
		} catch (DataAccessException e) {
			Info.serr("Error while generating random assessment questions: " + e.getMessage());
		}
	}

	private void addRandomAssessmentQuestions(AreaField areaField, CompetencyLevels cl, int numOfQs) {
		String randomQuestionBase = "Random Assessment Question ";
		String randomQuestion = "";
		String parOption1 = "Random Option 1", parOption2 = "Random Option 2", 
			parOption3 = "Random Option 3", parOption4 = "Random Option 4";
		String parCorrectOption = "1";
		String parPoint = "";
		
		try{
			for (int ctr = 1; ctr <= numOfQs; ctr++){
				randomQuestion = randomQuestionBase + ctr;
				int randomInt = rnd.nextInt(5);
				if (randomInt == 0) { randomInt = 1; }
				parCorrectOption = String.valueOf(randomInt);
				parPoint = String.valueOf(rnd.nextInt(10) + 1);
				
				Info.sout(" Adding Question: " + randomQuestion + ", Correct Option: " + parCorrectOption + ", Point: " + parPoint);
				
				AssessmentQuestion.AddAssessmentQuestion(
						areaField, randomQuestion, cl, parOption1, parOption2, parOption3, parOption4, parCorrectOption, parPoint);
			}
		} catch (Exception e) {
			Info.serr("Error while adding random assessment questions: " + e.getMessage());
		}
	}


	private void cleanTable() throws SQLException {
		//Nothing to do here
	}

}
