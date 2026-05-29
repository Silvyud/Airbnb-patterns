package State;

public class Completed extends ReservationState {

    @Override
    public String requestRefund(Reservation r) {
        return "Reembolso según política de Airbnb evaluado para: "
            + r.getPropertyName();
    }

}
