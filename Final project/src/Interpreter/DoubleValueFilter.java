package Interpreter;

public abstract class DoubleValueFilter extends FilterExpression{
    protected final double value;
    protected final InequationEvaluator evaluator;

    public DoubleValueFilter(double value, InequationEvaluator evaluator) {
        this.value = value;
        this.evaluator = evaluator;
    }
}