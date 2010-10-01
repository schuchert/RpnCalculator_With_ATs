package com.rpn.operators.stateless;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.rpn.IOperator;
import com.rpn.RpnStack;
import com.rpn.objectmothers.RpnStackObjectMother;

public class LessThenOrEqualToTest {
    RpnStack stack;
    IOperator op = new LessThenOrEqualTo();
    
    @Test
    public void trueWhenEqual() {
        stack = RpnStackObjectMother.build(4, 4);
        op.execute(stack);
        assertEquals(1, stack.peek());
    }
    
    @Test
    public void trueWhenLessThen() {
        stack = RpnStackObjectMother.build(2, 4);
        op.execute(stack);
        assertEquals(1, stack.peek());
    }
    
    @Test
    public void falseWhenGreaterThen() {
        stack = RpnStackObjectMother.build(5, 4);
        op.execute(stack);
        assertEquals(0, stack.peek());
    }
}
