package com.rpn.operators.stateless;

import java.math.BigDecimal;

import com.rpn.IOperator;
import com.rpn.RegisteredName;

@RegisteredName("-")
public class Subtract extends BinaryOperator implements IOperator {

    @Override
    protected BigDecimal figure(BigDecimal lhs, BigDecimal rhs) {
        return lhs.subtract(rhs);
    }

}
