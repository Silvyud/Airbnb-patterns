package Facade;

import Utilities.User;

public class ReservationFacade {
    private User user;

    public ReservationFacade(User user) {
        this.user = user;
    }

    public boolean reservateLodging(Lodging lodging, int numOfNight) {
        double totalPay = lodging.getCostPerNight() * numOfNight;
        if(lodging.isReserved() && user.pay(totalPay)){
            Owner owner = lodging.getOwner();
            owner.addToBalnce(totalPay);
            return true;
        }
        return false;
    }
}
