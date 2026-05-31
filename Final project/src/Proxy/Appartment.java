package Proxy;

// Subclase Concreta: Appartment
public class Appartment extends Accommodation {

    private int apartNum;
    private String section;

    public Appartment(String actualGuest, String city, String town, String street, int apartNum, String section) {
        super(actualGuest, city, town, street);
        this.apartNum = apartNum;
        this.section = section;
    }

    @Override
    public String checkLocation() {
        return super.checkLocation() + " Apto: " + apartNum + ", Torre/Bloque: " + section;
    }

}