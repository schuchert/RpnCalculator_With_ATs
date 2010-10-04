package com.rpn.fixtures;

import com.rpn.RpnCalculator;

public class TheCalculator {
    static RpnCalculator instance = new RpnCalculator();

    public static void reset() {
        instance = new RpnCalculator();
    }
}
