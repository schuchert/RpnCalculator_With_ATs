package com.rpn.operators.stateless;

import java.math.BigDecimal;

import com.rpn.*;

@RegisteredName("abs")
public class Absloute implements IOperator {
    public final BigDecimal NEGATIVE_ONE = new BigDecimal(-1);
    @Override
    public void execute(RpnStack values) {
        BigDecimal value = values.pop();
        
        if(value.compareTo(BigDecimal.ZERO) < 0)
            value = value.multiply(NEGATIVE_ONE);
        
        values.push(value);
    }

}
