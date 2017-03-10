/*******************************************************************************
 * Copyright (c) 2010 Eric Bodden.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Eric Bodden - initial implementation
 ******************************************************************************/
package de.bodden.tamiflex.playout.transformation.method;

import static de.bodden.tamiflex.playout.rt.Kind.MethodInvoke;
import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.GETSTATIC;
import static org.objectweb.asm.Opcodes.INVOKESTATIC;
import static org.objectweb.asm.Opcodes.IRETURN;
import static org.objectweb.asm.Opcodes.RETURN;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.Method;

import de.bodden.tamiflex.playout.rt.Kind;

public class MethodInvokeTransformation extends AbstractMethodTransformation {
	
	public MethodInvokeTransformation() {
		super(new Method("invoke", "(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;"));
	}

	@Override
	protected Kind methodKind() {
		return MethodInvoke;
	}
	
	@Override
	protected MethodVisitor getMethodVisitor(MethodVisitor parent) {
		return new MethodVisitor(Opcodes.ASM5,parent) {
			
			@Override
			public void visitInsn(int opcode) {
				if (IRETURN <= opcode && opcode <= RETURN) {
					mv.visitVarInsn(ALOAD, 1); // Load designated receiver
					mv.visitVarInsn(ALOAD, 0); // Load Method instance
					mv.visitFieldInsn(GETSTATIC, "de/bodden/tamiflex/playout/rt/Kind", methodKind().name(), Type.getDescriptor(Kind.class));
					mv.visitMethodInsn(
						INVOKESTATIC,
						"de/bodden/tamiflex/playout/rt/ReflLogger",
						"methodMethodInvoke",
						"(Ljava/lang/Object;Ljava/lang/reflect/Method;Lde/bodden/tamiflex/playout/rt/Kind;)V"
					);
				}
				super.visitInsn(opcode);
			}
		};
	}
	
}
