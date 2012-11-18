package phd.collins.imls.model;

public class AssessmentAnswer {
	private String strAssQID;
	private String strChosenOption;
	private AssessmentQuestion assessmentQuestion;
	
	public AssessmentAnswer(String strAssQID, String strChosenOption){
		this.setStrAssessmentQID(strAssQID);
		this.setStrChosenOption(strChosenOption);
	}
	
	public void setStrAssessmentQID(String strAssessmentID) {
		this.strAssQID = strAssessmentID;
	}

	public String getStrAssessmentQID() {
		return strAssQID;
	}

	public void setStrChosenOption(String strChosenOption) {
		this.strChosenOption = strChosenOption;
	}

	public String getStrChosenOption() {
		return strChosenOption;
	}
	
	public void setAssessmentQuestion(AssessmentQuestion assessmentQuestion) {
		this.assessmentQuestion = assessmentQuestion;
	}
	
	public AssessmentQuestion getAssessmentQuestion() {
		return assessmentQuestion;
	}
}
