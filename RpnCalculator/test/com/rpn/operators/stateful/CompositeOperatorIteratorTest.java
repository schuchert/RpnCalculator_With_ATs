package com.rpn.operators.stateful;

import java.util.Iterator;

import org.junit.Test;

import com.rpn.IOperator;
import com.rpn.operators.stateless.*;

import static org.junit.Assert.*;

public class CompositeOperatorIteratorTest {
    Macro m = new Macro();
    If ifOp = new If();

    @Test
    public void iterationOverEmptyMacroResultsInMacorItself() {
        Iterator<IOperator> iter = m.iterator();
        assertSame(m, iter.next());
        assertFalse(iter.hasNext());
    }

    @Test
    public void iterationOverNonEmptyFlatMacroWorks() {
        m.append(new Drop());
        m.append(new LessThen());
        Iterator<IOperator> iter = m.iterator();
        iter.next();
        assertTrue(iter.next() instanceof Drop);
        assertTrue(iter.next() instanceof LessThen);
        assertFalse(iter.hasNext());
    }

    @Test
    public void nestedButEmptyMacrosWork() {
        m.append(new Macro());
        Iterator<IOperator> iter = m.iterator();
        assertTrue(iter.next() instanceof Macro);
        assertTrue(iter.next() instanceof Macro);
        assertFalse(iter.hasNext());
    }

    @Test
    public void nestedNonEmptyMacrosWork() {
        m.append(new Drop());
        Macro m2 = new Macro();
        m2.append(new LessThen());
        m2.append(new EqualTo());
        m.append(m2);
        m.append(new Swap());
        CompositeOperatorIterator iter = new CompositeOperatorIterator(m);
        assertTrue(iter.next() instanceof Macro);
        assertTrue(iter.next() instanceof Drop);
        assertTrue(iter.next() instanceof Macro);
        assertTrue(iter.next() instanceof LessThen);
        assertTrue(iter.next() instanceof EqualTo);
        assertTrue(iter.next() instanceof Swap);
        assertFalse(iter.hasNext());
    }

    @Test
    public void emptyIfReturned() {
        Iterator<IOperator> iter = ifOp.iterator();
        assertTrue(iter.next() instanceof If);
        assertTrue(iter.next() instanceof CompositeOperator);
        assertTrue(iter.next() instanceof CompositeOperator);
        assertFalse(iter.hasNext());
    }

    @Test
    public void ifWithEmptyTrueBlockReturnsJustIf() {
        ifOp.setTrueBlock(m);
        Iterator<IOperator> iter = ifOp.iterator();
        assertTrue(iter.next() instanceof If);
        assertTrue(iter.next() instanceof CompositeOperator);
        assertTrue(iter.next() instanceof CompositeOperator);
        assertFalse(iter.hasNext());
    }

    @Test
    public void ifWithNonEmptyTrueBlockIteratesCorrectly() {
        m.append(new Swap());
        m.append(new Drop());
        ifOp.setTrueBlock(m);
        Iterator<IOperator> iter = ifOp.iterator();
        assertTrue(iter.next() instanceof If);
        assertTrue(iter.next() instanceof CompositeOperator);
        assertTrue(iter.next() instanceof Swap);
        assertTrue(iter.next() instanceof Drop);
        assertTrue(iter.next() instanceof CompositeOperator);
        assertFalse(iter.hasNext());
    }

    @Test
    public void ifWithIfWorks() {
        ifOp.setTrueBlock(new If());
        Iterator<IOperator> iter = ifOp.iterator();
        assertTrue(iter.next() instanceof If);
        assertTrue(iter.next() instanceof If);
        assertTrue(iter.next() instanceof CompositeOperator);
        assertTrue(iter.next() instanceof CompositeOperator);
        assertTrue(iter.next() instanceof CompositeOperator);
        assertFalse(iter.hasNext());
    }
}
