package com.rpn.operators.stateless;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.rpn.RpnStack;

public class NopTest {
    Nop op = new Nop();
    RpnStack stack;
    
    @Before
    public void init() {
        stack = Mockito.mock(RpnStack.class);
        op.execute(stack);
    }
    
    @Test
    public void itShouldNeverPopFromTheStack() {
        Mockito.verify(stack, times(0)).pop();
    }
    
    @Test
    public void itShouldNeverPushToTheStack() {
        Mockito.verify(stack, never()).push(anyInt());
    }
}
