package State;

public abstract class ReservationState {

    public void confirm(Reservation r) {
        System.out.println("Acción no permitida en el estado: "
            + r.getStateName());
    }

    public void pay(Reservation r) {
        System.out.println("Acción no permitida en el estado: "
            + r.getStateName());
    }

    public void checkIn(Reservation r) {
        System.out.println("Acción no permitida en el estado: "
            + r.getStateName());
    }

    public void checkOut(Reservation r) {
        System.out.println("Acción no permitida en el estado: "
                + r.getStateName());
    }

    public void cancel(Reservation r) {
        System.out.println("Acción no permitida en el estado: "
            + r.getStateName());
    }

    public void requestRefund(Reservation r) {
        System.out.println("Acción no permitida en el estado: "
            + r.getStateName());
    }

}