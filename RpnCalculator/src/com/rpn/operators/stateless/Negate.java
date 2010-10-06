package com.rpn.operators.stateless;

import java.math.BigDecimal;

import com.rpn.*;

@RegisteredName("neg")
public class Negate implements IOperator {
    public final BigDecimal NEGATIVE_ONE = new BigDecimal(-1);
    
    @Override
    public void execute(RpnStack values) {
        values.push(values.pop().multiply(NEGATIVE_ONE));
    }
}
