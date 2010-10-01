package com.rpn.operators.stateless;

import com.rpn.IOperator;
import com.rpn.RegisteredName;
import com.rpn.RpnStack;

@RegisteredName("!")
public class Factorial implements IOperator {

    public void execute(RpnStack values) {
        int operand = values.pop();

        int result = 1;
        for (int i = 2; i <= operand; ++i)
            result *= i;

        values.push(result);
    }

}
