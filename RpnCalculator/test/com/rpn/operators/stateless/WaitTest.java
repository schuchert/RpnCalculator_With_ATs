package com.rpn.operators.stateless;

import org.junit.Test;

import com.rpn.RpnStack;
import com.rpn.objectmothers.RpnStackObjectMother;

import static org.junit.Assert.*;

public class WaitTest {

    @Test
    public void delaysExecution() {
        RpnStack values = RpnStackObjectMother.build(5);
        
        Long startNano = System.nanoTime();
        new Wait().execute(values);
        Long stopNano = System.nanoTime();
        assertTrue(stopNano - startNano > 5000000);
    }
}
