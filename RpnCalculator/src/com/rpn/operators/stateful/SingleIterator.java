package com.rpn.operators.stateful;

import com.rpn.IOperator;

public class SingleIterator extends OperatorIteratorAdapter {
    private IOperator singleValue;

    public SingleIterator(IOperator value) {
        singleValue = value;
    }

    @Override
    public boolean hasNext() {
        return singleValue != null;
    }

    @Override
    public IOperator next() {
        IOperator result = singleValue;
        singleValue = null;
        return result;
    }
}

