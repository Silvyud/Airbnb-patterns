package Decorator;

public class CleaningService extends AditionalService{

    public CleaningService(Reservation toAdd) {
        super(toAdd);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + "\nIncludes a cleaning service.";
    }

    @Override
    public double getPrice() {
        return super.getPrice() + 50;
    }

}
