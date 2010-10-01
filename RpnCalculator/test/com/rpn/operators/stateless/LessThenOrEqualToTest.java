package com.rpn.operators.stateless;

import java.math.BigDecimal;

import org.junit.Test;

import com.rpn.IOperator;
import com.rpn.RpnStack;
import com.rpn.objectmothers.RpnStackObjectMother;

import static org.junit.Assert.assertEquals;

public class LessThenOrEqualToTest {
    RpnStack stack;
    IOperator op = new LessThenOrEqualTo();
    
    @Test
    public void trueWhenEqual() {
        stack = RpnStackObjectMother.build(4, 4);
        op.execute(stack);
        assertEquals(BigDecimal.ONE, stack.peek());
    }
    
    @Test
    public void trueWhenLessThen() {
        stack = RpnStackObjectMother.build(2, 4);
        op.execute(stack);
        assertEquals(BigDecimal.ONE, stack.peek());
    }
    
    @Test
    public void falseWhenGreaterThen() {
        stack = RpnStackObjectMother.build(5, 4);
        op.execute(stack);
        assertEquals(BigDecimal.ZERO, stack.peek());
    }
}
