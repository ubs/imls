package phd.collins.imls.model;

import java.util.Date;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;

public class FeedbackBase extends ModelBase {
	@DatabaseField(generatedId = true)
	private long id;
	
	@DatabaseField
	private String fullname;
	
	@DatabaseField
	private String feedback;
	
	@DatabaseField(dataType=DataType.DATE_STRING)
	private Date feedback_date;

	public FeedbackBase() { /*ORMLite needs a no-arg constructor*/ }

	public FeedbackBase(String _feedback) {
		this("", _feedback);
	}
	
	public FeedbackBase(String _fullname, String _feedback) {
		this.setFullname(_fullname);
		this.setFeedback(_feedback);
		this.setFeedback_date(new Date());
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback_date(Date feedback_date) {
		this.feedback_date = feedback_date;
	}

	public Date getFeedback_date() {
		return feedback_date;
	}
}
