package Decorator;

public class FoodService extends AditionalService {

    public FoodService(Reservation toAdd) {
        super(toAdd);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + "\nIncludes food service.";
    }

    @Override
    public double getPrice() {
        return super.getPrice() + 55;
    }
}
