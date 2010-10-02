package com.rpn;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.rpn.RpnCalculator;

public class RpnCalculatorShould {
    private RpnCalculator calculator;

    @Before
    public void init() {
        calculator = new RpnCalculator();
    }

    @Test
    public void calculateFactorial() {
        calculator.enter(5);
        calculator.perform("!");
        assertEquals(new BigDecimal(120), calculator.getDisplay());
    }

    @Test
    public void addTwoNumbersCorrectly() {
        calculator.enter(30);
        calculator.enter(4);
        calculator.perform("+");
        assertEquals(new BigDecimal(34), calculator.getDisplay());
    }
}
