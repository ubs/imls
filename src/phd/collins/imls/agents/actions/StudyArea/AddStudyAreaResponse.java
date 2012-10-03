package phd.collins.imls.agents.actions.StudyArea;

import jade.content.Concept;
import jade.content.onto.annotations.Slot;

import java.util.Hashtable;

public class AddStudyAreaResponse implements Concept {
	
	private static final long serialVersionUID = 8182449667889697397L;
	
	private String id;
	private String studyareaname;
	private String description;
	private boolean issuccessful;
	
	public AddStudyAreaResponse(){}
	
	public AddStudyAreaResponse(Hashtable<String, String> attrHash){
		this();
		setDefaults();
		setId(attrHash.get("id"));
		setStudyAreaName(attrHash.get("studyAreaName"));
		setDescription(attrHash.get("Description"));
		setSuccessStatus( Boolean.valueOf(attrHash.get("successStatus")) );
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append(getClass().getName())
		.append("(")
		.append(" id: ").append(getId())
		.append(" studyAreaName: ").append(getStudyAreaName())
		.append(" Description: ").append(getDescription())
		.append(" successStatus: ").append(isSuccessStatus())
		.append(")");
		
		return sb.toString();
	}
	
	public void setDefaults(){
		this.setId("");
		this.setStudyAreaName("");
		this.setDescription("");
		this.setSuccessStatus(false);
	}

	@Slot(mandatory = false)
	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	@Slot(mandatory = false)
	public void setStudyAreaName(String studyAreaName) {
		this.studyareaname = studyAreaName;
	}

	public String getStudyAreaName() {
		return studyareaname;
	}

	@Slot(mandatory = false)
	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	@Slot(mandatory = false)
	public void setSuccessStatus(boolean successStatus) {
		this.issuccessful = successStatus;
	}

	public boolean isSuccessStatus() {
		return issuccessful;
	}
}
