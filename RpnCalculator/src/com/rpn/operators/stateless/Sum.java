package com.rpn.operators.stateless;

import com.rpn.IOperator;
import com.rpn.RpnStack;

public class Sum implements IOperator {
    @Override
    public void execute(RpnStack values) {
        int sum = 0;
        while (values.size() > 0)
            sum += values.pop();
        values.push(sum);
    }
}
