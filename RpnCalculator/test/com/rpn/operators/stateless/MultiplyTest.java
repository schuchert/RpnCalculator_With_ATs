package com.rpn.operators.stateless;

import org.junit.Test;

import com.rpn.RpnStack;
import com.rpn.objectmothers.RpnStackObjectMother;

import static com.rpn.testutil.BigDecimalTestUtil.assertThatValuesMatch;

public class MultiplyTest {
    @Test
    public void twoNumbers() {
        RpnStack stack = RpnStackObjectMother.build(4, 9);
        new Multiply().execute(stack);
        assertThatValuesMatch(36, stack.peek());
    }
}
