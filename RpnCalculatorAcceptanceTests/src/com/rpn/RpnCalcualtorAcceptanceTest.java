package com.rpn;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class RpnCalcualtorAcceptanceTest {
    RpnCalculator calculator;

    @Before
    public void init() {
        calculator = new RpnCalculator();
    }

    void resultWas(int value) {
        assertEquals(value, calculator.getDisplay());
    }

    @Test
    public void addingTwoNumbers() {
        calculator.enter(30);
        calculator.enter(4);
        calculator.perform("+");
        resultWas(34);
    }

    @Test
    public void subtractingTwoNumbers() {
        calculator.enter(30);
        calculator.enter(4);
        calculator.perform("-");
        resultWas(26);
    }

    @Test
    public void factorialOf5() {
        calculator.enter(5);
        calculator.perform("!");
        resultWas(120);
    }

    @Test
    public void multiplyTwoNumbers() {
        calculator.enter(4);
        calculator.enter(6);
        calculator.perform("*");
        resultWas(24);
    }

    @Test
    public void divideTwoNumbers() {
        calculator.enter(8);
        calculator.enter(2);
        calculator.perform("/");
        resultWas(4);
    }

    @Test
    public void subtractWhenJustEnteringOneNumber() {
        calculator.enter(1);
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
        calculator.enter(8);
        calculator.enter(0);
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
        calculator.enter(3);
        calculator.enter(5);
        calculator.enter(2);
        calculator.enter(13);
        calculator.perform("ams");
        assertEquals(-72, calculator.getDisplay());
    }

}
