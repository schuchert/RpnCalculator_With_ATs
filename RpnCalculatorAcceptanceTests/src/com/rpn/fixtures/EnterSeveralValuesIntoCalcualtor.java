package com.rpn.fixtures;

import java.math.BigDecimal;

import com.rpn.RpnCalculator;

public class EnterSeveralValuesIntoCalcualtor {
    void process(RpnCalculator calculator, String values) {
        if (values == null || values.length() == 0)
            return;

        String[] tokens = values.split(" +");

        for (String current : tokens)
            calculator.enter(new BigDecimal(current));
    }
}
