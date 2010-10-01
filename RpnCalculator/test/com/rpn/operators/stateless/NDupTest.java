package com.rpn.operators.stateless;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.rpn.RpnStack;
import com.rpn.objectmothers.RpnStackObjectMother;
import com.rpn.operators.stateless.NDup;

public class NDupTest {
    RpnStack stack;

    @Before
    public void setup() {
        stack = RpnStackObjectMother.build(4, 6, 7, 2);
        new NDup().execute(stack);
    }

    @Test
    public void duplicateTopEntriesCorrectly() {
        assertEquals(7, stack.pop());
        assertEquals(6, stack.pop());
        assertEquals(7, stack.pop());
        assertEquals(6, stack.pop());
        assertEquals(4, stack.pop());
    }

    @Test
    public void leaveSizeCorrect() {
        assertEquals(5, stack.size());
    }
}
