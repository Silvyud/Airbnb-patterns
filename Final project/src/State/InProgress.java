package State;

import java.time.LocalDate;

public class InProgress extends ReservationState {

    @Override
    public void checkOut(Reservation r) {
        r.setCheckOut(LocalDate.now());
        System.out.println("Check-out realizado. ¡Hasta pronto, "
            + r.getGuestName() + "! Esperamos tu reseña.");
        r.setState(new Completed());
    }
    @Override
    public void cancel(Reservation r) {
        System.out.println("Cancelación durante la estancia. Penalización del 70%: $"
            + r.getTotalPrice() * 0.70);
        r.setState(new Canceled());
    }

}
