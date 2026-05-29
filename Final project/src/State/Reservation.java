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

    public String confirm() { return state.confirm(this); }
    public String pay() { return state.pay(this); }
    public String checkIn() { return state.checkIn(this); }
    public String checkOut() { return state.checkOut(this); }
    public String cancel() { return state.cancel(this); }
    public String requestRefund() { return state.requestRefund(this); }

    public void setState(ReservationState state) { this.state = state; }

    public String getGuestName()    { return guestName; }
    public String getPropertyName() { return propertyName; }
    public double getTotalPrice()   { return totalPrice; }
    public String getStateName()    { return state.getClass().getSimpleName(); }
    public LocalDate getCheckIn() { return checkIn; }
    public LocalDate getCheckOut() { return checkOut; }


    public void setCheckIn(LocalDate checkIn) { this.checkIn = checkIn; }
    public void setCheckOut(LocalDate checkOut) { this.checkOut = checkOut; }

    public String print() {
        return "Reservation created for: " + getGuestName() +
                "\nProperty: " + getPropertyName() +
                "\nTotal price: $" + String.valueOf(getTotalPrice()) +
                "\nCurrent state: " + getStateName();
    }

}