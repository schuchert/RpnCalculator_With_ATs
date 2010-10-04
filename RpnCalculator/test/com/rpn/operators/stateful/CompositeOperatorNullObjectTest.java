package com.rpn.operators.stateful;

import org.junit.Test;

import static org.junit.Assert.*;

public class CompositeOperatorNullObjectTest {
    private static final CompositeOperator NULL_OBJECT = CompositeOperator.NULL_OBJECT;

    @Test
    public void shouldNotEqualsItself() {
        NULL_OBJECT.equals(NULL_OBJECT);
    }
    
    @Test
    public void shouldReturnANullIterator() {
        assertSame(NULL_OBJECT.iterator(), OperatorIteratorAdapter.NULL_ITERATOR);
    }
}
