package State;

public class Pending extends ReservationState {

    @Override
    public void confirm(Reservation r) {
        System.out.println("Reserva confirmada por el anfitrión.");
        r.setState(new Confirmed());
    }
    @Override
    public void cancel(Reservation r) {
        System.out.println("Reserva cancelada. Sin penalización.");
        r.setState(new Canceled());
    }

}
