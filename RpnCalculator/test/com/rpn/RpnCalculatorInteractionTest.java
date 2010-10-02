package com.rpn;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.rpn.factory.OperatorFactory;

public class RpnCalculatorInteractionTest {
    IOperator op = mock(IOperator.class);
    OperatorFactory factory = mock(OperatorFactory.class);
    private RpnCalculator calculator;

    @Before
    public void init() {
        when(factory.findOperatorNamed(anyString())).thenReturn(op);
        calculator = new RpnCalculator(factory);
        calculator.perform("+");
    }

    @Test
    public void looksUpNameProvided() {
        verify(factory, times(1)).findOperatorNamed("+");
    }

    @Test
    public void executedOperate() {
        verify(op, times(1)).execute(any(RpnStack.class));
    }
}
