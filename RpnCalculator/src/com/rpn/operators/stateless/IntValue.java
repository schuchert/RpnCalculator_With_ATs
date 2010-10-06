package com.rpn.operators.stateless;

import java.math.*;

import com.rpn.*;

public class IntValue implements IOperator {
    @Override
    public void execute(RpnStack values) {
        values.push(new BigDecimal(values.pop().toBigInteger()));
    }
}
