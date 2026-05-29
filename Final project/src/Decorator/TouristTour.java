package Decorator;

public class TouristTour extends AditionalService {

    public TouristTour(Reservation toAdd) {
        super(toAdd);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + "\nIncludes a tourist tour.";
    }

    @Override
    public double getPrice() {
        return super.getPrice() + 65;
    }

}
