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

import java.util.Iterator;

import jade.content.AgentAction;
import jade.util.leap.List;

public class Multiplication implements AgentAction {

	private static final long serialVersionUID = -4207470777127999599L;
	
	private List numbers;

	public List getNumbers() {
		return numbers;
	}

	public void setNumbers(List numbers) {
		this.numbers = numbers;
	}

	@Override
	public String toString() {
		String s = "Multiplication (";
		
		@SuppressWarnings("rawtypes")
		Iterator it = numbers.iterator();
		boolean first = true;
		while(it.hasNext()) {
			if (!first) {
				s += ",";
			}
			first = false;
			s += it.next();
		}
		s += ")";
		return s;
	}

}
