package de.bodden.tamiflex.playout.transformation.Guice;

/**
 * Created by nernst on 5/19/17.
 * A monitor for the Google Guice DI Bind method call.
 */

import de.bodden.tamiflex.playout.rt.Kind;
import static de.bodden.tamiflex.playout.rt.Kind.GuiceBindMethod;
import static org.objectweb.asm.Opcodes.*;

import de.bodden.tamiflex.playout.transformation.AbstractTransformation;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.Method;

import com.google.inject.binder.AnnotatedBindingBuilder;

public class GuiceBindMethodTransformation extends AbstractTransformation {

    public GuiceBindMethodTransformation() {
            super(com.google.inject.binder.AnnotatedBindingBuilder.class, new Method("bind", "(Lcom/google/inject/Key;)Lcom/google/inject/binder/LinkedBindingBuilder;"));
    }  // this is where the proper method 'name' is used.
     //com/google/inject/binder/AnnotatedBindingBuilder.toInstance

    @Override
    protected MethodVisitor getMethodVisitor(MethodVisitor parent) {
        return new MethodVisitor(Opcodes.ASM5,parent) {

            @Override
            public void visitInsn(int opcode) {

                if (IRETURN <= opcode && opcode <= RETURN) {
                    System.out.println("in Guice!");
                    mv.visitInsn(ACONST_NULL); //ignore first argument to method methodMethodInvoke
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

//    @Override
    protected Kind methodKind() {
        return GuiceBindMethod;
	}
}
