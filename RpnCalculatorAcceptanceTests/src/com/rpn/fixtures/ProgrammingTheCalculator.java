package com.rpn.fixtures;

import java.math.BigDecimal;

import com.rpn.RpnCalculator;


public class ProgrammingTheCalculator {
    private String programName;

    public ProgrammingTheCalculator() {
        new ExecuteCalculator().reset();
    }
    
    private RpnCalculator calculator() {
        return TheCalculator.instance;
    }
    
    public void reset() {
        TheCalculator.reset();
    }
    
    public boolean xIs(int value) {
        return calculator().getDisplay().compareTo(new BigDecimal(value)) == 0;
    }
    
    public void enter(String value) {
        calculator().enter(new BigDecimal(value));
    }
    
    public void perform(String operatorName) {
        calculator().perform(operatorName);
    }
    
    public void startProgram() {
        calculator().start();
    }
    
    public void addStep(String operatorName) {
        calculator().addStep(operatorName);
    }
    
    public void save(String programName) {
        calculator().save(programName);
    }
    
    public void setName(String programName) {
        this.programName = programName;
    }
    
    public void setSteps(String steps) {
        calculator().addMacroNamed(programName, steps);
    }
    
    public void setGiven(String values) {
        new EnterSeveralValuesIntoCalcualtor().process(calculator(), values);
    }
    
    public String result() {
        return then();
    }
    
    public String then() {
        calculator().perform(programName);
        return new DestructiveCalculatorResultGenerator(calculator()).report();
    }
}
