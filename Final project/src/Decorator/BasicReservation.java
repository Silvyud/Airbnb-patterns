package Decorator;

public class BasicReservation extends Reservation{

    private final double price;

    public BasicReservation(double price) {
        this.price = price;
    }

    @Override
    public String getDescription() {
        return "Normal reservation";
    }

    @Override
    public double getPrice() {
        return price;
    }

}
