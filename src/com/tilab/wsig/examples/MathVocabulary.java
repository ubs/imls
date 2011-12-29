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

public interface MathVocabulary {
	
	public static final String ONTOLOGY_NAME = "math-ontology";
	
	public static final String COMPLEX = "complex";
	public static final String RESULT = "result";
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
    public static final String SUM = "sum";
    public static final String DIFF = "diff";
	public static final String MULTIPLICATION = "multiplication";
	public static final String ABS = "abs";
	public static final String SUMCOMPLEX = "sumComplex";
	public static final String GETCOMPONENTS = "getComponents";
	public static final String GETRANDOM = "getRandom";
	public static final String PRINTCOMPLEX = "printComplex";
	public static final String PRINTTIME = "printTime";
	public static final String GETAGENTINFO = "getAgentInfo";
	public static final String CONVERTDATE = "convertDate";
}
