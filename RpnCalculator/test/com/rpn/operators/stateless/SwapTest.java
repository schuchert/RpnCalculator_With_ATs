package com.rpn.operators.stateless;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import com.rpn.RpnStack;
import com.rpn.objectmothers.RpnStackObjectMother;
import com.rpn.operators.stateless.Swap;

public class SwapTest {
    @Test
    public void swapsXandY() {
        RpnStack stack = RpnStackObjectMother.build(4,5);
        new Swap().execute(stack);
        assertEquals(new BigDecimal(4), stack.pop());
        assertEquals(new BigDecimal(5), stack.pop());
    }
}
