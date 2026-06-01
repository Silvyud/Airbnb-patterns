package Bridge;

// Abstracción Base
public abstract class Reservation {

    private String accommodation;
    private Account account;
    protected Payment payment;

    public Reservation(String accommodation, Account account, Payment payment) {
        this.accommodation = accommodation;
        this.account = account;
        this.payment = payment;
    }

    public abstract String makeReservation(double amount);

    public String getAccommodation() {
        return accommodation;
    }

    public Account getAccount() {
        return account;
    }

}