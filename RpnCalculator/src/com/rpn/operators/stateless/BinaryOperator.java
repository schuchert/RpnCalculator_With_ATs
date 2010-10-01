package com.rpn.operators.stateless;

import com.rpn.IOperator;
import com.rpn.RpnStack;

public abstract class BinaryOperator implements IOperator {

    public BinaryOperator() {
        super();
    }

    protected abstract int figure(int lhs, int rhs);

    public void execute(RpnStack values) {
        int rhs = values.pop();
        int lhs = values.pop();
        int result = figure(lhs, rhs);
        values.push(result);
    }

}