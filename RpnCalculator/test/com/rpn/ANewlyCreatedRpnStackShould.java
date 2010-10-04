package com.rpn;

import java.math.BigDecimal;

import org.junit.Test;

import static org.junit.Assert.*;

public class ANewlyCreatedRpnStackShould {
    RpnStack stack = new RpnStack();
    
    @Test
    public void haveAPeekValue() {
        assertEquals(BigDecimal.ZERO, stack.peek());
    }

    @Test
    public void haveAPopValue() {
        assertEquals(BigDecimal.ZERO, stack.peek());
    }
}
