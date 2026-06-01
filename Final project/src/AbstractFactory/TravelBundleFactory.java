package AbstractFactory;

public interface TravelBundleFactory {
    Accommodation createAccommodation();
    Transport createTransport();
    Experience createExperience();
}
