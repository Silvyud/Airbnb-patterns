package Interpreter;

public abstract class ConnectionFilter extends FilterExpression{
    protected final FilterExpression expression1;
    protected final FilterExpression expression2;

    public ConnectionFilter(FilterExpression expression1, FilterExpression expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
    }
}
