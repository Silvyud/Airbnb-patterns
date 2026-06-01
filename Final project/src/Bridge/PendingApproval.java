package Bridge;

public class PendingApproval extends Reservation {

    public PendingApproval(String accommodation, Account account, Payment payment) {
        super(accommodation, account, payment);
    }

    @Override
    public String makeReservation(double amount) {
        String resultadoPago = payment.makePayment(getAccount(), amount);

        if (resultadoPago.contains("ERROR")) {
            return "Solicitud Rechazada para [" + getAccommodation() + "].\nMotivo: " + resultadoPago;
        }

        return "Reserva con Aprobación Pendiente para [" + getAccommodation() + "].\n"
                + resultadoPago + "\nEstado: Esperando respuesta del anfitrión...";
    }

}
