package com.rpn.operators.stateless;

import com.rpn.*;

public class ByteValue implements IOperator {
    @Override
    public void execute(RpnStack values) {
        values.push(values.pop().byteValue());
    }
}
