package com.rpn.operators.stateless;

import java.math.BigDecimal;

import com.rpn.IOperator;
import com.rpn.RegisteredName;
import com.rpn.RpnStack;

@RegisteredName("!")
public class Factorial implements IOperator {

    public void execute(RpnStack values) {
        BigDecimal operand = values.pop();

        BigDecimal result = BigDecimal.ONE;
        for (BigDecimal i = new BigDecimal(2); i.compareTo(operand) <= 0; i = i.add(BigDecimal.ONE))
            result = result.multiply(i);

        values.push(result);
    }
}
