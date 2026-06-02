package Command;

import Utilities.Lodging;

import java.util.ArrayList;

public class SeeLodgingsCommand implements Command{
    private final ArrayList<Lodging> lodgings;

    public SeeLodgingsCommand(ArrayList<Lodging> lodgings) {
        this.lodgings = lodgings;
    }
    @Override
    public String execute() {
        StringBuilder lodgingsString = new StringBuilder();
        for (Lodging lodging : lodgings) {
            lodgingsString.append(lodging.toString()).append("\n");
        }
        return lodgingsString.toString();
    }
}
