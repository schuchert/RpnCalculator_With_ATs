package uncle;

import java.math.BigDecimal;

import com.rpn.RpnCalculator;

public class Bob {
    RpnCalculator calcualtor = new RpnCalculator();
    private String operatorName;

    public void reset() {
        calcualtor = new RpnCalculator();
    }

    public void setGiven(String value) {
        enterValue(value);
    }

    private void enterValue(String value) {
        if (value.length() > 0)
            calcualtor.enter(new BigDecimal(value));
    }

    public void setAnd(String value) {
        enterValue(value);
    }

    public void setWhen(String operatorName) {
        this.operatorName = operatorName;
    }

    public String Then() {
        try {
            calcualtor.perform(operatorName);
            return calcualtor.getDisplay().toString();
        } catch (Exception e) {
            return "<error>";
        }
    }
}
