package Bridge;

public class PayPal implements Payment {

    @Override
    public String makePayment(Account account, double amount) {
        if (account.deduct(amount)) {
            return "Pago exitoso de $" + amount + " por PayPal. Saldo restante: $" + account.getBalance();
        }
        return "ERROR: Fondos insuficientes en PayPal para " + account.getOwner();
    }

}