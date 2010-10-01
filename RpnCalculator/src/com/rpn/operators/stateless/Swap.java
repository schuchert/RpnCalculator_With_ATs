package com.rpn.operators.stateless;

import com.rpn.IOperator;
import com.rpn.RpnStack;

public class Swap implements IOperator {
    @Override
    public void execute(RpnStack values) {
        int x = values.pop();
        int y = values.pop();
        values.push(x);
        values.push(y);
    }
}
