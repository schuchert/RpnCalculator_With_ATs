package com.rpn.operators.stateful;

import com.rpn.IOperator;

public interface ICompositeOperator extends IOperator {
    public abstract void append(IOperator step);
    public abstract boolean includes(IOperator step);
}