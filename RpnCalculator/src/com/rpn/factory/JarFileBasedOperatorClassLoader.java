package com.rpn.factory;

import java.io.File;
import java.util.*;
import java.util.zip.*;

import com.rpn.IOperator;
import com.rpn.operators.stateless.Add;

public class JarFileBasedOperatorClassLoader extends OperatorLoaderBase {
    public JarFileBasedOperatorClassLoader() {
        File addClassfile = getAddClassFile();
        loadAllObjectsIn(addClassfile);
    }

    private Iterator<IOperator> loadAllObjectsIn(File file) {
        try {
            ZipFile jar = new ZipFile(file);
            for (Enumeration<? extends ZipEntry> entries = jar.entries(); entries.hasMoreElements();) {
                processEntry(entries);
            }
        } catch (Exception e) {
            throw new RuntimeException("Unable to load classes from jar file", e);
        }

        return operators.iterator();
    }

    private void processEntry(Enumeration<? extends ZipEntry> entries) throws InstantiationException,
            IllegalAccessException {
        ZipEntry current = entries.nextElement();
        if (isProbablyOperator(current)) {
            Class<?> loadedClass = loadClassNamed(classNameOf(current));
            if (isPublicAndConcrete(loadedClass))
                operators.add((IOperator) loadedClass.newInstance());
        }
    }

    private boolean isProbablyOperator(ZipEntry current) {
        String packageName = addPackageName();
        String name = current.getName();
        return name.indexOf(packageName) > -1 && name.endsWith(".class") && !name.endsWith("Test.class");
    }

    private String classNameOf(ZipEntry current) {
        String name = current.getName();
        name = name.replaceAll(".class", "");
        return name.replaceAll("/", ".");
    }

    private String addPackageName() {
        String name = Add.class.getName().replaceAll("[.]", "/");
        return name.substring(0, name.lastIndexOf("Add"));
    }

    private File getAddClassFile() {
        return new File(Add.class.getProtectionDomain().getCodeSource().getLocation().getPath());
    }
}
