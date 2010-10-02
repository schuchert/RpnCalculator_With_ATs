package com.rpn.operators.stateless;

import com.rpn.*;

public class Dup implements IOperator {
    @Override
    public void execute(RpnStack values) {
        values.push(values.peek());
    }
}
