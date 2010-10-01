package com.rpn.operators.stateless;

import static org.junit.Assert.*;

import org.junit.Test;

import com.rpn.RpnStack;
import com.rpn.objectmothers.RpnStackObjectMother;
import com.rpn.operators.stateless.Multiply;

public class MultiplyTest {
    @Test
    public void twoNumbers() {
        RpnStack stack = RpnStackObjectMother.build(4, 9);
        new Multiply().execute(stack);
        assertEquals(36, (int) stack.peek());
    }
}
