package com.rpn.operators.stateless;

import com.rpn.IOperator;
import com.rpn.RpnStack;

public class Dup implements IOperator {

    @Override
    public void execute(RpnStack values) {
        values.push(values.peek());
    }

}
