package com.rpn;

import java.math.BigDecimal;

import org.junit.*;

import static org.junit.Assert.*;

public class RpnCalculatorShould {
    private RpnCalculator calculator;
    
    void enterValue(int value) {
        calculator.enter(new BigDecimal(value));
    }
    
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
        enterValue(4);
        assertTrue(calculator.hasOperands());
    }
    
    @Test
    public void calculateFactorial() {
        enterValue(5);
        calculator.perform("!");
        assertEquals(new BigDecimal(120), calculator.getDisplay());
    }

    @Test
    public void addTwoNumbersCorrectly() {
        enterValue(30);
        enterValue(4);
        calculator.perform("+");
        assertEquals(new BigDecimal(34), calculator.getDisplay());
    }
    
    @Test
    public void allowProgamming() {
        calculator.start();
        calculator.addStep("+");
        calculator.addStep("-");
        calculator.save("+-");
        enterValue(10);
        enterValue(30);
        enterValue(4);
        calculator.perform("+-");
        assertEquals(new BigDecimal(-24), calculator.getDisplay());
    }
}
