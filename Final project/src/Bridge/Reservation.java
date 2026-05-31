package Bridge;

public abstract class Reservation {

    private String accommodation;
    protected Payment payment; // El puente hacia la interfaz Payment

    public Reservation(String accommodation, Payment payment) {
        this.accommodation = accommodation;
        this.payment = payment;
    }

    public abstract String makeReservation(double amount);

    public String getAccommodation() {
        return accommodation;
    }

}
