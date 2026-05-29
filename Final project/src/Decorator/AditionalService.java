package Decorator;

public class AditionalService extends Reservation {

    private Reservation toAdd;

    AditionalService(Reservation toAdd) {
        this.toAdd = toAdd;
    }

    @Override
    public double getPrice() {
        return toAdd.getPrice();
    }

    @Override
    public String getDescription() {
        return toAdd.getDescription();
    }

}
