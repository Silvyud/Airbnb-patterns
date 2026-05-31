package Proxy;

public abstract class Accommodation implements AccommodationData {

    protected String actualGuest; // Huésped actual que reservó (puede ser null si está libre)
    protected String city;
    protected String town;
    protected String street;

    public Accommodation(String actualGuest, String city, String town, String street) {
        this.actualGuest = actualGuest;
        this.city = city;
        this.town = town;
        this.street = street;
    }

    // El objeto real expone la dirección exacta directamente
    @Override
    public String checkLocation() {
        return "Dirección Exacta: " + street + ", " + town + " (" + city + ").";
    }

    public String getActualGuest() {
        return actualGuest;
    }

}