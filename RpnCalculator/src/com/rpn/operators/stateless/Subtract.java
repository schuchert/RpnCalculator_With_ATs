package com.rpn.operators.stateless;

import com.rpn.IOperator;
import com.rpn.RegisteredName;

@RegisteredName("-")
public class Subtract extends BinaryOperator implements IOperator {

    @Override
    protected int figure(int lhs, int rhs) {
        return lhs - rhs;
    }

}
