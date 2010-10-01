package com.rpn.operators.stateless;

import java.math.BigDecimal;

import org.junit.Test;

import com.rpn.IOperator;
import com.rpn.RpnStack;
import com.rpn.objectmothers.RpnStackObjectMother;

import static org.junit.Assert.assertEquals;

public class GreaterThenOrEqualToTest {
    RpnStack stack;
    IOperator op = new GreaterThenOrEqualTo();

    @Test
    public void trueWhenEqual() {
        stack = RpnStackObjectMother.build(4, 4);
        op.execute(stack);
        assertEquals(BigDecimal.ONE, stack.peek());
    }

    @Test
    public void trueWhenGreaterThen() {
        stack = RpnStackObjectMother.build(4, 3);
        op.execute(stack);
        assertEquals(BigDecimal.ONE, stack.peek());
    }

    @Test
    public void falseWhenLessThen() {
        stack = RpnStackObjectMother.build(4, 5);
        op.execute(stack);
        assertEquals(BigDecimal.ZERO, stack.peek());
    }
}
