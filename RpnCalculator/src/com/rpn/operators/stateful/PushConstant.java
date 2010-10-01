package com.rpn.operators.stateful;

import com.rpn.IOperator;
import com.rpn.RpnStack;

public class PushConstant implements IOperator {
    public final int value;
    
    public PushConstant(int value) {
        this.value = value;
    }
    
    @Override
    public void execute(RpnStack values) {
        values.push(value);
    }

}
