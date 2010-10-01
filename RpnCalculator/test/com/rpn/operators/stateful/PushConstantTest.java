package com.rpn.operators.stateful;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.rpn.RpnStack;
import com.rpn.objectmothers.RpnStackObjectMother;

import static org.junit.Assert.assertEquals;

public class PushConstantTest {
    RpnStack stack = RpnStackObjectMother.build();

    @Before
    public void init() {
        new PushConstant(17).execute(stack);
    }

    @Test
    public void sizeCorrect() {
        assertEquals(1, stack.size());
    }

    @Test
    public void constantPushed() {
        assertEquals(new BigDecimal(17), stack.peek());
    }
}
