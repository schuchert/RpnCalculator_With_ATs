package com.rpn.operators.stateful;

import java.util.Iterator;

import com.rpn.*;

public abstract class CompositeOperator implements IOperator {
    public abstract boolean includesAnyIn(CompositeOperator step);

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
        public Iterator<IOperator> iterator() {
            return new Iterator<IOperator>() {
                @Override
                public boolean hasNext() {
                    return false;
                }

                @Override
                public IOperator next() {
                    return null;
                }

                @Override
                public void remove() {
                }
            };
        }
    };
}