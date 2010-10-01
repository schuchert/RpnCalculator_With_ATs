package com.rpn.operators.stateless;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.Vector;

import com.rpn.IOperator;
import com.rpn.RpnStack;

public class NDup implements IOperator {
    @Override
    public void execute(RpnStack values) {
        BigDecimal candidateCount = values.pop();

        if (candidateCount.compareTo(BigDecimal.ONE) < 0)
            throw new ArithmeticException("Cannot ndup < 1 stack items");

        BigInteger count = candidateCount.toBigInteger();

        Vector<BigDecimal> valuesToCopy = new Vector<BigDecimal>(count.intValue());
        while ((count = count.subtract(BigInteger.ONE)).compareTo(BigInteger.ZERO) >= 0)
            valuesToCopy.add(values.pop());
        Collections.reverse(valuesToCopy);

        for (int i = 0; i < 2; ++i)
            for (BigDecimal current : valuesToCopy)
                values.push(current);
    }
}
