package com.rpn;

public class OperatorNameAlreadyInUse extends RuntimeException {
    public OperatorNameAlreadyInUse(String candidateName) {
        super(candidateName);
    }

    private static final long serialVersionUID = 1L;
}
