package Prototype;

public class Publication implements Prototype {

    private String title;
    private String description;
    private double price;
    private int roomNumber;

    public Publication(String title, String description, double price, int roomNumber) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.roomNumber = roomNumber;
    }

    public Prototype clone() {
        Prototype pubProt = new Publication(this.title, this.description, this.price, this.roomNumber);
        return pubProt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

}
