package Interpreter;

import Utilities.Lodging;

public class RoomFilter extends DoubleValueFilter{
    public RoomFilter(double value, InequationEvaluator evaluator) {
        super(value, evaluator);
    }

    @Override
    public boolean evaluate(Lodging lodging) {
        return evaluator.evaluate(lodging.getNumOfRooms(), value);
    }
}
