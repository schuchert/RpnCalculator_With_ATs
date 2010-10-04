package com.rpn.operators.stateful;

import java.util.*;

import com.rpn.IOperator;

public class CompositeOperatorIterator extends IOperatorIteratorAdapter {
    private Stack<Iterator<IOperator>> pending = new Stack<Iterator<IOperator>>();

    public CompositeOperatorIterator(Iterator<IOperator> s1, Iterator<IOperator> s2) {
        makePending(s2);
        makePending(s1);
    }

    public CompositeOperatorIterator(CompositeOperator composite, Iterator<IOperator> steps) {
        makePending(steps);
        makePending(new SingleIterator(composite));
    }

    public CompositeOperatorIterator(CompositeOperator composite) {
        makePending(new SingleIterator(composite));
    }

    public CompositeOperatorIterator(CompositeOperator block1, CompositeOperator block2) {
        makePending(new SingleIterator(block2));
        makePending(new SingleIterator(block1));
    }

    @Override
    public boolean hasNext() {
        dropFinishedIterators();
        return notEmpty() && workRemains();
    }

    private void dropFinishedIterators() {
        while (notEmpty() && !workRemains())
            removePending();
    }

    @Override
    public IOperator next() {
        dropFinishedIterators();
        IOperator next = top().next();
        conditionallyAddToPendingWork(next);
        return next;
    }

    private void conditionallyAddToPendingWork(IOperator next) {
        if (next instanceof CompositeOperator) {
            if (!(top() instanceof CompositeOperatorIterator))
                makePending(asCo(next).containedIterator());
        }
    }

    CompositeOperator asCo(IOperator op) {
        if (op instanceof CompositeOperator)
            return (CompositeOperator) op;
        return null;
    }

    Iterator<IOperator> top() {
        return pending.peek();
    }

    void removePending() {
        pending.pop();
    }

    boolean notEmpty() {
        return pending.size() > 0;
    }

    void makePending(Iterator<IOperator> iter) {
        pending.push(iter);
    }

    private boolean workRemains() {
        return top().hasNext();
    }

}
