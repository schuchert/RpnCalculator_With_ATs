package com.rpn.factory;

import java.util.concurrent.ConcurrentHashMap;

import com.rpn.IOperator;
import com.rpn.OperatorNameAlreadyInUse;
import com.rpn.RegisteredName;
import com.rpn.UnknownOperatorException;

public class OperatorFactory {
    ConcurrentHashMap<String, IOperator> operators = new ConcurrentHashMap<String, IOperator>();

    public OperatorFactory() {
        addFoundOperators();
    }

    protected void addFoundOperators() {
        OperatorEnumerator enumerator = new OperatorEnumerator();
        for (IOperator current : enumerator) {
            add(getName(current), current);
        }
    }

    private String getName(IOperator current) {
        RegisteredName rn = current.getClass().getAnnotation(RegisteredName.class);
        if (rn != null)
            return rn.value();
        return unqualified(current).toLowerCase();
    }

    private String unqualified(IOperator current) {
        String name = current.getClass().getName();
        return name.substring(name.lastIndexOf('.') + 1);
    }

    public IOperator findOperatorNamed(String operatorName) {
        IOperator op = operators.get(operatorName);
        if (op != null)
            return op;

        throw new UnknownOperatorException(String.format("Operator not found: %s", operatorName));
    }

    public void add(String candidateName, IOperator op) {
        validateAdd(candidateName, op);
        operators.put(candidateName, op);
    }

    private void validateAdd(String candidateName, IOperator op) {
        if (candidateName == null)
            throw new IllegalArgumentException("OperatorName cannot be null");
        if (candidateName.length() == 0)
            throw new IllegalArgumentException("OperatorName cannot be an empty string");
        if (op == null)
            throw new IllegalArgumentException("cannot add a null operator");
        if (operators.containsKey(candidateName))
            throw new OperatorNameAlreadyInUse(candidateName);
    }
}
