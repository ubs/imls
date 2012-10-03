package phd.collins.imls.agents.actions.StudyArea;

import jade.content.AgentAction;
import jade.content.onto.annotations.Result;

@Result(type=AddStudyAreaResponse.class)
public class AddStudyArea implements AgentAction {

	private static final long serialVersionUID = 7970540272885133637L;
	
	private String studyareaname;
	private String description;
	
	public AddStudyArea() {
		super();
	}

	public void setStudyAreaName(String studyAreaName) {
		this.studyareaname = studyAreaName;
	}

	public String getStudyAreaName() {
		return studyareaname;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
	@Override
	public String toString() {
		return new StringBuffer().append("AddStudyArea (")
			.append(studyareaname).append(",").append(description)
			.append(")").toString();
	}
}
