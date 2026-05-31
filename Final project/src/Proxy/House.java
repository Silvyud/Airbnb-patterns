package Proxy;

// Subclase Concreta: House
public class House extends Accommodation {

    private int houseNum;

    public House(String actualGuest, String city, String town, String street, int houseNum) {
        super(actualGuest, city, town, street);
        this.houseNum = houseNum;
    }

    @Override
    public String checkLocation() {
        return super.checkLocation() + " Casa Número: " + houseNum;
    }

}