package com.rpn.operators.stateless;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.rpn.RpnStack;
import com.rpn.objectmothers.RpnStackObjectMother;
import static com.rpn.testutil.BigDecimalTestUtil.*;

import static org.junit.Assert.assertEquals;

public class NDupTest {
    RpnStack stack;
    NDup dup = new NDup();
    
    @Before
    public void setup() {
        stack = RpnStackObjectMother.build(4, 6, 7);
    }

    @Test
    public void duplicateTopEntriesCorrectly() {
        stack.push(2);
        dup.execute(stack);
        assertThatValuesMatch(7, stack.pop());
        assertThatValuesMatch(6, stack.pop());
        assertThatValuesMatch(7, stack.pop());
        assertThatValuesMatch(6, stack.pop());
        assertThatValuesMatch(4, stack.pop());
    }

    @Test
    public void leaveSizeCorrect() {
        stack.push(2);
        dup.execute(stack);
        assertEquals(5, stack.size());
    }
    
    @Test(expected=ArithmeticException.class)
    public void numberToDuplicateMustBeGreaterThan0() {
        stack.push(BigDecimal.ZERO);
        dup.execute(stack);
    }
    
    @Test(expected=ArithmeticException.class)
    public void numberToDuplicateMustBeInteger() {
        stack.push(new BigDecimal("1.1"));
        dup.execute(stack);
    }
}
