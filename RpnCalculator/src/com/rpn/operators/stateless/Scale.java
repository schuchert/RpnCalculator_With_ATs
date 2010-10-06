package com.rpn.operators.stateless;

import com.rpn.*;

public class Scale implements IOperator {
    @Override
    public void execute(RpnStack values) {
        values.push(values.pop().scale());
    }
}
