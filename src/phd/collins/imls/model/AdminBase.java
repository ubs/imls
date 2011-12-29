package phd.collins.imls.model;

import com.j256.ormlite.field.DatabaseField;

public class AdminBase extends ModelBase {
	@DatabaseField(generatedId = true)
	private long id;
	
	@DatabaseField
	private String fullname;
	
	@DatabaseField
	private String contact;
	
	@DatabaseField(canBeNull=false, foreign=true)
	private User user;

	public AdminBase() { /*ORMLite needs a no-arg constructor*/ }

	public AdminBase(String _fullname, String _contact) {
		this.setFullname(_fullname);
		this.setContact(_contact);
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

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getContact() {
		return contact;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}
}
