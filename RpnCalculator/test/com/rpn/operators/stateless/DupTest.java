package com.rpn.operators.stateless;

import org.junit.Before;
import org.junit.Test;

import com.rpn.RpnStack;
import com.rpn.objectmothers.RpnStackObjectMother;
import com.rpn.operators.stateless.Dup;

public class DupTest {
    RpnStack stack;
    
    @Before
    public void init() {
        stack = RpnStackObjectMother.build(4);
        new Dup().execute(stack);
    }
    
    @Test
    public void topElementCorrect() {
    }
    
    @Test
    public void sizeCorrect() {
    }
}
