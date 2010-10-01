package com.rpn.operators.stateless;

import java.math.BigDecimal;

import com.rpn.IOperator;
import com.rpn.RpnStack;

public class Swap implements IOperator {
    @Override
    public void execute(RpnStack values) {
        BigDecimal x = values.pop();
        BigDecimal y = values.pop();
        values.push(x);
        values.push(y);
    }
}
