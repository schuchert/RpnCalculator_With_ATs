package com.rpn.operators.stateless;

import static com.rpn.testutil.BigDecimalTestUtil.assertThatValuesMatch;

import org.junit.Test;

import com.rpn.RpnStack;
import com.rpn.objectmothers.RpnStackObjectMother;

public class FactorialTest {
    @Test
    public void of5() {
        RpnStack values = RpnStackObjectMother.build(5);
        values.push(5);
        UnaryOperator op = new Factorial();
        op.execute(values);
        assertThatValuesMatch(120, values.peek());
    }
}
