package com.rpn.operators.stateful;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.rpn.RpnStack;
import com.rpn.objectmothers.RpnStackObjectMother;

public class PushConstantTest {
    RpnStack stack = RpnStackObjectMother.build();
    
    @Before
    public void init() {
        new PushConstant(17).execute(stack);
    }
    
    @Test
    public void sizeCorrect() {
        assertEquals(1, stack.size());
    }
    
    @Test
    public void constantPushed() {
        assertEquals(17, stack.peek());
    }
}
