package com.rpn.operators.stateless;

import com.rpn.RegisteredName;

@RegisteredName(">")
public class GreaterThen extends BinaryOperator {
    @Override
    protected int figure(int lhs, int rhs) {
        return lhs > rhs ? 1 : 0;
    }
}
