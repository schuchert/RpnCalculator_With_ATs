package com.rpn;

import java.math.BigDecimal;

import org.junit.*;

import static org.junit.Assert.*;

public class RpnCalculatorShould {
    private RpnCalculator calculator;

    @Before
    public void init() {
        calculator = new RpnCalculator();
    }

    @Test
    public void notHaveOperandsAvailable() {
        assertFalse(calculator.hasOperands());
    }
    
    @Test
    public void haveOperandsAfterEnter() {
        calculator.enter(4);
        assertTrue(calculator.hasOperands());
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
    
    @Test
    public void allowProgamming() {
        calculator.start();
        calculator.addStep("+");
        calculator.addStep("-");
        calculator.save("+-");
        calculator.enter(10);
        calculator.enter(30);
        calculator.enter(4);
        calculator.perform("+-");
        assertEquals(new BigDecimal(-24), calculator.getDisplay());
    }
}
