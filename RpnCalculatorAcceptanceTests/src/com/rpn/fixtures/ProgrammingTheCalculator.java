package com.rpn.fixtures;


public class ProgrammingTheCalculator {

    public boolean xIs(int value) {
        return ExecuteCalculator.calculator.getDisplay() == value;
    }
    
    public void enter(int value) {
        ExecuteCalculator.calculator.enter(value);
    }
    
    public void perform(String operatorName) {
        ExecuteCalculator.calculator.perform(operatorName);
    }
    
    public void startProgram() {
        ExecuteCalculator.calculator.start();
    }
    
    public void addStep(String operatorName) {
        ExecuteCalculator.calculator.addStep(operatorName);
    }
    
    public void save(String programName) {
        ExecuteCalculator.calculator.save(programName);
    }
}
