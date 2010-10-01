package com.rpn.operators.stateless;

import java.math.BigDecimal;

import com.rpn.RegisteredName;

@RegisteredName("/")
public class Divide extends BinaryOperator {
    @Override
    protected BigDecimal figure(BigDecimal lhs, BigDecimal rhs) {
        if (rhs.equals(BigDecimal.ZERO))
            throw new ArithmeticException("Divide by 0");
        return lhs.divide(rhs);
    }
}
