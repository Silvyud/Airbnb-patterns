package Interpreter;

public class AndOperator extends ConnectionFilter{
    public AndOperator(FilterExpression expression1, FilterExpression expression2) {
        super(expression1, expression2);
    }

    @Override
    public boolean evaluate(Utilities.Lodging lodging) {
        return expression1.evaluate(lodging) && expression2.evaluate(lodging);
    }
}