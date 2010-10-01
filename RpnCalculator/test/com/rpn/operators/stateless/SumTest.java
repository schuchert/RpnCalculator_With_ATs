package com.rpn.operators.stateless;

import org.junit.Before;
import org.junit.Test;

import com.rpn.RpnStack;
import com.rpn.objectmothers.RpnStackObjectMother;
import com.rpn.operators.stateless.Sum;

public class SumTest {
    RpnStack stack;

    @Before
    public void init() {
        stack = RpnStackObjectMother.build(3, 5, 7);
    }

    @Test
    public void sumsAllValues() {
        new Sum().execute(stack);
    }
}
