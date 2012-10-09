package phd.collins.imls.exceptions;

public class DataAccessException extends Exception {

	private static final long serialVersionUID = 3339652874585986561L;
	
	private static final String defaultMsg = "Error retrieving data from persistent storage.";
	
	public DataAccessException(){
		super(defaultMsg);
	}

	public DataAccessException(String msg) { super(msg); }
}
