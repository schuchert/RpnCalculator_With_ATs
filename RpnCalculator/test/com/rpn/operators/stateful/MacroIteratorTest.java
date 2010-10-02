package com.rpn.operators.stateful;

import java.util.Iterator;

import org.junit.Test;

import com.rpn.IOperator;
import com.rpn.operators.stateless.*;

import static org.junit.Assert.*;

public class MacroIteratorTest {
    @Test
    public void walksFlatMacro() {
        Macro m = new Macro();
        m.append(new Drop());
        m.append(new Dup());
        m.append(new LessThen());
        Iterator<IOperator> iter = new MacroIterator(m);
        assertTrue(iter.next() instanceof Drop);
        assertTrue(iter.next() instanceof Dup);
        assertTrue(iter.next() instanceof LessThen);
        assertFalse(iter.hasNext());
    }
    
    @Test
    public void walksIf() {
        Macro trueBlock = new Macro();
        trueBlock.append(new Drop());
        Macro falseBlock = new Macro();
        falseBlock.append(new Swap());
        falseBlock.append(new Drop());
        If op = new If(trueBlock, falseBlock);
        Iterator<IOperator> iter = op.iterator();
        assertTrue(iter.next() instanceof Drop);
        assertTrue(iter.next() instanceof Swap);
        assertTrue(iter.next() instanceof Drop);
        assertFalse(iter.hasNext());
    }
    
    @Test
    public void walksNestedMacroWithNestedMacroAtEndOfList() {
        Macro m1 = new Macro();
        m1.append(new Swap());
        m1.append(new Drop());
        Macro m2 = new Macro();
        m2.append(new LessThen());
        m2.append(new PrimeFactors());
        m1.append(m2);
        Iterator<IOperator> iter = new MacroIterator(m1);
        assertTrue(iter.next() instanceof Swap);
        assertTrue(iter.next() instanceof Drop);
        assertTrue(iter.next() instanceof LessThen);
        assertTrue(iter.next() instanceof PrimeFactors);
        assertFalse(iter.hasNext());
    }
    
    @Test
    public void walksNestedMacroAndThenReturnsUpToCompleteTopLevel() {
        Macro m1 = new Macro();
        Macro m2 = new Macro();
        m1.append(m2);
        m1.append(new Drop());
        m2.append(new Swap());
        Iterator<IOperator> iter = new MacroIterator(m1);
        assertTrue(iter.next() instanceof Swap);
        assertTrue(iter.next() instanceof Drop);
        assertFalse(iter.hasNext());
    }
    
    @Test
    public void macroWithIfWalkedCorrectly() {
        Macro m1 = new Macro();
        m1.append(new Swap());
        Macro trueBlock = new Macro();
        trueBlock.append(new LessThen());
        Macro falseBlock = new Macro();
        falseBlock.append(new GreaterThen());
        If ifOp = new If(trueBlock, falseBlock);
        m1.append(ifOp);
        m1.append(new Drop());
        MacroIterator iter = new MacroIterator(m1);
        assertTrue(iter.next() instanceof Swap);
        assertTrue(iter.next() instanceof LessThen);
        assertTrue(iter.next() instanceof GreaterThen);
        assertTrue(iter.next() instanceof Drop);
        assertFalse(iter.hasNext());
    }
}
