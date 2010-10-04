package com.rpn;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RpnCalcualtorAcceptanceTest {
    RpnCalculator calculator;

    @Before
    public void init() {
        calculator = new RpnCalculator();
    }

    void resultWas(int value) {
        assertEquals(new BigDecimal(value), calculator.getDisplay());
    }

    void enterValue(int value) {
        calculator.enter(new BigDecimal(value));
    }
    
    @Test
    public void addingTwoNumbers() {
        enterValue(30);
        enterValue(4);
        calculator.perform("+");
        resultWas(34);
    }

    @Test
    public void subtractingTwoNumbers() {
        enterValue(30);
        enterValue(4);
        calculator.perform("-");
        resultWas(26);
    }

    @Test
    public void factorialOf5() {
        enterValue(5);
        calculator.perform("!");
        resultWas(120);
    }

    @Test
    public void multiplyTwoNumbers() {
        enterValue(4);
        enterValue(6);
        calculator.perform("*");
        resultWas(24);
    }

    @Test
    public void divideTwoNumbers() {
        enterValue(8);
        enterValue(2);
        calculator.perform("/");
        resultWas(4);
    }

    @Test
    public void subtractWhenJustEnteringOneNumber() {
        enterValue(1);
        calculator.perform("-");
        resultWas(-1);
    }

    @Test
    public void addWithNoNumbersEntered() {
        calculator.perform("+");
        resultWas(0);
    }

    @Test(expected = ArithmeticException.class)
    public void divideByZeroGeneratesError() {
        enterValue(8);
        enterValue(0);
        calculator.perform("/");
    }

    @Test(expected = UnknownOperatorException.class)
    public void unknownOperator() {
        calculator.perform("(^I*#KHAunknown*&*");
    }

    @Test
    public void programmingTheCalculatorWithSingleCommand() {
        calculator.addMacroNamed("ams", "+ * -");
        verifyAmsMacroWorks();
    }

    @Test
    public void programmingTheCalculatorInMultipleSteps() {
        calculator.start();
        calculator.addStep("+");
        calculator.addStep("*");
        calculator.addStep("-");
        calculator.save("ams");
        verifyAmsMacroWorks();
    }

    private void verifyAmsMacroWorks() {
        enterValue(3);
        enterValue(5);
        enterValue(2);
        enterValue(13);
        calculator.perform("ams");
        assertEquals(new BigDecimal(-72), calculator.getDisplay());
    }

}
