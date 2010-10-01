package com.rpn;

import java.math.BigDecimal;
import java.util.Stack;

public class RpnStack {
    private Stack<BigDecimal> values = new Stack<BigDecimal>();

    public BigDecimal pop() {
        if (values.size() > 0)
            return values.pop();
        return BigDecimal.ZERO;
    }

    public BigDecimal peek() {
        if (values.size() > 0)
            return values.peek();
        return BigDecimal.ZERO;
    }

    public void push(BigDecimal value) {
        values.push(value);
    }

    public int size() {
        return values.size();
    }

    public void push(int value) {
        push(new BigDecimal(value));
    }
}
