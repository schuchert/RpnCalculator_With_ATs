package com.rpn;

import java.math.BigDecimal;
import java.util.Stack;

import com.rpn.factory.OperatorFactory;
import com.rpn.operators.stateful.*;

public class RpnProgrammer {
    Stack<IOperator> operators = new Stack<IOperator>();

    private final OperatorFactory factory;

    public RpnProgrammer(OperatorFactory factory) {
        this.factory = factory;
    }

    public Macro compile(String name, String program) {
        String[] tokens = program.trim().split(" +");
        Macro m = new Macro(name.trim());
        operators.push(m);
        for (int i = 0; i < tokens.length; ++i) {
            String current = tokens[i];
            if (isRegularOperator(current))
                handleStatelessOperator(current);
            else if (isNumber(current))
                handlePushingOfAConstant(current);
            else if (isIf(current)) {
                beginIfStatement();
            } else if (isElse(current)) {
                moveToElsePartOfIf();
            } else if (isThen(current)) {
                completeIfStatement();
            }
        }
        return m;
    }

    private void completeIfStatement() {
        operators.pop();
        operators.pop();
    }

    private void moveToElsePartOfIf() {
        operators.pop();
        If ifOp = (If) operators.peek();
        operators.push(ifOp.falseBlock());
    }

    private void beginIfStatement() {
        If newOp = new If(new Macro(), new Macro());
        current().append(newOp);
        operators.push(newOp);
        operators.push(newOp.trueBlock());
    }

    private void handlePushingOfAConstant(String current) {
        current().append(new PushConstant(new BigDecimal(current)));
    }

    private void handleStatelessOperator(String current) {
        current().append(factory.findOperatorNamed(current));
    }

    private boolean isThen(String current) {
        return current.equals("then");
    }

    private boolean isElse(String current) {
        return current.equals("else");
    }

    Macro current() {
        return (Macro) operators.peek();
    }

    private boolean isIf(String current) {
        return current.equals("if");
    }

    private boolean isNumber(String current) {
        return current.matches("\\d*[.]*\\d+");
    }

    private boolean isRegularOperator(String token) {
        return factory.containsOperatorNamed(token);
    }

}
