package com.rpn.operators.stateful;

import java.math.BigDecimal;

import com.rpn.*;

public class If extends CompositeOperator {

    private IOperator trueBlock = NULL_OBJECT;
    private IOperator falseBlock = NULL_OBJECT;
    
    @Override
    public void execute(RpnStack values) {
        BigDecimal value = values.pop();

        if (value.compareTo(BigDecimal.ZERO) != 0)
            trueBlock.execute(values);
        else
            falseBlock.execute(values);
    }

    @Override
    public boolean includesAnyIn(CompositeOperator step) {
        return !this.equals(step) || asCompositeOperator(trueBlock).includesAnyIn(step)
                || asCompositeOperator(falseBlock).includesAnyIn(step);
    }

    public void setTrueBlock(IOperator block) {
        validateSetOf(block);
        this.trueBlock = block;
    }

    public void setFalseBlock(IOperator block) {
        validateSetOf(block);
        this.falseBlock = block;
    }

    private void validateSetOf(IOperator block) {
        if (asCompositeOperator(block).includesAnyIn(this))
            throw new CircularCompositionNotAllowed();
    }

}
