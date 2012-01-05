package phd.collins.imls.agents.vocabularies;

public interface AuthenticationVocabulary {
	
	public static final String ONTOLOGY_NAME = "auth-ontology";
	
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String LAST_LOGIN_DATE = "last_login_date";
	public static final String AGENTINFO = "agentInfo";
	
	public static final String FIRST_ELEMENT = "firstElement";
    public static final String SECOND_ELEMENT = "secondElement";
	public static final String FIRST_COMPLEX_ELEMENT = "firstComplexElement";
    public static final String SECOND_COMPLEX_ELEMENT = "secondComplexElement";
	public static final String NUMBERS = "numbers";
	public static final String COMPLEX_ELEMENT = "complexElement";
    public static final String REAL = "real";
    public static final String IMMAGINARY = "immaginary";
    public static final String AGENTAID = "agentAid";
    public static final String STARTDATE = "startDate";
    public static final String DATE = "date";
    
    //Actions
    public static final String AUTHENTICATE = "authenticate";
    public static final String DIFF = "diff";
}
