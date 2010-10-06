package com.rpn.operators.stateless;

import java.math.BigDecimal;

import com.rpn.*;

public class Wait implements IOperator {

    public void execute(RpnStack values) {
        BigDecimal delay = values.pop();
        try {
            Thread.sleep(delay.intValue());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
