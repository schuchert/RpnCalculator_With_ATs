package com.rpn.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.rpn.IOperator;
import com.rpn.UnknownOperatorException;
import com.rpn.operators.stateless.Add;
import com.rpn.operators.stateless.Divide;
import com.rpn.operators.stateless.Drop;
import com.rpn.operators.stateless.Dup;
import com.rpn.operators.stateless.EqualTo;
import com.rpn.operators.stateless.Factorial;
import com.rpn.operators.stateless.GreaterThen;
import com.rpn.operators.stateless.GreaterThenOrEqualTo;
import com.rpn.operators.stateless.LessThen;
import com.rpn.operators.stateless.LessThenOrEqualTo;
import com.rpn.operators.stateless.Multiply;
import com.rpn.operators.stateless.NDup;
import com.rpn.operators.stateless.Nop;
import com.rpn.operators.stateless.PrimeFactors;
import com.rpn.operators.stateless.Subtract;
import com.rpn.operators.stateless.Sum;
import com.rpn.operators.stateless.Swap;

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
        addInto(values, "sum", Sum.class);
        addInto(values, "swap", Swap.class);
        addInto(values, "drop", Drop.class);
        addInto(values, "dup", Dup.class);
        addInto(values, "ndup", NDup.class);
        addInto(values, "nop", Nop.class);
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
