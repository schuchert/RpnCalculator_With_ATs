package com.rpn.objectmothers;

import com.rpn.RpnStack;

public class RpnStackObjectMother {
    public static RpnStack build(int ...values) {
        RpnStack stack = new RpnStack();
        for(int current : values)
            stack.push(current);
        
        return stack;
    }
}
