package State;

import java.time.LocalDate;

public class Payed extends ReservationState {

    @Override
    public String checkIn(Reservation r) {
        r.setCheckIn(LocalDate.now());
        r.setState(new InProgress());
        return "Check-in realizado. ¡Bienvenido, "
                + r.getGuestName() + "!";
    }
    @Override
    public String cancel(Reservation r) {
        r.setState(new Canceled());
        return "Reserva cancelada. Penalización del 50%: $"
                + String.valueOf(r.getTotalPrice() * 0.50);
    }
    @Override
    public String requestRefund(Reservation r) {
        return "Reembolso parcial procesado: $"
            + String.valueOf(r.getTotalPrice() * 0.50);
    }

}