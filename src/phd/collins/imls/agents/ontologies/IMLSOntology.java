package phd.collins.imls.agents.ontologies;

import jade.content.onto.BeanOntology;
import jade.content.onto.BeanOntologyException;
import jade.content.onto.Ontology;
import phd.collins.imls.agents.ontologies.authentication.AuthInfo;
import phd.collins.imls.agents.ontologies.authentication.Authenticate;
import phd.collins.imls.agents.vocabularies.IMLSVocabulary;
import phd.collins.imls.util.Info;

public class IMLSOntology extends BeanOntology implements IMLSVocabulary {

	private static final long serialVersionUID = -6052189373483758586L;
	
	private static Ontology theInstance;
	
	public synchronized final static Ontology getInstance() {
		initTheInstance();
		return theInstance;
	}
	
	public synchronized final static Ontology getInstance(String ontologyPackage) {
		initTheInstance();
		return null;
	}

	/**
	 * Constructor
	 * @throws BeanOntologyException
	 */
	public IMLSOntology() throws BeanOntologyException {
		this(ONTOLOGY_NAME);
	}
	
	/**
	 * Constructor with String Ontology name
	 * @param name Ontology name
	 * @throws BeanOntologyException
	 */
	public IMLSOntology(String name) throws BeanOntologyException {
		super(name);
		
		String packageName = TestAction.class.getName();
		//String packageName = "phd.collins.imls.agents.ontologies.imlsontology";
		packageName = packageName.substring(0, packageName.lastIndexOf("."));
		
		add(packageName);
		Info.sout("Ontology successfully added via BeanOntology from: " + packageName);
		
		packageName = Authenticate.class.getName();
		packageName = packageName.substring(0, packageName.lastIndexOf("."));
		add(packageName);
		Info.sout("Ontology successfully added via BeanOntology from: " + packageName);
	}
	
	protected void addAllOntologies(){
		//
	}
	
	protected void addAuthenticationOntologies() throws BeanOntologyException {
		String packageName = AuthInfo.class.getName();
		packageName = packageName.substring(0, packageName.lastIndexOf("."));
		
		add(packageName);
		Info.sout("Ontology successfully added via BeanOntology from: " + packageName);
	}
	
	protected static void initTheInstance() {
		if (theInstance == null) {
			try {
				theInstance = new IMLSOntology(ONTOLOGY_NAME);
			} catch (BeanOntologyException e) {
				Info.serr("BeanOntology Exception while initializing Ontology " + e.getMessage());
				e.printStackTrace();
			}
		}
	}
}
