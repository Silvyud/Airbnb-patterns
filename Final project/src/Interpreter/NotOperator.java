package Interpreter;

public class NotOperator extends FilterExpression{
    private final FilterExpression expression;

    public NotOperator(FilterExpression expression) {
        this.expression = expression;
    }

    @Override
    public boolean evaluate(Utilities.Lodging lodging) {
        return !expression.evaluate(lodging);
    }
}
