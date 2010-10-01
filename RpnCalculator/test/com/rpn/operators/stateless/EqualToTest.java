package com.rpn.operators.stateless;

import static org.junit.Assert.*;

import org.junit.Test;

import com.rpn.IOperator;
import com.rpn.RpnStack;
import com.rpn.objectmothers.RpnStackObjectMother;

public class EqualToTest {
    RpnStack stack;
    IOperator op = new EqualTo();
    
    @Test
    public void trueWhenEqual() {
         stack = RpnStackObjectMother.build(3, 3);
         op.execute(stack);
         assertEquals(1, stack.peek());
    }
    
    @Test
    public void falseWhenLess() {
         stack = RpnStackObjectMother.build(2, 3);
         op.execute(stack);
         assertEquals(0, stack.peek());
    }
    
    @Test
    public void falseWhenGreater() {
         stack = RpnStackObjectMother.build(4, 3);
         op.execute(stack);
         assertEquals(0, stack.peek());
    }
}
