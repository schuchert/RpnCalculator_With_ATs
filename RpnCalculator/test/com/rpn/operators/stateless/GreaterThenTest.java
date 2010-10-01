package com.rpn.operators.stateless;

import static org.junit.Assert.*;

import org.junit.Test;

import com.rpn.RpnStack;
import com.rpn.objectmothers.RpnStackObjectMother;

public class GreaterThenTest {
    RpnStack stack = RpnStackObjectMother.build();
    GreaterThen op = new GreaterThen();
    
    @Test
    public void whenTrue() {
        stack.push(5);
        stack.push(4);
        op.execute(stack);
        assertEquals(1, stack.peek());
    }
    
    @Test
    public void whenFalse() {
        stack.push(4);
        stack.push(5);
        op.execute(stack);
        assertEquals(0, stack.peek());
    }
}
