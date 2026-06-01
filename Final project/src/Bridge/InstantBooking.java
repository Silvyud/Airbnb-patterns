package Bridge;

public class InstantBooking extends Reservation {

    public InstantBooking(String accommodation, Account account, Payment payment) {
        super(accommodation, account, payment);
    }

    @Override
    public String makeReservation(double amount) {
        String resultadoPago = payment.makePayment(getAccount(), amount);

        if (resultadoPago.contains("ERROR")) {
            return "Reserva Cancelada para [" + getAccommodation() + "].\nMotivo: " + resultadoPago;
        }

        return "Reserva Instantánea para [" + getAccommodation() + "] procesada.\n"
                + resultadoPago + "\n¡Confirmada de inmediato!";
    }

}