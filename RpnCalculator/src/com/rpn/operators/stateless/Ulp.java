package com.rpn.operators.stateless;

import java.math.BigDecimal;

import com.rpn.*;

public class Ulp implements IOperator {
    @Override
    public void execute(RpnStack values) {
        BigDecimal value = values.pop();
        values.push(value.ulp());
    }
}
