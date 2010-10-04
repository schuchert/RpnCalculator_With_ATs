package com.rpn.fixtures;

import com.rpn.RpnCalculator;

public class DestructiveCalculatorResultGenerator {
    private final RpnCalculator calculator;

    public DestructiveCalculatorResultGenerator(RpnCalculator calculator) {
        this.calculator = calculator;
    }

    public String report() {
        StringBuffer result = new StringBuffer();

        while (calculator.hasOperands())
            appendNextValueTo(result);

        return result.toString();
    }

    private void appendNextValueTo(StringBuffer result) {
        conditionallyAddSpace(result);
        appendCurrentValue(result);
        removeCurrentValue();
    }

    private void removeCurrentValue() {
        calculator.perform("drop");
    }

    private void appendCurrentValue(StringBuffer result) {
        result.insert(0, calculator.getDisplay());
    }

    private void conditionallyAddSpace(StringBuffer result) {
        if (result.length() > 0)
            result.insert(0, ' ');
    }
}
