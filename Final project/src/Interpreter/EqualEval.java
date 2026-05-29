package Interpreter;

public class EqualEval implements InequationEvaluator{
    @Override
    public boolean evaluate(double lvalue, double value) {
        return lvalue == value;
    }
}
