package com.rpn.operators.stateful;

import java.util.Iterator;

import com.rpn.*;

public class OperatorIteratorAdapter implements Iterator<IOperator> {
    static OperatorIteratorAdapter NULL_ITERATOR = new OperatorIteratorAdapter();
    
    @Override
    public void remove() {
    }
    @Override
    public IOperator next() {
        return null;
    }
    @Override
    public boolean hasNext() {
        return false;
    }
}
