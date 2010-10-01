package com.rpn.operators.stateless;

import java.math.BigDecimal;

import com.rpn.IOperator;
import com.rpn.RpnStack;

public class Sum implements IOperator {
    @Override
    public void execute(RpnStack values) {
        BigDecimal sum = BigDecimal.ZERO;
        while (values.size() > 0)
            sum = sum.add(values.pop());
        values.push(sum);
    }
}
