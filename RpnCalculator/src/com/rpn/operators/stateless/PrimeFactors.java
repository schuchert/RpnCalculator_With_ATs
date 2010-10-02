package com.rpn.operators.stateless;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.rpn.IOperator;
import com.rpn.RegisteredName;
import com.rpn.RpnStack;

@RegisteredName("primeFactors")
public class PrimeFactors implements IOperator {
    public static final BigInteger TWO = new BigInteger("2");

    @Override
    public void execute(RpnStack values) {
        BigInteger value = getIntegerValue(values);

        for (BigInteger divisor = TWO; divisor.compareTo(value) <= 0; divisor = divisor.add(BigInteger.ONE))
            while (value.remainder(divisor).compareTo(BigInteger.ZERO) == 0) {
                values.push(new BigDecimal(divisor));
                value = value.divide(divisor);
            }
    }

    private BigInteger getIntegerValue(RpnStack values) {
        BigDecimal candidateValue = values.pop();
        if (candidateValue.compareTo(BigDecimal.ZERO) < 0)
            throw new ArithmeticException("Prime Factors undefined for values < 0");
        BigInteger value = candidateValue.toBigIntegerExact();
        return value;
    }
}
