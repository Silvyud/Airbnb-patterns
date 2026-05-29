package State;

public class Pending extends ReservationState {

    @Override
    public String confirm(Reservation r) {
        r.setState(new Confirmed());
        return "Reserva confirmada por el anfitrión.";
    }
    @Override
    public String cancel(Reservation r) {
        r.setState(new Canceled());
        return "Reserva cancelada. Sin penalización.";
    }

}
