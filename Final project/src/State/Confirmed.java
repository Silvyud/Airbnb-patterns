package State;

public class Confirmed extends ReservationState {

    @Override
    public String pay(Reservation r) {
        r.setState(new Payed());
        return "Pago realizado exitosamente.";
    }
    @Override
    public String cancel(Reservation r) {
        r.setState(new Canceled());
        return "Reserva cancelada. Penalización del 30%: $"
                + String.valueOf(r.getTotalPrice() * 0.30);
    }
    @Override
    public String requestRefund(Reservation r) {
        return "Reembolso total procesado: $" + String.valueOf(r.getTotalPrice());
    }

}
