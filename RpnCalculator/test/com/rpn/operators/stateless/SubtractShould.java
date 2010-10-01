package com.rpn.operators.stateless;

import org.junit.Test;

import com.rpn.RpnStack;
import com.rpn.objectmothers.RpnStackObjectMother;

import static com.rpn.testutil.BigDecimalTestUtil.assertThatValuesMatch;

public class SubtractShould {
    @Test
    public void subtractTheSecondNumberFromTheFirst() {
        RpnStack stack = RpnStackObjectMother.build(30, 4);
        new Subtract().execute(stack);
        assertThatValuesMatch(26, stack.peek());
    }
}
