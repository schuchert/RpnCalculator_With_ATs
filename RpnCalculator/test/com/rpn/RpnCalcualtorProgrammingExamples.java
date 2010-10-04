package com.rpn;

import java.math.BigDecimal;

import org.junit.Test;

import static org.junit.Assert.*;

public class RpnCalcualtorProgrammingExamples {
    @Test
    public void createNewMacro() {
        RpnCalculator calculator = new RpnCalculator();
        calculator.addMacroNamed("foo", "* + -");
        calculator.enter(4);
        calculator.enter(4);
        calculator.enter(4);
        calculator.enter(4);
        calculator.perform("foo");
        assertEquals(new BigDecimal(-16), calculator.getDisplay());
    }
}
