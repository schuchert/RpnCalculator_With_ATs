package com.rpn.operators.stateless;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.rpn.RpnStack;
import com.rpn.objectmothers.RpnStackObjectMother;

import static org.junit.Assert.assertEquals;

public class DropTest {
    RpnStack stack;
    
    @Before
    public void init() {
        stack = RpnStackObjectMother.build(3, 4, 5);
        new Drop().execute(stack);
    } 
    
    @Test
    public void topElementRemoved() {
        assertEquals(new BigDecimal(4), stack.peek());
    }
    
    @Test
    public void sizeCorrectAfter() {
        assertEquals(2, stack.size());
    }
}
