package Utilities;

public class User{
    private final String name;
    private final String id;
    private double balance;

    public User(String name, String id, double balance) {
        this.name = name;
        this.id = id;
        this.balance = balance;
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

    public String getId() {
        return id;
    }
}
