package com.rpn.operators.stateless;

import java.math.BigDecimal;

import com.rpn.RegisteredName;

@RegisteredName("+")
public class Add extends BinaryOperator {

    @Override
    protected BigDecimal figure(BigDecimal lhs, BigDecimal rhs) {
        return lhs.add(rhs);
    }

}
