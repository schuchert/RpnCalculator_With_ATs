package com.rpn.operators.stateless;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.rpn.RpnStack;
import com.rpn.objectmothers.RpnStackObjectMother;
import com.rpn.operators.stateless.Drop;

public class DropTest {
    RpnStack stack;
    
    @Before
    public void init() {
        stack = RpnStackObjectMother.build(3, 4, 5);
        new Drop().execute(stack);
    } 
    
    @Test
    public void topElementRemoved() {
        assertEquals(4, stack.peek());
    }
    
    @Test
    public void sizeCorrectAfter() {
        assertEquals(2, stack.size());
    }
}
