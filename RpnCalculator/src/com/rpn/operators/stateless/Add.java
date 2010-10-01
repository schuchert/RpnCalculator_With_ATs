package com.rpn.operators.stateless;

import com.rpn.RegisteredName;

@RegisteredName("+")
public class Add extends BinaryOperator {

    @Override
    protected int figure(int lhs, int rhs) {
        return lhs + rhs;
    }

}
