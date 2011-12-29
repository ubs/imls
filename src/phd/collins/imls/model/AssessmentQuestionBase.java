package phd.collins.imls.model;

import com.j256.ormlite.field.DatabaseField;

public class AssessmentQuestionBase extends ModelBase {
	public static final String FIELDNAME_OPTION1 = "option1";
	public static final String FIELDNAME_OPTION2 = "option2";
	public static final String FIELDNAME_OPTION3 = "option3";
	public static final String FIELDNAME_OPTION4 = "option4";
	public static final String FIELDNAME_CORRECT_OPTION = "correct_option";
	
	@DatabaseField(generatedId = true)
	private long id;
	
	@DatabaseField(canBeNull=false, foreign=true)
	private FieldCourse fieldCourse;
	
	@DatabaseField
	private String question;
	
	@DatabaseField
	private String option1;
	
	@DatabaseField
	private String option2;
	
	@DatabaseField
	private String option3;
	
	@DatabaseField
	private String option4;
	
	@DatabaseField
	private String correct_option;
	
	@DatabaseField
	private int point;

	public AssessmentQuestionBase() { /*ORMLite needs a no-arg constructor*/ }

	public AssessmentQuestionBase(FieldCourse _fieldCourse, String _question) {
		this.setFieldCourse(_fieldCourse);
		this.setQuestion(_question);
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setFieldCourse(FieldCourse fieldCourse) {
		this.fieldCourse = fieldCourse;
	}

	public FieldCourse getFieldCourse() {
		return fieldCourse;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getQuestion() {
		return question;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption1() {
		return option1;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public String getOption2() {
		return option2;
	}

	public void setOption3(String option3) {
		this.option3 = option3;
	}

	public String getOption3() {
		return option3;
	}

	public void setOption4(String option4) {
		this.option4 = option4;
	}

	public String getOption4() {
		return option4;
	}

	public void setCorrect_option(String correct_option) {
		this.correct_option = correct_option;
	}

	public String getCorrect_option() {
		return correct_option;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getPoint() {
		return point;
	}
}
