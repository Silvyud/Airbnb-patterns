import Interpreter.FilterExpression;
import Utilities.Lodging;

import java.util.logging.Filter;

public class InterpreterCommand implements SuperCommand {

    private Lodging lodging;
    private FilterExpression filExp;

    public InterpreterCommand(Lodging lodging, FilterExpression filExp) {
        this.lodging = lodging;
        this.filExp = filExp;
    }

    public void setLodging(Lodging lodging) {
        this.lodging = lodging;
    }

    public void setFilExp(FilterExpression filExp) {
        this.filExp = filExp;
    }

    @Override
    public String execute() {
        return filExp.evaluate(lodging) ? "Lodging matches the filter criteria." : "Lodging does not match the filter criteria.";
    }
}
