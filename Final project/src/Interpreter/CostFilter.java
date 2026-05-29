package Interpreter;

import Utilities.Lodging;

public class CostFilter extends DoubleValueFilter{
    public CostFilter(double value, InequationEvaluator evaluator) {
        super(value, evaluator);
    }
    @Override
    public boolean evaluate(Lodging lodging) {
        return evaluator.evaluate(lodging.getCostPerNight(), value);
    }
}
