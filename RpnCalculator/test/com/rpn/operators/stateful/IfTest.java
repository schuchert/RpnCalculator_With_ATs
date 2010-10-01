package com.rpn.operators.stateful;

import java.math.BigDecimal;

import org.junit.*;
import org.mockito.Mockito;

import com.rpn.*;

import static org.mockito.Matchers.anyObject;

import static org.mockito.Mockito.*;

public class IfTest {
    RpnStack stack;
    If op = new If();
    IOperator trueBlock;
    IOperator falseBlock;

    @Before
    public void init() {
        trueBlock = Mockito.mock(IOperator.class);
        falseBlock = Mockito.mock(IOperator.class);
    }

    @Test
    public void executesTrueBlockWhenTrue() {
        trueOnStack();
        op.setTrueBlock(trueBlock);
        op.setFalseBlock(falseBlock);
        op.execute(stack);
        verify(trueBlock, times(1)).execute(stack);
        verify(falseBlock, never()).execute(stack);
    }

    private void trueOnStack() {
        stack = mock(RpnStack.class);
        when(stack.pop()).thenReturn(BigDecimal.ONE);
    }

    @Test
    public void executesFalseBlockWhenFalse() {
        falseOnStack();
        op.setTrueBlock(trueBlock);
        op.setFalseBlock(falseBlock);
        op.execute(stack);
        verify(falseBlock, times(1)).execute(stack);
        verify(trueBlock, never()).execute(stack);
    }

    private void falseOnStack() {
        stack = mock(RpnStack.class);
        when(stack.pop()).thenReturn(BigDecimal.ZERO);
    }

    @Test
    public void doesNothingWhenTrueWithDefaultInitialization() {
        trueOnStack();
        op.execute(stack);
        verify(stack, times(1)).pop();
        verify(stack, never()).push((BigDecimal) anyObject());
    }

    @Test
    public void doesNothingWhenFalseWithDefaultInitialization() {
        falseOnStack();
        op.execute(stack);
        verify(stack, times(1)).pop();
        verify(stack, never()).push((BigDecimal) anyObject());
    }
    
    @Test(expected=CircularCompositionNotAllowed.class)
    public void cannotBuildCircularIfStatementsInTrueToTrueBlock() {
        If op1 = new If();
        op.setTrueBlock(op1);
        op1.setTrueBlock(op);
    }
    
    @Test(expected=CircularCompositionNotAllowed.class)
    public void cannotBuildCircularIfStatementsInFalseToFalseBlock() {
        If op1 = new If();
        op.setFalseBlock(op1);
        op1.setFalseBlock(op);
    }
    
    @Test(expected=CircularCompositionNotAllowed.class)
    public void cannotBuildCircularIfStatementsInFalseToTrueBlock() {
        If op1 = new If();
        op.setFalseBlock(op1);
        op1.setTrueBlock(op);
    }
    
    @Test(expected=CircularCompositionNotAllowed.class)
    public void cannotBuildCircularIfStatementsInTrueToFalseBlock() {
        If op1 = new If();
        op.setTrueBlock(op1);
        op1.setFalseBlock(op);
    }
    
}
