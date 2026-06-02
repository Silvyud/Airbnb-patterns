package Mediator;

import Utilities.Lodging;

import java.util.HashMap;

public class Mediator {
    private final HashMap<String, Owner> owners;
    private final HashMap<String, Lodging> bookings;
    private final double ownerPercentage;

    public Mediator() {
        owners = new HashMap<>();
        bookings = new HashMap<>();
        ownerPercentage = 0.8; // Owners receive 80% of the booking fee
    }

    public Mediator(double ownerPercentage) {
        this.ownerPercentage = ownerPercentage;
        owners = new HashMap<>();
        bookings = new HashMap<>();
    }

    public void publishLodging(Owner owner, Lodging lodging) {
        owners.put(lodging.getId(), owner);
        bookings.put(lodging.getId(), lodging);
    }

    public boolean reserveLodging(User user, String lodgingId, int days) {
        if (bookings.containsKey(lodgingId)) {
            Lodging lodging = bookings.get(lodgingId);
            if (!lodging.isReserved() && user.pay(lodging.getCostPerNight()* days)) {
                lodging.setReserved(true);
                Owner owner = owners.get(lodgingId);
                owner.addToBalance(lodging.getCostPerNight() * ownerPercentage);
                return true;
            }
        }
        return false;
    }
}
