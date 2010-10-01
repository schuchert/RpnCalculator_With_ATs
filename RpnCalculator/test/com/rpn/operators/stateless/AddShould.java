package com.rpn.operators.stateless;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.rpn.RpnStack;
import com.rpn.objectmothers.RpnStackObjectMother;
import com.rpn.operators.stateless.Add;

public class AddShould {
    @Test
    public void subtractTheSecondNumberFromTheFirst() {
        RpnStack stack = RpnStackObjectMother.build(30, 4);
        new Add().execute(stack);
        assertEquals(34, (int) stack.peek());
    }
}
