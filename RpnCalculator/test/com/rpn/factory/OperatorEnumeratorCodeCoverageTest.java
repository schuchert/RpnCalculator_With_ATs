package com.rpn.factory;

import org.junit.Test;

import static org.mockito.Matchers.*;

import static org.mockito.Mockito.*;

public class OperatorEnumeratorCodeCoverageTest {
    @Test(expected = RuntimeException.class)
    public void exerciseCatchBlock() {
        new OperatorEnumerator() {
            protected boolean isPublicAndConcrete(Class<?> clazz) {
                return true;
            }
        };
    }

    @Test(expected = RuntimeException.class)
    public void exerciseClassNotFoundException() throws Exception {
        final ClassLoader saboteur = mock(ClassLoader.class);
        when(saboteur.loadClass(anyString())).thenThrow(new ClassNotFoundException());
        new OperatorEnumerator() {
            protected ClassLoader getClassloader() {
                return saboteur;
            }
        };
    }
}
