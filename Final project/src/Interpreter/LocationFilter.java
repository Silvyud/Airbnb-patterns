package Interpreter;

import Utilities.Lodging;

public class LocationFilter extends FilterExpression{
    private final String value;

    public LocationFilter(String location) {
        this.value = location;
    }
    public boolean evaluate(Lodging lodging) {
        return lodging.getUbication().equalsIgnoreCase(value);
    }
}