package Facade;

public class Lodging {
    private final String id;
    private final String location;
    private final int numOfRooms;
    private final int numOfBathrooms;
    private final double costPerNight;
    private boolean reserved;
    private final Owner owner;

    public Lodging(String id, String location, int numOfRooms, int numOfBathrooms, double costPerNight, boolean reserved, Owner owner) {
        this.id = id;
        this.location = location;
        this.numOfRooms = numOfRooms;
        this.numOfBathrooms = numOfBathrooms;
        this.costPerNight = costPerNight;
        this.reserved = reserved;
        this.owner = owner;
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
    public String getLocation() {
        return location;
    }

    public int getNumOfRooms() {
        return numOfRooms;
    }

    public int getNumOfBathrooms() {
        return numOfBathrooms;
    }

    @Override
    public String toString() {
        return "Lodging in " + location + " with " + numOfRooms + " rooms and " + numOfBathrooms + " bathrooms";
    }

    public Owner getOwner() {
        return owner;
    }
}