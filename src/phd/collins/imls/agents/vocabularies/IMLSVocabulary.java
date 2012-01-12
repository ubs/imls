package phd.collins.imls.agents.vocabularies;

public interface IMLSVocabulary {
	
	public static final String ONTOLOGY_NAME = "imls-ontology";
	
	//Concepts
	public static final String RESULT = "result";
	public static final String AUTHENTICATE_RESPONSE = "AuthenticateResponse";
	
	//Elements
	public static final String USER = "user";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String USER_IS_ACTIVE = "isActive";
	public static final String USER_TYPE = "userType";
	public static final String LAST_LOGIN_DATE = "lastLoginDate";
	public static final String AUTHENTICATED = "authenticated";
	public static final String LOGOUT_TYPE = "logoutType";
	
    //Actions
    public static final String AUTHENTICATE = "Authenticate";
    public static final String AUTH_LOGOUT = "AuthLogout";
}
