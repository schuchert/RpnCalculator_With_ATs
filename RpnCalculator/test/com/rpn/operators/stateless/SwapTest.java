package com.rpn.operators.stateless;

import static org.junit.Assert.*;

import org.junit.Test;

import com.rpn.RpnStack;
import com.rpn.objectmothers.RpnStackObjectMother;
import com.rpn.operators.stateless.Swap;

public class SwapTest {
    @Test
    public void swapsXandY() {
        RpnStack stack = RpnStackObjectMother.build(4,5);
        new Swap().execute(stack);
        assertEquals(4, stack.pop());
        assertEquals(5, stack.pop());
    }
}
