package phd.collins.imls.agents.actions;

import jade.content.AgentAction;
import jade.content.onto.annotations.Result;

@Result(type=AddStudyAreaResponse.class)
public class AddStudyArea implements AgentAction {

	private static final long serialVersionUID = 7970540272885133637L;
	
	private String studyAreaName;
	private String Description;
	
	public AddStudyArea() {
		super();
	}

	public void setStudyAreaName(String studyAreaName) {
		this.studyAreaName = studyAreaName;
	}

	public String getStudyAreaName() {
		return studyAreaName;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getDescription() {
		return Description;
	}
	
	@Override
	public String toString() {
		return new StringBuffer().append("AddStudyArea (")
			.append(studyAreaName).append(",").append(Description)
			.append(")").toString();
	}
}
