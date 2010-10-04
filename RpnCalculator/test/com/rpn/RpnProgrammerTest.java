package com.rpn;

import java.math.BigDecimal;
import java.util.Iterator;

import org.junit.Test;

import com.rpn.factory.OperatorFactory;
import com.rpn.objectmothers.RpnStackObjectMother;
import com.rpn.operators.stateful.*;
import com.rpn.operators.stateless.*;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

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
        m = programmer.compile("x", "");
        assertNameOfIs(m, "x");
        assertTrue(m.stepCountIs(0));
    }

    @Test
    public void ignoresLeadingSpaces() {
        m = programmer.compile("   x", "");
        assertNameOfIs(m, "x");
        assertTrue(m.stepCountIs(0));
    }

    @Test
    public void ignoresTrailingSpaces() {
        m = programmer.compile("x   ", "");
        assertNameOfIs(m, "x");
        assertTrue(m.stepCountIs(0));
    }

    @Test
    public void handlesStatelessOperators() {
        m = programmer.compile("x", "sum + - *");
        assertTrue(m.stepCountIs(4));
    }
    
    @Test
    public void canHandlePusingConstantStatefulOperator() {
        m = programmer.compile("x", "2 *");
        stack = RpnStackObjectMother.build(4);
        m.execute(stack);
        assertEquals(new BigDecimal(8), stack.peek());
    }
    
    @Test
    public void canHandleEmptyIfElseThen() {
        m = programmer.compile("x", "if then");
        stack = mock(RpnStack.class);
        when(stack.pop()).thenReturn(BigDecimal.ONE);
        m.execute(stack);
        verify(stack, times(1)).pop();
        verify(stack, never()).peek();
    }
    
    @Test
    public void canHandleSingleLevelIfWithNoElse() {
        m = programmer.compile("x", "if drop then");
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
        m = programmer.compile("x", "if drop else then");
    }
    
    @Test
    public void canHandleSingleLevelIfWithOnlythen() {
        m = programmer.compile("x", "if else drop then");
    }
    
    @Test
    public void canHandleSingleLevelIfElseWithBothPaths() {
        m = programmer.compile("x", "if swap else drop then");
    }
    
    @Test
    public void min() {
        m = programmer.compile("min", "2 ndup < if drop else swap drop then");
        stack = RpnStackObjectMother.build(4, 6);
        m.execute(stack);
        assertEquals(new BigDecimal(4), stack.peek());
        stack = RpnStackObjectMother.build(6, 4);
        m.execute(stack);
        assertEquals(new BigDecimal(4), stack.peek());
    }
    
    @Test
    public void nestedIf() {
        m = programmer.compile("x", "2 ndup < if drop 2 % if 7 * else 5 - then else 3 % then");
        Iterator<IOperator> iter = m.iterator();
        assertEquals(Macro.class, iter.next().getClass());
        assertEquals(PushConstant.class, iter.next().getClass());
        assertEquals(NDup.class, iter.next().getClass());
        assertEquals(LessThen.class, iter.next().getClass());
        assertEquals(If.class, iter.next().getClass());
        assertEquals(Macro.class, iter.next().getClass());
        assertEquals(Drop.class, iter.next().getClass());
        assertEquals(PushConstant.class, iter.next().getClass());
        assertEquals(Modulo.class, iter.next().getClass());
        assertEquals(If.class, iter.next().getClass());
        assertEquals(Macro.class, iter.next().getClass());
        assertEquals(PushConstant.class, iter.next().getClass());
        assertEquals(Multiply.class, iter.next().getClass());
        assertEquals(Macro.class, iter.next().getClass());
        assertEquals(PushConstant.class, iter.next().getClass());
        assertEquals(Subtract.class, iter.next().getClass());
        assertEquals(Macro.class, iter.next().getClass());
        assertEquals(PushConstant.class, iter.next().getClass());
        assertEquals(Modulo.class, iter.next().getClass());
        assertFalse(iter.hasNext());
    }
}
