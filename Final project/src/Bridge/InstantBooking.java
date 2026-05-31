package Bridge;

public class InstantBooking extends Reservation {

    public InstantBooking(String accommodation, Payment payment) {
        super(accommodation, payment);
    }

    @Override
    public String makeReservation(double amount) {
        return "Reserva Instantánea para [" + getAccommodation() + "] por valor de $" + amount + ".\n"
                + payment.makePayment() + "\n¡Confirmada de inmediato!";
    }

}
