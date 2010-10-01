package com.rpn.testutil;

import static junit.framework.Assert.assertEquals;

import java.math.BigDecimal;

public class BigDecimalTestUtil {
    public static void assertThatValuesMatch(int expected, BigDecimal actual) {
        assertEquals(new BigDecimal(expected), actual);
    }
}
