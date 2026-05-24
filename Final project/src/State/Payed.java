package State;

import java.time.LocalDate;

public class Payed extends ReservationState {

    @Override
    public void checkIn(Reservation r) {
        r.setCheckIn(LocalDate.now());
        System.out.println("Check-in realizado. ¡Bienvenido, "
            + r.getGuestName() + "!");
        r.setState(new InProgress());
    }
    @Override
    public void cancel(Reservation r) {
        System.out.println("Reserva cancelada. Penalización del 50%: $"
            + r.getTotalPrice() * 0.50);
        r.setState(new Canceled());
    }
    @Override
    public void requestRefund(Reservation r) {
        System.out.println("Reembolso parcial procesado: $"
            + r.getTotalPrice() * 0.50);
    }

}