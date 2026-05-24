package State;

public class Confirmed extends ReservationState {

    @Override
    public void pay(Reservation r) {
        System.out.println("Pago realizado exitosamente.");
        r.setState(new Payed());
    }
    @Override
    public void cancel(Reservation r) {
        System.out.println("Reserva cancelada. Penalización del 30%: $"
            + r.getTotalPrice() * 0.30);
        r.setState(new Canceled());
    }
    @Override
    public void requestRefund(Reservation r) {
        System.out.println("Reembolso total procesado: $" + r.getTotalPrice());
    }

}
