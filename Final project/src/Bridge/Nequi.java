package Bridge;

public class Nequi implements Payment {

    @Override
    public String makePayment(Account account, double amount) {
        if (account.deduct(amount)) {
            return "Pago exitoso de $" + amount + " por Nequi. Saldo restante: $" + account.getBalance();
        }
        return "ERROR: Fondos insuficientes en Nequi para " + account.getOwner();
    }

}