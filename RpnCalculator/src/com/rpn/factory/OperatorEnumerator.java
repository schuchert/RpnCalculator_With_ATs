package com.rpn.factory;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Modifier;
import java.util.Iterator;
import java.util.Vector;

import com.rpn.IOperator;
import com.rpn.operators.stateful.OperatorIteratorAdapter;

public class OperatorEnumerator extends OperatorIteratorAdapter implements Iterable<IOperator> {
    Vector<IOperator> operators = new Vector<IOperator>();
    Iterator<IOperator> iterator;

    public OperatorEnumerator() {
        this(new FindBinDir().name);
    }

    public OperatorEnumerator(String directory) {
        fillFrom(directory);
        iterator = operators.iterator();
    }

    private void fillFrom(String directory) {
        String[] candidates = getCandidateClassFiles(directory);

        for (String current : candidates) {
            Class<?> clazz = getClass(directory, current);
            addIfConcrete(clazz);
        }
    }

    private void addIfConcrete(Class<?> clazz) {
        if (isPublicAndConcrete(clazz))
            try {
                IOperator op = (IOperator) clazz.newInstance();
                operators.add(op);
            } catch (Exception e) {
                throw new RuntimeException(String.format("Unable to instantiate: %s", clazz), e);
            }
    }

    private String[] getCandidateClassFiles(String directory) {
        String[] candidates = new File(directory).list(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".class");
            }
        });
        return candidates;
    }

     boolean isPublicAndConcrete(Class<?> clazz) {
        int modifiers = clazz.getModifiers();

        return IOperator.class.isAssignableFrom(clazz) && !Modifier.isAbstract(modifiers)
                && !Modifier.isInterface(modifiers) && Modifier.isPublic(modifiers);
    }

    private Class<?> getClass(String directory, String current) {
        String name = buildClassName(directory, current);
        try {
            return getClassloader().loadClass(name);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(String.format("Unable to load from %s, class: %s", directory, current), e);
        }
    }

    protected ClassLoader getClassloader() {
        return ClassLoader.getSystemClassLoader();
    }

    private String buildClassName(String directory, String current) {
        String name = new java.io.File(directory, current).getPath();
        name = name.substring(0, name.indexOf(".class"));
        name = name.substring(name.lastIndexOf("com"));
        name = name.replaceAll("/", ".");
        return name;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public IOperator next() {
        return iterator.next();
    }

    @Override
    public Iterator<IOperator> iterator() {
        return this;
    }
}