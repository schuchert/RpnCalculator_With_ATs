package com.rpn.operators.stateful;

import java.util.*;

import com.rpn.IOperator;

public class MacroIterator implements Iterator<IOperator> {
    Stack<Iterator<IOperator>> pendingWork = new Stack<Iterator<IOperator>>();

    public MacroIterator(Iterable<IOperator> iterable) {
        pendingWork.push(iterable.iterator());
    }

    @Override
    public boolean hasNext() {
        while (pendingWork.size() > 0 && top().hasNext() == false)
            pendingWork.pop();
        return pendingWork.size() > 0 && top().hasNext();
    }

    @Override
    public IOperator next() {
        hasNext();
        IOperator candidate = top().next();
        Iterator<IOperator> iter = asIter(candidate);

        if (iter != null) {
            pendingWork.push(iter);
            return next();
        }
        return candidate;
    }

    Iterator<IOperator> asIter(IOperator candidate) {
        if (candidate instanceof CompositeOperator)
            return CompositeOperator.class.cast(candidate).iterator();
        return null;
    }

    @Override
    public void remove() {
    }

    Iterator<IOperator> top() {
        return pendingWork.peek();
    }
}
