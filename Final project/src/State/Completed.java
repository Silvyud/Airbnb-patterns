package State;

public class Completed extends ReservationState {

    @Override
    public void requestRefund(Reservation r) {
        System.out.println("Reembolso según política de Airbnb evaluado para: "
            + r.getPropertyName());
    }

}
