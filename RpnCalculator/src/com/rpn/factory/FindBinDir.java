package com.rpn.factory;

import com.rpn.operators.stateless.Add;

public class FindBinDir {
    public final String name;

    public FindBinDir() {
        name = findDirectory();
    }

    private String findDirectory() {
        String fullName = Add.class.getClassLoader().getResource(addLocation()).getFile();
        return fullName.substring(0, fullName.length() - 9);
    }

    private String addLocation() {
        String name = Add.class.getName().replaceAll("[.]", "/") + ".class";
        return name;
    }
}
