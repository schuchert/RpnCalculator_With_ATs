package com.rpn.operators.stateless;

import static com.rpn.testutil.BigDecimalTestUtil.assertThatValuesMatch;

import org.junit.Test;

import com.rpn.RpnStack;
import com.rpn.objectmothers.RpnStackObjectMother;

public class AddShould {
    @Test
    public void subtractTheSecondNumberFromTheFirst() {
        RpnStack stack = RpnStackObjectMother.build(30, 4);
        new Add().execute(stack);
        assertThatValuesMatch(34, stack.peek());
    }
}
