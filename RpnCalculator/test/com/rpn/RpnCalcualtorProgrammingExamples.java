package com.rpn;

import java.math.BigDecimal;

import org.junit.Test;

import static org.junit.Assert.*;

public class RpnCalcualtorProgrammingExamples {
    RpnCalculator calculator = new RpnCalculator();
    
    void enterValue(int value) {
        calculator.enter(new BigDecimal(value));
    }

    @Test
    public void createNewMacro() {
        calculator.addMacroNamed("foo", "* + -");
        enterValue(4);
        enterValue(4);
        enterValue(4);
        enterValue(4);
        calculator.perform("foo");
        assertEquals(new BigDecimal(-16), calculator.getDisplay());
    }
}
