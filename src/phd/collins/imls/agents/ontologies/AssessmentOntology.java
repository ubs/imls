package phd.collins.imls.agents.ontologies;

import jade.content.onto.BeanOntology;
import jade.content.onto.BeanOntologyException;
import jade.content.onto.Ontology;

import java.util.ArrayList;

import phd.collins.imls.agents.vocabularies.IMLSOntoVocabulary;
import phd.collins.imls.util.Info;

public class AssessmentOntology extends BeanOntology implements IMLSOntoVocabulary {

	private static final long serialVersionUID = -5149697134228051229L;

	private static String OntoName = "AssessmentOntology";
	
	private static ArrayList<String> BeanOntologyPackages = new ArrayList<String>(){
		private static final long serialVersionUID = 1L;
		{ add("phd.collins.imls.agents.actions.Assessment"); }
	};
	
	public static void addBeanOntologyPackage(String strPkg){
		BeanOntologyPackages.add(strPkg);
	}
	
	/**
	 * Main method to initialise and return a BeanOntology instance
	 * @return BeanOntology ontology
	 */
	public synchronized final static Ontology getInstance() {
		Ontology ontoInstance = null;
		
		try{
			ontoInstance = new AssessmentOntology();
			Info.sout("Ontology Object Created: " + ontoInstance);
		}
		catch (BeanOntologyException e) {
			Info.serr("BeanOntology Exception while initializing Ontology " + e.getMessage());
			e.printStackTrace();
		}
		
		return ontoInstance;
	}
	

	/**
	 * Constructor
	 * @throws BeanOntologyException
	 */
	public AssessmentOntology() throws BeanOntologyException {
		super(OntoName);
		
		for (String strPkg : BeanOntologyPackages){
			Info.sout("\n*** > Adding Ontology Packages > Total Packages: " + 
					BeanOntologyPackages.size() + ", Current Package: " + strPkg );
			
			this.add(strPkg);
		}
	}
	
}
