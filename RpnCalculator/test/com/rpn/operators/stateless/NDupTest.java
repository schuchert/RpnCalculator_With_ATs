package com.rpn.operators.stateless;

import org.junit.Before;
import org.junit.Test;

import com.rpn.RpnStack;
import com.rpn.objectmothers.RpnStackObjectMother;
import static com.rpn.testutil.BigDecimalTestUtil.*;

import static org.junit.Assert.assertEquals;

public class NDupTest {
    RpnStack stack;

    @Before
    public void setup() {
        stack = RpnStackObjectMother.build(4, 6, 7, 2);
        new NDup().execute(stack);
    }

    @Test
    public void duplicateTopEntriesCorrectly() {
        assertThatValuesMatch(7, stack.pop());
        assertThatValuesMatch(6, stack.pop());
        assertThatValuesMatch(7, stack.pop());
        assertThatValuesMatch(6, stack.pop());
        assertThatValuesMatch(4, stack.pop());
    }

    @Test
    public void leaveSizeCorrect() {
        assertEquals(5, stack.size());
    }
}
