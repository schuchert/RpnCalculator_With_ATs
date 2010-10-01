package com.rpn.operators.stateless;

import java.math.BigDecimal;

import com.rpn.RegisteredName;

@RegisteredName(">")
public class GreaterThen extends BinaryOperator {
    @Override
    protected BigDecimal figure(BigDecimal lhs, BigDecimal rhs) {
        return lhs.compareTo(rhs) > 0 ? BigDecimal.ONE : BigDecimal.ZERO;
    }
}
