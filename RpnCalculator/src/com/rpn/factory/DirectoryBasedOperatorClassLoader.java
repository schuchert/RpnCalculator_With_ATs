package com.rpn.factory;

import java.io.*;
import java.util.*;

import com.rpn.IOperator;

public class DirectoryBasedOperatorClassLoader extends OperatorLoaderBase {
    public DirectoryBasedOperatorClassLoader() {
        this(new FindBinDir().name);
    }

    public DirectoryBasedOperatorClassLoader(String directory) {
        fillFrom(directory);
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

    private Class<?> getClass(String directory, String current) {
        String name = buildClassName(directory, current);
        return loadClassNamed(name);
    }

    private String buildClassName(String directory, String current) {
        String name = new java.io.File(directory, current).getPath();
        name = name.substring(0, name.indexOf(".class"));
        name = name.substring(name.lastIndexOf("com"));
        name = name.replaceAll("/", ".");
        return name;
    }

    @Override
    public Iterator<IOperator> iterator() {
        return operators.iterator();
    }
}