package FactoryMethod;

import Utilities.Lodging;

import java.util.ArrayList;

public class Owner extends User{
    private ArrayList<Lodging> lodgings;

    public Owner(String nickname) {
        super(nickname);
        this.lodgings = new ArrayList<>();
    }

    public ArrayList<Lodging> getLodgings() {
        return lodgings;
    }
}
