package com.rpn.operators.stateless;

import java.math.BigDecimal;

import org.junit.Test;

import com.rpn.RpnStack;
import com.rpn.objectmothers.RpnStackObjectMother;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class DivideTest {
    Divide op = new Divide();

    @Test
    public void divideTwoNumbers() {
        RpnStack stack = RpnStackObjectMother.build(8, 2);
        op.execute(stack);
        assertEquals(new BigDecimal(4), stack.peek());
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
