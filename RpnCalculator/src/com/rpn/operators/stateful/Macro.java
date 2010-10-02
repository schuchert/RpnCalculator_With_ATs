package com.rpn.operators.stateful;

import java.util.*;

import com.rpn.*;

public class Macro extends CompositeOperator implements Iterable<IOperator> {
    public final String name;
    private ArrayList<IOperator> steps = new ArrayList<IOperator>();

    public Macro() {
        this("");
    }
    
    public Macro(String name) {
        this.name = name;
    }
    
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

    public boolean stepCountIs(int value) {
        return steps.size() == value;
    }

    @Override
    public Iterator<IOperator> iterator() {
        return new MacroIterator(steps);
    }
}
