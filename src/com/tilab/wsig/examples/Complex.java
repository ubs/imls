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

import jade.content.Concept;

public class Complex implements Concept {
	
	private static final long serialVersionUID = -7704296544754219210L;
	
	private float real;
	private float immaginary;

	public Complex() {
		real = 0;
		immaginary = 0;
	}

	public float getReal() {
		return real;
	}
	public void setReal(float real) {
		this.real = real;
	}
	public float getImmaginary() {
		return immaginary;
	}
	public void setImmaginary(float immaginary) {
		this.immaginary = immaginary;
	}
	
	@Override
	public String toString() {
		return real+"+j"+immaginary;
	}
}
