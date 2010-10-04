package com.rpn.operators.stateful;

import java.util.Iterator;

import com.rpn.*;

public class IOperatorIteratorAdapter implements Iterator<IOperator> {
    static IOperatorIteratorAdapter NULL_ITERATOR = new IOperatorIteratorAdapter();
    
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
