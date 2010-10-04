package com.rpn.operators.stateful;

import java.util.*;

import com.rpn.IOperator;

class IfIterator extends IOperatorIteratorAdapter {
    private Stack<Iterator<IOperator>> pending = new Stack<Iterator<IOperator>>();

    public IfIterator(Iterator<IOperator> trueIter, Iterator<IOperator> falseIter) {
        pending.push(falseIter);
        pending.push(trueIter);
    }
    
    public IfIterator(If ifOp, Iterator<IOperator> trueIter, Iterator<IOperator> falseIter) {
        this(trueIter, falseIter);
        pending.push(new SingleIterator(ifOp));
    }

    Iterator<IOperator> top() {
        return pending.peek();
    }
    
    @Override
    public boolean hasNext() {
        removeFinishedPending();
        return pending.size() > 0 && top().hasNext();
    }

    private void removeFinishedPending() {
        while(pending.size() > 0 && !top().hasNext())
            pending.pop();
    }

    @Override
    public IOperator next() {
        removeFinishedPending();
        IOperator next = top().next();
        return next;
    }
}
