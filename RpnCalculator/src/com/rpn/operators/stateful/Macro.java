package com.rpn.operators.stateful;

import java.util.ArrayList;

import com.rpn.IOperator;
import com.rpn.RpnStack;

public class Macro implements ICompositeOperator {

    private ArrayList<IOperator> steps = new ArrayList<IOperator>();

    @Override
    public void execute(RpnStack values) {
        for(IOperator current : steps)
            current.execute(values);
    }

    @Override
    public void append(IOperator step) {
        steps.add(step);
    }
    
    @Override
    public boolean includes(IOperator step) {
        return steps.contains(step);
    }
}
