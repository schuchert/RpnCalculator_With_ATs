package com.rpn.operators.stateless;

import java.math.BigDecimal;

import org.junit.Test;

import com.rpn.RpnStack;
import com.rpn.objectmothers.RpnStackObjectMother;

import static org.junit.Assert.assertEquals;

public class PrimeFactorsTest {
    RpnStack values = RpnStackObjectMother.build();
    
    @Test
    public void of1() {
        givenAValueOf(1);
        whenCalculatingItsPrimeFactors();
        thenExpect(0);
        andNothingElse();
    }

    @Test
    public void of2() {
        givenAValueOf(2);
        whenCalculatingItsPrimeFactors();
        thenExpect(2);
        andNothingElse();
    }
    
    private void andNothingElse() {
        assertEquals(0, values.size());
    }

    private void thenExpect(int value) {
        assertEquals(new BigDecimal(value), values.pop());
    }

    private void whenCalculatingItsPrimeFactors() {
        new PrimeFactors().execute(values);
    }

    private void givenAValueOf(int value) {
        values.push(value);
    }
}
