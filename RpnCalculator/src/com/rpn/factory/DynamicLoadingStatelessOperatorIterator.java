package com.rpn.factory;

import java.io.File;
import java.util.Iterator;

import com.rpn.IOperator;
import com.rpn.operators.stateless.Add;

public class DynamicLoadingStatelessOperatorIterator implements Iterable<IOperator> {
    OperatorLoaderBase loader;

    public DynamicLoadingStatelessOperatorIterator() {
        File addClassfile = getAddClassFile();

        if (runningFromJar(addClassfile.getName())) {
            loader = new JarFileBasedOperatorClassLoader();
        } else {
            loader = new DirectoryBasedOperatorClassLoader();
        }
    }

    private File getAddClassFile() {
        return new File(Add.class.getProtectionDomain().getCodeSource().getLocation().getPath());
    }

    private boolean runningFromJar(String fullName) {
        return fullName.endsWith(".jar");
    }

    @Override
    public Iterator<IOperator> iterator() {
        return loader.iterator();
    }
}
