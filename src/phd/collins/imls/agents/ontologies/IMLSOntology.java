package phd.collins.imls.agents.ontologies;

import jade.content.onto.BeanOntology;
import jade.content.onto.BeanOntologyException;
import jade.content.onto.Ontology;
import phd.collins.imls.agents.vocabularies.IMLSOntoVocabulary;
import phd.collins.imls.util.Info;

public class IMLSOntology extends BeanOntology implements IMLSOntoVocabulary {

	private static final long serialVersionUID = -6052189373483758586L;
	
	private static Ontology theInstance;
	private String[] beanOntologyPackages = { "phd.collins.imls.agents.actions" };
	
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
		
		for (String pkgName : beanOntologyPackages) {
			Info.sout("Currently adding package in IMLSOntology: " + pkgName);
			add(pkgName);
			Info.sout("Ontology successfully added via BeanOntology from: " + pkgName);
		}
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
