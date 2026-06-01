package AbstractFactory;

public class AdventurerTravelFactory implements TravelBundleFactory {

    @Override
    public Accommodation createAccommodation() { return new CabinInForest(); }
    @Override
    public Transport createTransport() { return new Truck4x4(); }
    @Override
    public Experience createExperience() { return new ClimbingTour(); }

}
