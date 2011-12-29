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

import com.tilab.wsig.store.OperationName;
import com.tilab.wsig.store.SuppressOperation;

public class MathOntologyMapper {
	
	public Abs toAbs(float real,float immaginary){
		Abs abs = new Abs();
		Complex complex = new Complex();
		complex.setImmaginary(immaginary);
		complex.setReal(real);
		abs.setComplex(complex);
		return abs;
	}
	
	public Abs toAbs(Complex complex){
		Abs abs = new Abs();
		abs.setComplex(complex);
		return abs;
	}
	
	public Abs toAbs(String real,String immaginary){
		Abs abs = new Abs();
		Complex complex = new Complex();
		complex.setImmaginary(Float.parseFloat(immaginary));
		complex.setReal(Float.parseFloat(real));
		abs.setComplex(complex);
		return abs;
	}

	// DO not expose a web service operation corresponding to the Diff ontology action
	@SuppressOperation
	public Abs toDiff(){
		return null;
	}

	// The web service operation corresponding to the sum ontology action will be called add
	@OperationName(name="add")
	public Sum toSum(float firstElement, float secondElement){
		Sum sum = new Sum();
		sum.setFirstElement(firstElement);
		sum.setSecondElement(secondElement);
		return sum;
	}

	public PrintTime toPrintTime(){
		return new PrintTime();
	}
}
