package com.rpn;

import java.util.Stack;

public class RpnStack {
    private Stack<Integer> values = new Stack<Integer>();

    public int pop() {
        if (values.size() > 0)
            return values.pop();
        return 0;
    }

    public int peek() {
        if (values.size() > 0)
            return values.peek();
        return 0;
    }

    public void push(int value) {
        values.push(value);
    }

    public int size() {
        return values.size();
    }
}
