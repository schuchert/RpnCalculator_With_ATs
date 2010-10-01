package com.rpn.operators.stateless;

import com.rpn.IOperator;
import com.rpn.RegisteredName;
import com.rpn.RpnStack;

@RegisteredName("primeFactors")
public class PrimeFactors implements IOperator {
    @Override
    public void execute(RpnStack values) {
        int value = values.pop();
        for (int divisor = 2; divisor <= value; ++divisor)
            while (value % divisor == 0) {
                values.push(divisor);
                value /= divisor;
            }
    }
}
