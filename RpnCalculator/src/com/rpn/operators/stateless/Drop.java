package com.rpn.operators.stateless;

import com.rpn.IOperator;
import com.rpn.RpnStack;

public class Drop implements IOperator {
    @Override
    public void execute(RpnStack values) {
        values.pop();
    }
}
