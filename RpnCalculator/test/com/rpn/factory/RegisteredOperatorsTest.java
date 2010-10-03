package com.rpn.factory;

import java.util.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.*;
import org.junit.runners.Parameterized.Parameters;

import com.rpn.*;
import com.rpn.operators.stateless.*;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class RegisteredOperatorsTest {
    OperatorFactory factory = new OperatorFactory();
    private final String operatorName;
    private final Class<?> clazz;

    @Parameters
    public static Collection<?> data() {
        ArrayList<Object[]> values = new ArrayList<Object[]>();
        addInto(values, "-", Subtract.class);
        addInto(values, "+", Add.class);
        addInto(values, "*", Multiply.class);
        addInto(values, "/", Divide.class);
        addInto(values, "!", Factorial.class);
        addInto(values, "<", LessThen.class);
        addInto(values, "<=", LessThenOrEqualTo.class);
        addInto(values, ">", GreaterThen.class);
        addInto(values, ">=", GreaterThenOrEqualTo.class);
        addInto(values, "==", EqualTo.class);
        addInto(values, "%", Modulo.class);
        addInto(values, "sum", Sum.class);
        addInto(values, "swap", Swap.class);
        addInto(values, "drop", Drop.class);
        addInto(values, "dup", Dup.class);
        addInto(values, "ndup", NDup.class);
        addInto(values, "nop", Nop.class);
        addInto(values, "not", Not.class);
        addInto(values, "primeFactors", PrimeFactors.class);
        return values;
    }

    private static void addInto(ArrayList<Object[]> list, String operatorName, Class<?> clazz) {
        list.add(new Object[] { operatorName, clazz });
    }

    public RegisteredOperatorsTest(String operatorName, Class<?> clazz) {
        this.operatorName = operatorName;
        this.clazz = clazz;
    }

    @Test
    public void operatorIsRegistered() {
        try {
            IOperator op = factory.findOperatorNamed(operatorName);
            assertTrue(op.getClass().equals(clazz));
            assertEquals("Not all registered operators being checked", factory.operators.size(), data().size());
        } catch (UnknownOperatorException e) {
            fail(String.format("Could not find: '%s'", operatorName));
        }
    }
}
