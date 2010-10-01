package com.rpn.operators.stateless;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.rpn.RpnStack;
import com.rpn.objectmothers.RpnStackObjectMother;
import com.rpn.operators.stateless.Factorial;

public class FactorialShould {
    @Test
    public void calculateFacorialOf5() {
        RpnStack values = RpnStackObjectMother.build(5);
        values.push(5);
        Factorial op = new Factorial();
        op.execute(values);
        assertEquals(120, (int) values.peek());
    }

}
