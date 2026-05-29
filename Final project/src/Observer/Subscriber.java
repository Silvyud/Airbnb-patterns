package Observer;

import Utilities.Lodging;

import java.util.ArrayList;

public class Subscriber {
    private String name;
    private final ArrayList<Lodging> preferredLodgings;

    public Subscriber(String name) {
        this.name = name;
        preferredLodgings = new ArrayList<>();
    }

    public void update(Lodging lodging) {
        preferredLodgings.add(lodging);
    }

    public ArrayList<Lodging> getReceivedLodgings() {
        return preferredLodgings;
    }
}
