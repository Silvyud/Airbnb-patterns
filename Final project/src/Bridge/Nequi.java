package Bridge;

public class Nequi implements Payment {

    @Override
    public String makePayment(Account account, double amount) {
        if (account.nequiDeduct(amount)) {
            return "Pago exitoso de $" + amount + " por Nequi. Saldo restante: $" + account.getNequiBalance();
        }
        return "ERROR: Fondos insuficientes en Nequi para " + account.getOwner();
    }

}