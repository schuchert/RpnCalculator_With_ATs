package com.rpn.operators.stateless;

import static com.rpn.testutil.BigDecimalTestUtil.assertThatValuesMatch;

import org.junit.Test;

import com.rpn.RpnStack;
import com.rpn.objectmothers.RpnStackObjectMother;

public class FactorialShould {
    @Test
    public void calculateFacorialOf5() {
        RpnStack values = RpnStackObjectMother.build(5);
        values.push(5);
        Factorial op = new Factorial();
        op.execute(values);
        assertThatValuesMatch(120, values.peek());
    }
}
