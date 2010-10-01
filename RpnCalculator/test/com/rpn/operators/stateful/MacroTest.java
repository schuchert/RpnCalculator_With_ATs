package com.rpn.operators.stateful;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.rpn.IOperator;
import com.rpn.RpnStack;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.*;

public class MacroTest {
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

    @Test(expected = CircularCompositionNotAllowed.class)
    public void cannotAddMacroToItself() {
        op.append(op);
    }

    @Test(expected = CircularCompositionNotAllowed.class)
    public void cannotAddSecondMacroToFirstMacro() {
        Macro op2 = new Macro();
        op.append(op2);
        op2.append(op);
    }

    private void shouldNotBeAllowed(Macro target, CompositeOperator appendee) {
        try {
            target.append(appendee);
            fail("Should have thrown exceptoin");
        } catch (CircularCompositionNotAllowed e) {
            assertTrue(true);
        }
    }

    @Test
    public void cannotAddWhenCricularReferenceNestedAFewLevels() {
        Macro m1 = new Macro();
        Macro m2 = new Macro();
        Macro m3 = new Macro();
        op.append(m1);
        m1.append(m2);
        m2.append(m3);
        shouldNotBeAllowed(m3, op);
        shouldNotBeAllowed(m3, m1);
        shouldNotBeAllowed(m3, m2);
        shouldNotBeAllowed(m3, m3);
        shouldNotBeAllowed(m2, op);
        shouldNotBeAllowed(m2, m1);
        shouldNotBeAllowed(m1, op);
    }
}
