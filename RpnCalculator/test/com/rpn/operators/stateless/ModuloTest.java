package com.rpn.operators.stateless;

import java.math.BigDecimal;

import org.junit.Test;

import com.rpn.RpnStack;
import com.rpn.objectmothers.RpnStackObjectMother;

import static org.junit.Assert.*;

public class ModuloTest {
    @Test
    public void basicModWorks() {
        BinaryOperator op = new Modulo();
        RpnStack stack = RpnStackObjectMother.build(4, 3);
        op.execute(stack);
        assertEquals(BigDecimal.ONE, stack.peek());
    }
}
