package com.rpn.operators.stateful;

import java.math.BigDecimal;
import java.util.Iterator;

import com.rpn.*;

class IfIterator implements Iterator<IOperator> {
    private final Iterator<IOperator> one;
    private final Iterator<IOperator> two;

    public IfIterator(Iterator<IOperator> one, Iterator<IOperator> two) {
        this.one = one;
        this.two = two;
    }

    @Override
    public boolean hasNext() {
        return one.hasNext() || two.hasNext();
    }

    @Override
    public IOperator next() {
        if (one.hasNext())
            return one.next();
        else if (two.hasNext())
            return two.next();
        else
            throw new RuntimeException("Beyond Both Iterators");
    }

    @Override
    public void remove() {
    }

}

public class If extends CompositeOperator implements Iterable<IOperator> {
    private CompositeOperator trueBlock;
    private CompositeOperator falseBlock;

    public If() {
        this(NULL_OBJECT, NULL_OBJECT);
    }

    public If(CompositeOperator trueBlock, CompositeOperator falseBlock) {
        this.trueBlock = trueBlock;
        this.falseBlock = falseBlock;
    }

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
        return this.equals(step) || asCompositeOperator(trueBlock).includesAnyIn(step)
                || asCompositeOperator(falseBlock).includesAnyIn(step);
    }

    public void setTrueBlock(CompositeOperator block) {
        validateSetOf(block);
        this.trueBlock = block;
    }

    public void setFalseBlock(CompositeOperator block) {
        validateSetOf(block);
        this.falseBlock = block;
    }

    private void validateSetOf(IOperator block) {
        if (asCompositeOperator(block).includesAnyIn(this))
            throw new CircularCompositionNotAllowed();
    }

    public IOperator trueBlock() {
        return trueBlock;
    }

    public IOperator falseBlock() {
        return falseBlock;
    }

    @Override
    public Iterator<IOperator> iterator() {
        return new IfIterator(trueBlock.iterator(), falseBlock.iterator());
    }

}
