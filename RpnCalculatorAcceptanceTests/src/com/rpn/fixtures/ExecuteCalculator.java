package com.rpn.fixtures;

import com.rpn.RpnCalculator;

public class ExecuteCalculator {
    static RpnCalculator calculator = new RpnCalculator();
    String operatorName;

    public ExecuteCalculator() {
        this("");
    }

    public ExecuteCalculator(String opeatorName) {
        this.operatorName = opeatorName;
    }

    public void reset() {
        System.out.println("resetting calculator");
        calculator = new RpnCalculator();
    }

    private void enterNonBlank(String value) {
        if (value == null || value.length() == 0)
            return;

        String[] values = value.split(" +");
        
        for (String current : values)
            calculator.enter(Integer.parseInt(current));
    }

    public void setGiven(String value) {
        enterNonBlank(value);
    }

    public void setOf(String value) {
        enterNonBlank(value);
    }

    public void setV1(String value) {
        enterNonBlank(value);
    }

    public void setV2(String value) {
        enterNonBlank(value);
    }

    public void setWhen(String operatorName) {
        this.operatorName = operatorName;
    }

    public void setOperator(String operator) {
        this.operatorName = operator;
    }

    public void setWhenthestackhas(String value) {
        enterNonBlank(value);
    }
    
    public String then() {
        return result();
    }

    public String are() {
        return result();
    }
    
    public String result() {
        try {
            calculator.perform(operatorName);
            return destructivelyReportFullStack();
        } catch (Exception e) {
            return "<error>";
        }
    }

    private String destructivelyReportFullStack() {
        StringBuffer result = new StringBuffer();
        
        while (calculator.hasOperands()) 
            appendNextValueTo(result);

        return result.toString();
    }

    private void appendNextValueTo(StringBuffer result) {
        conditionallyAddSpace(result);
        appendCurrentValue(result);
        removeCurrentValue();
    }

    private void removeCurrentValue() {
        calculator.perform("drop");
    }

    private void appendCurrentValue(StringBuffer result) {
        result.insert(0, calculator.getDisplay());
    }

    private void conditionallyAddSpace(StringBuffer result) {
        if (result.length() > 0)
            result.insert(0, ' ');
    }
}
