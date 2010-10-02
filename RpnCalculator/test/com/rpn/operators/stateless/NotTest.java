package com.rpn.operators.stateless;

import java.math.BigDecimal;

import org.junit.Test;

import com.rpn.*;
import com.rpn.objectmothers.RpnStackObjectMother;

import static org.junit.Assert.assertEquals;

public class NotTest {
    RpnStack stack;

    IOperator op = new Not();

    @Test
    public void not0is1() {
        stack = RpnStackObjectMother.build(0);
        op.execute(stack);
        assertEquals(BigDecimal.ONE, stack.peek());
    }

    @Test
    public void notAnythingBut0Is0() {
        stack = RpnStackObjectMother.build(18);
        op.execute(stack);
        assertEquals(BigDecimal.ZERO, stack.peek());
    }
}
