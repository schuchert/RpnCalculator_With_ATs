package com.rpn.factory;

import org.junit.Test;

import com.rpn.UnknownOperatorException;

public class OperatorFactoryTest {
    @Test(expected = UnknownOperatorException.class)
    public void throwsExceptionWhenOperatorNotFound() {
        new OperatorFactory().findOperatorNamed("lasdflkjaf)()(*");
    }

}
