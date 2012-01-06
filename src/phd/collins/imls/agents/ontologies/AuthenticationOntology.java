package phd.collins.imls.agents.ontologies;

import jade.content.onto.BasicOntology;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.schema.AgentActionSchema;
import jade.content.schema.ConceptSchema;
import jade.content.schema.PrimitiveSchema;
import phd.collins.imls.agents.Actions.AuthLogout;
import phd.collins.imls.agents.Actions.Authenticate;
import phd.collins.imls.agents.schemas.AuthenticationInfo;
import phd.collins.imls.agents.vocabularies.AuthenticationVocabulary;

public class AuthenticationOntology extends Ontology implements AuthenticationVocabulary{

	private static final long serialVersionUID = -8401361630196080981L;
	
	private final static Ontology theInstance = new AuthenticationOntology();

	public final static Ontology getInstance() { return theInstance; }

	/***Constructor***/
	public AuthenticationOntology() {
		super(ONTOLOGY_NAME, BasicOntology.getInstance());

		try {
			add(new AgentActionSchema(AUTHENTICATE), Authenticate.class);
			add(new AgentActionSchema(AUTH_LOGOUT), AuthLogout.class);
		    add(new ConceptSchema(AUTHENTICATE_INFO), AuthenticationInfo.class);

			ConceptSchema cs = (ConceptSchema) getSchema(AUTHENTICATE_INFO);
			//cs.add(USER, (ConceptSchema) getSchema(USER));
			cs.add(USERNAME, (PrimitiveSchema) getSchema(BasicOntology.STRING));
			cs.add(USER_TYPE, (PrimitiveSchema) getSchema(BasicOntology.STRING));
			cs.add(AUTHENTICATED, (PrimitiveSchema) getSchema(BasicOntology.BOOLEAN));

			AgentActionSchema as = (AgentActionSchema) getSchema(AUTHENTICATE);
			as.add(USERNAME, (PrimitiveSchema) getSchema(BasicOntology.STRING));
			as.add(PASSWORD, (PrimitiveSchema) getSchema(BasicOntology.STRING));
			as.setResult((ConceptSchema)getSchema(AUTHENTICATE_INFO));
			
			as = (AgentActionSchema) getSchema(AUTH_LOGOUT);
			as.add(LOGOUT_TYPE, (PrimitiveSchema) getSchema(BasicOntology.STRING));
			as.setResult((PrimitiveSchema)getSchema(BasicOntology.BOOLEAN));

		} catch (OntologyException oe) {
			oe.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
