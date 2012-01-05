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

import jade.content.AgentAction;

public class Diff implements AgentAction {

	private static final long serialVersionUID = -7019179095600689052L;

	private float firstElement;

	private float secondElement;

	public float getFirstElement() {
		return firstElement;
	}

	public void setFirstElement(float firstElement) {
		this.firstElement = firstElement;
	}

	public float getSecondElement() {
		return secondElement;
	}

	public void setSecondElement(float secondElement) {
		this.secondElement = secondElement;
	}

	@Override
	public String toString() {
		return "Diff (" + firstElement + "," + secondElement + ")";
	}
}
