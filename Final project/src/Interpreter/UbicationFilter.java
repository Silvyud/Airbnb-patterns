package Interpreter;

import Utilities.Lodging;

public class UbicationFilter extends FilterExpression{
    private final String ubication;

    public UbicationFilter(String ubication) {
        this.ubication = ubication;
    }
    public boolean evaluate(Lodging lodging) {
        return lodging.getUbication().equalsIgnoreCase(ubication);
    }
}
