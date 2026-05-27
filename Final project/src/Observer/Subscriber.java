package Observer;

import Utilities.Lodging;

import java.util.ArrayList;

public class Subscriber {
    private String name;
    private ArrayList<Lodging> myFavLodgings;

    public Subscriber(String name) {
        this.name = name;
        myFavLodgings = new ArrayList<>();
    }


    public void update(Lodging lodging) {
        myFavLodgings.add(lodging);
    }
}
