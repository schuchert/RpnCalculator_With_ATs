package com.rpn.operators.stateless;

import java.math.BigDecimal;

import com.rpn.*;

public abstract class UnaryOperator implements IOperator {

    public final void execute(RpnStack values) {
        BigDecimal operand = values.pop();
        BigDecimal result = figure(operand);
        values.push(result);
    }

    public abstract BigDecimal figure(BigDecimal operand); 

}