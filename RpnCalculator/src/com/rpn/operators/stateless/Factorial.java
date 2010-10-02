package com.rpn.operators.stateless;

import java.math.BigDecimal;

import com.rpn.RegisteredName;

@RegisteredName("!")
public class Factorial extends UnaryOperator {
    public BigDecimal figure(BigDecimal operand) {
        BigDecimal result = BigDecimal.ONE;
        for (BigDecimal i = new BigDecimal(2); i.compareTo(operand) <= 0; i = i.add(BigDecimal.ONE))
            result = result.multiply(i);
        return result;
    }
}
