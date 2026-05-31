package Bridge;

public class PendingApproval extends Reservation {

    public PendingApproval(String accommodation, Payment payment) {
        super(accommodation, payment);
    }

    @Override
    public String makeReservation(double amount) {
        return "Reserva con Aprobación Pendiente para [" + getAccommodation() + "] por valor de $" + amount + ".\n"
                + "Estado: Esperando respuesta del anfitrión...\n"
                + payment.makePayment();
    }

}
