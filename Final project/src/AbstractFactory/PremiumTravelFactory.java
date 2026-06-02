package AbstractFactory;

public class PremiumTravelFactory implements TravelBundleFactory {

    @Override
    public Accommodation createAccommodation() { return new PremiumVilla(); }
    @Override
    public Transport createTransport() { return new PremiumDriver(); }
    @Override
    public Experience createExperience() { return new YachtTour(); }

}
