package Decorator;

public class RecreationActivities extends AditionalService {

    public RecreationActivities(Reservation toAdd) {
        super(toAdd);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + "\nIncludes a recreation activities.";
    }

    @Override
    public double getPrice() {
        return super.getPrice() + 70;
    }

}
