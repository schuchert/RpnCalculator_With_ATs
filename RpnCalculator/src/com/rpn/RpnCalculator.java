package com.rpn;

import java.math.BigDecimal;

import com.rpn.factory.OperatorFactory;
import com.rpn.operators.stateful.Macro;

public class RpnCalculator {
    private RpnStack values = new RpnStack();
    private OperatorFactory factory;
    private Macro currentMacro;
    private Macro newProgram;

    public RpnCalculator() {
        factory = new OperatorFactory();
    }

    public RpnCalculator(OperatorFactory factory) {
        this.factory = factory;
    }

    public void enter(int value) {
        values.push(value);
    }

    public void perform(String operatorName) {
        IOperator op = factory.findOperatorNamed(operatorName);
        op.execute(values);
    }

    public BigDecimal getDisplay() {
        return values.peek();
    }

    public boolean hasOperands() {
        return values.size() > 0;
    }

    public void addMacroNamed(String candidateName, String programText) {
        newProgram = new RpnProgrammer(factory).compile(candidateName, programText);
        factory.add(newProgram.name, newProgram);
    }

    public void start() {
        currentMacro = new Macro();
    }

    public void addStep(String operatorName) {
        IOperator step = factory.findOperatorNamed(operatorName);
        currentMacro.append(step);
    }

    public void save(String programName) {
        factory.add(programName, currentMacro);
    }
}
