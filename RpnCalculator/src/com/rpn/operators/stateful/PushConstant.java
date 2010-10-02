package com.rpn.operators.stateful;

import java.math.BigDecimal;

import com.rpn.IOperator;
import com.rpn.RpnStack;

public class PushConstant implements IOperator {
    public final BigDecimal value;
    
    public PushConstant(BigDecimal value) {
        this.value = value;
    }
    
    @Override
    public void execute(RpnStack values) {
        values.push(value);
    }

}
