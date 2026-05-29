package Interpreter;

import Utilities.Lodging;

public class BathroomFilter extends DoubleValueFilter {
    public BathroomFilter(double value, InequationEvaluator evaluator) {
        super(value, evaluator);
    }

    @Override
    public boolean evaluate(Lodging lodging) {
        return evaluator.evaluate(lodging.getNumOfBathrooms(), value);
    }
}
