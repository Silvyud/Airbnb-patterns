public class Lodging {
    private String id;
    private String ubication;
    private int numOfRooms;
    private int numOfBathrooms;
    private double costPerNight;
    private boolean reserved;

    public Lodging(String id, String ubication, int numOfRooms, int numOfBathrooms, double costPerNight, boolean reserved) {
        this.id = id;
        this.ubication = ubication;
        this.numOfRooms = numOfRooms;
        this.numOfBathrooms = numOfBathrooms;
        this.costPerNight = costPerNight;
        this.reserved = reserved;
    }
    public boolean isReserved() {
        return reserved;
    }
    public String getId() {
        return id;
    }
    public double getCostPerNight() {
        return costPerNight;
    }
    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    @Override
    public String toString() {
        return "Lodging in " + ubication + " with " + numOfRooms + " rooms and " + numOfBathrooms + " bathrooms";
    }
}
