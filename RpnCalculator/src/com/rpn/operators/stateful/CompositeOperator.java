package com.rpn.operators.stateful;

import java.util.Iterator;

import com.rpn.*;

public abstract class CompositeOperator implements IOperator {
    public abstract boolean includesAnyIn(CompositeOperator step);

    public abstract Iterator<IOperator> containedIterator();
    public abstract Iterator<IOperator> iterator();

    final protected void validateAppend(IOperator step) {
        if (step.equals(this) || asCompositeOperator(step).includesAnyIn(this))
            throw new CircularCompositionNotAllowed();
    }

    final protected CompositeOperator asCompositeOperator(IOperator op) {
        if (op instanceof CompositeOperator)
            return (CompositeOperator) op;
        return NULL_OBJECT;
    }

    protected static final CompositeOperator NULL_OBJECT = new CompositeOperator() {
        @Override
        public boolean equals(Object o) {
            return false;
        }

        @Override
        public void execute(RpnStack values) {
        }

        @Override
        public boolean includesAnyIn(CompositeOperator step) {
            return false;
        }

        @Override
        public OperatorIteratorAdapter iterator() {
            return OperatorIteratorAdapter.NULL_ITERATOR;
        }

        @Override
        public OperatorIteratorAdapter containedIterator() {
            return OperatorIteratorAdapter.NULL_ITERATOR;
        }
    };

}