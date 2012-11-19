package phd.collins.imls.model;

import java.util.Date;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

public class StudentBase extends ModelBase {
	
	@DatabaseField(generatedId = true)
	private long id;
	
	@DatabaseField
	private String regno;
	
	@DatabaseField
	private String fullname;
	
	@DatabaseField
	private String address;
	
	@DatabaseField(canBeNull=true, foreign=true, columnName="field_of_study")
	private AreaField field_of_study;
	
	@DatabaseField(dataType=DataType.DATE_STRING)
	private Date date_registered;
	
	@DatabaseField(canBeNull=false, foreign=true, columnName="user_id")
	private User user;
	
	@ForeignCollectionField(eager = false, orderColumnName=StudentStudyRecord.FIELD_FIELD_COURSE_ID)
	private ForeignCollection<StudentStudyRecord> colMyStudyRecords;
	
	public static final String FIELD_REGNUMBER 	= "regno";

	public StudentBase() { /*ORMLite needs a no-arg constructor*/ }

	public StudentBase(String _regno, String _fullname) {
		this.setRegno(_regno);
		this.setFullname(_fullname);
	}

	public StudentBase(String _regno, String _fullname, String _address) {
		this.setRegno(_regno);
		this.setFullname(_fullname);
		this.setAddress(_address);
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setRegno(String regno) {
		this.regno = regno;
	}

	public String getRegno() {
		return regno;
	}
	
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getFullname() {
		return fullname;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}
	
	public void setField_of_study(AreaField field_of_study) {
		this.field_of_study = field_of_study;
	}

	public AreaField getField_of_study() {
		return field_of_study;
	}

	public void setDate_registered(Date date_registered) {
		this.date_registered = date_registered;
	}

	public Date getDate_registered() {
		return date_registered;
	}
	
	public ForeignCollection<StudentStudyRecord> getColMyStudyRecords() {
		return colMyStudyRecords;
	}

}
