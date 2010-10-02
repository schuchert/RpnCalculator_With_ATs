package com.rpn.operators.stateless;

import java.math.BigDecimal;

public class Not extends UnaryOperator {
    @Override
    public BigDecimal figure(BigDecimal operand) {
        return operand.compareTo(BigDecimal.ZERO) == 0 ? BigDecimal.ONE : BigDecimal.ZERO;
    }
}
