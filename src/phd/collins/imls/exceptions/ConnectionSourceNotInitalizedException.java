package phd.collins.imls.exceptions;

public class ConnectionSourceNotInitalizedException extends Exception {
	
	private static final long serialVersionUID = 8100876250625520678L;
	
	private static final String defaultMsg = "Database Connection Source has not been initialized.";
	
	public ConnectionSourceNotInitalizedException(){
		super(defaultMsg);
	}

	public ConnectionSourceNotInitalizedException(String msg) { super(msg); }
}
