package com.rpn.operators.stateful;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

import com.rpn.IOperator;
import com.rpn.RpnStack;

public class CompositeOperatorTest {
    RpnStack stack = new RpnStack();
    Macro op = new Macro();
    private IOperator op1;
    private IOperator op2;
    private IOperator op3;
    
    @Before
    public void init() {
        op1 = Mockito.mock(IOperator.class);
        op2 = Mockito.mock(IOperator.class);
        op3 = Mockito.mock(IOperator.class);
        
        op.append(op1);
        op.append(op2);
        op.append(op3);
        
        op.execute(stack);
    }
    
    @Test
    public void allContainedOperatorsCalled() {
        verify(op1, times(1)).execute(stack);
        verify(op2, times(1)).execute(stack);
        verify(op3, times(1)).execute(stack);
    }
}
