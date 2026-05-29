package State;

import java.time.LocalDate;

public class InProgress extends ReservationState {

    @Override
    public String checkOut(Reservation r) {
        r.setCheckOut(LocalDate.now());
        r.setState(new Completed());
        return "Check-out realizado. ¡Hasta pronto, "
            + r.getGuestName() + "! Esperamos tu reseña.";
    }
    @Override
    public String cancel(Reservation r) {
        r.setState(new Canceled());
        return "Cancelación durante la estancia. Penalización del 70%: $"
            + String.valueOf(r.getTotalPrice() * 0.70);
    }

}
