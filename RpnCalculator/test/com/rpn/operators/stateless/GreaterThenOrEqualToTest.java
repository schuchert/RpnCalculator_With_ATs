package com.rpn.operators.stateless;

import static org.junit.Assert.*;

import org.junit.Test;

import com.rpn.IOperator;
import com.rpn.RpnStack;
import com.rpn.objectmothers.RpnStackObjectMother;

public class GreaterThenOrEqualToTest {
    RpnStack stack = RpnStackObjectMother.build();
    IOperator op = new GreaterThenOrEqualTo();
    
    @Test
    public void trueWhenEqual() {
        stack.push(4);
        stack.push(4);
        op.execute(stack);
        assertEquals(1, stack.peek());
    }
    
    @Test
    public void trueWhenGreaterThen() {
        stack.push(4);
        stack.push(3);
        op.execute(stack);
        assertEquals(1, stack.peek());
    }
    
    @Test
    public void falseWhenLessThen() {
        stack.push(4);
        stack.push(5);
        op.execute(stack);
        assertEquals(0, stack.peek());
    }
}
