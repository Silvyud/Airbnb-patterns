package Mediator;

public class User{
    private final String name;
    private final String id;
    private double balance;
    private final Mediator mediator;

    public User(String name, String id, double balance, Mediator mediator) {
        this.name = name;
        this.id = id;
        this.balance = balance;
        this.mediator = mediator;
    }

    public String getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public boolean pay(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public boolean reserveLodging(String lodgingId, int days) {
        return mediator.reserveLodging(this, lodgingId, days);
    }
}
