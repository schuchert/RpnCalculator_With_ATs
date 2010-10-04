package com.rpn.fixtures;

import com.rpn.RpnCalculator;

public class ExecuteCalculator {
    private static final EnterSeveralValuesIntoCalcualtor VALUE_ENTERER = new EnterSeveralValuesIntoCalcualtor();
    String operatorName;

    public ExecuteCalculator() {
        this("");
    }

    public ExecuteCalculator(String opeatorName) {
        this.operatorName = opeatorName;
    }

    public RpnCalculator calculator() {
        return TheCalculator.instance;
    }
    
    public void reset() {
        TheCalculator.reset();
    }

    public void setGiven(String value) {
        VALUE_ENTERER.process(calculator(), value);
    }

    public void setOf(String value) {
        VALUE_ENTERER.process(calculator(), value);
    }

    public void setV1(String value) {
        VALUE_ENTERER.process(calculator(), value);
    }

    public void setV2(String value) {
        VALUE_ENTERER.process(calculator(), value);
    }

    public void setWhen(String operatorName) {
        this.operatorName = operatorName;
    }

    public void setOperator(String operator) {
        this.operatorName = operator;
    }

    public void setWhenthestackhas(String value) {
        VALUE_ENTERER.process(calculator(), value);
    }

    public String then() {
        return result();
    }

    public String are() {
        return result();
    }

    public String result() {
        try {
            calculator().perform(operatorName);
            return new DestructiveCalculatorResultGenerator(TheCalculator.instance).report();
        } catch (Exception e) {
            return "<error>";
        }
    }
}
