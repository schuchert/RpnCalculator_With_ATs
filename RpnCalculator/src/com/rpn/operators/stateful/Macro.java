package com.rpn.operators.stateful;

import java.util.ArrayList;

import com.rpn.*;

public class Macro extends CompositeOperator {
    private ArrayList<IOperator> steps = new ArrayList<IOperator>();

    @Override
    public void execute(RpnStack values) {
        for (IOperator current : steps)
            current.execute(values);
    }

    public void append(IOperator step) {
        validateAppend(step);
        steps.add(step);
    }

    @Override
    public boolean includesAnyIn(CompositeOperator composite) {
        for (IOperator current : steps)
            if (current.equals(composite) || asCompositeOperator(current).includesAnyIn(composite))
                return true;
        return false;
    }
}
