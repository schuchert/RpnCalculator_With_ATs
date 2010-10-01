package com.rpn.operators.stateless;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.rpn.RpnStack;
import com.rpn.objectmothers.RpnStackObjectMother;
import com.rpn.operators.stateless.Divide;

public class DivideTest {
    Divide op = new Divide();

    @Test
    public void divideTwoNumbers() {
        RpnStack stack = RpnStackObjectMother.build(8, 2);
        stack.push(8);
        stack.push(2);
        op.execute(stack);
        assertEquals(4, stack.peek());
    }

    @Test(expected = ArithmeticException.class)
    public void divideBy0() {
        op.execute(RpnStackObjectMother.build());
    }

    @Test
    public void divideBy0_checkExceptionContents() {
        try {
            op.execute(RpnStackObjectMother.build());
            fail("Should have throw exception");
        } catch (ArithmeticException e) {
            assertEquals("Divide by 0", e.getMessage());
        }
    }
}
