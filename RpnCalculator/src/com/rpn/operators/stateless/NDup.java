package com.rpn.operators.stateless;

import com.rpn.IOperator;
import com.rpn.RpnStack;

public class NDup implements IOperator {
    @Override
    public void execute(RpnStack values) {
        int count = values.pop();

        if (count < 1)
            throw new ArithmeticException("Cannot ndup < 1 stack items");

        int[] valuesToCopy = new int[count];
        while (count-- > 0)
            valuesToCopy[count] = values.pop();

        for (int i = 0; i < 2; ++i)
            for (int current : valuesToCopy)
                values.push(current);
    }
}
