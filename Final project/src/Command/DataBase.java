package Command;

import Utilities.Lodging;

import java.util.HashMap;

public class DataBase {
    private final HashMap<String, String> reservations;
    private final HashMap<String, Lodging> lodgings;

    public DataBase(HashMap<String, String> reservations, HashMap<String, Lodging> lodgings) {
        this.reservations = reservations;
        this.lodgings = lodgings;
    }

    public Lodging getNextReservation(String userId){
        return lodgings.get(reservations.get(userId));
    }
}
