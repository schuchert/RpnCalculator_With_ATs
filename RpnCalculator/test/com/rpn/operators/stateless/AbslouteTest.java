package com.rpn.operators.stateless;

import java.math.BigDecimal;

import org.junit.Test;

import com.rpn.RpnStack;
import com.rpn.objectmothers.RpnStackObjectMother;

import static org.junit.Assert.*;

public class AbslouteTest {
    @Test
    public void removedNegativeSign() {
        RpnStack values = RpnStackObjectMother.build(-4);
        new Absloute().execute(values);
        assertEquals(new BigDecimal(4), values.peek());
    }
}
