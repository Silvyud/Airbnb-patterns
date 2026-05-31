package Bridge;

public class Nequi implements Payment {

    @Override
    public String makePayment() {
        return "Pago procesado a través de Nequi.";
    }

}