package Bridge;

public class PayPal implements Payment {

    @Override
    public String makePayment() {
        return "Pago procesado a través de PayPal.";
    }

}
