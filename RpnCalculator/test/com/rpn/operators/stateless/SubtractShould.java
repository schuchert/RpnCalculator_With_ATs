package com.rpn.operators.stateless;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.rpn.RpnStack;
import com.rpn.objectmothers.RpnStackObjectMother;
import com.rpn.operators.stateless.Subtract;

public class SubtractShould {
    @Test
    public void subtractTheSecondNumberFromTheFirst() {
        RpnStack stack = RpnStackObjectMother.build(30, 4);
        new Subtract().execute(stack);
        assertEquals(26, (int) stack.peek());
    }
}
