package com.rpn.operators.stateless;

import java.math.BigDecimal;

import com.rpn.IOperator;
import com.rpn.RpnStack;

public abstract class BinaryOperator implements IOperator {

    protected abstract BigDecimal figure(BigDecimal lhs, BigDecimal rhs);

    public void execute(RpnStack values) {
        BigDecimal rhs = values.pop();
        BigDecimal lhs = values.pop();
        BigDecimal result = figure(lhs, rhs);
        values.push(result);
    }

}