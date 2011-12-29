package phd.collins.imls.model;

import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "feedbacks")
public class Feedback extends FeedbackBase implements IModelToOtherFormats {
	public Feedback(){ super(); }
	
	public Feedback(String _feedback) {
		super(_feedback);
	}
	
	public Feedback(String _fullname, String _feedback){
		super(_fullname, _feedback);
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
}
