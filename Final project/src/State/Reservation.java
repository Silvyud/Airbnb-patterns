package State;

import java.time.LocalDate;

public class Reservation {

    private ReservationState state;
    private String guestName;
    private String propertyName;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private double totalPrice;

    public Reservation(String guestName, String propertyName, double totalPrice) {
        this.guestName = guestName;
        this.propertyName = propertyName;
        this.totalPrice = totalPrice;
        this.state = new Pending();
    }

    public void confirm() { state.confirm(this); }
    public void pay() { state.pay(this); }
    public void checkIn() { state.checkIn(this); }
    public void checkOut() { state.checkOut(this); }
    public void cancel() { state.cancel(this); }
    public void requestRefund() { state.requestRefund(this); }

    public void setState(ReservationState state) { this.state = state; }

    public String getGuestName()    { return guestName; }
    public String getPropertyName() { return propertyName; }
    public double getTotalPrice()   { return totalPrice; }
    public String getStateName()    { return state.getClass().getSimpleName(); }
    public LocalDate getCheckIn() { return checkIn; }
    public LocalDate getCheckOut() { return checkOut; }


    public void setCheckIn(LocalDate checkIn) { this.checkIn = checkIn; }
    public void setCheckOut(LocalDate checkOut) { this.checkOut = checkOut; }

    public void print() {
        System.out.println("Reservation created for: " + getGuestName());
        System.out.println("Property: " + getPropertyName());
        System.out.println("Total price: $" + getTotalPrice());
        System.out.println("Current state: " + getStateName());
    }

}