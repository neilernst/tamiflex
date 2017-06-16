/*******************************************************************************
 * Copyright (c) 2010 Eric Bodden.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Eric Bodden - initial API and implementation
 ******************************************************************************/
package de.bodden.tamiflex.playout.rt;

import de.bodden.tamiflex.playout.transformation.Guice.GuiceBindMethodTransformation;

public enum Kind {

	ClassForName("Class.forName"),
	ClassNewInstance("Class.newInstance"),
	ClassGetDeclaredField("Class.getDeclaredField"),
	ClassGetDeclaredFields("Class.getDeclaredFields"),
	ClassGetDeclaredMethod("Class.getDeclaredMethod"),
	ClassGetDeclaredMethods("Class.getDeclaredMethods"),
	ClassGetField("Class.getField"),
	ClassGetFields("Class.getFields"),
	ClassGetMethod("Class.getMethod"),
	ClassGetMethods("Class.getMethods"),
	ClassGetModifiers("Class.getModifiers"),
	ConstructorNewInstance("Constructor.newInstance"),
	ConstructorGetModifiers("Constructor.getModifiers"),
	ConstructorToGenericString("Constructor.toGenericString"),
	ConstructorToString("Constructor.toString"),
	MethodInvoke("Method.invoke"),
	MethodGetName("Method.getName"),
	MethodGetDeclaringClass("Method.getDeclaringClass"),
	MethodGetModifiers("Method.getModifiers"),
	MethodToGenericString("Method.toGenericString"),
	MethodToString("Method.toString"),
	ArrayNewInstance("Array.newInstance"),
	FieldSet("Field.set*"),
	FieldGet("Field.get*"),
	FieldGetName("Field.getName"),
	FieldGetDeclaringClass("Field.getDeclaringClass"),
	FieldGetModifiers("Field.getModifiers"),
	FieldToGenericString("Field.toGenericString"),
	FieldToString("Field.toString"),
	GuiceBindMethod("Guice.bind");

	private final String output;

	Kind(String output) {
		this.output = output;		
	}

	public String label() {
		return output;
	}
	
	public static Kind kindForLabel(String label) {
		for(Kind k: Kind.values()) {
			if(k.label().equals(label)) {
				return k;
			}
		}
		throw new RuntimeException("unknown kind: "+label);
	}
	
	@Override
	public String toString() {
		return label();
	}
}
