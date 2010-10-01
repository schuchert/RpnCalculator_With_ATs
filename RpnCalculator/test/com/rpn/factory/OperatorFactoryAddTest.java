package com.rpn.factory;

import org.junit.Before;
import org.junit.Test;

import com.rpn.OperatorNameAlreadyInUse;
import com.rpn.factory.OperatorFactory;
import com.rpn.operators.stateless.Add;

public class OperatorFactoryAddTest {
    OperatorFactory factory;

    @Before
    public void init() {
        factory = new OperatorFactory() {
            @Override
            protected void addFoundOperators() {
            }
        };
    }

    @Test(expected = OperatorNameAlreadyInUse.class)
    public void cannotReuseRegisteredName() {
        factory.add("not_used", new Add());
        factory.add("not_used", new Add());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addedOperatorNameCannotBeNull() {
        factory.add(null, new Add());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addedOperatorNameCannotBeBlank() {
        factory.add("", new Add());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addedOperatorCanotBeNull() {
        factory.add("________", null);
    }
}
