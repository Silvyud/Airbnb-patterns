package Bridge;

public class Account {

    private String owner;
    private double nequiBalance;
    private double payPalBalance;

    public Account(String owner, double nequiBalance, double payPalBalance) {
        this.owner = owner;
        this.nequiBalance = nequiBalance;
        this.payPalBalance = payPalBalance;
    }

    public String getOwner() {
        return owner;
    }

    public double getNequiBalance() {
        return nequiBalance;
    }

    public double getPayPalBalance() {
        return payPalBalance;
    }

    // Metodo clave para descontar el dinero
    public boolean nequiDeduct(double amount) {
        if (nequiBalance >= amount) {
            nequiBalance -= amount;
            return true;
        }
        return false;
    }

    public boolean payPalDeduct(double amount) {
        if (payPalBalance >= amount) {
            payPalBalance -= amount;
            return true;
        }
        return false;
    }

}