package Interpreter;

public class GreaterEqualEval implements InequationEvaluator{
    @Override
    public boolean evaluate(double lvalue, double value) {
        return lvalue >= value;
    }
}
