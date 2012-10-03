package phd.collins.imls.agents.vocabularies;

public interface IMLSAgentsVocabulary {
	
	public static final String VOCABULARY_NAME = "imls-agents-vocabulary";
	
	//General
	public static final String TRUE  = "true";
	public static final String FALSE = "false";
	
	//Authentication Agent
	public static final String AUTHENTICATION_AGENT = "AuthenticationAgent";
	public static final String AUTHENTICATION_AGENT_OWNER = "AuthenticationAgentOwner";
	public static final String AUTHENTICATION_AGENT_BASENAME = "IMLS_Authentication_Agent";
	
	//Study Area Agent
	public static final String STUDY_AREA_AGENT = "StudyAreaAgent";
	public static final String STUDY_AREA_AGENT_OWNER = "StudyAreaAgentOwner";
	public static final String STUDY_AREA_AGENT_BASENAME = "IMLS_StudyArea_Agent";
	
	//IMLS General Agent
	public static final String IMLS_GENERAL_AGENT = "IMLSGeneralAgent";
	public static final String IMLS_GENERAL_AGENT_OWNER = "IMLSGeneralAgentOwner";
	public static final String IMLS_GENERAL_AGENT_BASENAME = "IMLS_General_Agent";
}
