/*****************************************************************
JADE - Java Agent DEvelopment Framework is a framework to develop 
multi-agent systems in compliance with the FIPA specifications.
Copyright (C) 2002 TILAB

GNU Lesser General Public License

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation, 
version 2.1 of the License. 

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the
Free Software Foundation, Inc., 59 Temple Place - Suite 330,
Boston, MA  02111-1307, USA.
*****************************************************************/

package com.tilab.wsig.examples;

import jade.content.onto.BasicOntology;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.schema.AgentActionSchema;
import jade.content.schema.ConceptSchema;
import jade.content.schema.ObjectSchema;
import jade.content.schema.PrimitiveSchema;
import jade.content.schema.TermSchema;

public class FacOntology extends Ontology{

	private final static Ontology theInstance = new FacOntology();

	public final static Ontology getInstance() {
		return theInstance;
	}

	/**
	 * Constructor
	 */
	public FacOntology() {
		super("fac-ontology", BasicOntology.getInstance());

		try {
			add(new AgentActionSchema("fac"), Fac.class);

			AgentActionSchema as = (AgentActionSchema) getSchema("fac");
			as.add("number", (PrimitiveSchema) getSchema(BasicOntology.INTEGER));
			as.setResult((PrimitiveSchema)getSchema(BasicOntology.FLOAT));
			
			/*			
			ConceptSchema cs = (ConceptSchema) getSchema();
			cs.add(REAL, (PrimitiveSchema) getSchema(BasicOntology.FLOAT));
			cs.add(IMMAGINARY, (PrimitiveSchema) getSchema(BasicOntology.FLOAT), ObjectSchema.OPTIONAL);

			cs = (ConceptSchema) getSchema(AGENTINFO);
			cs.add(AGENTAID, (TermSchema) getSchema(BasicOntology.AID));
			cs.add(STARTDATE, (PrimitiveSchema) getSchema(BasicOntology.DATE));

			AgentActionSchema as = (AgentActionSchema) getSchema(ABS);
			as.add(COMPLEX, (ConceptSchema) getSchema(COMPLEX));
			as.setResult((PrimitiveSchema)getSchema(BasicOntology.FLOAT));

			as = (AgentActionSchema) getSchema(SUM);
			as.add(FIRST_ELEMENT, (PrimitiveSchema) getSchema(BasicOntology.FLOAT));
			as.add(SECOND_ELEMENT, (PrimitiveSchema) getSchema(BasicOntology.FLOAT), ObjectSchema.OPTIONAL);
			as.setResult((PrimitiveSchema)getSchema(BasicOntology.FLOAT));
			
			as = (AgentActionSchema) getSchema(DIFF);
			as.add(FIRST_ELEMENT, (PrimitiveSchema) getSchema(BasicOntology.FLOAT));
			as.add(SECOND_ELEMENT, (PrimitiveSchema) getSchema(BasicOntology.FLOAT));
			as.setResult((PrimitiveSchema)getSchema(BasicOntology.FLOAT));

			as = (AgentActionSchema) getSchema(MULTIPLICATION);
			as.add(NUMBERS, (PrimitiveSchema) getSchema(BasicOntology.FLOAT), 2, ObjectSchema.UNLIMITED);
			as.setResult((PrimitiveSchema)getSchema(BasicOntology.FLOAT));
			
			as = (AgentActionSchema) getSchema(SUMCOMPLEX);
			as.add(FIRST_COMPLEX_ELEMENT, (ConceptSchema) getSchema(COMPLEX));
			as.add(SECOND_COMPLEX_ELEMENT, (ConceptSchema) getSchema(COMPLEX));
			as.setResult((ConceptSchema) getSchema(COMPLEX));
			
			as = (AgentActionSchema) getSchema(GETCOMPONENTS);
			as.add(COMPLEX, (ConceptSchema) getSchema(COMPLEX));
			as.setResult((PrimitiveSchema)getSchema(BasicOntology.FLOAT), 2, 2);
			
			as = (AgentActionSchema) getSchema(GETRANDOM);
			as.setResult((ConceptSchema) getSchema(COMPLEX));
			
			as = (AgentActionSchema) getSchema(PRINTCOMPLEX);
			as.add(COMPLEX, (ConceptSchema) getSchema(COMPLEX));
			
			as = (AgentActionSchema) getSchema(GETAGENTINFO);
			as.setResult((ConceptSchema)getSchema(AGENTINFO));
			
			as = (AgentActionSchema) getSchema(CONVERTDATE);
			as.add(DATE, (PrimitiveSchema) getSchema(BasicOntology.DATE));
			as.setResult((PrimitiveSchema)getSchema(BasicOntology.STRING));
			*/

		} catch (OntologyException oe) {
			oe.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
