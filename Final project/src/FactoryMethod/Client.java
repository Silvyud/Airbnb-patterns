package FactoryMethod;

import Utilities.Lodging;

public class Client extends User{
    private Lodging myReservation;

    public Client(String nickname) {
        super(nickname);
    }

    public Lodging getMyReservation() {
        return myReservation;
    }

    public void setMyReservation(Lodging myReservation) {
        this.myReservation = myReservation;
    }
}
