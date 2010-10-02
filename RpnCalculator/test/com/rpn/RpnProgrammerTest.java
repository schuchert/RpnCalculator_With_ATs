package com.rpn;

import java.math.BigDecimal;

import org.junit.Test;

import com.rpn.factory.OperatorFactory;
import com.rpn.objectmothers.RpnStackObjectMother;
import com.rpn.operators.stateful.Macro;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

public class RpnProgrammerTest {
    OperatorFactory factory = new OperatorFactory();
    RpnProgrammer programmer = new RpnProgrammer(factory);
    Macro m;
    RpnStack stack;

    void assertNameOfIs(Macro m, String name) {
        assertEquals(name, m.name);
    }

    @Test
    public void createsEmptyNamedProgram() {
        m = programmer.compile("x");
        assertNameOfIs(m, "x");
        assertTrue(m.stepCountIs(0));
    }

    @Test
    public void ignoresLeadingSpaces() {
        m = programmer.compile("   x");
        assertNameOfIs(m, "x");
        assertTrue(m.stepCountIs(0));
    }

    @Test
    public void ignoresTrailingSpaces() {
        m = programmer.compile("x   ");
        assertNameOfIs(m, "x");
        assertTrue(m.stepCountIs(0));
    }

    @Test
    public void handlesStatelessOperators() {
        m = programmer.compile("x sum + - *");
        assertTrue(m.stepCountIs(4));
    }
    
    @Test
    public void canHandlePusingConstantStatefulOperator() {
        m = programmer.compile("x 2 *");
        stack = RpnStackObjectMother.build(4);
        m.execute(stack);
        assertEquals(new BigDecimal(8), stack.peek());
    }
    
    @Test
    public void canHandleEmptyIfElseThen() {
        m = programmer.compile("x if then");
        stack = mock(RpnStack.class);
        when(stack.pop()).thenReturn(BigDecimal.ONE);
        m.execute(stack);
        verify(stack, times(1)).pop();
        verify(stack, never()).peek();
    }
    
    @Test
    public void canHandleSingleLevelIfWithNoElse() {
        m = programmer.compile("x if drop then");
        stack = mock(RpnStack.class);
        when(stack.pop()).thenReturn(BigDecimal.ONE);
        m.execute(stack);
        verify(stack, times(2)).pop();
        verify(stack, never()).peek();
        
        stack = mock(RpnStack.class);
        when(stack.pop()).thenReturn(BigDecimal.ZERO);
        m.execute(stack);
        verify(stack, times(1)).pop();
        verify(stack, never()).peek();
    }
    
    @Test
    public void canHandleSingleLevelIfWithEmptyElse() {
        m = programmer.compile("x if drop else then");
    }
    
    @Test
    public void canHandleSingleLevelIfWithOnlythen() {
        m = programmer.compile("x if else drop then");
    }
    
    @Test
    public void canHandleSingleLevelIfElseWithBothPaths() {
        m = programmer.compile("x if swap else drop then");
    }
}
