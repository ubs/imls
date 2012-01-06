package phd.collins.imls.agents.ontologies;

import jade.content.onto.BasicOntology;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.schema.AgentActionSchema;
import jade.content.schema.ConceptSchema;
import jade.content.schema.PrimitiveSchema;
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
		    add(new ConceptSchema(AUTHENTICATE_INFO), AuthenticationInfo.class);

			ConceptSchema cs = (ConceptSchema) getSchema(AUTHENTICATE_INFO);
			cs.add(USER, (ConceptSchema) getSchema(USER));

			AgentActionSchema as = (AgentActionSchema) getSchema(AUTHENTICATE);
			as.add(USERNAME, (PrimitiveSchema) getSchema(BasicOntology.STRING));
			as.add(PASSWORD, (PrimitiveSchema) getSchema(BasicOntology.STRING));
			as.setResult((ConceptSchema)getSchema(AUTHENTICATE_INFO));

		} catch (OntologyException oe) {
			oe.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
