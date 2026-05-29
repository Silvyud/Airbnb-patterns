package State;

public abstract class ReservationState {

    public String confirm(Reservation r) {
        return "Acción no permitida en el estado: "
            + r.getStateName();
    }

    public String pay(Reservation r) {
        return "Acción no permitida en el estado: "
            + r.getStateName();
    }

    public String checkIn(Reservation r) {
        return "Acción no permitida en el estado: "
            + r.getStateName();
    }

    public String checkOut(Reservation r) {
        return "Acción no permitida en el estado: "
                + r.getStateName();
    }

    public String cancel(Reservation r) {
        return "Acción no permitida en el estado: "
            + r.getStateName();
    }

    public String requestRefund(Reservation r) {
        return "Acción no permitida en el estado: "
            + r.getStateName();
    }

}