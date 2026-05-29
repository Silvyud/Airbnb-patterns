package Interpreter;

public class LessEqualEval implements InequationEvaluator{
    @Override
    public boolean evaluate(double lvalue, double value) {
        return lvalue <= value;
    }
}
