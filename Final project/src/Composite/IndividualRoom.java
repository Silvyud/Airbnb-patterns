package Composite;

// Elemento Hoja (Leaf)
public class IndividualRoom implements RentalUnit {

    private int roomNum;
    private int maxCapacity;

    public IndividualRoom(int roomNum, int maxCapacity) {
        this.roomNum = roomNum;
        this.maxCapacity = maxCapacity;
    }

    @Override
    public int getCapacity() {
        return maxCapacity;
    }

    @Override
    public String toString() {
        return "Habitación #" + roomNum + " (Capacidad: " + maxCapacity + " personas)";
    }

}