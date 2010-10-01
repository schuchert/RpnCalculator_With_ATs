package com.rpn.operators.stateless;

import com.rpn.RegisteredName;

@RegisteredName("/")
public class Divide extends BinaryOperator {
    @Override
    protected int figure(int lhs, int rhs) {
        if (rhs == 0)
            throw new ArithmeticException("Divide by 0");
        return lhs / rhs;
    }
}
