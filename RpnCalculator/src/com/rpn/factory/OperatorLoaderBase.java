package com.rpn.factory;

import java.lang.reflect.Modifier;
import java.util.*;

import com.rpn.IOperator;
import com.rpn.operators.stateful.OperatorIteratorAdapter;

public class OperatorLoaderBase extends OperatorIteratorAdapter implements Iterable<IOperator> {
    Vector<IOperator> operators = new Vector<IOperator>();

    boolean isPublicAndConcrete(Class<?> clazz) {
        int modifiers = clazz.getModifiers();

        return IOperator.class.isAssignableFrom(clazz) && !Modifier.isAbstract(modifiers)
                && !Modifier.isInterface(modifiers) && Modifier.isPublic(modifiers);
    }

    protected Class<?> loadClassNamed(String name) {
        try {
            return getClassloader().loadClass(name);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(String.format("Unable to load class named: ", name), e);
        }
    }

    protected ClassLoader getClassloader() {
        return ClassLoader.getSystemClassLoader();
    }

    @Override
    public Iterator<IOperator> iterator() {
        return operators.iterator();
    }

}
