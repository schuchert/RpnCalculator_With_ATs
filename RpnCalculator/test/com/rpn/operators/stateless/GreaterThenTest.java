package com.rpn.operators.stateless;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import com.rpn.RpnStack;
import com.rpn.objectmothers.RpnStackObjectMother;

public class GreaterThenTest {
    RpnStack stack;
    GreaterThen op = new GreaterThen();
    
    @Test
    public void whenTrue() {
        stack = RpnStackObjectMother.build(5, 4);
        op.execute(stack);
        assertEquals(BigDecimal.ONE, stack.peek());
    }
    
    @Test
    public void whenFalse() {
        stack = RpnStackObjectMother.build(4, 5);
        op.execute(stack);
        assertEquals(BigDecimal.ZERO, stack.peek());
    }
}
