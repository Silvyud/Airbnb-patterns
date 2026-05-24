public class Owner {
    private final String name;
    private double balance;
    private final Mediator mediator;

    public Owner(String name, double balance, Mediator mediator) {
        this.name = name;
        this.balance = balance;
        this.mediator = mediator;
    }

    public double getBalance() {
        return balance;
    }

    public void addToBalance(double amount) {
        balance += amount;
    }

    public void publishLodging(Lodging lodging) {
        mediator.publishLodging(this, lodging);
    }
}
